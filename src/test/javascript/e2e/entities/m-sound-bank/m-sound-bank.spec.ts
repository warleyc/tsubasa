/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MSoundBankComponentsPage, MSoundBankDeleteDialog, MSoundBankUpdatePage } from './m-sound-bank.page-object';

const expect = chai.expect;

describe('MSoundBank e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSoundBankUpdatePage: MSoundBankUpdatePage;
  let mSoundBankComponentsPage: MSoundBankComponentsPage;
  let mSoundBankDeleteDialog: MSoundBankDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSoundBanks', async () => {
    await navBarPage.goToEntity('m-sound-bank');
    mSoundBankComponentsPage = new MSoundBankComponentsPage();
    await browser.wait(ec.visibilityOf(mSoundBankComponentsPage.title), 5000);
    expect(await mSoundBankComponentsPage.getTitle()).to.eq('M Sound Banks');
  });

  it('should load create MSoundBank page', async () => {
    await mSoundBankComponentsPage.clickOnCreateButton();
    mSoundBankUpdatePage = new MSoundBankUpdatePage();
    expect(await mSoundBankUpdatePage.getPageTitle()).to.eq('Create or edit a M Sound Bank');
    await mSoundBankUpdatePage.cancel();
  });

  it('should create and save MSoundBanks', async () => {
    const nbButtonsBeforeCreate = await mSoundBankComponentsPage.countDeleteButtons();

    await mSoundBankComponentsPage.clickOnCreateButton();
    await promise.all([
      mSoundBankUpdatePage.setPathInput('path'),
      mSoundBankUpdatePage.setPfInput('pf'),
      mSoundBankUpdatePage.setVersionInput('5'),
      mSoundBankUpdatePage.setFileSizeInput('5')
    ]);
    expect(await mSoundBankUpdatePage.getPathInput()).to.eq('path', 'Expected Path value to be equals to path');
    expect(await mSoundBankUpdatePage.getPfInput()).to.eq('pf', 'Expected Pf value to be equals to pf');
    expect(await mSoundBankUpdatePage.getVersionInput()).to.eq('5', 'Expected version value to be equals to 5');
    expect(await mSoundBankUpdatePage.getFileSizeInput()).to.eq('5', 'Expected fileSize value to be equals to 5');
    await mSoundBankUpdatePage.save();
    expect(await mSoundBankUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSoundBankComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MSoundBank', async () => {
    const nbButtonsBeforeDelete = await mSoundBankComponentsPage.countDeleteButtons();
    await mSoundBankComponentsPage.clickOnLastDeleteButton();

    mSoundBankDeleteDialog = new MSoundBankDeleteDialog();
    expect(await mSoundBankDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Sound Bank?');
    await mSoundBankDeleteDialog.clickOnConfirmButton();

    expect(await mSoundBankComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
