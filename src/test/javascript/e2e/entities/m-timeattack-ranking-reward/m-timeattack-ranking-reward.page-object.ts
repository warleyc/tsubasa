import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTimeattackRankingRewardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-timeattack-ranking-reward div table .btn-danger'));
  title = element.all(by.css('jhi-m-timeattack-ranking-reward div h2#page-heading span')).first();

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

export class MTimeattackRankingRewardUpdatePage {
  pageTitle = element(by.id('jhi-m-timeattack-ranking-reward-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  eventIdInput = element(by.id('field_eventId'));
  rankingFromInput = element(by.id('field_rankingFrom'));
  rankingToInput = element(by.id('field_rankingTo'));
  rewardGroupIdInput = element(by.id('field_rewardGroupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEventIdInput(eventId) {
    await this.eventIdInput.sendKeys(eventId);
  }

  async getEventIdInput() {
    return await this.eventIdInput.getAttribute('value');
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

export class MTimeattackRankingRewardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTimeattackRankingReward-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTimeattackRankingReward'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
