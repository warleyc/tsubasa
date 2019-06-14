import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MShootComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-shoot div table .btn-danger'));
  title = element.all(by.css('jhi-m-shoot div h2#page-heading span')).first();

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

export class MShootUpdatePage {
  pageTitle = element(by.id('jhi-m-shoot-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  angleDecayTypeInput = element(by.id('field_angleDecayType'));
  shootOrbitInput = element(by.id('field_shootOrbit'));
  judgementIdInput = element(by.id('field_judgementId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setAngleDecayTypeInput(angleDecayType) {
    await this.angleDecayTypeInput.sendKeys(angleDecayType);
  }

  async getAngleDecayTypeInput() {
    return await this.angleDecayTypeInput.getAttribute('value');
  }

  async setShootOrbitInput(shootOrbit) {
    await this.shootOrbitInput.sendKeys(shootOrbit);
  }

  async getShootOrbitInput() {
    return await this.shootOrbitInput.getAttribute('value');
  }

  async setJudgementIdInput(judgementId) {
    await this.judgementIdInput.sendKeys(judgementId);
  }

  async getJudgementIdInput() {
    return await this.judgementIdInput.getAttribute('value');
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

export class MShootDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mShoot-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mShoot'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
