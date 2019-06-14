import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MChallengeQuestStageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-challenge-quest-stage div table .btn-danger'));
  title = element.all(by.css('jhi-m-challenge-quest-stage div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class MChallengeQuestStageUpdatePage {
  pageTitle = element(by.id('jhi-m-challenge-quest-stage-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  worldIdInput = element(by.id('field_worldId'));
  numberInput = element(by.id('field_number'));
  nameInput = element(by.id('field_name'));
  imagePathInput = element(by.id('field_imagePath'));
  characterThumbnailPathInput = element(by.id('field_characterThumbnailPath'));
  difficultyInput = element(by.id('field_difficulty'));
  stageUnlockPatternInput = element(by.id('field_stageUnlockPattern'));
  storyOnlyInput = element(by.id('field_storyOnly'));
  firstHalfNpcDeckIdInput = element(by.id('field_firstHalfNpcDeckId'));
  firstHalfEnvironmentIdInput = element(by.id('field_firstHalfEnvironmentId'));
  secondHalfNpcDeckIdInput = element(by.id('field_secondHalfNpcDeckId'));
  secondHalfEnvironmentIdInput = element(by.id('field_secondHalfEnvironmentId'));
  extraFirstHalfNpcDeckIdInput = element(by.id('field_extraFirstHalfNpcDeckId'));
  consumeApInput = element(by.id('field_consumeAp'));
  kickOffTypeInput = element(by.id('field_kickOffType'));
  matchMinuteInput = element(by.id('field_matchMinute'));
  enableExtraInput = element(by.id('field_enableExtra'));
  enablePkInput = element(by.id('field_enablePk'));
  aiLevelInput = element(by.id('field_aiLevel'));
  startAtSecondHalfInput = element(by.id('field_startAtSecondHalf'));
  startScoreInput = element(by.id('field_startScore'));
  startScoreOpponentInput = element(by.id('field_startScoreOpponent'));
  conditionIdInput = element(by.id('field_conditionId'));
  optionIdInput = element(by.id('field_optionId'));
  deckConditionIdInput = element(by.id('field_deckConditionId'));
  shortNameInput = element(by.id('field_shortName'));
  skipCheckPointInput = element(by.id('field_skipCheckPoint'));
  roadNumberInput = element(by.id('field_roadNumber'));
  mchallengequestworldSelect = element(by.id('field_mchallengequestworld'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWorldIdInput(worldId) {
    await this.worldIdInput.sendKeys(worldId);
  }

  async getWorldIdInput() {
    return await this.worldIdInput.getAttribute('value');
  }

  async setNumberInput(number) {
    await this.numberInput.sendKeys(number);
  }

  async getNumberInput() {
    return await this.numberInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setImagePathInput(imagePath) {
    await this.imagePathInput.sendKeys(imagePath);
  }

  async getImagePathInput() {
    return await this.imagePathInput.getAttribute('value');
  }

  async setCharacterThumbnailPathInput(characterThumbnailPath) {
    await this.characterThumbnailPathInput.sendKeys(characterThumbnailPath);
  }

  async getCharacterThumbnailPathInput() {
    return await this.characterThumbnailPathInput.getAttribute('value');
  }

  async setDifficultyInput(difficulty) {
    await this.difficultyInput.sendKeys(difficulty);
  }

  async getDifficultyInput() {
    return await this.difficultyInput.getAttribute('value');
  }

  async setStageUnlockPatternInput(stageUnlockPattern) {
    await this.stageUnlockPatternInput.sendKeys(stageUnlockPattern);
  }

  async getStageUnlockPatternInput() {
    return await this.stageUnlockPatternInput.getAttribute('value');
  }

  async setStoryOnlyInput(storyOnly) {
    await this.storyOnlyInput.sendKeys(storyOnly);
  }

  async getStoryOnlyInput() {
    return await this.storyOnlyInput.getAttribute('value');
  }

  async setFirstHalfNpcDeckIdInput(firstHalfNpcDeckId) {
    await this.firstHalfNpcDeckIdInput.sendKeys(firstHalfNpcDeckId);
  }

  async getFirstHalfNpcDeckIdInput() {
    return await this.firstHalfNpcDeckIdInput.getAttribute('value');
  }

  async setFirstHalfEnvironmentIdInput(firstHalfEnvironmentId) {
    await this.firstHalfEnvironmentIdInput.sendKeys(firstHalfEnvironmentId);
  }

  async getFirstHalfEnvironmentIdInput() {
    return await this.firstHalfEnvironmentIdInput.getAttribute('value');
  }

  async setSecondHalfNpcDeckIdInput(secondHalfNpcDeckId) {
    await this.secondHalfNpcDeckIdInput.sendKeys(secondHalfNpcDeckId);
  }

  async getSecondHalfNpcDeckIdInput() {
    return await this.secondHalfNpcDeckIdInput.getAttribute('value');
  }

  async setSecondHalfEnvironmentIdInput(secondHalfEnvironmentId) {
    await this.secondHalfEnvironmentIdInput.sendKeys(secondHalfEnvironmentId);
  }

  async getSecondHalfEnvironmentIdInput() {
    return await this.secondHalfEnvironmentIdInput.getAttribute('value');
  }

  async setExtraFirstHalfNpcDeckIdInput(extraFirstHalfNpcDeckId) {
    await this.extraFirstHalfNpcDeckIdInput.sendKeys(extraFirstHalfNpcDeckId);
  }

  async getExtraFirstHalfNpcDeckIdInput() {
    return await this.extraFirstHalfNpcDeckIdInput.getAttribute('value');
  }

  async setConsumeApInput(consumeAp) {
    await this.consumeApInput.sendKeys(consumeAp);
  }

  async getConsumeApInput() {
    return await this.consumeApInput.getAttribute('value');
  }

  async setKickOffTypeInput(kickOffType) {
    await this.kickOffTypeInput.sendKeys(kickOffType);
  }

  async getKickOffTypeInput() {
    return await this.kickOffTypeInput.getAttribute('value');
  }

  async setMatchMinuteInput(matchMinute) {
    await this.matchMinuteInput.sendKeys(matchMinute);
  }

  async getMatchMinuteInput() {
    return await this.matchMinuteInput.getAttribute('value');
  }

  async setEnableExtraInput(enableExtra) {
    await this.enableExtraInput.sendKeys(enableExtra);
  }

  async getEnableExtraInput() {
    return await this.enableExtraInput.getAttribute('value');
  }

  async setEnablePkInput(enablePk) {
    await this.enablePkInput.sendKeys(enablePk);
  }

  async getEnablePkInput() {
    return await this.enablePkInput.getAttribute('value');
  }

  async setAiLevelInput(aiLevel) {
    await this.aiLevelInput.sendKeys(aiLevel);
  }

  async getAiLevelInput() {
    return await this.aiLevelInput.getAttribute('value');
  }

  async setStartAtSecondHalfInput(startAtSecondHalf) {
    await this.startAtSecondHalfInput.sendKeys(startAtSecondHalf);
  }

  async getStartAtSecondHalfInput() {
    return await this.startAtSecondHalfInput.getAttribute('value');
  }

  async setStartScoreInput(startScore) {
    await this.startScoreInput.sendKeys(startScore);
  }

  async getStartScoreInput() {
    return await this.startScoreInput.getAttribute('value');
  }

  async setStartScoreOpponentInput(startScoreOpponent) {
    await this.startScoreOpponentInput.sendKeys(startScoreOpponent);
  }

  async getStartScoreOpponentInput() {
    return await this.startScoreOpponentInput.getAttribute('value');
  }

  async setConditionIdInput(conditionId) {
    await this.conditionIdInput.sendKeys(conditionId);
  }

  async getConditionIdInput() {
    return await this.conditionIdInput.getAttribute('value');
  }

  async setOptionIdInput(optionId) {
    await this.optionIdInput.sendKeys(optionId);
  }

  async getOptionIdInput() {
    return await this.optionIdInput.getAttribute('value');
  }

  async setDeckConditionIdInput(deckConditionId) {
    await this.deckConditionIdInput.sendKeys(deckConditionId);
  }

  async getDeckConditionIdInput() {
    return await this.deckConditionIdInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setSkipCheckPointInput(skipCheckPoint) {
    await this.skipCheckPointInput.sendKeys(skipCheckPoint);
  }

  async getSkipCheckPointInput() {
    return await this.skipCheckPointInput.getAttribute('value');
  }

  async setRoadNumberInput(roadNumber) {
    await this.roadNumberInput.sendKeys(roadNumber);
  }

  async getRoadNumberInput() {
    return await this.roadNumberInput.getAttribute('value');
  }

  async mchallengequestworldSelectLastOption(timeout?: number) {
    await this.mchallengequestworldSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mchallengequestworldSelectOption(option) {
    await this.mchallengequestworldSelect.sendKeys(option);
  }

  getMchallengequestworldSelect(): ElementFinder {
    return this.mchallengequestworldSelect;
  }

  async getMchallengequestworldSelectedOption() {
    return await this.mchallengequestworldSelect.element(by.css('option:checked')).getText();
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class MChallengeQuestStageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mChallengeQuestStage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mChallengeQuestStage'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
