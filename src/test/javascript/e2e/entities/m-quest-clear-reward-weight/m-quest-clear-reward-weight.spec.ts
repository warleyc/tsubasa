/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestClearRewardWeightComponentsPage,
  MQuestClearRewardWeightDeleteDialog,
  MQuestClearRewardWeightUpdatePage
} from './m-quest-clear-reward-weight.page-object';

const expect = chai.expect;

describe('MQuestClearRewardWeight e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestClearRewardWeightUpdatePage: MQuestClearRewardWeightUpdatePage;
  let mQuestClearRewardWeightComponentsPage: MQuestClearRewardWeightComponentsPage;
  let mQuestClearRewardWeightDeleteDialog: MQuestClearRewardWeightDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestClearRewardWeights', async () => {
    await navBarPage.goToEntity('m-quest-clear-reward-weight');
    mQuestClearRewardWeightComponentsPage = new MQuestClearRewardWeightComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestClearRewardWeightComponentsPage.title), 5000);
    expect(await mQuestClearRewardWeightComponentsPage.getTitle()).to.eq('M Quest Clear Reward Weights');
  });

  it('should load create MQuestClearRewardWeight page', async () => {
    await mQuestClearRewardWeightComponentsPage.clickOnCreateButton();
    mQuestClearRewardWeightUpdatePage = new MQuestClearRewardWeightUpdatePage();
    expect(await mQuestClearRewardWeightUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Clear Reward Weight');
    await mQuestClearRewardWeightUpdatePage.cancel();
  });

  it('should create and save MQuestClearRewardWeights', async () => {
    const nbButtonsBeforeCreate = await mQuestClearRewardWeightComponentsPage.countDeleteButtons();

    await mQuestClearRewardWeightComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestClearRewardWeightUpdatePage.setReward1Input('5'),
      mQuestClearRewardWeightUpdatePage.setReward2Input('5'),
      mQuestClearRewardWeightUpdatePage.setReward3Input('5'),
      mQuestClearRewardWeightUpdatePage.setReward4Input('5'),
      mQuestClearRewardWeightUpdatePage.setReward5Input('5')
    ]);
    expect(await mQuestClearRewardWeightUpdatePage.getReward1Input()).to.eq('5', 'Expected reward1 value to be equals to 5');
    expect(await mQuestClearRewardWeightUpdatePage.getReward2Input()).to.eq('5', 'Expected reward2 value to be equals to 5');
    expect(await mQuestClearRewardWeightUpdatePage.getReward3Input()).to.eq('5', 'Expected reward3 value to be equals to 5');
    expect(await mQuestClearRewardWeightUpdatePage.getReward4Input()).to.eq('5', 'Expected reward4 value to be equals to 5');
    expect(await mQuestClearRewardWeightUpdatePage.getReward5Input()).to.eq('5', 'Expected reward5 value to be equals to 5');
    await mQuestClearRewardWeightUpdatePage.save();
    expect(await mQuestClearRewardWeightUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestClearRewardWeightComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestClearRewardWeight', async () => {
    const nbButtonsBeforeDelete = await mQuestClearRewardWeightComponentsPage.countDeleteButtons();
    await mQuestClearRewardWeightComponentsPage.clickOnLastDeleteButton();

    mQuestClearRewardWeightDeleteDialog = new MQuestClearRewardWeightDeleteDialog();
    expect(await mQuestClearRewardWeightDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Quest Clear Reward Weight?'
    );
    await mQuestClearRewardWeightDeleteDialog.clickOnConfirmButton();

    expect(await mQuestClearRewardWeightComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
