import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetCharacterGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-character-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-character-group div h2#page-heading span')).first();

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

export class MTargetCharacterGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-character-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  characterIdInput = element(by.id('field_characterId'));
  mcharacterSelect = element(by.id('field_mcharacter'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setCharacterIdInput(characterId) {
    await this.characterIdInput.sendKeys(characterId);
  }

  async getCharacterIdInput() {
    return await this.characterIdInput.getAttribute('value');
  }

  async mcharacterSelectLastOption(timeout?: number) {
    await this.mcharacterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mcharacterSelectOption(option) {
    await this.mcharacterSelect.sendKeys(option);
  }

  getMcharacterSelect(): ElementFinder {
    return this.mcharacterSelect;
  }

  async getMcharacterSelectedOption() {
    return await this.mcharacterSelect.element(by.css('option:checked')).getText();
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

export class MTargetCharacterGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetCharacterGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetCharacterGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
