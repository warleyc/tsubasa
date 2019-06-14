import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMissionRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-mission-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-mission-reward div h2#page-heading span')).first();

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

export class MMissionRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-mission-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  contentTypeInput = element(by.id('field_contentType'));
  contentIdInput = element(by.id('field_contentId'));
  contentAmountInput = element(by.id('field_contentAmount'));

  async getPageTitle() {
    return this.pageTitle.getText();
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

  async setContentAmountInput(contentAmount) {
    await this.contentAmountInput.sendKeys(contentAmount);
  }

  async getContentAmountInput() {
    return await this.contentAmountInput.getAttribute('value');
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

export class MMissionRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMissionReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMissionReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
