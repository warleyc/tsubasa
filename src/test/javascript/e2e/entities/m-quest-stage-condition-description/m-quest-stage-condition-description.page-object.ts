import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestStageConditionDescriptionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-stage-condition-description div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-stage-condition-description div h2#page-heading span')).first();

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

export class MQuestStageConditionDescriptionUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-stage-condition-description-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  successConditionTypeInput = element(by.id('field_successConditionType'));
  successConditionDetailTypeValueInput = element(by.id('field_successConditionDetailTypeValue'));
  hasExistTargetCharacterGroupInput = element(by.id('field_hasExistTargetCharacterGroup'));
  hasSuccessConditionValueOneOnlyInput = element(by.id('field_hasSuccessConditionValueOneOnly'));
  failureConditionTypeValueInput = element(by.id('field_failureConditionTypeValue'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setSuccessConditionTypeInput(successConditionType) {
    await this.successConditionTypeInput.sendKeys(successConditionType);
  }

  async getSuccessConditionTypeInput() {
    return await this.successConditionTypeInput.getAttribute('value');
  }

  async setSuccessConditionDetailTypeValueInput(successConditionDetailTypeValue) {
    await this.successConditionDetailTypeValueInput.sendKeys(successConditionDetailTypeValue);
  }

  async getSuccessConditionDetailTypeValueInput() {
    return await this.successConditionDetailTypeValueInput.getAttribute('value');
  }

  async setHasExistTargetCharacterGroupInput(hasExistTargetCharacterGroup) {
    await this.hasExistTargetCharacterGroupInput.sendKeys(hasExistTargetCharacterGroup);
  }

  async getHasExistTargetCharacterGroupInput() {
    return await this.hasExistTargetCharacterGroupInput.getAttribute('value');
  }

  async setHasSuccessConditionValueOneOnlyInput(hasSuccessConditionValueOneOnly) {
    await this.hasSuccessConditionValueOneOnlyInput.sendKeys(hasSuccessConditionValueOneOnly);
  }

  async getHasSuccessConditionValueOneOnlyInput() {
    return await this.hasSuccessConditionValueOneOnlyInput.getAttribute('value');
  }

  async setFailureConditionTypeValueInput(failureConditionTypeValue) {
    await this.failureConditionTypeValueInput.sendKeys(failureConditionTypeValue);
  }

  async getFailureConditionTypeValueInput() {
    return await this.failureConditionTypeValueInput.getAttribute('value');
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

export class MQuestStageConditionDescriptionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestStageConditionDescription-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestStageConditionDescription'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
