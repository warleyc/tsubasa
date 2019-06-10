/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryJaComponentsPage, MDictionaryJaDeleteDialog, MDictionaryJaUpdatePage } from './m-dictionary-ja.page-object';

const expect = chai.expect;

describe('MDictionaryJa e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryJaUpdatePage: MDictionaryJaUpdatePage;
  let mDictionaryJaComponentsPage: MDictionaryJaComponentsPage;
  let mDictionaryJaDeleteDialog: MDictionaryJaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryJas', async () => {
    await navBarPage.goToEntity('m-dictionary-ja');
    mDictionaryJaComponentsPage = new MDictionaryJaComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryJaComponentsPage.title), 5000);
    expect(await mDictionaryJaComponentsPage.getTitle()).to.eq('M Dictionary Jas');
  });

  it('should load create MDictionaryJa page', async () => {
    await mDictionaryJaComponentsPage.clickOnCreateButton();
    mDictionaryJaUpdatePage = new MDictionaryJaUpdatePage();
    expect(await mDictionaryJaUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Ja');
    await mDictionaryJaUpdatePage.cancel();
  });

  it('should create and save MDictionaryJas', async () => {
    const nbButtonsBeforeCreate = await mDictionaryJaComponentsPage.countDeleteButtons();

    await mDictionaryJaComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryJaUpdatePage.setKeyInput('key'), mDictionaryJaUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryJaUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryJaUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryJaUpdatePage.save();
    expect(await mDictionaryJaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryJaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryJa', async () => {
    const nbButtonsBeforeDelete = await mDictionaryJaComponentsPage.countDeleteButtons();
    await mDictionaryJaComponentsPage.clickOnLastDeleteButton();

    mDictionaryJaDeleteDialog = new MDictionaryJaDeleteDialog();
    expect(await mDictionaryJaDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Ja?');
    await mDictionaryJaDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryJaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
