import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMedalComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-medal div table .btn-danger'));
  title = element.all(by.css('jhi-m-medal div h2#page-heading span')).first();

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

export class MMedalUpdatePage {
  pageTitle = element(by.id('jhi-m-medal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  medalTypeInput = element(by.id('field_medalType'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  maxAmountInput = element(by.id('field_maxAmount'));
  iconAssetNameInput = element(by.id('field_iconAssetName'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMedalTypeInput(medalType) {
    await this.medalTypeInput.sendKeys(medalType);
  }

  async getMedalTypeInput() {
    return await this.medalTypeInput.getAttribute('value');
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

  async setMaxAmountInput(maxAmount) {
    await this.maxAmountInput.sendKeys(maxAmount);
  }

  async getMaxAmountInput() {
    return await this.maxAmountInput.getAttribute('value');
  }

  async setIconAssetNameInput(iconAssetName) {
    await this.iconAssetNameInput.sendKeys(iconAssetName);
  }

  async getIconAssetNameInput() {
    return await this.iconAssetNameInput.getAttribute('value');
  }

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
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

export class MMedalDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMedal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMedal'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
