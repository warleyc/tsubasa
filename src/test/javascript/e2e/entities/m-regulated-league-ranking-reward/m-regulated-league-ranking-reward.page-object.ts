import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MRegulatedLeagueRankingRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-regulated-league-ranking-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-regulated-league-ranking-reward div h2#page-heading span')).first();

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

export class MRegulatedLeagueRankingRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-regulated-league-ranking-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  regulatedLeagueIdInput = element(by.id('field_regulatedLeagueId'));
  leagueHierarchyInput = element(by.id('field_leagueHierarchy'));
  rankToInput = element(by.id('field_rankTo'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRegulatedLeagueIdInput(regulatedLeagueId) {
    await this.regulatedLeagueIdInput.sendKeys(regulatedLeagueId);
  }

  async getRegulatedLeagueIdInput() {
    return await this.regulatedLeagueIdInput.getAttribute('value');
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

export class MRegulatedLeagueRankingRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mRegulatedLeagueRankingReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mRegulatedLeagueRankingReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
