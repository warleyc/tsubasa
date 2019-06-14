import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaTicketComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-ticket div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-ticket div h2#page-heading span')).first();

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

export class MGachaTicketUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-ticket-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  descriptionInput = element(by.id('field_description'));
  maxAmountInput = element(by.id('field_maxAmount'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setMaxAmountInput(maxAmount) {
    await this.maxAmountInput.sendKeys(maxAmount);
  }

  async getMaxAmountInput() {
    return await this.maxAmountInput.getAttribute('value');
  }

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
  }

  async setEndAtInput(endAt) {
    await this.endAtInput.sendKeys(endAt);
  }

  async getEndAtInput() {
    return await this.endAtInput.getAttribute('value');
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

export class MGachaTicketDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaTicket-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaTicket'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
