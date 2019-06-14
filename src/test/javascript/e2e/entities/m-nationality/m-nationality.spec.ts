/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MNationalityComponentsPage, MNationalityDeleteDialog, MNationalityUpdatePage } from './m-nationality.page-object';

const expect = chai.expect;

describe('MNationality e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mNationalityUpdatePage: MNationalityUpdatePage;
  let mNationalityComponentsPage: MNationalityComponentsPage;
  let mNationalityDeleteDialog: MNationalityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MNationalities', async () => {
    await navBarPage.goToEntity('m-nationality');
    mNationalityComponentsPage = new MNationalityComponentsPage();
    await browser.wait(ec.visibilityOf(mNationalityComponentsPage.title), 5000);
    expect(await mNationalityComponentsPage.getTitle()).to.eq('M Nationalities');
  });

  it('should load create MNationality page', async () => {
    await mNationalityComponentsPage.clickOnCreateButton();
    mNationalityUpdatePage = new MNationalityUpdatePage();
    expect(await mNationalityUpdatePage.getPageTitle()).to.eq('Create or edit a M Nationality');
    await mNationalityUpdatePage.cancel();
  });

  it('should create and save MNationalities', async () => {
    const nbButtonsBeforeCreate = await mNationalityComponentsPage.countDeleteButtons();

    await mNationalityComponentsPage.clickOnCreateButton();
    await promise.all([mNationalityUpdatePage.setNameInput('name'), mNationalityUpdatePage.setIconInput('icon')]);
    expect(await mNationalityUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mNationalityUpdatePage.getIconInput()).to.eq('icon', 'Expected Icon value to be equals to icon');
    await mNationalityUpdatePage.save();
    expect(await mNationalityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mNationalityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MNationality', async () => {
    const nbButtonsBeforeDelete = await mNationalityComponentsPage.countDeleteButtons();
    await mNationalityComponentsPage.clickOnLastDeleteButton();

    mNationalityDeleteDialog = new MNationalityDeleteDialog();
    expect(await mNationalityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Nationality?');
    await mNationalityDeleteDialog.clickOnConfirmButton();

    expect(await mNationalityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
