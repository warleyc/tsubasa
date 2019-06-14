/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MWeeklyQuestStageRewardComponentsPage,
  MWeeklyQuestStageRewardDeleteDialog,
  MWeeklyQuestStageRewardUpdatePage
} from './m-weekly-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MWeeklyQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mWeeklyQuestStageRewardUpdatePage: MWeeklyQuestStageRewardUpdatePage;
  let mWeeklyQuestStageRewardComponentsPage: MWeeklyQuestStageRewardComponentsPage;
  let mWeeklyQuestStageRewardDeleteDialog: MWeeklyQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MWeeklyQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-weekly-quest-stage-reward');
    mWeeklyQuestStageRewardComponentsPage = new MWeeklyQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mWeeklyQuestStageRewardComponentsPage.title), 5000);
    expect(await mWeeklyQuestStageRewardComponentsPage.getTitle()).to.eq('M Weekly Quest Stage Rewards');
  });

  it('should load create MWeeklyQuestStageReward page', async () => {
    await mWeeklyQuestStageRewardComponentsPage.clickOnCreateButton();
    mWeeklyQuestStageRewardUpdatePage = new MWeeklyQuestStageRewardUpdatePage();
    expect(await mWeeklyQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Weekly Quest Stage Reward');
    await mWeeklyQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MWeeklyQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mWeeklyQuestStageRewardComponentsPage.countDeleteButtons();

    await mWeeklyQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mWeeklyQuestStageRewardUpdatePage.setStageIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setExpInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setCoinInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mWeeklyQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mWeeklyQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mWeeklyQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mWeeklyQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mWeeklyQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mWeeklyQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mWeeklyQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mWeeklyQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mWeeklyQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mWeeklyQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mWeeklyQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mWeeklyQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mWeeklyQuestStageRewardUpdatePage.save();
    expect(await mWeeklyQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mWeeklyQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MWeeklyQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mWeeklyQuestStageRewardComponentsPage.countDeleteButtons();
    await mWeeklyQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mWeeklyQuestStageRewardDeleteDialog = new MWeeklyQuestStageRewardDeleteDialog();
    expect(await mWeeklyQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Weekly Quest Stage Reward?'
    );
    await mWeeklyQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mWeeklyQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
