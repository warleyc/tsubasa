/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuerillaQuestStageRewardComponentsPage,
  MGuerillaQuestStageRewardDeleteDialog,
  MGuerillaQuestStageRewardUpdatePage
} from './m-guerilla-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MGuerillaQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuerillaQuestStageRewardUpdatePage: MGuerillaQuestStageRewardUpdatePage;
  let mGuerillaQuestStageRewardComponentsPage: MGuerillaQuestStageRewardComponentsPage;
  let mGuerillaQuestStageRewardDeleteDialog: MGuerillaQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuerillaQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-guerilla-quest-stage-reward');
    mGuerillaQuestStageRewardComponentsPage = new MGuerillaQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mGuerillaQuestStageRewardComponentsPage.title), 5000);
    expect(await mGuerillaQuestStageRewardComponentsPage.getTitle()).to.eq('M Guerilla Quest Stage Rewards');
  });

  it('should load create MGuerillaQuestStageReward page', async () => {
    await mGuerillaQuestStageRewardComponentsPage.clickOnCreateButton();
    mGuerillaQuestStageRewardUpdatePage = new MGuerillaQuestStageRewardUpdatePage();
    expect(await mGuerillaQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Guerilla Quest Stage Reward');
    await mGuerillaQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MGuerillaQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mGuerillaQuestStageRewardComponentsPage.countDeleteButtons();

    await mGuerillaQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuerillaQuestStageRewardUpdatePage.setStageIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setExpInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setCoinInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mGuerillaQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mGuerillaQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mGuerillaQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mGuerillaQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mGuerillaQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mGuerillaQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mGuerillaQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mGuerillaQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mGuerillaQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mGuerillaQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mGuerillaQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mGuerillaQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mGuerillaQuestStageRewardUpdatePage.save();
    expect(await mGuerillaQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuerillaQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuerillaQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mGuerillaQuestStageRewardComponentsPage.countDeleteButtons();
    await mGuerillaQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mGuerillaQuestStageRewardDeleteDialog = new MGuerillaQuestStageRewardDeleteDialog();
    expect(await mGuerillaQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Guerilla Quest Stage Reward?'
    );
    await mGuerillaQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mGuerillaQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
