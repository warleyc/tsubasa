import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCheatCautionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-cheat-caution div table .btn-danger'));
  title = element.all(by.css('jhi-m-cheat-caution div h2#page-heading span')).first();

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

export class MCheatCautionUpdatePage {
  pageTitle = element(by.id('jhi-m-cheat-caution-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cautionInput = element(by.id('field_caution'));
  descriptionInput = element(by.id('field_description'));
  imageAssetNameInput = element(by.id('field_imageAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCautionInput(caution) {
    await this.cautionInput.sendKeys(caution);
  }

  async getCautionInput() {
    return await this.cautionInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setImageAssetNameInput(imageAssetName) {
    await this.imageAssetNameInput.sendKeys(imageAssetName);
  }

  async getImageAssetNameInput() {
    return await this.imageAssetNameInput.getAttribute('value');
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

export class MCheatCautionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCheatCaution-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCheatCaution'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
