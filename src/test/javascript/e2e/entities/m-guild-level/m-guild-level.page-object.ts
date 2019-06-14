import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildLevelComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-level div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-level div h2#page-heading span')).first();

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

export class MGuildLevelUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-level-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  levelInput = element(by.id('field_level'));
  expInput = element(by.id('field_exp'));
  memberCountInput = element(by.id('field_memberCount'));
  recommendMemberCountInput = element(by.id('field_recommendMemberCount'));
  guildMedalInput = element(by.id('field_guildMedal'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setExpInput(exp) {
    await this.expInput.sendKeys(exp);
  }

  async getExpInput() {
    return await this.expInput.getAttribute('value');
  }

  async setMemberCountInput(memberCount) {
    await this.memberCountInput.sendKeys(memberCount);
  }

  async getMemberCountInput() {
    return await this.memberCountInput.getAttribute('value');
  }

  async setRecommendMemberCountInput(recommendMemberCount) {
    await this.recommendMemberCountInput.sendKeys(recommendMemberCount);
  }

  async getRecommendMemberCountInput() {
    return await this.recommendMemberCountInput.getAttribute('value');
  }

  async setGuildMedalInput(guildMedal) {
    await this.guildMedalInput.sendKeys(guildMedal);
  }

  async getGuildMedalInput() {
    return await this.guildMedalInput.getAttribute('value');
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

export class MGuildLevelDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildLevel-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildLevel'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
