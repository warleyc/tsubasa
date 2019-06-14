import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MModelUniformBottomComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-model-uniform-bottom div table .btn-danger'));
  title = element.all(by.css('jhi-m-model-uniform-bottom div h2#page-heading span')).first();

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

export class MModelUniformBottomUpdatePage {
  pageTitle = element(by.id('jhi-m-model-uniform-bottom-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  uniformBottomIdInput = element(by.id('field_uniformBottomId'));
  defaultDressingTypeInput = element(by.id('field_defaultDressingType'));
  uniformModelInput = element(by.id('field_uniformModel'));
  uniformTextureInput = element(by.id('field_uniformTexture'));
  uniformNoTextureInput = element(by.id('field_uniformNoTexture'));
  defaultColorInput = element(by.id('field_defaultColor'));
  uniformNoColorInput = element(by.id('field_uniformNoColor'));
  numberRightLegInput = element(by.id('field_numberRightLeg'));
  numberLeftLegInput = element(by.id('field_numberLeftLeg'));
  uniformNoPositionXInput = element(by.id('field_uniformNoPositionX'));
  uniformNoPositionYInput = element(by.id('field_uniformNoPositionY'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUniformBottomIdInput(uniformBottomId) {
    await this.uniformBottomIdInput.sendKeys(uniformBottomId);
  }

  async getUniformBottomIdInput() {
    return await this.uniformBottomIdInput.getAttribute('value');
  }

  async setDefaultDressingTypeInput(defaultDressingType) {
    await this.defaultDressingTypeInput.sendKeys(defaultDressingType);
  }

  async getDefaultDressingTypeInput() {
    return await this.defaultDressingTypeInput.getAttribute('value');
  }

  async setUniformModelInput(uniformModel) {
    await this.uniformModelInput.sendKeys(uniformModel);
  }

  async getUniformModelInput() {
    return await this.uniformModelInput.getAttribute('value');
  }

  async setUniformTextureInput(uniformTexture) {
    await this.uniformTextureInput.sendKeys(uniformTexture);
  }

  async getUniformTextureInput() {
    return await this.uniformTextureInput.getAttribute('value');
  }

  async setUniformNoTextureInput(uniformNoTexture) {
    await this.uniformNoTextureInput.sendKeys(uniformNoTexture);
  }

  async getUniformNoTextureInput() {
    return await this.uniformNoTextureInput.getAttribute('value');
  }

  async setDefaultColorInput(defaultColor) {
    await this.defaultColorInput.sendKeys(defaultColor);
  }

  async getDefaultColorInput() {
    return await this.defaultColorInput.getAttribute('value');
  }

  async setUniformNoColorInput(uniformNoColor) {
    await this.uniformNoColorInput.sendKeys(uniformNoColor);
  }

  async getUniformNoColorInput() {
    return await this.uniformNoColorInput.getAttribute('value');
  }

  async setNumberRightLegInput(numberRightLeg) {
    await this.numberRightLegInput.sendKeys(numberRightLeg);
  }

  async getNumberRightLegInput() {
    return await this.numberRightLegInput.getAttribute('value');
  }

  async setNumberLeftLegInput(numberLeftLeg) {
    await this.numberLeftLegInput.sendKeys(numberLeftLeg);
  }

  async getNumberLeftLegInput() {
    return await this.numberLeftLegInput.getAttribute('value');
  }

  async setUniformNoPositionXInput(uniformNoPositionX) {
    await this.uniformNoPositionXInput.sendKeys(uniformNoPositionX);
  }

  async getUniformNoPositionXInput() {
    return await this.uniformNoPositionXInput.getAttribute('value');
  }

  async setUniformNoPositionYInput(uniformNoPositionY) {
    await this.uniformNoPositionYInput.sendKeys(uniformNoPositionY);
  }

  async getUniformNoPositionYInput() {
    return await this.uniformNoPositionYInput.getAttribute('value');
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

export class MModelUniformBottomDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mModelUniformBottom-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mModelUniformBottom'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
