import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLoginBonusSerifComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-login-bonus-serif div table .btn-danger'));
  title = element.all(by.css('jhi-m-login-bonus-serif div h2#page-heading span')).first();

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

export class MLoginBonusSerifUpdatePage {
  pageTitle = element(by.id('jhi-m-login-bonus-serif-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  serifIdInput = element(by.id('field_serifId'));
  serif1Input = element(by.id('field_serif1'));
  serif2Input = element(by.id('field_serif2'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setSerifIdInput(serifId) {
    await this.serifIdInput.sendKeys(serifId);
  }

  async getSerifIdInput() {
    return await this.serifIdInput.getAttribute('value');
  }

  async setSerif1Input(serif1) {
    await this.serif1Input.sendKeys(serif1);
  }

  async getSerif1Input() {
    return await this.serif1Input.getAttribute('value');
  }

  async setSerif2Input(serif2) {
    await this.serif2Input.sendKeys(serif2);
  }

  async getSerif2Input() {
    return await this.serif2Input.getAttribute('value');
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

export class MLoginBonusSerifDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLoginBonusSerif-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLoginBonusSerif'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
