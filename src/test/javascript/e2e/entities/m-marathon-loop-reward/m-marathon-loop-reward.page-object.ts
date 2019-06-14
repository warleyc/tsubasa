import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonLoopRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-loop-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-loop-reward div h2#page-heading span')).first();

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

export class MMarathonLoopRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-loop-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventIdInput = element(by.id('field_eventId'));
  loopPointInput = element(by.id('field_loopPoint'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
  }

  async setLoopPointInput(loopPoint) {
    await this.loopPointInput.sendKeys(loopPoint);
  }

  async getLoopPointInput() {
    return await this.loopPointInput.getAttribute('value');
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

export class MMarathonLoopRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonLoopReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonLoopReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
