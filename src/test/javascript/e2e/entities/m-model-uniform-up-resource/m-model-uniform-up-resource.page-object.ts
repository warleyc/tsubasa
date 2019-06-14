import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MModelUniformUpResourceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-model-uniform-up-resource div table .btn-danger'));
  title = element.all(by.css('jhi-m-model-uniform-up-resource div h2#page-heading span')).first();

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

export class MModelUniformUpResourceUpdatePage {
  pageTitle = element(by.id('jhi-m-model-uniform-up-resource-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  uniformTypeIdInput = element(by.id('field_uniformTypeId'));
  dressingTypeInput = element(by.id('field_dressingType'));
  widthInput = element(by.id('field_width'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUniformTypeIdInput(uniformTypeId) {
    await this.uniformTypeIdInput.sendKeys(uniformTypeId);
  }

  async getUniformTypeIdInput() {
    return await this.uniformTypeIdInput.getAttribute('value');
  }

  async setDressingTypeInput(dressingType) {
    await this.dressingTypeInput.sendKeys(dressingType);
  }

  async getDressingTypeInput() {
    return await this.dressingTypeInput.getAttribute('value');
  }

  async setWidthInput(width) {
    await this.widthInput.sendKeys(width);
  }

  async getWidthInput() {
    return await this.widthInput.getAttribute('value');
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

export class MModelUniformUpResourceDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mModelUniformUpResource-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mModelUniformUpResource'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
