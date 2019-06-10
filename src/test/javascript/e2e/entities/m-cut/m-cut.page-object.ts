import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCutComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-cut div table .btn-danger'));
  title = element.all(by.css('jhi-m-cut div h2#page-heading span')).first();

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

export class MCutUpdatePage {
  pageTitle = element(by.id('jhi-m-cut-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  fpAInput = element(by.id('field_fpA'));
  fpBInput = element(by.id('field_fpB'));
  fpCInput = element(by.id('field_fpC'));
  fpDInput = element(by.id('field_fpD'));
  fpEInput = element(by.id('field_fpE'));
  fpFInput = element(by.id('field_fpF'));
  gkAInput = element(by.id('field_gkA'));
  gkBInput = element(by.id('field_gkB'));
  showEnvironmentalEffectInput = element(by.id('field_showEnvironmentalEffect'));
  cutTypeInput = element(by.id('field_cutType'));
  groupIdInput = element(by.id('field_groupId'));
  isCombiCutInput = element(by.id('field_isCombiCut'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setFpAInput(fpA) {
    await this.fpAInput.sendKeys(fpA);
  }

  async getFpAInput() {
    return await this.fpAInput.getAttribute('value');
  }

  async setFpBInput(fpB) {
    await this.fpBInput.sendKeys(fpB);
  }

  async getFpBInput() {
    return await this.fpBInput.getAttribute('value');
  }

  async setFpCInput(fpC) {
    await this.fpCInput.sendKeys(fpC);
  }

  async getFpCInput() {
    return await this.fpCInput.getAttribute('value');
  }

  async setFpDInput(fpD) {
    await this.fpDInput.sendKeys(fpD);
  }

  async getFpDInput() {
    return await this.fpDInput.getAttribute('value');
  }

  async setFpEInput(fpE) {
    await this.fpEInput.sendKeys(fpE);
  }

  async getFpEInput() {
    return await this.fpEInput.getAttribute('value');
  }

  async setFpFInput(fpF) {
    await this.fpFInput.sendKeys(fpF);
  }

  async getFpFInput() {
    return await this.fpFInput.getAttribute('value');
  }

  async setGkAInput(gkA) {
    await this.gkAInput.sendKeys(gkA);
  }

  async getGkAInput() {
    return await this.gkAInput.getAttribute('value');
  }

  async setGkBInput(gkB) {
    await this.gkBInput.sendKeys(gkB);
  }

  async getGkBInput() {
    return await this.gkBInput.getAttribute('value');
  }

  async setShowEnvironmentalEffectInput(showEnvironmentalEffect) {
    await this.showEnvironmentalEffectInput.sendKeys(showEnvironmentalEffect);
  }

  async getShowEnvironmentalEffectInput() {
    return await this.showEnvironmentalEffectInput.getAttribute('value');
  }

  async setCutTypeInput(cutType) {
    await this.cutTypeInput.sendKeys(cutType);
  }

  async getCutTypeInput() {
    return await this.cutTypeInput.getAttribute('value');
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setIsCombiCutInput(isCombiCut) {
    await this.isCombiCutInput.sendKeys(isCombiCut);
  }

  async getIsCombiCutInput() {
    return await this.isCombiCutInput.getAttribute('value');
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

export class MCutDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCut-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCut'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
