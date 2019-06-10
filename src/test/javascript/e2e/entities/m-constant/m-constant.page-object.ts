import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MConstantComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-constant div table .btn-danger'));
  title = element.all(by.css('jhi-m-constant div h2#page-heading span')).first();

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

export class MConstantUpdatePage {
  pageTitle = element(by.id('jhi-m-constant-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  keyInput = element(by.id('field_key'));
  valueInput = element(by.id('field_value'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setKeyInput(key) {
    await this.keyInput.sendKeys(key);
  }

  async getKeyInput() {
    return await this.keyInput.getAttribute('value');
  }

  async setValueInput(value) {
    await this.valueInput.sendKeys(value);
  }

  async getValueInput() {
    return await this.valueInput.getAttribute('value');
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

export class MConstantDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mConstant-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mConstant'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
