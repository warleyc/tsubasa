/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPvpRankingRewardGroupComponentsPage,
  MPvpRankingRewardGroupDeleteDialog,
  MPvpRankingRewardGroupUpdatePage
} from './m-pvp-ranking-reward-group.page-object';

const expect = chai.expect;

describe('MPvpRankingRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpRankingRewardGroupUpdatePage: MPvpRankingRewardGroupUpdatePage;
  let mPvpRankingRewardGroupComponentsPage: MPvpRankingRewardGroupComponentsPage;
  let mPvpRankingRewardGroupDeleteDialog: MPvpRankingRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpRankingRewardGroups', async () => {
    await navBarPage.goToEntity('m-pvp-ranking-reward-group');
    mPvpRankingRewardGroupComponentsPage = new MPvpRankingRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpRankingRewardGroupComponentsPage.title), 5000);
    expect(await mPvpRankingRewardGroupComponentsPage.getTitle()).to.eq('M Pvp Ranking Reward Groups');
  });

  it('should load create MPvpRankingRewardGroup page', async () => {
    await mPvpRankingRewardGroupComponentsPage.clickOnCreateButton();
    mPvpRankingRewardGroupUpdatePage = new MPvpRankingRewardGroupUpdatePage();
    expect(await mPvpRankingRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Ranking Reward Group');
    await mPvpRankingRewardGroupUpdatePage.cancel();
  });

  it('should create and save MPvpRankingRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mPvpRankingRewardGroupComponentsPage.countDeleteButtons();

    await mPvpRankingRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mPvpRankingRewardGroupUpdatePage.setGroupIdInput('5'),
      mPvpRankingRewardGroupUpdatePage.setContentTypeInput('5'),
      mPvpRankingRewardGroupUpdatePage.setContentIdInput('5'),
      mPvpRankingRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mPvpRankingRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mPvpRankingRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mPvpRankingRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mPvpRankingRewardGroupUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mPvpRankingRewardGroupUpdatePage.save();
    expect(await mPvpRankingRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPvpRankingRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mPvpRankingRewardGroupComponentsPage.countDeleteButtons();
    await mPvpRankingRewardGroupComponentsPage.clickOnLastDeleteButton();

    mPvpRankingRewardGroupDeleteDialog = new MPvpRankingRewardGroupDeleteDialog();
    expect(await mPvpRankingRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Pvp Ranking Reward Group?'
    );
    await mPvpRankingRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mPvpRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
