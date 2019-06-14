import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MModelUniformUpComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-model-uniform-up div table .btn-danger'));
  title = element.all(by.css('jhi-m-model-uniform-up div h2#page-heading span')).first();

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

export class MModelUniformUpUpdatePage {
  pageTitle = element(by.id('jhi-m-model-uniform-up-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  uniformUpIdInput = element(by.id('field_uniformUpId'));
  defaultDressingTypeInput = element(by.id('field_defaultDressingType'));
  uniformModelInput = element(by.id('field_uniformModel'));
  uniformTextureInput = element(by.id('field_uniformTexture'));
  uniformNoTextureInput = element(by.id('field_uniformNoTexture'));
  defaultColorInput = element(by.id('field_defaultColor'));
  uniformNoColorInput = element(by.id('field_uniformNoColor'));
  numberChestInput = element(by.id('field_numberChest'));
  numberBellyInput = element(by.id('field_numberBelly'));
  numberBackInput = element(by.id('field_numberBack'));
  uniformNoPositionXInput = element(by.id('field_uniformNoPositionX'));
  uniformNoPositionYInput = element(by.id('field_uniformNoPositionY'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUniformUpIdInput(uniformUpId) {
    await this.uniformUpIdInput.sendKeys(uniformUpId);
  }

  async getUniformUpIdInput() {
    return await this.uniformUpIdInput.getAttribute('value');
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

  async setNumberChestInput(numberChest) {
    await this.numberChestInput.sendKeys(numberChest);
  }

  async getNumberChestInput() {
    return await this.numberChestInput.getAttribute('value');
  }

  async setNumberBellyInput(numberBelly) {
    await this.numberBellyInput.sendKeys(numberBelly);
  }

  async getNumberBellyInput() {
    return await this.numberBellyInput.getAttribute('value');
  }

  async setNumberBackInput(numberBack) {
    await this.numberBackInput.sendKeys(numberBack);
  }

  async getNumberBackInput() {
    return await this.numberBackInput.getAttribute('value');
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

export class MModelUniformUpDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mModelUniformUp-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mModelUniformUp'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
