import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDistributeParamPointComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-distribute-param-point div table .btn-danger'));
  title = element.all(by.css('jhi-m-distribute-param-point div h2#page-heading span')).first();

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

export class MDistributeParamPointUpdatePage {
  pageTitle = element(by.id('jhi-m-distribute-param-point-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  targetAttributeInput = element(by.id('field_targetAttribute'));
  targetNationalityGroupIdInput = element(by.id('field_targetNationalityGroupId'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  iconAssetNameInput = element(by.id('field_iconAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTargetAttributeInput(targetAttribute) {
    await this.targetAttributeInput.sendKeys(targetAttribute);
  }

  async getTargetAttributeInput() {
    return await this.targetAttributeInput.getAttribute('value');
  }

  async setTargetNationalityGroupIdInput(targetNationalityGroupId) {
    await this.targetNationalityGroupIdInput.sendKeys(targetNationalityGroupId);
  }

  async getTargetNationalityGroupIdInput() {
    return await this.targetNationalityGroupIdInput.getAttribute('value');
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

  async setIconAssetNameInput(iconAssetName) {
    await this.iconAssetNameInput.sendKeys(iconAssetName);
  }

  async getIconAssetNameInput() {
    return await this.iconAssetNameInput.getAttribute('value');
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

export class MDistributeParamPointDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDistributeParamPoint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDistributeParamPoint'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
