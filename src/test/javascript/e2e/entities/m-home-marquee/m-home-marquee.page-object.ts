import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MHomeMarqueeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-home-marquee div table .btn-danger'));
  title = element.all(by.css('jhi-m-home-marquee div h2#page-heading span')).first();

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

export class MHomeMarqueeUpdatePage {
  pageTitle = element(by.id('jhi-m-home-marquee-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  priorityInput = element(by.id('field_priority'));
  marqueeTextInput = element(by.id('field_marqueeText'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPriorityInput(priority) {
    await this.priorityInput.sendKeys(priority);
  }

  async getPriorityInput() {
    return await this.priorityInput.getAttribute('value');
  }

  async setMarqueeTextInput(marqueeText) {
    await this.marqueeTextInput.sendKeys(marqueeText);
  }

  async getMarqueeTextInput() {
    return await this.marqueeTextInput.getAttribute('value');
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

export class MHomeMarqueeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mHomeMarquee-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mHomeMarquee'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
