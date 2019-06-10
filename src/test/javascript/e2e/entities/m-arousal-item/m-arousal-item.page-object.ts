import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MArousalItemComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-arousal-item div table .btn-danger'));
  title = element.all(by.css('jhi-m-arousal-item div h2#page-heading span')).first();

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

export class MArousalItemUpdatePage {
  pageTitle = element(by.id('jhi-m-arousal-item-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  thumbnailBgAssetNameInput = element(by.id('field_thumbnailBgAssetName'));
  thumbnailFrameAssetNameInput = element(by.id('field_thumbnailFrameAssetName'));
  arousalItemTypeInput = element(by.id('field_arousalItemType'));

  async getPageTitle() {
    return this.pageTitle.getText();
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

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
  }

  async setThumbnailBgAssetNameInput(thumbnailBgAssetName) {
    await this.thumbnailBgAssetNameInput.sendKeys(thumbnailBgAssetName);
  }

  async getThumbnailBgAssetNameInput() {
    return await this.thumbnailBgAssetNameInput.getAttribute('value');
  }

  async setThumbnailFrameAssetNameInput(thumbnailFrameAssetName) {
    await this.thumbnailFrameAssetNameInput.sendKeys(thumbnailFrameAssetName);
  }

  async getThumbnailFrameAssetNameInput() {
    return await this.thumbnailFrameAssetNameInput.getAttribute('value');
  }

  async setArousalItemTypeInput(arousalItemType) {
    await this.arousalItemTypeInput.sendKeys(arousalItemType);
  }

  async getArousalItemTypeInput() {
    return await this.arousalItemTypeInput.getAttribute('value');
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

export class MArousalItemDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mArousalItem-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mArousalItem'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
