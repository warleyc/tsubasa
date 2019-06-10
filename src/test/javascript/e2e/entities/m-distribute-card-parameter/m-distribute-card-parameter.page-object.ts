import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDistributeCardParameterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-distribute-card-parameter div table .btn-danger'));
  title = element.all(by.css('jhi-m-distribute-card-parameter div h2#page-heading span')).first();

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

export class MDistributeCardParameterUpdatePage {
  pageTitle = element(by.id('jhi-m-distribute-card-parameter-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  rarityInput = element(by.id('field_rarity'));
  isGkInput = element(by.id('field_isGk'));
  maxStepCountInput = element(by.id('field_maxStepCount'));
  maxTotalStepCountInput = element(by.id('field_maxTotalStepCount'));
  distributePointByStepInput = element(by.id('field_distributePointByStep'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setIsGkInput(isGk) {
    await this.isGkInput.sendKeys(isGk);
  }

  async getIsGkInput() {
    return await this.isGkInput.getAttribute('value');
  }

  async setMaxStepCountInput(maxStepCount) {
    await this.maxStepCountInput.sendKeys(maxStepCount);
  }

  async getMaxStepCountInput() {
    return await this.maxStepCountInput.getAttribute('value');
  }

  async setMaxTotalStepCountInput(maxTotalStepCount) {
    await this.maxTotalStepCountInput.sendKeys(maxTotalStepCount);
  }

  async getMaxTotalStepCountInput() {
    return await this.maxTotalStepCountInput.getAttribute('value');
  }

  async setDistributePointByStepInput(distributePointByStep) {
    await this.distributePointByStepInput.sendKeys(distributePointByStep);
  }

  async getDistributePointByStepInput() {
    return await this.distributePointByStepInput.getAttribute('value');
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

export class MDistributeCardParameterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDistributeCardParameter-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDistributeCardParameter'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
