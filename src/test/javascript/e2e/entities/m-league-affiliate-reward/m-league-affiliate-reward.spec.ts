/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLeagueAffiliateRewardComponentsPage,
  MLeagueAffiliateRewardDeleteDialog,
  MLeagueAffiliateRewardUpdatePage
} from './m-league-affiliate-reward.page-object';

const expect = chai.expect;

describe('MLeagueAffiliateReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueAffiliateRewardUpdatePage: MLeagueAffiliateRewardUpdatePage;
  let mLeagueAffiliateRewardComponentsPage: MLeagueAffiliateRewardComponentsPage;
  let mLeagueAffiliateRewardDeleteDialog: MLeagueAffiliateRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagueAffiliateRewards', async () => {
    await navBarPage.goToEntity('m-league-affiliate-reward');
    mLeagueAffiliateRewardComponentsPage = new MLeagueAffiliateRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueAffiliateRewardComponentsPage.title), 5000);
    expect(await mLeagueAffiliateRewardComponentsPage.getTitle()).to.eq('M League Affiliate Rewards');
  });

  it('should load create MLeagueAffiliateReward page', async () => {
    await mLeagueAffiliateRewardComponentsPage.clickOnCreateButton();
    mLeagueAffiliateRewardUpdatePage = new MLeagueAffiliateRewardUpdatePage();
    expect(await mLeagueAffiliateRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M League Affiliate Reward');
    await mLeagueAffiliateRewardUpdatePage.cancel();
  });

  it('should create and save MLeagueAffiliateRewards', async () => {
    const nbButtonsBeforeCreate = await mLeagueAffiliateRewardComponentsPage.countDeleteButtons();

    await mLeagueAffiliateRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mLeagueAffiliateRewardUpdatePage.setHierarchyInput('5'),
      mLeagueAffiliateRewardUpdatePage.setContentTypeInput('5'),
      mLeagueAffiliateRewardUpdatePage.setContentIdInput('5'),
      mLeagueAffiliateRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mLeagueAffiliateRewardUpdatePage.getHierarchyInput()).to.eq('5', 'Expected hierarchy value to be equals to 5');
    expect(await mLeagueAffiliateRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mLeagueAffiliateRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mLeagueAffiliateRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mLeagueAffiliateRewardUpdatePage.save();
    expect(await mLeagueAffiliateRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLeagueAffiliateRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLeagueAffiliateReward', async () => {
    const nbButtonsBeforeDelete = await mLeagueAffiliateRewardComponentsPage.countDeleteButtons();
    await mLeagueAffiliateRewardComponentsPage.clickOnLastDeleteButton();

    mLeagueAffiliateRewardDeleteDialog = new MLeagueAffiliateRewardDeleteDialog();
    expect(await mLeagueAffiliateRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M League Affiliate Reward?'
    );
    await mLeagueAffiliateRewardDeleteDialog.clickOnConfirmButton();

    expect(await mLeagueAffiliateRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
