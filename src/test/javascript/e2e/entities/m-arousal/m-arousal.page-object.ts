import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MArousalComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-arousal div table .btn-danger'));
  title = element.all(by.css('jhi-m-arousal div h2#page-heading span')).first();

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

export class MArousalUpdatePage {
  pageTitle = element(by.id('jhi-m-arousal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  beforeIdInput = element(by.id('field_beforeId'));
  afterIdInput = element(by.id('field_afterId'));
  costInput = element(by.id('field_cost'));
  materialGroupIdInput = element(by.id('field_materialGroupId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setBeforeIdInput(beforeId) {
    await this.beforeIdInput.sendKeys(beforeId);
  }

  async getBeforeIdInput() {
    return await this.beforeIdInput.getAttribute('value');
  }

  async setAfterIdInput(afterId) {
    await this.afterIdInput.sendKeys(afterId);
  }

  async getAfterIdInput() {
    return await this.afterIdInput.getAttribute('value');
  }

  async setCostInput(cost) {
    await this.costInput.sendKeys(cost);
  }

  async getCostInput() {
    return await this.costInput.getAttribute('value');
  }

  async setMaterialGroupIdInput(materialGroupId) {
    await this.materialGroupIdInput.sendKeys(materialGroupId);
  }

  async getMaterialGroupIdInput() {
    return await this.materialGroupIdInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MArousalDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mArousal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mArousal'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
