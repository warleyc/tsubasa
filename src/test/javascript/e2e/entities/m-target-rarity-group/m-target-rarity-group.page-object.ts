import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetRarityGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-rarity-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-rarity-group div h2#page-heading span')).first();

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

export class MTargetRarityGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-rarity-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  cardRarityInput = element(by.id('field_cardRarity'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setCardRarityInput(cardRarity) {
    await this.cardRarityInput.sendKeys(cardRarity);
  }

  async getCardRarityInput() {
    return await this.cardRarityInput.getAttribute('value');
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

export class MTargetRarityGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetRarityGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetRarityGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
