import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEmblemSetComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-emblem-set div table .btn-danger'));
  title = element.all(by.css('jhi-m-emblem-set div h2#page-heading span')).first();

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

export class MEmblemSetUpdatePage {
  pageTitle = element(by.id('jhi-m-emblem-set-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  assetNameInput = element(by.id('field_assetName'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
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

export class MEmblemSetDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEmblemSet-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEmblemSet'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
