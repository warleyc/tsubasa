/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryItComponentsPage, MDictionaryItDeleteDialog, MDictionaryItUpdatePage } from './m-dictionary-it.page-object';

const expect = chai.expect;

describe('MDictionaryIt e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryItUpdatePage: MDictionaryItUpdatePage;
  let mDictionaryItComponentsPage: MDictionaryItComponentsPage;
  let mDictionaryItDeleteDialog: MDictionaryItDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryIts', async () => {
    await navBarPage.goToEntity('m-dictionary-it');
    mDictionaryItComponentsPage = new MDictionaryItComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryItComponentsPage.title), 5000);
    expect(await mDictionaryItComponentsPage.getTitle()).to.eq('M Dictionary Its');
  });

  it('should load create MDictionaryIt page', async () => {
    await mDictionaryItComponentsPage.clickOnCreateButton();
    mDictionaryItUpdatePage = new MDictionaryItUpdatePage();
    expect(await mDictionaryItUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary It');
    await mDictionaryItUpdatePage.cancel();
  });

  it('should create and save MDictionaryIts', async () => {
    const nbButtonsBeforeCreate = await mDictionaryItComponentsPage.countDeleteButtons();

    await mDictionaryItComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryItUpdatePage.setKeyInput('key'), mDictionaryItUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryItUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryItUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryItUpdatePage.save();
    expect(await mDictionaryItUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryItComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryIt', async () => {
    const nbButtonsBeforeDelete = await mDictionaryItComponentsPage.countDeleteButtons();
    await mDictionaryItComponentsPage.clickOnLastDeleteButton();

    mDictionaryItDeleteDialog = new MDictionaryItDeleteDialog();
    expect(await mDictionaryItDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary It?');
    await mDictionaryItDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryItComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
