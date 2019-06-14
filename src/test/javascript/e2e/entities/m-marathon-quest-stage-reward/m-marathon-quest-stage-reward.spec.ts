/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonQuestStageRewardComponentsPage,
  MMarathonQuestStageRewardDeleteDialog,
  MMarathonQuestStageRewardUpdatePage
} from './m-marathon-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MMarathonQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonQuestStageRewardUpdatePage: MMarathonQuestStageRewardUpdatePage;
  let mMarathonQuestStageRewardComponentsPage: MMarathonQuestStageRewardComponentsPage;
  let mMarathonQuestStageRewardDeleteDialog: MMarathonQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-marathon-quest-stage-reward');
    mMarathonQuestStageRewardComponentsPage = new MMarathonQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonQuestStageRewardComponentsPage.title), 5000);
    expect(await mMarathonQuestStageRewardComponentsPage.getTitle()).to.eq('M Marathon Quest Stage Rewards');
  });

  it('should load create MMarathonQuestStageReward page', async () => {
    await mMarathonQuestStageRewardComponentsPage.clickOnCreateButton();
    mMarathonQuestStageRewardUpdatePage = new MMarathonQuestStageRewardUpdatePage();
    expect(await mMarathonQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Quest Stage Reward');
    await mMarathonQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MMarathonQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mMarathonQuestStageRewardComponentsPage.countDeleteButtons();

    await mMarathonQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonQuestStageRewardUpdatePage.setStageIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setExpInput('5'),
      mMarathonQuestStageRewardUpdatePage.setCoinInput('5'),
      mMarathonQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mMarathonQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mMarathonQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mMarathonQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mMarathonQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mMarathonQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mMarathonQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mMarathonQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mMarathonQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mMarathonQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mMarathonQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mMarathonQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mMarathonQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mMarathonQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mMarathonQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mMarathonQuestStageRewardUpdatePage.save();
    expect(await mMarathonQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mMarathonQuestStageRewardComponentsPage.countDeleteButtons();
    await mMarathonQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mMarathonQuestStageRewardDeleteDialog = new MMarathonQuestStageRewardDeleteDialog();
    expect(await mMarathonQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Quest Stage Reward?'
    );
    await mMarathonQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
