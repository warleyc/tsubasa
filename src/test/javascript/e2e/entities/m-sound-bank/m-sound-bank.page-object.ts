import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSoundBankComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-sound-bank div table .btn-danger'));
  title = element.all(by.css('jhi-m-sound-bank div h2#page-heading span')).first();

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

export class MSoundBankUpdatePage {
  pageTitle = element(by.id('jhi-m-sound-bank-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  pathInput = element(by.id('field_path'));
  pfInput = element(by.id('field_pf'));
  versionInput = element(by.id('field_version'));
  fileSizeInput = element(by.id('field_fileSize'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return await this.pathInput.getAttribute('value');
  }

  async setPfInput(pf) {
    await this.pfInput.sendKeys(pf);
  }

  async getPfInput() {
    return await this.pfInput.getAttribute('value');
  }

  async setVersionInput(version) {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput() {
    return await this.versionInput.getAttribute('value');
  }

  async setFileSizeInput(fileSize) {
    await this.fileSizeInput.sendKeys(fileSize);
  }

  async getFileSizeInput() {
    return await this.fileSizeInput.getAttribute('value');
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

export class MSoundBankDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSoundBank-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSoundBank'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
