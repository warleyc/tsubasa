/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPenaltyKickCutComponentsPage, MPenaltyKickCutDeleteDialog, MPenaltyKickCutUpdatePage } from './m-penalty-kick-cut.page-object';

const expect = chai.expect;

describe('MPenaltyKickCut e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPenaltyKickCutUpdatePage: MPenaltyKickCutUpdatePage;
  let mPenaltyKickCutComponentsPage: MPenaltyKickCutComponentsPage;
  let mPenaltyKickCutDeleteDialog: MPenaltyKickCutDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPenaltyKickCuts', async () => {
    await navBarPage.goToEntity('m-penalty-kick-cut');
    mPenaltyKickCutComponentsPage = new MPenaltyKickCutComponentsPage();
    await browser.wait(ec.visibilityOf(mPenaltyKickCutComponentsPage.title), 5000);
    expect(await mPenaltyKickCutComponentsPage.getTitle()).to.eq('M Penalty Kick Cuts');
  });

  it('should load create MPenaltyKickCut page', async () => {
    await mPenaltyKickCutComponentsPage.clickOnCreateButton();
    mPenaltyKickCutUpdatePage = new MPenaltyKickCutUpdatePage();
    expect(await mPenaltyKickCutUpdatePage.getPageTitle()).to.eq('Create or edit a M Penalty Kick Cut');
    await mPenaltyKickCutUpdatePage.cancel();
  });

  it('should create and save MPenaltyKickCuts', async () => {
    const nbButtonsBeforeCreate = await mPenaltyKickCutComponentsPage.countDeleteButtons();

    await mPenaltyKickCutComponentsPage.clickOnCreateButton();
    await promise.all([
      mPenaltyKickCutUpdatePage.setKickerCourseInput('5'),
      mPenaltyKickCutUpdatePage.setKeeperCourseInput('5'),
      mPenaltyKickCutUpdatePage.setKeeperCommandInput('5'),
      mPenaltyKickCutUpdatePage.setResultTypeInput('5'),
      mPenaltyKickCutUpdatePage.setOffenseSequenceTextInput('offenseSequenceText'),
      mPenaltyKickCutUpdatePage.setDefenseSequenceTextInput('defenseSequenceText')
    ]);
    expect(await mPenaltyKickCutUpdatePage.getKickerCourseInput()).to.eq('5', 'Expected kickerCourse value to be equals to 5');
    expect(await mPenaltyKickCutUpdatePage.getKeeperCourseInput()).to.eq('5', 'Expected keeperCourse value to be equals to 5');
    expect(await mPenaltyKickCutUpdatePage.getKeeperCommandInput()).to.eq('5', 'Expected keeperCommand value to be equals to 5');
    expect(await mPenaltyKickCutUpdatePage.getResultTypeInput()).to.eq('5', 'Expected resultType value to be equals to 5');
    expect(await mPenaltyKickCutUpdatePage.getOffenseSequenceTextInput()).to.eq(
      'offenseSequenceText',
      'Expected OffenseSequenceText value to be equals to offenseSequenceText'
    );
    expect(await mPenaltyKickCutUpdatePage.getDefenseSequenceTextInput()).to.eq(
      'defenseSequenceText',
      'Expected DefenseSequenceText value to be equals to defenseSequenceText'
    );
    await mPenaltyKickCutUpdatePage.save();
    expect(await mPenaltyKickCutUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPenaltyKickCutComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPenaltyKickCut', async () => {
    const nbButtonsBeforeDelete = await mPenaltyKickCutComponentsPage.countDeleteButtons();
    await mPenaltyKickCutComponentsPage.clickOnLastDeleteButton();

    mPenaltyKickCutDeleteDialog = new MPenaltyKickCutDeleteDialog();
    expect(await mPenaltyKickCutDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Penalty Kick Cut?');
    await mPenaltyKickCutDeleteDialog.clickOnConfirmButton();

    expect(await mPenaltyKickCutComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
