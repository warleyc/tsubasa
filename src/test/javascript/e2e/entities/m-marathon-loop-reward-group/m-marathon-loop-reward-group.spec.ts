/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonLoopRewardGroupComponentsPage,
  MMarathonLoopRewardGroupDeleteDialog,
  MMarathonLoopRewardGroupUpdatePage
} from './m-marathon-loop-reward-group.page-object';

const expect = chai.expect;

describe('MMarathonLoopRewardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonLoopRewardGroupUpdatePage: MMarathonLoopRewardGroupUpdatePage;
  let mMarathonLoopRewardGroupComponentsPage: MMarathonLoopRewardGroupComponentsPage;
  let mMarathonLoopRewardGroupDeleteDialog: MMarathonLoopRewardGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonLoopRewardGroups', async () => {
    await navBarPage.goToEntity('m-marathon-loop-reward-group');
    mMarathonLoopRewardGroupComponentsPage = new MMarathonLoopRewardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonLoopRewardGroupComponentsPage.title), 5000);
    expect(await mMarathonLoopRewardGroupComponentsPage.getTitle()).to.eq('M Marathon Loop Reward Groups');
  });

  it('should load create MMarathonLoopRewardGroup page', async () => {
    await mMarathonLoopRewardGroupComponentsPage.clickOnCreateButton();
    mMarathonLoopRewardGroupUpdatePage = new MMarathonLoopRewardGroupUpdatePage();
    expect(await mMarathonLoopRewardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Loop Reward Group');
    await mMarathonLoopRewardGroupUpdatePage.cancel();
  });

  it('should create and save MMarathonLoopRewardGroups', async () => {
    const nbButtonsBeforeCreate = await mMarathonLoopRewardGroupComponentsPage.countDeleteButtons();

    await mMarathonLoopRewardGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonLoopRewardGroupUpdatePage.setEventIdInput('5'),
      mMarathonLoopRewardGroupUpdatePage.setContentTypeInput('5'),
      mMarathonLoopRewardGroupUpdatePage.setContentIdInput('5'),
      mMarathonLoopRewardGroupUpdatePage.setContentAmountInput('5'),
      mMarathonLoopRewardGroupUpdatePage.setWeightInput('5')
    ]);
    expect(await mMarathonLoopRewardGroupUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonLoopRewardGroupUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mMarathonLoopRewardGroupUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mMarathonLoopRewardGroupUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    expect(await mMarathonLoopRewardGroupUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    await mMarathonLoopRewardGroupUpdatePage.save();
    expect(await mMarathonLoopRewardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonLoopRewardGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonLoopRewardGroup', async () => {
    const nbButtonsBeforeDelete = await mMarathonLoopRewardGroupComponentsPage.countDeleteButtons();
    await mMarathonLoopRewardGroupComponentsPage.clickOnLastDeleteButton();

    mMarathonLoopRewardGroupDeleteDialog = new MMarathonLoopRewardGroupDeleteDialog();
    expect(await mMarathonLoopRewardGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Loop Reward Group?'
    );
    await mMarathonLoopRewardGroupDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonLoopRewardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
