import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMatchResultCutinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-match-result-cutin div table .btn-danger'));
  title = element.all(by.css('jhi-m-match-result-cutin div h2#page-heading span')).first();

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

export class MMatchResultCutinUpdatePage {
  pageTitle = element(by.id('jhi-m-match-result-cutin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  characterIdInput = element(by.id('field_characterId'));
  teamIdInput = element(by.id('field_teamId'));
  isWinInput = element(by.id('field_isWin'));
  textInput = element(by.id('field_text'));
  soundEventInput = element(by.id('field_soundEvent'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCharacterIdInput(characterId) {
    await this.characterIdInput.sendKeys(characterId);
  }

  async getCharacterIdInput() {
    return await this.characterIdInput.getAttribute('value');
  }

  async setTeamIdInput(teamId) {
    await this.teamIdInput.sendKeys(teamId);
  }

  async getTeamIdInput() {
    return await this.teamIdInput.getAttribute('value');
  }

  async setIsWinInput(isWin) {
    await this.isWinInput.sendKeys(isWin);
  }

  async getIsWinInput() {
    return await this.isWinInput.getAttribute('value');
  }

  async setTextInput(text) {
    await this.textInput.sendKeys(text);
  }

  async getTextInput() {
    return await this.textInput.getAttribute('value');
  }

  async setSoundEventInput(soundEvent) {
    await this.soundEventInput.sendKeys(soundEvent);
  }

  async getSoundEventInput() {
    return await this.soundEventInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MMatchResultCutinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMatchResultCutin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMatchResultCutin'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
