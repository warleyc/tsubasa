import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MSituationAnnounceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-situation-announce div table .btn-danger'));
  title = element.all(by.css('jhi-m-situation-announce div h2#page-heading span')).first();

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

export class MSituationAnnounceUpdatePage {
  pageTitle = element(by.id('jhi-m-situation-announce-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  situationIdInput = element(by.id('field_situationId'));
  groupIdInput = element(by.id('field_groupId'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setSituationIdInput(situationId) {
    await this.situationIdInput.sendKeys(situationId);
  }

  async getSituationIdInput() {
    return await this.situationIdInput.getAttribute('value');
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
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

export class MSituationAnnounceDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mSituationAnnounce-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mSituationAnnounce'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
