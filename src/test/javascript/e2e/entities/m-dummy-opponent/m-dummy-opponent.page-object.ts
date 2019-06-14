import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDummyOpponentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-dummy-opponent div table .btn-danger'));
  title = element.all(by.css('jhi-m-dummy-opponent div h2#page-heading span')).first();

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

export class MDummyOpponentUpdatePage {
  pageTitle = element(by.id('jhi-m-dummy-opponent-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  npcDeckIdInput = element(by.id('field_npcDeckId'));
  totalParameterInput = element(by.id('field_totalParameter'));
  nameInput = element(by.id('field_name'));
  rankInput = element(by.id('field_rank'));
  emblemIdInput = element(by.id('field_emblemId'));
  fpUniformUpIdInput = element(by.id('field_fpUniformUpId'));
  fpUniformUpColorInput = element(by.id('field_fpUniformUpColor'));
  fpUniformBottomIdInput = element(by.id('field_fpUniformBottomId'));
  fpUniformBottomColorInput = element(by.id('field_fpUniformBottomColor'));
  gkUniformUpIdInput = element(by.id('field_gkUniformUpId'));
  gkUniformUpColorInput = element(by.id('field_gkUniformUpColor'));
  gkUniformBottomIdInput = element(by.id('field_gkUniformBottomId'));
  gkUniformBottomColorInput = element(by.id('field_gkUniformBottomColor'));
  mnpcdeckSelect = element(by.id('field_mnpcdeck'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNpcDeckIdInput(npcDeckId) {
    await this.npcDeckIdInput.sendKeys(npcDeckId);
  }

  async getNpcDeckIdInput() {
    return await this.npcDeckIdInput.getAttribute('value');
  }

  async setTotalParameterInput(totalParameter) {
    await this.totalParameterInput.sendKeys(totalParameter);
  }

  async getTotalParameterInput() {
    return await this.totalParameterInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setRankInput(rank) {
    await this.rankInput.sendKeys(rank);
  }

  async getRankInput() {
    return await this.rankInput.getAttribute('value');
  }

  async setEmblemIdInput(emblemId) {
    await this.emblemIdInput.sendKeys(emblemId);
  }

  async getEmblemIdInput() {
    return await this.emblemIdInput.getAttribute('value');
  }

  async setFpUniformUpIdInput(fpUniformUpId) {
    await this.fpUniformUpIdInput.sendKeys(fpUniformUpId);
  }

  async getFpUniformUpIdInput() {
    return await this.fpUniformUpIdInput.getAttribute('value');
  }

  async setFpUniformUpColorInput(fpUniformUpColor) {
    await this.fpUniformUpColorInput.sendKeys(fpUniformUpColor);
  }

  async getFpUniformUpColorInput() {
    return await this.fpUniformUpColorInput.getAttribute('value');
  }

  async setFpUniformBottomIdInput(fpUniformBottomId) {
    await this.fpUniformBottomIdInput.sendKeys(fpUniformBottomId);
  }

  async getFpUniformBottomIdInput() {
    return await this.fpUniformBottomIdInput.getAttribute('value');
  }

  async setFpUniformBottomColorInput(fpUniformBottomColor) {
    await this.fpUniformBottomColorInput.sendKeys(fpUniformBottomColor);
  }

  async getFpUniformBottomColorInput() {
    return await this.fpUniformBottomColorInput.getAttribute('value');
  }

  async setGkUniformUpIdInput(gkUniformUpId) {
    await this.gkUniformUpIdInput.sendKeys(gkUniformUpId);
  }

  async getGkUniformUpIdInput() {
    return await this.gkUniformUpIdInput.getAttribute('value');
  }

  async setGkUniformUpColorInput(gkUniformUpColor) {
    await this.gkUniformUpColorInput.sendKeys(gkUniformUpColor);
  }

  async getGkUniformUpColorInput() {
    return await this.gkUniformUpColorInput.getAttribute('value');
  }

  async setGkUniformBottomIdInput(gkUniformBottomId) {
    await this.gkUniformBottomIdInput.sendKeys(gkUniformBottomId);
  }

  async getGkUniformBottomIdInput() {
    return await this.gkUniformBottomIdInput.getAttribute('value');
  }

  async setGkUniformBottomColorInput(gkUniformBottomColor) {
    await this.gkUniformBottomColorInput.sendKeys(gkUniformBottomColor);
  }

  async getGkUniformBottomColorInput() {
    return await this.gkUniformBottomColorInput.getAttribute('value');
  }

  async mnpcdeckSelectLastOption(timeout?: number) {
    await this.mnpcdeckSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mnpcdeckSelectOption(option) {
    await this.mnpcdeckSelect.sendKeys(option);
  }

  getMnpcdeckSelect(): ElementFinder {
    return this.mnpcdeckSelect;
  }

  async getMnpcdeckSelectedOption() {
    return await this.mnpcdeckSelect.element(by.css('option:checked')).getText();
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

export class MDummyOpponentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDummyOpponent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDummyOpponent'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
