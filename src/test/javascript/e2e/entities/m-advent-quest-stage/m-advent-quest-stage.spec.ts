/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAdventQuestStageComponentsPage,
  MAdventQuestStageDeleteDialog,
  MAdventQuestStageUpdatePage
} from './m-advent-quest-stage.page-object';

const expect = chai.expect;

describe('MAdventQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAdventQuestStageUpdatePage: MAdventQuestStageUpdatePage;
  let mAdventQuestStageComponentsPage: MAdventQuestStageComponentsPage;
  /*let mAdventQuestStageDeleteDialog: MAdventQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAdventQuestStages', async () => {
    await navBarPage.goToEntity('m-advent-quest-stage');
    mAdventQuestStageComponentsPage = new MAdventQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mAdventQuestStageComponentsPage.title), 5000);
    expect(await mAdventQuestStageComponentsPage.getTitle()).to.eq('M Advent Quest Stages');
  });

  it('should load create MAdventQuestStage page', async () => {
    await mAdventQuestStageComponentsPage.clickOnCreateButton();
    mAdventQuestStageUpdatePage = new MAdventQuestStageUpdatePage();
    expect(await mAdventQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Advent Quest Stage');
    await mAdventQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MAdventQuestStages', async () => {
        const nbButtonsBeforeCreate = await mAdventQuestStageComponentsPage.countDeleteButtons();

        await mAdventQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mAdventQuestStageUpdatePage.setWorldIdInput('5'),
            mAdventQuestStageUpdatePage.setNumberInput('5'),
            mAdventQuestStageUpdatePage.setNameInput('name'),
            mAdventQuestStageUpdatePage.setImagePathInput('imagePath'),
            mAdventQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mAdventQuestStageUpdatePage.setDifficultyInput('5'),
            mAdventQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mAdventQuestStageUpdatePage.setStoryOnlyInput('5'),
            mAdventQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mAdventQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mAdventQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mAdventQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mAdventQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mAdventQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mAdventQuestStageUpdatePage.setConsumeApInput('5'),
            mAdventQuestStageUpdatePage.setKickOffTypeInput('5'),
            mAdventQuestStageUpdatePage.setMatchMinuteInput('5'),
            mAdventQuestStageUpdatePage.setEnableExtraInput('5'),
            mAdventQuestStageUpdatePage.setEnablePkInput('5'),
            mAdventQuestStageUpdatePage.setAiLevelInput('5'),
            mAdventQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mAdventQuestStageUpdatePage.setStartScoreInput('5'),
            mAdventQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mAdventQuestStageUpdatePage.setConditionIdInput('5'),
            mAdventQuestStageUpdatePage.setOptionIdInput('5'),
            mAdventQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mAdventQuestStageUpdatePage.madventquestworldSelectLastOption(),
        ]);
        expect(await mAdventQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mAdventQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mAdventQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mAdventQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mAdventQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mAdventQuestStageUpdatePage.save();
        expect(await mAdventQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mAdventQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MAdventQuestStage', async () => {
        const nbButtonsBeforeDelete = await mAdventQuestStageComponentsPage.countDeleteButtons();
        await mAdventQuestStageComponentsPage.clickOnLastDeleteButton();

        mAdventQuestStageDeleteDialog = new MAdventQuestStageDeleteDialog();
        expect(await mAdventQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Advent Quest Stage?');
        await mAdventQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mAdventQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
