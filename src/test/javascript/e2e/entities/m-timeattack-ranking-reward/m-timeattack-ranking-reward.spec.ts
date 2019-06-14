/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTimeattackRankingRewardComponentsPage,
  MTimeattackRankingRewardDeleteDialog,
  MTimeattackRankingRewardUpdatePage
} from './m-timeattack-ranking-reward.page-object';

const expect = chai.expect;

describe('MTimeattackRankingReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTimeattackRankingRewardUpdatePage: MTimeattackRankingRewardUpdatePage;
  let mTimeattackRankingRewardComponentsPage: MTimeattackRankingRewardComponentsPage;
  let mTimeattackRankingRewardDeleteDialog: MTimeattackRankingRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTimeattackRankingRewards', async () => {
    await navBarPage.goToEntity('m-timeattack-ranking-reward');
    mTimeattackRankingRewardComponentsPage = new MTimeattackRankingRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mTimeattackRankingRewardComponentsPage.title), 5000);
    expect(await mTimeattackRankingRewardComponentsPage.getTitle()).to.eq('M Timeattack Ranking Rewards');
  });

  it('should load create MTimeattackRankingReward page', async () => {
    await mTimeattackRankingRewardComponentsPage.clickOnCreateButton();
    mTimeattackRankingRewardUpdatePage = new MTimeattackRankingRewardUpdatePage();
    expect(await mTimeattackRankingRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Timeattack Ranking Reward');
    await mTimeattackRankingRewardUpdatePage.cancel();
  });

  it('should create and save MTimeattackRankingRewards', async () => {
    const nbButtonsBeforeCreate = await mTimeattackRankingRewardComponentsPage.countDeleteButtons();

    await mTimeattackRankingRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mTimeattackRankingRewardUpdatePage.setEventIdInput('5'),
      mTimeattackRankingRewardUpdatePage.setRankingFromInput('5'),
      mTimeattackRankingRewardUpdatePage.setRankingToInput('5'),
      mTimeattackRankingRewardUpdatePage.setRewardGroupIdInput('5')
    ]);
    expect(await mTimeattackRankingRewardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mTimeattackRankingRewardUpdatePage.getRankingFromInput()).to.eq('5', 'Expected rankingFrom value to be equals to 5');
    expect(await mTimeattackRankingRewardUpdatePage.getRankingToInput()).to.eq('5', 'Expected rankingTo value to be equals to 5');
    expect(await mTimeattackRankingRewardUpdatePage.getRewardGroupIdInput()).to.eq('5', 'Expected rewardGroupId value to be equals to 5');
    await mTimeattackRankingRewardUpdatePage.save();
    expect(await mTimeattackRankingRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTimeattackRankingRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTimeattackRankingReward', async () => {
    const nbButtonsBeforeDelete = await mTimeattackRankingRewardComponentsPage.countDeleteButtons();
    await mTimeattackRankingRewardComponentsPage.clickOnLastDeleteButton();

    mTimeattackRankingRewardDeleteDialog = new MTimeattackRankingRewardDeleteDialog();
    expect(await mTimeattackRankingRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Timeattack Ranking Reward?'
    );
    await mTimeattackRankingRewardDeleteDialog.clickOnConfirmButton();

    expect(await mTimeattackRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
