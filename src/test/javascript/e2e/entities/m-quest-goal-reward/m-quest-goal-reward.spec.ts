/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestGoalRewardComponentsPage,
  MQuestGoalRewardDeleteDialog,
  MQuestGoalRewardUpdatePage
} from './m-quest-goal-reward.page-object';

const expect = chai.expect;

describe('MQuestGoalReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestGoalRewardUpdatePage: MQuestGoalRewardUpdatePage;
  let mQuestGoalRewardComponentsPage: MQuestGoalRewardComponentsPage;
  let mQuestGoalRewardDeleteDialog: MQuestGoalRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestGoalRewards', async () => {
    await navBarPage.goToEntity('m-quest-goal-reward');
    mQuestGoalRewardComponentsPage = new MQuestGoalRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestGoalRewardComponentsPage.title), 5000);
    expect(await mQuestGoalRewardComponentsPage.getTitle()).to.eq('M Quest Goal Rewards');
  });

  it('should load create MQuestGoalReward page', async () => {
    await mQuestGoalRewardComponentsPage.clickOnCreateButton();
    mQuestGoalRewardUpdatePage = new MQuestGoalRewardUpdatePage();
    expect(await mQuestGoalRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Goal Reward');
    await mQuestGoalRewardUpdatePage.cancel();
  });

  it('should create and save MQuestGoalRewards', async () => {
    const nbButtonsBeforeCreate = await mQuestGoalRewardComponentsPage.countDeleteButtons();

    await mQuestGoalRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestGoalRewardUpdatePage.setGroupIdInput('5'),
      mQuestGoalRewardUpdatePage.setWeightInput('5'),
      mQuestGoalRewardUpdatePage.setAssetNameInput('assetName'),
      mQuestGoalRewardUpdatePage.setContentTypeInput('5'),
      mQuestGoalRewardUpdatePage.setContentIdInput('5'),
      mQuestGoalRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestGoalRewardUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestGoalRewardUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mQuestGoalRewardUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mQuestGoalRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestGoalRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestGoalRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestGoalRewardUpdatePage.save();
    expect(await mQuestGoalRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestGoalRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestGoalReward', async () => {
    const nbButtonsBeforeDelete = await mQuestGoalRewardComponentsPage.countDeleteButtons();
    await mQuestGoalRewardComponentsPage.clickOnLastDeleteButton();

    mQuestGoalRewardDeleteDialog = new MQuestGoalRewardDeleteDialog();
    expect(await mQuestGoalRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Goal Reward?');
    await mQuestGoalRewardDeleteDialog.clickOnConfirmButton();

    expect(await mQuestGoalRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
