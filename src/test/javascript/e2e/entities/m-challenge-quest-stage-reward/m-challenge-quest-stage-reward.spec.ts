/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChallengeQuestStageRewardComponentsPage,
  MChallengeQuestStageRewardDeleteDialog,
  MChallengeQuestStageRewardUpdatePage
} from './m-challenge-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MChallengeQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChallengeQuestStageRewardUpdatePage: MChallengeQuestStageRewardUpdatePage;
  let mChallengeQuestStageRewardComponentsPage: MChallengeQuestStageRewardComponentsPage;
  let mChallengeQuestStageRewardDeleteDialog: MChallengeQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChallengeQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-challenge-quest-stage-reward');
    mChallengeQuestStageRewardComponentsPage = new MChallengeQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mChallengeQuestStageRewardComponentsPage.title), 5000);
    expect(await mChallengeQuestStageRewardComponentsPage.getTitle()).to.eq('M Challenge Quest Stage Rewards');
  });

  it('should load create MChallengeQuestStageReward page', async () => {
    await mChallengeQuestStageRewardComponentsPage.clickOnCreateButton();
    mChallengeQuestStageRewardUpdatePage = new MChallengeQuestStageRewardUpdatePage();
    expect(await mChallengeQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Challenge Quest Stage Reward');
    await mChallengeQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MChallengeQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mChallengeQuestStageRewardComponentsPage.countDeleteButtons();

    await mChallengeQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mChallengeQuestStageRewardUpdatePage.setStageIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setExpInput('5'),
      mChallengeQuestStageRewardUpdatePage.setCoinInput('5'),
      mChallengeQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mChallengeQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mChallengeQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mChallengeQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mChallengeQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mChallengeQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mChallengeQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mChallengeQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mChallengeQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mChallengeQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mChallengeQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mChallengeQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mChallengeQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mChallengeQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mChallengeQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mChallengeQuestStageRewardUpdatePage.save();
    expect(await mChallengeQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mChallengeQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MChallengeQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mChallengeQuestStageRewardComponentsPage.countDeleteButtons();
    await mChallengeQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mChallengeQuestStageRewardDeleteDialog = new MChallengeQuestStageRewardDeleteDialog();
    expect(await mChallengeQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Challenge Quest Stage Reward?'
    );
    await mChallengeQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mChallengeQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
