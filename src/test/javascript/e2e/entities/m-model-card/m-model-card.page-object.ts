import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MModelCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-model-card div table .btn-danger'));
  title = element.all(by.css('jhi-m-model-card div h2#page-heading span')).first();

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

export class MModelCardUpdatePage {
  pageTitle = element(by.id('jhi-m-model-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  hairModelInput = element(by.id('field_hairModel'));
  hairTextureInput = element(by.id('field_hairTexture'));
  headModelInput = element(by.id('field_headModel'));
  headTextureInput = element(by.id('field_headTexture'));
  skinTextureInput = element(by.id('field_skinTexture'));
  shoesModelInput = element(by.id('field_shoesModel'));
  shoesTextureInput = element(by.id('field_shoesTexture'));
  globeTextureInput = element(by.id('field_globeTexture'));
  uniqueModelInput = element(by.id('field_uniqueModel'));
  uniqueTextureInput = element(by.id('field_uniqueTexture'));
  dressingTypeUpInput = element(by.id('field_dressingTypeUp'));
  dressingTypeBottomInput = element(by.id('field_dressingTypeBottom'));
  heightInput = element(by.id('field_height'));
  widthInput = element(by.id('field_width'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setHairModelInput(hairModel) {
    await this.hairModelInput.sendKeys(hairModel);
  }

  async getHairModelInput() {
    return await this.hairModelInput.getAttribute('value');
  }

  async setHairTextureInput(hairTexture) {
    await this.hairTextureInput.sendKeys(hairTexture);
  }

  async getHairTextureInput() {
    return await this.hairTextureInput.getAttribute('value');
  }

  async setHeadModelInput(headModel) {
    await this.headModelInput.sendKeys(headModel);
  }

  async getHeadModelInput() {
    return await this.headModelInput.getAttribute('value');
  }

  async setHeadTextureInput(headTexture) {
    await this.headTextureInput.sendKeys(headTexture);
  }

  async getHeadTextureInput() {
    return await this.headTextureInput.getAttribute('value');
  }

  async setSkinTextureInput(skinTexture) {
    await this.skinTextureInput.sendKeys(skinTexture);
  }

  async getSkinTextureInput() {
    return await this.skinTextureInput.getAttribute('value');
  }

  async setShoesModelInput(shoesModel) {
    await this.shoesModelInput.sendKeys(shoesModel);
  }

  async getShoesModelInput() {
    return await this.shoesModelInput.getAttribute('value');
  }

  async setShoesTextureInput(shoesTexture) {
    await this.shoesTextureInput.sendKeys(shoesTexture);
  }

  async getShoesTextureInput() {
    return await this.shoesTextureInput.getAttribute('value');
  }

  async setGlobeTextureInput(globeTexture) {
    await this.globeTextureInput.sendKeys(globeTexture);
  }

  async getGlobeTextureInput() {
    return await this.globeTextureInput.getAttribute('value');
  }

  async setUniqueModelInput(uniqueModel) {
    await this.uniqueModelInput.sendKeys(uniqueModel);
  }

  async getUniqueModelInput() {
    return await this.uniqueModelInput.getAttribute('value');
  }

  async setUniqueTextureInput(uniqueTexture) {
    await this.uniqueTextureInput.sendKeys(uniqueTexture);
  }

  async getUniqueTextureInput() {
    return await this.uniqueTextureInput.getAttribute('value');
  }

  async setDressingTypeUpInput(dressingTypeUp) {
    await this.dressingTypeUpInput.sendKeys(dressingTypeUp);
  }

  async getDressingTypeUpInput() {
    return await this.dressingTypeUpInput.getAttribute('value');
  }

  async setDressingTypeBottomInput(dressingTypeBottom) {
    await this.dressingTypeBottomInput.sendKeys(dressingTypeBottom);
  }

  async getDressingTypeBottomInput() {
    return await this.dressingTypeBottomInput.getAttribute('value');
  }

  async setHeightInput(height) {
    await this.heightInput.sendKeys(height);
  }

  async getHeightInput() {
    return await this.heightInput.getAttribute('value');
  }

  async setWidthInput(width) {
    await this.widthInput.sendKeys(width);
  }

  async getWidthInput() {
    return await this.widthInput.getAttribute('value');
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

export class MModelCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mModelCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mModelCard'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
