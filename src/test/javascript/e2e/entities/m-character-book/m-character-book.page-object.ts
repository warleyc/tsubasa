import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MCharacterBookComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-m-character-book div table .btn-danger'));
  title = element.all(by.css('jhi-m-character-book div h2#page-heading span')).first();

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

export class MCharacterBookUpdatePage {
  pageTitle = element(by.id('jhi-m-character-book-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cvNameInput = element(by.id('field_cvName'));
  seriesInput = element(by.id('field_series'));
  heightInput = element(by.id('field_height'));
  weightInput = element(by.id('field_weight'));
  introductionInput = element(by.id('field_introduction'));
  firstAppearedComicInput = element(by.id('field_firstAppearedComic'));
  firstAppearedComicLinkInput = element(by.id('field_firstAppearedComicLink'));
  skill1Input = element(by.id('field_skill1'));
  skill1ComicInput = element(by.id('field_skill1Comic'));
  skill1ComicLinkInput = element(by.id('field_skill1ComicLink'));
  skill2Input = element(by.id('field_skill2'));
  skill2ComicInput = element(by.id('field_skill2Comic'));
  skill2ComicLinkInput = element(by.id('field_skill2ComicLink'));
  skill3Input = element(by.id('field_skill3'));
  skill3ComicInput = element(by.id('field_skill3Comic'));
  skill3ComicLinkInput = element(by.id('field_skill3ComicLink'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCvNameInput(cvName) {
    await this.cvNameInput.sendKeys(cvName);
  }

  async getCvNameInput() {
    return await this.cvNameInput.getAttribute('value');
  }

  async setSeriesInput(series) {
    await this.seriesInput.sendKeys(series);
  }

  async getSeriesInput() {
    return await this.seriesInput.getAttribute('value');
  }

  async setHeightInput(height) {
    await this.heightInput.sendKeys(height);
  }

  async getHeightInput() {
    return await this.heightInput.getAttribute('value');
  }

  async setWeightInput(weight) {
    await this.weightInput.sendKeys(weight);
  }

  async getWeightInput() {
    return await this.weightInput.getAttribute('value');
  }

  async setIntroductionInput(introduction) {
    await this.introductionInput.sendKeys(introduction);
  }

  async getIntroductionInput() {
    return await this.introductionInput.getAttribute('value');
  }

  async setFirstAppearedComicInput(firstAppearedComic) {
    await this.firstAppearedComicInput.sendKeys(firstAppearedComic);
  }

  async getFirstAppearedComicInput() {
    return await this.firstAppearedComicInput.getAttribute('value');
  }

  async setFirstAppearedComicLinkInput(firstAppearedComicLink) {
    await this.firstAppearedComicLinkInput.sendKeys(firstAppearedComicLink);
  }

  async getFirstAppearedComicLinkInput() {
    return await this.firstAppearedComicLinkInput.getAttribute('value');
  }

  async setSkill1Input(skill1) {
    await this.skill1Input.sendKeys(skill1);
  }

  async getSkill1Input() {
    return await this.skill1Input.getAttribute('value');
  }

  async setSkill1ComicInput(skill1Comic) {
    await this.skill1ComicInput.sendKeys(skill1Comic);
  }

  async getSkill1ComicInput() {
    return await this.skill1ComicInput.getAttribute('value');
  }

  async setSkill1ComicLinkInput(skill1ComicLink) {
    await this.skill1ComicLinkInput.sendKeys(skill1ComicLink);
  }

  async getSkill1ComicLinkInput() {
    return await this.skill1ComicLinkInput.getAttribute('value');
  }

  async setSkill2Input(skill2) {
    await this.skill2Input.sendKeys(skill2);
  }

  async getSkill2Input() {
    return await this.skill2Input.getAttribute('value');
  }

  async setSkill2ComicInput(skill2Comic) {
    await this.skill2ComicInput.sendKeys(skill2Comic);
  }

  async getSkill2ComicInput() {
    return await this.skill2ComicInput.getAttribute('value');
  }

  async setSkill2ComicLinkInput(skill2ComicLink) {
    await this.skill2ComicLinkInput.sendKeys(skill2ComicLink);
  }

  async getSkill2ComicLinkInput() {
    return await this.skill2ComicLinkInput.getAttribute('value');
  }

  async setSkill3Input(skill3) {
    await this.skill3Input.sendKeys(skill3);
  }

  async getSkill3Input() {
    return await this.skill3Input.getAttribute('value');
  }

  async setSkill3ComicInput(skill3Comic) {
    await this.skill3ComicInput.sendKeys(skill3Comic);
  }

  async getSkill3ComicInput() {
    return await this.skill3ComicInput.getAttribute('value');
  }

  async setSkill3ComicLinkInput(skill3ComicLink) {
    await this.skill3ComicLinkInput.sendKeys(skill3ComicLink);
  }

  async getSkill3ComicLinkInput() {
    return await this.skill3ComicLinkInput.getAttribute('value');
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

export class MCharacterBookDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mCharacterBook-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mCharacterBook'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
