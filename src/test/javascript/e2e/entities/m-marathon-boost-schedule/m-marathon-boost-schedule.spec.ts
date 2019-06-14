/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonBoostScheduleComponentsPage,
  MMarathonBoostScheduleDeleteDialog,
  MMarathonBoostScheduleUpdatePage
} from './m-marathon-boost-schedule.page-object';

const expect = chai.expect;

describe('MMarathonBoostSchedule e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonBoostScheduleUpdatePage: MMarathonBoostScheduleUpdatePage;
  let mMarathonBoostScheduleComponentsPage: MMarathonBoostScheduleComponentsPage;
  let mMarathonBoostScheduleDeleteDialog: MMarathonBoostScheduleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonBoostSchedules', async () => {
    await navBarPage.goToEntity('m-marathon-boost-schedule');
    mMarathonBoostScheduleComponentsPage = new MMarathonBoostScheduleComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonBoostScheduleComponentsPage.title), 5000);
    expect(await mMarathonBoostScheduleComponentsPage.getTitle()).to.eq('M Marathon Boost Schedules');
  });

  it('should load create MMarathonBoostSchedule page', async () => {
    await mMarathonBoostScheduleComponentsPage.clickOnCreateButton();
    mMarathonBoostScheduleUpdatePage = new MMarathonBoostScheduleUpdatePage();
    expect(await mMarathonBoostScheduleUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Boost Schedule');
    await mMarathonBoostScheduleUpdatePage.cancel();
  });

  it('should create and save MMarathonBoostSchedules', async () => {
    const nbButtonsBeforeCreate = await mMarathonBoostScheduleComponentsPage.countDeleteButtons();

    await mMarathonBoostScheduleComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonBoostScheduleUpdatePage.setEventIdInput('5'),
      mMarathonBoostScheduleUpdatePage.setBoostRatioInput('5'),
      mMarathonBoostScheduleUpdatePage.setStartAtInput('5'),
      mMarathonBoostScheduleUpdatePage.setEndAtInput('5')
    ]);
    expect(await mMarathonBoostScheduleUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonBoostScheduleUpdatePage.getBoostRatioInput()).to.eq('5', 'Expected boostRatio value to be equals to 5');
    expect(await mMarathonBoostScheduleUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mMarathonBoostScheduleUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mMarathonBoostScheduleUpdatePage.save();
    expect(await mMarathonBoostScheduleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonBoostScheduleComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonBoostSchedule', async () => {
    const nbButtonsBeforeDelete = await mMarathonBoostScheduleComponentsPage.countDeleteButtons();
    await mMarathonBoostScheduleComponentsPage.clickOnLastDeleteButton();

    mMarathonBoostScheduleDeleteDialog = new MMarathonBoostScheduleDeleteDialog();
    expect(await mMarathonBoostScheduleDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Boost Schedule?'
    );
    await mMarathonBoostScheduleDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonBoostScheduleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
