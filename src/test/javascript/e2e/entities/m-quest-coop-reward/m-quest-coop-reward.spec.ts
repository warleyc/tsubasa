/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestCoopRewardComponentsPage,
  MQuestCoopRewardDeleteDialog,
  MQuestCoopRewardUpdatePage
} from './m-quest-coop-reward.page-object';

const expect = chai.expect;

describe('MQuestCoopReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestCoopRewardUpdatePage: MQuestCoopRewardUpdatePage;
  let mQuestCoopRewardComponentsPage: MQuestCoopRewardComponentsPage;
  let mQuestCoopRewardDeleteDialog: MQuestCoopRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestCoopRewards', async () => {
    await navBarPage.goToEntity('m-quest-coop-reward');
    mQuestCoopRewardComponentsPage = new MQuestCoopRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestCoopRewardComponentsPage.title), 5000);
    expect(await mQuestCoopRewardComponentsPage.getTitle()).to.eq('M Quest Coop Rewards');
  });

  it('should load create MQuestCoopReward page', async () => {
    await mQuestCoopRewardComponentsPage.clickOnCreateButton();
    mQuestCoopRewardUpdatePage = new MQuestCoopRewardUpdatePage();
    expect(await mQuestCoopRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Coop Reward');
    await mQuestCoopRewardUpdatePage.cancel();
  });

  it('should create and save MQuestCoopRewards', async () => {
    const nbButtonsBeforeCreate = await mQuestCoopRewardComponentsPage.countDeleteButtons();

    await mQuestCoopRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestCoopRewardUpdatePage.setGroupIdInput('5'),
      mQuestCoopRewardUpdatePage.setWeightInput('5'),
      mQuestCoopRewardUpdatePage.setRankInput('5'),
      mQuestCoopRewardUpdatePage.setContentTypeInput('5'),
      mQuestCoopRewardUpdatePage.setContentIdInput('5'),
      mQuestCoopRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestCoopRewardUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestCoopRewardUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mQuestCoopRewardUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mQuestCoopRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestCoopRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestCoopRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestCoopRewardUpdatePage.save();
    expect(await mQuestCoopRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestCoopRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestCoopReward', async () => {
    const nbButtonsBeforeDelete = await mQuestCoopRewardComponentsPage.countDeleteButtons();
    await mQuestCoopRewardComponentsPage.clickOnLastDeleteButton();

    mQuestCoopRewardDeleteDialog = new MQuestCoopRewardDeleteDialog();
    expect(await mQuestCoopRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Coop Reward?');
    await mQuestCoopRewardDeleteDialog.clickOnConfirmButton();

    expect(await mQuestCoopRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
