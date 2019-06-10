import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEncountersBonusComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-encounters-bonus div table .btn-danger'));
  title = element.all(by.css('jhi-m-encounters-bonus div h2#page-heading span')).first();

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

export class MEncountersBonusUpdatePage {
  pageTitle = element(by.id('jhi-m-encounters-bonus-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  encountersTypeInput = element(by.id('field_encountersType'));
  isSkillSuccessInput = element(by.id('field_isSkillSuccess'));
  thresholdInput = element(by.id('field_threshold'));
  probabilityBonusValueInput = element(by.id('field_probabilityBonusValue'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEncountersTypeInput(encountersType) {
    await this.encountersTypeInput.sendKeys(encountersType);
  }

  async getEncountersTypeInput() {
    return await this.encountersTypeInput.getAttribute('value');
  }

  async setIsSkillSuccessInput(isSkillSuccess) {
    await this.isSkillSuccessInput.sendKeys(isSkillSuccess);
  }

  async getIsSkillSuccessInput() {
    return await this.isSkillSuccessInput.getAttribute('value');
  }

  async setThresholdInput(threshold) {
    await this.thresholdInput.sendKeys(threshold);
  }

  async getThresholdInput() {
    return await this.thresholdInput.getAttribute('value');
  }

  async setProbabilityBonusValueInput(probabilityBonusValue) {
    await this.probabilityBonusValueInput.sendKeys(probabilityBonusValue);
  }

  async getProbabilityBonusValueInput() {
    return await this.probabilityBonusValueInput.getAttribute('value');
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

export class MEncountersBonusDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEncountersBonus-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEncountersBonus'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
