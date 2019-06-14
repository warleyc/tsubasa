/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLeagueRankingRewardGroupComponentsPage,
  MLeagueRankingRewardGroupDeleteDialog,
  MLeagueRankingRewardGroupUpdatePage
} from './m-league-ranking-reward-group.page-object';

const expect = chai.expect;

describe('MLeagueRankingRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueRankingRewardGroupUpdatePage: MLeagueRankingRewardGroupUpdatePage;
  let mLeagueRankingRewardGroupComponentsPage: MLeagueRankingRewardGroupComponentsPage;
  let mLeagueRankingRewardGroupDeleteDialog: MLeagueRankingRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagueRankingRewardGroups', async () => {
    await navBarPage.goToEntity('m-league-ranking-reward-group');
    mLeagueRankingRewardGroupComponentsPage = new MLeagueRankingRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueRankingRewardGroupComponentsPage.title), 5000);
    expect(await mLeagueRankingRewardGroupComponentsPage.getTitle()).to.eq('M League Ranking Reward Groups');
  });

  it('should load create MLeagueRankingRewardGroup page', async () => {
    await mLeagueRankingRewardGroupComponentsPage.clickOnCreateButton();
    mLeagueRankingRewardGroupUpdatePage = new MLeagueRankingRewardGroupUpdatePage();
    expect(await mLeagueRankingRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M League Ranking Reward Group');
    await mLeagueRankingRewardGroupUpdatePage.cancel();
  });

  it('should create and save MLeagueRankingRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mLeagueRankingRewardGroupComponentsPage.countDeleteButtons();

    await mLeagueRankingRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mLeagueRankingRewardGroupUpdatePage.setGroupIdInput('5'),
      mLeagueRankingRewardGroupUpdatePage.setContentTypeInput('5'),
      mLeagueRankingRewardGroupUpdatePage.setContentIdInput('5'),
      mLeagueRankingRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mLeagueRankingRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mLeagueRankingRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mLeagueRankingRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mLeagueRankingRewardGroupUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mLeagueRankingRewardGroupUpdatePage.save();
    expect(await mLeagueRankingRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLeagueRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLeagueRankingRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mLeagueRankingRewardGroupComponentsPage.countDeleteButtons();
    await mLeagueRankingRewardGroupComponentsPage.clickOnLastDeleteButton();

    mLeagueRankingRewardGroupDeleteDialog = new MLeagueRankingRewardGroupDeleteDialog();
    expect(await mLeagueRankingRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M League Ranking Reward Group?'
    );
    await mLeagueRankingRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mLeagueRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
