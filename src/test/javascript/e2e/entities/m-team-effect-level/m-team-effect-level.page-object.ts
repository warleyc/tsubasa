import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTeamEffectLevelComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-team-effect-level div table .btn-danger'));
  title = element.all(by.css('jhi-m-team-effect-level div h2#page-heading span')).first();

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

export class MTeamEffectLevelUpdatePage {
  pageTitle = element(by.id('jhi-m-team-effect-level-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityInput = element(by.id('field_rarity'));
  levelInput = element(by.id('field_level'));
  expInput = element(by.id('field_exp'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setExpInput(exp) {
    await this.expInput.sendKeys(exp);
  }

  async getExpInput() {
    return await this.expInput.getAttribute('value');
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

export class MTeamEffectLevelDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTeamEffectLevel-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTeamEffectLevel'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
