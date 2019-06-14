import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MQuestClearRewardWeightComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-quest-clear-reward-weight div table .btn-danger'));
  title = element.all(by.css('jhi-m-quest-clear-reward-weight div h2#page-heading span')).first();

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

export class MQuestClearRewardWeightUpdatePage {
  pageTitle = element(by.id('jhi-m-quest-clear-reward-weight-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  reward1Input = element(by.id('field_reward1'));
  reward2Input = element(by.id('field_reward2'));
  reward3Input = element(by.id('field_reward3'));
  reward4Input = element(by.id('field_reward4'));
  reward5Input = element(by.id('field_reward5'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setReward1Input(reward1) {
    await this.reward1Input.sendKeys(reward1);
  }

  async getReward1Input() {
    return await this.reward1Input.getAttribute('value');
  }

  async setReward2Input(reward2) {
    await this.reward2Input.sendKeys(reward2);
  }

  async getReward2Input() {
    return await this.reward2Input.getAttribute('value');
  }

  async setReward3Input(reward3) {
    await this.reward3Input.sendKeys(reward3);
  }

  async getReward3Input() {
    return await this.reward3Input.getAttribute('value');
  }

  async setReward4Input(reward4) {
    await this.reward4Input.sendKeys(reward4);
  }

  async getReward4Input() {
    return await this.reward4Input.getAttribute('value');
  }

  async setReward5Input(reward5) {
    await this.reward5Input.sendKeys(reward5);
  }

  async getReward5Input() {
    return await this.reward5Input.getAttribute('value');
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

export class MQuestClearRewardWeightDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mQuestClearRewardWeight-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mQuestClearRewardWeight'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
