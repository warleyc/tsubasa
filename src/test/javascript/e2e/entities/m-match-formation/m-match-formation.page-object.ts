import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMatchFormationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-match-formation div table .btn-danger'));
  title = element.all(by.css('jhi-m-match-formation div h2#page-heading span')).first();

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

export class MMatchFormationUpdatePage {
  pageTitle = element(by.id('jhi-m-match-formation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  position1Input = element(by.id('field_position1'));
  position2Input = element(by.id('field_position2'));
  position3Input = element(by.id('field_position3'));
  position4Input = element(by.id('field_position4'));
  position5Input = element(by.id('field_position5'));
  position6Input = element(by.id('field_position6'));
  position7Input = element(by.id('field_position7'));
  position8Input = element(by.id('field_position8'));
  position9Input = element(by.id('field_position9'));
  position10Input = element(by.id('field_position10'));
  position11Input = element(by.id('field_position11'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setPosition1Input(position1) {
    await this.position1Input.sendKeys(position1);
  }

  async getPosition1Input() {
    return await this.position1Input.getAttribute('value');
  }

  async setPosition2Input(position2) {
    await this.position2Input.sendKeys(position2);
  }

  async getPosition2Input() {
    return await this.position2Input.getAttribute('value');
  }

  async setPosition3Input(position3) {
    await this.position3Input.sendKeys(position3);
  }

  async getPosition3Input() {
    return await this.position3Input.getAttribute('value');
  }

  async setPosition4Input(position4) {
    await this.position4Input.sendKeys(position4);
  }

  async getPosition4Input() {
    return await this.position4Input.getAttribute('value');
  }

  async setPosition5Input(position5) {
    await this.position5Input.sendKeys(position5);
  }

  async getPosition5Input() {
    return await this.position5Input.getAttribute('value');
  }

  async setPosition6Input(position6) {
    await this.position6Input.sendKeys(position6);
  }

  async getPosition6Input() {
    return await this.position6Input.getAttribute('value');
  }

  async setPosition7Input(position7) {
    await this.position7Input.sendKeys(position7);
  }

  async getPosition7Input() {
    return await this.position7Input.getAttribute('value');
  }

  async setPosition8Input(position8) {
    await this.position8Input.sendKeys(position8);
  }

  async getPosition8Input() {
    return await this.position8Input.getAttribute('value');
  }

  async setPosition9Input(position9) {
    await this.position9Input.sendKeys(position9);
  }

  async getPosition9Input() {
    return await this.position9Input.getAttribute('value');
  }

  async setPosition10Input(position10) {
    await this.position10Input.sendKeys(position10);
  }

  async getPosition10Input() {
    return await this.position10Input.getAttribute('value');
  }

  async setPosition11Input(position11) {
    await this.position11Input.sendKeys(position11);
  }

  async getPosition11Input() {
    return await this.position11Input.getAttribute('value');
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

export class MMatchFormationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMatchFormation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMatchFormation'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
