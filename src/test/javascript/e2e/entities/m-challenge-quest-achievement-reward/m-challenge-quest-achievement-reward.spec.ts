/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChallengeQuestAchievementRewardComponentsPage,
  MChallengeQuestAchievementRewardDeleteDialog,
  MChallengeQuestAchievementRewardUpdatePage
} from './m-challenge-quest-achievement-reward.page-object';

const expect = chai.expect;

describe('MChallengeQuestAchievementReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChallengeQuestAchievementRewardUpdatePage: MChallengeQuestAchievementRewardUpdatePage;
  let mChallengeQuestAchievementRewardComponentsPage: MChallengeQuestAchievementRewardComponentsPage;
  let mChallengeQuestAchievementRewardDeleteDialog: MChallengeQuestAchievementRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChallengeQuestAchievementRewards', async () => {
    await navBarPage.goToEntity('m-challenge-quest-achievement-reward');
    mChallengeQuestAchievementRewardComponentsPage = new MChallengeQuestAchievementRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mChallengeQuestAchievementRewardComponentsPage.title), 5000);
    expect(await mChallengeQuestAchievementRewardComponentsPage.getTitle()).to.eq('M Challenge Quest Achievement Rewards');
  });

  it('should load create MChallengeQuestAchievementReward page', async () => {
    await mChallengeQuestAchievementRewardComponentsPage.clickOnCreateButton();
    mChallengeQuestAchievementRewardUpdatePage = new MChallengeQuestAchievementRewardUpdatePage();
    expect(await mChallengeQuestAchievementRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Challenge Quest Achievement Reward');
    await mChallengeQuestAchievementRewardUpdatePage.cancel();
  });

  it('should create and save MChallengeQuestAchievementRewards', async () => {
    const nbButtonsBeforeCreate = await mChallengeQuestAchievementRewardComponentsPage.countDeleteButtons();

    await mChallengeQuestAchievementRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mChallengeQuestAchievementRewardUpdatePage.setWorldIdInput('5'),
      mChallengeQuestAchievementRewardUpdatePage.setStageIdInput('5'),
      mChallengeQuestAchievementRewardUpdatePage.setRewardGroupIdInput('5')
    ]);
    expect(await mChallengeQuestAchievementRewardUpdatePage.getWorldIdInput()).to.eq('5', 'Expected worldId value to be equals to 5');
    expect(await mChallengeQuestAchievementRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mChallengeQuestAchievementRewardUpdatePage.getRewardGroupIdInput()).to.eq(
      '5',
      'Expected rewardGroupId value to be equals to 5'
    );
    await mChallengeQuestAchievementRewardUpdatePage.save();
    expect(await mChallengeQuestAchievementRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mChallengeQuestAchievementRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MChallengeQuestAchievementReward', async () => {
    const nbButtonsBeforeDelete = await mChallengeQuestAchievementRewardComponentsPage.countDeleteButtons();
    await mChallengeQuestAchievementRewardComponentsPage.clickOnLastDeleteButton();

    mChallengeQuestAchievementRewardDeleteDialog = new MChallengeQuestAchievementRewardDeleteDialog();
    expect(await mChallengeQuestAchievementRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Challenge Quest Achievement Reward?'
    );
    await mChallengeQuestAchievementRewardDeleteDialog.clickOnConfirmButton();

    expect(await mChallengeQuestAchievementRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
