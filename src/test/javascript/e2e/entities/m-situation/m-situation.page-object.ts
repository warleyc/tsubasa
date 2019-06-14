import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSituationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-situation div table .btn-danger'));
  title = element.all(by.css('jhi-m-situation div h2#page-heading span')).first();

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

export class MSituationUpdatePage {
  pageTitle = element(by.id('jhi-m-situation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  kickoffInput = element(by.id('field_kickoff'));
  penaltyKickInput = element(by.id('field_penaltyKick'));
  matchIntervalInput = element(by.id('field_matchInterval'));
  outOfPlayInput = element(by.id('field_outOfPlay'));
  foulInput = element(by.id('field_foul'));
  goalInput = element(by.id('field_goal'));
  scoreInput = element(by.id('field_score'));
  timeInput = element(by.id('field_time'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setKickoffInput(kickoff) {
    await this.kickoffInput.sendKeys(kickoff);
  }

  async getKickoffInput() {
    return await this.kickoffInput.getAttribute('value');
  }

  async setPenaltyKickInput(penaltyKick) {
    await this.penaltyKickInput.sendKeys(penaltyKick);
  }

  async getPenaltyKickInput() {
    return await this.penaltyKickInput.getAttribute('value');
  }

  async setMatchIntervalInput(matchInterval) {
    await this.matchIntervalInput.sendKeys(matchInterval);
  }

  async getMatchIntervalInput() {
    return await this.matchIntervalInput.getAttribute('value');
  }

  async setOutOfPlayInput(outOfPlay) {
    await this.outOfPlayInput.sendKeys(outOfPlay);
  }

  async getOutOfPlayInput() {
    return await this.outOfPlayInput.getAttribute('value');
  }

  async setFoulInput(foul) {
    await this.foulInput.sendKeys(foul);
  }

  async getFoulInput() {
    return await this.foulInput.getAttribute('value');
  }

  async setGoalInput(goal) {
    await this.goalInput.sendKeys(goal);
  }

  async getGoalInput() {
    return await this.goalInput.getAttribute('value');
  }

  async setScoreInput(score) {
    await this.scoreInput.sendKeys(score);
  }

  async getScoreInput() {
    return await this.scoreInput.getAttribute('value');
  }

  async setTimeInput(time) {
    await this.timeInput.sendKeys(time);
  }

  async getTimeInput() {
    return await this.timeInput.getAttribute('value');
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

export class MSituationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSituation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSituation'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
