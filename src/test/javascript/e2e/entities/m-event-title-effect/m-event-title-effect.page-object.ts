import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MEventTitleEffectComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-event-title-effect div table .btn-danger'));
  title = element.all(by.css('jhi-m-event-title-effect div h2#page-heading span')).first();

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

export class MEventTitleEffectUpdatePage {
  pageTitle = element(by.id('jhi-m-event-title-effect-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  effectPathInput = element(by.id('field_effectPath'));
  bgmSoundEventInput = element(by.id('field_bgmSoundEvent'));
  seSoundEventInput = element(by.id('field_seSoundEvent'));
  voiceSoundEventSuffixesInput = element(by.id('field_voiceSoundEventSuffixes'));
  copyrightTextInput = element(by.id('field_copyrightText'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEffectPathInput(effectPath) {
    await this.effectPathInput.sendKeys(effectPath);
  }

  async getEffectPathInput() {
    return await this.effectPathInput.getAttribute('value');
  }

  async setBgmSoundEventInput(bgmSoundEvent) {
    await this.bgmSoundEventInput.sendKeys(bgmSoundEvent);
  }

  async getBgmSoundEventInput() {
    return await this.bgmSoundEventInput.getAttribute('value');
  }

  async setSeSoundEventInput(seSoundEvent) {
    await this.seSoundEventInput.sendKeys(seSoundEvent);
  }

  async getSeSoundEventInput() {
    return await this.seSoundEventInput.getAttribute('value');
  }

  async setVoiceSoundEventSuffixesInput(voiceSoundEventSuffixes) {
    await this.voiceSoundEventSuffixesInput.sendKeys(voiceSoundEventSuffixes);
  }

  async getVoiceSoundEventSuffixesInput() {
    return await this.voiceSoundEventSuffixesInput.getAttribute('value');
  }

  async setCopyrightTextInput(copyrightText) {
    await this.copyrightTextInput.sendKeys(copyrightText);
  }

  async getCopyrightTextInput() {
    return await this.copyrightTextInput.getAttribute('value');
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

export class MEventTitleEffectDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mEventTitleEffect-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mEventTitleEffect'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
