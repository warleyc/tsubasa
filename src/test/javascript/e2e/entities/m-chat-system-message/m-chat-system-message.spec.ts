/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChatSystemMessageComponentsPage,
  MChatSystemMessageDeleteDialog,
  MChatSystemMessageUpdatePage
} from './m-chat-system-message.page-object';

const expect = chai.expect;

describe('MChatSystemMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChatSystemMessageUpdatePage: MChatSystemMessageUpdatePage;
  let mChatSystemMessageComponentsPage: MChatSystemMessageComponentsPage;
  let mChatSystemMessageDeleteDialog: MChatSystemMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChatSystemMessages', async () => {
    await navBarPage.goToEntity('m-chat-system-message');
    mChatSystemMessageComponentsPage = new MChatSystemMessageComponentsPage();
    await browser.wait(ec.visibilityOf(mChatSystemMessageComponentsPage.title), 5000);
    expect(await mChatSystemMessageComponentsPage.getTitle()).to.eq('M Chat System Messages');
  });

  it('should load create MChatSystemMessage page', async () => {
    await mChatSystemMessageComponentsPage.clickOnCreateButton();
    mChatSystemMessageUpdatePage = new MChatSystemMessageUpdatePage();
    expect(await mChatSystemMessageUpdatePage.getPageTitle()).to.eq('Create or edit a M Chat System Message');
    await mChatSystemMessageUpdatePage.cancel();
  });

  it('should create and save MChatSystemMessages', async () => {
    const nbButtonsBeforeCreate = await mChatSystemMessageComponentsPage.countDeleteButtons();

    await mChatSystemMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      mChatSystemMessageUpdatePage.setMessageTypeInput('5'),
      mChatSystemMessageUpdatePage.setMessageKeyInput('messageKey')
    ]);
    expect(await mChatSystemMessageUpdatePage.getMessageTypeInput()).to.eq('5', 'Expected messageType value to be equals to 5');
    expect(await mChatSystemMessageUpdatePage.getMessageKeyInput()).to.eq(
      'messageKey',
      'Expected MessageKey value to be equals to messageKey'
    );
    await mChatSystemMessageUpdatePage.save();
    expect(await mChatSystemMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mChatSystemMessageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MChatSystemMessage', async () => {
    const nbButtonsBeforeDelete = await mChatSystemMessageComponentsPage.countDeleteButtons();
    await mChatSystemMessageComponentsPage.clickOnLastDeleteButton();

    mChatSystemMessageDeleteDialog = new MChatSystemMessageDeleteDialog();
    expect(await mChatSystemMessageDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Chat System Message?');
    await mChatSystemMessageDeleteDialog.clickOnConfirmButton();

    expect(await mChatSystemMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
