import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionBallComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-ball div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-ball div h2#page-heading span')).first();

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

export class MGachaRenditionBallUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-ball-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  ballTextureNameInput = element(by.id('field_ballTextureName'));
  trajectoryNormalTextureNameInput = element(by.id('field_trajectoryNormalTextureName'));
  trajectoryGoldTextureNameInput = element(by.id('field_trajectoryGoldTextureName'));
  trajectoryRainbowTextureNameInput = element(by.id('field_trajectoryRainbowTextureName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
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

  async setBallTextureNameInput(ballTextureName) {
    await this.ballTextureNameInput.sendKeys(ballTextureName);
  }

  async getBallTextureNameInput() {
    return await this.ballTextureNameInput.getAttribute('value');
  }

  async setTrajectoryNormalTextureNameInput(trajectoryNormalTextureName) {
    await this.trajectoryNormalTextureNameInput.sendKeys(trajectoryNormalTextureName);
  }

  async getTrajectoryNormalTextureNameInput() {
    return await this.trajectoryNormalTextureNameInput.getAttribute('value');
  }

  async setTrajectoryGoldTextureNameInput(trajectoryGoldTextureName) {
    await this.trajectoryGoldTextureNameInput.sendKeys(trajectoryGoldTextureName);
  }

  async getTrajectoryGoldTextureNameInput() {
    return await this.trajectoryGoldTextureNameInput.getAttribute('value');
  }

  async setTrajectoryRainbowTextureNameInput(trajectoryRainbowTextureName) {
    await this.trajectoryRainbowTextureNameInput.sendKeys(trajectoryRainbowTextureName);
  }

  async getTrajectoryRainbowTextureNameInput() {
    return await this.trajectoryRainbowTextureNameInput.getAttribute('value');
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

export class MGachaRenditionBallDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionBall-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionBall'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
