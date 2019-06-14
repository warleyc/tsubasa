import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionSwipeIconComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-swipe-icon div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-swipe-icon div h2#page-heading span')).first();

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

export class MGachaRenditionSwipeIconUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-swipe-icon-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isSsrInput = element(by.id('field_isSsr'));
  isROrLessInput = element(by.id('field_isROrLess'));
  weightInput = element(by.id('field_weight'));
  swipeIconPrefabNameInput = element(by.id('field_swipeIconPrefabName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIsSsrInput(isSsr) {
    await this.isSsrInput.sendKeys(isSsr);
  }

  async getIsSsrInput() {
    return await this.isSsrInput.getAttribute('value');
  }

  async setIsROrLessInput(isROrLess) {
    await this.isROrLessInput.sendKeys(isROrLess);
  }

  async getIsROrLessInput() {
    return await this.isROrLessInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setSwipeIconPrefabNameInput(swipeIconPrefabName) {
    await this.swipeIconPrefabNameInput.sendKeys(swipeIconPrefabName);
  }

  async getSwipeIconPrefabNameInput() {
    return await this.swipeIconPrefabNameInput.getAttribute('value');
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

export class MGachaRenditionSwipeIconDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionSwipeIcon-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionSwipeIcon'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
