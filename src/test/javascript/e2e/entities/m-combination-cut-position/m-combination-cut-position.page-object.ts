import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCombinationCutPositionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-combination-cut-position div table .btn-danger'));
  title = element.all(by.css('jhi-m-combination-cut-position div h2#page-heading span')).first();

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

export class MCombinationCutPositionUpdatePage {
  pageTitle = element(by.id('jhi-m-combination-cut-position-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  actionCutIdInput = element(by.id('field_actionCutId'));
  characterIdInput = element(by.id('field_characterId'));
  activatorPositionInput = element(by.id('field_activatorPosition'));
  participantPosition1Input = element(by.id('field_participantPosition1'));
  participantPosition2Input = element(by.id('field_participantPosition2'));
  participantPosition3Input = element(by.id('field_participantPosition3'));
  participantPosition4Input = element(by.id('field_participantPosition4'));
  participantPosition5Input = element(by.id('field_participantPosition5'));
  mcharacterSelect = element(by.id('field_mcharacter'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setActionCutIdInput(actionCutId) {
    await this.actionCutIdInput.sendKeys(actionCutId);
  }

  async getActionCutIdInput() {
    return await this.actionCutIdInput.getAttribute('value');
  }

  async setCharacterIdInput(characterId) {
    await this.characterIdInput.sendKeys(characterId);
  }

  async getCharacterIdInput() {
    return await this.characterIdInput.getAttribute('value');
  }

  async setActivatorPositionInput(activatorPosition) {
    await this.activatorPositionInput.sendKeys(activatorPosition);
  }

  async getActivatorPositionInput() {
    return await this.activatorPositionInput.getAttribute('value');
  }

  async setParticipantPosition1Input(participantPosition1) {
    await this.participantPosition1Input.sendKeys(participantPosition1);
  }

  async getParticipantPosition1Input() {
    return await this.participantPosition1Input.getAttribute('value');
  }

  async setParticipantPosition2Input(participantPosition2) {
    await this.participantPosition2Input.sendKeys(participantPosition2);
  }

  async getParticipantPosition2Input() {
    return await this.participantPosition2Input.getAttribute('value');
  }

  async setParticipantPosition3Input(participantPosition3) {
    await this.participantPosition3Input.sendKeys(participantPosition3);
  }

  async getParticipantPosition3Input() {
    return await this.participantPosition3Input.getAttribute('value');
  }

  async setParticipantPosition4Input(participantPosition4) {
    await this.participantPosition4Input.sendKeys(participantPosition4);
  }

  async getParticipantPosition4Input() {
    return await this.participantPosition4Input.getAttribute('value');
  }

  async setParticipantPosition5Input(participantPosition5) {
    await this.participantPosition5Input.sendKeys(participantPosition5);
  }

  async getParticipantPosition5Input() {
    return await this.participantPosition5Input.getAttribute('value');
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

export class MCombinationCutPositionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCombinationCutPosition-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCombinationCutPosition'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
