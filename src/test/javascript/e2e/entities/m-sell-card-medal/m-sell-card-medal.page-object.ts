import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSellCardMedalComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-sell-card-medal div table .btn-danger'));
  title = element.all(by.css('jhi-m-sell-card-medal div h2#page-heading span')).first();

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

export class MSellCardMedalUpdatePage {
  pageTitle = element(by.id('jhi-m-sell-card-medal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  medalIdInput = element(by.id('field_medalId'));
  amountInput = element(by.id('field_amount'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMedalIdInput(medalId) {
    await this.medalIdInput.sendKeys(medalId);
  }

  async getMedalIdInput() {
    return await this.medalIdInput.getAttribute('value');
  }

  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return await this.amountInput.getAttribute('value');
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

export class MSellCardMedalDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSellCardMedal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSellCardMedal'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
