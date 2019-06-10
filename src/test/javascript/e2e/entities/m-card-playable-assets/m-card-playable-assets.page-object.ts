import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCardPlayableAssetsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-card-playable-assets div table .btn-danger'));
  title = element.all(by.css('jhi-m-card-playable-assets div h2#page-heading span')).first();

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

export class MCardPlayableAssetsUpdatePage {
  pageTitle = element(by.id('jhi-m-card-playable-assets-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cutinImageAssetNameInput = element(by.id('field_cutinImageAssetName'));
  soundEventSuffixInput = element(by.id('field_soundEventSuffix'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCutinImageAssetNameInput(cutinImageAssetName) {
    await this.cutinImageAssetNameInput.sendKeys(cutinImageAssetName);
  }

  async getCutinImageAssetNameInput() {
    return await this.cutinImageAssetNameInput.getAttribute('value');
  }

  async setSoundEventSuffixInput(soundEventSuffix) {
    await this.soundEventSuffixInput.sendKeys(soundEventSuffix);
  }

  async getSoundEventSuffixInput() {
    return await this.soundEventSuffixInput.getAttribute('value');
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

export class MCardPlayableAssetsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCardPlayableAssets-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCardPlayableAssets'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
