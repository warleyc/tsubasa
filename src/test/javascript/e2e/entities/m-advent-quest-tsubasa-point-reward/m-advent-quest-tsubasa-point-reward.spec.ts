/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAdventQuestTsubasaPointRewardComponentsPage,
  MAdventQuestTsubasaPointRewardDeleteDialog,
  MAdventQuestTsubasaPointRewardUpdatePage
} from './m-advent-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MAdventQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAdventQuestTsubasaPointRewardUpdatePage: MAdventQuestTsubasaPointRewardUpdatePage;
  let mAdventQuestTsubasaPointRewardComponentsPage: MAdventQuestTsubasaPointRewardComponentsPage;
  let mAdventQuestTsubasaPointRewardDeleteDialog: MAdventQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAdventQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-advent-quest-tsubasa-point-reward');
    mAdventQuestTsubasaPointRewardComponentsPage = new MAdventQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mAdventQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mAdventQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Advent Quest Tsubasa Point Rewards');
  });

  it('should load create MAdventQuestTsubasaPointReward page', async () => {
    await mAdventQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mAdventQuestTsubasaPointRewardUpdatePage = new MAdventQuestTsubasaPointRewardUpdatePage();
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Advent Quest Tsubasa Point Reward');
    await mAdventQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MAdventQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mAdventQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mAdventQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mAdventQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mAdventQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mAdventQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mAdventQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mAdventQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq(
      '5',
      'Expected tsubasaPoint value to be equals to 5'
    );
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mAdventQuestTsubasaPointRewardUpdatePage.save();
    expect(await mAdventQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAdventQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MAdventQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mAdventQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mAdventQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mAdventQuestTsubasaPointRewardDeleteDialog = new MAdventQuestTsubasaPointRewardDeleteDialog();
    expect(await mAdventQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Advent Quest Tsubasa Point Reward?'
    );
    await mAdventQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mAdventQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
