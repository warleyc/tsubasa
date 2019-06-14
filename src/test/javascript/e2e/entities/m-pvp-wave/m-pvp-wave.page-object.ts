import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPvpWaveComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-pvp-wave div table .btn-danger'));
  title = element.all(by.css('jhi-m-pvp-wave div h2#page-heading span')).first();

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

export class MPvpWaveUpdatePage {
  pageTitle = element(by.id('jhi-m-pvp-wave-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  isRankingInput = element(by.id('field_isRanking'));

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

  async setIsRankingInput(isRanking) {
    await this.isRankingInput.sendKeys(isRanking);
  }

  async getIsRankingInput() {
    return await this.isRankingInput.getAttribute('value');
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

export class MPvpWaveDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPvpWave-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPvpWave'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
