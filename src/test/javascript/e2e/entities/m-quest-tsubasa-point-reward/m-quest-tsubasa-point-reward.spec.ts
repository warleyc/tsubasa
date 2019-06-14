/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestTsubasaPointRewardComponentsPage,
  MQuestTsubasaPointRewardDeleteDialog,
  MQuestTsubasaPointRewardUpdatePage
} from './m-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestTsubasaPointRewardUpdatePage: MQuestTsubasaPointRewardUpdatePage;
  let mQuestTsubasaPointRewardComponentsPage: MQuestTsubasaPointRewardComponentsPage;
  let mQuestTsubasaPointRewardDeleteDialog: MQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-quest-tsubasa-point-reward');
    mQuestTsubasaPointRewardComponentsPage = new MQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Quest Tsubasa Point Rewards');
  });

  it('should load create MQuestTsubasaPointReward page', async () => {
    await mQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mQuestTsubasaPointRewardUpdatePage = new MQuestTsubasaPointRewardUpdatePage();
    expect(await mQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Tsubasa Point Reward');
    await mQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq('5', 'Expected tsubasaPoint value to be equals to 5');
    expect(await mQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestTsubasaPointRewardUpdatePage.save();
    expect(await mQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mQuestTsubasaPointRewardDeleteDialog = new MQuestTsubasaPointRewardDeleteDialog();
    expect(await mQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Quest Tsubasa Point Reward?'
    );
    await mQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
