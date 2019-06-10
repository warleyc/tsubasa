import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEncountersCutComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-encounters-cut div table .btn-danger'));
  title = element.all(by.css('jhi-m-encounters-cut div h2#page-heading span')).first();

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

export class MEncountersCutUpdatePage {
  pageTitle = element(by.id('jhi-m-encounters-cut-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  conditionInput = element(by.id('field_condition'));
  ballFloatConditionInput = element(by.id('field_ballFloatCondition'));
  command1TypeInput = element(by.id('field_command1Type'));
  command1IsSkillInput = element(by.id('field_command1IsSkill'));
  command2TypeInput = element(by.id('field_command2Type'));
  command2IsSkillInput = element(by.id('field_command2IsSkill'));
  resultInput = element(by.id('field_result'));
  shootResultInput = element(by.id('field_shootResult'));
  isThrownInput = element(by.id('field_isThrown'));
  offenseSequenceTextInput = element(by.id('field_offenseSequenceText'));
  defenseSequenceTextInput = element(by.id('field_defenseSequenceText'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setConditionInput(condition) {
    await this.conditionInput.sendKeys(condition);
  }

  async getConditionInput() {
    return await this.conditionInput.getAttribute('value');
  }

  async setBallFloatConditionInput(ballFloatCondition) {
    await this.ballFloatConditionInput.sendKeys(ballFloatCondition);
  }

  async getBallFloatConditionInput() {
    return await this.ballFloatConditionInput.getAttribute('value');
  }

  async setCommand1TypeInput(command1Type) {
    await this.command1TypeInput.sendKeys(command1Type);
  }

  async getCommand1TypeInput() {
    return await this.command1TypeInput.getAttribute('value');
  }

  async setCommand1IsSkillInput(command1IsSkill) {
    await this.command1IsSkillInput.sendKeys(command1IsSkill);
  }

  async getCommand1IsSkillInput() {
    return await this.command1IsSkillInput.getAttribute('value');
  }

  async setCommand2TypeInput(command2Type) {
    await this.command2TypeInput.sendKeys(command2Type);
  }

  async getCommand2TypeInput() {
    return await this.command2TypeInput.getAttribute('value');
  }

  async setCommand2IsSkillInput(command2IsSkill) {
    await this.command2IsSkillInput.sendKeys(command2IsSkill);
  }

  async getCommand2IsSkillInput() {
    return await this.command2IsSkillInput.getAttribute('value');
  }

  async setResultInput(result) {
    await this.resultInput.sendKeys(result);
  }

  async getResultInput() {
    return await this.resultInput.getAttribute('value');
  }

  async setShootResultInput(shootResult) {
    await this.shootResultInput.sendKeys(shootResult);
  }

  async getShootResultInput() {
    return await this.shootResultInput.getAttribute('value');
  }

  async setIsThrownInput(isThrown) {
    await this.isThrownInput.sendKeys(isThrown);
  }

  async getIsThrownInput() {
    return await this.isThrownInput.getAttribute('value');
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

export class MEncountersCutDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEncountersCut-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEncountersCut'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
