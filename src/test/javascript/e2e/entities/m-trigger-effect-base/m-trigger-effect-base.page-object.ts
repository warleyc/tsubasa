import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTriggerEffectBaseComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-trigger-effect-base div table .btn-danger'));
  title = element.all(by.css('jhi-m-trigger-effect-base div h2#page-heading span')).first();

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

export class MTriggerEffectBaseUpdatePage {
  pageTitle = element(by.id('jhi-m-trigger-effect-base-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  rarityInput = element(by.id('field_rarity'));
  effectValueInput = element(by.id('field_effectValue'));
  triggerProbabilityInput = element(by.id('field_triggerProbability'));
  targetCardParameterInput = element(by.id('field_targetCardParameter'));
  effectIdInput = element(by.id('field_effectId'));
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

  async setEffectValueInput(effectValue) {
    await this.effectValueInput.sendKeys(effectValue);
  }

  async getEffectValueInput() {
    return await this.effectValueInput.getAttribute('value');
  }

  async setTriggerProbabilityInput(triggerProbability) {
    await this.triggerProbabilityInput.sendKeys(triggerProbability);
  }

  async getTriggerProbabilityInput() {
    return await this.triggerProbabilityInput.getAttribute('value');
  }

  async setTargetCardParameterInput(targetCardParameter) {
    await this.targetCardParameterInput.sendKeys(targetCardParameter);
  }

  async getTargetCardParameterInput() {
    return await this.targetCardParameterInput.getAttribute('value');
  }

  async setEffectIdInput(effectId) {
    await this.effectIdInput.sendKeys(effectId);
  }

  async getEffectIdInput() {
    return await this.effectIdInput.getAttribute('value');
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

export class MTriggerEffectBaseDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTriggerEffectBase-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTriggerEffectBase'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
