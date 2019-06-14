/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPvpRankingRewardComponentsPage,
  MPvpRankingRewardDeleteDialog,
  MPvpRankingRewardUpdatePage
} from './m-pvp-ranking-reward.page-object';

const expect = chai.expect;

describe('MPvpRankingReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpRankingRewardUpdatePage: MPvpRankingRewardUpdatePage;
  let mPvpRankingRewardComponentsPage: MPvpRankingRewardComponentsPage;
  /*let mPvpRankingRewardDeleteDialog: MPvpRankingRewardDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpRankingRewards', async () => {
    await navBarPage.goToEntity('m-pvp-ranking-reward');
    mPvpRankingRewardComponentsPage = new MPvpRankingRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpRankingRewardComponentsPage.title), 5000);
    expect(await mPvpRankingRewardComponentsPage.getTitle()).to.eq('M Pvp Ranking Rewards');
  });

  it('should load create MPvpRankingReward page', async () => {
    await mPvpRankingRewardComponentsPage.clickOnCreateButton();
    mPvpRankingRewardUpdatePage = new MPvpRankingRewardUpdatePage();
    expect(await mPvpRankingRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Ranking Reward');
    await mPvpRankingRewardUpdatePage.cancel();
  });

  /* it('should create and save MPvpRankingRewards', async () => {
        const nbButtonsBeforeCreate = await mPvpRankingRewardComponentsPage.countDeleteButtons();

        await mPvpRankingRewardComponentsPage.clickOnCreateButton();
        await promise.all([
            mPvpRankingRewardUpdatePage.setWaveIdInput('5'),
            mPvpRankingRewardUpdatePage.setDifficultyInput('5'),
            mPvpRankingRewardUpdatePage.setRankingFromInput('5'),
            mPvpRankingRewardUpdatePage.setRankingToInput('5'),
            mPvpRankingRewardUpdatePage.setRewardGroupIdInput('5'),
            mPvpRankingRewardUpdatePage.idSelectLastOption(),
        ]);
        expect(await mPvpRankingRewardUpdatePage.getWaveIdInput()).to.eq('5', 'Expected waveId value to be equals to 5');
        expect(await mPvpRankingRewardUpdatePage.getDifficultyInput()).to.eq('5', 'Expected difficulty value to be equals to 5');
        expect(await mPvpRankingRewardUpdatePage.getRankingFromInput()).to.eq('5', 'Expected rankingFrom value to be equals to 5');
        expect(await mPvpRankingRewardUpdatePage.getRankingToInput()).to.eq('5', 'Expected rankingTo value to be equals to 5');
        expect(await mPvpRankingRewardUpdatePage.getRewardGroupIdInput()).to.eq('5', 'Expected rewardGroupId value to be equals to 5');
        await mPvpRankingRewardUpdatePage.save();
        expect(await mPvpRankingRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mPvpRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MPvpRankingReward', async () => {
        const nbButtonsBeforeDelete = await mPvpRankingRewardComponentsPage.countDeleteButtons();
        await mPvpRankingRewardComponentsPage.clickOnLastDeleteButton();

        mPvpRankingRewardDeleteDialog = new MPvpRankingRewardDeleteDialog();
        expect(await mPvpRankingRewardDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Pvp Ranking Reward?');
        await mPvpRankingRewardDeleteDialog.clickOnConfirmButton();

        expect(await mPvpRankingRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
