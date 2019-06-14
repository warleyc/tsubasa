import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionTrajectoryCutInComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-trajectory-cut-in div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-trajectory-cut-in div h2#page-heading span')).first();

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

export class MGachaRenditionTrajectoryCutInUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-trajectory-cut-in-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  trajectoryTypeInput = element(by.id('field_trajectoryType'));
  spriteNameInput = element(by.id('field_spriteName'));
  weightInput = element(by.id('field_weight'));
  voiceInput = element(by.id('field_voice'));
  exceptKickerIdInput = element(by.id('field_exceptKickerId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
  }

  async setTrajectoryTypeInput(trajectoryType) {
    await this.trajectoryTypeInput.sendKeys(trajectoryType);
  }

  async getTrajectoryTypeInput() {
    return await this.trajectoryTypeInput.getAttribute('value');
  }

  async setSpriteNameInput(spriteName) {
    await this.spriteNameInput.sendKeys(spriteName);
  }

  async getSpriteNameInput() {
    return await this.spriteNameInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setVoiceInput(voice) {
    await this.voiceInput.sendKeys(voice);
  }

  async getVoiceInput() {
    return await this.voiceInput.getAttribute('value');
  }

  async setExceptKickerIdInput(exceptKickerId) {
    await this.exceptKickerIdInput.sendKeys(exceptKickerId);
  }

  async getExceptKickerIdInput() {
    return await this.exceptKickerIdInput.getAttribute('value');
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

export class MGachaRenditionTrajectoryCutInDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionTrajectoryCutIn-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionTrajectoryCutIn'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
