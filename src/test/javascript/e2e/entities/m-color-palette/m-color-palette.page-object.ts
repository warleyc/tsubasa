import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MColorPaletteComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-color-palette div table .btn-danger'));
  title = element.all(by.css('jhi-m-color-palette div h2#page-heading span')).first();

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

export class MColorPaletteUpdatePage {
  pageTitle = element(by.id('jhi-m-color-palette-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  colorInput = element(by.id('field_color'));
  orderNumInput = element(by.id('field_orderNum'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setColorInput(color) {
    await this.colorInput.sendKeys(color);
  }

  async getColorInput() {
    return await this.colorInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
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

export class MColorPaletteDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mColorPalette-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mColorPalette'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
