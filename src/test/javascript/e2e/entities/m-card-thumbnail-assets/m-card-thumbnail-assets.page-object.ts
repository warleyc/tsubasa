import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCardThumbnailAssetsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-card-thumbnail-assets div table .btn-danger'));
  title = element.all(by.css('jhi-m-card-thumbnail-assets div h2#page-heading span')).first();

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

export class MCardThumbnailAssetsUpdatePage {
  pageTitle = element(by.id('jhi-m-card-thumbnail-assets-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  thumbnailFrameNameInput = element(by.id('field_thumbnailFrameName'));
  thumbnailBackgoundAssetNameInput = element(by.id('field_thumbnailBackgoundAssetName'));
  thumbnailEffectAssetNameInput = element(by.id('field_thumbnailEffectAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
  }

  async setThumbnailFrameNameInput(thumbnailFrameName) {
    await this.thumbnailFrameNameInput.sendKeys(thumbnailFrameName);
  }

  async getThumbnailFrameNameInput() {
    return await this.thumbnailFrameNameInput.getAttribute('value');
  }

  async setThumbnailBackgoundAssetNameInput(thumbnailBackgoundAssetName) {
    await this.thumbnailBackgoundAssetNameInput.sendKeys(thumbnailBackgoundAssetName);
  }

  async getThumbnailBackgoundAssetNameInput() {
    return await this.thumbnailBackgoundAssetNameInput.getAttribute('value');
  }

  async setThumbnailEffectAssetNameInput(thumbnailEffectAssetName) {
    await this.thumbnailEffectAssetNameInput.sendKeys(thumbnailEffectAssetName);
  }

  async getThumbnailEffectAssetNameInput() {
    return await this.thumbnailEffectAssetNameInput.getAttribute('value');
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

export class MCardThumbnailAssetsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCardThumbnailAssets-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCardThumbnailAssets'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
