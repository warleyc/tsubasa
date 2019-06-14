import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPenaltyKickCutComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-penalty-kick-cut div table .btn-danger'));
  title = element.all(by.css('jhi-m-penalty-kick-cut div h2#page-heading span')).first();

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

export class MPenaltyKickCutUpdatePage {
  pageTitle = element(by.id('jhi-m-penalty-kick-cut-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  kickerCourseInput = element(by.id('field_kickerCourse'));
  keeperCourseInput = element(by.id('field_keeperCourse'));
  keeperCommandInput = element(by.id('field_keeperCommand'));
  resultTypeInput = element(by.id('field_resultType'));
  offenseSequenceTextInput = element(by.id('field_offenseSequenceText'));
  defenseSequenceTextInput = element(by.id('field_defenseSequenceText'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setKickerCourseInput(kickerCourse) {
    await this.kickerCourseInput.sendKeys(kickerCourse);
  }

  async getKickerCourseInput() {
    return await this.kickerCourseInput.getAttribute('value');
  }

  async setKeeperCourseInput(keeperCourse) {
    await this.keeperCourseInput.sendKeys(keeperCourse);
  }

  async getKeeperCourseInput() {
    return await this.keeperCourseInput.getAttribute('value');
  }

  async setKeeperCommandInput(keeperCommand) {
    await this.keeperCommandInput.sendKeys(keeperCommand);
  }

  async getKeeperCommandInput() {
    return await this.keeperCommandInput.getAttribute('value');
  }

  async setResultTypeInput(resultType) {
    await this.resultTypeInput.sendKeys(resultType);
  }

  async getResultTypeInput() {
    return await this.resultTypeInput.getAttribute('value');
  }

  async setOffenseSequenceTextInput(offenseSequenceText) {
    await this.offenseSequenceTextInput.sendKeys(offenseSequenceText);
  }

  async getOffenseSequenceTextInput() {
    return await this.offenseSequenceTextInput.getAttribute('value');
  }

  async setDefenseSequenceTextInput(defenseSequenceText) {
    await this.defenseSequenceTextInput.sendKeys(defenseSequenceText);
  }

  async getDefenseSequenceTextInput() {
    return await this.defenseSequenceTextInput.getAttribute('value');
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

export class MPenaltyKickCutDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPenaltyKickCut-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPenaltyKickCut'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
