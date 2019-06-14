import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestDropRateCampaignContentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-drop-rate-campaign-content div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-drop-rate-campaign-content div h2#page-heading span')).first();

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

export class MQuestDropRateCampaignContentUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-drop-rate-campaign-content-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  contentTypeInput = element(by.id('field_contentType'));
  contentIdInput = element(by.id('field_contentId'));
  rateInput = element(by.id('field_rate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setContentTypeInput(contentType) {
    await this.contentTypeInput.sendKeys(contentType);
  }

  async getContentTypeInput() {
    return await this.contentTypeInput.getAttribute('value');
  }

  async setContentIdInput(contentId) {
    await this.contentIdInput.sendKeys(contentId);
  }

  async getContentIdInput() {
    return await this.contentIdInput.getAttribute('value');
  }

  async setRateInput(rate) {
    await this.rateInput.sendKeys(rate);
  }

  async getRateInput() {
    return await this.rateInput.getAttribute('value');
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

export class MQuestDropRateCampaignContentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestDropRateCampaignContent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestDropRateCampaignContent'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
