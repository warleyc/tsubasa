import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonAchievementRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-achievement-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-achievement-reward div h2#page-heading span')).first();

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

export class MMarathonAchievementRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-achievement-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventIdInput = element(by.id('field_eventId'));
  eventPointInput = element(by.id('field_eventPoint'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
  }

  async setEventPointInput(eventPoint) {
    await this.eventPointInput.sendKeys(eventPoint);
  }

  async getEventPointInput() {
    return await this.eventPointInput.getAttribute('value');
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

export class MMarathonAchievementRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonAchievementReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonAchievementReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
