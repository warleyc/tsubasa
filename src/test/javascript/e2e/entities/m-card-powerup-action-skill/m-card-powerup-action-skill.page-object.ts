import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCardPowerupActionSkillComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-card-powerup-action-skill div table .btn-danger'));
  title = element.all(by.css('jhi-m-card-powerup-action-skill div h2#page-heading span')).first();

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

export class MCardPowerupActionSkillUpdatePage {
  pageTitle = element(by.id('jhi-m-card-powerup-action-skill-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  descriptionInput = element(by.id('field_description'));
  rarityInput = element(by.id('field_rarity'));
  attributeInput = element(by.id('field_attribute'));
  actionRarityInput = element(by.id('field_actionRarity'));
  gainActionExpInput = element(by.id('field_gainActionExp'));
  coinInput = element(by.id('field_coin'));
  sellMedalIdInput = element(by.id('field_sellMedalId'));
  thumbnailAssetsIdInput = element(by.id('field_thumbnailAssetsId'));
  cardIllustAssetsIdInput = element(by.id('field_cardIllustAssetsId'));
  targetActionCommandTypeInput = element(by.id('field_targetActionCommandType'));
  targetCharacterIdInput = element(by.id('field_targetCharacterId'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return await this.shortNameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setRarityInput(rarity) {
    await this.rarityInput.sendKeys(rarity);
  }

  async getRarityInput() {
    return await this.rarityInput.getAttribute('value');
  }

  async setAttributeInput(attribute) {
    await this.attributeInput.sendKeys(attribute);
  }

  async getAttributeInput() {
    return await this.attributeInput.getAttribute('value');
  }

  async setActionRarityInput(actionRarity) {
    await this.actionRarityInput.sendKeys(actionRarity);
  }

  async getActionRarityInput() {
    return await this.actionRarityInput.getAttribute('value');
  }

  async setGainActionExpInput(gainActionExp) {
    await this.gainActionExpInput.sendKeys(gainActionExp);
  }

  async getGainActionExpInput() {
    return await this.gainActionExpInput.getAttribute('value');
  }

  async setCoinInput(coin) {
    await this.coinInput.sendKeys(coin);
  }

  async getCoinInput() {
    return await this.coinInput.getAttribute('value');
  }

  async setSellMedalIdInput(sellMedalId) {
    await this.sellMedalIdInput.sendKeys(sellMedalId);
  }

  async getSellMedalIdInput() {
    return await this.sellMedalIdInput.getAttribute('value');
  }

  async setThumbnailAssetsIdInput(thumbnailAssetsId) {
    await this.thumbnailAssetsIdInput.sendKeys(thumbnailAssetsId);
  }

  async getThumbnailAssetsIdInput() {
    return await this.thumbnailAssetsIdInput.getAttribute('value');
  }

  async setCardIllustAssetsIdInput(cardIllustAssetsId) {
    await this.cardIllustAssetsIdInput.sendKeys(cardIllustAssetsId);
  }

  async getCardIllustAssetsIdInput() {
    return await this.cardIllustAssetsIdInput.getAttribute('value');
  }

  async setTargetActionCommandTypeInput(targetActionCommandType) {
    await this.targetActionCommandTypeInput.sendKeys(targetActionCommandType);
  }

  async getTargetActionCommandTypeInput() {
    return await this.targetActionCommandTypeInput.getAttribute('value');
  }

  async setTargetCharacterIdInput(targetCharacterId) {
    await this.targetCharacterIdInput.sendKeys(targetCharacterId);
  }

  async getTargetCharacterIdInput() {
    return await this.targetCharacterIdInput.getAttribute('value');
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

export class MCardPowerupActionSkillDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCardPowerupActionSkill-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCardPowerupActionSkill'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
