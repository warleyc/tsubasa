import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGachaRenditionTrajectoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-gacha-rendition-trajectory div table .btn-danger'));
  title = element.all(by.css('jhi-m-gacha-rendition-trajectory div h2#page-heading span')).first();

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

export class MGachaRenditionTrajectoryUpdatePage {
  pageTitle = element(by.id('jhi-m-gacha-rendition-trajectory-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  weightInput = element(by.id('field_weight'));
  trajectoryTypeInput = element(by.id('field_trajectoryType'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setTrajectoryTypeInput(trajectoryType) {
    await this.trajectoryTypeInput.sendKeys(trajectoryType);
  }

  async getTrajectoryTypeInput() {
    return await this.trajectoryTypeInput.getAttribute('value');
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

export class MGachaRenditionTrajectoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGachaRenditionTrajectory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGachaRenditionTrajectory'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
