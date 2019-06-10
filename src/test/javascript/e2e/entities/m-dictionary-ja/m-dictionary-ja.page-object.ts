import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDictionaryJaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-dictionary-ja div table .btn-danger'));
  title = element.all(by.css('jhi-m-dictionary-ja div h2#page-heading span')).first();

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

export class MDictionaryJaUpdatePage {
  pageTitle = element(by.id('jhi-m-dictionary-ja-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  keyInput = element(by.id('field_key'));
  messageInput = element(by.id('field_message'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setKeyInput(key) {
    await this.keyInput.sendKeys(key);
  }

  async getKeyInput() {
    return await this.keyInput.getAttribute('value');
  }

  async setMessageInput(message) {
    await this.messageInput.sendKeys(message);
  }

  async getMessageInput() {
    return await this.messageInput.getAttribute('value');
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

export class MDictionaryJaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDictionaryJa-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDictionaryJa'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
