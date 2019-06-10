import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MActionRarityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-action-rarity div table .btn-danger'));
  title = element.all(by.css('jhi-m-action-rarity div h2#page-heading span')).first();

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

export class MActionRarityUpdatePage {
  pageTitle = element(by.id('jhi-m-action-rarity-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityInput = element(by.id('field_rarity'));
  nameInput = element(by.id('field_name'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
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

export class MActionRarityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mActionRarity-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mActionRarity'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
