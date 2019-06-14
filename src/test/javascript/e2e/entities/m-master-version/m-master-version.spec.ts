/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMasterVersionComponentsPage, MMasterVersionDeleteDialog, MMasterVersionUpdatePage } from './m-master-version.page-object';

const expect = chai.expect;

describe('MMasterVersion e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMasterVersionUpdatePage: MMasterVersionUpdatePage;
  let mMasterVersionComponentsPage: MMasterVersionComponentsPage;
  let mMasterVersionDeleteDialog: MMasterVersionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMasterVersions', async () => {
    await navBarPage.goToEntity('m-master-version');
    mMasterVersionComponentsPage = new MMasterVersionComponentsPage();
    await browser.wait(ec.visibilityOf(mMasterVersionComponentsPage.title), 5000);
    expect(await mMasterVersionComponentsPage.getTitle()).to.eq('M Master Versions');
  });

  it('should load create MMasterVersion page', async () => {
    await mMasterVersionComponentsPage.clickOnCreateButton();
    mMasterVersionUpdatePage = new MMasterVersionUpdatePage();
    expect(await mMasterVersionUpdatePage.getPageTitle()).to.eq('Create or edit a M Master Version');
    await mMasterVersionUpdatePage.cancel();
  });

  it('should create and save MMasterVersions', async () => {
    const nbButtonsBeforeCreate = await mMasterVersionComponentsPage.countDeleteButtons();

    await mMasterVersionComponentsPage.clickOnCreateButton();
    await promise.all([
      mMasterVersionUpdatePage.setNameInput('name'),
      mMasterVersionUpdatePage.setVersionInput('5'),
      mMasterVersionUpdatePage.setPathInput('path'),
      mMasterVersionUpdatePage.setSizeInput('5')
    ]);
    expect(await mMasterVersionUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mMasterVersionUpdatePage.getVersionInput()).to.eq('5', 'Expected version value to be equals to 5');
    expect(await mMasterVersionUpdatePage.getPathInput()).to.eq('path', 'Expected Path value to be equals to path');
    expect(await mMasterVersionUpdatePage.getSizeInput()).to.eq('5', 'Expected size value to be equals to 5');
    await mMasterVersionUpdatePage.save();
    expect(await mMasterVersionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMasterVersionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMasterVersion', async () => {
    const nbButtonsBeforeDelete = await mMasterVersionComponentsPage.countDeleteButtons();
    await mMasterVersionComponentsPage.clickOnLastDeleteButton();

    mMasterVersionDeleteDialog = new MMasterVersionDeleteDialog();
    expect(await mMasterVersionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Master Version?');
    await mMasterVersionDeleteDialog.clickOnConfirmButton();

    expect(await mMasterVersionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
