import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLuckComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-luck div table .btn-danger'));
  title = element.all(by.css('jhi-m-luck div h2#page-heading span')).first();

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

export class MLuckUpdatePage {
  pageTitle = element(by.id('jhi-m-luck-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  targetAttributeInput = element(by.id('field_targetAttribute'));
  targetCharacterGroupIdInput = element(by.id('field_targetCharacterGroupId'));
  targetTeamGroupIdInput = element(by.id('field_targetTeamGroupId'));
  targetNationalityGroupIdInput = element(by.id('field_targetNationalityGroupId'));
  luckRateGroupIdInput = element(by.id('field_luckRateGroupId'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTargetAttributeInput(targetAttribute) {
    await this.targetAttributeInput.sendKeys(targetAttribute);
  }

  async getTargetAttributeInput() {
    return await this.targetAttributeInput.getAttribute('value');
  }

  async setTargetCharacterGroupIdInput(targetCharacterGroupId) {
    await this.targetCharacterGroupIdInput.sendKeys(targetCharacterGroupId);
  }

  async getTargetCharacterGroupIdInput() {
    return await this.targetCharacterGroupIdInput.getAttribute('value');
  }

  async setTargetTeamGroupIdInput(targetTeamGroupId) {
    await this.targetTeamGroupIdInput.sendKeys(targetTeamGroupId);
  }

  async getTargetTeamGroupIdInput() {
    return await this.targetTeamGroupIdInput.getAttribute('value');
  }

  async setTargetNationalityGroupIdInput(targetNationalityGroupId) {
    await this.targetNationalityGroupIdInput.sendKeys(targetNationalityGroupId);
  }

  async getTargetNationalityGroupIdInput() {
    return await this.targetNationalityGroupIdInput.getAttribute('value');
  }

  async setLuckRateGroupIdInput(luckRateGroupId) {
    await this.luckRateGroupIdInput.sendKeys(luckRateGroupId);
  }

  async getLuckRateGroupIdInput() {
    return await this.luckRateGroupIdInput.getAttribute('value');
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

export class MLuckDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLuck-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLuck'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
