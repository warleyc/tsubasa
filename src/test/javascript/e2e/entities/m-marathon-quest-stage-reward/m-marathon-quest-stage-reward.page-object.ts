import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonQuestStageRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-quest-stage-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-quest-stage-reward div h2#page-heading span')).first();

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

export class MMarathonQuestStageRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-quest-stage-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  stageIdInput = element(by.id('field_stageId'));
  expInput = element(by.id('field_exp'));
  coinInput = element(by.id('field_coin'));
  guildPointInput = element(by.id('field_guildPoint'));
  clearRewardGroupIdInput = element(by.id('field_clearRewardGroupId'));
  clearRewardWeightIdInput = element(by.id('field_clearRewardWeightId'));
  achievementRewardGroupIdInput = element(by.id('field_achievementRewardGroupId'));
  coopGroupIdInput = element(by.id('field_coopGroupId'));
  specialRewardGroupIdInput = element(by.id('field_specialRewardGroupId'));
  specialRewardAmountInput = element(by.id('field_specialRewardAmount'));
  goalRewardGroupIdInput = element(by.id('field_goalRewardGroupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setStageIdInput(stageId) {
    await this.stageIdInput.sendKeys(stageId);
  }

  async getStageIdInput() {
    return await this.stageIdInput.getAttribute('value');
  }

  async setExpInput(exp) {
    await this.expInput.sendKeys(exp);
  }

  async getExpInput() {
    return await this.expInput.getAttribute('value');
  }

  async setCoinInput(coin) {
    await this.coinInput.sendKeys(coin);
  }

  async getCoinInput() {
    return await this.coinInput.getAttribute('value');
  }

  async setGuildPointInput(guildPoint) {
    await this.guildPointInput.sendKeys(guildPoint);
  }

  async getGuildPointInput() {
    return await this.guildPointInput.getAttribute('value');
  }

  async setClearRewardGroupIdInput(clearRewardGroupId) {
    await this.clearRewardGroupIdInput.sendKeys(clearRewardGroupId);
  }

  async getClearRewardGroupIdInput() {
    return await this.clearRewardGroupIdInput.getAttribute('value');
  }

  async setClearRewardWeightIdInput(clearRewardWeightId) {
    await this.clearRewardWeightIdInput.sendKeys(clearRewardWeightId);
  }

  async getClearRewardWeightIdInput() {
    return await this.clearRewardWeightIdInput.getAttribute('value');
  }

  async setAchievementRewardGroupIdInput(achievementRewardGroupId) {
    await this.achievementRewardGroupIdInput.sendKeys(achievementRewardGroupId);
  }

  async getAchievementRewardGroupIdInput() {
    return await this.achievementRewardGroupIdInput.getAttribute('value');
  }

  async setCoopGroupIdInput(coopGroupId) {
    await this.coopGroupIdInput.sendKeys(coopGroupId);
  }

  async getCoopGroupIdInput() {
    return await this.coopGroupIdInput.getAttribute('value');
  }

  async setSpecialRewardGroupIdInput(specialRewardGroupId) {
    await this.specialRewardGroupIdInput.sendKeys(specialRewardGroupId);
  }

  async getSpecialRewardGroupIdInput() {
    return await this.specialRewardGroupIdInput.getAttribute('value');
  }

  async setSpecialRewardAmountInput(specialRewardAmount) {
    await this.specialRewardAmountInput.sendKeys(specialRewardAmount);
  }

  async getSpecialRewardAmountInput() {
    return await this.specialRewardAmountInput.getAttribute('value');
  }

  async setGoalRewardGroupIdInput(goalRewardGroupId) {
    await this.goalRewardGroupIdInput.sendKeys(goalRewardGroupId);
  }

  async getGoalRewardGroupIdInput() {
    return await this.goalRewardGroupIdInput.getAttribute('value');
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

export class MMarathonQuestStageRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonQuestStageReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonQuestStageReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
