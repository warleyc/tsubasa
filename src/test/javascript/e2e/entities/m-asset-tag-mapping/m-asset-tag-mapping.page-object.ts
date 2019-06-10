import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MAssetTagMappingComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-asset-tag-mapping div table .btn-danger'));
  title = element.all(by.css('jhi-m-asset-tag-mapping div h2#page-heading span')).first();

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

export class MAssetTagMappingUpdatePage {
  pageTitle = element(by.id('jhi-m-asset-tag-mapping-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tagInput = element(by.id('field_tag'));
  idsInput = element(by.id('field_ids'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTagInput(tag) {
    await this.tagInput.sendKeys(tag);
  }

  async getTagInput() {
    return await this.tagInput.getAttribute('value');
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

export class MAssetTagMappingDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAssetTagMapping-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAssetTagMapping'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
