/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MEmblemSetComponentsPage, MEmblemSetDeleteDialog, MEmblemSetUpdatePage } from './m-emblem-set.page-object';

const expect = chai.expect;

describe('MEmblemSet e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEmblemSetUpdatePage: MEmblemSetUpdatePage;
  let mEmblemSetComponentsPage: MEmblemSetComponentsPage;
  let mEmblemSetDeleteDialog: MEmblemSetDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEmblemSets', async () => {
    await navBarPage.goToEntity('m-emblem-set');
    mEmblemSetComponentsPage = new MEmblemSetComponentsPage();
    await browser.wait(ec.visibilityOf(mEmblemSetComponentsPage.title), 5000);
    expect(await mEmblemSetComponentsPage.getTitle()).to.eq('M Emblem Sets');
  });

  it('should load create MEmblemSet page', async () => {
    await mEmblemSetComponentsPage.clickOnCreateButton();
    mEmblemSetUpdatePage = new MEmblemSetUpdatePage();
    expect(await mEmblemSetUpdatePage.getPageTitle()).to.eq('Create or edit a M Emblem Set');
    await mEmblemSetUpdatePage.cancel();
  });

  it('should create and save MEmblemSets', async () => {
    const nbButtonsBeforeCreate = await mEmblemSetComponentsPage.countDeleteButtons();

    await mEmblemSetComponentsPage.clickOnCreateButton();
    await promise.all([
      mEmblemSetUpdatePage.setAssetNameInput('assetName'),
      mEmblemSetUpdatePage.setNameInput('name'),
      mEmblemSetUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mEmblemSetUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mEmblemSetUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mEmblemSetUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    await mEmblemSetUpdatePage.save();
    expect(await mEmblemSetUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEmblemSetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MEmblemSet', async () => {
    const nbButtonsBeforeDelete = await mEmblemSetComponentsPage.countDeleteButtons();
    await mEmblemSetComponentsPage.clickOnLastDeleteButton();

    mEmblemSetDeleteDialog = new MEmblemSetDeleteDialog();
    expect(await mEmblemSetDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Emblem Set?');
    await mEmblemSetDeleteDialog.clickOnConfirmButton();

    expect(await mEmblemSetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
