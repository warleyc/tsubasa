import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCardIllustAssetsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-card-illust-assets div table .btn-danger'));
  title = element.all(by.css('jhi-m-card-illust-assets div h2#page-heading span')).first();

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

export class MCardIllustAssetsUpdatePage {
  pageTitle = element(by.id('jhi-m-card-illust-assets-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  assetNameInput = element(by.id('field_assetName'));
  subAssetNameInput = element(by.id('field_subAssetName'));
  offsetInput = element(by.id('field_offset'));
  backgroundAssetNameInput = element(by.id('field_backgroundAssetName'));
  decorationAssetNameInput = element(by.id('field_decorationAssetName'));
  effectAssetNameInput = element(by.id('field_effectAssetName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAssetNameInput(assetName) {
    await this.assetNameInput.sendKeys(assetName);
  }

  async getAssetNameInput() {
    return await this.assetNameInput.getAttribute('value');
  }

  async setSubAssetNameInput(subAssetName) {
    await this.subAssetNameInput.sendKeys(subAssetName);
  }

  async getSubAssetNameInput() {
    return await this.subAssetNameInput.getAttribute('value');
  }

  async setOffsetInput(offset) {
    await this.offsetInput.sendKeys(offset);
  }

  async getOffsetInput() {
    return await this.offsetInput.getAttribute('value');
  }

  async setBackgroundAssetNameInput(backgroundAssetName) {
    await this.backgroundAssetNameInput.sendKeys(backgroundAssetName);
  }

  async getBackgroundAssetNameInput() {
    return await this.backgroundAssetNameInput.getAttribute('value');
  }

  async setDecorationAssetNameInput(decorationAssetName) {
    await this.decorationAssetNameInput.sendKeys(decorationAssetName);
  }

  async getDecorationAssetNameInput() {
    return await this.decorationAssetNameInput.getAttribute('value');
  }

  async setEffectAssetNameInput(effectAssetName) {
    await this.effectAssetNameInput.sendKeys(effectAssetName);
  }

  async getEffectAssetNameInput() {
    return await this.effectAssetNameInput.getAttribute('value');
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

export class MCardIllustAssetsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCardIllustAssets-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCardIllustAssets'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
