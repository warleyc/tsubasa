/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTicketQuestTsubasaPointRewardComponentsPage,
  MTicketQuestTsubasaPointRewardDeleteDialog,
  MTicketQuestTsubasaPointRewardUpdatePage
} from './m-ticket-quest-tsubasa-point-reward.page-object';

const expect = chai.expect;

describe('MTicketQuestTsubasaPointReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTicketQuestTsubasaPointRewardUpdatePage: MTicketQuestTsubasaPointRewardUpdatePage;
  let mTicketQuestTsubasaPointRewardComponentsPage: MTicketQuestTsubasaPointRewardComponentsPage;
  let mTicketQuestTsubasaPointRewardDeleteDialog: MTicketQuestTsubasaPointRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTicketQuestTsubasaPointRewards', async () => {
    await navBarPage.goToEntity('m-ticket-quest-tsubasa-point-reward');
    mTicketQuestTsubasaPointRewardComponentsPage = new MTicketQuestTsubasaPointRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mTicketQuestTsubasaPointRewardComponentsPage.title), 5000);
    expect(await mTicketQuestTsubasaPointRewardComponentsPage.getTitle()).to.eq('M Ticket Quest Tsubasa Point Rewards');
  });

  it('should load create MTicketQuestTsubasaPointReward page', async () => {
    await mTicketQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    mTicketQuestTsubasaPointRewardUpdatePage = new MTicketQuestTsubasaPointRewardUpdatePage();
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Ticket Quest Tsubasa Point Reward');
    await mTicketQuestTsubasaPointRewardUpdatePage.cancel();
  });

  it('should create and save MTicketQuestTsubasaPointRewards', async () => {
    const nbButtonsBeforeCreate = await mTicketQuestTsubasaPointRewardComponentsPage.countDeleteButtons();

    await mTicketQuestTsubasaPointRewardComponentsPage.clickOnCreateButton();
    await promise.all([
      mTicketQuestTsubasaPointRewardUpdatePage.setStageIdInput('5'),
      mTicketQuestTsubasaPointRewardUpdatePage.setTsubasaPointInput('5'),
      mTicketQuestTsubasaPointRewardUpdatePage.setContentTypeInput('5'),
      mTicketQuestTsubasaPointRewardUpdatePage.setContentIdInput('5'),
      mTicketQuestTsubasaPointRewardUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getStageIdInput()).to.eq('5', 'Expected stageId value to be equals to 5');
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getTsubasaPointInput()).to.eq(
      '5',
      'Expected tsubasaPoint value to be equals to 5'
    );
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getContentAmountInput()).to.eq(
      '5',
      'Expected contentAmount value to be equals to 5'
    );
    await mTicketQuestTsubasaPointRewardUpdatePage.save();
    expect(await mTicketQuestTsubasaPointRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTicketQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTicketQuestTsubasaPointReward', async () => {
    const nbButtonsBeforeDelete = await mTicketQuestTsubasaPointRewardComponentsPage.countDeleteButtons();
    await mTicketQuestTsubasaPointRewardComponentsPage.clickOnLastDeleteButton();

    mTicketQuestTsubasaPointRewardDeleteDialog = new MTicketQuestTsubasaPointRewardDeleteDialog();
    expect(await mTicketQuestTsubasaPointRewardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Ticket Quest Tsubasa Point Reward?'
    );
    await mTicketQuestTsubasaPointRewardDeleteDialog.clickOnConfirmButton();

    expect(await mTicketQuestTsubasaPointRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
