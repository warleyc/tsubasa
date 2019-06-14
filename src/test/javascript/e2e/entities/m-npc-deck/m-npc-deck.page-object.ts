import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MNpcDeckComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-npc-deck div table .btn-danger'));
  title = element.all(by.css('jhi-m-npc-deck div h2#page-heading span')).first();

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

export class MNpcDeckUpdatePage {
  pageTitle = element(by.id('jhi-m-npc-deck-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  teamNameInput = element(by.id('field_teamName'));
  uniformBottomFpInput = element(by.id('field_uniformBottomFp'));
  uniformUpFpInput = element(by.id('field_uniformUpFp'));
  uniformBottomGkInput = element(by.id('field_uniformBottomGk'));
  uniformUpGkInput = element(by.id('field_uniformUpGk'));
  formationIdInput = element(by.id('field_formationId'));
  captainCardIdInput = element(by.id('field_captainCardId'));
  teamEffect1CardIdInput = element(by.id('field_teamEffect1CardId'));
  teamEffect2CardIdInput = element(by.id('field_teamEffect2CardId'));
  teamEffect3CardIdInput = element(by.id('field_teamEffect3CardId'));
  npcCardId1Input = element(by.id('field_npcCardId1'));
  npcCardId2Input = element(by.id('field_npcCardId2'));
  npcCardId3Input = element(by.id('field_npcCardId3'));
  npcCardId4Input = element(by.id('field_npcCardId4'));
  npcCardId5Input = element(by.id('field_npcCardId5'));
  npcCardId6Input = element(by.id('field_npcCardId6'));
  npcCardId7Input = element(by.id('field_npcCardId7'));
  npcCardId8Input = element(by.id('field_npcCardId8'));
  npcCardId9Input = element(by.id('field_npcCardId9'));
  npcCardId10Input = element(by.id('field_npcCardId10'));
  npcCardId11Input = element(by.id('field_npcCardId11'));
  tickInput = element(by.id('field_tick'));
  mformationSelect = element(by.id('field_mformation'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTeamNameInput(teamName) {
    await this.teamNameInput.sendKeys(teamName);
  }

  async getTeamNameInput() {
    return await this.teamNameInput.getAttribute('value');
  }

  async setUniformBottomFpInput(uniformBottomFp) {
    await this.uniformBottomFpInput.sendKeys(uniformBottomFp);
  }

  async getUniformBottomFpInput() {
    return await this.uniformBottomFpInput.getAttribute('value');
  }

  async setUniformUpFpInput(uniformUpFp) {
    await this.uniformUpFpInput.sendKeys(uniformUpFp);
  }

  async getUniformUpFpInput() {
    return await this.uniformUpFpInput.getAttribute('value');
  }

  async setUniformBottomGkInput(uniformBottomGk) {
    await this.uniformBottomGkInput.sendKeys(uniformBottomGk);
  }

  async getUniformBottomGkInput() {
    return await this.uniformBottomGkInput.getAttribute('value');
  }

  async setUniformUpGkInput(uniformUpGk) {
    await this.uniformUpGkInput.sendKeys(uniformUpGk);
  }

  async getUniformUpGkInput() {
    return await this.uniformUpGkInput.getAttribute('value');
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

  async setNpcCardId1Input(npcCardId1) {
    await this.npcCardId1Input.sendKeys(npcCardId1);
  }

  async getNpcCardId1Input() {
    return await this.npcCardId1Input.getAttribute('value');
  }

  async setNpcCardId2Input(npcCardId2) {
    await this.npcCardId2Input.sendKeys(npcCardId2);
  }

  async getNpcCardId2Input() {
    return await this.npcCardId2Input.getAttribute('value');
  }

  async setNpcCardId3Input(npcCardId3) {
    await this.npcCardId3Input.sendKeys(npcCardId3);
  }

  async getNpcCardId3Input() {
    return await this.npcCardId3Input.getAttribute('value');
  }

  async setNpcCardId4Input(npcCardId4) {
    await this.npcCardId4Input.sendKeys(npcCardId4);
  }

  async getNpcCardId4Input() {
    return await this.npcCardId4Input.getAttribute('value');
  }

  async setNpcCardId5Input(npcCardId5) {
    await this.npcCardId5Input.sendKeys(npcCardId5);
  }

  async getNpcCardId5Input() {
    return await this.npcCardId5Input.getAttribute('value');
  }

  async setNpcCardId6Input(npcCardId6) {
    await this.npcCardId6Input.sendKeys(npcCardId6);
  }

  async getNpcCardId6Input() {
    return await this.npcCardId6Input.getAttribute('value');
  }

  async setNpcCardId7Input(npcCardId7) {
    await this.npcCardId7Input.sendKeys(npcCardId7);
  }

  async getNpcCardId7Input() {
    return await this.npcCardId7Input.getAttribute('value');
  }

  async setNpcCardId8Input(npcCardId8) {
    await this.npcCardId8Input.sendKeys(npcCardId8);
  }

  async getNpcCardId8Input() {
    return await this.npcCardId8Input.getAttribute('value');
  }

  async setNpcCardId9Input(npcCardId9) {
    await this.npcCardId9Input.sendKeys(npcCardId9);
  }

  async getNpcCardId9Input() {
    return await this.npcCardId9Input.getAttribute('value');
  }

  async setNpcCardId10Input(npcCardId10) {
    await this.npcCardId10Input.sendKeys(npcCardId10);
  }

  async getNpcCardId10Input() {
    return await this.npcCardId10Input.getAttribute('value');
  }

  async setNpcCardId11Input(npcCardId11) {
    await this.npcCardId11Input.sendKeys(npcCardId11);
  }

  async getNpcCardId11Input() {
    return await this.npcCardId11Input.getAttribute('value');
  }

  async setTickInput(tick) {
    await this.tickInput.sendKeys(tick);
  }

  async getTickInput() {
    return await this.tickInput.getAttribute('value');
  }

  async mformationSelectLastOption(timeout?: number) {
    await this.mformationSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async mformationSelectOption(option) {
    await this.mformationSelect.sendKeys(option);
  }

  getMformationSelect(): ElementFinder {
    return this.mformationSelect;
  }

  async getMformationSelectedOption() {
    return await this.mformationSelect.element(by.css('option:checked')).getText();
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

export class MNpcDeckDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mNpcDeck-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mNpcDeck'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
