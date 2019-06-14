import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MKeeperCutActionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-keeper-cut-action div table .btn-danger'));
  title = element.all(by.css('jhi-m-keeper-cut-action div h2#page-heading span')).first();

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

export class MKeeperCutActionUpdatePage {
  pageTitle = element(by.id('jhi-m-keeper-cut-action-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  actionCutIdInput = element(by.id('field_actionCutId'));
  keeperCutActionTypeInput = element(by.id('field_keeperCutActionType'));
  cutSequenceTextInput = element(by.id('field_cutSequenceText'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setActionCutIdInput(actionCutId) {
    await this.actionCutIdInput.sendKeys(actionCutId);
  }

  async getActionCutIdInput() {
    return await this.actionCutIdInput.getAttribute('value');
  }

  async setKeeperCutActionTypeInput(keeperCutActionType) {
    await this.keeperCutActionTypeInput.sendKeys(keeperCutActionType);
  }

  async getKeeperCutActionTypeInput() {
    return await this.keeperCutActionTypeInput.getAttribute('value');
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

export class MKeeperCutActionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mKeeperCutAction-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mKeeperCutAction'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
