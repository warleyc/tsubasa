import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLoginBonusRoundComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-login-bonus-round div table .btn-danger'));
  title = element.all(by.css('jhi-m-login-bonus-round div h2#page-heading span')).first();

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

export class MLoginBonusRoundUpdatePage {
  pageTitle = element(by.id('jhi-m-login-bonus-round-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  roundIdInput = element(by.id('field_roundId'));
  bonusTypeInput = element(by.id('field_bonusType'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  serifSanaeInput = element(by.id('field_serifSanae'));
  serifYayoiInput = element(by.id('field_serifYayoi'));
  serifYoshikoInput = element(by.id('field_serifYoshiko'));
  serifMakiInput = element(by.id('field_serifMaki'));
  sanaeImageInput = element(by.id('field_sanaeImage'));
  yayoiImageInput = element(by.id('field_yayoiImage'));
  yoshikoImageInput = element(by.id('field_yoshikoImage'));
  makiImageInput = element(by.id('field_makiImage'));
  soundSwitchEventNameInput = element(by.id('field_soundSwitchEventName'));
  nextIdInput = element(by.id('field_nextId'));
  lastDayAppealCommentInput = element(by.id('field_lastDayAppealComment'));
  backgroundImageInput = element(by.id('field_backgroundImage'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRoundIdInput(roundId) {
    await this.roundIdInput.sendKeys(roundId);
  }

  async getRoundIdInput() {
    return await this.roundIdInput.getAttribute('value');
  }

  async setBonusTypeInput(bonusType) {
    await this.bonusTypeInput.sendKeys(bonusType);
  }

  async getBonusTypeInput() {
    return await this.bonusTypeInput.getAttribute('value');
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

  async setSerifSanaeInput(serifSanae) {
    await this.serifSanaeInput.sendKeys(serifSanae);
  }

  async getSerifSanaeInput() {
    return await this.serifSanaeInput.getAttribute('value');
  }

  async setSerifYayoiInput(serifYayoi) {
    await this.serifYayoiInput.sendKeys(serifYayoi);
  }

  async getSerifYayoiInput() {
    return await this.serifYayoiInput.getAttribute('value');
  }

  async setSerifYoshikoInput(serifYoshiko) {
    await this.serifYoshikoInput.sendKeys(serifYoshiko);
  }

  async getSerifYoshikoInput() {
    return await this.serifYoshikoInput.getAttribute('value');
  }

  async setSerifMakiInput(serifMaki) {
    await this.serifMakiInput.sendKeys(serifMaki);
  }

  async getSerifMakiInput() {
    return await this.serifMakiInput.getAttribute('value');
  }

  async setSanaeImageInput(sanaeImage) {
    await this.sanaeImageInput.sendKeys(sanaeImage);
  }

  async getSanaeImageInput() {
    return await this.sanaeImageInput.getAttribute('value');
  }

  async setYayoiImageInput(yayoiImage) {
    await this.yayoiImageInput.sendKeys(yayoiImage);
  }

  async getYayoiImageInput() {
    return await this.yayoiImageInput.getAttribute('value');
  }

  async setYoshikoImageInput(yoshikoImage) {
    await this.yoshikoImageInput.sendKeys(yoshikoImage);
  }

  async getYoshikoImageInput() {
    return await this.yoshikoImageInput.getAttribute('value');
  }

  async setMakiImageInput(makiImage) {
    await this.makiImageInput.sendKeys(makiImage);
  }

  async getMakiImageInput() {
    return await this.makiImageInput.getAttribute('value');
  }

  async setSoundSwitchEventNameInput(soundSwitchEventName) {
    await this.soundSwitchEventNameInput.sendKeys(soundSwitchEventName);
  }

  async getSoundSwitchEventNameInput() {
    return await this.soundSwitchEventNameInput.getAttribute('value');
  }

  async setNextIdInput(nextId) {
    await this.nextIdInput.sendKeys(nextId);
  }

  async getNextIdInput() {
    return await this.nextIdInput.getAttribute('value');
  }

  async setLastDayAppealCommentInput(lastDayAppealComment) {
    await this.lastDayAppealCommentInput.sendKeys(lastDayAppealComment);
  }

  async getLastDayAppealCommentInput() {
    return await this.lastDayAppealCommentInput.getAttribute('value');
  }

  async setBackgroundImageInput(backgroundImage) {
    await this.backgroundImageInput.sendKeys(backgroundImage);
  }

  async getBackgroundImageInput() {
    return await this.backgroundImageInput.getAttribute('value');
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

export class MLoginBonusRoundDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLoginBonusRound-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLoginBonusRound'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
