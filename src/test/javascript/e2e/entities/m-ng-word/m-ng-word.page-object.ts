import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MNgWordComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-ng-word div table .btn-danger'));
  title = element.all(by.css('jhi-m-ng-word div h2#page-heading span')).first();

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

export class MNgWordUpdatePage {
  pageTitle = element(by.id('jhi-m-ng-word-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  wordInput = element(by.id('field_word'));
  judgeTypeInput = element(by.id('field_judgeType'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWordInput(word) {
    await this.wordInput.sendKeys(word);
  }

  async getWordInput() {
    return await this.wordInput.getAttribute('value');
  }

  async setJudgeTypeInput(judgeType) {
    await this.judgeTypeInput.sendKeys(judgeType);
  }

  async getJudgeTypeInput() {
    return await this.judgeTypeInput.getAttribute('value');
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

export class MNgWordDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mNgWord-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mNgWord'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
