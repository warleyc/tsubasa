import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MApRecoveryItemComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-ap-recovery-item div table .btn-danger'));
  title = element.all(by.css('jhi-m-ap-recovery-item div h2#page-heading span')).first();

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

export class MApRecoveryItemUpdatePage {
  pageTitle = element(by.id('jhi-m-ap-recovery-item-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  apRecoveryItemTypeInput = element(by.id('field_apRecoveryItemType'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setApRecoveryItemTypeInput(apRecoveryItemType) {
    await this.apRecoveryItemTypeInput.sendKeys(apRecoveryItemType);
  }

  async getApRecoveryItemTypeInput() {
    return await this.apRecoveryItemTypeInput.getAttribute('value');
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

export class MApRecoveryItemDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mApRecoveryItem-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mApRecoveryItem'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
