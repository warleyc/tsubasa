/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRegulatedLeagueRankingRewardComponentsPage,
  MRegulatedLeagueRankingRewardDeleteDialog,
  MRegulatedLeagueRankingRewardUpdatePage
} from './m-regulated-league-ranking-reward.page-object';

const expect = chai.expect;

describe('MRegulatedLeagueRankingReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRegulatedLeagueRankingRewardUpdatePage: MRegulatedLeagueRankingRewardUpdatePage;
  let mRegulatedLeagueRankingRewardComponentsPage: MRegulatedLeagueRankingRewardComponentsPage;
  let mRegulatedLeagueRankingRewardDeleteDialog: MRegulatedLeagueRankingRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRegulatedLeagueRankingRewards', async () => {
    await navBarPage.goToEntity('m-regulated-league-ranking-reward');
    mRegulatedLeagueRankingRewardComponentsPage = new MRegulatedLeagueRankingRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mRegulatedLeagueRankingRewardComponentsPage.title), 5000);
    expect(await mRegulatedLeagueRankingRewardComponentsPage.getTitle()).to.eq('M Regulated League Ranking Rewards');
  });

  it('should load create MRegulatedLeagueRankingReward page', async () => {
    await mRegulatedLeagueRankingRewardComponentsPage.clickOnCreateButton();
    mRegulatedLeagueRankingRewardUpdatePage = new MRegulatedLeagueRankingRewardUpdatePage();
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Regulated League Ranking Reward');
    await mRegulatedLeagueRankingRewardUpdatePage.cancel();
  });

  it('should create and save MRegulatedLeagueRankingRewards', async () => {
    const nbButtonsBeforeCreate = await mRegulatedLeagueRankingRewardComponentsPage.countDeleteButtons();

    await mRegulatedLeagueRankingRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mRegulatedLeagueRankingRewardUpdatePage.setRegulatedLeagueIdInput('5'),
      mRegulatedLeagueRankingRewardUpdatePage.setLeagueHierarchyInput('5'),
      mRegulatedLeagueRankingRewardUpdatePage.setRankToInput('5'),
      mRegulatedLeagueRankingRewardUpdatePage.setRewardGroupIdInput('5')
    ]);
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getRegulatedLeagueIdInput()).to.eq(
      '5',
      'Expected regulatedLeagueId value to be equals to 5'
    );
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getLeagueHierarchyInput()).to.eq(
      '5',
      'Expected leagueHierarchy value to be equals to 5'
    );
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getRankToInput()).to.eq('5', 'Expected rankTo value to be equals to 5');
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getRewardGroupIdInput()).to.eq(
      '5',
      'Expected rewardGroupId value to be equals to 5'
    );
    await mRegulatedLeagueRankingRewardUpdatePage.save();
    expect(await mRegulatedLeagueRankingRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRegulatedLeagueRankingRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRegulatedLeagueRankingReward', async () => {
    const nbButtonsBeforeDelete = await mRegulatedLeagueRankingRewardComponentsPage.countDeleteButtons();
    await mRegulatedLeagueRankingRewardComponentsPage.clickOnLastDeleteButton();

    mRegulatedLeagueRankingRewardDeleteDialog = new MRegulatedLeagueRankingRewardDeleteDialog();
    expect(await mRegulatedLeagueRankingRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Regulated League Ranking Reward?'
    );
    await mRegulatedLeagueRankingRewardDeleteDialog.clickOnConfirmButton();

    expect(await mRegulatedLeagueRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
