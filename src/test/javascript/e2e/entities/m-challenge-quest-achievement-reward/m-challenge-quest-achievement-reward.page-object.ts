import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MChallengeQuestAchievementRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-challenge-quest-achievement-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-challenge-quest-achievement-reward div h2#page-heading span')).first();

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

export class MChallengeQuestAchievementRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-challenge-quest-achievement-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  worldIdInput = element(by.id('field_worldId'));
  stageIdInput = element(by.id('field_stageId'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWorldIdInput(worldId) {
    await this.worldIdInput.sendKeys(worldId);
  }

  async getWorldIdInput() {
    return await this.worldIdInput.getAttribute('value');
  }

  async setStageIdInput(stageId) {
    await this.stageIdInput.sendKeys(stageId);
  }

  async getStageIdInput() {
    return await this.stageIdInput.getAttribute('value');
  }

  async setRewardGroupIdInput(rewardGroupId) {
    await this.rewardGroupIdInput.sendKeys(rewardGroupId);
  }

  async getRewardGroupIdInput() {
    return await this.rewardGroupIdInput.getAttribute('value');
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

export class MChallengeQuestAchievementRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mChallengeQuestAchievementReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mChallengeQuestAchievementReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
