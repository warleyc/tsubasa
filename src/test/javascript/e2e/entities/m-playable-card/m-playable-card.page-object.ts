import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MPlayableCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-playable-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-playable-card div h2#page-heading span')).first();

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

export class MPlayableCardUpdatePage {
  pageTitle = element(by.id('jhi-m-playable-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  modelIdInput = element(by.id('field_modelId'));
  properPositionGkInput = element(by.id('field_properPositionGk'));
  properPositionFwInput = element(by.id('field_properPositionFw'));
  properPositionOmfInput = element(by.id('field_properPositionOmf'));
  properPositionDmfInput = element(by.id('field_properPositionDmf'));
  properPositionDfInput = element(by.id('field_properPositionDf'));
  characterIdInput = element(by.id('field_characterId'));
  nickNameInput = element(by.id('field_nickName'));
  teamIdInput = element(by.id('field_teamId'));
  nationalityIdInput = element(by.id('field_nationalityId'));
  rarityInput = element(by.id('field_rarity'));
  attributeInput = element(by.id('field_attribute'));
  thumbnailAssetsIdInput = element(by.id('field_thumbnailAssetsId'));
  cardIllustAssetsIdInput = element(by.id('field_cardIllustAssetsId'));
  playableAssetsIdInput = element(by.id('field_playableAssetsId'));
  teamEffectIdInput = element(by.id('field_teamEffectId'));
  triggerEffectIdInput = element(by.id('field_triggerEffectId'));
  maxActionSlotInput = element(by.id('field_maxActionSlot'));
  initialActionId1Input = element(by.id('field_initialActionId1'));
  initialActionId2Input = element(by.id('field_initialActionId2'));
  initialActionId3Input = element(by.id('field_initialActionId3'));
  initialActionId4Input = element(by.id('field_initialActionId4'));
  initialActionId5Input = element(by.id('field_initialActionId5'));
  initialStaminaInput = element(by.id('field_initialStamina'));
  initialDribbleInput = element(by.id('field_initialDribble'));
  initialShootInput = element(by.id('field_initialShoot'));
  initialPassInput = element(by.id('field_initialPass'));
  initialTackleInput = element(by.id('field_initialTackle'));
  initialBlockInput = element(by.id('field_initialBlock'));
  initialInterceptInput = element(by.id('field_initialIntercept'));
  initialSpeedInput = element(by.id('field_initialSpeed'));
  initialPowerInput = element(by.id('field_initialPower'));
  initialTechniqueInput = element(by.id('field_initialTechnique'));
  initialPunchingInput = element(by.id('field_initialPunching'));
  initialCatchingInput = element(by.id('field_initialCatching'));
  maxStaminaInput = element(by.id('field_maxStamina'));
  maxDribbleInput = element(by.id('field_maxDribble'));
  maxShootInput = element(by.id('field_maxShoot'));
  maxPassInput = element(by.id('field_maxPass'));
  maxTackleInput = element(by.id('field_maxTackle'));
  maxBlockInput = element(by.id('field_maxBlock'));
  maxInterceptInput = element(by.id('field_maxIntercept'));
  maxSpeedInput = element(by.id('field_maxSpeed'));
  maxPowerInput = element(by.id('field_maxPower'));
  maxTechniqueInput = element(by.id('field_maxTechnique'));
  maxPunchingInput = element(by.id('field_maxPunching'));
  maxCatchingInput = element(by.id('field_maxCatching'));
  maxPlusDribbleInput = element(by.id('field_maxPlusDribble'));
  maxPlusShootInput = element(by.id('field_maxPlusShoot'));
  maxPlusPassInput = element(by.id('field_maxPlusPass'));
  maxPlusTackleInput = element(by.id('field_maxPlusTackle'));
  maxPlusBlockInput = element(by.id('field_maxPlusBlock'));
  maxPlusInterceptInput = element(by.id('field_maxPlusIntercept'));
  maxPlusSpeedInput = element(by.id('field_maxPlusSpeed'));
  maxPlusPowerInput = element(by.id('field_maxPlusPower'));
  maxPlusTechniqueInput = element(by.id('field_maxPlusTechnique'));
  maxPlusPunchingInput = element(by.id('field_maxPlusPunching'));
  maxPlusCatchingInput = element(by.id('field_maxPlusCatching'));
  highBallBonusInput = element(by.id('field_highBallBonus'));
  lowBallBonusInput = element(by.id('field_lowBallBonus'));
  isDropOnlyInput = element(by.id('field_isDropOnly'));
  sellCoinGroupNumInput = element(by.id('field_sellCoinGroupNum'));
  sellMedalIdInput = element(by.id('field_sellMedalId'));
  characterBookIdInput = element(by.id('field_characterBookId'));
  bookNoInput = element(by.id('field_bookNo'));
  isShowBookInput = element(by.id('field_isShowBook'));
  levelGroupIdInput = element(by.id('field_levelGroupId'));
  startAtInput = element(by.id('field_startAt'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setModelIdInput(modelId) {
    await this.modelIdInput.sendKeys(modelId);
  }

  async getModelIdInput() {
    return await this.modelIdInput.getAttribute('value');
  }

  async setProperPositionGkInput(properPositionGk) {
    await this.properPositionGkInput.sendKeys(properPositionGk);
  }

  async getProperPositionGkInput() {
    return await this.properPositionGkInput.getAttribute('value');
  }

  async setProperPositionFwInput(properPositionFw) {
    await this.properPositionFwInput.sendKeys(properPositionFw);
  }

  async getProperPositionFwInput() {
    return await this.properPositionFwInput.getAttribute('value');
  }

  async setProperPositionOmfInput(properPositionOmf) {
    await this.properPositionOmfInput.sendKeys(properPositionOmf);
  }

  async getProperPositionOmfInput() {
    return await this.properPositionOmfInput.getAttribute('value');
  }

  async setProperPositionDmfInput(properPositionDmf) {
    await this.properPositionDmfInput.sendKeys(properPositionDmf);
  }

  async getProperPositionDmfInput() {
    return await this.properPositionDmfInput.getAttribute('value');
  }

  async setProperPositionDfInput(properPositionDf) {
    await this.properPositionDfInput.sendKeys(properPositionDf);
  }

  async getProperPositionDfInput() {
    return await this.properPositionDfInput.getAttribute('value');
  }

  async setCharacterIdInput(characterId) {
    await this.characterIdInput.sendKeys(characterId);
  }

  async getCharacterIdInput() {
    return await this.characterIdInput.getAttribute('value');
  }

  async setNickNameInput(nickName) {
    await this.nickNameInput.sendKeys(nickName);
  }

  async getNickNameInput() {
    return await this.nickNameInput.getAttribute('value');
  }

  async setTeamIdInput(teamId) {
    await this.teamIdInput.sendKeys(teamId);
  }

  async getTeamIdInput() {
    return await this.teamIdInput.getAttribute('value');
  }

  async setNationalityIdInput(nationalityId) {
    await this.nationalityIdInput.sendKeys(nationalityId);
  }

  async getNationalityIdInput() {
    return await this.nationalityIdInput.getAttribute('value');
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

  async setPlayableAssetsIdInput(playableAssetsId) {
    await this.playableAssetsIdInput.sendKeys(playableAssetsId);
  }

  async getPlayableAssetsIdInput() {
    return await this.playableAssetsIdInput.getAttribute('value');
  }

  async setTeamEffectIdInput(teamEffectId) {
    await this.teamEffectIdInput.sendKeys(teamEffectId);
  }

  async getTeamEffectIdInput() {
    return await this.teamEffectIdInput.getAttribute('value');
  }

  async setTriggerEffectIdInput(triggerEffectId) {
    await this.triggerEffectIdInput.sendKeys(triggerEffectId);
  }

  async getTriggerEffectIdInput() {
    return await this.triggerEffectIdInput.getAttribute('value');
  }

  async setMaxActionSlotInput(maxActionSlot) {
    await this.maxActionSlotInput.sendKeys(maxActionSlot);
  }

  async getMaxActionSlotInput() {
    return await this.maxActionSlotInput.getAttribute('value');
  }

  async setInitialActionId1Input(initialActionId1) {
    await this.initialActionId1Input.sendKeys(initialActionId1);
  }

  async getInitialActionId1Input() {
    return await this.initialActionId1Input.getAttribute('value');
  }

  async setInitialActionId2Input(initialActionId2) {
    await this.initialActionId2Input.sendKeys(initialActionId2);
  }

  async getInitialActionId2Input() {
    return await this.initialActionId2Input.getAttribute('value');
  }

  async setInitialActionId3Input(initialActionId3) {
    await this.initialActionId3Input.sendKeys(initialActionId3);
  }

  async getInitialActionId3Input() {
    return await this.initialActionId3Input.getAttribute('value');
  }

  async setInitialActionId4Input(initialActionId4) {
    await this.initialActionId4Input.sendKeys(initialActionId4);
  }

  async getInitialActionId4Input() {
    return await this.initialActionId4Input.getAttribute('value');
  }

  async setInitialActionId5Input(initialActionId5) {
    await this.initialActionId5Input.sendKeys(initialActionId5);
  }

  async getInitialActionId5Input() {
    return await this.initialActionId5Input.getAttribute('value');
  }

  async setInitialStaminaInput(initialStamina) {
    await this.initialStaminaInput.sendKeys(initialStamina);
  }

  async getInitialStaminaInput() {
    return await this.initialStaminaInput.getAttribute('value');
  }

  async setInitialDribbleInput(initialDribble) {
    await this.initialDribbleInput.sendKeys(initialDribble);
  }

  async getInitialDribbleInput() {
    return await this.initialDribbleInput.getAttribute('value');
  }

  async setInitialShootInput(initialShoot) {
    await this.initialShootInput.sendKeys(initialShoot);
  }

  async getInitialShootInput() {
    return await this.initialShootInput.getAttribute('value');
  }

  async setInitialPassInput(initialPass) {
    await this.initialPassInput.sendKeys(initialPass);
  }

  async getInitialPassInput() {
    return await this.initialPassInput.getAttribute('value');
  }

  async setInitialTackleInput(initialTackle) {
    await this.initialTackleInput.sendKeys(initialTackle);
  }

  async getInitialTackleInput() {
    return await this.initialTackleInput.getAttribute('value');
  }

  async setInitialBlockInput(initialBlock) {
    await this.initialBlockInput.sendKeys(initialBlock);
  }

  async getInitialBlockInput() {
    return await this.initialBlockInput.getAttribute('value');
  }

  async setInitialInterceptInput(initialIntercept) {
    await this.initialInterceptInput.sendKeys(initialIntercept);
  }

  async getInitialInterceptInput() {
    return await this.initialInterceptInput.getAttribute('value');
  }

  async setInitialSpeedInput(initialSpeed) {
    await this.initialSpeedInput.sendKeys(initialSpeed);
  }

  async getInitialSpeedInput() {
    return await this.initialSpeedInput.getAttribute('value');
  }

  async setInitialPowerInput(initialPower) {
    await this.initialPowerInput.sendKeys(initialPower);
  }

  async getInitialPowerInput() {
    return await this.initialPowerInput.getAttribute('value');
  }

  async setInitialTechniqueInput(initialTechnique) {
    await this.initialTechniqueInput.sendKeys(initialTechnique);
  }

  async getInitialTechniqueInput() {
    return await this.initialTechniqueInput.getAttribute('value');
  }

  async setInitialPunchingInput(initialPunching) {
    await this.initialPunchingInput.sendKeys(initialPunching);
  }

  async getInitialPunchingInput() {
    return await this.initialPunchingInput.getAttribute('value');
  }

  async setInitialCatchingInput(initialCatching) {
    await this.initialCatchingInput.sendKeys(initialCatching);
  }

  async getInitialCatchingInput() {
    return await this.initialCatchingInput.getAttribute('value');
  }

  async setMaxStaminaInput(maxStamina) {
    await this.maxStaminaInput.sendKeys(maxStamina);
  }

  async getMaxStaminaInput() {
    return await this.maxStaminaInput.getAttribute('value');
  }

  async setMaxDribbleInput(maxDribble) {
    await this.maxDribbleInput.sendKeys(maxDribble);
  }

  async getMaxDribbleInput() {
    return await this.maxDribbleInput.getAttribute('value');
  }

  async setMaxShootInput(maxShoot) {
    await this.maxShootInput.sendKeys(maxShoot);
  }

  async getMaxShootInput() {
    return await this.maxShootInput.getAttribute('value');
  }

  async setMaxPassInput(maxPass) {
    await this.maxPassInput.sendKeys(maxPass);
  }

  async getMaxPassInput() {
    return await this.maxPassInput.getAttribute('value');
  }

  async setMaxTackleInput(maxTackle) {
    await this.maxTackleInput.sendKeys(maxTackle);
  }

  async getMaxTackleInput() {
    return await this.maxTackleInput.getAttribute('value');
  }

  async setMaxBlockInput(maxBlock) {
    await this.maxBlockInput.sendKeys(maxBlock);
  }

  async getMaxBlockInput() {
    return await this.maxBlockInput.getAttribute('value');
  }

  async setMaxInterceptInput(maxIntercept) {
    await this.maxInterceptInput.sendKeys(maxIntercept);
  }

  async getMaxInterceptInput() {
    return await this.maxInterceptInput.getAttribute('value');
  }

  async setMaxSpeedInput(maxSpeed) {
    await this.maxSpeedInput.sendKeys(maxSpeed);
  }

  async getMaxSpeedInput() {
    return await this.maxSpeedInput.getAttribute('value');
  }

  async setMaxPowerInput(maxPower) {
    await this.maxPowerInput.sendKeys(maxPower);
  }

  async getMaxPowerInput() {
    return await this.maxPowerInput.getAttribute('value');
  }

  async setMaxTechniqueInput(maxTechnique) {
    await this.maxTechniqueInput.sendKeys(maxTechnique);
  }

  async getMaxTechniqueInput() {
    return await this.maxTechniqueInput.getAttribute('value');
  }

  async setMaxPunchingInput(maxPunching) {
    await this.maxPunchingInput.sendKeys(maxPunching);
  }

  async getMaxPunchingInput() {
    return await this.maxPunchingInput.getAttribute('value');
  }

  async setMaxCatchingInput(maxCatching) {
    await this.maxCatchingInput.sendKeys(maxCatching);
  }

  async getMaxCatchingInput() {
    return await this.maxCatchingInput.getAttribute('value');
  }

  async setMaxPlusDribbleInput(maxPlusDribble) {
    await this.maxPlusDribbleInput.sendKeys(maxPlusDribble);
  }

  async getMaxPlusDribbleInput() {
    return await this.maxPlusDribbleInput.getAttribute('value');
  }

  async setMaxPlusShootInput(maxPlusShoot) {
    await this.maxPlusShootInput.sendKeys(maxPlusShoot);
  }

  async getMaxPlusShootInput() {
    return await this.maxPlusShootInput.getAttribute('value');
  }

  async setMaxPlusPassInput(maxPlusPass) {
    await this.maxPlusPassInput.sendKeys(maxPlusPass);
  }

  async getMaxPlusPassInput() {
    return await this.maxPlusPassInput.getAttribute('value');
  }

  async setMaxPlusTackleInput(maxPlusTackle) {
    await this.maxPlusTackleInput.sendKeys(maxPlusTackle);
  }

  async getMaxPlusTackleInput() {
    return await this.maxPlusTackleInput.getAttribute('value');
  }

  async setMaxPlusBlockInput(maxPlusBlock) {
    await this.maxPlusBlockInput.sendKeys(maxPlusBlock);
  }

  async getMaxPlusBlockInput() {
    return await this.maxPlusBlockInput.getAttribute('value');
  }

  async setMaxPlusInterceptInput(maxPlusIntercept) {
    await this.maxPlusInterceptInput.sendKeys(maxPlusIntercept);
  }

  async getMaxPlusInterceptInput() {
    return await this.maxPlusInterceptInput.getAttribute('value');
  }

  async setMaxPlusSpeedInput(maxPlusSpeed) {
    await this.maxPlusSpeedInput.sendKeys(maxPlusSpeed);
  }

  async getMaxPlusSpeedInput() {
    return await this.maxPlusSpeedInput.getAttribute('value');
  }

  async setMaxPlusPowerInput(maxPlusPower) {
    await this.maxPlusPowerInput.sendKeys(maxPlusPower);
  }

  async getMaxPlusPowerInput() {
    return await this.maxPlusPowerInput.getAttribute('value');
  }

  async setMaxPlusTechniqueInput(maxPlusTechnique) {
    await this.maxPlusTechniqueInput.sendKeys(maxPlusTechnique);
  }

  async getMaxPlusTechniqueInput() {
    return await this.maxPlusTechniqueInput.getAttribute('value');
  }

  async setMaxPlusPunchingInput(maxPlusPunching) {
    await this.maxPlusPunchingInput.sendKeys(maxPlusPunching);
  }

  async getMaxPlusPunchingInput() {
    return await this.maxPlusPunchingInput.getAttribute('value');
  }

  async setMaxPlusCatchingInput(maxPlusCatching) {
    await this.maxPlusCatchingInput.sendKeys(maxPlusCatching);
  }

  async getMaxPlusCatchingInput() {
    return await this.maxPlusCatchingInput.getAttribute('value');
  }

  async setHighBallBonusInput(highBallBonus) {
    await this.highBallBonusInput.sendKeys(highBallBonus);
  }

  async getHighBallBonusInput() {
    return await this.highBallBonusInput.getAttribute('value');
  }

  async setLowBallBonusInput(lowBallBonus) {
    await this.lowBallBonusInput.sendKeys(lowBallBonus);
  }

  async getLowBallBonusInput() {
    return await this.lowBallBonusInput.getAttribute('value');
  }

  async setIsDropOnlyInput(isDropOnly) {
    await this.isDropOnlyInput.sendKeys(isDropOnly);
  }

  async getIsDropOnlyInput() {
    return await this.isDropOnlyInput.getAttribute('value');
  }

  async setSellCoinGroupNumInput(sellCoinGroupNum) {
    await this.sellCoinGroupNumInput.sendKeys(sellCoinGroupNum);
  }

  async getSellCoinGroupNumInput() {
    return await this.sellCoinGroupNumInput.getAttribute('value');
  }

  async setSellMedalIdInput(sellMedalId) {
    await this.sellMedalIdInput.sendKeys(sellMedalId);
  }

  async getSellMedalIdInput() {
    return await this.sellMedalIdInput.getAttribute('value');
  }

  async setCharacterBookIdInput(characterBookId) {
    await this.characterBookIdInput.sendKeys(characterBookId);
  }

  async getCharacterBookIdInput() {
    return await this.characterBookIdInput.getAttribute('value');
  }

  async setBookNoInput(bookNo) {
    await this.bookNoInput.sendKeys(bookNo);
  }

  async getBookNoInput() {
    return await this.bookNoInput.getAttribute('value');
  }

  async setIsShowBookInput(isShowBook) {
    await this.isShowBookInput.sendKeys(isShowBook);
  }

  async getIsShowBookInput() {
    return await this.isShowBookInput.getAttribute('value');
  }

  async setLevelGroupIdInput(levelGroupId) {
    await this.levelGroupIdInput.sendKeys(levelGroupId);
  }

  async getLevelGroupIdInput() {
    return await this.levelGroupIdInput.getAttribute('value');
  }

  async setStartAtInput(startAt) {
    await this.startAtInput.sendKeys(startAt);
  }

  async getStartAtInput() {
    return await this.startAtInput.getAttribute('value');
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

export class MPlayableCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mPlayableCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mPlayableCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
