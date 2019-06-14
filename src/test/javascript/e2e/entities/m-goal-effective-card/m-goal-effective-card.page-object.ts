import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGoalEffectiveCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-goal-effective-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-goal-effective-card div h2#page-heading span')).first();

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

export class MGoalEffectiveCardUpdatePage {
  pageTitle = element(by.id('jhi-m-goal-effective-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventTypeInput = element(by.id('field_eventType'));
  eventIdInput = element(by.id('field_eventId'));
  playableCardIdInput = element(by.id('field_playableCardId'));
  rateInput = element(by.id('field_rate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventTypeInput(eventType) {
    await this.eventTypeInput.sendKeys(eventType);
  }

  async getEventTypeInput() {
    return await this.eventTypeInput.getAttribute('value');
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

export class MGoalEffectiveCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGoalEffectiveCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGoalEffectiveCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
