import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MStageStoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-stage-story div table .btn-danger'));
  title = element.all(by.css('jhi-m-stage-story div h2#page-heading span')).first();

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

export class MStageStoryUpdatePage {
  pageTitle = element(by.id('jhi-m-stage-story-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  stageIdInput = element(by.id('field_stageId'));
  eventTypeInput = element(by.id('field_eventType'));
  mainStoryAssetInput = element(by.id('field_mainStoryAsset'));
  kickoffStoryAssetInput = element(by.id('field_kickoffStoryAsset'));
  halftimeStoryAssetInput = element(by.id('field_halftimeStoryAsset'));
  resultStoryAssetInput = element(by.id('field_resultStoryAsset'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setStageIdInput(stageId) {
    await this.stageIdInput.sendKeys(stageId);
  }

  async getStageIdInput() {
    return await this.stageIdInput.getAttribute('value');
  }

  async setEventTypeInput(eventType) {
    await this.eventTypeInput.sendKeys(eventType);
  }

  async getEventTypeInput() {
    return await this.eventTypeInput.getAttribute('value');
  }

  async setMainStoryAssetInput(mainStoryAsset) {
    await this.mainStoryAssetInput.sendKeys(mainStoryAsset);
  }

  async getMainStoryAssetInput() {
    return await this.mainStoryAssetInput.getAttribute('value');
  }

  async setKickoffStoryAssetInput(kickoffStoryAsset) {
    await this.kickoffStoryAssetInput.sendKeys(kickoffStoryAsset);
  }

  async getKickoffStoryAssetInput() {
    return await this.kickoffStoryAssetInput.getAttribute('value');
  }

  async setHalftimeStoryAssetInput(halftimeStoryAsset) {
    await this.halftimeStoryAssetInput.sendKeys(halftimeStoryAsset);
  }

  async getHalftimeStoryAssetInput() {
    return await this.halftimeStoryAssetInput.getAttribute('value');
  }

  async setResultStoryAssetInput(resultStoryAsset) {
    await this.resultStoryAssetInput.sendKeys(resultStoryAsset);
  }

  async getResultStoryAssetInput() {
    return await this.resultStoryAssetInput.getAttribute('value');
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

export class MStageStoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mStageStory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mStageStory'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
