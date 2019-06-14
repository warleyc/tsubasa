/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestSpecialRewardComponentsPage,
  MQuestSpecialRewardDeleteDialog,
  MQuestSpecialRewardUpdatePage
} from './m-quest-special-reward.page-object';

const expect = chai.expect;

describe('MQuestSpecialReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestSpecialRewardUpdatePage: MQuestSpecialRewardUpdatePage;
  let mQuestSpecialRewardComponentsPage: MQuestSpecialRewardComponentsPage;
  let mQuestSpecialRewardDeleteDialog: MQuestSpecialRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestSpecialRewards', async () => {
    await navBarPage.goToEntity('m-quest-special-reward');
    mQuestSpecialRewardComponentsPage = new MQuestSpecialRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestSpecialRewardComponentsPage.title), 5000);
    expect(await mQuestSpecialRewardComponentsPage.getTitle()).to.eq('M Quest Special Rewards');
  });

  it('should load create MQuestSpecialReward page', async () => {
    await mQuestSpecialRewardComponentsPage.clickOnCreateButton();
    mQuestSpecialRewardUpdatePage = new MQuestSpecialRewardUpdatePage();
    expect(await mQuestSpecialRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Special Reward');
    await mQuestSpecialRewardUpdatePage.cancel();
  });

  it('should create and save MQuestSpecialRewards', async () => {
    const nbButtonsBeforeCreate = await mQuestSpecialRewardComponentsPage.countDeleteButtons();

    await mQuestSpecialRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestSpecialRewardUpdatePage.setGroupIdInput('5'),
      mQuestSpecialRewardUpdatePage.setWeightInput('5'),
      mQuestSpecialRewardUpdatePage.setRankInput('5'),
      mQuestSpecialRewardUpdatePage.setContentTypeInput('5'),
      mQuestSpecialRewardUpdatePage.setContentIdInput('5'),
      mQuestSpecialRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestSpecialRewardUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestSpecialRewardUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mQuestSpecialRewardUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mQuestSpecialRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestSpecialRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestSpecialRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestSpecialRewardUpdatePage.save();
    expect(await mQuestSpecialRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestSpecialRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestSpecialReward', async () => {
    const nbButtonsBeforeDelete = await mQuestSpecialRewardComponentsPage.countDeleteButtons();
    await mQuestSpecialRewardComponentsPage.clickOnLastDeleteButton();

    mQuestSpecialRewardDeleteDialog = new MQuestSpecialRewardDeleteDialog();
    expect(await mQuestSpecialRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Special Reward?');
    await mQuestSpecialRewardDeleteDialog.clickOnConfirmButton();

    expect(await mQuestSpecialRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
