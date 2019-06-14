/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MQuestTicketComponentsPage, MQuestTicketDeleteDialog, MQuestTicketUpdatePage } from './m-quest-ticket.page-object';

const expect = chai.expect;

describe('MQuestTicket e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestTicketUpdatePage: MQuestTicketUpdatePage;
  let mQuestTicketComponentsPage: MQuestTicketComponentsPage;
  let mQuestTicketDeleteDialog: MQuestTicketDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestTickets', async () => {
    await navBarPage.goToEntity('m-quest-ticket');
    mQuestTicketComponentsPage = new MQuestTicketComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestTicketComponentsPage.title), 5000);
    expect(await mQuestTicketComponentsPage.getTitle()).to.eq('M Quest Tickets');
  });

  it('should load create MQuestTicket page', async () => {
    await mQuestTicketComponentsPage.clickOnCreateButton();
    mQuestTicketUpdatePage = new MQuestTicketUpdatePage();
    expect(await mQuestTicketUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Ticket');
    await mQuestTicketUpdatePage.cancel();
  });

  it('should create and save MQuestTickets', async () => {
    const nbButtonsBeforeCreate = await mQuestTicketComponentsPage.countDeleteButtons();

    await mQuestTicketComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestTicketUpdatePage.setNameInput('name'),
      mQuestTicketUpdatePage.setShortNameInput('shortName'),
      mQuestTicketUpdatePage.setDescriptionInput('description'),
      mQuestTicketUpdatePage.setThumbnailAssetInput('thumbnailAsset')
    ]);
    expect(await mQuestTicketUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mQuestTicketUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mQuestTicketUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mQuestTicketUpdatePage.getThumbnailAssetInput()).to.eq(
      'thumbnailAsset',
      'Expected ThumbnailAsset value to be equals to thumbnailAsset'
    );
    await mQuestTicketUpdatePage.save();
    expect(await mQuestTicketUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestTicketComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MQuestTicket', async () => {
    const nbButtonsBeforeDelete = await mQuestTicketComponentsPage.countDeleteButtons();
    await mQuestTicketComponentsPage.clickOnLastDeleteButton();

    mQuestTicketDeleteDialog = new MQuestTicketDeleteDialog();
    expect(await mQuestTicketDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Ticket?');
    await mQuestTicketDeleteDialog.clickOnConfirmButton();

    expect(await mQuestTicketComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
