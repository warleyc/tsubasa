import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MActionSkillHolderCardContentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-action-skill-holder-card-content div table .btn-danger'));
  title = element.all(by.css('jhi-m-action-skill-holder-card-content div h2#page-heading span')).first();

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

export class MActionSkillHolderCardContentUpdatePage {
  pageTitle = element(by.id('jhi-m-action-skill-holder-card-content-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  targetCharaIdInput = element(by.id('field_targetCharaId'));
  actionMasterIdInput = element(by.id('field_actionMasterId'));
  actionSkillExpInput = element(by.id('field_actionSkillExp'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  mcharacterSelect = element(by.id('field_mcharacter'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTargetCharaIdInput(targetCharaId) {
    await this.targetCharaIdInput.sendKeys(targetCharaId);
  }

  async getTargetCharaIdInput() {
    return await this.targetCharaIdInput.getAttribute('value');
  }

  async setActionMasterIdInput(actionMasterId) {
    await this.actionMasterIdInput.sendKeys(actionMasterId);
  }

  async getActionMasterIdInput() {
    return await this.actionMasterIdInput.getAttribute('value');
  }

  async setActionSkillExpInput(actionSkillExp) {
    await this.actionSkillExpInput.sendKeys(actionSkillExp);
  }

  async getActionSkillExpInput() {
    return await this.actionSkillExpInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async mcharacterSelectLastOption(timeout?: number) {
    await this.mcharacterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mcharacterSelectOption(option) {
    await this.mcharacterSelect.sendKeys(option);
  }

  getMcharacterSelect(): ElementFinder {
    return this.mcharacterSelect;
  }

  async getMcharacterSelectedOption() {
    return await this.mcharacterSelect.element(by.css('option:checked')).getText();
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

export class MActionSkillHolderCardContentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mActionSkillHolderCardContent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mActionSkillHolderCardContent'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
