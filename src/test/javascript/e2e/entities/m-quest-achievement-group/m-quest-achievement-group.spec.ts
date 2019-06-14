/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestAchievementGroupComponentsPage,
  MQuestAchievementGroupDeleteDialog,
  MQuestAchievementGroupUpdatePage
} from './m-quest-achievement-group.page-object';

const expect = chai.expect;

describe('MQuestAchievementGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestAchievementGroupUpdatePage: MQuestAchievementGroupUpdatePage;
  let mQuestAchievementGroupComponentsPage: MQuestAchievementGroupComponentsPage;
  let mQuestAchievementGroupDeleteDialog: MQuestAchievementGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestAchievementGroups', async () => {
    await navBarPage.goToEntity('m-quest-achievement-group');
    mQuestAchievementGroupComponentsPage = new MQuestAchievementGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestAchievementGroupComponentsPage.title), 5000);
    expect(await mQuestAchievementGroupComponentsPage.getTitle()).to.eq('M Quest Achievement Groups');
  });

  it('should load create MQuestAchievementGroup page', async () => {
    await mQuestAchievementGroupComponentsPage.clickOnCreateButton();
    mQuestAchievementGroupUpdatePage = new MQuestAchievementGroupUpdatePage();
    expect(await mQuestAchievementGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Achievement Group');
    await mQuestAchievementGroupUpdatePage.cancel();
  });

  it('should create and save MQuestAchievementGroups', async () => {
    const nbButtonsBeforeCreate = await mQuestAchievementGroupComponentsPage.countDeleteButtons();

    await mQuestAchievementGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestAchievementGroupUpdatePage.setGroupIdInput('5'),
      mQuestAchievementGroupUpdatePage.setAchievementTypeInput('5'),
      mQuestAchievementGroupUpdatePage.setRankInput('5'),
      mQuestAchievementGroupUpdatePage.setWeightInput('5'),
      mQuestAchievementGroupUpdatePage.setContentTypeInput('5'),
      mQuestAchievementGroupUpdatePage.setContentIdInput('5'),
      mQuestAchievementGroupUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mQuestAchievementGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getAchievementTypeInput()).to.eq('5', 'Expected achievementType value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestAchievementGroupUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mQuestAchievementGroupUpdatePage.save();
    expect(await mQuestAchievementGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestAchievementGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestAchievementGroup', async () => {
    const nbButtonsBeforeDelete = await mQuestAchievementGroupComponentsPage.countDeleteButtons();
    await mQuestAchievementGroupComponentsPage.clickOnLastDeleteButton();

    mQuestAchievementGroupDeleteDialog = new MQuestAchievementGroupDeleteDialog();
    expect(await mQuestAchievementGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Quest Achievement Group?'
    );
    await mQuestAchievementGroupDeleteDialog.clickOnConfirmButton();

    expect(await mQuestAchievementGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
