import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCharacterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-character div table .btn-danger'));
  title = element.all(by.css('jhi-m-character div h2#page-heading span')).first();

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

export class MCharacterUpdatePage {
  pageTitle = element(by.id('jhi-m-character-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  announceNameInput = element(by.id('field_announceName'));
  shortNameInput = element(by.id('field_shortName'));
  characterBookPriorityInput = element(by.id('field_characterBookPriority'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setAnnounceNameInput(announceName) {
    await this.announceNameInput.sendKeys(announceName);
  }

  async getAnnounceNameInput() {
    return await this.announceNameInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setCharacterBookPriorityInput(characterBookPriority) {
    await this.characterBookPriorityInput.sendKeys(characterBookPriority);
  }

  async getCharacterBookPriorityInput() {
    return await this.characterBookPriorityInput.getAttribute('value');
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

export class MCharacterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCharacter-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCharacter'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
