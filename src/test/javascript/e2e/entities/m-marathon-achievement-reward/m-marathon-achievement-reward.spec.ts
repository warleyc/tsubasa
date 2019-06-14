/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonAchievementRewardComponentsPage,
  MMarathonAchievementRewardDeleteDialog,
  MMarathonAchievementRewardUpdatePage
} from './m-marathon-achievement-reward.page-object';

const expect = chai.expect;

describe('MMarathonAchievementReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonAchievementRewardUpdatePage: MMarathonAchievementRewardUpdatePage;
  let mMarathonAchievementRewardComponentsPage: MMarathonAchievementRewardComponentsPage;
  let mMarathonAchievementRewardDeleteDialog: MMarathonAchievementRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonAchievementRewards', async () => {
    await navBarPage.goToEntity('m-marathon-achievement-reward');
    mMarathonAchievementRewardComponentsPage = new MMarathonAchievementRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonAchievementRewardComponentsPage.title), 5000);
    expect(await mMarathonAchievementRewardComponentsPage.getTitle()).to.eq('M Marathon Achievement Rewards');
  });

  it('should load create MMarathonAchievementReward page', async () => {
    await mMarathonAchievementRewardComponentsPage.clickOnCreateButton();
    mMarathonAchievementRewardUpdatePage = new MMarathonAchievementRewardUpdatePage();
    expect(await mMarathonAchievementRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Achievement Reward');
    await mMarathonAchievementRewardUpdatePage.cancel();
  });

  it('should create and save MMarathonAchievementRewards', async () => {
    const nbButtonsBeforeCreate = await mMarathonAchievementRewardComponentsPage.countDeleteButtons();

    await mMarathonAchievementRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonAchievementRewardUpdatePage.setEventIdInput('5'),
      mMarathonAchievementRewardUpdatePage.setEventPointInput('5'),
      mMarathonAchievementRewardUpdatePage.setRewardGroupIdInput('5')
    ]);
    expect(await mMarathonAchievementRewardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonAchievementRewardUpdatePage.getEventPointInput()).to.eq('5', 'Expected eventPoint value to be equals to 5');
    expect(await mMarathonAchievementRewardUpdatePage.getRewardGroupIdInput()).to.eq('5', 'Expected rewardGroupId value to be equals to 5');
    await mMarathonAchievementRewardUpdatePage.save();
    expect(await mMarathonAchievementRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonAchievementRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonAchievementReward', async () => {
    const nbButtonsBeforeDelete = await mMarathonAchievementRewardComponentsPage.countDeleteButtons();
    await mMarathonAchievementRewardComponentsPage.clickOnLastDeleteButton();

    mMarathonAchievementRewardDeleteDialog = new MMarathonAchievementRewardDeleteDialog();
    expect(await mMarathonAchievementRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Achievement Reward?'
    );
    await mMarathonAchievementRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonAchievementRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
