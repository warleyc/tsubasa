import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MUserPolicyUpdateDateComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-user-policy-update-date div table .btn-danger'));
  title = element.all(by.css('jhi-m-user-policy-update-date div h2#page-heading span')).first();

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

export class MUserPolicyUpdateDateUpdatePage {
  pageTitle = element(by.id('jhi-m-user-policy-update-date-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  updateDateInput = element(by.id('field_updateDate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUpdateDateInput(updateDate) {
    await this.updateDateInput.sendKeys(updateDate);
  }

  async getUpdateDateInput() {
    return await this.updateDateInput.getAttribute('value');
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

export class MUserPolicyUpdateDateDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mUserPolicyUpdateDate-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mUserPolicyUpdateDate'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
