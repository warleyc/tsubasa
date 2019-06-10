import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MContentableCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-contentable-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-contentable-card div h2#page-heading span')).first();

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

export class MContentableCardUpdatePage {
  pageTitle = element(by.id('jhi-m-contentable-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  playableCardIdInput = element(by.id('field_playableCardId'));
  levelInput = element(by.id('field_level'));
  actionMainLevelInput = element(by.id('field_actionMainLevel'));
  actionSub1LevelInput = element(by.id('field_actionSub1Level'));
  actionSub2LevelInput = element(by.id('field_actionSub2Level'));
  actionSub3LevelInput = element(by.id('field_actionSub3Level'));
  actionSub4LevelInput = element(by.id('field_actionSub4Level'));
  plusRateInput = element(by.id('field_plusRate'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPlayableCardIdInput(playableCardId) {
    await this.playableCardIdInput.sendKeys(playableCardId);
  }

  async getPlayableCardIdInput() {
    return await this.playableCardIdInput.getAttribute('value');
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setActionMainLevelInput(actionMainLevel) {
    await this.actionMainLevelInput.sendKeys(actionMainLevel);
  }

  async getActionMainLevelInput() {
    return await this.actionMainLevelInput.getAttribute('value');
  }

  async setActionSub1LevelInput(actionSub1Level) {
    await this.actionSub1LevelInput.sendKeys(actionSub1Level);
  }

  async getActionSub1LevelInput() {
    return await this.actionSub1LevelInput.getAttribute('value');
  }

  async setActionSub2LevelInput(actionSub2Level) {
    await this.actionSub2LevelInput.sendKeys(actionSub2Level);
  }

  async getActionSub2LevelInput() {
    return await this.actionSub2LevelInput.getAttribute('value');
  }

  async setActionSub3LevelInput(actionSub3Level) {
    await this.actionSub3LevelInput.sendKeys(actionSub3Level);
  }

  async getActionSub3LevelInput() {
    return await this.actionSub3LevelInput.getAttribute('value');
  }

  async setActionSub4LevelInput(actionSub4Level) {
    await this.actionSub4LevelInput.sendKeys(actionSub4Level);
  }

  async getActionSub4LevelInput() {
    return await this.actionSub4LevelInput.getAttribute('value');
  }

  async setPlusRateInput(plusRate) {
    await this.plusRateInput.sendKeys(plusRate);
  }

  async getPlusRateInput() {
    return await this.plusRateInput.getAttribute('value');
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

export class MContentableCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mContentableCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mContentableCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
