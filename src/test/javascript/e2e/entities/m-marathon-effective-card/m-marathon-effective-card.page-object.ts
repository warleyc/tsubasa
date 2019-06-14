import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonEffectiveCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-effective-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-effective-card div h2#page-heading span')).first();

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

export class MMarathonEffectiveCardUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-effective-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventIdInput = element(by.id('field_eventId'));
  playableCardIdInput = element(by.id('field_playableCardId'));
  rateInput = element(by.id('field_rate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
  }

  async setPlayableCardIdInput(playableCardId) {
    await this.playableCardIdInput.sendKeys(playableCardId);
  }

  async getPlayableCardIdInput() {
    return await this.playableCardIdInput.getAttribute('value');
  }

  async setRateInput(rate) {
    await this.rateInput.sendKeys(rate);
  }

  async getRateInput() {
    return await this.rateInput.getAttribute('value');
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

export class MMarathonEffectiveCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonEffectiveCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonEffectiveCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
