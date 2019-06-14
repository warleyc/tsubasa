/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGoalJudgementComponentsPage, MGoalJudgementDeleteDialog, MGoalJudgementUpdatePage } from './m-goal-judgement.page-object';

const expect = chai.expect;

describe('MGoalJudgement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGoalJudgementUpdatePage: MGoalJudgementUpdatePage;
  let mGoalJudgementComponentsPage: MGoalJudgementComponentsPage;
  let mGoalJudgementDeleteDialog: MGoalJudgementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGoalJudgements', async () => {
    await navBarPage.goToEntity('m-goal-judgement');
    mGoalJudgementComponentsPage = new MGoalJudgementComponentsPage();
    await browser.wait(ec.visibilityOf(mGoalJudgementComponentsPage.title), 5000);
    expect(await mGoalJudgementComponentsPage.getTitle()).to.eq('M Goal Judgements');
  });

  it('should load create MGoalJudgement page', async () => {
    await mGoalJudgementComponentsPage.clickOnCreateButton();
    mGoalJudgementUpdatePage = new MGoalJudgementUpdatePage();
    expect(await mGoalJudgementUpdatePage.getPageTitle()).to.eq('Create or edit a M Goal Judgement');
    await mGoalJudgementUpdatePage.cancel();
  });

  it('should create and save MGoalJudgements', async () => {
    const nbButtonsBeforeCreate = await mGoalJudgementComponentsPage.countDeleteButtons();

    await mGoalJudgementComponentsPage.clickOnCreateButton();
    await promise.all([
      mGoalJudgementUpdatePage.setJudgementIdInput('5'),
      mGoalJudgementUpdatePage.setEncountersTypeInput('5'),
      mGoalJudgementUpdatePage.setSuccessRateInput('5'),
      mGoalJudgementUpdatePage.setGoalPostRateInput('5'),
      mGoalJudgementUpdatePage.setBallPushRateInput('5'),
      mGoalJudgementUpdatePage.setClearRateInput('5')
    ]);
    expect(await mGoalJudgementUpdatePage.getJudgementIdInput()).to.eq('5', 'Expected judgementId value to be equals to 5');
    expect(await mGoalJudgementUpdatePage.getEncountersTypeInput()).to.eq('5', 'Expected encountersType value to be equals to 5');
    expect(await mGoalJudgementUpdatePage.getSuccessRateInput()).to.eq('5', 'Expected successRate value to be equals to 5');
    expect(await mGoalJudgementUpdatePage.getGoalPostRateInput()).to.eq('5', 'Expected goalPostRate value to be equals to 5');
    expect(await mGoalJudgementUpdatePage.getBallPushRateInput()).to.eq('5', 'Expected ballPushRate value to be equals to 5');
    expect(await mGoalJudgementUpdatePage.getClearRateInput()).to.eq('5', 'Expected clearRate value to be equals to 5');
    await mGoalJudgementUpdatePage.save();
    expect(await mGoalJudgementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGoalJudgementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGoalJudgement', async () => {
    const nbButtonsBeforeDelete = await mGoalJudgementComponentsPage.countDeleteButtons();
    await mGoalJudgementComponentsPage.clickOnLastDeleteButton();

    mGoalJudgementDeleteDialog = new MGoalJudgementDeleteDialog();
    expect(await mGoalJudgementDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Goal Judgement?');
    await mGoalJudgementDeleteDialog.clickOnConfirmButton();

    expect(await mGoalJudgementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
