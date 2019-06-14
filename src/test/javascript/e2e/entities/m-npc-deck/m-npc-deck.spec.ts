/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MNpcDeckComponentsPage, MNpcDeckDeleteDialog, MNpcDeckUpdatePage } from './m-npc-deck.page-object';

const expect = chai.expect;

describe('MNpcDeck e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mNpcDeckUpdatePage: MNpcDeckUpdatePage;
  let mNpcDeckComponentsPage: MNpcDeckComponentsPage;
  /*let mNpcDeckDeleteDialog: MNpcDeckDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MNpcDecks', async () => {
    await navBarPage.goToEntity('m-npc-deck');
    mNpcDeckComponentsPage = new MNpcDeckComponentsPage();
    await browser.wait(ec.visibilityOf(mNpcDeckComponentsPage.title), 5000);
    expect(await mNpcDeckComponentsPage.getTitle()).to.eq('M Npc Decks');
  });

  it('should load create MNpcDeck page', async () => {
    await mNpcDeckComponentsPage.clickOnCreateButton();
    mNpcDeckUpdatePage = new MNpcDeckUpdatePage();
    expect(await mNpcDeckUpdatePage.getPageTitle()).to.eq('Create or edit a M Npc Deck');
    await mNpcDeckUpdatePage.cancel();
  });

  /* it('should create and save MNpcDecks', async () => {
        const nbButtonsBeforeCreate = await mNpcDeckComponentsPage.countDeleteButtons();

        await mNpcDeckComponentsPage.clickOnCreateButton();
        await promise.all([
            mNpcDeckUpdatePage.setTeamNameInput('teamName'),
            mNpcDeckUpdatePage.setUniformBottomFpInput('5'),
            mNpcDeckUpdatePage.setUniformUpFpInput('5'),
            mNpcDeckUpdatePage.setUniformBottomGkInput('5'),
            mNpcDeckUpdatePage.setUniformUpGkInput('5'),
            mNpcDeckUpdatePage.setFormationIdInput('5'),
            mNpcDeckUpdatePage.setCaptainCardIdInput('5'),
            mNpcDeckUpdatePage.setTeamEffect1CardIdInput('5'),
            mNpcDeckUpdatePage.setTeamEffect2CardIdInput('5'),
            mNpcDeckUpdatePage.setTeamEffect3CardIdInput('5'),
            mNpcDeckUpdatePage.setNpcCardId1Input('5'),
            mNpcDeckUpdatePage.setNpcCardId2Input('5'),
            mNpcDeckUpdatePage.setNpcCardId3Input('5'),
            mNpcDeckUpdatePage.setNpcCardId4Input('5'),
            mNpcDeckUpdatePage.setNpcCardId5Input('5'),
            mNpcDeckUpdatePage.setNpcCardId6Input('5'),
            mNpcDeckUpdatePage.setNpcCardId7Input('5'),
            mNpcDeckUpdatePage.setNpcCardId8Input('5'),
            mNpcDeckUpdatePage.setNpcCardId9Input('5'),
            mNpcDeckUpdatePage.setNpcCardId10Input('5'),
            mNpcDeckUpdatePage.setNpcCardId11Input('5'),
            mNpcDeckUpdatePage.setTickInput('5'),
            mNpcDeckUpdatePage.mformationSelectLastOption(),
        ]);
        expect(await mNpcDeckUpdatePage.getTeamNameInput()).to.eq('teamName', 'Expected TeamName value to be equals to teamName');
        expect(await mNpcDeckUpdatePage.getUniformBottomFpInput()).to.eq('5', 'Expected uniformBottomFp value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getUniformUpFpInput()).to.eq('5', 'Expected uniformUpFp value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getUniformBottomGkInput()).to.eq('5', 'Expected uniformBottomGk value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getUniformUpGkInput()).to.eq('5', 'Expected uniformUpGk value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getFormationIdInput()).to.eq('5', 'Expected formationId value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getCaptainCardIdInput()).to.eq('5', 'Expected captainCardId value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getTeamEffect1CardIdInput()).to.eq('5', 'Expected teamEffect1CardId value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getTeamEffect2CardIdInput()).to.eq('5', 'Expected teamEffect2CardId value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getTeamEffect3CardIdInput()).to.eq('5', 'Expected teamEffect3CardId value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId1Input()).to.eq('5', 'Expected npcCardId1 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId2Input()).to.eq('5', 'Expected npcCardId2 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId3Input()).to.eq('5', 'Expected npcCardId3 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId4Input()).to.eq('5', 'Expected npcCardId4 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId5Input()).to.eq('5', 'Expected npcCardId5 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId6Input()).to.eq('5', 'Expected npcCardId6 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId7Input()).to.eq('5', 'Expected npcCardId7 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId8Input()).to.eq('5', 'Expected npcCardId8 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId9Input()).to.eq('5', 'Expected npcCardId9 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId10Input()).to.eq('5', 'Expected npcCardId10 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getNpcCardId11Input()).to.eq('5', 'Expected npcCardId11 value to be equals to 5');
        expect(await mNpcDeckUpdatePage.getTickInput()).to.eq('5', 'Expected tick value to be equals to 5');
        await mNpcDeckUpdatePage.save();
        expect(await mNpcDeckUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mNpcDeckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MNpcDeck', async () => {
        const nbButtonsBeforeDelete = await mNpcDeckComponentsPage.countDeleteButtons();
        await mNpcDeckComponentsPage.clickOnLastDeleteButton();

        mNpcDeckDeleteDialog = new MNpcDeckDeleteDialog();
        expect(await mNpcDeckDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Npc Deck?');
        await mNpcDeckDeleteDialog.clickOnConfirmButton();

        expect(await mNpcDeckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
