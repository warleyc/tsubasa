import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPassiveEffectRangeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-passive-effect-range div table .btn-danger'));
  title = element.all(by.css('jhi-m-passive-effect-range div h2#page-heading span')).first();

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

export class MPassiveEffectRangeUpdatePage {
  pageTitle = element(by.id('jhi-m-passive-effect-range-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  effectParamTypeInput = element(by.id('field_effectParamType'));
  targetPositionGkInput = element(by.id('field_targetPositionGk'));
  targetPositionFwInput = element(by.id('field_targetPositionFw'));
  targetPositionOmfInput = element(by.id('field_targetPositionOmf'));
  targetPositionDmfInput = element(by.id('field_targetPositionDmf'));
  targetPositionDfInput = element(by.id('field_targetPositionDf'));
  targetAttributeInput = element(by.id('field_targetAttribute'));
  targetNationalityGroupIdInput = element(by.id('field_targetNationalityGroupId'));
  targetTeamGroupIdInput = element(by.id('field_targetTeamGroupId'));
  targetPlayableCardGroupIdInput = element(by.id('field_targetPlayableCardGroupId'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setEffectParamTypeInput(effectParamType) {
    await this.effectParamTypeInput.sendKeys(effectParamType);
  }

  async getEffectParamTypeInput() {
    return await this.effectParamTypeInput.getAttribute('value');
  }

  async setTargetPositionGkInput(targetPositionGk) {
    await this.targetPositionGkInput.sendKeys(targetPositionGk);
  }

  async getTargetPositionGkInput() {
    return await this.targetPositionGkInput.getAttribute('value');
  }

  async setTargetPositionFwInput(targetPositionFw) {
    await this.targetPositionFwInput.sendKeys(targetPositionFw);
  }

  async getTargetPositionFwInput() {
    return await this.targetPositionFwInput.getAttribute('value');
  }

  async setTargetPositionOmfInput(targetPositionOmf) {
    await this.targetPositionOmfInput.sendKeys(targetPositionOmf);
  }

  async getTargetPositionOmfInput() {
    return await this.targetPositionOmfInput.getAttribute('value');
  }

  async setTargetPositionDmfInput(targetPositionDmf) {
    await this.targetPositionDmfInput.sendKeys(targetPositionDmf);
  }

  async getTargetPositionDmfInput() {
    return await this.targetPositionDmfInput.getAttribute('value');
  }

  async setTargetPositionDfInput(targetPositionDf) {
    await this.targetPositionDfInput.sendKeys(targetPositionDf);
  }

  async getTargetPositionDfInput() {
    return await this.targetPositionDfInput.getAttribute('value');
  }

  async setTargetAttributeInput(targetAttribute) {
    await this.targetAttributeInput.sendKeys(targetAttribute);
  }

  async getTargetAttributeInput() {
    return await this.targetAttributeInput.getAttribute('value');
  }

  async setTargetNationalityGroupIdInput(targetNationalityGroupId) {
    await this.targetNationalityGroupIdInput.sendKeys(targetNationalityGroupId);
  }

  async getTargetNationalityGroupIdInput() {
    return await this.targetNationalityGroupIdInput.getAttribute('value');
  }

  async setTargetTeamGroupIdInput(targetTeamGroupId) {
    await this.targetTeamGroupIdInput.sendKeys(targetTeamGroupId);
  }

  async getTargetTeamGroupIdInput() {
    return await this.targetTeamGroupIdInput.getAttribute('value');
  }

  async setTargetPlayableCardGroupIdInput(targetPlayableCardGroupId) {
    await this.targetPlayableCardGroupIdInput.sendKeys(targetPlayableCardGroupId);
  }

  async getTargetPlayableCardGroupIdInput() {
    return await this.targetPlayableCardGroupIdInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
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

export class MPassiveEffectRangeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPassiveEffectRange-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPassiveEffectRange'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
