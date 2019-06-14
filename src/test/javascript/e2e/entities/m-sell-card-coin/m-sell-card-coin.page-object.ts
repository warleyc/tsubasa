import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSellCardCoinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-sell-card-coin div table .btn-danger'));
  title = element.all(by.css('jhi-m-sell-card-coin div h2#page-heading span')).first();

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

export class MSellCardCoinUpdatePage {
  pageTitle = element(by.id('jhi-m-sell-card-coin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupNumInput = element(by.id('field_groupNum'));
  levelInput = element(by.id('field_level'));
  coinInput = element(by.id('field_coin'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupNumInput(groupNum) {
    await this.groupNumInput.sendKeys(groupNum);
  }

  async getGroupNumInput() {
    return await this.groupNumInput.getAttribute('value');
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setCoinInput(coin) {
    await this.coinInput.sendKeys(coin);
  }

  async getCoinInput() {
    return await this.coinInput.getAttribute('value');
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

export class MSellCardCoinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSellCardCoin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSellCardCoin'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
