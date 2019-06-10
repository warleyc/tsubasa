import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEncountersCommandCompatibilityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-encounters-command-compatibility div table .btn-danger'));
  title = element.all(by.css('jhi-m-encounters-command-compatibility div h2#page-heading span')).first();

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

export class MEncountersCommandCompatibilityUpdatePage {
  pageTitle = element(by.id('jhi-m-encounters-command-compatibility-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  encountersTypeInput = element(by.id('field_encountersType'));
  offenseCommandTypeInput = element(by.id('field_offenseCommandType'));
  defenseCommandTypeInput = element(by.id('field_defenseCommandType'));
  offenseMagnificationInput = element(by.id('field_offenseMagnification'));
  defenseMagnificationInput = element(by.id('field_defenseMagnification'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEncountersTypeInput(encountersType) {
    await this.encountersTypeInput.sendKeys(encountersType);
  }

  async getEncountersTypeInput() {
    return await this.encountersTypeInput.getAttribute('value');
  }

  async setOffenseCommandTypeInput(offenseCommandType) {
    await this.offenseCommandTypeInput.sendKeys(offenseCommandType);
  }

  async getOffenseCommandTypeInput() {
    return await this.offenseCommandTypeInput.getAttribute('value');
  }

  async setDefenseCommandTypeInput(defenseCommandType) {
    await this.defenseCommandTypeInput.sendKeys(defenseCommandType);
  }

  async getDefenseCommandTypeInput() {
    return await this.defenseCommandTypeInput.getAttribute('value');
  }

  async setOffenseMagnificationInput(offenseMagnification) {
    await this.offenseMagnificationInput.sendKeys(offenseMagnification);
  }

  async getOffenseMagnificationInput() {
    return await this.offenseMagnificationInput.getAttribute('value');
  }

  async setDefenseMagnificationInput(defenseMagnification) {
    await this.defenseMagnificationInput.sendKeys(defenseMagnification);
  }

  async getDefenseMagnificationInput() {
    return await this.defenseMagnificationInput.getAttribute('value');
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

export class MEncountersCommandCompatibilityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEncountersCommandCompatibility-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEncountersCommandCompatibility'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
