import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPvpRankingRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-pvp-ranking-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-pvp-ranking-reward div h2#page-heading span')).first();

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

export class MPvpRankingRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-pvp-ranking-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  waveIdInput = element(by.id('field_waveId'));
  difficultyInput = element(by.id('field_difficulty'));
  rankingFromInput = element(by.id('field_rankingFrom'));
  rankingToInput = element(by.id('field_rankingTo'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWaveIdInput(waveId) {
    await this.waveIdInput.sendKeys(waveId);
  }

  async getWaveIdInput() {
    return await this.waveIdInput.getAttribute('value');
  }

  async setDifficultyInput(difficulty) {
    await this.difficultyInput.sendKeys(difficulty);
  }

  async getDifficultyInput() {
    return await this.difficultyInput.getAttribute('value');
  }

  async setRankingFromInput(rankingFrom) {
    await this.rankingFromInput.sendKeys(rankingFrom);
  }

  async getRankingFromInput() {
    return await this.rankingFromInput.getAttribute('value');
  }

  async setRankingToInput(rankingTo) {
    await this.rankingToInput.sendKeys(rankingTo);
  }

  async getRankingToInput() {
    return await this.rankingToInput.getAttribute('value');
  }

  async setRewardGroupIdInput(rewardGroupId) {
    await this.rewardGroupIdInput.sendKeys(rewardGroupId);
  }

  async getRewardGroupIdInput() {
    return await this.rewardGroupIdInput.getAttribute('value');
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

export class MPvpRankingRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPvpRankingReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPvpRankingReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
