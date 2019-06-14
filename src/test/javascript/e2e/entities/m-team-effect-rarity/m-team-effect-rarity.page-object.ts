import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTeamEffectRarityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-team-effect-rarity div table .btn-danger'));
  title = element.all(by.css('jhi-m-team-effect-rarity div h2#page-heading span')).first();

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

export class MTeamEffectRarityUpdatePage {
  pageTitle = element(by.id('jhi-m-team-effect-rarity-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityInput = element(by.id('field_rarity'));
  nameInput = element(by.id('field_name'));
  maxLevelInput = element(by.id('field_maxLevel'));

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

  async setMaxLevelInput(maxLevel) {
    await this.maxLevelInput.sendKeys(maxLevel);
  }

  async getMaxLevelInput() {
    return await this.maxLevelInput.getAttribute('value');
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

export class MTeamEffectRarityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTeamEffectRarity-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTeamEffectRarity'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
