import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLeagueEffectComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-league-effect div table .btn-danger'));
  title = element.all(by.css('jhi-m-league-effect div h2#page-heading span')).first();

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

export class MLeagueEffectUpdatePage {
  pageTitle = element(by.id('jhi-m-league-effect-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  effectTypeInput = element(by.id('field_effectType'));
  leagueHierarchyInput = element(by.id('field_leagueHierarchy'));
  rateInput = element(by.id('field_rate'));
  priceInput = element(by.id('field_price'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEffectTypeInput(effectType) {
    await this.effectTypeInput.sendKeys(effectType);
  }

  async getEffectTypeInput() {
    return await this.effectTypeInput.getAttribute('value');
  }

  async setLeagueHierarchyInput(leagueHierarchy) {
    await this.leagueHierarchyInput.sendKeys(leagueHierarchy);
  }

  async getLeagueHierarchyInput() {
    return await this.leagueHierarchyInput.getAttribute('value');
  }

  async setRateInput(rate) {
    await this.rateInput.sendKeys(rate);
  }

  async getRateInput() {
    return await this.rateInput.getAttribute('value');
  }

  async setPriceInput(price) {
    await this.priceInput.sendKeys(price);
  }

  async getPriceInput() {
    return await this.priceInput.getAttribute('value');
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

export class MLeagueEffectDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLeagueEffect-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLeagueEffect'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
