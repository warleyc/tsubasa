/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MWeeklyQuestStageComponentsPage,
  MWeeklyQuestStageDeleteDialog,
  MWeeklyQuestStageUpdatePage
} from './m-weekly-quest-stage.page-object';

const expect = chai.expect;

describe('MWeeklyQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mWeeklyQuestStageUpdatePage: MWeeklyQuestStageUpdatePage;
  let mWeeklyQuestStageComponentsPage: MWeeklyQuestStageComponentsPage;
  /*let mWeeklyQuestStageDeleteDialog: MWeeklyQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MWeeklyQuestStages', async () => {
    await navBarPage.goToEntity('m-weekly-quest-stage');
    mWeeklyQuestStageComponentsPage = new MWeeklyQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mWeeklyQuestStageComponentsPage.title), 5000);
    expect(await mWeeklyQuestStageComponentsPage.getTitle()).to.eq('M Weekly Quest Stages');
  });

  it('should load create MWeeklyQuestStage page', async () => {
    await mWeeklyQuestStageComponentsPage.clickOnCreateButton();
    mWeeklyQuestStageUpdatePage = new MWeeklyQuestStageUpdatePage();
    expect(await mWeeklyQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Weekly Quest Stage');
    await mWeeklyQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MWeeklyQuestStages', async () => {
        const nbButtonsBeforeCreate = await mWeeklyQuestStageComponentsPage.countDeleteButtons();

        await mWeeklyQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mWeeklyQuestStageUpdatePage.setWorldIdInput('5'),
            mWeeklyQuestStageUpdatePage.setNumberInput('5'),
            mWeeklyQuestStageUpdatePage.setNameInput('name'),
            mWeeklyQuestStageUpdatePage.setImagePathInput('imagePath'),
            mWeeklyQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mWeeklyQuestStageUpdatePage.setDifficultyInput('5'),
            mWeeklyQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mWeeklyQuestStageUpdatePage.setStoryOnlyInput('5'),
            mWeeklyQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mWeeklyQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mWeeklyQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mWeeklyQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mWeeklyQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mWeeklyQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mWeeklyQuestStageUpdatePage.setConsumeApInput('5'),
            mWeeklyQuestStageUpdatePage.setKickOffTypeInput('5'),
            mWeeklyQuestStageUpdatePage.setMatchMinuteInput('5'),
            mWeeklyQuestStageUpdatePage.setEnableExtraInput('5'),
            mWeeklyQuestStageUpdatePage.setEnablePkInput('5'),
            mWeeklyQuestStageUpdatePage.setAiLevelInput('5'),
            mWeeklyQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mWeeklyQuestStageUpdatePage.setStartScoreInput('5'),
            mWeeklyQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mWeeklyQuestStageUpdatePage.setConditionIdInput('5'),
            mWeeklyQuestStageUpdatePage.setOptionIdInput('5'),
            mWeeklyQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mWeeklyQuestStageUpdatePage.idSelectLastOption(),
        ]);
        expect(await mWeeklyQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mWeeklyQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mWeeklyQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mWeeklyQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mWeeklyQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mWeeklyQuestStageUpdatePage.save();
        expect(await mWeeklyQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mWeeklyQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MWeeklyQuestStage', async () => {
        const nbButtonsBeforeDelete = await mWeeklyQuestStageComponentsPage.countDeleteButtons();
        await mWeeklyQuestStageComponentsPage.clickOnLastDeleteButton();

        mWeeklyQuestStageDeleteDialog = new MWeeklyQuestStageDeleteDialog();
        expect(await mWeeklyQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Weekly Quest Stage?');
        await mWeeklyQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mWeeklyQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
