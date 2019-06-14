import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestStageConditionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-stage-condition div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-stage-condition div h2#page-heading span')).first();

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

export class MQuestStageConditionUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-stage-condition-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  successConditionTypeInput = element(by.id('field_successConditionType'));
  successConditionDetailTypeInput = element(by.id('field_successConditionDetailType'));
  successConditionValueInput = element(by.id('field_successConditionValue'));
  targetCharacterGroupIdInput = element(by.id('field_targetCharacterGroupId'));
  failureConditionTypeInput = element(by.id('field_failureConditionType'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setSuccessConditionTypeInput(successConditionType) {
    await this.successConditionTypeInput.sendKeys(successConditionType);
  }

  async getSuccessConditionTypeInput() {
    return await this.successConditionTypeInput.getAttribute('value');
  }

  async setSuccessConditionDetailTypeInput(successConditionDetailType) {
    await this.successConditionDetailTypeInput.sendKeys(successConditionDetailType);
  }

  async getSuccessConditionDetailTypeInput() {
    return await this.successConditionDetailTypeInput.getAttribute('value');
  }

  async setSuccessConditionValueInput(successConditionValue) {
    await this.successConditionValueInput.sendKeys(successConditionValue);
  }

  async getSuccessConditionValueInput() {
    return await this.successConditionValueInput.getAttribute('value');
  }

  async setTargetCharacterGroupIdInput(targetCharacterGroupId) {
    await this.targetCharacterGroupIdInput.sendKeys(targetCharacterGroupId);
  }

  async getTargetCharacterGroupIdInput() {
    return await this.targetCharacterGroupIdInput.getAttribute('value');
  }

  async setFailureConditionTypeInput(failureConditionType) {
    await this.failureConditionTypeInput.sendKeys(failureConditionType);
  }

  async getFailureConditionTypeInput() {
    return await this.failureConditionTypeInput.getAttribute('value');
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

export class MQuestStageConditionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestStageCondition-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestStageCondition'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
