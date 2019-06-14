import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSoundBankEventComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-sound-bank-event div table .btn-danger'));
  title = element.all(by.css('jhi-m-sound-bank-event div h2#page-heading span')).first();

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

export class MSoundBankEventUpdatePage {
  pageTitle = element(by.id('jhi-m-sound-bank-event-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  pathInput = element(by.id('field_path'));
  nameInput = element(by.id('field_name'));
  eventIdInput = element(by.id('field_eventId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return await this.pathInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
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

export class MSoundBankEventDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSoundBankEvent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSoundBankEvent'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
