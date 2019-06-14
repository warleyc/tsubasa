/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MStageStoryComponentsPage, MStageStoryDeleteDialog, MStageStoryUpdatePage } from './m-stage-story.page-object';

const expect = chai.expect;

describe('MStageStory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mStageStoryUpdatePage: MStageStoryUpdatePage;
  let mStageStoryComponentsPage: MStageStoryComponentsPage;
  let mStageStoryDeleteDialog: MStageStoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MStageStories', async () => {
    await navBarPage.goToEntity('m-stage-story');
    mStageStoryComponentsPage = new MStageStoryComponentsPage();
    await browser.wait(ec.visibilityOf(mStageStoryComponentsPage.title), 5000);
    expect(await mStageStoryComponentsPage.getTitle()).to.eq('M Stage Stories');
  });

  it('should load create MStageStory page', async () => {
    await mStageStoryComponentsPage.clickOnCreateButton();
    mStageStoryUpdatePage = new MStageStoryUpdatePage();
    expect(await mStageStoryUpdatePage.getPageTitle()).to.eq('Create or edit a M Stage Story');
    await mStageStoryUpdatePage.cancel();
  });

  it('should create and save MStageStories', async () => {
    const nbButtonsBeforeCreate = await mStageStoryComponentsPage.countDeleteButtons();

    await mStageStoryComponentsPage.clickOnCreateButton();
    await promise.all([
      mStageStoryUpdatePage.setStageIdInput('5'),
      mStageStoryUpdatePage.setEventTypeInput('5'),
      mStageStoryUpdatePage.setMainStoryAssetInput('mainStoryAsset'),
      mStageStoryUpdatePage.setKickoffStoryAssetInput('kickoffStoryAsset'),
      mStageStoryUpdatePage.setHalftimeStoryAssetInput('halftimeStoryAsset'),
      mStageStoryUpdatePage.setResultStoryAssetInput('resultStoryAsset')
    ]);
    expect(await mStageStoryUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mStageStoryUpdatePage.getEventTypeInput()).to.eq('5', 'Expected eventType value to be equals to 5');
    expect(await mStageStoryUpdatePage.getMainStoryAssetInput()).to.eq(
      'mainStoryAsset',
      'Expected MainStoryAsset value to be equals to mainStoryAsset'
    );
    expect(await mStageStoryUpdatePage.getKickoffStoryAssetInput()).to.eq(
      'kickoffStoryAsset',
      'Expected KickoffStoryAsset value to be equals to kickoffStoryAsset'
    );
    expect(await mStageStoryUpdatePage.getHalftimeStoryAssetInput()).to.eq(
      'halftimeStoryAsset',
      'Expected HalftimeStoryAsset value to be equals to halftimeStoryAsset'
    );
    expect(await mStageStoryUpdatePage.getResultStoryAssetInput()).to.eq(
      'resultStoryAsset',
      'Expected ResultStoryAsset value to be equals to resultStoryAsset'
    );
    await mStageStoryUpdatePage.save();
    expect(await mStageStoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mStageStoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MStageStory', async () => {
    const nbButtonsBeforeDelete = await mStageStoryComponentsPage.countDeleteButtons();
    await mStageStoryComponentsPage.clickOnLastDeleteButton();

    mStageStoryDeleteDialog = new MStageStoryDeleteDialog();
    expect(await mStageStoryDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Stage Story?');
    await mStageStoryDeleteDialog.clickOnConfirmButton();

    expect(await mStageStoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
