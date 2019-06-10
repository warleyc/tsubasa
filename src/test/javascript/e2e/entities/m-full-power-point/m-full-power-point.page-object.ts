import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MFullPowerPointComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-full-power-point div table .btn-danger'));
  title = element.all(by.css('jhi-m-full-power-point div h2#page-heading span')).first();

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

export class MFullPowerPointUpdatePage {
  pageTitle = element(by.id('jhi-m-full-power-point-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  pointTypeInput = element(by.id('field_pointType'));
  valueInput = element(by.id('field_value'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPointTypeInput(pointType) {
    await this.pointTypeInput.sendKeys(pointType);
  }

  async getPointTypeInput() {
    return await this.pointTypeInput.getAttribute('value');
  }

  async setValueInput(value) {
    await this.valueInput.sendKeys(value);
  }

  async getValueInput() {
    return await this.valueInput.getAttribute('value');
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

export class MFullPowerPointDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mFullPowerPoint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mFullPowerPoint'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
