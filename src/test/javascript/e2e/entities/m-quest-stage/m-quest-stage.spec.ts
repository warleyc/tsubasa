/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MQuestStageComponentsPage, MQuestStageDeleteDialog, MQuestStageUpdatePage } from './m-quest-stage.page-object';

const expect = chai.expect;

describe('MQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestStageUpdatePage: MQuestStageUpdatePage;
  let mQuestStageComponentsPage: MQuestStageComponentsPage;
  /*let mQuestStageDeleteDialog: MQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestStages', async () => {
    await navBarPage.goToEntity('m-quest-stage');
    mQuestStageComponentsPage = new MQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestStageComponentsPage.title), 5000);
    expect(await mQuestStageComponentsPage.getTitle()).to.eq('M Quest Stages');
  });

  it('should load create MQuestStage page', async () => {
    await mQuestStageComponentsPage.clickOnCreateButton();
    mQuestStageUpdatePage = new MQuestStageUpdatePage();
    expect(await mQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Stage');
    await mQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MQuestStages', async () => {
        const nbButtonsBeforeCreate = await mQuestStageComponentsPage.countDeleteButtons();

        await mQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mQuestStageUpdatePage.setWorldIdInput('5'),
            mQuestStageUpdatePage.setNumberInput('5'),
            mQuestStageUpdatePage.setNameInput('name'),
            mQuestStageUpdatePage.setImagePathInput('imagePath'),
            mQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mQuestStageUpdatePage.setDifficultyInput('5'),
            mQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mQuestStageUpdatePage.setStoryOnlyInput('5'),
            mQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mQuestStageUpdatePage.setConsumeApInput('5'),
            mQuestStageUpdatePage.setKickOffTypeInput('5'),
            mQuestStageUpdatePage.setMatchMinuteInput('5'),
            mQuestStageUpdatePage.setEnableExtraInput('5'),
            mQuestStageUpdatePage.setEnablePkInput('5'),
            mQuestStageUpdatePage.setAiLevelInput('5'),
            mQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mQuestStageUpdatePage.setStartScoreInput('5'),
            mQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mQuestStageUpdatePage.setConditionIdInput('5'),
            mQuestStageUpdatePage.setOptionIdInput('5'),
            mQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mQuestStageUpdatePage.mquestworldSelectLastOption(),
        ]);
        expect(await mQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mQuestStageUpdatePage.save();
        expect(await mQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MQuestStage', async () => {
        const nbButtonsBeforeDelete = await mQuestStageComponentsPage.countDeleteButtons();
        await mQuestStageComponentsPage.clickOnLastDeleteButton();

        mQuestStageDeleteDialog = new MQuestStageDeleteDialog();
        expect(await mQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Quest Stage?');
        await mQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
