import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MInitUserDeckComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-init-user-deck div table .btn-danger'));
  title = element.all(by.css('jhi-m-init-user-deck div h2#page-heading span')).first();

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

export class MInitUserDeckUpdatePage {
  pageTitle = element(by.id('jhi-m-init-user-deck-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  deckIdInput = element(by.id('field_deckId'));
  nameInput = element(by.id('field_name'));
  formationIdInput = element(by.id('field_formationId'));
  captainCardIdInput = element(by.id('field_captainCardId'));
  gkCardIdInput = element(by.id('field_gkCardId'));
  fp1CardIdInput = element(by.id('field_fp1CardId'));
  fp2CardIdInput = element(by.id('field_fp2CardId'));
  fp3CardIdInput = element(by.id('field_fp3CardId'));
  fp4CardIdInput = element(by.id('field_fp4CardId'));
  fp5CardIdInput = element(by.id('field_fp5CardId'));
  fp6CardIdInput = element(by.id('field_fp6CardId'));
  fp7CardIdInput = element(by.id('field_fp7CardId'));
  fp8CardIdInput = element(by.id('field_fp8CardId'));
  fp9CardIdInput = element(by.id('field_fp9CardId'));
  fp10CardIdInput = element(by.id('field_fp10CardId'));
  sub1CardIdInput = element(by.id('field_sub1CardId'));
  sub2CardIdInput = element(by.id('field_sub2CardId'));
  sub3CardIdInput = element(by.id('field_sub3CardId'));
  sub4CardIdInput = element(by.id('field_sub4CardId'));
  sub5CardIdInput = element(by.id('field_sub5CardId'));
  teamEffect1CardIdInput = element(by.id('field_teamEffect1CardId'));
  teamEffect2CardIdInput = element(by.id('field_teamEffect2CardId'));
  teamEffect3CardIdInput = element(by.id('field_teamEffect3CardId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setDeckIdInput(deckId) {
    await this.deckIdInput.sendKeys(deckId);
  }

  async getDeckIdInput() {
    return await this.deckIdInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setFormationIdInput(formationId) {
    await this.formationIdInput.sendKeys(formationId);
  }

  async getFormationIdInput() {
    return await this.formationIdInput.getAttribute('value');
  }

  async setCaptainCardIdInput(captainCardId) {
    await this.captainCardIdInput.sendKeys(captainCardId);
  }

  async getCaptainCardIdInput() {
    return await this.captainCardIdInput.getAttribute('value');
  }

  async setGkCardIdInput(gkCardId) {
    await this.gkCardIdInput.sendKeys(gkCardId);
  }

  async getGkCardIdInput() {
    return await this.gkCardIdInput.getAttribute('value');
  }

  async setFp1CardIdInput(fp1CardId) {
    await this.fp1CardIdInput.sendKeys(fp1CardId);
  }

  async getFp1CardIdInput() {
    return await this.fp1CardIdInput.getAttribute('value');
  }

  async setFp2CardIdInput(fp2CardId) {
    await this.fp2CardIdInput.sendKeys(fp2CardId);
  }

  async getFp2CardIdInput() {
    return await this.fp2CardIdInput.getAttribute('value');
  }

  async setFp3CardIdInput(fp3CardId) {
    await this.fp3CardIdInput.sendKeys(fp3CardId);
  }

  async getFp3CardIdInput() {
    return await this.fp3CardIdInput.getAttribute('value');
  }

  async setFp4CardIdInput(fp4CardId) {
    await this.fp4CardIdInput.sendKeys(fp4CardId);
  }

  async getFp4CardIdInput() {
    return await this.fp4CardIdInput.getAttribute('value');
  }

  async setFp5CardIdInput(fp5CardId) {
    await this.fp5CardIdInput.sendKeys(fp5CardId);
  }

  async getFp5CardIdInput() {
    return await this.fp5CardIdInput.getAttribute('value');
  }

  async setFp6CardIdInput(fp6CardId) {
    await this.fp6CardIdInput.sendKeys(fp6CardId);
  }

  async getFp6CardIdInput() {
    return await this.fp6CardIdInput.getAttribute('value');
  }

  async setFp7CardIdInput(fp7CardId) {
    await this.fp7CardIdInput.sendKeys(fp7CardId);
  }

  async getFp7CardIdInput() {
    return await this.fp7CardIdInput.getAttribute('value');
  }

  async setFp8CardIdInput(fp8CardId) {
    await this.fp8CardIdInput.sendKeys(fp8CardId);
  }

  async getFp8CardIdInput() {
    return await this.fp8CardIdInput.getAttribute('value');
  }

  async setFp9CardIdInput(fp9CardId) {
    await this.fp9CardIdInput.sendKeys(fp9CardId);
  }

  async getFp9CardIdInput() {
    return await this.fp9CardIdInput.getAttribute('value');
  }

  async setFp10CardIdInput(fp10CardId) {
    await this.fp10CardIdInput.sendKeys(fp10CardId);
  }

  async getFp10CardIdInput() {
    return await this.fp10CardIdInput.getAttribute('value');
  }

  async setSub1CardIdInput(sub1CardId) {
    await this.sub1CardIdInput.sendKeys(sub1CardId);
  }

  async getSub1CardIdInput() {
    return await this.sub1CardIdInput.getAttribute('value');
  }

  async setSub2CardIdInput(sub2CardId) {
    await this.sub2CardIdInput.sendKeys(sub2CardId);
  }

  async getSub2CardIdInput() {
    return await this.sub2CardIdInput.getAttribute('value');
  }

  async setSub3CardIdInput(sub3CardId) {
    await this.sub3CardIdInput.sendKeys(sub3CardId);
  }

  async getSub3CardIdInput() {
    return await this.sub3CardIdInput.getAttribute('value');
  }

  async setSub4CardIdInput(sub4CardId) {
    await this.sub4CardIdInput.sendKeys(sub4CardId);
  }

  async getSub4CardIdInput() {
    return await this.sub4CardIdInput.getAttribute('value');
  }

  async setSub5CardIdInput(sub5CardId) {
    await this.sub5CardIdInput.sendKeys(sub5CardId);
  }

  async getSub5CardIdInput() {
    return await this.sub5CardIdInput.getAttribute('value');
  }

  async setTeamEffect1CardIdInput(teamEffect1CardId) {
    await this.teamEffect1CardIdInput.sendKeys(teamEffect1CardId);
  }

  async getTeamEffect1CardIdInput() {
    return await this.teamEffect1CardIdInput.getAttribute('value');
  }

  async setTeamEffect2CardIdInput(teamEffect2CardId) {
    await this.teamEffect2CardIdInput.sendKeys(teamEffect2CardId);
  }

  async getTeamEffect2CardIdInput() {
    return await this.teamEffect2CardIdInput.getAttribute('value');
  }

  async setTeamEffect3CardIdInput(teamEffect3CardId) {
    await this.teamEffect3CardIdInput.sendKeys(teamEffect3CardId);
  }

  async getTeamEffect3CardIdInput() {
    return await this.teamEffect3CardIdInput.getAttribute('value');
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

export class MInitUserDeckDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mInitUserDeck-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mInitUserDeck'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
