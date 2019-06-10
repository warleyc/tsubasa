import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCoopRoomStampComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-coop-room-stamp div table .btn-danger'));
  title = element.all(by.css('jhi-m-coop-room-stamp div h2#page-heading span')).first();

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

export class MCoopRoomStampUpdatePage {
  pageTitle = element(by.id('jhi-m-coop-room-stamp-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  roleInput = element(by.id('field_role'));
  orderNumInput = element(by.id('field_orderNum'));
  masterIdInput = element(by.id('field_masterId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRoleInput(role) {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput() {
    return await this.roleInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
  }

  async setMasterIdInput(masterId) {
    await this.masterIdInput.sendKeys(masterId);
  }

  async getMasterIdInput() {
    return await this.masterIdInput.getAttribute('value');
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

export class MCoopRoomStampDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCoopRoomStamp-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCoopRoomStamp'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
