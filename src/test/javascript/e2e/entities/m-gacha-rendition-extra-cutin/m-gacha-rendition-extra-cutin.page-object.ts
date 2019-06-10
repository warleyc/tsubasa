import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionExtraCutinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-extra-cutin div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-extra-cutin div h2#page-heading span')).first();

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

export class MGachaRenditionExtraCutinUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-extra-cutin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  renditionIdInput = element(by.id('field_renditionId'));
  mainPrefabNameInput = element(by.id('field_mainPrefabName'));
  voiceStartCutInInput = element(by.id('field_voiceStartCutIn'));
  serifInput = element(by.id('field_serif'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRenditionIdInput(renditionId) {
    await this.renditionIdInput.sendKeys(renditionId);
  }

  async getRenditionIdInput() {
    return await this.renditionIdInput.getAttribute('value');
  }

  async setMainPrefabNameInput(mainPrefabName) {
    await this.mainPrefabNameInput.sendKeys(mainPrefabName);
  }

  async getMainPrefabNameInput() {
    return await this.mainPrefabNameInput.getAttribute('value');
  }

  async setVoiceStartCutInInput(voiceStartCutIn) {
    await this.voiceStartCutInInput.sendKeys(voiceStartCutIn);
  }

  async getVoiceStartCutInInput() {
    return await this.voiceStartCutInInput.getAttribute('value');
  }

  async setSerifInput(serif) {
    await this.serifInput.sendKeys(serif);
  }

  async getSerifInput() {
    return await this.serifInput.getAttribute('value');
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

export class MGachaRenditionExtraCutinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionExtraCutin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionExtraCutin'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
