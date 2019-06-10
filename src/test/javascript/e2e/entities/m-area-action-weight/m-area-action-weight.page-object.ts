import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MAreaActionWeightComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-area-action-weight div table .btn-danger'));
  title = element.all(by.css('jhi-m-area-action-weight div h2#page-heading span')).first();

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

export class MAreaActionWeightUpdatePage {
  pageTitle = element(by.id('jhi-m-area-action-weight-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  areaTypeInput = element(by.id('field_areaType'));
  dribbleRateInput = element(by.id('field_dribbleRate'));
  passingRateInput = element(by.id('field_passingRate'));
  onetwoRateInput = element(by.id('field_onetwoRate'));
  shootRateInput = element(by.id('field_shootRate'));
  volleyShootRateInput = element(by.id('field_volleyShootRate'));
  headingShootRateInput = element(by.id('field_headingShootRate'));
  tackleRateInput = element(by.id('field_tackleRate'));
  blockRateInput = element(by.id('field_blockRate'));
  passCutRateInput = element(by.id('field_passCutRate'));
  clearRateInput = element(by.id('field_clearRate'));
  competeRateInput = element(by.id('field_competeRate'));
  trapRateInput = element(by.id('field_trapRate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAreaTypeInput(areaType) {
    await this.areaTypeInput.sendKeys(areaType);
  }

  async getAreaTypeInput() {
    return await this.areaTypeInput.getAttribute('value');
  }

  async setDribbleRateInput(dribbleRate) {
    await this.dribbleRateInput.sendKeys(dribbleRate);
  }

  async getDribbleRateInput() {
    return await this.dribbleRateInput.getAttribute('value');
  }

  async setPassingRateInput(passingRate) {
    await this.passingRateInput.sendKeys(passingRate);
  }

  async getPassingRateInput() {
    return await this.passingRateInput.getAttribute('value');
  }

  async setOnetwoRateInput(onetwoRate) {
    await this.onetwoRateInput.sendKeys(onetwoRate);
  }

  async getOnetwoRateInput() {
    return await this.onetwoRateInput.getAttribute('value');
  }

  async setShootRateInput(shootRate) {
    await this.shootRateInput.sendKeys(shootRate);
  }

  async getShootRateInput() {
    return await this.shootRateInput.getAttribute('value');
  }

  async setVolleyShootRateInput(volleyShootRate) {
    await this.volleyShootRateInput.sendKeys(volleyShootRate);
  }

  async getVolleyShootRateInput() {
    return await this.volleyShootRateInput.getAttribute('value');
  }

  async setHeadingShootRateInput(headingShootRate) {
    await this.headingShootRateInput.sendKeys(headingShootRate);
  }

  async getHeadingShootRateInput() {
    return await this.headingShootRateInput.getAttribute('value');
  }

  async setTackleRateInput(tackleRate) {
    await this.tackleRateInput.sendKeys(tackleRate);
  }

  async getTackleRateInput() {
    return await this.tackleRateInput.getAttribute('value');
  }

  async setBlockRateInput(blockRate) {
    await this.blockRateInput.sendKeys(blockRate);
  }

  async getBlockRateInput() {
    return await this.blockRateInput.getAttribute('value');
  }

  async setPassCutRateInput(passCutRate) {
    await this.passCutRateInput.sendKeys(passCutRate);
  }

  async getPassCutRateInput() {
    return await this.passCutRateInput.getAttribute('value');
  }

  async setClearRateInput(clearRate) {
    await this.clearRateInput.sendKeys(clearRate);
  }

  async getClearRateInput() {
    return await this.clearRateInput.getAttribute('value');
  }

  async setCompeteRateInput(competeRate) {
    await this.competeRateInput.sendKeys(competeRate);
  }

  async getCompeteRateInput() {
    return await this.competeRateInput.getAttribute('value');
  }

  async setTrapRateInput(trapRate) {
    await this.trapRateInput.sendKeys(trapRate);
  }

  async getTrapRateInput() {
    return await this.trapRateInput.getAttribute('value');
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

export class MAreaActionWeightDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAreaActionWeight-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAreaActionWeight'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
