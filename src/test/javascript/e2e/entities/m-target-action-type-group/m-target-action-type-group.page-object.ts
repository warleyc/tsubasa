import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetActionTypeGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-action-type-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-action-type-group div h2#page-heading span')).first();

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

export class MTargetActionTypeGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-action-type-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  commandTypeInput = element(by.id('field_commandType'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setCommandTypeInput(commandType) {
    await this.commandTypeInput.sendKeys(commandType);
  }

  async getCommandTypeInput() {
    return await this.commandTypeInput.getAttribute('value');
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

export class MTargetActionTypeGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetActionTypeGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetActionTypeGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
