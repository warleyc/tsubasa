/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonLoopRewardComponentsPage,
  MMarathonLoopRewardDeleteDialog,
  MMarathonLoopRewardUpdatePage
} from './m-marathon-loop-reward.page-object';

const expect = chai.expect;

describe('MMarathonLoopReward e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonLoopRewardUpdatePage: MMarathonLoopRewardUpdatePage;
  let mMarathonLoopRewardComponentsPage: MMarathonLoopRewardComponentsPage;
  let mMarathonLoopRewardDeleteDialog: MMarathonLoopRewardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonLoopRewards', async () => {
    await navBarPage.goToEntity('m-marathon-loop-reward');
    mMarathonLoopRewardComponentsPage = new MMarathonLoopRewardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonLoopRewardComponentsPage.title), 5000);
    expect(await mMarathonLoopRewardComponentsPage.getTitle()).to.eq('M Marathon Loop Rewards');
  });

  it('should load create MMarathonLoopReward page', async () => {
    await mMarathonLoopRewardComponentsPage.clickOnCreateButton();
    mMarathonLoopRewardUpdatePage = new MMarathonLoopRewardUpdatePage();
    expect(await mMarathonLoopRewardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Loop Reward');
    await mMarathonLoopRewardUpdatePage.cancel();
  });

  it('should create and save MMarathonLoopRewards', async () => {
    const nbButtonsBeforeCreate = await mMarathonLoopRewardComponentsPage.countDeleteButtons();

    await mMarathonLoopRewardComponentsPage.clickOnCreateButton();
    await promise.all([mMarathonLoopRewardUpdatePage.setEventIdInput('5'), mMarathonLoopRewardUpdatePage.setLoopPointInput('5')]);
    expect(await mMarathonLoopRewardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonLoopRewardUpdatePage.getLoopPointInput()).to.eq('5', 'Expected loopPoint value to be equals to 5');
    await mMarathonLoopRewardUpdatePage.save();
    expect(await mMarathonLoopRewardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonLoopRewardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonLoopReward', async () => {
    const nbButtonsBeforeDelete = await mMarathonLoopRewardComponentsPage.countDeleteButtons();
    await mMarathonLoopRewardComponentsPage.clickOnLastDeleteButton();

    mMarathonLoopRewardDeleteDialog = new MMarathonLoopRewardDeleteDialog();
    expect(await mMarathonLoopRewardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Marathon Loop Reward?');
    await mMarathonLoopRewardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonLoopRewardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
