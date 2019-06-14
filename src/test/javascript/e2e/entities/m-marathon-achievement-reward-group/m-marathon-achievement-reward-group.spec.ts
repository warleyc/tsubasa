/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonAchievementRewardGroupComponentsPage,
  MMarathonAchievementRewardGroupDeleteDialog,
  MMarathonAchievementRewardGroupUpdatePage
} from './m-marathon-achievement-reward-group.page-object';

const expect = chai.expect;

describe('MMarathonAchievementRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonAchievementRewardGroupUpdatePage: MMarathonAchievementRewardGroupUpdatePage;
  let mMarathonAchievementRewardGroupComponentsPage: MMarathonAchievementRewardGroupComponentsPage;
  let mMarathonAchievementRewardGroupDeleteDialog: MMarathonAchievementRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonAchievementRewardGroups', async () => {
    await navBarPage.goToEntity('m-marathon-achievement-reward-group');
    mMarathonAchievementRewardGroupComponentsPage = new MMarathonAchievementRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonAchievementRewardGroupComponentsPage.title), 5000);
    expect(await mMarathonAchievementRewardGroupComponentsPage.getTitle()).to.eq('M Marathon Achievement Reward Groups');
  });

  it('should load create MMarathonAchievementRewardGroup page', async () => {
    await mMarathonAchievementRewardGroupComponentsPage.clickOnCreateButton();
    mMarathonAchievementRewardGroupUpdatePage = new MMarathonAchievementRewardGroupUpdatePage();
    expect(await mMarathonAchievementRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Achievement Reward Group');
    await mMarathonAchievementRewardGroupUpdatePage.cancel();
  });

  it('should create and save MMarathonAchievementRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mMarathonAchievementRewardGroupComponentsPage.countDeleteButtons();

    await mMarathonAchievementRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonAchievementRewardGroupUpdatePage.setGroupIdInput('5'),
      mMarathonAchievementRewardGroupUpdatePage.setContentTypeInput('5'),
      mMarathonAchievementRewardGroupUpdatePage.setContentIdInput('5'),
      mMarathonAchievementRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mMarathonAchievementRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mMarathonAchievementRewardGroupUpdatePage.getContentTypeInput()).to.eq(
      '5',
      'Expected contentType value to be equals to 5'
    );
    expect(await mMarathonAchievementRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mMarathonAchievementRewardGroupUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mMarathonAchievementRewardGroupUpdatePage.save();
    expect(await mMarathonAchievementRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonAchievementRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonAchievementRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mMarathonAchievementRewardGroupComponentsPage.countDeleteButtons();
    await mMarathonAchievementRewardGroupComponentsPage.clickOnLastDeleteButton();

    mMarathonAchievementRewardGroupDeleteDialog = new MMarathonAchievementRewardGroupDeleteDialog();
    expect(await mMarathonAchievementRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Achievement Reward Group?'
    );
    await mMarathonAchievementRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonAchievementRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
