import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MAchievementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-achievement div table .btn-danger'));
  title = element.all(by.css('jhi-m-achievement div h2#page-heading span')).first();

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

export class MAchievementUpdatePage {
  pageTitle = element(by.id('jhi-m-achievement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  achievementIdInput = element(by.id('field_achievementId'));
  nameInput = element(by.id('field_name'));
  typeInput = element(by.id('field_type'));
  missionIdInput = element(by.id('field_missionId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAchievementIdInput(achievementId) {
    await this.achievementIdInput.sendKeys(achievementId);
  }

  async getAchievementIdInput() {
    return await this.achievementIdInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return await this.typeInput.getAttribute('value');
  }

  async setMissionIdInput(missionId) {
    await this.missionIdInput.sendKeys(missionId);
  }

  async getMissionIdInput() {
    return await this.missionIdInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MAchievementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAchievement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAchievement'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
