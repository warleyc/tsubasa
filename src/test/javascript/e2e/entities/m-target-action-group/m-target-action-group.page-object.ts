import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetActionGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-action-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-action-group div h2#page-heading span')).first();

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

export class MTargetActionGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-action-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  actionIdInput = element(by.id('field_actionId'));
  mactionSelect = element(by.id('field_maction'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setActionIdInput(actionId) {
    await this.actionIdInput.sendKeys(actionId);
  }

  async getActionIdInput() {
    return await this.actionIdInput.getAttribute('value');
  }

  async mactionSelectLastOption(timeout?: number) {
    await this.mactionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mactionSelectOption(option) {
    await this.mactionSelect.sendKeys(option);
  }

  getMactionSelect(): ElementFinder {
    return this.mactionSelect;
  }

  async getMactionSelectedOption() {
    return await this.mactionSelect.element(by.css('option:checked')).getText();
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

export class MTargetActionGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetActionGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetActionGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
