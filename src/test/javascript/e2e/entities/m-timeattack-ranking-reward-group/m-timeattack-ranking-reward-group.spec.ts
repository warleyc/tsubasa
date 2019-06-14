/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTimeattackRankingRewardGroupComponentsPage,
  MTimeattackRankingRewardGroupDeleteDialog,
  MTimeattackRankingRewardGroupUpdatePage
} from './m-timeattack-ranking-reward-group.page-object';

const expect = chai.expect;

describe('MTimeattackRankingRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTimeattackRankingRewardGroupUpdatePage: MTimeattackRankingRewardGroupUpdatePage;
  let mTimeattackRankingRewardGroupComponentsPage: MTimeattackRankingRewardGroupComponentsPage;
  let mTimeattackRankingRewardGroupDeleteDialog: MTimeattackRankingRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTimeattackRankingRewardGroups', async () => {
    await navBarPage.goToEntity('m-timeattack-ranking-reward-group');
    mTimeattackRankingRewardGroupComponentsPage = new MTimeattackRankingRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTimeattackRankingRewardGroupComponentsPage.title), 5000);
    expect(await mTimeattackRankingRewardGroupComponentsPage.getTitle()).to.eq('M Timeattack Ranking Reward Groups');
  });

  it('should load create MTimeattackRankingRewardGroup page', async () => {
    await mTimeattackRankingRewardGroupComponentsPage.clickOnCreateButton();
    mTimeattackRankingRewardGroupUpdatePage = new MTimeattackRankingRewardGroupUpdatePage();
    expect(await mTimeattackRankingRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Timeattack Ranking Reward Group');
    await mTimeattackRankingRewardGroupUpdatePage.cancel();
  });

  it('should create and save MTimeattackRankingRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mTimeattackRankingRewardGroupComponentsPage.countDeleteButtons();

    await mTimeattackRankingRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mTimeattackRankingRewardGroupUpdatePage.setGroupIdInput('5'),
      mTimeattackRankingRewardGroupUpdatePage.setContentTypeInput('5'),
      mTimeattackRankingRewardGroupUpdatePage.setContentIdInput('5'),
      mTimeattackRankingRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mTimeattackRankingRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mTimeattackRankingRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mTimeattackRankingRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mTimeattackRankingRewardGroupUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mTimeattackRankingRewardGroupUpdatePage.save();
    expect(await mTimeattackRankingRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTimeattackRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTimeattackRankingRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mTimeattackRankingRewardGroupComponentsPage.countDeleteButtons();
    await mTimeattackRankingRewardGroupComponentsPage.clickOnLastDeleteButton();

    mTimeattackRankingRewardGroupDeleteDialog = new MTimeattackRankingRewardGroupDeleteDialog();
    expect(await mTimeattackRankingRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Timeattack Ranking Reward Group?'
    );
    await mTimeattackRankingRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mTimeattackRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
