/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMedalComponentsPage, MMedalDeleteDialog, MMedalUpdatePage } from './m-medal.page-object';

const expect = chai.expect;

describe('MMedal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMedalUpdatePage: MMedalUpdatePage;
  let mMedalComponentsPage: MMedalComponentsPage;
  let mMedalDeleteDialog: MMedalDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMedals', async () => {
    await navBarPage.goToEntity('m-medal');
    mMedalComponentsPage = new MMedalComponentsPage();
    await browser.wait(ec.visibilityOf(mMedalComponentsPage.title), 5000);
    expect(await mMedalComponentsPage.getTitle()).to.eq('M Medals');
  });

  it('should load create MMedal page', async () => {
    await mMedalComponentsPage.clickOnCreateButton();
    mMedalUpdatePage = new MMedalUpdatePage();
    expect(await mMedalUpdatePage.getPageTitle()).to.eq('Create or edit a M Medal');
    await mMedalUpdatePage.cancel();
  });

  it('should create and save MMedals', async () => {
    const nbButtonsBeforeCreate = await mMedalComponentsPage.countDeleteButtons();

    await mMedalComponentsPage.clickOnCreateButton();
    await promise.all([
      mMedalUpdatePage.setMedalTypeInput('5'),
      mMedalUpdatePage.setNameInput('name'),
      mMedalUpdatePage.setDescriptionInput('description'),
      mMedalUpdatePage.setMaxAmountInput('5'),
      mMedalUpdatePage.setIconAssetNameInput('iconAssetName'),
      mMedalUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName')
    ]);
    expect(await mMedalUpdatePage.getMedalTypeInput()).to.eq('5', 'Expected medalType value to be equals to 5');
    expect(await mMedalUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mMedalUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await mMedalUpdatePage.getMaxAmountInput()).to.eq('5', 'Expected maxAmount value to be equals to 5');
    expect(await mMedalUpdatePage.getIconAssetNameInput()).to.eq(
      'iconAssetName',
      'Expected IconAssetName value to be equals to iconAssetName'
    );
    expect(await mMedalUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    await mMedalUpdatePage.save();
    expect(await mMedalUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMedalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MMedal', async () => {
    const nbButtonsBeforeDelete = await mMedalComponentsPage.countDeleteButtons();
    await mMedalComponentsPage.clickOnLastDeleteButton();

    mMedalDeleteDialog = new MMedalDeleteDialog();
    expect(await mMedalDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Medal?');
    await mMedalDeleteDialog.clickOnConfirmButton();

    expect(await mMedalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
