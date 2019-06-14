import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MRecommendShopMessageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-recommend-shop-message div table .btn-danger'));
  title = element.all(by.css('jhi-m-recommend-shop-message div h2#page-heading span')).first();

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

export class MRecommendShopMessageUpdatePage {
  pageTitle = element(by.id('jhi-m-recommend-shop-message-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  messageInput = element(by.id('field_message'));
  hasSalesPeriodInput = element(by.id('field_hasSalesPeriod'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMessageInput(message) {
    await this.messageInput.sendKeys(message);
  }

  async getMessageInput() {
    return await this.messageInput.getAttribute('value');
  }

  async setHasSalesPeriodInput(hasSalesPeriod) {
    await this.hasSalesPeriodInput.sendKeys(hasSalesPeriod);
  }

  async getHasSalesPeriodInput() {
    return await this.hasSalesPeriodInput.getAttribute('value');
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

export class MRecommendShopMessageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mRecommendShopMessage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mRecommendShopMessage'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
