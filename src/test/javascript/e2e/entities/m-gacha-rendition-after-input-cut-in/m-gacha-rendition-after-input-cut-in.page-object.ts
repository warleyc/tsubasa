import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionAfterInputCutInComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-after-input-cut-in div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-after-input-cut-in div h2#page-heading span')).first();

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

export class MGachaRenditionAfterInputCutInUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-after-input-cut-in-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  isSsrInput = element(by.id('field_isSsr'));
  weightInput = element(by.id('field_weight'));
  cutInBgPrefabNameInput = element(by.id('field_cutInBgPrefabName'));
  seStartCutInInput = element(by.id('field_seStartCutIn'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
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

  async setCutInBgPrefabNameInput(cutInBgPrefabName) {
    await this.cutInBgPrefabNameInput.sendKeys(cutInBgPrefabName);
  }

  async getCutInBgPrefabNameInput() {
    return await this.cutInBgPrefabNameInput.getAttribute('value');
  }

  async setSeStartCutInInput(seStartCutIn) {
    await this.seStartCutInInput.sendKeys(seStartCutIn);
  }

  async getSeStartCutInInput() {
    return await this.seStartCutInInput.getAttribute('value');
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

export class MGachaRenditionAfterInputCutInDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionAfterInputCutIn-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionAfterInputCutIn'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
