/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MSoundBankEventComponentsPage, MSoundBankEventDeleteDialog, MSoundBankEventUpdatePage } from './m-sound-bank-event.page-object';

const expect = chai.expect;

describe('MSoundBankEvent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSoundBankEventUpdatePage: MSoundBankEventUpdatePage;
  let mSoundBankEventComponentsPage: MSoundBankEventComponentsPage;
  let mSoundBankEventDeleteDialog: MSoundBankEventDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSoundBankEvents', async () => {
    await navBarPage.goToEntity('m-sound-bank-event');
    mSoundBankEventComponentsPage = new MSoundBankEventComponentsPage();
    await browser.wait(ec.visibilityOf(mSoundBankEventComponentsPage.title), 5000);
    expect(await mSoundBankEventComponentsPage.getTitle()).to.eq('M Sound Bank Events');
  });

  it('should load create MSoundBankEvent page', async () => {
    await mSoundBankEventComponentsPage.clickOnCreateButton();
    mSoundBankEventUpdatePage = new MSoundBankEventUpdatePage();
    expect(await mSoundBankEventUpdatePage.getPageTitle()).to.eq('Create or edit a M Sound Bank Event');
    await mSoundBankEventUpdatePage.cancel();
  });

  it('should create and save MSoundBankEvents', async () => {
    const nbButtonsBeforeCreate = await mSoundBankEventComponentsPage.countDeleteButtons();

    await mSoundBankEventComponentsPage.clickOnCreateButton();
    await promise.all([
      mSoundBankEventUpdatePage.setPathInput('path'),
      mSoundBankEventUpdatePage.setNameInput('name'),
      mSoundBankEventUpdatePage.setEventIdInput('eventId')
    ]);
    expect(await mSoundBankEventUpdatePage.getPathInput()).to.eq('path', 'Expected Path value to be equals to path');
    expect(await mSoundBankEventUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mSoundBankEventUpdatePage.getEventIdInput()).to.eq('eventId', 'Expected EventId value to be equals to eventId');
    await mSoundBankEventUpdatePage.save();
    expect(await mSoundBankEventUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSoundBankEventComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MSoundBankEvent', async () => {
    const nbButtonsBeforeDelete = await mSoundBankEventComponentsPage.countDeleteButtons();
    await mSoundBankEventComponentsPage.clickOnLastDeleteButton();

    mSoundBankEventDeleteDialog = new MSoundBankEventDeleteDialog();
    expect(await mSoundBankEventDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Sound Bank Event?');
    await mSoundBankEventDeleteDialog.clickOnConfirmButton();

    expect(await mSoundBankEventComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
