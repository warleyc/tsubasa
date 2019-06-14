import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPowerupActionSkillCostComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-powerup-action-skill-cost div table .btn-danger'));
  title = element.all(by.css('jhi-m-powerup-action-skill-cost div h2#page-heading span')).first();

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

export class MPowerupActionSkillCostUpdatePage {
  pageTitle = element(by.id('jhi-m-powerup-action-skill-cost-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  actionRarityInput = element(by.id('field_actionRarity'));
  actionLevelInput = element(by.id('field_actionLevel'));
  costInput = element(by.id('field_cost'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setActionRarityInput(actionRarity) {
    await this.actionRarityInput.sendKeys(actionRarity);
  }

  async getActionRarityInput() {
    return await this.actionRarityInput.getAttribute('value');
  }

  async setActionLevelInput(actionLevel) {
    await this.actionLevelInput.sendKeys(actionLevel);
  }

  async getActionLevelInput() {
    return await this.actionLevelInput.getAttribute('value');
  }

  async setCostInput(cost) {
    await this.costInput.sendKeys(cost);
  }

  async getCostInput() {
    return await this.costInput.getAttribute('value');
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

export class MPowerupActionSkillCostDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPowerupActionSkillCost-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPowerupActionSkillCost'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
