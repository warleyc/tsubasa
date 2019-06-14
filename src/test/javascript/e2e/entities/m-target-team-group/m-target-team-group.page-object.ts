import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetTeamGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-team-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-team-group div h2#page-heading span')).first();

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

export class MTargetTeamGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-team-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  teamIdInput = element(by.id('field_teamId'));
  mteamSelect = element(by.id('field_mteam'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setTeamIdInput(teamId) {
    await this.teamIdInput.sendKeys(teamId);
  }

  async getTeamIdInput() {
    return await this.teamIdInput.getAttribute('value');
  }

  async mteamSelectLastOption(timeout?: number) {
    await this.mteamSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mteamSelectOption(option) {
    await this.mteamSelect.sendKeys(option);
  }

  getMteamSelect(): ElementFinder {
    return this.mteamSelect;
  }

  async getMteamSelectedOption() {
    return await this.mteamSelect.element(by.css('option:checked')).getText();
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

export class MTargetTeamGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetTeamGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetTeamGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
