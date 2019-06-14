import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGoalJudgementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-goal-judgement div table .btn-danger'));
  title = element.all(by.css('jhi-m-goal-judgement div h2#page-heading span')).first();

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

export class MGoalJudgementUpdatePage {
  pageTitle = element(by.id('jhi-m-goal-judgement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  judgementIdInput = element(by.id('field_judgementId'));
  encountersTypeInput = element(by.id('field_encountersType'));
  successRateInput = element(by.id('field_successRate'));
  goalPostRateInput = element(by.id('field_goalPostRate'));
  ballPushRateInput = element(by.id('field_ballPushRate'));
  clearRateInput = element(by.id('field_clearRate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setJudgementIdInput(judgementId) {
    await this.judgementIdInput.sendKeys(judgementId);
  }

  async getJudgementIdInput() {
    return await this.judgementIdInput.getAttribute('value');
  }

  async setEncountersTypeInput(encountersType) {
    await this.encountersTypeInput.sendKeys(encountersType);
  }

  async getEncountersTypeInput() {
    return await this.encountersTypeInput.getAttribute('value');
  }

  async setSuccessRateInput(successRate) {
    await this.successRateInput.sendKeys(successRate);
  }

  async getSuccessRateInput() {
    return await this.successRateInput.getAttribute('value');
  }

  async setGoalPostRateInput(goalPostRate) {
    await this.goalPostRateInput.sendKeys(goalPostRate);
  }

  async getGoalPostRateInput() {
    return await this.goalPostRateInput.getAttribute('value');
  }

  async setBallPushRateInput(ballPushRate) {
    await this.ballPushRateInput.sendKeys(ballPushRate);
  }

  async getBallPushRateInput() {
    return await this.ballPushRateInput.getAttribute('value');
  }

  async setClearRateInput(clearRate) {
    await this.clearRateInput.sendKeys(clearRate);
  }

  async getClearRateInput() {
    return await this.clearRateInput.getAttribute('value');
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

export class MGoalJudgementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGoalJudgement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGoalJudgement'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
