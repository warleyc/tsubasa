import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MAssetComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-asset div table .btn-danger'));
  title = element.all(by.css('jhi-m-asset div h2#page-heading span')).first();

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

export class MAssetUpdatePage {
  pageTitle = element(by.id('jhi-m-asset-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  assetBundleNameInput = element(by.id('field_assetBundleName'));
  tagInput = element(by.id('field_tag'));
  dependenciesInput = element(by.id('field_dependencies'));
  i18nInput = element(by.id('field_i18n'));
  platformInput = element(by.id('field_platform'));
  packNameInput = element(by.id('field_packName'));
  headInput = element(by.id('field_head'));
  sizeInput = element(by.id('field_size'));
  key1Input = element(by.id('field_key1'));
  key2Input = element(by.id('field_key2'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAssetBundleNameInput(assetBundleName) {
    await this.assetBundleNameInput.sendKeys(assetBundleName);
  }

  async getAssetBundleNameInput() {
    return await this.assetBundleNameInput.getAttribute('value');
  }

  async setTagInput(tag) {
    await this.tagInput.sendKeys(tag);
  }

  async getTagInput() {
    return await this.tagInput.getAttribute('value');
  }

  async setDependenciesInput(dependencies) {
    await this.dependenciesInput.sendKeys(dependencies);
  }

  async getDependenciesInput() {
    return await this.dependenciesInput.getAttribute('value');
  }

  async setI18nInput(i18n) {
    await this.i18nInput.sendKeys(i18n);
  }

  async getI18nInput() {
    return await this.i18nInput.getAttribute('value');
  }

  async setPlatformInput(platform) {
    await this.platformInput.sendKeys(platform);
  }

  async getPlatformInput() {
    return await this.platformInput.getAttribute('value');
  }

  async setPackNameInput(packName) {
    await this.packNameInput.sendKeys(packName);
  }

  async getPackNameInput() {
    return await this.packNameInput.getAttribute('value');
  }

  async setHeadInput(head) {
    await this.headInput.sendKeys(head);
  }

  async getHeadInput() {
    return await this.headInput.getAttribute('value');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return await this.sizeInput.getAttribute('value');
  }

  async setKey1Input(key1) {
    await this.key1Input.sendKeys(key1);
  }

  async getKey1Input() {
    return await this.key1Input.getAttribute('value');
  }

  async setKey2Input(key2) {
    await this.key2Input.sendKeys(key2);
  }

  async getKey2Input() {
    return await this.key2Input.getAttribute('value');
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

export class MAssetDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAsset-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAsset'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
