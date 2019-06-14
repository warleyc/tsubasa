import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildNegativeWordComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-negative-word div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-negative-word div h2#page-heading span')).first();

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

export class MGuildNegativeWordUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-negative-word-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  wordInput = element(by.id('field_word'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWordInput(word) {
    await this.wordInput.sendKeys(word);
  }

  async getWordInput() {
    return await this.wordInput.getAttribute('value');
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

export class MGuildNegativeWordDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildNegativeWord-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildNegativeWord'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
