/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryEnComponentsPage, MDictionaryEnDeleteDialog, MDictionaryEnUpdatePage } from './m-dictionary-en.page-object';

const expect = chai.expect;

describe('MDictionaryEn e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryEnUpdatePage: MDictionaryEnUpdatePage;
  let mDictionaryEnComponentsPage: MDictionaryEnComponentsPage;
  let mDictionaryEnDeleteDialog: MDictionaryEnDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryEns', async () => {
    await navBarPage.goToEntity('m-dictionary-en');
    mDictionaryEnComponentsPage = new MDictionaryEnComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryEnComponentsPage.title), 5000);
    expect(await mDictionaryEnComponentsPage.getTitle()).to.eq('M Dictionary Ens');
  });

  it('should load create MDictionaryEn page', async () => {
    await mDictionaryEnComponentsPage.clickOnCreateButton();
    mDictionaryEnUpdatePage = new MDictionaryEnUpdatePage();
    expect(await mDictionaryEnUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary En');
    await mDictionaryEnUpdatePage.cancel();
  });

  it('should create and save MDictionaryEns', async () => {
    const nbButtonsBeforeCreate = await mDictionaryEnComponentsPage.countDeleteButtons();

    await mDictionaryEnComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryEnUpdatePage.setKeyInput('key'), mDictionaryEnUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryEnUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryEnUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryEnUpdatePage.save();
    expect(await mDictionaryEnUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryEnComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryEn', async () => {
    const nbButtonsBeforeDelete = await mDictionaryEnComponentsPage.countDeleteButtons();
    await mDictionaryEnComponentsPage.clickOnLastDeleteButton();

    mDictionaryEnDeleteDialog = new MDictionaryEnDeleteDialog();
    expect(await mDictionaryEnDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary En?');
    await mDictionaryEnDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryEnComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
