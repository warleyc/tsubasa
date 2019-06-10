/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAdventQuestStageRewardComponentsPage,
  MAdventQuestStageRewardDeleteDialog,
  MAdventQuestStageRewardUpdatePage
} from './m-advent-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MAdventQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAdventQuestStageRewardUpdatePage: MAdventQuestStageRewardUpdatePage;
  let mAdventQuestStageRewardComponentsPage: MAdventQuestStageRewardComponentsPage;
  let mAdventQuestStageRewardDeleteDialog: MAdventQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAdventQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-advent-quest-stage-reward');
    mAdventQuestStageRewardComponentsPage = new MAdventQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mAdventQuestStageRewardComponentsPage.title), 5000);
    expect(await mAdventQuestStageRewardComponentsPage.getTitle()).to.eq('M Advent Quest Stage Rewards');
  });

  it('should load create MAdventQuestStageReward page', async () => {
    await mAdventQuestStageRewardComponentsPage.clickOnCreateButton();
    mAdventQuestStageRewardUpdatePage = new MAdventQuestStageRewardUpdatePage();
    expect(await mAdventQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Advent Quest Stage Reward');
    await mAdventQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MAdventQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mAdventQuestStageRewardComponentsPage.countDeleteButtons();

    await mAdventQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mAdventQuestStageRewardUpdatePage.setStageIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setExpInput('5'),
      mAdventQuestStageRewardUpdatePage.setCoinInput('5'),
      mAdventQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mAdventQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mAdventQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mAdventQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mAdventQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mAdventQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mAdventQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mAdventQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mAdventQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mAdventQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mAdventQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mAdventQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mAdventQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mAdventQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mAdventQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mAdventQuestStageRewardUpdatePage.save();
    expect(await mAdventQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAdventQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MAdventQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mAdventQuestStageRewardComponentsPage.countDeleteButtons();
    await mAdventQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mAdventQuestStageRewardDeleteDialog = new MAdventQuestStageRewardDeleteDialog();
    expect(await mAdventQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Advent Quest Stage Reward?'
    );
    await mAdventQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mAdventQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
