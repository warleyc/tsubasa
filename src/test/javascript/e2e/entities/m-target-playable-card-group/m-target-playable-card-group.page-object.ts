import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MTargetPlayableCardGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-target-playable-card-group div table .btn-danger'));
  title = element.all(by.css('jhi-m-target-playable-card-group div h2#page-heading span')).first();

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

export class MTargetPlayableCardGroupUpdatePage {
  pageTitle = element(by.id('jhi-m-target-playable-card-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  cardIdInput = element(by.id('field_cardId'));
  isShowThumbnailInput = element(by.id('field_isShowThumbnail'));
  idSelect = element(by.id('field_id'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setCardIdInput(cardId) {
    await this.cardIdInput.sendKeys(cardId);
  }

  async getCardIdInput() {
    return await this.cardIdInput.getAttribute('value');
  }

  async setIsShowThumbnailInput(isShowThumbnail) {
    await this.isShowThumbnailInput.sendKeys(isShowThumbnail);
  }

  async getIsShowThumbnailInput() {
    return await this.isShowThumbnailInput.getAttribute('value');
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

export class MTargetPlayableCardGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mTargetPlayableCardGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mTargetPlayableCardGroup'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
