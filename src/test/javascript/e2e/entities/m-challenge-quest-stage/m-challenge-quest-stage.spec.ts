/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChallengeQuestStageComponentsPage,
  MChallengeQuestStageDeleteDialog,
  MChallengeQuestStageUpdatePage
} from './m-challenge-quest-stage.page-object';

const expect = chai.expect;

describe('MChallengeQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChallengeQuestStageUpdatePage: MChallengeQuestStageUpdatePage;
  let mChallengeQuestStageComponentsPage: MChallengeQuestStageComponentsPage;
  /*let mChallengeQuestStageDeleteDialog: MChallengeQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChallengeQuestStages', async () => {
    await navBarPage.goToEntity('m-challenge-quest-stage');
    mChallengeQuestStageComponentsPage = new MChallengeQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mChallengeQuestStageComponentsPage.title), 5000);
    expect(await mChallengeQuestStageComponentsPage.getTitle()).to.eq('M Challenge Quest Stages');
  });

  it('should load create MChallengeQuestStage page', async () => {
    await mChallengeQuestStageComponentsPage.clickOnCreateButton();
    mChallengeQuestStageUpdatePage = new MChallengeQuestStageUpdatePage();
    expect(await mChallengeQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Challenge Quest Stage');
    await mChallengeQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MChallengeQuestStages', async () => {
        const nbButtonsBeforeCreate = await mChallengeQuestStageComponentsPage.countDeleteButtons();

        await mChallengeQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mChallengeQuestStageUpdatePage.setWorldIdInput('5'),
            mChallengeQuestStageUpdatePage.setNumberInput('5'),
            mChallengeQuestStageUpdatePage.setNameInput('name'),
            mChallengeQuestStageUpdatePage.setImagePathInput('imagePath'),
            mChallengeQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mChallengeQuestStageUpdatePage.setDifficultyInput('5'),
            mChallengeQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mChallengeQuestStageUpdatePage.setStoryOnlyInput('5'),
            mChallengeQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mChallengeQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mChallengeQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mChallengeQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mChallengeQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mChallengeQuestStageUpdatePage.setConsumeApInput('5'),
            mChallengeQuestStageUpdatePage.setKickOffTypeInput('5'),
            mChallengeQuestStageUpdatePage.setMatchMinuteInput('5'),
            mChallengeQuestStageUpdatePage.setEnableExtraInput('5'),
            mChallengeQuestStageUpdatePage.setEnablePkInput('5'),
            mChallengeQuestStageUpdatePage.setAiLevelInput('5'),
            mChallengeQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mChallengeQuestStageUpdatePage.setStartScoreInput('5'),
            mChallengeQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mChallengeQuestStageUpdatePage.setConditionIdInput('5'),
            mChallengeQuestStageUpdatePage.setOptionIdInput('5'),
            mChallengeQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mChallengeQuestStageUpdatePage.setShortNameInput('shortName'),
            mChallengeQuestStageUpdatePage.setSkipCheckPointInput('5'),
            mChallengeQuestStageUpdatePage.setRoadNumberInput('5'),
            mChallengeQuestStageUpdatePage.mchallengequestworldSelectLastOption(),
        ]);
        expect(await mChallengeQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mChallengeQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mChallengeQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mChallengeQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
        expect(await mChallengeQuestStageUpdatePage.getSkipCheckPointInput()).to.eq('5', 'Expected skipCheckPoint value to be equals to 5');
        expect(await mChallengeQuestStageUpdatePage.getRoadNumberInput()).to.eq('5', 'Expected roadNumber value to be equals to 5');
        await mChallengeQuestStageUpdatePage.save();
        expect(await mChallengeQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mChallengeQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MChallengeQuestStage', async () => {
        const nbButtonsBeforeDelete = await mChallengeQuestStageComponentsPage.countDeleteButtons();
        await mChallengeQuestStageComponentsPage.clickOnLastDeleteButton();

        mChallengeQuestStageDeleteDialog = new MChallengeQuestStageDeleteDialog();
        expect(await mChallengeQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Challenge Quest Stage?');
        await mChallengeQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mChallengeQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
