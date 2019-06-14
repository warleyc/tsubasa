/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTicketQuestStageRewardComponentsPage,
  MTicketQuestStageRewardDeleteDialog,
  MTicketQuestStageRewardUpdatePage
} from './m-ticket-quest-stage-reward.page-object';

const expect = chai.expect;

describe('MTicketQuestStageReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTicketQuestStageRewardUpdatePage: MTicketQuestStageRewardUpdatePage;
  let mTicketQuestStageRewardComponentsPage: MTicketQuestStageRewardComponentsPage;
  let mTicketQuestStageRewardDeleteDialog: MTicketQuestStageRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTicketQuestStageRewards', async () => {
    await navBarPage.goToEntity('m-ticket-quest-stage-reward');
    mTicketQuestStageRewardComponentsPage = new MTicketQuestStageRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mTicketQuestStageRewardComponentsPage.title), 5000);
    expect(await mTicketQuestStageRewardComponentsPage.getTitle()).to.eq('M Ticket Quest Stage Rewards');
  });

  it('should load create MTicketQuestStageReward page', async () => {
    await mTicketQuestStageRewardComponentsPage.clickOnCreateButton();
    mTicketQuestStageRewardUpdatePage = new MTicketQuestStageRewardUpdatePage();
    expect(await mTicketQuestStageRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Ticket Quest Stage Reward');
    await mTicketQuestStageRewardUpdatePage.cancel();
  });

  it('should create and save MTicketQuestStageRewards', async () => {
    const nbButtonsBeforeCreate = await mTicketQuestStageRewardComponentsPage.countDeleteButtons();

    await mTicketQuestStageRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mTicketQuestStageRewardUpdatePage.setStageIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setExpInput('5'),
      mTicketQuestStageRewardUpdatePage.setCoinInput('5'),
      mTicketQuestStageRewardUpdatePage.setGuildPointInput('5'),
      mTicketQuestStageRewardUpdatePage.setClearRewardGroupIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setClearRewardWeightIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setAchievementRewardGroupIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setCoopGroupIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setSpecialRewardGroupIdInput('5'),
      mTicketQuestStageRewardUpdatePage.setSpecialRewardAmountInput('5'),
      mTicketQuestStageRewardUpdatePage.setGoalRewardGroupIdInput('5')
    ]);
    expect(await mTicketQuestStageRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mTicketQuestStageRewardUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mTicketQuestStageRewardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mTicketQuestStageRewardUpdatePage.getGuildPointInput()).to.eq('5', 'Expected guildPoint value to be equals to 5');
    expect(await mTicketQuestStageRewardUpdatePage.getClearRewardGroupIdInput()).to.eq(
      '5',
      'Expected clearRewardGroupId value to be equals to 5'
    );
    expect(await mTicketQuestStageRewardUpdatePage.getClearRewardWeightIdInput()).to.eq(
      '5',
      'Expected clearRewardWeightId value to be equals to 5'
    );
    expect(await mTicketQuestStageRewardUpdatePage.getAchievementRewardGroupIdInput()).to.eq(
      '5',
      'Expected achievementRewardGroupId value to be equals to 5'
    );
    expect(await mTicketQuestStageRewardUpdatePage.getCoopGroupIdInput()).to.eq('5', 'Expected coopGroupId value to be equals to 5');
    expect(await mTicketQuestStageRewardUpdatePage.getSpecialRewardGroupIdInput()).to.eq(
      '5',
      'Expected specialRewardGroupId value to be equals to 5'
    );
    expect(await mTicketQuestStageRewardUpdatePage.getSpecialRewardAmountInput()).to.eq(
      '5',
      'Expected specialRewardAmount value to be equals to 5'
    );
    expect(await mTicketQuestStageRewardUpdatePage.getGoalRewardGroupIdInput()).to.eq(
      '5',
      'Expected goalRewardGroupId value to be equals to 5'
    );
    await mTicketQuestStageRewardUpdatePage.save();
    expect(await mTicketQuestStageRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTicketQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTicketQuestStageReward', async () => {
    const nbButtonsBeforeDelete = await mTicketQuestStageRewardComponentsPage.countDeleteButtons();
    await mTicketQuestStageRewardComponentsPage.clickOnLastDeleteButton();

    mTicketQuestStageRewardDeleteDialog = new MTicketQuestStageRewardDeleteDialog();
    expect(await mTicketQuestStageRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Ticket Quest Stage Reward?'
    );
    await mTicketQuestStageRewardDeleteDialog.clickOnConfirmButton();

    expect(await mTicketQuestStageRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
