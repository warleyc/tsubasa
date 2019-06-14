/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLeagueRankingRewardComponentsPage,
  MLeagueRankingRewardDeleteDialog,
  MLeagueRankingRewardUpdatePage
} from './m-league-ranking-reward.page-object';

const expect = chai.expect;

describe('MLeagueRankingReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueRankingRewardUpdatePage: MLeagueRankingRewardUpdatePage;
  let mLeagueRankingRewardComponentsPage: MLeagueRankingRewardComponentsPage;
  let mLeagueRankingRewardDeleteDialog: MLeagueRankingRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagueRankingRewards', async () => {
    await navBarPage.goToEntity('m-league-ranking-reward');
    mLeagueRankingRewardComponentsPage = new MLeagueRankingRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueRankingRewardComponentsPage.title), 5000);
    expect(await mLeagueRankingRewardComponentsPage.getTitle()).to.eq('M League Ranking Rewards');
  });

  it('should load create MLeagueRankingReward page', async () => {
    await mLeagueRankingRewardComponentsPage.clickOnCreateButton();
    mLeagueRankingRewardUpdatePage = new MLeagueRankingRewardUpdatePage();
    expect(await mLeagueRankingRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M League Ranking Reward');
    await mLeagueRankingRewardUpdatePage.cancel();
  });

  it('should create and save MLeagueRankingRewards', async () => {
    const nbButtonsBeforeCreate = await mLeagueRankingRewardComponentsPage.countDeleteButtons();

    await mLeagueRankingRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mLeagueRankingRewardUpdatePage.setLeagueHierarchyInput('5'),
      mLeagueRankingRewardUpdatePage.setRankToInput('5'),
      mLeagueRankingRewardUpdatePage.setRewardGroupIdInput('5'),
      mLeagueRankingRewardUpdatePage.setStartAtInput('5'),
      mLeagueRankingRewardUpdatePage.setEndAtInput('5')
    ]);
    expect(await mLeagueRankingRewardUpdatePage.getLeagueHierarchyInput()).to.eq('5', 'Expected leagueHierarchy value to be equals to 5');
    expect(await mLeagueRankingRewardUpdatePage.getRankToInput()).to.eq('5', 'Expected rankTo value to be equals to 5');
    expect(await mLeagueRankingRewardUpdatePage.getRewardGroupIdInput()).to.eq('5', 'Expected rewardGroupId value to be equals to 5');
    expect(await mLeagueRankingRewardUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mLeagueRankingRewardUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mLeagueRankingRewardUpdatePage.save();
    expect(await mLeagueRankingRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLeagueRankingRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLeagueRankingReward', async () => {
    const nbButtonsBeforeDelete = await mLeagueRankingRewardComponentsPage.countDeleteButtons();
    await mLeagueRankingRewardComponentsPage.clickOnLastDeleteButton();

    mLeagueRankingRewardDeleteDialog = new MLeagueRankingRewardDeleteDialog();
    expect(await mLeagueRankingRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M League Ranking Reward?');
    await mLeagueRankingRewardDeleteDialog.clickOnConfirmButton();

    expect(await mLeagueRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
