/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTimeattackQuestStageComponentsPage,
  MTimeattackQuestStageDeleteDialog,
  MTimeattackQuestStageUpdatePage
} from './m-timeattack-quest-stage.page-object';

const expect = chai.expect;

describe('MTimeattackQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTimeattackQuestStageUpdatePage: MTimeattackQuestStageUpdatePage;
  let mTimeattackQuestStageComponentsPage: MTimeattackQuestStageComponentsPage;
  /*let mTimeattackQuestStageDeleteDialog: MTimeattackQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTimeattackQuestStages', async () => {
    await navBarPage.goToEntity('m-timeattack-quest-stage');
    mTimeattackQuestStageComponentsPage = new MTimeattackQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mTimeattackQuestStageComponentsPage.title), 5000);
    expect(await mTimeattackQuestStageComponentsPage.getTitle()).to.eq('M Timeattack Quest Stages');
  });

  it('should load create MTimeattackQuestStage page', async () => {
    await mTimeattackQuestStageComponentsPage.clickOnCreateButton();
    mTimeattackQuestStageUpdatePage = new MTimeattackQuestStageUpdatePage();
    expect(await mTimeattackQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Timeattack Quest Stage');
    await mTimeattackQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MTimeattackQuestStages', async () => {
        const nbButtonsBeforeCreate = await mTimeattackQuestStageComponentsPage.countDeleteButtons();

        await mTimeattackQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mTimeattackQuestStageUpdatePage.setWorldIdInput('5'),
            mTimeattackQuestStageUpdatePage.setNumberInput('5'),
            mTimeattackQuestStageUpdatePage.setStartAtInput('5'),
            mTimeattackQuestStageUpdatePage.setNameInput('name'),
            mTimeattackQuestStageUpdatePage.setKickoffDescriptionInput('kickoffDescription'),
            mTimeattackQuestStageUpdatePage.setImagePathInput('imagePath'),
            mTimeattackQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mTimeattackQuestStageUpdatePage.setDifficultyInput('5'),
            mTimeattackQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mTimeattackQuestStageUpdatePage.setStoryOnlyInput('5'),
            mTimeattackQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mTimeattackQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mTimeattackQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mTimeattackQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mTimeattackQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mTimeattackQuestStageUpdatePage.setConsumeApInput('5'),
            mTimeattackQuestStageUpdatePage.setTicketIdInput('5'),
            mTimeattackQuestStageUpdatePage.setTicketAmountInput('5'),
            mTimeattackQuestStageUpdatePage.setKickOffTypeInput('5'),
            mTimeattackQuestStageUpdatePage.setMatchMinuteInput('5'),
            mTimeattackQuestStageUpdatePage.setEnableExtraInput('5'),
            mTimeattackQuestStageUpdatePage.setEnablePkInput('5'),
            mTimeattackQuestStageUpdatePage.setAiLevelInput('5'),
            mTimeattackQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mTimeattackQuestStageUpdatePage.setStartScoreInput('5'),
            mTimeattackQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mTimeattackQuestStageUpdatePage.setConditionIdInput('5'),
            mTimeattackQuestStageUpdatePage.setOptionIdInput('5'),
            mTimeattackQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mTimeattackQuestStageUpdatePage.setShortNameInput('shortName'),
            mTimeattackQuestStageUpdatePage.mtimeattackquestworldSelectLastOption(),
        ]);
        expect(await mTimeattackQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mTimeattackQuestStageUpdatePage.getKickoffDescriptionInput()).to.eq('kickoffDescription', 'Expected KickoffDescription value to be equals to kickoffDescription');
        expect(await mTimeattackQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mTimeattackQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mTimeattackQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getTicketIdInput()).to.eq('5', 'Expected ticketId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getTicketAmountInput()).to.eq('5', 'Expected ticketAmount value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        expect(await mTimeattackQuestStageUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
        await mTimeattackQuestStageUpdatePage.save();
        expect(await mTimeattackQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTimeattackQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTimeattackQuestStage', async () => {
        const nbButtonsBeforeDelete = await mTimeattackQuestStageComponentsPage.countDeleteButtons();
        await mTimeattackQuestStageComponentsPage.clickOnLastDeleteButton();

        mTimeattackQuestStageDeleteDialog = new MTimeattackQuestStageDeleteDialog();
        expect(await mTimeattackQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Timeattack Quest Stage?');
        await mTimeattackQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mTimeattackQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
