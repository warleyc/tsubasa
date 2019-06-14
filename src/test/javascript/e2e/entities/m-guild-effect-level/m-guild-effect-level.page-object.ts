import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildEffectLevelComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-effect-level div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-effect-level div h2#page-heading span')).first();

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

export class MGuildEffectLevelUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-effect-level-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  effectTypeInput = element(by.id('field_effectType'));
  levelInput = element(by.id('field_level'));
  rateInput = element(by.id('field_rate'));
  rateStrInput = element(by.id('field_rateStr'));
  coinInput = element(by.id('field_coin'));
  guildLevelInput = element(by.id('field_guildLevel'));
  guildMedalInput = element(by.id('field_guildMedal'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEffectTypeInput(effectType) {
    await this.effectTypeInput.sendKeys(effectType);
  }

  async getEffectTypeInput() {
    return await this.effectTypeInput.getAttribute('value');
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setRateInput(rate) {
    await this.rateInput.sendKeys(rate);
  }

  async getRateInput() {
    return await this.rateInput.getAttribute('value');
  }

  async setRateStrInput(rateStr) {
    await this.rateStrInput.sendKeys(rateStr);
  }

  async getRateStrInput() {
    return await this.rateStrInput.getAttribute('value');
  }

  async setCoinInput(coin) {
    await this.coinInput.sendKeys(coin);
  }

  async getCoinInput() {
    return await this.coinInput.getAttribute('value');
  }

  async setGuildLevelInput(guildLevel) {
    await this.guildLevelInput.sendKeys(guildLevel);
  }

  async getGuildLevelInput() {
    return await this.guildLevelInput.getAttribute('value');
  }

  async setGuildMedalInput(guildMedal) {
    await this.guildMedalInput.sendKeys(guildMedal);
  }

  async getGuildMedalInput() {
    return await this.guildMedalInput.getAttribute('value');
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

export class MGuildEffectLevelDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildEffectLevel-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildEffectLevel'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
