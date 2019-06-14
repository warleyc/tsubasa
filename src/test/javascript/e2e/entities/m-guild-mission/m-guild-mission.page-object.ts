import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MGuildMissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-guild-mission div table .btn-danger'));
  title = element.all(by.css('jhi-m-guild-mission div h2#page-heading span')).first();

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

export class MGuildMissionUpdatePage {
  pageTitle = element(by.id('jhi-m-guild-mission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  termInput = element(by.id('field_term'));
  titleInput = element(by.id('field_title'));
  descriptionInput = element(by.id('field_description'));
  missionTypeInput = element(by.id('field_missionType'));
  param1Input = element(by.id('field_param1'));
  rewardIdInput = element(by.id('field_rewardId'));
  linkInput = element(by.id('field_link'));
  sceneTransitionParamInput = element(by.id('field_sceneTransitionParam'));
  pickupInput = element(by.id('field_pickup'));
  triggerMissionIdInput = element(by.id('field_triggerMissionId'));
  orderNumInput = element(by.id('field_orderNum'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTermInput(term) {
    await this.termInput.sendKeys(term);
  }

  async getTermInput() {
    return await this.termInput.getAttribute('value');
  }

  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return await this.titleInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setMissionTypeInput(missionType) {
    await this.missionTypeInput.sendKeys(missionType);
  }

  async getMissionTypeInput() {
    return await this.missionTypeInput.getAttribute('value');
  }

  async setParam1Input(param1) {
    await this.param1Input.sendKeys(param1);
  }

  async getParam1Input() {
    return await this.param1Input.getAttribute('value');
  }

  async setRewardIdInput(rewardId) {
    await this.rewardIdInput.sendKeys(rewardId);
  }

  async getRewardIdInput() {
    return await this.rewardIdInput.getAttribute('value');
  }

  async setLinkInput(link) {
    await this.linkInput.sendKeys(link);
  }

  async getLinkInput() {
    return await this.linkInput.getAttribute('value');
  }

  async setSceneTransitionParamInput(sceneTransitionParam) {
    await this.sceneTransitionParamInput.sendKeys(sceneTransitionParam);
  }

  async getSceneTransitionParamInput() {
    return await this.sceneTransitionParamInput.getAttribute('value');
  }

  async setPickupInput(pickup) {
    await this.pickupInput.sendKeys(pickup);
  }

  async getPickupInput() {
    return await this.pickupInput.getAttribute('value');
  }

  async setTriggerMissionIdInput(triggerMissionId) {
    await this.triggerMissionIdInput.sendKeys(triggerMissionId);
  }

  async getTriggerMissionIdInput() {
    return await this.triggerMissionIdInput.getAttribute('value');
  }

  async setOrderNumInput(orderNum) {
    await this.orderNumInput.sendKeys(orderNum);
  }

  async getOrderNumInput() {
    return await this.orderNumInput.getAttribute('value');
  }

  async idSelectLastOption(timeout?: number) {
    await this.idSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async idSelectOption(option) {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption() {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class MGuildMissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mGuildMission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mGuildMission'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
