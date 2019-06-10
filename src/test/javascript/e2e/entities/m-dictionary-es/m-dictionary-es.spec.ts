/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryEsComponentsPage, MDictionaryEsDeleteDialog, MDictionaryEsUpdatePage } from './m-dictionary-es.page-object';

const expect = chai.expect;

describe('MDictionaryEs e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryEsUpdatePage: MDictionaryEsUpdatePage;
  let mDictionaryEsComponentsPage: MDictionaryEsComponentsPage;
  let mDictionaryEsDeleteDialog: MDictionaryEsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryEs', async () => {
    await navBarPage.goToEntity('m-dictionary-es');
    mDictionaryEsComponentsPage = new MDictionaryEsComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryEsComponentsPage.title), 5000);
    expect(await mDictionaryEsComponentsPage.getTitle()).to.eq('M Dictionary Es');
  });

  it('should load create MDictionaryEs page', async () => {
    await mDictionaryEsComponentsPage.clickOnCreateButton();
    mDictionaryEsUpdatePage = new MDictionaryEsUpdatePage();
    expect(await mDictionaryEsUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Es');
    await mDictionaryEsUpdatePage.cancel();
  });

  it('should create and save MDictionaryEs', async () => {
    const nbButtonsBeforeCreate = await mDictionaryEsComponentsPage.countDeleteButtons();

    await mDictionaryEsComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryEsUpdatePage.setKeyInput('key'), mDictionaryEsUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryEsUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryEsUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryEsUpdatePage.save();
    expect(await mDictionaryEsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryEsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryEs', async () => {
    const nbButtonsBeforeDelete = await mDictionaryEsComponentsPage.countDeleteButtons();
    await mDictionaryEsComponentsPage.clickOnLastDeleteButton();

    mDictionaryEsDeleteDialog = new MDictionaryEsDeleteDialog();
    expect(await mDictionaryEsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Es?');
    await mDictionaryEsDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryEsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
