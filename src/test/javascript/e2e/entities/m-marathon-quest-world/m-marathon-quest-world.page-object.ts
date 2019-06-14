import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMarathonQuestWorldComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-marathon-quest-world div table .btn-danger'));
  title = element.all(by.css('jhi-m-marathon-quest-world div h2#page-heading span')).first();

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

export class MMarathonQuestWorldUpdatePage {
  pageTitle = element(by.id('jhi-m-marathon-quest-world-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  setIdInput = element(by.id('field_setId'));
  numberInput = element(by.id('field_number'));
  nameInput = element(by.id('field_name'));
  imagePathInput = element(by.id('field_imagePath'));
  descriptionInput = element(by.id('field_description'));
  stageUnlockPatternInput = element(by.id('field_stageUnlockPattern'));
  arousalBannerInput = element(by.id('field_arousalBanner'));
  specialRewardContentTypeInput = element(by.id('field_specialRewardContentType'));
  specialRewardContentIdInput = element(by.id('field_specialRewardContentId'));
  isEnableCoopInput = element(by.id('field_isEnableCoop'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setSetIdInput(setId) {
    await this.setIdInput.sendKeys(setId);
  }

  async getSetIdInput() {
    return await this.setIdInput.getAttribute('value');
  }

  async setNumberInput(number) {
    await this.numberInput.sendKeys(number);
  }

  async getNumberInput() {
    return await this.numberInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setImagePathInput(imagePath) {
    await this.imagePathInput.sendKeys(imagePath);
  }

  async getImagePathInput() {
    return await this.imagePathInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setStageUnlockPatternInput(stageUnlockPattern) {
    await this.stageUnlockPatternInput.sendKeys(stageUnlockPattern);
  }

  async getStageUnlockPatternInput() {
    return await this.stageUnlockPatternInput.getAttribute('value');
  }

  async setArousalBannerInput(arousalBanner) {
    await this.arousalBannerInput.sendKeys(arousalBanner);
  }

  async getArousalBannerInput() {
    return await this.arousalBannerInput.getAttribute('value');
  }

  async setSpecialRewardContentTypeInput(specialRewardContentType) {
    await this.specialRewardContentTypeInput.sendKeys(specialRewardContentType);
  }

  async getSpecialRewardContentTypeInput() {
    return await this.specialRewardContentTypeInput.getAttribute('value');
  }

  async setSpecialRewardContentIdInput(specialRewardContentId) {
    await this.specialRewardContentIdInput.sendKeys(specialRewardContentId);
  }

  async getSpecialRewardContentIdInput() {
    return await this.specialRewardContentIdInput.getAttribute('value');
  }

  async setIsEnableCoopInput(isEnableCoop) {
    await this.isEnableCoopInput.sendKeys(isEnableCoop);
  }

  async getIsEnableCoopInput() {
    return await this.isEnableCoopInput.getAttribute('value');
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

export class MMarathonQuestWorldDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMarathonQuestWorld-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMarathonQuestWorld'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
