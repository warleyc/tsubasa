import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionTrajectoryPhoenixComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-trajectory-phoenix div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-trajectory-phoenix div h2#page-heading span')).first();

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

export class MGachaRenditionTrajectoryPhoenixUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-trajectory-phoenix-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  isPhoenixInput = element(by.id('field_isPhoenix'));
  weightInput = element(by.id('field_weight'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIsPhoenixInput(isPhoenix) {
    await this.isPhoenixInput.sendKeys(isPhoenix);
  }

  async getIsPhoenixInput() {
    return await this.isPhoenixInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
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

export class MGachaRenditionTrajectoryPhoenixDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionTrajectoryPhoenix-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionTrajectoryPhoenix'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
