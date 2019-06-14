import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MStampComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-stamp div table .btn-danger'));
  title = element.all(by.css('jhi-m-stamp div h2#page-heading span')).first();

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

export class MStampUpdatePage {
  pageTitle = element(by.id('jhi-m-stamp-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  thumbnailAssetInput = element(by.id('field_thumbnailAsset'));
  soundEventInput = element(by.id('field_soundEvent'));
  descriptionInput = element(by.id('field_description'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setThumbnailAssetInput(thumbnailAsset) {
    await this.thumbnailAssetInput.sendKeys(thumbnailAsset);
  }

  async getThumbnailAssetInput() {
    return await this.thumbnailAssetInput.getAttribute('value');
  }

  async setSoundEventInput(soundEvent) {
    await this.soundEventInput.sendKeys(soundEvent);
  }

  async getSoundEventInput() {
    return await this.soundEventInput.getAttribute('value');
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

export class MStampDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mStamp-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mStamp'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
