/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuildChatDefaultStampComponentsPage,
  MGuildChatDefaultStampDeleteDialog,
  MGuildChatDefaultStampUpdatePage
} from './m-guild-chat-default-stamp.page-object';

const expect = chai.expect;

describe('MGuildChatDefaultStamp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildChatDefaultStampUpdatePage: MGuildChatDefaultStampUpdatePage;
  let mGuildChatDefaultStampComponentsPage: MGuildChatDefaultStampComponentsPage;
  let mGuildChatDefaultStampDeleteDialog: MGuildChatDefaultStampDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildChatDefaultStamps', async () => {
    await navBarPage.goToEntity('m-guild-chat-default-stamp');
    mGuildChatDefaultStampComponentsPage = new MGuildChatDefaultStampComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildChatDefaultStampComponentsPage.title), 5000);
    expect(await mGuildChatDefaultStampComponentsPage.getTitle()).to.eq('M Guild Chat Default Stamps');
  });

  it('should load create MGuildChatDefaultStamp page', async () => {
    await mGuildChatDefaultStampComponentsPage.clickOnCreateButton();
    mGuildChatDefaultStampUpdatePage = new MGuildChatDefaultStampUpdatePage();
    expect(await mGuildChatDefaultStampUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Chat Default Stamp');
    await mGuildChatDefaultStampUpdatePage.cancel();
  });

  it('should create and save MGuildChatDefaultStamps', async () => {
    const nbButtonsBeforeCreate = await mGuildChatDefaultStampComponentsPage.countDeleteButtons();

    await mGuildChatDefaultStampComponentsPage.clickOnCreateButton();
    await promise.all([mGuildChatDefaultStampUpdatePage.setMasterIdInput('5')]);
    expect(await mGuildChatDefaultStampUpdatePage.getMasterIdInput()).to.eq('5', 'Expected masterId value to be equals to 5');
    await mGuildChatDefaultStampUpdatePage.save();
    expect(await mGuildChatDefaultStampUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildChatDefaultStampComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuildChatDefaultStamp', async () => {
    const nbButtonsBeforeDelete = await mGuildChatDefaultStampComponentsPage.countDeleteButtons();
    await mGuildChatDefaultStampComponentsPage.clickOnLastDeleteButton();

    mGuildChatDefaultStampDeleteDialog = new MGuildChatDefaultStampDeleteDialog();
    expect(await mGuildChatDefaultStampDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Guild Chat Default Stamp?'
    );
    await mGuildChatDefaultStampDeleteDialog.clickOnConfirmButton();

    expect(await mGuildChatDefaultStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
