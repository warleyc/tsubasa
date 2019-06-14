import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetTriggerEffectGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-trigger-effect-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-trigger-effect-group div h2#page-heading span')).first();

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

export class MTargetTriggerEffectGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-trigger-effect-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  triggerEffectIdInput = element(by.id('field_triggerEffectId'));
  mtriggereffectbaseSelect = element(by.id('field_mtriggereffectbase'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setTriggerEffectIdInput(triggerEffectId) {
    await this.triggerEffectIdInput.sendKeys(triggerEffectId);
  }

  async getTriggerEffectIdInput() {
    return await this.triggerEffectIdInput.getAttribute('value');
  }

  async mtriggereffectbaseSelectLastOption(timeout?: number) {
    await this.mtriggereffectbaseSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mtriggereffectbaseSelectOption(option) {
    await this.mtriggereffectbaseSelect.sendKeys(option);
  }

  getMtriggereffectbaseSelect(): ElementFinder {
    return this.mtriggereffectbaseSelect;
  }

  async getMtriggereffectbaseSelectedOption() {
    return await this.mtriggereffectbaseSelect.element(by.css('option:checked')).getText();
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

export class MTargetTriggerEffectGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetTriggerEffectGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetTriggerEffectGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
