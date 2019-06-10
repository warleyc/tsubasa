import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MDeckConditionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-deck-condition div table .btn-danger'));
  title = element.all(by.css('jhi-m-deck-condition div h2#page-heading span')).first();

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

export class MDeckConditionUpdatePage {
  pageTitle = element(by.id('jhi-m-deck-condition-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  targetFormationGroupIdInput = element(by.id('field_targetFormationGroupId'));
  targetCharacterGroupMinIdInput = element(by.id('field_targetCharacterGroupMinId'));
  targetCharacterGroupMaxIdInput = element(by.id('field_targetCharacterGroupMaxId'));
  targetPlayableCardGroupMinIdInput = element(by.id('field_targetPlayableCardGroupMinId'));
  targetPlayableCardGroupMaxIdInput = element(by.id('field_targetPlayableCardGroupMaxId'));
  targetRarityGroupIdInput = element(by.id('field_targetRarityGroupId'));
  targetAttributeInput = element(by.id('field_targetAttribute'));
  targetNationalityGroupMinIdInput = element(by.id('field_targetNationalityGroupMinId'));
  targetNationalityGroupMaxIdInput = element(by.id('field_targetNationalityGroupMaxId'));
  targetTeamGroupMinIdInput = element(by.id('field_targetTeamGroupMinId'));
  targetTeamGroupMaxIdInput = element(by.id('field_targetTeamGroupMaxId'));
  targetActionGroupMinIdInput = element(by.id('field_targetActionGroupMinId'));
  targetActionGroupMaxIdInput = element(by.id('field_targetActionGroupMaxId'));
  targetTriggerEffectGroupMinIdInput = element(by.id('field_targetTriggerEffectGroupMinId'));
  targetTriggerEffectGroupMaxIdInput = element(by.id('field_targetTriggerEffectGroupMaxId'));
  targetCharacterMinCountInput = element(by.id('field_targetCharacterMinCount'));
  targetCharacterMaxCountInput = element(by.id('field_targetCharacterMaxCount'));
  targetPlayableCardMinCountInput = element(by.id('field_targetPlayableCardMinCount'));
  targetPlayableCardMaxCountInput = element(by.id('field_targetPlayableCardMaxCount'));
  targetRarityMaxCountInput = element(by.id('field_targetRarityMaxCount'));
  targetAttributeMinCountInput = element(by.id('field_targetAttributeMinCount'));
  targetNationalityMinCountInput = element(by.id('field_targetNationalityMinCount'));
  targetNationalityMaxCountInput = element(by.id('field_targetNationalityMaxCount'));
  targetTeamMinCountInput = element(by.id('field_targetTeamMinCount'));
  targetTeamMaxCountInput = element(by.id('field_targetTeamMaxCount'));
  targetActionMinCountInput = element(by.id('field_targetActionMinCount'));
  targetActionMaxCountInput = element(by.id('field_targetActionMaxCount'));
  targetTriggerEffectMinCountInput = element(by.id('field_targetTriggerEffectMinCount'));
  targetTriggerEffectMaxCountInput = element(by.id('field_targetTriggerEffectMaxCount'));
  targetCharacterIsStartingMinInput = element(by.id('field_targetCharacterIsStartingMin'));
  targetCharacterIsStartingMaxInput = element(by.id('field_targetCharacterIsStartingMax'));
  targetPlayableCardIsStartingMinInput = element(by.id('field_targetPlayableCardIsStartingMin'));
  targetPlayableCardIsStartingMaxInput = element(by.id('field_targetPlayableCardIsStartingMax'));
  targetRarityIsStartingInput = element(by.id('field_targetRarityIsStarting'));
  targetAttributeIsStartingInput = element(by.id('field_targetAttributeIsStarting'));
  targetNationalityIsStartingMinInput = element(by.id('field_targetNationalityIsStartingMin'));
  targetNationalityIsStartingMaxInput = element(by.id('field_targetNationalityIsStartingMax'));
  targetTeamIsStartingMinInput = element(by.id('field_targetTeamIsStartingMin'));
  targetTeamIsStartingMaxInput = element(by.id('field_targetTeamIsStartingMax'));
  targetActionIsStartingMinInput = element(by.id('field_targetActionIsStartingMin'));
  targetActionIsStartingMaxInput = element(by.id('field_targetActionIsStartingMax'));
  targetTriggerEffectIsStartingMinInput = element(by.id('field_targetTriggerEffectIsStartingMin'));
  targetTriggerEffectIsStartingMaxInput = element(by.id('field_targetTriggerEffectIsStartingMax'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTargetFormationGroupIdInput(targetFormationGroupId) {
    await this.targetFormationGroupIdInput.sendKeys(targetFormationGroupId);
  }

  async getTargetFormationGroupIdInput() {
    return await this.targetFormationGroupIdInput.getAttribute('value');
  }

  async setTargetCharacterGroupMinIdInput(targetCharacterGroupMinId) {
    await this.targetCharacterGroupMinIdInput.sendKeys(targetCharacterGroupMinId);
  }

  async getTargetCharacterGroupMinIdInput() {
    return await this.targetCharacterGroupMinIdInput.getAttribute('value');
  }

  async setTargetCharacterGroupMaxIdInput(targetCharacterGroupMaxId) {
    await this.targetCharacterGroupMaxIdInput.sendKeys(targetCharacterGroupMaxId);
  }

  async getTargetCharacterGroupMaxIdInput() {
    return await this.targetCharacterGroupMaxIdInput.getAttribute('value');
  }

  async setTargetPlayableCardGroupMinIdInput(targetPlayableCardGroupMinId) {
    await this.targetPlayableCardGroupMinIdInput.sendKeys(targetPlayableCardGroupMinId);
  }

  async getTargetPlayableCardGroupMinIdInput() {
    return await this.targetPlayableCardGroupMinIdInput.getAttribute('value');
  }

  async setTargetPlayableCardGroupMaxIdInput(targetPlayableCardGroupMaxId) {
    await this.targetPlayableCardGroupMaxIdInput.sendKeys(targetPlayableCardGroupMaxId);
  }

  async getTargetPlayableCardGroupMaxIdInput() {
    return await this.targetPlayableCardGroupMaxIdInput.getAttribute('value');
  }

  async setTargetRarityGroupIdInput(targetRarityGroupId) {
    await this.targetRarityGroupIdInput.sendKeys(targetRarityGroupId);
  }

  async getTargetRarityGroupIdInput() {
    return await this.targetRarityGroupIdInput.getAttribute('value');
  }

  async setTargetAttributeInput(targetAttribute) {
    await this.targetAttributeInput.sendKeys(targetAttribute);
  }

  async getTargetAttributeInput() {
    return await this.targetAttributeInput.getAttribute('value');
  }

  async setTargetNationalityGroupMinIdInput(targetNationalityGroupMinId) {
    await this.targetNationalityGroupMinIdInput.sendKeys(targetNationalityGroupMinId);
  }

  async getTargetNationalityGroupMinIdInput() {
    return await this.targetNationalityGroupMinIdInput.getAttribute('value');
  }

  async setTargetNationalityGroupMaxIdInput(targetNationalityGroupMaxId) {
    await this.targetNationalityGroupMaxIdInput.sendKeys(targetNationalityGroupMaxId);
  }

  async getTargetNationalityGroupMaxIdInput() {
    return await this.targetNationalityGroupMaxIdInput.getAttribute('value');
  }

  async setTargetTeamGroupMinIdInput(targetTeamGroupMinId) {
    await this.targetTeamGroupMinIdInput.sendKeys(targetTeamGroupMinId);
  }

  async getTargetTeamGroupMinIdInput() {
    return await this.targetTeamGroupMinIdInput.getAttribute('value');
  }

  async setTargetTeamGroupMaxIdInput(targetTeamGroupMaxId) {
    await this.targetTeamGroupMaxIdInput.sendKeys(targetTeamGroupMaxId);
  }

  async getTargetTeamGroupMaxIdInput() {
    return await this.targetTeamGroupMaxIdInput.getAttribute('value');
  }

  async setTargetActionGroupMinIdInput(targetActionGroupMinId) {
    await this.targetActionGroupMinIdInput.sendKeys(targetActionGroupMinId);
  }

  async getTargetActionGroupMinIdInput() {
    return await this.targetActionGroupMinIdInput.getAttribute('value');
  }

  async setTargetActionGroupMaxIdInput(targetActionGroupMaxId) {
    await this.targetActionGroupMaxIdInput.sendKeys(targetActionGroupMaxId);
  }

  async getTargetActionGroupMaxIdInput() {
    return await this.targetActionGroupMaxIdInput.getAttribute('value');
  }

  async setTargetTriggerEffectGroupMinIdInput(targetTriggerEffectGroupMinId) {
    await this.targetTriggerEffectGroupMinIdInput.sendKeys(targetTriggerEffectGroupMinId);
  }

  async getTargetTriggerEffectGroupMinIdInput() {
    return await this.targetTriggerEffectGroupMinIdInput.getAttribute('value');
  }

  async setTargetTriggerEffectGroupMaxIdInput(targetTriggerEffectGroupMaxId) {
    await this.targetTriggerEffectGroupMaxIdInput.sendKeys(targetTriggerEffectGroupMaxId);
  }

  async getTargetTriggerEffectGroupMaxIdInput() {
    return await this.targetTriggerEffectGroupMaxIdInput.getAttribute('value');
  }

  async setTargetCharacterMinCountInput(targetCharacterMinCount) {
    await this.targetCharacterMinCountInput.sendKeys(targetCharacterMinCount);
  }

  async getTargetCharacterMinCountInput() {
    return await this.targetCharacterMinCountInput.getAttribute('value');
  }

  async setTargetCharacterMaxCountInput(targetCharacterMaxCount) {
    await this.targetCharacterMaxCountInput.sendKeys(targetCharacterMaxCount);
  }

  async getTargetCharacterMaxCountInput() {
    return await this.targetCharacterMaxCountInput.getAttribute('value');
  }

  async setTargetPlayableCardMinCountInput(targetPlayableCardMinCount) {
    await this.targetPlayableCardMinCountInput.sendKeys(targetPlayableCardMinCount);
  }

  async getTargetPlayableCardMinCountInput() {
    return await this.targetPlayableCardMinCountInput.getAttribute('value');
  }

  async setTargetPlayableCardMaxCountInput(targetPlayableCardMaxCount) {
    await this.targetPlayableCardMaxCountInput.sendKeys(targetPlayableCardMaxCount);
  }

  async getTargetPlayableCardMaxCountInput() {
    return await this.targetPlayableCardMaxCountInput.getAttribute('value');
  }

  async setTargetRarityMaxCountInput(targetRarityMaxCount) {
    await this.targetRarityMaxCountInput.sendKeys(targetRarityMaxCount);
  }

  async getTargetRarityMaxCountInput() {
    return await this.targetRarityMaxCountInput.getAttribute('value');
  }

  async setTargetAttributeMinCountInput(targetAttributeMinCount) {
    await this.targetAttributeMinCountInput.sendKeys(targetAttributeMinCount);
  }

  async getTargetAttributeMinCountInput() {
    return await this.targetAttributeMinCountInput.getAttribute('value');
  }

  async setTargetNationalityMinCountInput(targetNationalityMinCount) {
    await this.targetNationalityMinCountInput.sendKeys(targetNationalityMinCount);
  }

  async getTargetNationalityMinCountInput() {
    return await this.targetNationalityMinCountInput.getAttribute('value');
  }

  async setTargetNationalityMaxCountInput(targetNationalityMaxCount) {
    await this.targetNationalityMaxCountInput.sendKeys(targetNationalityMaxCount);
  }

  async getTargetNationalityMaxCountInput() {
    return await this.targetNationalityMaxCountInput.getAttribute('value');
  }

  async setTargetTeamMinCountInput(targetTeamMinCount) {
    await this.targetTeamMinCountInput.sendKeys(targetTeamMinCount);
  }

  async getTargetTeamMinCountInput() {
    return await this.targetTeamMinCountInput.getAttribute('value');
  }

  async setTargetTeamMaxCountInput(targetTeamMaxCount) {
    await this.targetTeamMaxCountInput.sendKeys(targetTeamMaxCount);
  }

  async getTargetTeamMaxCountInput() {
    return await this.targetTeamMaxCountInput.getAttribute('value');
  }

  async setTargetActionMinCountInput(targetActionMinCount) {
    await this.targetActionMinCountInput.sendKeys(targetActionMinCount);
  }

  async getTargetActionMinCountInput() {
    return await this.targetActionMinCountInput.getAttribute('value');
  }

  async setTargetActionMaxCountInput(targetActionMaxCount) {
    await this.targetActionMaxCountInput.sendKeys(targetActionMaxCount);
  }

  async getTargetActionMaxCountInput() {
    return await this.targetActionMaxCountInput.getAttribute('value');
  }

  async setTargetTriggerEffectMinCountInput(targetTriggerEffectMinCount) {
    await this.targetTriggerEffectMinCountInput.sendKeys(targetTriggerEffectMinCount);
  }

  async getTargetTriggerEffectMinCountInput() {
    return await this.targetTriggerEffectMinCountInput.getAttribute('value');
  }

  async setTargetTriggerEffectMaxCountInput(targetTriggerEffectMaxCount) {
    await this.targetTriggerEffectMaxCountInput.sendKeys(targetTriggerEffectMaxCount);
  }

  async getTargetTriggerEffectMaxCountInput() {
    return await this.targetTriggerEffectMaxCountInput.getAttribute('value');
  }

  async setTargetCharacterIsStartingMinInput(targetCharacterIsStartingMin) {
    await this.targetCharacterIsStartingMinInput.sendKeys(targetCharacterIsStartingMin);
  }

  async getTargetCharacterIsStartingMinInput() {
    return await this.targetCharacterIsStartingMinInput.getAttribute('value');
  }

  async setTargetCharacterIsStartingMaxInput(targetCharacterIsStartingMax) {
    await this.targetCharacterIsStartingMaxInput.sendKeys(targetCharacterIsStartingMax);
  }

  async getTargetCharacterIsStartingMaxInput() {
    return await this.targetCharacterIsStartingMaxInput.getAttribute('value');
  }

  async setTargetPlayableCardIsStartingMinInput(targetPlayableCardIsStartingMin) {
    await this.targetPlayableCardIsStartingMinInput.sendKeys(targetPlayableCardIsStartingMin);
  }

  async getTargetPlayableCardIsStartingMinInput() {
    return await this.targetPlayableCardIsStartingMinInput.getAttribute('value');
  }

  async setTargetPlayableCardIsStartingMaxInput(targetPlayableCardIsStartingMax) {
    await this.targetPlayableCardIsStartingMaxInput.sendKeys(targetPlayableCardIsStartingMax);
  }

  async getTargetPlayableCardIsStartingMaxInput() {
    return await this.targetPlayableCardIsStartingMaxInput.getAttribute('value');
  }

  async setTargetRarityIsStartingInput(targetRarityIsStarting) {
    await this.targetRarityIsStartingInput.sendKeys(targetRarityIsStarting);
  }

  async getTargetRarityIsStartingInput() {
    return await this.targetRarityIsStartingInput.getAttribute('value');
  }

  async setTargetAttributeIsStartingInput(targetAttributeIsStarting) {
    await this.targetAttributeIsStartingInput.sendKeys(targetAttributeIsStarting);
  }

  async getTargetAttributeIsStartingInput() {
    return await this.targetAttributeIsStartingInput.getAttribute('value');
  }

  async setTargetNationalityIsStartingMinInput(targetNationalityIsStartingMin) {
    await this.targetNationalityIsStartingMinInput.sendKeys(targetNationalityIsStartingMin);
  }

  async getTargetNationalityIsStartingMinInput() {
    return await this.targetNationalityIsStartingMinInput.getAttribute('value');
  }

  async setTargetNationalityIsStartingMaxInput(targetNationalityIsStartingMax) {
    await this.targetNationalityIsStartingMaxInput.sendKeys(targetNationalityIsStartingMax);
  }

  async getTargetNationalityIsStartingMaxInput() {
    return await this.targetNationalityIsStartingMaxInput.getAttribute('value');
  }

  async setTargetTeamIsStartingMinInput(targetTeamIsStartingMin) {
    await this.targetTeamIsStartingMinInput.sendKeys(targetTeamIsStartingMin);
  }

  async getTargetTeamIsStartingMinInput() {
    return await this.targetTeamIsStartingMinInput.getAttribute('value');
  }

  async setTargetTeamIsStartingMaxInput(targetTeamIsStartingMax) {
    await this.targetTeamIsStartingMaxInput.sendKeys(targetTeamIsStartingMax);
  }

  async getTargetTeamIsStartingMaxInput() {
    return await this.targetTeamIsStartingMaxInput.getAttribute('value');
  }

  async setTargetActionIsStartingMinInput(targetActionIsStartingMin) {
    await this.targetActionIsStartingMinInput.sendKeys(targetActionIsStartingMin);
  }

  async getTargetActionIsStartingMinInput() {
    return await this.targetActionIsStartingMinInput.getAttribute('value');
  }

  async setTargetActionIsStartingMaxInput(targetActionIsStartingMax) {
    await this.targetActionIsStartingMaxInput.sendKeys(targetActionIsStartingMax);
  }

  async getTargetActionIsStartingMaxInput() {
    return await this.targetActionIsStartingMaxInput.getAttribute('value');
  }

  async setTargetTriggerEffectIsStartingMinInput(targetTriggerEffectIsStartingMin) {
    await this.targetTriggerEffectIsStartingMinInput.sendKeys(targetTriggerEffectIsStartingMin);
  }

  async getTargetTriggerEffectIsStartingMinInput() {
    return await this.targetTriggerEffectIsStartingMinInput.getAttribute('value');
  }

  async setTargetTriggerEffectIsStartingMaxInput(targetTriggerEffectIsStartingMax) {
    await this.targetTriggerEffectIsStartingMaxInput.sendKeys(targetTriggerEffectIsStartingMax);
  }

  async getTargetTriggerEffectIsStartingMaxInput() {
    return await this.targetTriggerEffectIsStartingMaxInput.getAttribute('value');
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

export class MDeckConditionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mDeckCondition-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mDeckCondition'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
