import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTrainingCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-training-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-training-card div h2#page-heading span')).first();

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

export class MTrainingCardUpdatePage {
  pageTitle = element(by.id('jhi-m-training-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  shortNameInput = element(by.id('field_shortName'));
  descriptionInput = element(by.id('field_description'));
  rarityInput = element(by.id('field_rarity'));
  attributeInput = element(by.id('field_attribute'));
  gainExpInput = element(by.id('field_gainExp'));
  coinInput = element(by.id('field_coin'));
  sellMedalIdInput = element(by.id('field_sellMedalId'));
  plusDribbleInput = element(by.id('field_plusDribble'));
  plusShootInput = element(by.id('field_plusShoot'));
  plusPassInput = element(by.id('field_plusPass'));
  plusTackleInput = element(by.id('field_plusTackle'));
  plusBlockInput = element(by.id('field_plusBlock'));
  plusInterceptInput = element(by.id('field_plusIntercept'));
  plusSpeedInput = element(by.id('field_plusSpeed'));
  plusPowerInput = element(by.id('field_plusPower'));
  plusTechniqueInput = element(by.id('field_plusTechnique'));
  plusPunchingInput = element(by.id('field_plusPunching'));
  plusCatchingInput = element(by.id('field_plusCatching'));
  thumbnailAssetsIdInput = element(by.id('field_thumbnailAssetsId'));
  cardIllustAssetsIdInput = element(by.id('field_cardIllustAssetsId'));
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

  async setGainExpInput(gainExp) {
    await this.gainExpInput.sendKeys(gainExp);
  }

  async getGainExpInput() {
    return await this.gainExpInput.getAttribute('value');
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

  async setPlusDribbleInput(plusDribble) {
    await this.plusDribbleInput.sendKeys(plusDribble);
  }

  async getPlusDribbleInput() {
    return await this.plusDribbleInput.getAttribute('value');
  }

  async setPlusShootInput(plusShoot) {
    await this.plusShootInput.sendKeys(plusShoot);
  }

  async getPlusShootInput() {
    return await this.plusShootInput.getAttribute('value');
  }

  async setPlusPassInput(plusPass) {
    await this.plusPassInput.sendKeys(plusPass);
  }

  async getPlusPassInput() {
    return await this.plusPassInput.getAttribute('value');
  }

  async setPlusTackleInput(plusTackle) {
    await this.plusTackleInput.sendKeys(plusTackle);
  }

  async getPlusTackleInput() {
    return await this.plusTackleInput.getAttribute('value');
  }

  async setPlusBlockInput(plusBlock) {
    await this.plusBlockInput.sendKeys(plusBlock);
  }

  async getPlusBlockInput() {
    return await this.plusBlockInput.getAttribute('value');
  }

  async setPlusInterceptInput(plusIntercept) {
    await this.plusInterceptInput.sendKeys(plusIntercept);
  }

  async getPlusInterceptInput() {
    return await this.plusInterceptInput.getAttribute('value');
  }

  async setPlusSpeedInput(plusSpeed) {
    await this.plusSpeedInput.sendKeys(plusSpeed);
  }

  async getPlusSpeedInput() {
    return await this.plusSpeedInput.getAttribute('value');
  }

  async setPlusPowerInput(plusPower) {
    await this.plusPowerInput.sendKeys(plusPower);
  }

  async getPlusPowerInput() {
    return await this.plusPowerInput.getAttribute('value');
  }

  async setPlusTechniqueInput(plusTechnique) {
    await this.plusTechniqueInput.sendKeys(plusTechnique);
  }

  async getPlusTechniqueInput() {
    return await this.plusTechniqueInput.getAttribute('value');
  }

  async setPlusPunchingInput(plusPunching) {
    await this.plusPunchingInput.sendKeys(plusPunching);
  }

  async getPlusPunchingInput() {
    return await this.plusPunchingInput.getAttribute('value');
  }

  async setPlusCatchingInput(plusCatching) {
    await this.plusCatchingInput.sendKeys(plusCatching);
  }

  async getPlusCatchingInput() {
    return await this.plusCatchingInput.getAttribute('value');
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

export class MTrainingCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTrainingCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTrainingCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
