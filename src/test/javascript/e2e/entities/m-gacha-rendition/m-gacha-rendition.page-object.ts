import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition div h2#page-heading span')).first();

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

export class MGachaRenditionUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  mainPrefabNameInput = element(by.id('field_mainPrefabName'));
  resultExpectedUpPrefabNameInput = element(by.id('field_resultExpectedUpPrefabName'));
  resultQuestionPrefabNameInput = element(by.id('field_resultQuestionPrefabName'));
  soundSwitchEventNameInput = element(by.id('field_soundSwitchEventName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setMainPrefabNameInput(mainPrefabName) {
    await this.mainPrefabNameInput.sendKeys(mainPrefabName);
  }

  async getMainPrefabNameInput() {
    return await this.mainPrefabNameInput.getAttribute('value');
  }

  async setResultExpectedUpPrefabNameInput(resultExpectedUpPrefabName) {
    await this.resultExpectedUpPrefabNameInput.sendKeys(resultExpectedUpPrefabName);
  }

  async getResultExpectedUpPrefabNameInput() {
    return await this.resultExpectedUpPrefabNameInput.getAttribute('value');
  }

  async setResultQuestionPrefabNameInput(resultQuestionPrefabName) {
    await this.resultQuestionPrefabNameInput.sendKeys(resultQuestionPrefabName);
  }

  async getResultQuestionPrefabNameInput() {
    return await this.resultQuestionPrefabNameInput.getAttribute('value');
  }

  async setSoundSwitchEventNameInput(soundSwitchEventName) {
    await this.soundSwitchEventNameInput.sendKeys(soundSwitchEventName);
  }

  async getSoundSwitchEventNameInput() {
    return await this.soundSwitchEventNameInput.getAttribute('value');
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

export class MGachaRenditionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRendition-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRendition'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
