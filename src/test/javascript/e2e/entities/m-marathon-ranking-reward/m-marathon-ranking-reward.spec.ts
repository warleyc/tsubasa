/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonRankingRewardComponentsPage,
  MMarathonRankingRewardDeleteDialog,
  MMarathonRankingRewardUpdatePage
} from './m-marathon-ranking-reward.page-object';

const expect = chai.expect;

describe('MMarathonRankingReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonRankingRewardUpdatePage: MMarathonRankingRewardUpdatePage;
  let mMarathonRankingRewardComponentsPage: MMarathonRankingRewardComponentsPage;
  let mMarathonRankingRewardDeleteDialog: MMarathonRankingRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonRankingRewards', async () => {
    await navBarPage.goToEntity('m-marathon-ranking-reward');
    mMarathonRankingRewardComponentsPage = new MMarathonRankingRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonRankingRewardComponentsPage.title), 5000);
    expect(await mMarathonRankingRewardComponentsPage.getTitle()).to.eq('M Marathon Ranking Rewards');
  });

  it('should load create MMarathonRankingReward page', async () => {
    await mMarathonRankingRewardComponentsPage.clickOnCreateButton();
    mMarathonRankingRewardUpdatePage = new MMarathonRankingRewardUpdatePage();
    expect(await mMarathonRankingRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Ranking Reward');
    await mMarathonRankingRewardUpdatePage.cancel();
  });

  it('should create and save MMarathonRankingRewards', async () => {
    const nbButtonsBeforeCreate = await mMarathonRankingRewardComponentsPage.countDeleteButtons();

    await mMarathonRankingRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonRankingRewardUpdatePage.setEventIdInput('5'),
      mMarathonRankingRewardUpdatePage.setRankingFromInput('5'),
      mMarathonRankingRewardUpdatePage.setRankingToInput('5'),
      mMarathonRankingRewardUpdatePage.setRewardGroupIdInput('5')
    ]);
    expect(await mMarathonRankingRewardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonRankingRewardUpdatePage.getRankingFromInput()).to.eq('5', 'Expected rankingFrom value to be equals to 5');
    expect(await mMarathonRankingRewardUpdatePage.getRankingToInput()).to.eq('5', 'Expected rankingTo value to be equals to 5');
    expect(await mMarathonRankingRewardUpdatePage.getRewardGroupIdInput()).to.eq('5', 'Expected rewardGroupId value to be equals to 5');
    await mMarathonRankingRewardUpdatePage.save();
    expect(await mMarathonRankingRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonRankingRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonRankingReward', async () => {
    const nbButtonsBeforeDelete = await mMarathonRankingRewardComponentsPage.countDeleteButtons();
    await mMarathonRankingRewardComponentsPage.clickOnLastDeleteButton();

    mMarathonRankingRewardDeleteDialog = new MMarathonRankingRewardDeleteDialog();
    expect(await mMarathonRankingRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Ranking Reward?'
    );
    await mMarathonRankingRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
