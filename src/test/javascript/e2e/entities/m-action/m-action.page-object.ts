import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MActionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-action div table .btn-danger'));
  title = element.all(by.css('jhi-m-action div h2#page-heading span')).first();

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

export class MActionUpdatePage {
  pageTitle = element(by.id('jhi-m-action-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  nameRubyInput = element(by.id('field_nameRuby'));
  descriptionInput = element(by.id('field_description'));
  effectDescriptionInput = element(by.id('field_effectDescription'));
  initialCommandInput = element(by.id('field_initialCommand'));
  rarityInput = element(by.id('field_rarity'));
  commandTypeInput = element(by.id('field_commandType'));
  ballConditionGroundInput = element(by.id('field_ballConditionGround'));
  ballConditionLowInput = element(by.id('field_ballConditionLow'));
  ballConditionHighInput = element(by.id('field_ballConditionHigh'));
  staminaLvMinInput = element(by.id('field_staminaLvMin'));
  staminaLvMaxInput = element(by.id('field_staminaLvMax'));
  powerLvMinInput = element(by.id('field_powerLvMin'));
  powerLvMaxInput = element(by.id('field_powerLvMax'));
  blowOffCountInput = element(by.id('field_blowOffCount'));
  mShootIdInput = element(by.id('field_mShootId'));
  foulRateInput = element(by.id('field_foulRate'));
  distanceDecayTypeInput = element(by.id('field_distanceDecayType'));
  activateCharacterCountInput = element(by.id('field_activateCharacterCount'));
  actionCutIdInput = element(by.id('field_actionCutId'));
  powerupGroupInput = element(by.id('field_powerupGroup'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setNameRubyInput(nameRuby) {
    await this.nameRubyInput.sendKeys(nameRuby);
  }

  async getNameRubyInput() {
    return await this.nameRubyInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setEffectDescriptionInput(effectDescription) {
    await this.effectDescriptionInput.sendKeys(effectDescription);
  }

  async getEffectDescriptionInput() {
    return await this.effectDescriptionInput.getAttribute('value');
  }

  async setInitialCommandInput(initialCommand) {
    await this.initialCommandInput.sendKeys(initialCommand);
  }

  async getInitialCommandInput() {
    return await this.initialCommandInput.getAttribute('value');
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setCommandTypeInput(commandType) {
    await this.commandTypeInput.sendKeys(commandType);
  }

  async getCommandTypeInput() {
    return await this.commandTypeInput.getAttribute('value');
  }

  async setBallConditionGroundInput(ballConditionGround) {
    await this.ballConditionGroundInput.sendKeys(ballConditionGround);
  }

  async getBallConditionGroundInput() {
    return await this.ballConditionGroundInput.getAttribute('value');
  }

  async setBallConditionLowInput(ballConditionLow) {
    await this.ballConditionLowInput.sendKeys(ballConditionLow);
  }

  async getBallConditionLowInput() {
    return await this.ballConditionLowInput.getAttribute('value');
  }

  async setBallConditionHighInput(ballConditionHigh) {
    await this.ballConditionHighInput.sendKeys(ballConditionHigh);
  }

  async getBallConditionHighInput() {
    return await this.ballConditionHighInput.getAttribute('value');
  }

  async setStaminaLvMinInput(staminaLvMin) {
    await this.staminaLvMinInput.sendKeys(staminaLvMin);
  }

  async getStaminaLvMinInput() {
    return await this.staminaLvMinInput.getAttribute('value');
  }

  async setStaminaLvMaxInput(staminaLvMax) {
    await this.staminaLvMaxInput.sendKeys(staminaLvMax);
  }

  async getStaminaLvMaxInput() {
    return await this.staminaLvMaxInput.getAttribute('value');
  }

  async setPowerLvMinInput(powerLvMin) {
    await this.powerLvMinInput.sendKeys(powerLvMin);
  }

  async getPowerLvMinInput() {
    return await this.powerLvMinInput.getAttribute('value');
  }

  async setPowerLvMaxInput(powerLvMax) {
    await this.powerLvMaxInput.sendKeys(powerLvMax);
  }

  async getPowerLvMaxInput() {
    return await this.powerLvMaxInput.getAttribute('value');
  }

  async setBlowOffCountInput(blowOffCount) {
    await this.blowOffCountInput.sendKeys(blowOffCount);
  }

  async getBlowOffCountInput() {
    return await this.blowOffCountInput.getAttribute('value');
  }

  async setMShootIdInput(mShootId) {
    await this.mShootIdInput.sendKeys(mShootId);
  }

  async getMShootIdInput() {
    return await this.mShootIdInput.getAttribute('value');
  }

  async setFoulRateInput(foulRate) {
    await this.foulRateInput.sendKeys(foulRate);
  }

  async getFoulRateInput() {
    return await this.foulRateInput.getAttribute('value');
  }

  async setDistanceDecayTypeInput(distanceDecayType) {
    await this.distanceDecayTypeInput.sendKeys(distanceDecayType);
  }

  async getDistanceDecayTypeInput() {
    return await this.distanceDecayTypeInput.getAttribute('value');
  }

  async setActivateCharacterCountInput(activateCharacterCount) {
    await this.activateCharacterCountInput.sendKeys(activateCharacterCount);
  }

  async getActivateCharacterCountInput() {
    return await this.activateCharacterCountInput.getAttribute('value');
  }

  async setActionCutIdInput(actionCutId) {
    await this.actionCutIdInput.sendKeys(actionCutId);
  }

  async getActionCutIdInput() {
    return await this.actionCutIdInput.getAttribute('value');
  }

  async setPowerupGroupInput(powerupGroup) {
    await this.powerupGroupInput.sendKeys(powerupGroup);
  }

  async getPowerupGroupInput() {
    return await this.powerupGroupInput.getAttribute('value');
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

export class MActionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAction-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAction'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
