/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonQuestTsubasaPointRewardComponentsPage,
  MMarathonQuestTsubasaPointRewardDeleteDialog,
  MMarathonQuestTsubasaPointRewardUpdatePage
} from './m-marathon-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MMarathonQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonQuestTsubasaPointRewardUpdatePage: MMarathonQuestTsubasaPointRewardUpdatePage;
  let mMarathonQuestTsubasaPointRewardComponentsPage: MMarathonQuestTsubasaPointRewardComponentsPage;
  let mMarathonQuestTsubasaPointRewardDeleteDialog: MMarathonQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-marathon-quest-tsubasa-point-reward');
    mMarathonQuestTsubasaPointRewardComponentsPage = new MMarathonQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mMarathonQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Marathon Quest Tsubasa Point Rewards');
  });

  it('should load create MMarathonQuestTsubasaPointReward page', async () => {
    await mMarathonQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mMarathonQuestTsubasaPointRewardUpdatePage = new MMarathonQuestTsubasaPointRewardUpdatePage();
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Quest Tsubasa Point Reward');
    await mMarathonQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MMarathonQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mMarathonQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mMarathonQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mMarathonQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mMarathonQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mMarathonQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mMarathonQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq(
      '5',
      'Expected tsubasaPoint value to be equals to 5'
    );
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq(
      '5',
      'Expected contentType value to be equals to 5'
    );
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mMarathonQuestTsubasaPointRewardUpdatePage.save();
    expect(await mMarathonQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mMarathonQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mMarathonQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mMarathonQuestTsubasaPointRewardDeleteDialog = new MMarathonQuestTsubasaPointRewardDeleteDialog();
    expect(await mMarathonQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Quest Tsubasa Point Reward?'
    );
    await mMarathonQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
