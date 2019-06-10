import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDbMappingComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-db-mapping div table .btn-danger'));
  title = element.all(by.css('jhi-m-db-mapping div h2#page-heading span')).first();

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

export class MDbMappingUpdatePage {
  pageTitle = element(by.id('jhi-m-db-mapping-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  fileNameInput = element(by.id('field_fileName'));
  dbNameInput = element(by.id('field_dbName'));
  pathInput = element(by.id('field_path'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setFileNameInput(fileName) {
    await this.fileNameInput.sendKeys(fileName);
  }

  async getFileNameInput() {
    return await this.fileNameInput.getAttribute('value');
  }

  async setDbNameInput(dbName) {
    await this.dbNameInput.sendKeys(dbName);
  }

  async getDbNameInput() {
    return await this.dbNameInput.getAttribute('value');
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return await this.pathInput.getAttribute('value');
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

export class MDbMappingDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDbMapping-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDbMapping'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
