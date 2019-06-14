/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGachaTicketComponentsPage, MGachaTicketDeleteDialog, MGachaTicketUpdatePage } from './m-gacha-ticket.page-object';

const expect = chai.expect;

describe('MGachaTicket e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaTicketUpdatePage: MGachaTicketUpdatePage;
  let mGachaTicketComponentsPage: MGachaTicketComponentsPage;
  let mGachaTicketDeleteDialog: MGachaTicketDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaTickets', async () => {
    await navBarPage.goToEntity('m-gacha-ticket');
    mGachaTicketComponentsPage = new MGachaTicketComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaTicketComponentsPage.title), 5000);
    expect(await mGachaTicketComponentsPage.getTitle()).to.eq('M Gacha Tickets');
  });

  it('should load create MGachaTicket page', async () => {
    await mGachaTicketComponentsPage.clickOnCreateButton();
    mGachaTicketUpdatePage = new MGachaTicketUpdatePage();
    expect(await mGachaTicketUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Ticket');
    await mGachaTicketUpdatePage.cancel();
  });

  it('should create and save MGachaTickets', async () => {
    const nbButtonsBeforeCreate = await mGachaTicketComponentsPage.countDeleteButtons();

    await mGachaTicketComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaTicketUpdatePage.setNameInput('name'),
      mGachaTicketUpdatePage.setShortNameInput('shortName'),
      mGachaTicketUpdatePage.setDescriptionInput('description'),
      mGachaTicketUpdatePage.setMaxAmountInput('5'),
      mGachaTicketUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mGachaTicketUpdatePage.setEndAtInput('5')
    ]);
    expect(await mGachaTicketUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mGachaTicketUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mGachaTicketUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mGachaTicketUpdatePage.getMaxAmountInput()).to.eq('5', 'Expected maxAmount value to be equals to 5');
    expect(await mGachaTicketUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mGachaTicketUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mGachaTicketUpdatePage.save();
    expect(await mGachaTicketUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaTicketComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MGachaTicket', async () => {
    const nbButtonsBeforeDelete = await mGachaTicketComponentsPage.countDeleteButtons();
    await mGachaTicketComponentsPage.clickOnLastDeleteButton();

    mGachaTicketDeleteDialog = new MGachaTicketDeleteDialog();
    expect(await mGachaTicketDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Gacha Ticket?');
    await mGachaTicketDeleteDialog.clickOnConfirmButton();

    expect(await mGachaTicketComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
