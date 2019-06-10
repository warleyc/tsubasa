import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCutShootOrbitComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-cut-shoot-orbit div table .btn-danger'));
  title = element.all(by.css('jhi-m-cut-shoot-orbit div h2#page-heading span')).first();

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

export class MCutShootOrbitUpdatePage {
  pageTitle = element(by.id('jhi-m-cut-shoot-orbit-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  shootOrbitInput = element(by.id('field_shootOrbit'));
  shootResultInput = element(by.id('field_shootResult'));
  offenseSequenceTextInput = element(by.id('field_offenseSequenceText'));
  defenseSequenceTextInput = element(by.id('field_defenseSequenceText'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setShootOrbitInput(shootOrbit) {
    await this.shootOrbitInput.sendKeys(shootOrbit);
  }

  async getShootOrbitInput() {
    return await this.shootOrbitInput.getAttribute('value');
  }

  async setShootResultInput(shootResult) {
    await this.shootResultInput.sendKeys(shootResult);
  }

  async getShootResultInput() {
    return await this.shootResultInput.getAttribute('value');
  }

  async setOffenseSequenceTextInput(offenseSequenceText) {
    await this.offenseSequenceTextInput.sendKeys(offenseSequenceText);
  }

  async getOffenseSequenceTextInput() {
    return await this.offenseSequenceTextInput.getAttribute('value');
  }

  async setDefenseSequenceTextInput(defenseSequenceText) {
    await this.defenseSequenceTextInput.sendKeys(defenseSequenceText);
  }

  async getDefenseSequenceTextInput() {
    return await this.defenseSequenceTextInput.getAttribute('value');
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

export class MCutShootOrbitDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCutShootOrbit-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCutShootOrbit'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
