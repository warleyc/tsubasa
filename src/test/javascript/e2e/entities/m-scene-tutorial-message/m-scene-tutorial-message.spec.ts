/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MSceneTutorialMessageComponentsPage,
  MSceneTutorialMessageDeleteDialog,
  MSceneTutorialMessageUpdatePage
} from './m-scene-tutorial-message.page-object';

const expect = chai.expect;

describe('MSceneTutorialMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSceneTutorialMessageUpdatePage: MSceneTutorialMessageUpdatePage;
  let mSceneTutorialMessageComponentsPage: MSceneTutorialMessageComponentsPage;
  let mSceneTutorialMessageDeleteDialog: MSceneTutorialMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSceneTutorialMessages', async () => {
    await navBarPage.goToEntity('m-scene-tutorial-message');
    mSceneTutorialMessageComponentsPage = new MSceneTutorialMessageComponentsPage();
    await browser.wait(ec.visibilityOf(mSceneTutorialMessageComponentsPage.title), 5000);
    expect(await mSceneTutorialMessageComponentsPage.getTitle()).to.eq('M Scene Tutorial Messages');
  });

  it('should load create MSceneTutorialMessage page', async () => {
    await mSceneTutorialMessageComponentsPage.clickOnCreateButton();
    mSceneTutorialMessageUpdatePage = new MSceneTutorialMessageUpdatePage();
    expect(await mSceneTutorialMessageUpdatePage.getPageTitle()).to.eq('Create or edit a M Scene Tutorial Message');
    await mSceneTutorialMessageUpdatePage.cancel();
  });

  it('should create and save MSceneTutorialMessages', async () => {
    const nbButtonsBeforeCreate = await mSceneTutorialMessageComponentsPage.countDeleteButtons();

    await mSceneTutorialMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      mSceneTutorialMessageUpdatePage.setTargetInput('5'),
      mSceneTutorialMessageUpdatePage.setOrderNumInput('5'),
      mSceneTutorialMessageUpdatePage.setPositionInput('5'),
      mSceneTutorialMessageUpdatePage.setMessageInput('message'),
      mSceneTutorialMessageUpdatePage.setAssetNameInput('assetName'),
      mSceneTutorialMessageUpdatePage.setTitleInput('title')
    ]);
    expect(await mSceneTutorialMessageUpdatePage.getTargetInput()).to.eq('5', 'Expected target value to be equals to 5');
    expect(await mSceneTutorialMessageUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mSceneTutorialMessageUpdatePage.getPositionInput()).to.eq('5', 'Expected position value to be equals to 5');
    expect(await mSceneTutorialMessageUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    expect(await mSceneTutorialMessageUpdatePage.getAssetNameInput()).to.eq(
      'assetName',
      'Expected AssetName value to be equals to assetName'
    );
    expect(await mSceneTutorialMessageUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    await mSceneTutorialMessageUpdatePage.save();
    expect(await mSceneTutorialMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSceneTutorialMessageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MSceneTutorialMessage', async () => {
    const nbButtonsBeforeDelete = await mSceneTutorialMessageComponentsPage.countDeleteButtons();
    await mSceneTutorialMessageComponentsPage.clickOnLastDeleteButton();

    mSceneTutorialMessageDeleteDialog = new MSceneTutorialMessageDeleteDialog();
    expect(await mSceneTutorialMessageDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Scene Tutorial Message?'
    );
    await mSceneTutorialMessageDeleteDialog.clickOnConfirmButton();

    expect(await mSceneTutorialMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
