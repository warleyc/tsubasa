import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMatchEnvironmentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-match-environment div table .btn-danger'));
  title = element.all(by.css('jhi-m-match-environment div h2#page-heading span')).first();

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

export class MMatchEnvironmentUpdatePage {
  pageTitle = element(by.id('jhi-m-match-environment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  categoryInput = element(by.id('field_category'));
  skyTexInput = element(by.id('field_skyTex'));
  ballInput = element(by.id('field_ball'));
  stadiumInput = element(by.id('field_stadium'));
  rainyAssetNameInput = element(by.id('field_rainyAssetName'));
  isPlayRainySoundInput = element(by.id('field_isPlayRainySound'));
  offenseStartBgmInput = element(by.id('field_offenseStartBgm'));
  offenseStopBgmInput = element(by.id('field_offenseStopBgm'));
  defenseStartBgmInput = element(by.id('field_defenseStartBgm'));
  defenseStopBgmInput = element(by.id('field_defenseStopBgm'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCategoryInput(category) {
    await this.categoryInput.sendKeys(category);
  }

  async getCategoryInput() {
    return await this.categoryInput.getAttribute('value');
  }

  async setSkyTexInput(skyTex) {
    await this.skyTexInput.sendKeys(skyTex);
  }

  async getSkyTexInput() {
    return await this.skyTexInput.getAttribute('value');
  }

  async setBallInput(ball) {
    await this.ballInput.sendKeys(ball);
  }

  async getBallInput() {
    return await this.ballInput.getAttribute('value');
  }

  async setStadiumInput(stadium) {
    await this.stadiumInput.sendKeys(stadium);
  }

  async getStadiumInput() {
    return await this.stadiumInput.getAttribute('value');
  }

  async setRainyAssetNameInput(rainyAssetName) {
    await this.rainyAssetNameInput.sendKeys(rainyAssetName);
  }

  async getRainyAssetNameInput() {
    return await this.rainyAssetNameInput.getAttribute('value');
  }

  async setIsPlayRainySoundInput(isPlayRainySound) {
    await this.isPlayRainySoundInput.sendKeys(isPlayRainySound);
  }

  async getIsPlayRainySoundInput() {
    return await this.isPlayRainySoundInput.getAttribute('value');
  }

  async setOffenseStartBgmInput(offenseStartBgm) {
    await this.offenseStartBgmInput.sendKeys(offenseStartBgm);
  }

  async getOffenseStartBgmInput() {
    return await this.offenseStartBgmInput.getAttribute('value');
  }

  async setOffenseStopBgmInput(offenseStopBgm) {
    await this.offenseStopBgmInput.sendKeys(offenseStopBgm);
  }

  async getOffenseStopBgmInput() {
    return await this.offenseStopBgmInput.getAttribute('value');
  }

  async setDefenseStartBgmInput(defenseStartBgm) {
    await this.defenseStartBgmInput.sendKeys(defenseStartBgm);
  }

  async getDefenseStartBgmInput() {
    return await this.defenseStartBgmInput.getAttribute('value');
  }

  async setDefenseStopBgmInput(defenseStopBgm) {
    await this.defenseStopBgmInput.sendKeys(defenseStopBgm);
  }

  async getDefenseStopBgmInput() {
    return await this.defenseStopBgmInput.getAttribute('value');
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

export class MMatchEnvironmentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMatchEnvironment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMatchEnvironment'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
