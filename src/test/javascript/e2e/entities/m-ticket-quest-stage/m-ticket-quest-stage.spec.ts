/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTicketQuestStageComponentsPage,
  MTicketQuestStageDeleteDialog,
  MTicketQuestStageUpdatePage
} from './m-ticket-quest-stage.page-object';

const expect = chai.expect;

describe('MTicketQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTicketQuestStageUpdatePage: MTicketQuestStageUpdatePage;
  let mTicketQuestStageComponentsPage: MTicketQuestStageComponentsPage;
  /*let mTicketQuestStageDeleteDialog: MTicketQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTicketQuestStages', async () => {
    await navBarPage.goToEntity('m-ticket-quest-stage');
    mTicketQuestStageComponentsPage = new MTicketQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mTicketQuestStageComponentsPage.title), 5000);
    expect(await mTicketQuestStageComponentsPage.getTitle()).to.eq('M Ticket Quest Stages');
  });

  it('should load create MTicketQuestStage page', async () => {
    await mTicketQuestStageComponentsPage.clickOnCreateButton();
    mTicketQuestStageUpdatePage = new MTicketQuestStageUpdatePage();
    expect(await mTicketQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Ticket Quest Stage');
    await mTicketQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MTicketQuestStages', async () => {
        const nbButtonsBeforeCreate = await mTicketQuestStageComponentsPage.countDeleteButtons();

        await mTicketQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mTicketQuestStageUpdatePage.setWorldIdInput('5'),
            mTicketQuestStageUpdatePage.setNumberInput('5'),
            mTicketQuestStageUpdatePage.setNameInput('name'),
            mTicketQuestStageUpdatePage.setTicketIdInput('5'),
            mTicketQuestStageUpdatePage.setTicketAmountInput('5'),
            mTicketQuestStageUpdatePage.setImagePathInput('imagePath'),
            mTicketQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mTicketQuestStageUpdatePage.setDifficultyInput('5'),
            mTicketQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mTicketQuestStageUpdatePage.setStoryOnlyInput('5'),
            mTicketQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mTicketQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mTicketQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mTicketQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mTicketQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mTicketQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mTicketQuestStageUpdatePage.setConsumeApInput('5'),
            mTicketQuestStageUpdatePage.setKickOffTypeInput('5'),
            mTicketQuestStageUpdatePage.setMatchMinuteInput('5'),
            mTicketQuestStageUpdatePage.setEnableExtraInput('5'),
            mTicketQuestStageUpdatePage.setEnablePkInput('5'),
            mTicketQuestStageUpdatePage.setAiLevelInput('5'),
            mTicketQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mTicketQuestStageUpdatePage.setStartScoreInput('5'),
            mTicketQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mTicketQuestStageUpdatePage.setConditionIdInput('5'),
            mTicketQuestStageUpdatePage.setOptionIdInput('5'),
            mTicketQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mTicketQuestStageUpdatePage.idSelectLastOption(),
        ]);
        expect(await mTicketQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mTicketQuestStageUpdatePage.getTicketIdInput()).to.eq('5', 'Expected ticketId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getTicketAmountInput()).to.eq('5', 'Expected ticketAmount value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mTicketQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mTicketQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mTicketQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mTicketQuestStageUpdatePage.save();
        expect(await mTicketQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTicketQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTicketQuestStage', async () => {
        const nbButtonsBeforeDelete = await mTicketQuestStageComponentsPage.countDeleteButtons();
        await mTicketQuestStageComponentsPage.clickOnLastDeleteButton();

        mTicketQuestStageDeleteDialog = new MTicketQuestStageDeleteDialog();
        expect(await mTicketQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Ticket Quest Stage?');
        await mTicketQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mTicketQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
