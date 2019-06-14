import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDummyEmblemComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-dummy-emblem div table .btn-danger'));
  title = element.all(by.css('jhi-m-dummy-emblem div h2#page-heading span')).first();

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

export class MDummyEmblemUpdatePage {
  pageTitle = element(by.id('jhi-m-dummy-emblem-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  backgroundIdInput = element(by.id('field_backgroundId'));
  backgroundColorInput = element(by.id('field_backgroundColor'));
  upperIdInput = element(by.id('field_upperId'));
  upperColorInput = element(by.id('field_upperColor'));
  middleIdInput = element(by.id('field_middleId'));
  middleColorInput = element(by.id('field_middleColor'));
  lowerIdInput = element(by.id('field_lowerId'));
  lowerColorInput = element(by.id('field_lowerColor'));
  memblempartsSelect = element(by.id('field_memblemparts'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setBackgroundIdInput(backgroundId) {
    await this.backgroundIdInput.sendKeys(backgroundId);
  }

  async getBackgroundIdInput() {
    return await this.backgroundIdInput.getAttribute('value');
  }

  async setBackgroundColorInput(backgroundColor) {
    await this.backgroundColorInput.sendKeys(backgroundColor);
  }

  async getBackgroundColorInput() {
    return await this.backgroundColorInput.getAttribute('value');
  }

  async setUpperIdInput(upperId) {
    await this.upperIdInput.sendKeys(upperId);
  }

  async getUpperIdInput() {
    return await this.upperIdInput.getAttribute('value');
  }

  async setUpperColorInput(upperColor) {
    await this.upperColorInput.sendKeys(upperColor);
  }

  async getUpperColorInput() {
    return await this.upperColorInput.getAttribute('value');
  }

  async setMiddleIdInput(middleId) {
    await this.middleIdInput.sendKeys(middleId);
  }

  async getMiddleIdInput() {
    return await this.middleIdInput.getAttribute('value');
  }

  async setMiddleColorInput(middleColor) {
    await this.middleColorInput.sendKeys(middleColor);
  }

  async getMiddleColorInput() {
    return await this.middleColorInput.getAttribute('value');
  }

  async setLowerIdInput(lowerId) {
    await this.lowerIdInput.sendKeys(lowerId);
  }

  async getLowerIdInput() {
    return await this.lowerIdInput.getAttribute('value');
  }

  async setLowerColorInput(lowerColor) {
    await this.lowerColorInput.sendKeys(lowerColor);
  }

  async getLowerColorInput() {
    return await this.lowerColorInput.getAttribute('value');
  }

  async memblempartsSelectLastOption(timeout?: number) {
    await this.memblempartsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async memblempartsSelectOption(option) {
    await this.memblempartsSelect.sendKeys(option);
  }

  getMemblempartsSelect(): ElementFinder {
    return this.memblempartsSelect;
  }

  async getMemblempartsSelectedOption() {
    return await this.memblempartsSelect.element(by.css('option:checked')).getText();
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

export class MDummyEmblemDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDummyEmblem-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDummyEmblem'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
