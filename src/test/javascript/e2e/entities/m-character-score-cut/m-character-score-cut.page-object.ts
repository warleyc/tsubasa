import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCharacterScoreCutComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-character-score-cut div table .btn-danger'));
  title = element.all(by.css('jhi-m-character-score-cut div h2#page-heading span')).first();

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

export class MCharacterScoreCutUpdatePage {
  pageTitle = element(by.id('jhi-m-character-score-cut-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  characterIdInput = element(by.id('field_characterId'));
  teamIdInput = element(by.id('field_teamId'));
  scoreCutTypeInput = element(by.id('field_scoreCutType'));

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

  async setScoreCutTypeInput(scoreCutType) {
    await this.scoreCutTypeInput.sendKeys(scoreCutType);
  }

  async getScoreCutTypeInput() {
    return await this.scoreCutTypeInput.getAttribute('value');
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

export class MCharacterScoreCutDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCharacterScoreCut-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCharacterScoreCut'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
