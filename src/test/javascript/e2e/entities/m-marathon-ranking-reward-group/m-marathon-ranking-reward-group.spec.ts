/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonRankingRewardGroupComponentsPage,
  MMarathonRankingRewardGroupDeleteDialog,
  MMarathonRankingRewardGroupUpdatePage
} from './m-marathon-ranking-reward-group.page-object';

const expect = chai.expect;

describe('MMarathonRankingRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonRankingRewardGroupUpdatePage: MMarathonRankingRewardGroupUpdatePage;
  let mMarathonRankingRewardGroupComponentsPage: MMarathonRankingRewardGroupComponentsPage;
  let mMarathonRankingRewardGroupDeleteDialog: MMarathonRankingRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonRankingRewardGroups', async () => {
    await navBarPage.goToEntity('m-marathon-ranking-reward-group');
    mMarathonRankingRewardGroupComponentsPage = new MMarathonRankingRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonRankingRewardGroupComponentsPage.title), 5000);
    expect(await mMarathonRankingRewardGroupComponentsPage.getTitle()).to.eq('M Marathon Ranking Reward Groups');
  });

  it('should load create MMarathonRankingRewardGroup page', async () => {
    await mMarathonRankingRewardGroupComponentsPage.clickOnCreateButton();
    mMarathonRankingRewardGroupUpdatePage = new MMarathonRankingRewardGroupUpdatePage();
    expect(await mMarathonRankingRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Ranking Reward Group');
    await mMarathonRankingRewardGroupUpdatePage.cancel();
  });

  it('should create and save MMarathonRankingRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mMarathonRankingRewardGroupComponentsPage.countDeleteButtons();

    await mMarathonRankingRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonRankingRewardGroupUpdatePage.setGroupIdInput('5'),
      mMarathonRankingRewardGroupUpdatePage.setContentTypeInput('5'),
      mMarathonRankingRewardGroupUpdatePage.setContentIdInput('5'),
      mMarathonRankingRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mMarathonRankingRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mMarathonRankingRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mMarathonRankingRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mMarathonRankingRewardGroupUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mMarathonRankingRewardGroupUpdatePage.save();
    expect(await mMarathonRankingRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonRankingRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mMarathonRankingRewardGroupComponentsPage.countDeleteButtons();
    await mMarathonRankingRewardGroupComponentsPage.clickOnLastDeleteButton();

    mMarathonRankingRewardGroupDeleteDialog = new MMarathonRankingRewardGroupDeleteDialog();
    expect(await mMarathonRankingRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Ranking Reward Group?'
    );
    await mMarathonRankingRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonRankingRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
