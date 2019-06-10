import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MArousalMaterialComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-arousal-material div table .btn-danger'));
  title = element.all(by.css('jhi-m-arousal-material div h2#page-heading span')).first();

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

export class MArousalMaterialUpdatePage {
  pageTitle = element(by.id('jhi-m-arousal-material-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  contentTypeInput = element(by.id('field_contentType'));
  contentIdInput = element(by.id('field_contentId'));
  contentAmountInput = element(by.id('field_contentAmount'));
  mainActionLevelInput = element(by.id('field_mainActionLevel'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setContentTypeInput(contentType) {
    await this.contentTypeInput.sendKeys(contentType);
  }

  async getContentTypeInput() {
    return await this.contentTypeInput.getAttribute('value');
  }

  async setContentIdInput(contentId) {
    await this.contentIdInput.sendKeys(contentId);
  }

  async getContentIdInput() {
    return await this.contentIdInput.getAttribute('value');
  }

  async setContentAmountInput(contentAmount) {
    await this.contentAmountInput.sendKeys(contentAmount);
  }

  async getContentAmountInput() {
    return await this.contentAmountInput.getAttribute('value');
  }

  async setMainActionLevelInput(mainActionLevel) {
    await this.mainActionLevelInput.sendKeys(mainActionLevel);
  }

  async getMainActionLevelInput() {
    return await this.mainActionLevelInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
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

export class MArousalMaterialDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mArousalMaterial-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mArousalMaterial'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
