/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MWeeklyQuestTsubasaPointRewardComponentsPage,
  MWeeklyQuestTsubasaPointRewardDeleteDialog,
  MWeeklyQuestTsubasaPointRewardUpdatePage
} from './m-weekly-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MWeeklyQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mWeeklyQuestTsubasaPointRewardUpdatePage: MWeeklyQuestTsubasaPointRewardUpdatePage;
  let mWeeklyQuestTsubasaPointRewardComponentsPage: MWeeklyQuestTsubasaPointRewardComponentsPage;
  let mWeeklyQuestTsubasaPointRewardDeleteDialog: MWeeklyQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MWeeklyQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-weekly-quest-tsubasa-point-reward');
    mWeeklyQuestTsubasaPointRewardComponentsPage = new MWeeklyQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mWeeklyQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mWeeklyQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Weekly Quest Tsubasa Point Rewards');
  });

  it('should load create MWeeklyQuestTsubasaPointReward page', async () => {
    await mWeeklyQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mWeeklyQuestTsubasaPointRewardUpdatePage = new MWeeklyQuestTsubasaPointRewardUpdatePage();
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Weekly Quest Tsubasa Point Reward');
    await mWeeklyQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MWeeklyQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mWeeklyQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mWeeklyQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mWeeklyQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mWeeklyQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mWeeklyQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mWeeklyQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mWeeklyQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq(
      '5',
      'Expected tsubasaPoint value to be equals to 5'
    );
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mWeeklyQuestTsubasaPointRewardUpdatePage.save();
    expect(await mWeeklyQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mWeeklyQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MWeeklyQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mWeeklyQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mWeeklyQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mWeeklyQuestTsubasaPointRewardDeleteDialog = new MWeeklyQuestTsubasaPointRewardDeleteDialog();
    expect(await mWeeklyQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Weekly Quest Tsubasa Point Reward?'
    );
    await mWeeklyQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mWeeklyQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
