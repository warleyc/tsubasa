import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MLeagueComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-league div table .btn-danger'));
  title = element.all(by.css('jhi-m-league div h2#page-heading span')).first();

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

export class MLeagueUpdatePage {
  pageTitle = element(by.id('jhi-m-league-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  hierarchyInput = element(by.id('field_hierarchy'));
  weightInput = element(by.id('field_weight'));
  nameInput = element(by.id('field_name'));
  roomsInput = element(by.id('field_rooms'));
  promotionRateInput = element(by.id('field_promotionRate'));
  demotionRateInput = element(by.id('field_demotionRate'));
  totalParameterRangeUpperInput = element(by.id('field_totalParameterRangeUpper'));
  totalParameterRangeLowerInput = element(by.id('field_totalParameterRangeLower'));
  requiredMatchesInput = element(by.id('field_requiredMatches'));
  startWeekIdInput = element(by.id('field_startWeekId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setHierarchyInput(hierarchy) {
    await this.hierarchyInput.sendKeys(hierarchy);
  }

  async getHierarchyInput() {
    return await this.hierarchyInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setRoomsInput(rooms) {
    await this.roomsInput.sendKeys(rooms);
  }

  async getRoomsInput() {
    return await this.roomsInput.getAttribute('value');
  }

  async setPromotionRateInput(promotionRate) {
    await this.promotionRateInput.sendKeys(promotionRate);
  }

  async getPromotionRateInput() {
    return await this.promotionRateInput.getAttribute('value');
  }

  async setDemotionRateInput(demotionRate) {
    await this.demotionRateInput.sendKeys(demotionRate);
  }

  async getDemotionRateInput() {
    return await this.demotionRateInput.getAttribute('value');
  }

  async setTotalParameterRangeUpperInput(totalParameterRangeUpper) {
    await this.totalParameterRangeUpperInput.sendKeys(totalParameterRangeUpper);
  }

  async getTotalParameterRangeUpperInput() {
    return await this.totalParameterRangeUpperInput.getAttribute('value');
  }

  async setTotalParameterRangeLowerInput(totalParameterRangeLower) {
    await this.totalParameterRangeLowerInput.sendKeys(totalParameterRangeLower);
  }

  async getTotalParameterRangeLowerInput() {
    return await this.totalParameterRangeLowerInput.getAttribute('value');
  }

  async setRequiredMatchesInput(requiredMatches) {
    await this.requiredMatchesInput.sendKeys(requiredMatches);
  }

  async getRequiredMatchesInput() {
    return await this.requiredMatchesInput.getAttribute('value');
  }

  async setStartWeekIdInput(startWeekId) {
    await this.startWeekIdInput.sendKeys(startWeekId);
  }

  async getStartWeekIdInput() {
    return await this.startWeekIdInput.getAttribute('value');
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

export class MLeagueDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mLeague-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mLeague'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
