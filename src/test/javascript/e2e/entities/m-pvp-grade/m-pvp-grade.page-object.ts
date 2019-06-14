import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPvpGradeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-pvp-grade div table .btn-danger'));
  title = element.all(by.css('jhi-m-pvp-grade div h2#page-heading span')).first();

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

export class MPvpGradeUpdatePage {
  pageTitle = element(by.id('jhi-m-pvp-grade-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  waveIdInput = element(by.id('field_waveId'));
  gradeInput = element(by.id('field_grade'));
  isHigherInput = element(by.id('field_isHigher'));
  isLowerInput = element(by.id('field_isLower'));
  gradeNameInput = element(by.id('field_gradeName'));
  lookInput = element(by.id('field_look'));
  upRequirementIdInput = element(by.id('field_upRequirementId'));
  downRequirementIdInput = element(by.id('field_downRequirementId'));
  winPointInput = element(by.id('field_winPoint'));
  losePointInput = element(by.id('field_losePoint'));
  gradeUpSerifInput = element(by.id('field_gradeUpSerif'));
  gradeDownSerifInput = element(by.id('field_gradeDownSerif'));
  gradeUpCharaAssetNameInput = element(by.id('field_gradeUpCharaAssetName'));
  gradeDownCharaAssetNameInput = element(by.id('field_gradeDownCharaAssetName'));
  gradeUpVoiceCharaIdInput = element(by.id('field_gradeUpVoiceCharaId'));
  gradeDownVoiceCharaIdInput = element(by.id('field_gradeDownVoiceCharaId'));
  totalParameterRangeUpperInput = element(by.id('field_totalParameterRangeUpper'));
  totalParameterRangeLowerInput = element(by.id('field_totalParameterRangeLower'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWaveIdInput(waveId) {
    await this.waveIdInput.sendKeys(waveId);
  }

  async getWaveIdInput() {
    return await this.waveIdInput.getAttribute('value');
  }

  async setGradeInput(grade) {
    await this.gradeInput.sendKeys(grade);
  }

  async getGradeInput() {
    return await this.gradeInput.getAttribute('value');
  }

  async setIsHigherInput(isHigher) {
    await this.isHigherInput.sendKeys(isHigher);
  }

  async getIsHigherInput() {
    return await this.isHigherInput.getAttribute('value');
  }

  async setIsLowerInput(isLower) {
    await this.isLowerInput.sendKeys(isLower);
  }

  async getIsLowerInput() {
    return await this.isLowerInput.getAttribute('value');
  }

  async setGradeNameInput(gradeName) {
    await this.gradeNameInput.sendKeys(gradeName);
  }

  async getGradeNameInput() {
    return await this.gradeNameInput.getAttribute('value');
  }

  async setLookInput(look) {
    await this.lookInput.sendKeys(look);
  }

  async getLookInput() {
    return await this.lookInput.getAttribute('value');
  }

  async setUpRequirementIdInput(upRequirementId) {
    await this.upRequirementIdInput.sendKeys(upRequirementId);
  }

  async getUpRequirementIdInput() {
    return await this.upRequirementIdInput.getAttribute('value');
  }

  async setDownRequirementIdInput(downRequirementId) {
    await this.downRequirementIdInput.sendKeys(downRequirementId);
  }

  async getDownRequirementIdInput() {
    return await this.downRequirementIdInput.getAttribute('value');
  }

  async setWinPointInput(winPoint) {
    await this.winPointInput.sendKeys(winPoint);
  }

  async getWinPointInput() {
    return await this.winPointInput.getAttribute('value');
  }

  async setLosePointInput(losePoint) {
    await this.losePointInput.sendKeys(losePoint);
  }

  async getLosePointInput() {
    return await this.losePointInput.getAttribute('value');
  }

  async setGradeUpSerifInput(gradeUpSerif) {
    await this.gradeUpSerifInput.sendKeys(gradeUpSerif);
  }

  async getGradeUpSerifInput() {
    return await this.gradeUpSerifInput.getAttribute('value');
  }

  async setGradeDownSerifInput(gradeDownSerif) {
    await this.gradeDownSerifInput.sendKeys(gradeDownSerif);
  }

  async getGradeDownSerifInput() {
    return await this.gradeDownSerifInput.getAttribute('value');
  }

  async setGradeUpCharaAssetNameInput(gradeUpCharaAssetName) {
    await this.gradeUpCharaAssetNameInput.sendKeys(gradeUpCharaAssetName);
  }

  async getGradeUpCharaAssetNameInput() {
    return await this.gradeUpCharaAssetNameInput.getAttribute('value');
  }

  async setGradeDownCharaAssetNameInput(gradeDownCharaAssetName) {
    await this.gradeDownCharaAssetNameInput.sendKeys(gradeDownCharaAssetName);
  }

  async getGradeDownCharaAssetNameInput() {
    return await this.gradeDownCharaAssetNameInput.getAttribute('value');
  }

  async setGradeUpVoiceCharaIdInput(gradeUpVoiceCharaId) {
    await this.gradeUpVoiceCharaIdInput.sendKeys(gradeUpVoiceCharaId);
  }

  async getGradeUpVoiceCharaIdInput() {
    return await this.gradeUpVoiceCharaIdInput.getAttribute('value');
  }

  async setGradeDownVoiceCharaIdInput(gradeDownVoiceCharaId) {
    await this.gradeDownVoiceCharaIdInput.sendKeys(gradeDownVoiceCharaId);
  }

  async getGradeDownVoiceCharaIdInput() {
    return await this.gradeDownVoiceCharaIdInput.getAttribute('value');
  }

  async setTotalParameterRangeUpperInput(totalParameterRangeUpper) {
    await this.totalParameterRangeUpperInput.sendKeys(totalParameterRangeUpper);
  }

  async getTotalParameterRangeUpperInput() {
    return await this.totalParameterRangeUpperInput.getAttribute('value');
  }

  async setTotalParameterRangeLowerInput(totalParameterRangeLower) {
    await this.totalParameterRangeLowerInput.sendKeys(totalParameterRangeLower);
  }

  async getTotalParameterRangeLowerInput() {
    return await this.totalParameterRangeLowerInput.getAttribute('value');
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

export class MPvpGradeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPvpGrade-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPvpGrade'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
