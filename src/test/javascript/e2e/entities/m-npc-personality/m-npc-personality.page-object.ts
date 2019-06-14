import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MNpcPersonalityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-npc-personality div table .btn-danger'));
  title = element.all(by.css('jhi-m-npc-personality div h2#page-heading span')).first();

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

export class MNpcPersonalityUpdatePage {
  pageTitle = element(by.id('jhi-m-npc-personality-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  passingTargetRateInput = element(by.id('field_passingTargetRate'));
  actionSkillRateInput = element(by.id('field_actionSkillRate'));
  dribbleMagnificationInput = element(by.id('field_dribbleMagnification'));
  passingMagnificationInput = element(by.id('field_passingMagnification'));
  onetwoMagnificationInput = element(by.id('field_onetwoMagnification'));
  shootMagnificationInput = element(by.id('field_shootMagnification'));
  volleyShootMagnificationInput = element(by.id('field_volleyShootMagnification'));
  headingShootMagnificationInput = element(by.id('field_headingShootMagnification'));
  tackleMagnificationInput = element(by.id('field_tackleMagnification'));
  blockMagnificationInput = element(by.id('field_blockMagnification'));
  passCutMagnificationInput = element(by.id('field_passCutMagnification'));
  clearMagnificationInput = element(by.id('field_clearMagnification'));
  competeMagnificationInput = element(by.id('field_competeMagnification'));
  trapMagnificationInput = element(by.id('field_trapMagnification'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPassingTargetRateInput(passingTargetRate) {
    await this.passingTargetRateInput.sendKeys(passingTargetRate);
  }

  async getPassingTargetRateInput() {
    return await this.passingTargetRateInput.getAttribute('value');
  }

  async setActionSkillRateInput(actionSkillRate) {
    await this.actionSkillRateInput.sendKeys(actionSkillRate);
  }

  async getActionSkillRateInput() {
    return await this.actionSkillRateInput.getAttribute('value');
  }

  async setDribbleMagnificationInput(dribbleMagnification) {
    await this.dribbleMagnificationInput.sendKeys(dribbleMagnification);
  }

  async getDribbleMagnificationInput() {
    return await this.dribbleMagnificationInput.getAttribute('value');
  }

  async setPassingMagnificationInput(passingMagnification) {
    await this.passingMagnificationInput.sendKeys(passingMagnification);
  }

  async getPassingMagnificationInput() {
    return await this.passingMagnificationInput.getAttribute('value');
  }

  async setOnetwoMagnificationInput(onetwoMagnification) {
    await this.onetwoMagnificationInput.sendKeys(onetwoMagnification);
  }

  async getOnetwoMagnificationInput() {
    return await this.onetwoMagnificationInput.getAttribute('value');
  }

  async setShootMagnificationInput(shootMagnification) {
    await this.shootMagnificationInput.sendKeys(shootMagnification);
  }

  async getShootMagnificationInput() {
    return await this.shootMagnificationInput.getAttribute('value');
  }

  async setVolleyShootMagnificationInput(volleyShootMagnification) {
    await this.volleyShootMagnificationInput.sendKeys(volleyShootMagnification);
  }

  async getVolleyShootMagnificationInput() {
    return await this.volleyShootMagnificationInput.getAttribute('value');
  }

  async setHeadingShootMagnificationInput(headingShootMagnification) {
    await this.headingShootMagnificationInput.sendKeys(headingShootMagnification);
  }

  async getHeadingShootMagnificationInput() {
    return await this.headingShootMagnificationInput.getAttribute('value');
  }

  async setTackleMagnificationInput(tackleMagnification) {
    await this.tackleMagnificationInput.sendKeys(tackleMagnification);
  }

  async getTackleMagnificationInput() {
    return await this.tackleMagnificationInput.getAttribute('value');
  }

  async setBlockMagnificationInput(blockMagnification) {
    await this.blockMagnificationInput.sendKeys(blockMagnification);
  }

  async getBlockMagnificationInput() {
    return await this.blockMagnificationInput.getAttribute('value');
  }

  async setPassCutMagnificationInput(passCutMagnification) {
    await this.passCutMagnificationInput.sendKeys(passCutMagnification);
  }

  async getPassCutMagnificationInput() {
    return await this.passCutMagnificationInput.getAttribute('value');
  }

  async setClearMagnificationInput(clearMagnification) {
    await this.clearMagnificationInput.sendKeys(clearMagnification);
  }

  async getClearMagnificationInput() {
    return await this.clearMagnificationInput.getAttribute('value');
  }

  async setCompeteMagnificationInput(competeMagnification) {
    await this.competeMagnificationInput.sendKeys(competeMagnification);
  }

  async getCompeteMagnificationInput() {
    return await this.competeMagnificationInput.getAttribute('value');
  }

  async setTrapMagnificationInput(trapMagnification) {
    await this.trapMagnificationInput.sendKeys(trapMagnification);
  }

  async getTrapMagnificationInput() {
    return await this.trapMagnificationInput.getAttribute('value');
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

export class MNpcPersonalityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mNpcPersonality-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mNpcPersonality'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
