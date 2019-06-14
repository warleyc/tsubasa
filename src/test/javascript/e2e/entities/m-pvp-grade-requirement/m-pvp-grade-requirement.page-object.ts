import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPvpGradeRequirementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-pvp-grade-requirement div table .btn-danger'));
  title = element.all(by.css('jhi-m-pvp-grade-requirement div h2#page-heading span')).first();

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

export class MPvpGradeRequirementUpdatePage {
  pageTitle = element(by.id('jhi-m-pvp-grade-requirement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  upDescriptionInput = element(by.id('field_upDescription'));
  downDescriptionInput = element(by.id('field_downDescription'));
  targetTypeInput = element(by.id('field_targetType'));
  targetWaveInput = element(by.id('field_targetWave'));
  targetLowerGradeInput = element(by.id('field_targetLowerGrade'));
  targetPointInput = element(by.id('field_targetPoint'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setUpDescriptionInput(upDescription) {
    await this.upDescriptionInput.sendKeys(upDescription);
  }

  async getUpDescriptionInput() {
    return await this.upDescriptionInput.getAttribute('value');
  }

  async setDownDescriptionInput(downDescription) {
    await this.downDescriptionInput.sendKeys(downDescription);
  }

  async getDownDescriptionInput() {
    return await this.downDescriptionInput.getAttribute('value');
  }

  async setTargetTypeInput(targetType) {
    await this.targetTypeInput.sendKeys(targetType);
  }

  async getTargetTypeInput() {
    return await this.targetTypeInput.getAttribute('value');
  }

  async setTargetWaveInput(targetWave) {
    await this.targetWaveInput.sendKeys(targetWave);
  }

  async getTargetWaveInput() {
    return await this.targetWaveInput.getAttribute('value');
  }

  async setTargetLowerGradeInput(targetLowerGrade) {
    await this.targetLowerGradeInput.sendKeys(targetLowerGrade);
  }

  async getTargetLowerGradeInput() {
    return await this.targetLowerGradeInput.getAttribute('value');
  }

  async setTargetPointInput(targetPoint) {
    await this.targetPointInput.sendKeys(targetPoint);
  }

  async getTargetPointInput() {
    return await this.targetPointInput.getAttribute('value');
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

export class MPvpGradeRequirementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPvpGradeRequirement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPvpGradeRequirement'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
