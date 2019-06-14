/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MStampComponentsPage, MStampDeleteDialog, MStampUpdatePage } from './m-stamp.page-object';

const expect = chai.expect;

describe('MStamp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mStampUpdatePage: MStampUpdatePage;
  let mStampComponentsPage: MStampComponentsPage;
  let mStampDeleteDialog: MStampDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MStamps', async () => {
    await navBarPage.goToEntity('m-stamp');
    mStampComponentsPage = new MStampComponentsPage();
    await browser.wait(ec.visibilityOf(mStampComponentsPage.title), 5000);
    expect(await mStampComponentsPage.getTitle()).to.eq('M Stamps');
  });

  it('should load create MStamp page', async () => {
    await mStampComponentsPage.clickOnCreateButton();
    mStampUpdatePage = new MStampUpdatePage();
    expect(await mStampUpdatePage.getPageTitle()).to.eq('Create or edit a M Stamp');
    await mStampUpdatePage.cancel();
  });

  it('should create and save MStamps', async () => {
    const nbButtonsBeforeCreate = await mStampComponentsPage.countDeleteButtons();

    await mStampComponentsPage.clickOnCreateButton();
    await promise.all([
      mStampUpdatePage.setNameInput('name'),
      mStampUpdatePage.setThumbnailAssetInput('thumbnailAsset'),
      mStampUpdatePage.setSoundEventInput('soundEvent'),
      mStampUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mStampUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mStampUpdatePage.getThumbnailAssetInput()).to.eq(
      'thumbnailAsset',
      'Expected ThumbnailAsset value to be equals to thumbnailAsset'
    );
    expect(await mStampUpdatePage.getSoundEventInput()).to.eq('soundEvent', 'Expected SoundEvent value to be equals to soundEvent');
    expect(await mStampUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    await mStampUpdatePage.save();
    expect(await mStampUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MStamp', async () => {
    const nbButtonsBeforeDelete = await mStampComponentsPage.countDeleteButtons();
    await mStampComponentsPage.clickOnLastDeleteButton();

    mStampDeleteDialog = new MStampDeleteDialog();
    expect(await mStampDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Stamp?');
    await mStampDeleteDialog.clickOnConfirmButton();

    expect(await mStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
