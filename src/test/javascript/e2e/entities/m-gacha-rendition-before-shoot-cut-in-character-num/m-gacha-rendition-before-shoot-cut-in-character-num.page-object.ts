import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionBeforeShootCutInCharacterNumComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-before-shoot-cut-in-character-num div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-before-shoot-cut-in-character-num div h2#page-heading span')).first();

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

export class MGachaRenditionBeforeShootCutInCharacterNumUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-before-shoot-cut-in-character-num-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isManySsrInput = element(by.id('field_isManySsr'));
  isSsrInput = element(by.id('field_isSsr'));
  patternInput = element(by.id('field_pattern'));
  weightInput = element(by.id('field_weight'));
  numInput = element(by.id('field_num'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIsManySsrInput(isManySsr) {
    await this.isManySsrInput.sendKeys(isManySsr);
  }

  async getIsManySsrInput() {
    return await this.isManySsrInput.getAttribute('value');
  }

  async setIsSsrInput(isSsr) {
    await this.isSsrInput.sendKeys(isSsr);
  }

  async getIsSsrInput() {
    return await this.isSsrInput.getAttribute('value');
  }

  async setPatternInput(pattern) {
    await this.patternInput.sendKeys(pattern);
  }

  async getPatternInput() {
    return await this.patternInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setNumInput(num) {
    await this.numInput.sendKeys(num);
  }

  async getNumInput() {
    return await this.numInput.getAttribute('value');
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

export class MGachaRenditionBeforeShootCutInCharacterNumDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionBeforeShootCutInCharacterNum-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionBeforeShootCutInCharacterNum'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
