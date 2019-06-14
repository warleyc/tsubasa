import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLeagueRankingRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-league-ranking-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-league-ranking-reward div h2#page-heading span')).first();

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

export class MLeagueRankingRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-league-ranking-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  leagueHierarchyInput = element(by.id('field_leagueHierarchy'));
  rankToInput = element(by.id('field_rankTo'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setLeagueHierarchyInput(leagueHierarchy) {
    await this.leagueHierarchyInput.sendKeys(leagueHierarchy);
  }

  async getLeagueHierarchyInput() {
    return await this.leagueHierarchyInput.getAttribute('value');
  }

  async setRankToInput(rankTo) {
    await this.rankToInput.sendKeys(rankTo);
  }

  async getRankToInput() {
    return await this.rankToInput.getAttribute('value');
  }

  async setRewardGroupIdInput(rewardGroupId) {
    await this.rewardGroupIdInput.sendKeys(rewardGroupId);
  }

  async getRewardGroupIdInput() {
    return await this.rewardGroupIdInput.getAttribute('value');
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

export class MLeagueRankingRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLeagueRankingReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLeagueRankingReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
