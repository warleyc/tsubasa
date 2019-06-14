import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildChatDefaultStampComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-chat-default-stamp div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-chat-default-stamp div h2#page-heading span')).first();

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

export class MGuildChatDefaultStampUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-chat-default-stamp-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  masterIdInput = element(by.id('field_masterId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMasterIdInput(masterId) {
    await this.masterIdInput.sendKeys(masterId);
  }

  async getMasterIdInput() {
    return await this.masterIdInput.getAttribute('value');
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

export class MGuildChatDefaultStampDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildChatDefaultStamp-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildChatDefaultStamp'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
