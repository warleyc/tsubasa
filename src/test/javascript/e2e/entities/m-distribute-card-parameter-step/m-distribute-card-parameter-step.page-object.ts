import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDistributeCardParameterStepComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-distribute-card-parameter-step div table .btn-danger'));
  title = element.all(by.css('jhi-m-distribute-card-parameter-step div h2#page-heading span')).first();

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

export class MDistributeCardParameterStepUpdatePage {
  pageTitle = element(by.id('jhi-m-distribute-card-parameter-step-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isGkInput = element(by.id('field_isGk'));
  stepInput = element(by.id('field_step'));
  paramInput = element(by.id('field_param'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIsGkInput(isGk) {
    await this.isGkInput.sendKeys(isGk);
  }

  async getIsGkInput() {
    return await this.isGkInput.getAttribute('value');
  }

  async setStepInput(step) {
    await this.stepInput.sendKeys(step);
  }

  async getStepInput() {
    return await this.stepInput.getAttribute('value');
  }

  async setParamInput(param) {
    await this.paramInput.sendKeys(param);
  }

  async getParamInput() {
    return await this.paramInput.getAttribute('value');
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

export class MDistributeCardParameterStepDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDistributeCardParameterStep-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDistributeCardParameterStep'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
