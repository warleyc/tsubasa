import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionAfterInputCutInTextColorComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-after-input-cut-in-text-color div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-after-input-cut-in-text-color div h2#page-heading span')).first();

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

export class MGachaRenditionAfterInputCutInTextColorUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-after-input-cut-in-text-color-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  colorInput = element(by.id('field_color'));

  async getPageTitle() {
    return this.pageTitle.getText();
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

  async setColorInput(color) {
    await this.colorInput.sendKeys(color);
  }

  async getColorInput() {
    return await this.colorInput.getAttribute('value');
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

export class MGachaRenditionAfterInputCutInTextColorDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionAfterInputCutInTextColor-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionAfterInputCutInTextColor'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
