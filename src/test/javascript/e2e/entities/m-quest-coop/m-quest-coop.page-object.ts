import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestCoopComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-coop div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-coop div h2#page-heading span')).first();

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

export class MQuestCoopUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-coop-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  effectRarityInput = element(by.id('field_effectRarity'));
  effectLevelFromInput = element(by.id('field_effectLevelFrom'));
  effectLevelToInput = element(by.id('field_effectLevelTo'));
  choose1WeightInput = element(by.id('field_choose1Weight'));
  choose2WeightInput = element(by.id('field_choose2Weight'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setEffectRarityInput(effectRarity) {
    await this.effectRarityInput.sendKeys(effectRarity);
  }

  async getEffectRarityInput() {
    return await this.effectRarityInput.getAttribute('value');
  }

  async setEffectLevelFromInput(effectLevelFrom) {
    await this.effectLevelFromInput.sendKeys(effectLevelFrom);
  }

  async getEffectLevelFromInput() {
    return await this.effectLevelFromInput.getAttribute('value');
  }

  async setEffectLevelToInput(effectLevelTo) {
    await this.effectLevelToInput.sendKeys(effectLevelTo);
  }

  async getEffectLevelToInput() {
    return await this.effectLevelToInput.getAttribute('value');
  }

  async setChoose1WeightInput(choose1Weight) {
    await this.choose1WeightInput.sendKeys(choose1Weight);
  }

  async getChoose1WeightInput() {
    return await this.choose1WeightInput.getAttribute('value');
  }

  async setChoose2WeightInput(choose2Weight) {
    await this.choose2WeightInput.sendKeys(choose2Weight);
  }

  async getChoose2WeightInput() {
    return await this.choose2WeightInput.getAttribute('value');
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

export class MQuestCoopDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestCoop-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestCoop'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
