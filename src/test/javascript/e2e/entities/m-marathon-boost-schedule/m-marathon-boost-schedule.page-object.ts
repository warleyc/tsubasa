import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonBoostScheduleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-boost-schedule div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-boost-schedule div h2#page-heading span')).first();

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

export class MMarathonBoostScheduleUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-boost-schedule-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventIdInput = element(by.id('field_eventId'));
  boostRatioInput = element(by.id('field_boostRatio'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
  }

  async setBoostRatioInput(boostRatio) {
    await this.boostRatioInput.sendKeys(boostRatio);
  }

  async getBoostRatioInput() {
    return await this.boostRatioInput.getAttribute('value');
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

export class MMarathonBoostScheduleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonBoostSchedule-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonBoostSchedule'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
