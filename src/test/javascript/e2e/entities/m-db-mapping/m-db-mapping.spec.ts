/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDbMappingComponentsPage, MDbMappingDeleteDialog, MDbMappingUpdatePage } from './m-db-mapping.page-object';

const expect = chai.expect;

describe('MDbMapping e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDbMappingUpdatePage: MDbMappingUpdatePage;
  let mDbMappingComponentsPage: MDbMappingComponentsPage;
  let mDbMappingDeleteDialog: MDbMappingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDbMappings', async () => {
    await navBarPage.goToEntity('m-db-mapping');
    mDbMappingComponentsPage = new MDbMappingComponentsPage();
    await browser.wait(ec.visibilityOf(mDbMappingComponentsPage.title), 5000);
    expect(await mDbMappingComponentsPage.getTitle()).to.eq('M Db Mappings');
  });

  it('should load create MDbMapping page', async () => {
    await mDbMappingComponentsPage.clickOnCreateButton();
    mDbMappingUpdatePage = new MDbMappingUpdatePage();
    expect(await mDbMappingUpdatePage.getPageTitle()).to.eq('Create or edit a M Db Mapping');
    await mDbMappingUpdatePage.cancel();
  });

  it('should create and save MDbMappings', async () => {
    const nbButtonsBeforeCreate = await mDbMappingComponentsPage.countDeleteButtons();

    await mDbMappingComponentsPage.clickOnCreateButton();
    await promise.all([
      mDbMappingUpdatePage.setFileNameInput('fileName'),
      mDbMappingUpdatePage.setDbNameInput('dbName'),
      mDbMappingUpdatePage.setPathInput('path')
    ]);
    expect(await mDbMappingUpdatePage.getFileNameInput()).to.eq('fileName', 'Expected FileName value to be equals to fileName');
    expect(await mDbMappingUpdatePage.getDbNameInput()).to.eq('dbName', 'Expected DbName value to be equals to dbName');
    expect(await mDbMappingUpdatePage.getPathInput()).to.eq('path', 'Expected Path value to be equals to path');
    await mDbMappingUpdatePage.save();
    expect(await mDbMappingUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDbMappingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDbMapping', async () => {
    const nbButtonsBeforeDelete = await mDbMappingComponentsPage.countDeleteButtons();
    await mDbMappingComponentsPage.clickOnLastDeleteButton();

    mDbMappingDeleteDialog = new MDbMappingDeleteDialog();
    expect(await mDbMappingDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Db Mapping?');
    await mDbMappingDeleteDialog.clickOnConfirmButton();

    expect(await mDbMappingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
