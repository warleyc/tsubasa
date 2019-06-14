/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuerillaQuestTsubasaPointRewardComponentsPage,
  MGuerillaQuestTsubasaPointRewardDeleteDialog,
  MGuerillaQuestTsubasaPointRewardUpdatePage
} from './m-guerilla-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MGuerillaQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuerillaQuestTsubasaPointRewardUpdatePage: MGuerillaQuestTsubasaPointRewardUpdatePage;
  let mGuerillaQuestTsubasaPointRewardComponentsPage: MGuerillaQuestTsubasaPointRewardComponentsPage;
  let mGuerillaQuestTsubasaPointRewardDeleteDialog: MGuerillaQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuerillaQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-guerilla-quest-tsubasa-point-reward');
    mGuerillaQuestTsubasaPointRewardComponentsPage = new MGuerillaQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mGuerillaQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mGuerillaQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Guerilla Quest Tsubasa Point Rewards');
  });

  it('should load create MGuerillaQuestTsubasaPointReward page', async () => {
    await mGuerillaQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mGuerillaQuestTsubasaPointRewardUpdatePage = new MGuerillaQuestTsubasaPointRewardUpdatePage();
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Guerilla Quest Tsubasa Point Reward');
    await mGuerillaQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MGuerillaQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mGuerillaQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mGuerillaQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuerillaQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mGuerillaQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mGuerillaQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mGuerillaQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mGuerillaQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq(
      '5',
      'Expected tsubasaPoint value to be equals to 5'
    );
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq(
      '5',
      'Expected contentType value to be equals to 5'
    );
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mGuerillaQuestTsubasaPointRewardUpdatePage.save();
    expect(await mGuerillaQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuerillaQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuerillaQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mGuerillaQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mGuerillaQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mGuerillaQuestTsubasaPointRewardDeleteDialog = new MGuerillaQuestTsubasaPointRewardDeleteDialog();
    expect(await mGuerillaQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Guerilla Quest Tsubasa Point Reward?'
    );
    await mGuerillaQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mGuerillaQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
