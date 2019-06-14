/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MSituationComponentsPage, MSituationDeleteDialog, MSituationUpdatePage } from './m-situation.page-object';

const expect = chai.expect;

describe('MSituation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSituationUpdatePage: MSituationUpdatePage;
  let mSituationComponentsPage: MSituationComponentsPage;
  let mSituationDeleteDialog: MSituationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSituations', async () => {
    await navBarPage.goToEntity('m-situation');
    mSituationComponentsPage = new MSituationComponentsPage();
    await browser.wait(ec.visibilityOf(mSituationComponentsPage.title), 5000);
    expect(await mSituationComponentsPage.getTitle()).to.eq('M Situations');
  });

  it('should load create MSituation page', async () => {
    await mSituationComponentsPage.clickOnCreateButton();
    mSituationUpdatePage = new MSituationUpdatePage();
    expect(await mSituationUpdatePage.getPageTitle()).to.eq('Create or edit a M Situation');
    await mSituationUpdatePage.cancel();
  });

  it('should create and save MSituations', async () => {
    const nbButtonsBeforeCreate = await mSituationComponentsPage.countDeleteButtons();

    await mSituationComponentsPage.clickOnCreateButton();
    await promise.all([
      mSituationUpdatePage.setKickoffInput('5'),
      mSituationUpdatePage.setPenaltyKickInput('5'),
      mSituationUpdatePage.setMatchIntervalInput('5'),
      mSituationUpdatePage.setOutOfPlayInput('5'),
      mSituationUpdatePage.setFoulInput('5'),
      mSituationUpdatePage.setGoalInput('5'),
      mSituationUpdatePage.setScoreInput('5'),
      mSituationUpdatePage.setTimeInput('5')
    ]);
    expect(await mSituationUpdatePage.getKickoffInput()).to.eq('5', 'Expected kickoff value to be equals to 5');
    expect(await mSituationUpdatePage.getPenaltyKickInput()).to.eq('5', 'Expected penaltyKick value to be equals to 5');
    expect(await mSituationUpdatePage.getMatchIntervalInput()).to.eq('5', 'Expected matchInterval value to be equals to 5');
    expect(await mSituationUpdatePage.getOutOfPlayInput()).to.eq('5', 'Expected outOfPlay value to be equals to 5');
    expect(await mSituationUpdatePage.getFoulInput()).to.eq('5', 'Expected foul value to be equals to 5');
    expect(await mSituationUpdatePage.getGoalInput()).to.eq('5', 'Expected goal value to be equals to 5');
    expect(await mSituationUpdatePage.getScoreInput()).to.eq('5', 'Expected score value to be equals to 5');
    expect(await mSituationUpdatePage.getTimeInput()).to.eq('5', 'Expected time value to be equals to 5');
    await mSituationUpdatePage.save();
    expect(await mSituationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSituationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MSituation', async () => {
    const nbButtonsBeforeDelete = await mSituationComponentsPage.countDeleteButtons();
    await mSituationComponentsPage.clickOnLastDeleteButton();

    mSituationDeleteDialog = new MSituationDeleteDialog();
    expect(await mSituationDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Situation?');
    await mSituationDeleteDialog.clickOnConfirmButton();

    expect(await mSituationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
