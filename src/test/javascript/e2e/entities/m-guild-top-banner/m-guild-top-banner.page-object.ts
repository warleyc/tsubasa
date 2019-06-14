import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildTopBannerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-top-banner div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-top-banner div h2#page-heading span')).first();

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

export class MGuildTopBannerUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-top-banner-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  assetPathInput = element(by.id('field_assetPath'));
  guildBannerTypeInput = element(by.id('field_guildBannerType'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAssetPathInput(assetPath) {
    await this.assetPathInput.sendKeys(assetPath);
  }

  async getAssetPathInput() {
    return await this.assetPathInput.getAttribute('value');
  }

  async setGuildBannerTypeInput(guildBannerType) {
    await this.guildBannerTypeInput.sendKeys(guildBannerType);
  }

  async getGuildBannerTypeInput() {
    return await this.guildBannerTypeInput.getAttribute('value');
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

export class MGuildTopBannerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildTopBanner-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildTopBanner'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
