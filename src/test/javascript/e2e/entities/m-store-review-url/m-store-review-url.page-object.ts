import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MStoreReviewUrlComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-store-review-url div table .btn-danger'));
  title = element.all(by.css('jhi-m-store-review-url div h2#page-heading span')).first();

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

export class MStoreReviewUrlUpdatePage {
  pageTitle = element(by.id('jhi-m-store-review-url-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  platformInput = element(by.id('field_platform'));
  urlInput = element(by.id('field_url'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPlatformInput(platform) {
    await this.platformInput.sendKeys(platform);
  }

  async getPlatformInput() {
    return await this.platformInput.getAttribute('value');
  }

  async setUrlInput(url) {
    await this.urlInput.sendKeys(url);
  }

  async getUrlInput() {
    return await this.urlInput.getAttribute('value');
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

export class MStoreReviewUrlDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mStoreReviewUrl-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mStoreReviewUrl'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
