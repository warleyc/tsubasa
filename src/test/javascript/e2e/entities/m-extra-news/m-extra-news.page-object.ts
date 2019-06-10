import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MExtraNewsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-extra-news div table .btn-danger'));
  title = element.all(by.css('jhi-m-extra-news div h2#page-heading span')).first();

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

export class MExtraNewsUpdatePage {
  pageTitle = element(by.id('jhi-m-extra-news-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  orderNumInput = element(by.id('field_orderNum'));
  assetNameInput = element(by.id('field_assetName'));
  webviewIdInput = element(by.id('field_webviewId'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
  }

  async setWebviewIdInput(webviewId) {
    await this.webviewIdInput.sendKeys(webviewId);
  }

  async getWebviewIdInput() {
    return await this.webviewIdInput.getAttribute('value');
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

export class MExtraNewsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mExtraNews-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mExtraNews'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
