/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLuckWeeklyQuestStageRewardComponentsPage,
  MLuckWeeklyQuestStageRewardDeleteDialog,
  MLuckWeeklyQuestStageRewardUpdatePage
} from './m-luck-weekly-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MLuckWeeklyQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLuckWeeklyQuestStageRewardUpdatePage: MLuckWeeklyQuestStageRewardUpdatePage;
  let mLuckWeeklyQuestStageRewardComponentsPage: MLuckWeeklyQuestStageRewardComponentsPage;
  let mLuckWeeklyQuestStageRewardDeleteDialog: MLuckWeeklyQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLuckWeeklyQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-luck-weekly-quest-stage-reward');
    mLuckWeeklyQuestStageRewardComponentsPage = new MLuckWeeklyQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mLuckWeeklyQuestStageRewardComponentsPage.title), 5000);
    expect(await mLuckWeeklyQuestStageRewardComponentsPage.getTitle()).to.eq('M Luck Weekly Quest Stage Rewards');
  });

  it('should load create MLuckWeeklyQuestStageReward page', async () => {
    await mLuckWeeklyQuestStageRewardComponentsPage.clickOnCreateButton();
    mLuckWeeklyQuestStageRewardUpdatePage = new MLuckWeeklyQuestStageRewardUpdatePage();
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Luck Weekly Quest Stage Reward');
    await mLuckWeeklyQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MLuckWeeklyQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mLuckWeeklyQuestStageRewardComponentsPage.countDeleteButtons();

    await mLuckWeeklyQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mLuckWeeklyQuestStageRewardUpdatePage.setStageIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setExpInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setCoinInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mLuckWeeklyQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mLuckWeeklyQuestStageRewardUpdatePage.save();
    expect(await mLuckWeeklyQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLuckWeeklyQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLuckWeeklyQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mLuckWeeklyQuestStageRewardComponentsPage.countDeleteButtons();
    await mLuckWeeklyQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mLuckWeeklyQuestStageRewardDeleteDialog = new MLuckWeeklyQuestStageRewardDeleteDialog();
    expect(await mLuckWeeklyQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Luck Weekly Quest Stage Reward?'
    );
    await mLuckWeeklyQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mLuckWeeklyQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
