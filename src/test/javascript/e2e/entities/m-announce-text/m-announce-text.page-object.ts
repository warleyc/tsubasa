import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MAnnounceTextComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-announce-text div table .btn-danger'));
  title = element.all(by.css('jhi-m-announce-text div h2#page-heading span')).first();

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

export class MAnnounceTextUpdatePage {
  pageTitle = element(by.id('jhi-m-announce-text-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  groupIdInput = element(by.id('field_groupId'));
  seqNoInput = element(by.id('field_seqNo'));
  normalAnnounceInput = element(by.id('field_normalAnnounce'));
  criticalSAnnounceInput = element(by.id('field_criticalSAnnounce'));
  criticalMAnnounceInput = element(by.id('field_criticalMAnnounce'));
  criticalLAnnounceInput = element(by.id('field_criticalLAnnounce'));
  delayTimeInput = element(by.id('field_delayTime'));
  continueTimeInput = element(by.id('field_continueTime'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setGroupIdInput(groupId) {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput() {
    return await this.groupIdInput.getAttribute('value');
  }

  async setSeqNoInput(seqNo) {
    await this.seqNoInput.sendKeys(seqNo);
  }

  async getSeqNoInput() {
    return await this.seqNoInput.getAttribute('value');
  }

  async setNormalAnnounceInput(normalAnnounce) {
    await this.normalAnnounceInput.sendKeys(normalAnnounce);
  }

  async getNormalAnnounceInput() {
    return await this.normalAnnounceInput.getAttribute('value');
  }

  async setCriticalSAnnounceInput(criticalSAnnounce) {
    await this.criticalSAnnounceInput.sendKeys(criticalSAnnounce);
  }

  async getCriticalSAnnounceInput() {
    return await this.criticalSAnnounceInput.getAttribute('value');
  }

  async setCriticalMAnnounceInput(criticalMAnnounce) {
    await this.criticalMAnnounceInput.sendKeys(criticalMAnnounce);
  }

  async getCriticalMAnnounceInput() {
    return await this.criticalMAnnounceInput.getAttribute('value');
  }

  async setCriticalLAnnounceInput(criticalLAnnounce) {
    await this.criticalLAnnounceInput.sendKeys(criticalLAnnounce);
  }

  async getCriticalLAnnounceInput() {
    return await this.criticalLAnnounceInput.getAttribute('value');
  }

  async setDelayTimeInput(delayTime) {
    await this.delayTimeInput.sendKeys(delayTime);
  }

  async getDelayTimeInput() {
    return await this.delayTimeInput.getAttribute('value');
  }

  async setContinueTimeInput(continueTime) {
    await this.continueTimeInput.sendKeys(continueTime);
  }

  async getContinueTimeInput() {
    return await this.continueTimeInput.getAttribute('value');
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

export class MAnnounceTextDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mAnnounceText-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mAnnounceText'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
