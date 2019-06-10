import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionBeforeShootCutInPatternComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-before-shoot-cut-in-pattern div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-before-shoot-cut-in-pattern div h2#page-heading span')).first();

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

export class MGachaRenditionBeforeShootCutInPatternUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-before-shoot-cut-in-pattern-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  isManySsrInput = element(by.id('field_isManySsr'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  patternInput = element(by.id('field_pattern'));
  normalPrefabNameInput = element(by.id('field_normalPrefabName'));
  flashBackPrefabName1Input = element(by.id('field_flashBackPrefabName1'));
  flashBackPrefabName2Input = element(by.id('field_flashBackPrefabName2'));
  flashBackPrefabName3Input = element(by.id('field_flashBackPrefabName3'));
  flashBackPrefabName4Input = element(by.id('field_flashBackPrefabName4'));
  voicePrefixInput = element(by.id('field_voicePrefix'));
  seNormalInput = element(by.id('field_seNormal'));
  seFlashBackInput = element(by.id('field_seFlashBack'));
  exceptKickerIdInput = element(by.id('field_exceptKickerId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
  }

  async setIsManySsrInput(isManySsr) {
    await this.isManySsrInput.sendKeys(isManySsr);
  }

  async getIsManySsrInput() {
    return await this.isManySsrInput.getAttribute('value');
  }

  async setIsSsrInput(isSsr) {
    await this.isSsrInput.sendKeys(isSsr);
  }

  async getIsSsrInput() {
    return await this.isSsrInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setPatternInput(pattern) {
    await this.patternInput.sendKeys(pattern);
  }

  async getPatternInput() {
    return await this.patternInput.getAttribute('value');
  }

  async setNormalPrefabNameInput(normalPrefabName) {
    await this.normalPrefabNameInput.sendKeys(normalPrefabName);
  }

  async getNormalPrefabNameInput() {
    return await this.normalPrefabNameInput.getAttribute('value');
  }

  async setFlashBackPrefabName1Input(flashBackPrefabName1) {
    await this.flashBackPrefabName1Input.sendKeys(flashBackPrefabName1);
  }

  async getFlashBackPrefabName1Input() {
    return await this.flashBackPrefabName1Input.getAttribute('value');
  }

  async setFlashBackPrefabName2Input(flashBackPrefabName2) {
    await this.flashBackPrefabName2Input.sendKeys(flashBackPrefabName2);
  }

  async getFlashBackPrefabName2Input() {
    return await this.flashBackPrefabName2Input.getAttribute('value');
  }

  async setFlashBackPrefabName3Input(flashBackPrefabName3) {
    await this.flashBackPrefabName3Input.sendKeys(flashBackPrefabName3);
  }

  async getFlashBackPrefabName3Input() {
    return await this.flashBackPrefabName3Input.getAttribute('value');
  }

  async setFlashBackPrefabName4Input(flashBackPrefabName4) {
    await this.flashBackPrefabName4Input.sendKeys(flashBackPrefabName4);
  }

  async getFlashBackPrefabName4Input() {
    return await this.flashBackPrefabName4Input.getAttribute('value');
  }

  async setVoicePrefixInput(voicePrefix) {
    await this.voicePrefixInput.sendKeys(voicePrefix);
  }

  async getVoicePrefixInput() {
    return await this.voicePrefixInput.getAttribute('value');
  }

  async setSeNormalInput(seNormal) {
    await this.seNormalInput.sendKeys(seNormal);
  }

  async getSeNormalInput() {
    return await this.seNormalInput.getAttribute('value');
  }

  async setSeFlashBackInput(seFlashBack) {
    await this.seFlashBackInput.sendKeys(seFlashBack);
  }

  async getSeFlashBackInput() {
    return await this.seFlashBackInput.getAttribute('value');
  }

  async setExceptKickerIdInput(exceptKickerId) {
    await this.exceptKickerIdInput.sendKeys(exceptKickerId);
  }

  async getExceptKickerIdInput() {
    return await this.exceptKickerIdInput.getAttribute('value');
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

export class MGachaRenditionBeforeShootCutInPatternDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionBeforeShootCutInPattern-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionBeforeShootCutInPattern'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
