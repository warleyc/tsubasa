import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MChatSystemMessageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-chat-system-message div table .btn-danger'));
  title = element.all(by.css('jhi-m-chat-system-message div h2#page-heading span')).first();

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

export class MChatSystemMessageUpdatePage {
  pageTitle = element(by.id('jhi-m-chat-system-message-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  messageTypeInput = element(by.id('field_messageType'));
  messageKeyInput = element(by.id('field_messageKey'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMessageTypeInput(messageType) {
    await this.messageTypeInput.sendKeys(messageType);
  }

  async getMessageTypeInput() {
    return await this.messageTypeInput.getAttribute('value');
  }

  async setMessageKeyInput(messageKey) {
    await this.messageKeyInput.sendKeys(messageKey);
  }

  async getMessageKeyInput() {
    return await this.messageKeyInput.getAttribute('value');
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

export class MChatSystemMessageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mChatSystemMessage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mChatSystemMessage'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
