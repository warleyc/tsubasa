import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MActionSkillCutinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-action-skill-cutin div table .btn-danger'));
  title = element.all(by.css('jhi-m-action-skill-cutin div h2#page-heading span')).first();

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

export class MActionSkillCutinUpdatePage {
  pageTitle = element(by.id('jhi-m-action-skill-cutin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  actionCutIdInput = element(by.id('field_actionCutId'));
  characterIdInput = element(by.id('field_characterId'));
  cutNameInput = element(by.id('field_cutName'));
  typeInput = element(by.id('field_type'));
  startSynchronizeInput = element(by.id('field_startSynchronize'));
  finishSynchronizeInput = element(by.id('field_finishSynchronize'));
  startDelayInput = element(by.id('field_startDelay'));
  chapter1CharacterInput = element(by.id('field_chapter1Character'));
  chapter1TextInput = element(by.id('field_chapter1Text'));
  chapter1SoundEventInput = element(by.id('field_chapter1SoundEvent'));
  chapter2CharacterInput = element(by.id('field_chapter2Character'));
  chapter2TextInput = element(by.id('field_chapter2Text'));
  chapter2SoundEventInput = element(by.id('field_chapter2SoundEvent'));
  chapter3CharacterInput = element(by.id('field_chapter3Character'));
  chapter3TextInput = element(by.id('field_chapter3Text'));
  chapter3SoundEventInput = element(by.id('field_chapter3SoundEvent'));
  chapter4CharacterInput = element(by.id('field_chapter4Character'));
  chapter4TextInput = element(by.id('field_chapter4Text'));
  chapter4SoundEventInput = element(by.id('field_chapter4SoundEvent'));
  chapter5CharacterInput = element(by.id('field_chapter5Character'));
  chapter5TextInput = element(by.id('field_chapter5Text'));
  chapter5SoundEventInput = element(by.id('field_chapter5SoundEvent'));
  synchronizeTextInput = element(by.id('field_synchronizeText'));
  synchronizeSoundEventInput = element(by.id('field_synchronizeSoundEvent'));

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

  async setCutNameInput(cutName) {
    await this.cutNameInput.sendKeys(cutName);
  }

  async getCutNameInput() {
    return await this.cutNameInput.getAttribute('value');
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return await this.typeInput.getAttribute('value');
  }

  async setStartSynchronizeInput(startSynchronize) {
    await this.startSynchronizeInput.sendKeys(startSynchronize);
  }

  async getStartSynchronizeInput() {
    return await this.startSynchronizeInput.getAttribute('value');
  }

  async setFinishSynchronizeInput(finishSynchronize) {
    await this.finishSynchronizeInput.sendKeys(finishSynchronize);
  }

  async getFinishSynchronizeInput() {
    return await this.finishSynchronizeInput.getAttribute('value');
  }

  async setStartDelayInput(startDelay) {
    await this.startDelayInput.sendKeys(startDelay);
  }

  async getStartDelayInput() {
    return await this.startDelayInput.getAttribute('value');
  }

  async setChapter1CharacterInput(chapter1Character) {
    await this.chapter1CharacterInput.sendKeys(chapter1Character);
  }

  async getChapter1CharacterInput() {
    return await this.chapter1CharacterInput.getAttribute('value');
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

  async setChapter2CharacterInput(chapter2Character) {
    await this.chapter2CharacterInput.sendKeys(chapter2Character);
  }

  async getChapter2CharacterInput() {
    return await this.chapter2CharacterInput.getAttribute('value');
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

  async setChapter3CharacterInput(chapter3Character) {
    await this.chapter3CharacterInput.sendKeys(chapter3Character);
  }

  async getChapter3CharacterInput() {
    return await this.chapter3CharacterInput.getAttribute('value');
  }

  async setChapter3TextInput(chapter3Text) {
    await this.chapter3TextInput.sendKeys(chapter3Text);
  }

  async getChapter3TextInput() {
    return await this.chapter3TextInput.getAttribute('value');
  }

  async setChapter3SoundEventInput(chapter3SoundEvent) {
    await this.chapter3SoundEventInput.sendKeys(chapter3SoundEvent);
  }

  async getChapter3SoundEventInput() {
    return await this.chapter3SoundEventInput.getAttribute('value');
  }

  async setChapter4CharacterInput(chapter4Character) {
    await this.chapter4CharacterInput.sendKeys(chapter4Character);
  }

  async getChapter4CharacterInput() {
    return await this.chapter4CharacterInput.getAttribute('value');
  }

  async setChapter4TextInput(chapter4Text) {
    await this.chapter4TextInput.sendKeys(chapter4Text);
  }

  async getChapter4TextInput() {
    return await this.chapter4TextInput.getAttribute('value');
  }

  async setChapter4SoundEventInput(chapter4SoundEvent) {
    await this.chapter4SoundEventInput.sendKeys(chapter4SoundEvent);
  }

  async getChapter4SoundEventInput() {
    return await this.chapter4SoundEventInput.getAttribute('value');
  }

  async setChapter5CharacterInput(chapter5Character) {
    await this.chapter5CharacterInput.sendKeys(chapter5Character);
  }

  async getChapter5CharacterInput() {
    return await this.chapter5CharacterInput.getAttribute('value');
  }

  async setChapter5TextInput(chapter5Text) {
    await this.chapter5TextInput.sendKeys(chapter5Text);
  }

  async getChapter5TextInput() {
    return await this.chapter5TextInput.getAttribute('value');
  }

  async setChapter5SoundEventInput(chapter5SoundEvent) {
    await this.chapter5SoundEventInput.sendKeys(chapter5SoundEvent);
  }

  async getChapter5SoundEventInput() {
    return await this.chapter5SoundEventInput.getAttribute('value');
  }

  async setSynchronizeTextInput(synchronizeText) {
    await this.synchronizeTextInput.sendKeys(synchronizeText);
  }

  async getSynchronizeTextInput() {
    return await this.synchronizeTextInput.getAttribute('value');
  }

  async setSynchronizeSoundEventInput(synchronizeSoundEvent) {
    await this.synchronizeSoundEventInput.sendKeys(synchronizeSoundEvent);
  }

  async getSynchronizeSoundEventInput() {
    return await this.synchronizeSoundEventInput.getAttribute('value');
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

export class MActionSkillCutinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mActionSkillCutin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mActionSkillCutin'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
