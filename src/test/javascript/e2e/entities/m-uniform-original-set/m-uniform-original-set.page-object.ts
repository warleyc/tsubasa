import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MUniformOriginalSetComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-uniform-original-set div table .btn-danger'));
  title = element.all(by.css('jhi-m-uniform-original-set div h2#page-heading span')).first();

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

export class MUniformOriginalSetUpdatePage {
  pageTitle = element(by.id('jhi-m-uniform-original-set-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  menuNameInput = element(by.id('field_menuName'));
  upModelIdInput = element(by.id('field_upModelId'));
  bottomModelIdInput = element(by.id('field_bottomModelId'));
  thumbnailAssetNameInput = element(by.id('field_thumbnailAssetName'));
  uniformTypeInput = element(by.id('field_uniformType'));
  isDefaultInput = element(by.id('field_isDefault'));
  orderNumInput = element(by.id('field_orderNum'));
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

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setMenuNameInput(menuName) {
    await this.menuNameInput.sendKeys(menuName);
  }

  async getMenuNameInput() {
    return await this.menuNameInput.getAttribute('value');
  }

  async setUpModelIdInput(upModelId) {
    await this.upModelIdInput.sendKeys(upModelId);
  }

  async getUpModelIdInput() {
    return await this.upModelIdInput.getAttribute('value');
  }

  async setBottomModelIdInput(bottomModelId) {
    await this.bottomModelIdInput.sendKeys(bottomModelId);
  }

  async getBottomModelIdInput() {
    return await this.bottomModelIdInput.getAttribute('value');
  }

  async setThumbnailAssetNameInput(thumbnailAssetName) {
    await this.thumbnailAssetNameInput.sendKeys(thumbnailAssetName);
  }

  async getThumbnailAssetNameInput() {
    return await this.thumbnailAssetNameInput.getAttribute('value');
  }

  async setUniformTypeInput(uniformType) {
    await this.uniformTypeInput.sendKeys(uniformType);
  }

  async getUniformTypeInput() {
    return await this.uniformTypeInput.getAttribute('value');
  }

  async setIsDefaultInput(isDefault) {
    await this.isDefaultInput.sendKeys(isDefault);
  }

  async getIsDefaultInput() {
    return await this.isDefaultInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
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

export class MUniformOriginalSetDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mUniformOriginalSet-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mUniformOriginalSet'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
