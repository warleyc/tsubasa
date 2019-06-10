import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEncountersCommandBranchComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-encounters-command-branch div table .btn-danger'));
  title = element.all(by.css('jhi-m-encounters-command-branch div h2#page-heading span')).first();

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

export class MEncountersCommandBranchUpdatePage {
  pageTitle = element(by.id('jhi-m-encounters-command-branch-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  ballFloatConditionInput = element(by.id('field_ballFloatCondition'));
  conditionInput = element(by.id('field_condition'));
  encountersTypeInput = element(by.id('field_encountersType'));
  isSuccessInput = element(by.id('field_isSuccess'));
  commandTypeInput = element(by.id('field_commandType'));
  successRateInput = element(by.id('field_successRate'));
  looseBallRateInput = element(by.id('field_looseBallRate'));
  touchLightlyRateInput = element(by.id('field_touchLightlyRate'));
  postRateInput = element(by.id('field_postRate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setBallFloatConditionInput(ballFloatCondition) {
    await this.ballFloatConditionInput.sendKeys(ballFloatCondition);
  }

  async getBallFloatConditionInput() {
    return await this.ballFloatConditionInput.getAttribute('value');
  }

  async setConditionInput(condition) {
    await this.conditionInput.sendKeys(condition);
  }

  async getConditionInput() {
    return await this.conditionInput.getAttribute('value');
  }

  async setEncountersTypeInput(encountersType) {
    await this.encountersTypeInput.sendKeys(encountersType);
  }

  async getEncountersTypeInput() {
    return await this.encountersTypeInput.getAttribute('value');
  }

  async setIsSuccessInput(isSuccess) {
    await this.isSuccessInput.sendKeys(isSuccess);
  }

  async getIsSuccessInput() {
    return await this.isSuccessInput.getAttribute('value');
  }

  async setCommandTypeInput(commandType) {
    await this.commandTypeInput.sendKeys(commandType);
  }

  async getCommandTypeInput() {
    return await this.commandTypeInput.getAttribute('value');
  }

  async setSuccessRateInput(successRate) {
    await this.successRateInput.sendKeys(successRate);
  }

  async getSuccessRateInput() {
    return await this.successRateInput.getAttribute('value');
  }

  async setLooseBallRateInput(looseBallRate) {
    await this.looseBallRateInput.sendKeys(looseBallRate);
  }

  async getLooseBallRateInput() {
    return await this.looseBallRateInput.getAttribute('value');
  }

  async setTouchLightlyRateInput(touchLightlyRate) {
    await this.touchLightlyRateInput.sendKeys(touchLightlyRate);
  }

  async getTouchLightlyRateInput() {
    return await this.touchLightlyRateInput.getAttribute('value');
  }

  async setPostRateInput(postRate) {
    await this.postRateInput.sendKeys(postRate);
  }

  async getPostRateInput() {
    return await this.postRateInput.getAttribute('value');
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

export class MEncountersCommandBranchDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEncountersCommandBranch-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEncountersCommandBranch'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
