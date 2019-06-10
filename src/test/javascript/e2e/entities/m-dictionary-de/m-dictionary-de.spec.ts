/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryDeComponentsPage, MDictionaryDeDeleteDialog, MDictionaryDeUpdatePage } from './m-dictionary-de.page-object';

const expect = chai.expect;

describe('MDictionaryDe e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryDeUpdatePage: MDictionaryDeUpdatePage;
  let mDictionaryDeComponentsPage: MDictionaryDeComponentsPage;
  let mDictionaryDeDeleteDialog: MDictionaryDeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryDes', async () => {
    await navBarPage.goToEntity('m-dictionary-de');
    mDictionaryDeComponentsPage = new MDictionaryDeComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryDeComponentsPage.title), 5000);
    expect(await mDictionaryDeComponentsPage.getTitle()).to.eq('M Dictionary Des');
  });

  it('should load create MDictionaryDe page', async () => {
    await mDictionaryDeComponentsPage.clickOnCreateButton();
    mDictionaryDeUpdatePage = new MDictionaryDeUpdatePage();
    expect(await mDictionaryDeUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary De');
    await mDictionaryDeUpdatePage.cancel();
  });

  it('should create and save MDictionaryDes', async () => {
    const nbButtonsBeforeCreate = await mDictionaryDeComponentsPage.countDeleteButtons();

    await mDictionaryDeComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryDeUpdatePage.setKeyInput('key'), mDictionaryDeUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryDeUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryDeUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryDeUpdatePage.save();
    expect(await mDictionaryDeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryDeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryDe', async () => {
    const nbButtonsBeforeDelete = await mDictionaryDeComponentsPage.countDeleteButtons();
    await mDictionaryDeComponentsPage.clickOnLastDeleteButton();

    mDictionaryDeDeleteDialog = new MDictionaryDeDeleteDialog();
    expect(await mDictionaryDeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary De?');
    await mDictionaryDeDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryDeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
