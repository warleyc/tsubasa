/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MExtraNewsComponentsPage, MExtraNewsDeleteDialog, MExtraNewsUpdatePage } from './m-extra-news.page-object';

const expect = chai.expect;

describe('MExtraNews e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mExtraNewsUpdatePage: MExtraNewsUpdatePage;
  let mExtraNewsComponentsPage: MExtraNewsComponentsPage;
  let mExtraNewsDeleteDialog: MExtraNewsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MExtraNews', async () => {
    await navBarPage.goToEntity('m-extra-news');
    mExtraNewsComponentsPage = new MExtraNewsComponentsPage();
    await browser.wait(ec.visibilityOf(mExtraNewsComponentsPage.title), 5000);
    expect(await mExtraNewsComponentsPage.getTitle()).to.eq('M Extra News');
  });

  it('should load create MExtraNews page', async () => {
    await mExtraNewsComponentsPage.clickOnCreateButton();
    mExtraNewsUpdatePage = new MExtraNewsUpdatePage();
    expect(await mExtraNewsUpdatePage.getPageTitle()).to.eq('Create or edit a M Extra News');
    await mExtraNewsUpdatePage.cancel();
  });

  it('should create and save MExtraNews', async () => {
    const nbButtonsBeforeCreate = await mExtraNewsComponentsPage.countDeleteButtons();

    await mExtraNewsComponentsPage.clickOnCreateButton();
    await promise.all([
      mExtraNewsUpdatePage.setOrderNumInput('5'),
      mExtraNewsUpdatePage.setAssetNameInput('assetName'),
      mExtraNewsUpdatePage.setWebviewIdInput('webviewId'),
      mExtraNewsUpdatePage.setStartAtInput('5'),
      mExtraNewsUpdatePage.setEndAtInput('5')
    ]);
    expect(await mExtraNewsUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mExtraNewsUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mExtraNewsUpdatePage.getWebviewIdInput()).to.eq('webviewId', 'Expected WebviewId value to be equals to webviewId');
    expect(await mExtraNewsUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mExtraNewsUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mExtraNewsUpdatePage.save();
    expect(await mExtraNewsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mExtraNewsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MExtraNews', async () => {
    const nbButtonsBeforeDelete = await mExtraNewsComponentsPage.countDeleteButtons();
    await mExtraNewsComponentsPage.clickOnLastDeleteButton();

    mExtraNewsDeleteDialog = new MExtraNewsDeleteDialog();
    expect(await mExtraNewsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Extra News?');
    await mExtraNewsDeleteDialog.clickOnConfirmButton();

    expect(await mExtraNewsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
