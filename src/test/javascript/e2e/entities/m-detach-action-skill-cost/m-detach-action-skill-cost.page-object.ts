import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDetachActionSkillCostComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-detach-action-skill-cost div table .btn-danger'));
  title = element.all(by.css('jhi-m-detach-action-skill-cost div h2#page-heading span')).first();

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

export class MDetachActionSkillCostUpdatePage {
  pageTitle = element(by.id('jhi-m-detach-action-skill-cost-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityInput = element(by.id('field_rarity'));
  costInput = element(by.id('field_cost'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
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

export class MDetachActionSkillCostDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDetachActionSkillCost-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDetachActionSkillCost'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
