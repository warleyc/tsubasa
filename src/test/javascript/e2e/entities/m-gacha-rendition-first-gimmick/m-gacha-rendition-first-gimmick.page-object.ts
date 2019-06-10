import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionFirstGimmickComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-first-gimmick div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-first-gimmick div h2#page-heading span')).first();

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

export class MGachaRenditionFirstGimmickUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-first-gimmick-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  birdNumInput = element(by.id('field_birdNum'));
  isTickerTapeInput = element(by.id('field_isTickerTape'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIsSsrInput(isSsr) {
    await this.isSsrInput.sendKeys(isSsr);
  }

  async getIsSsrInput() {
    return await this.isSsrInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setBirdNumInput(birdNum) {
    await this.birdNumInput.sendKeys(birdNum);
  }

  async getBirdNumInput() {
    return await this.birdNumInput.getAttribute('value');
  }

  async setIsTickerTapeInput(isTickerTape) {
    await this.isTickerTapeInput.sendKeys(isTickerTape);
  }

  async getIsTickerTapeInput() {
    return await this.isTickerTapeInput.getAttribute('value');
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

export class MGachaRenditionFirstGimmickDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionFirstGimmick-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionFirstGimmick'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
