/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MApRecoveryItemComponentsPage, MApRecoveryItemDeleteDialog, MApRecoveryItemUpdatePage } from './m-ap-recovery-item.page-object';

const expect = chai.expect;

describe('MApRecoveryItem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mApRecoveryItemUpdatePage: MApRecoveryItemUpdatePage;
  let mApRecoveryItemComponentsPage: MApRecoveryItemComponentsPage;
  let mApRecoveryItemDeleteDialog: MApRecoveryItemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MApRecoveryItems', async () => {
    await navBarPage.goToEntity('m-ap-recovery-item');
    mApRecoveryItemComponentsPage = new MApRecoveryItemComponentsPage();
    await browser.wait(ec.visibilityOf(mApRecoveryItemComponentsPage.title), 5000);
    expect(await mApRecoveryItemComponentsPage.getTitle()).to.eq('M Ap Recovery Items');
  });

  it('should load create MApRecoveryItem page', async () => {
    await mApRecoveryItemComponentsPage.clickOnCreateButton();
    mApRecoveryItemUpdatePage = new MApRecoveryItemUpdatePage();
    expect(await mApRecoveryItemUpdatePage.getPageTitle()).to.eq('Create or edit a M Ap Recovery Item');
    await mApRecoveryItemUpdatePage.cancel();
  });

  it('should create and save MApRecoveryItems', async () => {
    const nbButtonsBeforeCreate = await mApRecoveryItemComponentsPage.countDeleteButtons();

    await mApRecoveryItemComponentsPage.clickOnCreateButton();
    await promise.all([
      mApRecoveryItemUpdatePage.setApRecoveryItemTypeInput('5'),
      mApRecoveryItemUpdatePage.setNameInput('name'),
      mApRecoveryItemUpdatePage.setDescriptionInput('description'),
      mApRecoveryItemUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName')
    ]);
    expect(await mApRecoveryItemUpdatePage.getApRecoveryItemTypeInput()).to.eq('5', 'Expected apRecoveryItemType value to be equals to 5');
    expect(await mApRecoveryItemUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mApRecoveryItemUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mApRecoveryItemUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    await mApRecoveryItemUpdatePage.save();
    expect(await mApRecoveryItemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mApRecoveryItemComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MApRecoveryItem', async () => {
    const nbButtonsBeforeDelete = await mApRecoveryItemComponentsPage.countDeleteButtons();
    await mApRecoveryItemComponentsPage.clickOnLastDeleteButton();

    mApRecoveryItemDeleteDialog = new MApRecoveryItemDeleteDialog();
    expect(await mApRecoveryItemDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Ap Recovery Item?');
    await mApRecoveryItemDeleteDialog.clickOnConfirmButton();

    expect(await mApRecoveryItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
