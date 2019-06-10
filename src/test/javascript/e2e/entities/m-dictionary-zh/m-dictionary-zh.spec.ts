/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryZhComponentsPage, MDictionaryZhDeleteDialog, MDictionaryZhUpdatePage } from './m-dictionary-zh.page-object';

const expect = chai.expect;

describe('MDictionaryZh e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryZhUpdatePage: MDictionaryZhUpdatePage;
  let mDictionaryZhComponentsPage: MDictionaryZhComponentsPage;
  let mDictionaryZhDeleteDialog: MDictionaryZhDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryZhs', async () => {
    await navBarPage.goToEntity('m-dictionary-zh');
    mDictionaryZhComponentsPage = new MDictionaryZhComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryZhComponentsPage.title), 5000);
    expect(await mDictionaryZhComponentsPage.getTitle()).to.eq('M Dictionary Zhs');
  });

  it('should load create MDictionaryZh page', async () => {
    await mDictionaryZhComponentsPage.clickOnCreateButton();
    mDictionaryZhUpdatePage = new MDictionaryZhUpdatePage();
    expect(await mDictionaryZhUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Zh');
    await mDictionaryZhUpdatePage.cancel();
  });

  it('should create and save MDictionaryZhs', async () => {
    const nbButtonsBeforeCreate = await mDictionaryZhComponentsPage.countDeleteButtons();

    await mDictionaryZhComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryZhUpdatePage.setKeyInput('key'), mDictionaryZhUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryZhUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryZhUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryZhUpdatePage.save();
    expect(await mDictionaryZhUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryZhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryZh', async () => {
    const nbButtonsBeforeDelete = await mDictionaryZhComponentsPage.countDeleteButtons();
    await mDictionaryZhComponentsPage.clickOnLastDeleteButton();

    mDictionaryZhDeleteDialog = new MDictionaryZhDeleteDialog();
    expect(await mDictionaryZhDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Zh?');
    await mDictionaryZhDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryZhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
