import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTipsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-tips div table .btn-danger'));
  title = element.all(by.css('jhi-m-tips div h2#page-heading span')).first();

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

export class MTipsUpdatePage {
  pageTitle = element(by.id('jhi-m-tips-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  priorityInput = element(by.id('field_priority'));
  titleInput = element(by.id('field_title'));
  descriptionInput = element(by.id('field_description'));
  imageAssetNameInput = element(by.id('field_imageAssetName'));
  colorTypeInput = element(by.id('field_colorType'));
  isTipsInput = element(by.id('field_isTips'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPriorityInput(priority) {
    await this.priorityInput.sendKeys(priority);
  }

  async getPriorityInput() {
    return await this.priorityInput.getAttribute('value');
  }

  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return await this.titleInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setImageAssetNameInput(imageAssetName) {
    await this.imageAssetNameInput.sendKeys(imageAssetName);
  }

  async getImageAssetNameInput() {
    return await this.imageAssetNameInput.getAttribute('value');
  }

  async setColorTypeInput(colorType) {
    await this.colorTypeInput.sendKeys(colorType);
  }

  async getColorTypeInput() {
    return await this.colorTypeInput.getAttribute('value');
  }

  async setIsTipsInput(isTips) {
    await this.isTipsInput.sendKeys(isTips);
  }

  async getIsTipsInput() {
    return await this.isTipsInput.getAttribute('value');
  }

  async setStartAtInput(startAt) {
    await this.startAtInput.sendKeys(startAt);
  }

  async getStartAtInput() {
    return await this.startAtInput.getAttribute('value');
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

export class MTipsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTips-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTips'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
