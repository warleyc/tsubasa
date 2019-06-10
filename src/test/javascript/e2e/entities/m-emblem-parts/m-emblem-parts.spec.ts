/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MEmblemPartsComponentsPage, MEmblemPartsDeleteDialog, MEmblemPartsUpdatePage } from './m-emblem-parts.page-object';

const expect = chai.expect;

describe('MEmblemParts e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEmblemPartsUpdatePage: MEmblemPartsUpdatePage;
  let mEmblemPartsComponentsPage: MEmblemPartsComponentsPage;
  let mEmblemPartsDeleteDialog: MEmblemPartsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEmblemParts', async () => {
    await navBarPage.goToEntity('m-emblem-parts');
    mEmblemPartsComponentsPage = new MEmblemPartsComponentsPage();
    await browser.wait(ec.visibilityOf(mEmblemPartsComponentsPage.title), 5000);
    expect(await mEmblemPartsComponentsPage.getTitle()).to.eq('M Emblem Parts');
  });

  it('should load create MEmblemParts page', async () => {
    await mEmblemPartsComponentsPage.clickOnCreateButton();
    mEmblemPartsUpdatePage = new MEmblemPartsUpdatePage();
    expect(await mEmblemPartsUpdatePage.getPageTitle()).to.eq('Create or edit a M Emblem Parts');
    await mEmblemPartsUpdatePage.cancel();
  });

  it('should create and save MEmblemParts', async () => {
    const nbButtonsBeforeCreate = await mEmblemPartsComponentsPage.countDeleteButtons();

    await mEmblemPartsComponentsPage.clickOnCreateButton();
    await promise.all([mEmblemPartsUpdatePage.setAssetNameInput('assetName'), mEmblemPartsUpdatePage.setPartsTypeInput('5')]);
    expect(await mEmblemPartsUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mEmblemPartsUpdatePage.getPartsTypeInput()).to.eq('5', 'Expected partsType value to be equals to 5');
    await mEmblemPartsUpdatePage.save();
    expect(await mEmblemPartsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEmblemPartsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MEmblemParts', async () => {
    const nbButtonsBeforeDelete = await mEmblemPartsComponentsPage.countDeleteButtons();
    await mEmblemPartsComponentsPage.clickOnLastDeleteButton();

    mEmblemPartsDeleteDialog = new MEmblemPartsDeleteDialog();
    expect(await mEmblemPartsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Emblem Parts?');
    await mEmblemPartsDeleteDialog.clickOnConfirmButton();

    expect(await mEmblemPartsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
