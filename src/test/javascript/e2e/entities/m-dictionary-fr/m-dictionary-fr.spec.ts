/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDictionaryFrComponentsPage, MDictionaryFrDeleteDialog, MDictionaryFrUpdatePage } from './m-dictionary-fr.page-object';

const expect = chai.expect;

describe('MDictionaryFr e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDictionaryFrUpdatePage: MDictionaryFrUpdatePage;
  let mDictionaryFrComponentsPage: MDictionaryFrComponentsPage;
  let mDictionaryFrDeleteDialog: MDictionaryFrDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDictionaryFrs', async () => {
    await navBarPage.goToEntity('m-dictionary-fr');
    mDictionaryFrComponentsPage = new MDictionaryFrComponentsPage();
    await browser.wait(ec.visibilityOf(mDictionaryFrComponentsPage.title), 5000);
    expect(await mDictionaryFrComponentsPage.getTitle()).to.eq('M Dictionary Frs');
  });

  it('should load create MDictionaryFr page', async () => {
    await mDictionaryFrComponentsPage.clickOnCreateButton();
    mDictionaryFrUpdatePage = new MDictionaryFrUpdatePage();
    expect(await mDictionaryFrUpdatePage.getPageTitle()).to.eq('Create or edit a M Dictionary Fr');
    await mDictionaryFrUpdatePage.cancel();
  });

  it('should create and save MDictionaryFrs', async () => {
    const nbButtonsBeforeCreate = await mDictionaryFrComponentsPage.countDeleteButtons();

    await mDictionaryFrComponentsPage.clickOnCreateButton();
    await promise.all([mDictionaryFrUpdatePage.setKeyInput('key'), mDictionaryFrUpdatePage.setMessageInput('message')]);
    expect(await mDictionaryFrUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDictionaryFrUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mDictionaryFrUpdatePage.save();
    expect(await mDictionaryFrUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDictionaryFrComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDictionaryFr', async () => {
    const nbButtonsBeforeDelete = await mDictionaryFrComponentsPage.countDeleteButtons();
    await mDictionaryFrComponentsPage.clickOnLastDeleteButton();

    mDictionaryFrDeleteDialog = new MDictionaryFrDeleteDialog();
    expect(await mDictionaryFrDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Dictionary Fr?');
    await mDictionaryFrDeleteDialog.clickOnConfirmButton();

    expect(await mDictionaryFrComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
