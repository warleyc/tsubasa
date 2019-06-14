import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLoginBonusIncentiveComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-login-bonus-incentive div table .btn-danger'));
  title = element.all(by.css('jhi-m-login-bonus-incentive div h2#page-heading span')).first();

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

export class MLoginBonusIncentiveUpdatePage {
  pageTitle = element(by.id('jhi-m-login-bonus-incentive-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  roundIdInput = element(by.id('field_roundId'));
  dayInput = element(by.id('field_day'));
  contentTypeInput = element(by.id('field_contentType'));
  contentIdInput = element(by.id('field_contentId'));
  contentAmountInput = element(by.id('field_contentAmount'));
  presentDetailInput = element(by.id('field_presentDetail'));
  isDecoratedInput = element(by.id('field_isDecorated'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRoundIdInput(roundId) {
    await this.roundIdInput.sendKeys(roundId);
  }

  async getRoundIdInput() {
    return await this.roundIdInput.getAttribute('value');
  }

  async setDayInput(day) {
    await this.dayInput.sendKeys(day);
  }

  async getDayInput() {
    return await this.dayInput.getAttribute('value');
  }

  async setContentTypeInput(contentType) {
    await this.contentTypeInput.sendKeys(contentType);
  }

  async getContentTypeInput() {
    return await this.contentTypeInput.getAttribute('value');
  }

  async setContentIdInput(contentId) {
    await this.contentIdInput.sendKeys(contentId);
  }

  async getContentIdInput() {
    return await this.contentIdInput.getAttribute('value');
  }

  async setContentAmountInput(contentAmount) {
    await this.contentAmountInput.sendKeys(contentAmount);
  }

  async getContentAmountInput() {
    return await this.contentAmountInput.getAttribute('value');
  }

  async setPresentDetailInput(presentDetail) {
    await this.presentDetailInput.sendKeys(presentDetail);
  }

  async getPresentDetailInput() {
    return await this.presentDetailInput.getAttribute('value');
  }

  async setIsDecoratedInput(isDecorated) {
    await this.isDecoratedInput.sendKeys(isDecorated);
  }

  async getIsDecoratedInput() {
    return await this.isDecoratedInput.getAttribute('value');
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

export class MLoginBonusIncentiveDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLoginBonusIncentive-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLoginBonusIncentive'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
