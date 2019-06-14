import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestTicketComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-ticket div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-ticket div h2#page-heading span')).first();

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

export class MQuestTicketUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-ticket-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  descriptionInput = element(by.id('field_description'));
  thumbnailAssetInput = element(by.id('field_thumbnailAsset'));

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

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setThumbnailAssetInput(thumbnailAsset) {
    await this.thumbnailAssetInput.sendKeys(thumbnailAsset);
  }

  async getThumbnailAssetInput() {
    return await this.thumbnailAssetInput.getAttribute('value');
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

export class MQuestTicketDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestTicket-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestTicket'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
