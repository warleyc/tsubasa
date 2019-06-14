/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTimeattackQuestStageRewardComponentsPage,
  MTimeattackQuestStageRewardDeleteDialog,
  MTimeattackQuestStageRewardUpdatePage
} from './m-timeattack-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MTimeattackQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTimeattackQuestStageRewardUpdatePage: MTimeattackQuestStageRewardUpdatePage;
  let mTimeattackQuestStageRewardComponentsPage: MTimeattackQuestStageRewardComponentsPage;
  let mTimeattackQuestStageRewardDeleteDialog: MTimeattackQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTimeattackQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-timeattack-quest-stage-reward');
    mTimeattackQuestStageRewardComponentsPage = new MTimeattackQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mTimeattackQuestStageRewardComponentsPage.title), 5000);
    expect(await mTimeattackQuestStageRewardComponentsPage.getTitle()).to.eq('M Timeattack Quest Stage Rewards');
  });

  it('should load create MTimeattackQuestStageReward page', async () => {
    await mTimeattackQuestStageRewardComponentsPage.clickOnCreateButton();
    mTimeattackQuestStageRewardUpdatePage = new MTimeattackQuestStageRewardUpdatePage();
    expect(await mTimeattackQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Timeattack Quest Stage Reward');
    await mTimeattackQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MTimeattackQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mTimeattackQuestStageRewardComponentsPage.countDeleteButtons();

    await mTimeattackQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mTimeattackQuestStageRewardUpdatePage.setStageIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setExpInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setCoinInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mTimeattackQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mTimeattackQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mTimeattackQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mTimeattackQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mTimeattackQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mTimeattackQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mTimeattackQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mTimeattackQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mTimeattackQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mTimeattackQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mTimeattackQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mTimeattackQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mTimeattackQuestStageRewardUpdatePage.save();
    expect(await mTimeattackQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTimeattackQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTimeattackQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mTimeattackQuestStageRewardComponentsPage.countDeleteButtons();
    await mTimeattackQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mTimeattackQuestStageRewardDeleteDialog = new MTimeattackQuestStageRewardDeleteDialog();
    expect(await mTimeattackQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Timeattack Quest Stage Reward?'
    );
    await mTimeattackQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mTimeattackQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
