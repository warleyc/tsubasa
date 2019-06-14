import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MModelQuestStageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-model-quest-stage div table .btn-danger'));
  title = element.all(by.css('jhi-m-model-quest-stage div h2#page-heading span')).first();

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

export class MModelQuestStageUpdatePage {
  pageTitle = element(by.id('jhi-m-model-quest-stage-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  stageIdInput = element(by.id('field_stageId'));
  imageInput = element(by.id('field_image'));
  modelNameInput = element(by.id('field_modelName'));
  bgmOffencingInput = element(by.id('field_bgmOffencing'));
  bgmDefencingInput = element(by.id('field_bgmDefencing'));
  bgmHurryingInput = element(by.id('field_bgmHurrying'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setStageIdInput(stageId) {
    await this.stageIdInput.sendKeys(stageId);
  }

  async getStageIdInput() {
    return await this.stageIdInput.getAttribute('value');
  }

  async setImageInput(image) {
    await this.imageInput.sendKeys(image);
  }

  async getImageInput() {
    return await this.imageInput.getAttribute('value');
  }

  async setModelNameInput(modelName) {
    await this.modelNameInput.sendKeys(modelName);
  }

  async getModelNameInput() {
    return await this.modelNameInput.getAttribute('value');
  }

  async setBgmOffencingInput(bgmOffencing) {
    await this.bgmOffencingInput.sendKeys(bgmOffencing);
  }

  async getBgmOffencingInput() {
    return await this.bgmOffencingInput.getAttribute('value');
  }

  async setBgmDefencingInput(bgmDefencing) {
    await this.bgmDefencingInput.sendKeys(bgmDefencing);
  }

  async getBgmDefencingInput() {
    return await this.bgmDefencingInput.getAttribute('value');
  }

  async setBgmHurryingInput(bgmHurrying) {
    await this.bgmHurryingInput.sendKeys(bgmHurrying);
  }

  async getBgmHurryingInput() {
    return await this.bgmHurryingInput.getAttribute('value');
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

export class MModelQuestStageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mModelQuestStage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mModelQuestStage'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
