import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MMissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-mission div table .btn-danger'));
  title = element.all(by.css('jhi-m-mission div h2#page-heading span')).first();

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

export class MMissionUpdatePage {
  pageTitle = element(by.id('jhi-m-mission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  termInput = element(by.id('field_term'));
  roundNumInput = element(by.id('field_roundNum'));
  titleInput = element(by.id('field_title'));
  descriptionInput = element(by.id('field_description'));
  missionTypeInput = element(by.id('field_missionType'));
  absoluteInput = element(by.id('field_absolute'));
  param1Input = element(by.id('field_param1'));
  param2Input = element(by.id('field_param2'));
  param3Input = element(by.id('field_param3'));
  param4Input = element(by.id('field_param4'));
  param5Input = element(by.id('field_param5'));
  triggerInput = element(by.id('field_trigger'));
  triggerConditionInput = element(by.id('field_triggerCondition'));
  rewardIdInput = element(by.id('field_rewardId'));
  startAtInput = element(by.id('field_startAt'));
  endAtInput = element(by.id('field_endAt'));
  linkInput = element(by.id('field_link'));
  sceneTransitionParamInput = element(by.id('field_sceneTransitionParam'));
  pickupInput = element(by.id('field_pickup'));
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

  async setRoundNumInput(roundNum) {
    await this.roundNumInput.sendKeys(roundNum);
  }

  async getRoundNumInput() {
    return await this.roundNumInput.getAttribute('value');
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

  async setAbsoluteInput(absolute) {
    await this.absoluteInput.sendKeys(absolute);
  }

  async getAbsoluteInput() {
    return await this.absoluteInput.getAttribute('value');
  }

  async setParam1Input(param1) {
    await this.param1Input.sendKeys(param1);
  }

  async getParam1Input() {
    return await this.param1Input.getAttribute('value');
  }

  async setParam2Input(param2) {
    await this.param2Input.sendKeys(param2);
  }

  async getParam2Input() {
    return await this.param2Input.getAttribute('value');
  }

  async setParam3Input(param3) {
    await this.param3Input.sendKeys(param3);
  }

  async getParam3Input() {
    return await this.param3Input.getAttribute('value');
  }

  async setParam4Input(param4) {
    await this.param4Input.sendKeys(param4);
  }

  async getParam4Input() {
    return await this.param4Input.getAttribute('value');
  }

  async setParam5Input(param5) {
    await this.param5Input.sendKeys(param5);
  }

  async getParam5Input() {
    return await this.param5Input.getAttribute('value');
  }

  async setTriggerInput(trigger) {
    await this.triggerInput.sendKeys(trigger);
  }

  async getTriggerInput() {
    return await this.triggerInput.getAttribute('value');
  }

  async setTriggerConditionInput(triggerCondition) {
    await this.triggerConditionInput.sendKeys(triggerCondition);
  }

  async getTriggerConditionInput() {
    return await this.triggerConditionInput.getAttribute('value');
  }

  async setRewardIdInput(rewardId) {
    await this.rewardIdInput.sendKeys(rewardId);
  }

  async getRewardIdInput() {
    return await this.rewardIdInput.getAttribute('value');
  }

  async setStartAtInput(startAt) {
    await this.startAtInput.sendKeys(startAt);
  }

  async getStartAtInput() {
    return await this.startAtInput.getAttribute('value');
  }

  async setEndAtInput(endAt) {
    await this.endAtInput.sendKeys(endAt);
  }

  async getEndAtInput() {
    return await this.endAtInput.getAttribute('value');
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

export class MMissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mMission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mMission'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
