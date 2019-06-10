/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryArComponentsPage, MDictionaryArDeleteDialog, MDictionaryArUpdatePage } from './m-dictionary-ar.page-object';

const expect = chai.expect;

describe('MDictionaryAr e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryArUpdatePage: MDictionaryArUpdatePage;
  let mDictionaryArComponentsPage: MDictionaryArComponentsPage;
  let mDictionaryArDeleteDialog: MDictionaryArDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryArs', async () => {
    await navBarPage.goToEntity('m-dictionary-ar');
    mDictionaryArComponentsPage = new MDictionaryArComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryArComponentsPage.title), 5000);
    expect(await mDictionaryArComponentsPage.getTitle()).to.eq('M Dictionary Ars');
  });

  it('should load create MDictionaryAr page', async () => {
    await mDictionaryArComponentsPage.clickOnCreateButton();
    mDictionaryArUpdatePage = new MDictionaryArUpdatePage();
    expect(await mDictionaryArUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Ar');
    await mDictionaryArUpdatePage.cancel();
  });

  it('should create and save MDictionaryArs', async () => {
    const nbButtonsBeforeCreate = await mDictionaryArComponentsPage.countDeleteButtons();

    await mDictionaryArComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryArUpdatePage.setKeyInput('key'), mDictionaryArUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryArUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryArUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryArUpdatePage.save();
    expect(await mDictionaryArUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryArComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryAr', async () => {
    const nbButtonsBeforeDelete = await mDictionaryArComponentsPage.countDeleteButtons();
    await mDictionaryArComponentsPage.clickOnLastDeleteButton();

    mDictionaryArDeleteDialog = new MDictionaryArDeleteDialog();
    expect(await mDictionaryArDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Ar?');
    await mDictionaryArDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryArComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
