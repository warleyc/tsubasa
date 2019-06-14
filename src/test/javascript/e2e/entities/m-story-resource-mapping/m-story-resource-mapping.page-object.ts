import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MStoryResourceMappingComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-story-resource-mapping div table .btn-danger'));
  title = element.all(by.css('jhi-m-story-resource-mapping div h2#page-heading span')).first();

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

export class MStoryResourceMappingUpdatePage {
  pageTitle = element(by.id('jhi-m-story-resource-mapping-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  langInput = element(by.id('field_lang'));
  scriptNameInput = element(by.id('field_scriptName'));
  idsInput = element(by.id('field_ids'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setLangInput(lang) {
    await this.langInput.sendKeys(lang);
  }

  async getLangInput() {
    return await this.langInput.getAttribute('value');
  }

  async setScriptNameInput(scriptName) {
    await this.scriptNameInput.sendKeys(scriptName);
  }

  async getScriptNameInput() {
    return await this.scriptNameInput.getAttribute('value');
  }

  async setIdsInput(ids) {
    await this.idsInput.sendKeys(ids);
  }

  async getIdsInput() {
    return await this.idsInput.getAttribute('value');
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

export class MStoryResourceMappingDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mStoryResourceMapping-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mStoryResourceMapping'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
