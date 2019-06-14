import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMatchOptionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-match-option div table .btn-danger'));
  title = element.all(by.css('jhi-m-match-option div h2#page-heading span')).first();

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

export class MMatchOptionUpdatePage {
  pageTitle = element(by.id('jhi-m-match-option-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  passiveEffectIdInput = element(by.id('field_passiveEffectId'));
  passiveEffectValueInput = element(by.id('field_passiveEffectValue'));
  activateFullPowerTypeInput = element(by.id('field_activateFullPowerType'));
  attributeMagnificationInput = element(by.id('field_attributeMagnification'));
  useStaminaMagnificationInput = element(by.id('field_useStaminaMagnification'));
  isIgnoreTeamSkillInput = element(by.id('field_isIgnoreTeamSkill'));
  staminaInfinityTypeInput = element(by.id('field_staminaInfinityType'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPassiveEffectIdInput(passiveEffectId) {
    await this.passiveEffectIdInput.sendKeys(passiveEffectId);
  }

  async getPassiveEffectIdInput() {
    return await this.passiveEffectIdInput.getAttribute('value');
  }

  async setPassiveEffectValueInput(passiveEffectValue) {
    await this.passiveEffectValueInput.sendKeys(passiveEffectValue);
  }

  async getPassiveEffectValueInput() {
    return await this.passiveEffectValueInput.getAttribute('value');
  }

  async setActivateFullPowerTypeInput(activateFullPowerType) {
    await this.activateFullPowerTypeInput.sendKeys(activateFullPowerType);
  }

  async getActivateFullPowerTypeInput() {
    return await this.activateFullPowerTypeInput.getAttribute('value');
  }

  async setAttributeMagnificationInput(attributeMagnification) {
    await this.attributeMagnificationInput.sendKeys(attributeMagnification);
  }

  async getAttributeMagnificationInput() {
    return await this.attributeMagnificationInput.getAttribute('value');
  }

  async setUseStaminaMagnificationInput(useStaminaMagnification) {
    await this.useStaminaMagnificationInput.sendKeys(useStaminaMagnification);
  }

  async getUseStaminaMagnificationInput() {
    return await this.useStaminaMagnificationInput.getAttribute('value');
  }

  async setIsIgnoreTeamSkillInput(isIgnoreTeamSkill) {
    await this.isIgnoreTeamSkillInput.sendKeys(isIgnoreTeamSkill);
  }

  async getIsIgnoreTeamSkillInput() {
    return await this.isIgnoreTeamSkillInput.getAttribute('value');
  }

  async setStaminaInfinityTypeInput(staminaInfinityType) {
    await this.staminaInfinityTypeInput.sendKeys(staminaInfinityType);
  }

  async getStaminaInfinityTypeInput() {
    return await this.staminaInfinityTypeInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MMatchOptionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMatchOption-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMatchOption'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
