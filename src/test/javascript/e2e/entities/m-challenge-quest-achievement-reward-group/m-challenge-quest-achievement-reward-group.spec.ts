/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChallengeQuestAchievementRewardGroupComponentsPage,
  MChallengeQuestAchievementRewardGroupDeleteDialog,
  MChallengeQuestAchievementRewardGroupUpdatePage
} from './m-challenge-quest-achievement-reward-group.page-object';

const expect = chai.expect;

describe('MChallengeQuestAchievementRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChallengeQuestAchievementRewardGroupUpdatePage: MChallengeQuestAchievementRewardGroupUpdatePage;
  let mChallengeQuestAchievementRewardGroupComponentsPage: MChallengeQuestAchievementRewardGroupComponentsPage;
  let mChallengeQuestAchievementRewardGroupDeleteDialog: MChallengeQuestAchievementRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChallengeQuestAchievementRewardGroups', async () => {
    await navBarPage.goToEntity('m-challenge-quest-achievement-reward-group');
    mChallengeQuestAchievementRewardGroupComponentsPage = new MChallengeQuestAchievementRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mChallengeQuestAchievementRewardGroupComponentsPage.title), 5000);
    expect(await mChallengeQuestAchievementRewardGroupComponentsPage.getTitle()).to.eq('M Challenge Quest Achievement Reward Groups');
  });

  it('should load create MChallengeQuestAchievementRewardGroup page', async () => {
    await mChallengeQuestAchievementRewardGroupComponentsPage.clickOnCreateButton();
    mChallengeQuestAchievementRewardGroupUpdatePage = new MChallengeQuestAchievementRewardGroupUpdatePage();
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getPageTitle()).to.eq(
      'Create or edit a M Challenge Quest Achievement Reward Group'
    );
    await mChallengeQuestAchievementRewardGroupUpdatePage.cancel();
  });

  it('should create and save MChallengeQuestAchievementRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mChallengeQuestAchievementRewardGroupComponentsPage.countDeleteButtons();

    await mChallengeQuestAchievementRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mChallengeQuestAchievementRewardGroupUpdatePage.setGroupIdInput('5'),
      mChallengeQuestAchievementRewardGroupUpdatePage.setContentTypeInput('5'),
      mChallengeQuestAchievementRewardGroupUpdatePage.setContentIdInput('5'),
      mChallengeQuestAchievementRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getContentTypeInput()).to.eq(
      '5',
      'Expected contentType value to be equals to 5'
    );
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getContentIdInput()).to.eq(
      '5',
      'Expected contentId value to be equals to 5'
    );
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mChallengeQuestAchievementRewardGroupUpdatePage.save();
    expect(await mChallengeQuestAchievementRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mChallengeQuestAchievementRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MChallengeQuestAchievementRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mChallengeQuestAchievementRewardGroupComponentsPage.countDeleteButtons();
    await mChallengeQuestAchievementRewardGroupComponentsPage.clickOnLastDeleteButton();

    mChallengeQuestAchievementRewardGroupDeleteDialog = new MChallengeQuestAchievementRewardGroupDeleteDialog();
    expect(await mChallengeQuestAchievementRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Challenge Quest Achievement Reward Group?'
    );
    await mChallengeQuestAchievementRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mChallengeQuestAchievementRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
