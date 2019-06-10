import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDictionaryArComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-dictionary-ar div table .btn-danger'));
  title = element.all(by.css('jhi-m-dictionary-ar div h2#page-heading span')).first();

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

export class MDictionaryArUpdatePage {
  pageTitle = element(by.id('jhi-m-dictionary-ar-heading'));
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

export class MDictionaryArDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDictionaryAr-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDictionaryAr'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
