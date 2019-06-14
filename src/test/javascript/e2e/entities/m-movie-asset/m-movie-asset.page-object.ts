import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMovieAssetComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-movie-asset div table .btn-danger'));
  title = element.all(by.css('jhi-m-movie-asset div h2#page-heading span')).first();

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

export class MMovieAssetUpdatePage {
  pageTitle = element(by.id('jhi-m-movie-asset-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  langInput = element(by.id('field_lang'));
  nameInput = element(by.id('field_name'));
  sizeInput = element(by.id('field_size'));
  versionInput = element(by.id('field_version'));
  typeInput = element(by.id('field_type'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setLangInput(lang) {
    await this.langInput.sendKeys(lang);
  }

  async getLangInput() {
    return await this.langInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return await this.sizeInput.getAttribute('value');
  }

  async setVersionInput(version) {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput() {
    return await this.versionInput.getAttribute('value');
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return await this.typeInput.getAttribute('value');
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

export class MMovieAssetDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMovieAsset-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMovieAsset'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
