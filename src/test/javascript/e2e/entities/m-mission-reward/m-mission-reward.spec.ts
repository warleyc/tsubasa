/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMissionRewardComponentsPage, MMissionRewardDeleteDialog, MMissionRewardUpdatePage } from './m-mission-reward.page-object';

const expect = chai.expect;

describe('MMissionReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMissionRewardUpdatePage: MMissionRewardUpdatePage;
  let mMissionRewardComponentsPage: MMissionRewardComponentsPage;
  let mMissionRewardDeleteDialog: MMissionRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMissionRewards', async () => {
    await navBarPage.goToEntity('m-mission-reward');
    mMissionRewardComponentsPage = new MMissionRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMissionRewardComponentsPage.title), 5000);
    expect(await mMissionRewardComponentsPage.getTitle()).to.eq('M Mission Rewards');
  });

  it('should load create MMissionReward page', async () => {
    await mMissionRewardComponentsPage.clickOnCreateButton();
    mMissionRewardUpdatePage = new MMissionRewardUpdatePage();
    expect(await mMissionRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Mission Reward');
    await mMissionRewardUpdatePage.cancel();
  });

  it('should create and save MMissionRewards', async () => {
    const nbButtonsBeforeCreate = await mMissionRewardComponentsPage.countDeleteButtons();

    await mMissionRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMissionRewardUpdatePage.setContentTypeInput('5'),
      mMissionRewardUpdatePage.setContentIdInput('5'),
      mMissionRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mMissionRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mMissionRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mMissionRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mMissionRewardUpdatePage.save();
    expect(await mMissionRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMissionRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMissionReward', async () => {
    const nbButtonsBeforeDelete = await mMissionRewardComponentsPage.countDeleteButtons();
    await mMissionRewardComponentsPage.clickOnLastDeleteButton();

    mMissionRewardDeleteDialog = new MMissionRewardDeleteDialog();
    expect(await mMissionRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Mission Reward?');
    await mMissionRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMissionRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
