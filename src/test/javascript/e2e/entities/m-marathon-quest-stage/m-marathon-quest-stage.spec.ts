/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonQuestStageComponentsPage,
  MMarathonQuestStageDeleteDialog,
  MMarathonQuestStageUpdatePage
} from './m-marathon-quest-stage.page-object';

const expect = chai.expect;

describe('MMarathonQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonQuestStageUpdatePage: MMarathonQuestStageUpdatePage;
  let mMarathonQuestStageComponentsPage: MMarathonQuestStageComponentsPage;
  /*let mMarathonQuestStageDeleteDialog: MMarathonQuestStageDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonQuestStages', async () => {
    await navBarPage.goToEntity('m-marathon-quest-stage');
    mMarathonQuestStageComponentsPage = new MMarathonQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonQuestStageComponentsPage.title), 5000);
    expect(await mMarathonQuestStageComponentsPage.getTitle()).to.eq('M Marathon Quest Stages');
  });

  it('should load create MMarathonQuestStage page', async () => {
    await mMarathonQuestStageComponentsPage.clickOnCreateButton();
    mMarathonQuestStageUpdatePage = new MMarathonQuestStageUpdatePage();
    expect(await mMarathonQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Quest Stage');
    await mMarathonQuestStageUpdatePage.cancel();
  });

  /* it('should create and save MMarathonQuestStages', async () => {
        const nbButtonsBeforeCreate = await mMarathonQuestStageComponentsPage.countDeleteButtons();

        await mMarathonQuestStageComponentsPage.clickOnCreateButton();
        await promise.all([
            mMarathonQuestStageUpdatePage.setWorldIdInput('5'),
            mMarathonQuestStageUpdatePage.setNumberInput('5'),
            mMarathonQuestStageUpdatePage.setNameInput('name'),
            mMarathonQuestStageUpdatePage.setImagePathInput('imagePath'),
            mMarathonQuestStageUpdatePage.setCharacterThumbnailPathInput('characterThumbnailPath'),
            mMarathonQuestStageUpdatePage.setDifficultyInput('5'),
            mMarathonQuestStageUpdatePage.setStageUnlockPatternInput('5'),
            mMarathonQuestStageUpdatePage.setStoryOnlyInput('5'),
            mMarathonQuestStageUpdatePage.setFirstHalfNpcDeckIdInput('5'),
            mMarathonQuestStageUpdatePage.setFirstHalfEnvironmentIdInput('5'),
            mMarathonQuestStageUpdatePage.setSecondHalfNpcDeckIdInput('5'),
            mMarathonQuestStageUpdatePage.setSecondHalfEnvironmentIdInput('5'),
            mMarathonQuestStageUpdatePage.setExtraFirstHalfNpcDeckIdInput('5'),
            mMarathonQuestStageUpdatePage.setExtraSecondHalfNpcDeckIdInput('5'),
            mMarathonQuestStageUpdatePage.setConsumeApInput('5'),
            mMarathonQuestStageUpdatePage.setKickOffTypeInput('5'),
            mMarathonQuestStageUpdatePage.setMatchMinuteInput('5'),
            mMarathonQuestStageUpdatePage.setEnableExtraInput('5'),
            mMarathonQuestStageUpdatePage.setEnablePkInput('5'),
            mMarathonQuestStageUpdatePage.setAiLevelInput('5'),
            mMarathonQuestStageUpdatePage.setStartAtSecondHalfInput('5'),
            mMarathonQuestStageUpdatePage.setStartScoreInput('5'),
            mMarathonQuestStageUpdatePage.setStartScoreOpponentInput('5'),
            mMarathonQuestStageUpdatePage.setConditionIdInput('5'),
            mMarathonQuestStageUpdatePage.setOptionIdInput('5'),
            mMarathonQuestStageUpdatePage.setDeckConditionIdInput('5'),
            mMarathonQuestStageUpdatePage.idSelectLastOption(),
        ]);
        expect(await mMarathonQuestStageUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mMarathonQuestStageUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
        expect(await mMarathonQuestStageUpdatePage.getCharacterThumbnailPathInput()).to.eq('characterThumbnailPath', 'Expected CharacterThumbnailPath value to be equals to characterThumbnailPath');
        expect(await mMarathonQuestStageUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getStoryOnlyInput()).to.eq('5', 'Expected storyOnly value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected firstHalfNpcDeckId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getFirstHalfEnvironmentIdInput()).to.eq('5', 'Expected firstHalfEnvironmentId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected secondHalfNpcDeckId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getSecondHalfEnvironmentIdInput()).to.eq('5', 'Expected secondHalfEnvironmentId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getExtraFirstHalfNpcDeckIdInput()).to.eq('5', 'Expected extraFirstHalfNpcDeckId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getExtraSecondHalfNpcDeckIdInput()).to.eq('5', 'Expected extraSecondHalfNpcDeckId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getConsumeApInput()).to.eq('5', 'Expected consumeAp value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getKickOffTypeInput()).to.eq('5', 'Expected kickOffType value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getMatchMinuteInput()).to.eq('5', 'Expected matchMinute value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getEnableExtraInput()).to.eq('5', 'Expected enableExtra value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getEnablePkInput()).to.eq('5', 'Expected enablePk value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getAiLevelInput()).to.eq('5', 'Expected aiLevel value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getStartAtSecondHalfInput()).to.eq('5', 'Expected startAtSecondHalf value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getStartScoreInput()).to.eq('5', 'Expected startScore value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getStartScoreOpponentInput()).to.eq('5', 'Expected startScoreOpponent value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getConditionIdInput()).to.eq('5', 'Expected conditionId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getOptionIdInput()).to.eq('5', 'Expected optionId value to be equals to 5');
        expect(await mMarathonQuestStageUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        await mMarathonQuestStageUpdatePage.save();
        expect(await mMarathonQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mMarathonQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MMarathonQuestStage', async () => {
        const nbButtonsBeforeDelete = await mMarathonQuestStageComponentsPage.countDeleteButtons();
        await mMarathonQuestStageComponentsPage.clickOnLastDeleteButton();

        mMarathonQuestStageDeleteDialog = new MMarathonQuestStageDeleteDialog();
        expect(await mMarathonQuestStageDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Marathon Quest Stage?');
        await mMarathonQuestStageDeleteDialog.clickOnConfirmButton();

        expect(await mMarathonQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
