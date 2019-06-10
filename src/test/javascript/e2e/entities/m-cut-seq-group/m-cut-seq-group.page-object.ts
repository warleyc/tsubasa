import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCutSeqGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-cut-seq-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-cut-seq-group div h2#page-heading span')).first();

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

export class MCutSeqGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-cut-seq-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  keyInput = element(by.id('field_key'));
  paramInput = element(by.id('field_param'));
  cutSequenceTextInput = element(by.id('field_cutSequenceText'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setKeyInput(key) {
    await this.keyInput.sendKeys(key);
  }

  async getKeyInput() {
    return await this.keyInput.getAttribute('value');
  }

  async setParamInput(param) {
    await this.paramInput.sendKeys(param);
  }

  async getParamInput() {
    return await this.paramInput.getAttribute('value');
  }

  async setCutSequenceTextInput(cutSequenceText) {
    await this.cutSequenceTextInput.sendKeys(cutSequenceText);
  }

  async getCutSequenceTextInput() {
    return await this.cutSequenceTextInput.getAttribute('value');
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

export class MCutSeqGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCutSeqGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCutSeqGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
