import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MRegulationMatchTutorialMessageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-regulation-match-tutorial-message div table .btn-danger'));
  title = element.all(by.css('jhi-m-regulation-match-tutorial-message div h2#page-heading span')).first();

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

export class MRegulationMatchTutorialMessageUpdatePage {
  pageTitle = element(by.id('jhi-m-regulation-match-tutorial-message-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  ruleIdInput = element(by.id('field_ruleId'));
  orderNumInput = element(by.id('field_orderNum'));
  positionInput = element(by.id('field_position'));
  messageInput = element(by.id('field_message'));
  assetNameInput = element(by.id('field_assetName'));
  titleInput = element(by.id('field_title'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRuleIdInput(ruleId) {
    await this.ruleIdInput.sendKeys(ruleId);
  }

  async getRuleIdInput() {
    return await this.ruleIdInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
  }

  async setPositionInput(position) {
    await this.positionInput.sendKeys(position);
  }

  async getPositionInput() {
    return await this.positionInput.getAttribute('value');
  }

  async setMessageInput(message) {
    await this.messageInput.sendKeys(message);
  }

  async getMessageInput() {
    return await this.messageInput.getAttribute('value');
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
  }

  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return await this.titleInput.getAttribute('value');
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

export class MRegulationMatchTutorialMessageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mRegulationMatchTutorialMessage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mRegulationMatchTutorialMessage'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
