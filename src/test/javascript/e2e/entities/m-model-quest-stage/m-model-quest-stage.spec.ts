/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MModelQuestStageComponentsPage,
  MModelQuestStageDeleteDialog,
  MModelQuestStageUpdatePage
} from './m-model-quest-stage.page-object';

const expect = chai.expect;

describe('MModelQuestStage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelQuestStageUpdatePage: MModelQuestStageUpdatePage;
  let mModelQuestStageComponentsPage: MModelQuestStageComponentsPage;
  let mModelQuestStageDeleteDialog: MModelQuestStageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelQuestStages', async () => {
    await navBarPage.goToEntity('m-model-quest-stage');
    mModelQuestStageComponentsPage = new MModelQuestStageComponentsPage();
    await browser.wait(ec.visibilityOf(mModelQuestStageComponentsPage.title), 5000);
    expect(await mModelQuestStageComponentsPage.getTitle()).to.eq('M Model Quest Stages');
  });

  it('should load create MModelQuestStage page', async () => {
    await mModelQuestStageComponentsPage.clickOnCreateButton();
    mModelQuestStageUpdatePage = new MModelQuestStageUpdatePage();
    expect(await mModelQuestStageUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Quest Stage');
    await mModelQuestStageUpdatePage.cancel();
  });

  it('should create and save MModelQuestStages', async () => {
    const nbButtonsBeforeCreate = await mModelQuestStageComponentsPage.countDeleteButtons();

    await mModelQuestStageComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelQuestStageUpdatePage.setStageIdInput('5'),
      mModelQuestStageUpdatePage.setImageInput('image'),
      mModelQuestStageUpdatePage.setModelNameInput('modelName'),
      mModelQuestStageUpdatePage.setBgmOffencingInput('bgmOffencing'),
      mModelQuestStageUpdatePage.setBgmDefencingInput('bgmDefencing'),
      mModelQuestStageUpdatePage.setBgmHurryingInput('bgmHurrying')
    ]);
    expect(await mModelQuestStageUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mModelQuestStageUpdatePage.getImageInput()).to.eq('image', 'Expected Image value to be equals to image');
    expect(await mModelQuestStageUpdatePage.getModelNameInput()).to.eq('modelName', 'Expected ModelName value to be equals to modelName');
    expect(await mModelQuestStageUpdatePage.getBgmOffencingInput()).to.eq(
      'bgmOffencing',
      'Expected BgmOffencing value to be equals to bgmOffencing'
    );
    expect(await mModelQuestStageUpdatePage.getBgmDefencingInput()).to.eq(
      'bgmDefencing',
      'Expected BgmDefencing value to be equals to bgmDefencing'
    );
    expect(await mModelQuestStageUpdatePage.getBgmHurryingInput()).to.eq(
      'bgmHurrying',
      'Expected BgmHurrying value to be equals to bgmHurrying'
    );
    await mModelQuestStageUpdatePage.save();
    expect(await mModelQuestStageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelQuestStageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MModelQuestStage', async () => {
    const nbButtonsBeforeDelete = await mModelQuestStageComponentsPage.countDeleteButtons();
    await mModelQuestStageComponentsPage.clickOnLastDeleteButton();

    mModelQuestStageDeleteDialog = new MModelQuestStageDeleteDialog();
    expect(await mModelQuestStageDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Model Quest Stage?');
    await mModelQuestStageDeleteDialog.clickOnConfirmButton();

    expect(await mModelQuestStageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
