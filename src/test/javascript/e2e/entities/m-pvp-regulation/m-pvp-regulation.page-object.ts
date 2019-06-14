import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPvpRegulationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-pvp-regulation div table .btn-danger'));
  title = element.all(by.css('jhi-m-pvp-regulation div h2#page-heading span')).first();

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

export class MPvpRegulationUpdatePage {
  pageTitle = element(by.id('jhi-m-pvp-regulation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  matchOptionIdInput = element(by.id('field_matchOptionId'));
  deckConditionIdInput = element(by.id('field_deckConditionId'));
  ruleTutorialIdInput = element(by.id('field_ruleTutorialId'));
  mmatchoptionSelect = element(by.id('field_mmatchoption'));

  async getPageTitle() {
    return this.pageTitle.getText();
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

  async setMatchOptionIdInput(matchOptionId) {
    await this.matchOptionIdInput.sendKeys(matchOptionId);
  }

  async getMatchOptionIdInput() {
    return await this.matchOptionIdInput.getAttribute('value');
  }

  async setDeckConditionIdInput(deckConditionId) {
    await this.deckConditionIdInput.sendKeys(deckConditionId);
  }

  async getDeckConditionIdInput() {
    return await this.deckConditionIdInput.getAttribute('value');
  }

  async setRuleTutorialIdInput(ruleTutorialId) {
    await this.ruleTutorialIdInput.sendKeys(ruleTutorialId);
  }

  async getRuleTutorialIdInput() {
    return await this.ruleTutorialIdInput.getAttribute('value');
  }

  async mmatchoptionSelectLastOption(timeout?: number) {
    await this.mmatchoptionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mmatchoptionSelectOption(option) {
    await this.mmatchoptionSelect.sendKeys(option);
  }

  getMmatchoptionSelect(): ElementFinder {
    return this.mmatchoptionSelect;
  }

  async getMmatchoptionSelectedOption() {
    return await this.mmatchoptionSelect.element(by.css('option:checked')).getText();
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

export class MPvpRegulationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPvpRegulation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPvpRegulation'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
