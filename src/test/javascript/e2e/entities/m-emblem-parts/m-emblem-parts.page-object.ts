import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEmblemPartsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-emblem-parts div table .btn-danger'));
  title = element.all(by.css('jhi-m-emblem-parts div h2#page-heading span')).first();

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

export class MEmblemPartsUpdatePage {
  pageTitle = element(by.id('jhi-m-emblem-parts-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  assetNameInput = element(by.id('field_assetName'));
  partsTypeInput = element(by.id('field_partsType'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
  }

  async setPartsTypeInput(partsType) {
    await this.partsTypeInput.sendKeys(partsType);
  }

  async getPartsTypeInput() {
    return await this.partsTypeInput.getAttribute('value');
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

export class MEmblemPartsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEmblemParts-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEmblemParts'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
