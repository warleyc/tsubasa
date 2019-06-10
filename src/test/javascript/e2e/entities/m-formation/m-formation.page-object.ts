import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MFormationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-formation div table .btn-danger'));
  title = element.all(by.css('jhi-m-formation div h2#page-heading span')).first();

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

export class MFormationUpdatePage {
  pageTitle = element(by.id('jhi-m-formation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  effectValueInput = element(by.id('field_effectValue'));
  effectProbabilityInput = element(by.id('field_effectProbability'));
  formationArrangementFwInput = element(by.id('field_formationArrangementFw'));
  formationArrangementOmfInput = element(by.id('field_formationArrangementOmf'));
  formationArrangementDmfInput = element(by.id('field_formationArrangementDmf'));
  formationArrangementDfInput = element(by.id('field_formationArrangementDf'));
  effectIdInput = element(by.id('field_effectId'));
  descriptionInput = element(by.id('field_description'));
  shortDescriptionInput = element(by.id('field_shortDescription'));
  nameInput = element(by.id('field_name'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  startingUniformNosInput = element(by.id('field_startingUniformNos'));
  subUniformNosInput = element(by.id('field_subUniformNos'));
  exTypeInput = element(by.id('field_exType'));
  matchFormationIdInput = element(by.id('field_matchFormationId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEffectValueInput(effectValue) {
    await this.effectValueInput.sendKeys(effectValue);
  }

  async getEffectValueInput() {
    return await this.effectValueInput.getAttribute('value');
  }

  async setEffectProbabilityInput(effectProbability) {
    await this.effectProbabilityInput.sendKeys(effectProbability);
  }

  async getEffectProbabilityInput() {
    return await this.effectProbabilityInput.getAttribute('value');
  }

  async setFormationArrangementFwInput(formationArrangementFw) {
    await this.formationArrangementFwInput.sendKeys(formationArrangementFw);
  }

  async getFormationArrangementFwInput() {
    return await this.formationArrangementFwInput.getAttribute('value');
  }

  async setFormationArrangementOmfInput(formationArrangementOmf) {
    await this.formationArrangementOmfInput.sendKeys(formationArrangementOmf);
  }

  async getFormationArrangementOmfInput() {
    return await this.formationArrangementOmfInput.getAttribute('value');
  }

  async setFormationArrangementDmfInput(formationArrangementDmf) {
    await this.formationArrangementDmfInput.sendKeys(formationArrangementDmf);
  }

  async getFormationArrangementDmfInput() {
    return await this.formationArrangementDmfInput.getAttribute('value');
  }

  async setFormationArrangementDfInput(formationArrangementDf) {
    await this.formationArrangementDfInput.sendKeys(formationArrangementDf);
  }

  async getFormationArrangementDfInput() {
    return await this.formationArrangementDfInput.getAttribute('value');
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

  async setShortDescriptionInput(shortDescription) {
    await this.shortDescriptionInput.sendKeys(shortDescription);
  }

  async getShortDescriptionInput() {
    return await this.shortDescriptionInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
  }

  async setStartingUniformNosInput(startingUniformNos) {
    await this.startingUniformNosInput.sendKeys(startingUniformNos);
  }

  async getStartingUniformNosInput() {
    return await this.startingUniformNosInput.getAttribute('value');
  }

  async setSubUniformNosInput(subUniformNos) {
    await this.subUniformNosInput.sendKeys(subUniformNos);
  }

  async getSubUniformNosInput() {
    return await this.subUniformNosInput.getAttribute('value');
  }

  async setExTypeInput(exType) {
    await this.exTypeInput.sendKeys(exType);
  }

  async getExTypeInput() {
    return await this.exTypeInput.getAttribute('value');
  }

  async setMatchFormationIdInput(matchFormationId) {
    await this.matchFormationIdInput.sendKeys(matchFormationId);
  }

  async getMatchFormationIdInput() {
    return await this.matchFormationIdInput.getAttribute('value');
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

export class MFormationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mFormation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mFormation'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
