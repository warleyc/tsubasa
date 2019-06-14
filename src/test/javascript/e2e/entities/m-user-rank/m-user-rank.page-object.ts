import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MUserRankComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-user-rank div table .btn-danger'));
  title = element.all(by.css('jhi-m-user-rank div h2#page-heading span')).first();

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

export class MUserRankUpdatePage {
  pageTitle = element(by.id('jhi-m-user-rank-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rankInput = element(by.id('field_rank'));
  expInput = element(by.id('field_exp'));
  questApInput = element(by.id('field_questAp'));
  maxFriendCountInput = element(by.id('field_maxFriendCount'));
  rankupSerifInput = element(by.id('field_rankupSerif'));
  charaAssetNameInput = element(by.id('field_charaAssetName'));
  voiceCharaIdInput = element(by.id('field_voiceCharaId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRankInput(rank) {
    await this.rankInput.sendKeys(rank);
  }

  async getRankInput() {
    return await this.rankInput.getAttribute('value');
  }

  async setExpInput(exp) {
    await this.expInput.sendKeys(exp);
  }

  async getExpInput() {
    return await this.expInput.getAttribute('value');
  }

  async setQuestApInput(questAp) {
    await this.questApInput.sendKeys(questAp);
  }

  async getQuestApInput() {
    return await this.questApInput.getAttribute('value');
  }

  async setMaxFriendCountInput(maxFriendCount) {
    await this.maxFriendCountInput.sendKeys(maxFriendCount);
  }

  async getMaxFriendCountInput() {
    return await this.maxFriendCountInput.getAttribute('value');
  }

  async setRankupSerifInput(rankupSerif) {
    await this.rankupSerifInput.sendKeys(rankupSerif);
  }

  async getRankupSerifInput() {
    return await this.rankupSerifInput.getAttribute('value');
  }

  async setCharaAssetNameInput(charaAssetName) {
    await this.charaAssetNameInput.sendKeys(charaAssetName);
  }

  async getCharaAssetNameInput() {
    return await this.charaAssetNameInput.getAttribute('value');
  }

  async setVoiceCharaIdInput(voiceCharaId) {
    await this.voiceCharaIdInput.sendKeys(voiceCharaId);
  }

  async getVoiceCharaIdInput() {
    return await this.voiceCharaIdInput.getAttribute('value');
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

export class MUserRankDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mUserRank-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mUserRank'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
