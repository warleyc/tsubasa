import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildEffectComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-effect div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-effect div h2#page-heading span')).first();

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

export class MGuildEffectUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-effect-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  effectTypeInput = element(by.id('field_effectType'));
  nameInput = element(by.id('field_name'));
  effectIdInput = element(by.id('field_effectId'));
  thumbnailPathInput = element(by.id('field_thumbnailPath'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEffectTypeInput(effectType) {
    await this.effectTypeInput.sendKeys(effectType);
  }

  async getEffectTypeInput() {
    return await this.effectTypeInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setEffectIdInput(effectId) {
    await this.effectIdInput.sendKeys(effectId);
  }

  async getEffectIdInput() {
    return await this.effectIdInput.getAttribute('value');
  }

  async setThumbnailPathInput(thumbnailPath) {
    await this.thumbnailPathInput.sendKeys(thumbnailPath);
  }

  async getThumbnailPathInput() {
    return await this.thumbnailPathInput.getAttribute('value');
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

export class MGuildEffectDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildEffect-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildEffect'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
