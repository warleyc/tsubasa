/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestRewardGroupComponentsPage,
  MQuestRewardGroupDeleteDialog,
  MQuestRewardGroupUpdatePage
} from './m-quest-reward-group.page-object';

const expect = chai.expect;

describe('MQuestRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestRewardGroupUpdatePage: MQuestRewardGroupUpdatePage;
  let mQuestRewardGroupComponentsPage: MQuestRewardGroupComponentsPage;
  let mQuestRewardGroupDeleteDialog: MQuestRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestRewardGroups', async () => {
    await navBarPage.goToEntity('m-quest-reward-group');
    mQuestRewardGroupComponentsPage = new MQuestRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestRewardGroupComponentsPage.title), 5000);
    expect(await mQuestRewardGroupComponentsPage.getTitle()).to.eq('M Quest Reward Groups');
  });

  it('should load create MQuestRewardGroup page', async () => {
    await mQuestRewardGroupComponentsPage.clickOnCreateButton();
    mQuestRewardGroupUpdatePage = new MQuestRewardGroupUpdatePage();
    expect(await mQuestRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Reward Group');
    await mQuestRewardGroupUpdatePage.cancel();
  });

  it('should create and save MQuestRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mQuestRewardGroupComponentsPage.countDeleteButtons();

    await mQuestRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestRewardGroupUpdatePage.setGroupIdInput('5'),
      mQuestRewardGroupUpdatePage.setRateInput('5'),
      mQuestRewardGroupUpdatePage.setRankInput('5'),
      mQuestRewardGroupUpdatePage.setContentTypeInput('5'),
      mQuestRewardGroupUpdatePage.setContentIdInput('5'),
      mQuestRewardGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestRewardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestRewardGroupUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    expect(await mQuestRewardGroupUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mQuestRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestRewardGroupUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestRewardGroupUpdatePage.save();
    expect(await mQuestRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mQuestRewardGroupComponentsPage.countDeleteButtons();
    await mQuestRewardGroupComponentsPage.clickOnLastDeleteButton();

    mQuestRewardGroupDeleteDialog = new MQuestRewardGroupDeleteDialog();
    expect(await mQuestRewardGroupDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Reward Group?');
    await mQuestRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mQuestRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
