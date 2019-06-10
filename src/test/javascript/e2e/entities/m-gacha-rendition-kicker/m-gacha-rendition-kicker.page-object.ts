import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionKickerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-kicker div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-kicker div h2#page-heading span')).first();

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

export class MGachaRenditionKickerUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-kicker-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  isManySsrInput = element(by.id('field_isManySsr'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  leftModelIdInput = element(by.id('field_leftModelId'));
  leftUniformUpIdInput = element(by.id('field_leftUniformUpId'));
  leftUniformBottomIdInput = element(by.id('field_leftUniformBottomId'));
  leftUniformNumberInput = element(by.id('field_leftUniformNumber'));
  rightModelIdInput = element(by.id('field_rightModelId'));
  rightUniformUpIdInput = element(by.id('field_rightUniformUpId'));
  rightUniformBottomIdInput = element(by.id('field_rightUniformBottomId'));
  rightUniformNumberInput = element(by.id('field_rightUniformNumber'));
  cutInSpriteNameInput = element(by.id('field_cutInSpriteName'));
  leftMessageInput = element(by.id('field_leftMessage'));
  rightMessageInput = element(by.id('field_rightMessage'));
  voiceTriggerInput = element(by.id('field_voiceTrigger'));
  voiceStartCutInInput = element(by.id('field_voiceStartCutIn'));
  kickerIdInput = element(by.id('field_kickerId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
  }

  async setIsManySsrInput(isManySsr) {
    await this.isManySsrInput.sendKeys(isManySsr);
  }

  async getIsManySsrInput() {
    return await this.isManySsrInput.getAttribute('value');
  }

  async setIsSsrInput(isSsr) {
    await this.isSsrInput.sendKeys(isSsr);
  }

  async getIsSsrInput() {
    return await this.isSsrInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setLeftModelIdInput(leftModelId) {
    await this.leftModelIdInput.sendKeys(leftModelId);
  }

  async getLeftModelIdInput() {
    return await this.leftModelIdInput.getAttribute('value');
  }

  async setLeftUniformUpIdInput(leftUniformUpId) {
    await this.leftUniformUpIdInput.sendKeys(leftUniformUpId);
  }

  async getLeftUniformUpIdInput() {
    return await this.leftUniformUpIdInput.getAttribute('value');
  }

  async setLeftUniformBottomIdInput(leftUniformBottomId) {
    await this.leftUniformBottomIdInput.sendKeys(leftUniformBottomId);
  }

  async getLeftUniformBottomIdInput() {
    return await this.leftUniformBottomIdInput.getAttribute('value');
  }

  async setLeftUniformNumberInput(leftUniformNumber) {
    await this.leftUniformNumberInput.sendKeys(leftUniformNumber);
  }

  async getLeftUniformNumberInput() {
    return await this.leftUniformNumberInput.getAttribute('value');
  }

  async setRightModelIdInput(rightModelId) {
    await this.rightModelIdInput.sendKeys(rightModelId);
  }

  async getRightModelIdInput() {
    return await this.rightModelIdInput.getAttribute('value');
  }

  async setRightUniformUpIdInput(rightUniformUpId) {
    await this.rightUniformUpIdInput.sendKeys(rightUniformUpId);
  }

  async getRightUniformUpIdInput() {
    return await this.rightUniformUpIdInput.getAttribute('value');
  }

  async setRightUniformBottomIdInput(rightUniformBottomId) {
    await this.rightUniformBottomIdInput.sendKeys(rightUniformBottomId);
  }

  async getRightUniformBottomIdInput() {
    return await this.rightUniformBottomIdInput.getAttribute('value');
  }

  async setRightUniformNumberInput(rightUniformNumber) {
    await this.rightUniformNumberInput.sendKeys(rightUniformNumber);
  }

  async getRightUniformNumberInput() {
    return await this.rightUniformNumberInput.getAttribute('value');
  }

  async setCutInSpriteNameInput(cutInSpriteName) {
    await this.cutInSpriteNameInput.sendKeys(cutInSpriteName);
  }

  async getCutInSpriteNameInput() {
    return await this.cutInSpriteNameInput.getAttribute('value');
  }

  async setLeftMessageInput(leftMessage) {
    await this.leftMessageInput.sendKeys(leftMessage);
  }

  async getLeftMessageInput() {
    return await this.leftMessageInput.getAttribute('value');
  }

  async setRightMessageInput(rightMessage) {
    await this.rightMessageInput.sendKeys(rightMessage);
  }

  async getRightMessageInput() {
    return await this.rightMessageInput.getAttribute('value');
  }

  async setVoiceTriggerInput(voiceTrigger) {
    await this.voiceTriggerInput.sendKeys(voiceTrigger);
  }

  async getVoiceTriggerInput() {
    return await this.voiceTriggerInput.getAttribute('value');
  }

  async setVoiceStartCutInInput(voiceStartCutIn) {
    await this.voiceStartCutInInput.sendKeys(voiceStartCutIn);
  }

  async getVoiceStartCutInInput() {
    return await this.voiceStartCutInInput.getAttribute('value');
  }

  async setKickerIdInput(kickerId) {
    await this.kickerIdInput.sendKeys(kickerId);
  }

  async getKickerIdInput() {
    return await this.kickerIdInput.getAttribute('value');
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

export class MGachaRenditionKickerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionKicker-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionKicker'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
