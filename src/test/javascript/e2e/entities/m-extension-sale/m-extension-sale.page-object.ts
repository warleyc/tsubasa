import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MExtensionSaleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-extension-sale div table .btn-danger'));
  title = element.all(by.css('jhi-m-extension-sale div h2#page-heading span')).first();

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

export class MExtensionSaleUpdatePage {
  pageTitle = element(by.id('jhi-m-extension-sale-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  menuMessageInput = element(by.id('field_menuMessage'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  typeInput = element(by.id('field_type'));
  addExtensionInput = element(by.id('field_addExtension'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMenuMessageInput(menuMessage) {
    await this.menuMessageInput.sendKeys(menuMessage);
  }

  async getMenuMessageInput() {
    return await this.menuMessageInput.getAttribute('value');
  }

  async setStartAtInput(startAt) {
    await this.startAtInput.sendKeys(startAt);
  }

  async getStartAtInput() {
    return await this.startAtInput.getAttribute('value');
  }

  async setEndAtInput(endAt) {
    await this.endAtInput.sendKeys(endAt);
  }

  async getEndAtInput() {
    return await this.endAtInput.getAttribute('value');
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return await this.typeInput.getAttribute('value');
  }

  async setAddExtensionInput(addExtension) {
    await this.addExtensionInput.sendKeys(addExtension);
  }

  async getAddExtensionInput() {
    return await this.addExtensionInput.getAttribute('value');
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

export class MExtensionSaleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mExtensionSale-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mExtensionSale'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
