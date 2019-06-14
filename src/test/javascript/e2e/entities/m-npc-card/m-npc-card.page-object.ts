import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MNpcCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-npc-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-npc-card div h2#page-heading span')).first();

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

export class MNpcCardUpdatePage {
  pageTitle = element(by.id('jhi-m-npc-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  characterIdInput = element(by.id('field_characterId'));
  shortNameInput = element(by.id('field_shortName'));
  nickNameInput = element(by.id('field_nickName'));
  teamIdInput = element(by.id('field_teamId'));
  nationalityIdInput = element(by.id('field_nationalityId'));
  rarityInput = element(by.id('field_rarity'));
  attributeInput = element(by.id('field_attribute'));
  modelIdInput = element(by.id('field_modelId'));
  levelInput = element(by.id('field_level'));
  thumbnailAssetsIdInput = element(by.id('field_thumbnailAssetsId'));
  cardIllustAssetsIdInput = element(by.id('field_cardIllustAssetsId'));
  playableAssetsIdInput = element(by.id('field_playableAssetsId'));
  teamEffectIdInput = element(by.id('field_teamEffectId'));
  teamEffectLevelInput = element(by.id('field_teamEffectLevel'));
  triggerEffectIdInput = element(by.id('field_triggerEffectId'));
  action1IdInput = element(by.id('field_action1Id'));
  action1LevelInput = element(by.id('field_action1Level'));
  action2IdInput = element(by.id('field_action2Id'));
  action2LevelInput = element(by.id('field_action2Level'));
  action3IdInput = element(by.id('field_action3Id'));
  action3LevelInput = element(by.id('field_action3Level'));
  action4IdInput = element(by.id('field_action4Id'));
  action4LevelInput = element(by.id('field_action4Level'));
  action5IdInput = element(by.id('field_action5Id'));
  action5LevelInput = element(by.id('field_action5Level'));
  staminaInput = element(by.id('field_stamina'));
  dribbleInput = element(by.id('field_dribble'));
  shootInput = element(by.id('field_shoot'));
  ballPassInput = element(by.id('field_ballPass'));
  tackleInput = element(by.id('field_tackle'));
  blockInput = element(by.id('field_block'));
  interceptInput = element(by.id('field_intercept'));
  speedInput = element(by.id('field_speed'));
  powerInput = element(by.id('field_power'));
  techniqueInput = element(by.id('field_technique'));
  punchingInput = element(by.id('field_punching'));
  catchingInput = element(by.id('field_catching'));
  highBallBonusInput = element(by.id('field_highBallBonus'));
  lowBallBonusInput = element(by.id('field_lowBallBonus'));
  personalityIdInput = element(by.id('field_personalityId'));
  uniformNoInput = element(by.id('field_uniformNo'));
  levelGroupIdInput = element(by.id('field_levelGroupId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCharacterIdInput(characterId) {
    await this.characterIdInput.sendKeys(characterId);
  }

  async getCharacterIdInput() {
    return await this.characterIdInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setNickNameInput(nickName) {
    await this.nickNameInput.sendKeys(nickName);
  }

  async getNickNameInput() {
    return await this.nickNameInput.getAttribute('value');
  }

  async setTeamIdInput(teamId) {
    await this.teamIdInput.sendKeys(teamId);
  }

  async getTeamIdInput() {
    return await this.teamIdInput.getAttribute('value');
  }

  async setNationalityIdInput(nationalityId) {
    await this.nationalityIdInput.sendKeys(nationalityId);
  }

  async getNationalityIdInput() {
    return await this.nationalityIdInput.getAttribute('value');
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setAttributeInput(attribute) {
    await this.attributeInput.sendKeys(attribute);
  }

  async getAttributeInput() {
    return await this.attributeInput.getAttribute('value');
  }

  async setModelIdInput(modelId) {
    await this.modelIdInput.sendKeys(modelId);
  }

  async getModelIdInput() {
    return await this.modelIdInput.getAttribute('value');
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setThumbnailAssetsIdInput(thumbnailAssetsId) {
    await this.thumbnailAssetsIdInput.sendKeys(thumbnailAssetsId);
  }

  async getThumbnailAssetsIdInput() {
    return await this.thumbnailAssetsIdInput.getAttribute('value');
  }

  async setCardIllustAssetsIdInput(cardIllustAssetsId) {
    await this.cardIllustAssetsIdInput.sendKeys(cardIllustAssetsId);
  }

  async getCardIllustAssetsIdInput() {
    return await this.cardIllustAssetsIdInput.getAttribute('value');
  }

  async setPlayableAssetsIdInput(playableAssetsId) {
    await this.playableAssetsIdInput.sendKeys(playableAssetsId);
  }

  async getPlayableAssetsIdInput() {
    return await this.playableAssetsIdInput.getAttribute('value');
  }

  async setTeamEffectIdInput(teamEffectId) {
    await this.teamEffectIdInput.sendKeys(teamEffectId);
  }

  async getTeamEffectIdInput() {
    return await this.teamEffectIdInput.getAttribute('value');
  }

  async setTeamEffectLevelInput(teamEffectLevel) {
    await this.teamEffectLevelInput.sendKeys(teamEffectLevel);
  }

  async getTeamEffectLevelInput() {
    return await this.teamEffectLevelInput.getAttribute('value');
  }

  async setTriggerEffectIdInput(triggerEffectId) {
    await this.triggerEffectIdInput.sendKeys(triggerEffectId);
  }

  async getTriggerEffectIdInput() {
    return await this.triggerEffectIdInput.getAttribute('value');
  }

  async setAction1IdInput(action1Id) {
    await this.action1IdInput.sendKeys(action1Id);
  }

  async getAction1IdInput() {
    return await this.action1IdInput.getAttribute('value');
  }

  async setAction1LevelInput(action1Level) {
    await this.action1LevelInput.sendKeys(action1Level);
  }

  async getAction1LevelInput() {
    return await this.action1LevelInput.getAttribute('value');
  }

  async setAction2IdInput(action2Id) {
    await this.action2IdInput.sendKeys(action2Id);
  }

  async getAction2IdInput() {
    return await this.action2IdInput.getAttribute('value');
  }

  async setAction2LevelInput(action2Level) {
    await this.action2LevelInput.sendKeys(action2Level);
  }

  async getAction2LevelInput() {
    return await this.action2LevelInput.getAttribute('value');
  }

  async setAction3IdInput(action3Id) {
    await this.action3IdInput.sendKeys(action3Id);
  }

  async getAction3IdInput() {
    return await this.action3IdInput.getAttribute('value');
  }

  async setAction3LevelInput(action3Level) {
    await this.action3LevelInput.sendKeys(action3Level);
  }

  async getAction3LevelInput() {
    return await this.action3LevelInput.getAttribute('value');
  }

  async setAction4IdInput(action4Id) {
    await this.action4IdInput.sendKeys(action4Id);
  }

  async getAction4IdInput() {
    return await this.action4IdInput.getAttribute('value');
  }

  async setAction4LevelInput(action4Level) {
    await this.action4LevelInput.sendKeys(action4Level);
  }

  async getAction4LevelInput() {
    return await this.action4LevelInput.getAttribute('value');
  }

  async setAction5IdInput(action5Id) {
    await this.action5IdInput.sendKeys(action5Id);
  }

  async getAction5IdInput() {
    return await this.action5IdInput.getAttribute('value');
  }

  async setAction5LevelInput(action5Level) {
    await this.action5LevelInput.sendKeys(action5Level);
  }

  async getAction5LevelInput() {
    return await this.action5LevelInput.getAttribute('value');
  }

  async setStaminaInput(stamina) {
    await this.staminaInput.sendKeys(stamina);
  }

  async getStaminaInput() {
    return await this.staminaInput.getAttribute('value');
  }

  async setDribbleInput(dribble) {
    await this.dribbleInput.sendKeys(dribble);
  }

  async getDribbleInput() {
    return await this.dribbleInput.getAttribute('value');
  }

  async setShootInput(shoot) {
    await this.shootInput.sendKeys(shoot);
  }

  async getShootInput() {
    return await this.shootInput.getAttribute('value');
  }

  async setBallPassInput(ballPass) {
    await this.ballPassInput.sendKeys(ballPass);
  }

  async getBallPassInput() {
    return await this.ballPassInput.getAttribute('value');
  }

  async setTackleInput(tackle) {
    await this.tackleInput.sendKeys(tackle);
  }

  async getTackleInput() {
    return await this.tackleInput.getAttribute('value');
  }

  async setBlockInput(block) {
    await this.blockInput.sendKeys(block);
  }

  async getBlockInput() {
    return await this.blockInput.getAttribute('value');
  }

  async setInterceptInput(intercept) {
    await this.interceptInput.sendKeys(intercept);
  }

  async getInterceptInput() {
    return await this.interceptInput.getAttribute('value');
  }

  async setSpeedInput(speed) {
    await this.speedInput.sendKeys(speed);
  }

  async getSpeedInput() {
    return await this.speedInput.getAttribute('value');
  }

  async setPowerInput(power) {
    await this.powerInput.sendKeys(power);
  }

  async getPowerInput() {
    return await this.powerInput.getAttribute('value');
  }

  async setTechniqueInput(technique) {
    await this.techniqueInput.sendKeys(technique);
  }

  async getTechniqueInput() {
    return await this.techniqueInput.getAttribute('value');
  }

  async setPunchingInput(punching) {
    await this.punchingInput.sendKeys(punching);
  }

  async getPunchingInput() {
    return await this.punchingInput.getAttribute('value');
  }

  async setCatchingInput(catching) {
    await this.catchingInput.sendKeys(catching);
  }

  async getCatchingInput() {
    return await this.catchingInput.getAttribute('value');
  }

  async setHighBallBonusInput(highBallBonus) {
    await this.highBallBonusInput.sendKeys(highBallBonus);
  }

  async getHighBallBonusInput() {
    return await this.highBallBonusInput.getAttribute('value');
  }

  async setLowBallBonusInput(lowBallBonus) {
    await this.lowBallBonusInput.sendKeys(lowBallBonus);
  }

  async getLowBallBonusInput() {
    return await this.lowBallBonusInput.getAttribute('value');
  }

  async setPersonalityIdInput(personalityId) {
    await this.personalityIdInput.sendKeys(personalityId);
  }

  async getPersonalityIdInput() {
    return await this.personalityIdInput.getAttribute('value');
  }

  async setUniformNoInput(uniformNo) {
    await this.uniformNoInput.sendKeys(uniformNo);
  }

  async getUniformNoInput() {
    return await this.uniformNoInput.getAttribute('value');
  }

  async setLevelGroupIdInput(levelGroupId) {
    await this.levelGroupIdInput.sendKeys(levelGroupId);
  }

  async getLevelGroupIdInput() {
    return await this.levelGroupIdInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MNpcCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mNpcCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mNpcCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
