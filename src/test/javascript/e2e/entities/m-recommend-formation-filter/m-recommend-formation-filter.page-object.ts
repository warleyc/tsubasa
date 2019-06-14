import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MRecommendFormationFilterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-recommend-formation-filter div table .btn-danger'));
  title = element.all(by.css('jhi-m-recommend-formation-filter div h2#page-heading span')).first();

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

export class MRecommendFormationFilterUpdatePage {
  pageTitle = element(by.id('jhi-m-recommend-formation-filter-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  filterTypeInput = element(by.id('field_filterType'));
  nameInput = element(by.id('field_name'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setFilterTypeInput(filterType) {
    await this.filterTypeInput.sendKeys(filterType);
  }

  async getFilterTypeInput() {
    return await this.filterTypeInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
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

export class MRecommendFormationFilterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mRecommendFormationFilter-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mRecommendFormationFilter'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
