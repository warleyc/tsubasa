/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLuckWeeklyQuestStageComponentsPage,
  MLuckWeeklyQuestStageDeleteDialog,
  MLuckWeeklyQuestStageUpdatePage
} from './m-luck-weekly-quest-stage.page-object';

const expect = chai.expect;

describe('MLuckWeeklyQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLuckWeeklyQuestStageUpdatePage: MLuckWeeklyQuestStageUpdatePage;
  let mLuckWeeklyQuestStageComponentsPage: MLuckWeeklyQuestStageComponentsPage;
  /*let mLuckWeeklyQuestStageDeleteDialog: MLuckWeeklyQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLuckWeeklyQuestStages', async () => {
    await navBarPage.goToEntity('m-luck-weekly-quest-stage');
    mLuckWeeklyQuestStageComponentsPage = new MLuckWeeklyQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mLuckWeeklyQuestStageComponentsPage.title), 5000);
    expect(await mLuckWeeklyQuestStageComponentsPage.getTitle()).to.eq('M Luck Weekly Quest Stages');
  });

  it('should load create MLuckWeeklyQuestStage page', async () => {
    await mLuckWeeklyQuestStageComponentsPage.clickOnCreateButton();
    mLuckWeeklyQuestStageUpdatePage = new MLuckWeeklyQuestStageUpdatePage();
    expect(await mLuckWeeklyQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Luck Weekly Quest Stage');
    await mLuckWeeklyQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MLuckWeeklyQuestStages', async () => {
        const nbButtonsBeforeCreate = await mLuckWeeklyQuestStageComponentsPage.countDeleteButtons();

        await mLuckWeeklyQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mLuckWeeklyQuestStageUpdatePage.setWorldIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setNumberInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setNameInput('name'),
            mLuckWeeklyQuestStageUpdatePage.setImagePathInput('imagePath'),
            mLuckWeeklyQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mLuckWeeklyQuestStageUpdatePage.setDifficultyInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setStoryOnlyInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setConsumeApInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setKickOffTypeInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setMatchMinuteInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setEnableExtraInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setEnablePkInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setAiLevelInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setStartScoreInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setConditionIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setOptionIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.setLuckIdInput('5'),
            mLuckWeeklyQuestStageUpdatePage.idSelectLastOption(),
        ]);
        expect(await mLuckWeeklyQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mLuckWeeklyQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mLuckWeeklyQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mLuckWeeklyQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        expect(await mLuckWeeklyQuestStageUpdatePage.getLuckIdInput()).to.eq('5', 'Expected luckId value to be equals to 5');
        await mLuckWeeklyQuestStageUpdatePage.save();
        expect(await mLuckWeeklyQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mLuckWeeklyQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MLuckWeeklyQuestStage', async () => {
        const nbButtonsBeforeDelete = await mLuckWeeklyQuestStageComponentsPage.countDeleteButtons();
        await mLuckWeeklyQuestStageComponentsPage.clickOnLastDeleteButton();

        mLuckWeeklyQuestStageDeleteDialog = new MLuckWeeklyQuestStageDeleteDialog();
        expect(await mLuckWeeklyQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Luck Weekly Quest Stage?');
        await mLuckWeeklyQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mLuckWeeklyQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
