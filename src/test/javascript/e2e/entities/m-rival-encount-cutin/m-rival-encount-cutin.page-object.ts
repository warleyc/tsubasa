import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MRivalEncountCutinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-rival-encount-cutin div table .btn-danger'));
  title = element.all(by.css('jhi-m-rival-encount-cutin div h2#page-heading span')).first();

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

export class MRivalEncountCutinUpdatePage {
  pageTitle = element(by.id('jhi-m-rival-encount-cutin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  offenseCharacterIdInput = element(by.id('field_offenseCharacterId'));
  offenseTeamIdInput = element(by.id('field_offenseTeamId'));
  defenseCharacterIdInput = element(by.id('field_defenseCharacterId'));
  defenseTeamIdInput = element(by.id('field_defenseTeamId'));
  cutinTypeInput = element(by.id('field_cutinType'));
  chapter1TextInput = element(by.id('field_chapter1Text'));
  chapter1SoundEventInput = element(by.id('field_chapter1SoundEvent'));
  chapter2TextInput = element(by.id('field_chapter2Text'));
  chapter2SoundEventInput = element(by.id('field_chapter2SoundEvent'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setOffenseCharacterIdInput(offenseCharacterId) {
    await this.offenseCharacterIdInput.sendKeys(offenseCharacterId);
  }

  async getOffenseCharacterIdInput() {
    return await this.offenseCharacterIdInput.getAttribute('value');
  }

  async setOffenseTeamIdInput(offenseTeamId) {
    await this.offenseTeamIdInput.sendKeys(offenseTeamId);
  }

  async getOffenseTeamIdInput() {
    return await this.offenseTeamIdInput.getAttribute('value');
  }

  async setDefenseCharacterIdInput(defenseCharacterId) {
    await this.defenseCharacterIdInput.sendKeys(defenseCharacterId);
  }

  async getDefenseCharacterIdInput() {
    return await this.defenseCharacterIdInput.getAttribute('value');
  }

  async setDefenseTeamIdInput(defenseTeamId) {
    await this.defenseTeamIdInput.sendKeys(defenseTeamId);
  }

  async getDefenseTeamIdInput() {
    return await this.defenseTeamIdInput.getAttribute('value');
  }

  async setCutinTypeInput(cutinType) {
    await this.cutinTypeInput.sendKeys(cutinType);
  }

  async getCutinTypeInput() {
    return await this.cutinTypeInput.getAttribute('value');
  }

  async setChapter1TextInput(chapter1Text) {
    await this.chapter1TextInput.sendKeys(chapter1Text);
  }

  async getChapter1TextInput() {
    return await this.chapter1TextInput.getAttribute('value');
  }

  async setChapter1SoundEventInput(chapter1SoundEvent) {
    await this.chapter1SoundEventInput.sendKeys(chapter1SoundEvent);
  }

  async getChapter1SoundEventInput() {
    return await this.chapter1SoundEventInput.getAttribute('value');
  }

  async setChapter2TextInput(chapter2Text) {
    await this.chapter2TextInput.sendKeys(chapter2Text);
  }

  async getChapter2TextInput() {
    return await this.chapter2TextInput.getAttribute('value');
  }

  async setChapter2SoundEventInput(chapter2SoundEvent) {
    await this.chapter2SoundEventInput.sendKeys(chapter2SoundEvent);
  }

  async getChapter2SoundEventInput() {
    return await this.chapter2SoundEventInput.getAttribute('value');
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

export class MRivalEncountCutinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mRivalEncountCutin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mRivalEncountCutin'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
