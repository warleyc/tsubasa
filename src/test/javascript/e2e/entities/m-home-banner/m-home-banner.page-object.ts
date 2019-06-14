import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MHomeBannerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-home-banner div table .btn-danger'));
  title = element.all(by.css('jhi-m-home-banner div h2#page-heading span')).first();

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

export class MHomeBannerUpdatePage {
  pageTitle = element(by.id('jhi-m-home-banner-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  bannerTypeInput = element(by.id('field_bannerType'));
  assetNameInput = element(by.id('field_assetName'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  labelEndAtInput = element(by.id('field_labelEndAt'));
  sceneTransitionInput = element(by.id('field_sceneTransition'));
  sceneTransitionParamInput = element(by.id('field_sceneTransitionParam'));
  orderNumInput = element(by.id('field_orderNum'));
  appealTypeInput = element(by.id('field_appealType'));
  appealContentIdInput = element(by.id('field_appealContentId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setBannerTypeInput(bannerType) {
    await this.bannerTypeInput.sendKeys(bannerType);
  }

  async getBannerTypeInput() {
    return await this.bannerTypeInput.getAttribute('value');
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
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

  async setLabelEndAtInput(labelEndAt) {
    await this.labelEndAtInput.sendKeys(labelEndAt);
  }

  async getLabelEndAtInput() {
    return await this.labelEndAtInput.getAttribute('value');
  }

  async setSceneTransitionInput(sceneTransition) {
    await this.sceneTransitionInput.sendKeys(sceneTransition);
  }

  async getSceneTransitionInput() {
    return await this.sceneTransitionInput.getAttribute('value');
  }

  async setSceneTransitionParamInput(sceneTransitionParam) {
    await this.sceneTransitionParamInput.sendKeys(sceneTransitionParam);
  }

  async getSceneTransitionParamInput() {
    return await this.sceneTransitionParamInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
  }

  async setAppealTypeInput(appealType) {
    await this.appealTypeInput.sendKeys(appealType);
  }

  async getAppealTypeInput() {
    return await this.appealTypeInput.getAttribute('value');
  }

  async setAppealContentIdInput(appealContentId) {
    await this.appealContentIdInput.sendKeys(appealContentId);
  }

  async getAppealContentIdInput() {
    return await this.appealContentIdInput.getAttribute('value');
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

export class MHomeBannerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mHomeBanner-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mHomeBanner'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
