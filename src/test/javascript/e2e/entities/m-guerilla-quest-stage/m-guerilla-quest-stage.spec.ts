/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuerillaQuestStageComponentsPage,
  MGuerillaQuestStageDeleteDialog,
  MGuerillaQuestStageUpdatePage
} from './m-guerilla-quest-stage.page-object';

const expect = chai.expect;

describe('MGuerillaQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuerillaQuestStageUpdatePage: MGuerillaQuestStageUpdatePage;
  let mGuerillaQuestStageComponentsPage: MGuerillaQuestStageComponentsPage;
  /*let mGuerillaQuestStageDeleteDialog: MGuerillaQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuerillaQuestStages', async () => {
    await navBarPage.goToEntity('m-guerilla-quest-stage');
    mGuerillaQuestStageComponentsPage = new MGuerillaQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mGuerillaQuestStageComponentsPage.title), 5000);
    expect(await mGuerillaQuestStageComponentsPage.getTitle()).to.eq('M Guerilla Quest Stages');
  });

  it('should load create MGuerillaQuestStage page', async () => {
    await mGuerillaQuestStageComponentsPage.clickOnCreateButton();
    mGuerillaQuestStageUpdatePage = new MGuerillaQuestStageUpdatePage();
    expect(await mGuerillaQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Guerilla Quest Stage');
    await mGuerillaQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MGuerillaQuestStages', async () => {
        const nbButtonsBeforeCreate = await mGuerillaQuestStageComponentsPage.countDeleteButtons();

        await mGuerillaQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mGuerillaQuestStageUpdatePage.setWorldIdInput('5'),
            mGuerillaQuestStageUpdatePage.setNumberInput('5'),
            mGuerillaQuestStageUpdatePage.setNameInput('name'),
            mGuerillaQuestStageUpdatePage.setImagePathInput('imagePath'),
            mGuerillaQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mGuerillaQuestStageUpdatePage.setDifficultyInput('5'),
            mGuerillaQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mGuerillaQuestStageUpdatePage.setStoryOnlyInput('5'),
            mGuerillaQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mGuerillaQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mGuerillaQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mGuerillaQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mGuerillaQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mGuerillaQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mGuerillaQuestStageUpdatePage.setConsumeApInput('5'),
            mGuerillaQuestStageUpdatePage.setKickOffTypeInput('5'),
            mGuerillaQuestStageUpdatePage.setMatchMinuteInput('5'),
            mGuerillaQuestStageUpdatePage.setEnableExtraInput('5'),
            mGuerillaQuestStageUpdatePage.setEnablePkInput('5'),
            mGuerillaQuestStageUpdatePage.setAiLevelInput('5'),
            mGuerillaQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mGuerillaQuestStageUpdatePage.setStartScoreInput('5'),
            mGuerillaQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mGuerillaQuestStageUpdatePage.setConditionIdInput('5'),
            mGuerillaQuestStageUpdatePage.setOptionIdInput('5'),
            mGuerillaQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mGuerillaQuestStageUpdatePage.mguerillaquestworldSelectLastOption(),
        ]);
        expect(await mGuerillaQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mGuerillaQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mGuerillaQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mGuerillaQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mGuerillaQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mGuerillaQuestStageUpdatePage.save();
        expect(await mGuerillaQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mGuerillaQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MGuerillaQuestStage', async () => {
        const nbButtonsBeforeDelete = await mGuerillaQuestStageComponentsPage.countDeleteButtons();
        await mGuerillaQuestStageComponentsPage.clickOnLastDeleteButton();

        mGuerillaQuestStageDeleteDialog = new MGuerillaQuestStageDeleteDialog();
        expect(await mGuerillaQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Guerilla Quest Stage?');
        await mGuerillaQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mGuerillaQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
