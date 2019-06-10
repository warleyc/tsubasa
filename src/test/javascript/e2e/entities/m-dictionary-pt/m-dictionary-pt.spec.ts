/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryPtComponentsPage, MDictionaryPtDeleteDialog, MDictionaryPtUpdatePage } from './m-dictionary-pt.page-object';

const expect = chai.expect;

describe('MDictionaryPt e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryPtUpdatePage: MDictionaryPtUpdatePage;
  let mDictionaryPtComponentsPage: MDictionaryPtComponentsPage;
  let mDictionaryPtDeleteDialog: MDictionaryPtDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryPts', async () => {
    await navBarPage.goToEntity('m-dictionary-pt');
    mDictionaryPtComponentsPage = new MDictionaryPtComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryPtComponentsPage.title), 5000);
    expect(await mDictionaryPtComponentsPage.getTitle()).to.eq('M Dictionary Pts');
  });

  it('should load create MDictionaryPt page', async () => {
    await mDictionaryPtComponentsPage.clickOnCreateButton();
    mDictionaryPtUpdatePage = new MDictionaryPtUpdatePage();
    expect(await mDictionaryPtUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Pt');
    await mDictionaryPtUpdatePage.cancel();
  });

  it('should create and save MDictionaryPts', async () => {
    const nbButtonsBeforeCreate = await mDictionaryPtComponentsPage.countDeleteButtons();

    await mDictionaryPtComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryPtUpdatePage.setKeyInput('key'), mDictionaryPtUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryPtUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryPtUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryPtUpdatePage.save();
    expect(await mDictionaryPtUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryPtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryPt', async () => {
    const nbButtonsBeforeDelete = await mDictionaryPtComponentsPage.countDeleteButtons();
    await mDictionaryPtComponentsPage.clickOnLastDeleteButton();

    mDictionaryPtDeleteDialog = new MDictionaryPtDeleteDialog();
    expect(await mDictionaryPtDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Pt?');
    await mDictionaryPtDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryPtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
