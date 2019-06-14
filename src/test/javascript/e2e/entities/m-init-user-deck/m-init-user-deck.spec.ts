/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MInitUserDeckComponentsPage, MInitUserDeckDeleteDialog, MInitUserDeckUpdatePage } from './m-init-user-deck.page-object';

const expect = chai.expect;

describe('MInitUserDeck e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mInitUserDeckUpdatePage: MInitUserDeckUpdatePage;
  let mInitUserDeckComponentsPage: MInitUserDeckComponentsPage;
  let mInitUserDeckDeleteDialog: MInitUserDeckDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MInitUserDecks', async () => {
    await navBarPage.goToEntity('m-init-user-deck');
    mInitUserDeckComponentsPage = new MInitUserDeckComponentsPage();
    await browser.wait(ec.visibilityOf(mInitUserDeckComponentsPage.title), 5000);
    expect(await mInitUserDeckComponentsPage.getTitle()).to.eq('M Init User Decks');
  });

  it('should load create MInitUserDeck page', async () => {
    await mInitUserDeckComponentsPage.clickOnCreateButton();
    mInitUserDeckUpdatePage = new MInitUserDeckUpdatePage();
    expect(await mInitUserDeckUpdatePage.getPageTitle()).to.eq('Create or edit a M Init User Deck');
    await mInitUserDeckUpdatePage.cancel();
  });

  it('should create and save MInitUserDecks', async () => {
    const nbButtonsBeforeCreate = await mInitUserDeckComponentsPage.countDeleteButtons();

    await mInitUserDeckComponentsPage.clickOnCreateButton();
    await promise.all([
      mInitUserDeckUpdatePage.setDeckIdInput('5'),
      mInitUserDeckUpdatePage.setNameInput('name'),
      mInitUserDeckUpdatePage.setFormationIdInput('5'),
      mInitUserDeckUpdatePage.setCaptainCardIdInput('5'),
      mInitUserDeckUpdatePage.setGkCardIdInput('5'),
      mInitUserDeckUpdatePage.setFp1CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp2CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp3CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp4CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp5CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp6CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp7CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp8CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp9CardIdInput('5'),
      mInitUserDeckUpdatePage.setFp10CardIdInput('5'),
      mInitUserDeckUpdatePage.setSub1CardIdInput('5'),
      mInitUserDeckUpdatePage.setSub2CardIdInput('5'),
      mInitUserDeckUpdatePage.setSub3CardIdInput('5'),
      mInitUserDeckUpdatePage.setSub4CardIdInput('5'),
      mInitUserDeckUpdatePage.setSub5CardIdInput('5'),
      mInitUserDeckUpdatePage.setTeamEffect1CardIdInput('5'),
      mInitUserDeckUpdatePage.setTeamEffect2CardIdInput('5'),
      mInitUserDeckUpdatePage.setTeamEffect3CardIdInput('5')
    ]);
    expect(await mInitUserDeckUpdatePage.getDeckIdInput()).to.eq('5', 'Expected deckId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mInitUserDeckUpdatePage.getFormationIdInput()).to.eq('5', 'Expected formationId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getCaptainCardIdInput()).to.eq('5', 'Expected captainCardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getGkCardIdInput()).to.eq('5', 'Expected gkCardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp1CardIdInput()).to.eq('5', 'Expected fp1CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp2CardIdInput()).to.eq('5', 'Expected fp2CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp3CardIdInput()).to.eq('5', 'Expected fp3CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp4CardIdInput()).to.eq('5', 'Expected fp4CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp5CardIdInput()).to.eq('5', 'Expected fp5CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp6CardIdInput()).to.eq('5', 'Expected fp6CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp7CardIdInput()).to.eq('5', 'Expected fp7CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp8CardIdInput()).to.eq('5', 'Expected fp8CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp9CardIdInput()).to.eq('5', 'Expected fp9CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getFp10CardIdInput()).to.eq('5', 'Expected fp10CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getSub1CardIdInput()).to.eq('5', 'Expected sub1CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getSub2CardIdInput()).to.eq('5', 'Expected sub2CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getSub3CardIdInput()).to.eq('5', 'Expected sub3CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getSub4CardIdInput()).to.eq('5', 'Expected sub4CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getSub5CardIdInput()).to.eq('5', 'Expected sub5CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getTeamEffect1CardIdInput()).to.eq('5', 'Expected teamEffect1CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getTeamEffect2CardIdInput()).to.eq('5', 'Expected teamEffect2CardId value to be equals to 5');
    expect(await mInitUserDeckUpdatePage.getTeamEffect3CardIdInput()).to.eq('5', 'Expected teamEffect3CardId value to be equals to 5');
    await mInitUserDeckUpdatePage.save();
    expect(await mInitUserDeckUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mInitUserDeckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MInitUserDeck', async () => {
    const nbButtonsBeforeDelete = await mInitUserDeckComponentsPage.countDeleteButtons();
    await mInitUserDeckComponentsPage.clickOnLastDeleteButton();

    mInitUserDeckDeleteDialog = new MInitUserDeckDeleteDialog();
    expect(await mInitUserDeckDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Init User Deck?');
    await mInitUserDeckDeleteDialog.clickOnConfirmButton();

    expect(await mInitUserDeckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
