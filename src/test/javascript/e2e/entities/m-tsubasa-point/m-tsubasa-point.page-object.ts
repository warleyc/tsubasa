import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTsubasaPointComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-tsubasa-point div table .btn-danger'));
  title = element.all(by.css('jhi-m-tsubasa-point div h2#page-heading span')).first();

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

export class MTsubasaPointUpdatePage {
  pageTitle = element(by.id('jhi-m-tsubasa-point-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  matchTypeInput = element(by.id('field_matchType'));
  pointTypeInput = element(by.id('field_pointType'));
  calcTypeInput = element(by.id('field_calcType'));
  aValueInput = element(by.id('field_aValue'));
  bValueInput = element(by.id('field_bValue'));
  orderNumInput = element(by.id('field_orderNum'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMatchTypeInput(matchType) {
    await this.matchTypeInput.sendKeys(matchType);
  }

  async getMatchTypeInput() {
    return await this.matchTypeInput.getAttribute('value');
  }

  async setPointTypeInput(pointType) {
    await this.pointTypeInput.sendKeys(pointType);
  }

  async getPointTypeInput() {
    return await this.pointTypeInput.getAttribute('value');
  }

  async setCalcTypeInput(calcType) {
    await this.calcTypeInput.sendKeys(calcType);
  }

  async getCalcTypeInput() {
    return await this.calcTypeInput.getAttribute('value');
  }

  async setAValueInput(aValue) {
    await this.aValueInput.sendKeys(aValue);
  }

  async getAValueInput() {
    return await this.aValueInput.getAttribute('value');
  }

  async setBValueInput(bValue) {
    await this.bValueInput.sendKeys(bValue);
  }

  async getBValueInput() {
    return await this.bValueInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
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

export class MTsubasaPointDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTsubasaPoint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTsubasaPoint'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
