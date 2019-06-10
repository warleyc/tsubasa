import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDeckRarityConditionDescriptionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-deck-rarity-condition-description div table .btn-danger'));
  title = element.all(by.css('jhi-m-deck-rarity-condition-description div h2#page-heading span')).first();

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

export class MDeckRarityConditionDescriptionUpdatePage {
  pageTitle = element(by.id('jhi-m-deck-rarity-condition-description-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityGroupIdInput = element(by.id('field_rarityGroupId'));
  countTypeInput = element(by.id('field_countType'));
  isStartingInput = element(by.id('field_isStarting'));
  descriptionInput = element(by.id('field_description'));
  iconNameInput = element(by.id('field_iconName'));
  smallIconNameInput = element(by.id('field_smallIconName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRarityGroupIdInput(rarityGroupId) {
    await this.rarityGroupIdInput.sendKeys(rarityGroupId);
  }

  async getRarityGroupIdInput() {
    return await this.rarityGroupIdInput.getAttribute('value');
  }

  async setCountTypeInput(countType) {
    await this.countTypeInput.sendKeys(countType);
  }

  async getCountTypeInput() {
    return await this.countTypeInput.getAttribute('value');
  }

  async setIsStartingInput(isStarting) {
    await this.isStartingInput.sendKeys(isStarting);
  }

  async getIsStartingInput() {
    return await this.isStartingInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setIconNameInput(iconName) {
    await this.iconNameInput.sendKeys(iconName);
  }

  async getIconNameInput() {
    return await this.iconNameInput.getAttribute('value');
  }

  async setSmallIconNameInput(smallIconName) {
    await this.smallIconNameInput.sendKeys(smallIconName);
  }

  async getSmallIconNameInput() {
    return await this.smallIconNameInput.getAttribute('value');
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

export class MDeckRarityConditionDescriptionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDeckRarityConditionDescription-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDeckRarityConditionDescription'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
