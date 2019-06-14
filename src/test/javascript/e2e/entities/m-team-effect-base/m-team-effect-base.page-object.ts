import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTeamEffectBaseComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-team-effect-base div table .btn-danger'));
  title = element.all(by.css('jhi-m-team-effect-base div h2#page-heading span')).first();

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

export class MTeamEffectBaseUpdatePage {
  pageTitle = element(by.id('jhi-m-team-effect-base-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  rarityInput = element(by.id('field_rarity'));
  effectValueMinInput = element(by.id('field_effectValueMin'));
  effectValueMaxInput = element(by.id('field_effectValueMax'));
  triggerProbabilityMinInput = element(by.id('field_triggerProbabilityMin'));
  triggerProbabilityMaxInput = element(by.id('field_triggerProbabilityMax'));
  effectIdInput = element(by.id('field_effectId'));
  effectValueMin2Input = element(by.id('field_effectValueMin2'));
  effectValueMax2Input = element(by.id('field_effectValueMax2'));
  triggerProbabilityMin2Input = element(by.id('field_triggerProbabilityMin2'));
  triggerProbabilityMax2Input = element(by.id('field_triggerProbabilityMax2'));
  effectId2Input = element(by.id('field_effectId2'));
  descriptionInput = element(by.id('field_description'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setEffectValueMinInput(effectValueMin) {
    await this.effectValueMinInput.sendKeys(effectValueMin);
  }

  async getEffectValueMinInput() {
    return await this.effectValueMinInput.getAttribute('value');
  }

  async setEffectValueMaxInput(effectValueMax) {
    await this.effectValueMaxInput.sendKeys(effectValueMax);
  }

  async getEffectValueMaxInput() {
    return await this.effectValueMaxInput.getAttribute('value');
  }

  async setTriggerProbabilityMinInput(triggerProbabilityMin) {
    await this.triggerProbabilityMinInput.sendKeys(triggerProbabilityMin);
  }

  async getTriggerProbabilityMinInput() {
    return await this.triggerProbabilityMinInput.getAttribute('value');
  }

  async setTriggerProbabilityMaxInput(triggerProbabilityMax) {
    await this.triggerProbabilityMaxInput.sendKeys(triggerProbabilityMax);
  }

  async getTriggerProbabilityMaxInput() {
    return await this.triggerProbabilityMaxInput.getAttribute('value');
  }

  async setEffectIdInput(effectId) {
    await this.effectIdInput.sendKeys(effectId);
  }

  async getEffectIdInput() {
    return await this.effectIdInput.getAttribute('value');
  }

  async setEffectValueMin2Input(effectValueMin2) {
    await this.effectValueMin2Input.sendKeys(effectValueMin2);
  }

  async getEffectValueMin2Input() {
    return await this.effectValueMin2Input.getAttribute('value');
  }

  async setEffectValueMax2Input(effectValueMax2) {
    await this.effectValueMax2Input.sendKeys(effectValueMax2);
  }

  async getEffectValueMax2Input() {
    return await this.effectValueMax2Input.getAttribute('value');
  }

  async setTriggerProbabilityMin2Input(triggerProbabilityMin2) {
    await this.triggerProbabilityMin2Input.sendKeys(triggerProbabilityMin2);
  }

  async getTriggerProbabilityMin2Input() {
    return await this.triggerProbabilityMin2Input.getAttribute('value');
  }

  async setTriggerProbabilityMax2Input(triggerProbabilityMax2) {
    await this.triggerProbabilityMax2Input.sendKeys(triggerProbabilityMax2);
  }

  async getTriggerProbabilityMax2Input() {
    return await this.triggerProbabilityMax2Input.getAttribute('value');
  }

  async setEffectId2Input(effectId2) {
    await this.effectId2Input.sendKeys(effectId2);
  }

  async getEffectId2Input() {
    return await this.effectId2Input.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
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

export class MTeamEffectBaseDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTeamEffectBase-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTeamEffectBase'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
