/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestStageRewardComponentsPage,
  MQuestStageRewardDeleteDialog,
  MQuestStageRewardUpdatePage
} from './m-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestStageRewardUpdatePage: MQuestStageRewardUpdatePage;
  let mQuestStageRewardComponentsPage: MQuestStageRewardComponentsPage;
  let mQuestStageRewardDeleteDialog: MQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-quest-stage-reward');
    mQuestStageRewardComponentsPage = new MQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestStageRewardComponentsPage.title), 5000);
    expect(await mQuestStageRewardComponentsPage.getTitle()).to.eq('M Quest Stage Rewards');
  });

  it('should load create MQuestStageReward page', async () => {
    await mQuestStageRewardComponentsPage.clickOnCreateButton();
    mQuestStageRewardUpdatePage = new MQuestStageRewardUpdatePage();
    expect(await mQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Stage Reward');
    await mQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mQuestStageRewardComponentsPage.countDeleteButtons();

    await mQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestStageRewardUpdatePage.setStageIdInput('5'),
      mQuestStageRewardUpdatePage.setExpInput('5'),
      mQuestStageRewardUpdatePage.setCoinInput('5'),
      mQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5')
    ]);
    expect(await mQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    await mQuestStageRewardUpdatePage.save();
    expect(await mQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mQuestStageRewardComponentsPage.countDeleteButtons();
    await mQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mQuestStageRewardDeleteDialog = new MQuestStageRewardDeleteDialog();
    expect(await mQuestStageRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Stage Reward?');
    await mQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
