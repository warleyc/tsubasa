/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPvpGradeComponentsPage, MPvpGradeDeleteDialog, MPvpGradeUpdatePage } from './m-pvp-grade.page-object';

const expect = chai.expect;

describe('MPvpGrade e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpGradeUpdatePage: MPvpGradeUpdatePage;
  let mPvpGradeComponentsPage: MPvpGradeComponentsPage;
  let mPvpGradeDeleteDialog: MPvpGradeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpGrades', async () => {
    await navBarPage.goToEntity('m-pvp-grade');
    mPvpGradeComponentsPage = new MPvpGradeComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpGradeComponentsPage.title), 5000);
    expect(await mPvpGradeComponentsPage.getTitle()).to.eq('M Pvp Grades');
  });

  it('should load create MPvpGrade page', async () => {
    await mPvpGradeComponentsPage.clickOnCreateButton();
    mPvpGradeUpdatePage = new MPvpGradeUpdatePage();
    expect(await mPvpGradeUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Grade');
    await mPvpGradeUpdatePage.cancel();
  });

  it('should create and save MPvpGrades', async () => {
    const nbButtonsBeforeCreate = await mPvpGradeComponentsPage.countDeleteButtons();

    await mPvpGradeComponentsPage.clickOnCreateButton();
    await promise.all([
      mPvpGradeUpdatePage.setWaveIdInput('5'),
      mPvpGradeUpdatePage.setGradeInput('5'),
      mPvpGradeUpdatePage.setIsHigherInput('5'),
      mPvpGradeUpdatePage.setIsLowerInput('5'),
      mPvpGradeUpdatePage.setGradeNameInput('gradeName'),
      mPvpGradeUpdatePage.setLookInput('5'),
      mPvpGradeUpdatePage.setUpRequirementIdInput('5'),
      mPvpGradeUpdatePage.setDownRequirementIdInput('5'),
      mPvpGradeUpdatePage.setWinPointInput('5'),
      mPvpGradeUpdatePage.setLosePointInput('5'),
      mPvpGradeUpdatePage.setGradeUpSerifInput('gradeUpSerif'),
      mPvpGradeUpdatePage.setGradeDownSerifInput('gradeDownSerif'),
      mPvpGradeUpdatePage.setGradeUpCharaAssetNameInput('gradeUpCharaAssetName'),
      mPvpGradeUpdatePage.setGradeDownCharaAssetNameInput('gradeDownCharaAssetName'),
      mPvpGradeUpdatePage.setGradeUpVoiceCharaIdInput('gradeUpVoiceCharaId'),
      mPvpGradeUpdatePage.setGradeDownVoiceCharaIdInput('gradeDownVoiceCharaId'),
      mPvpGradeUpdatePage.setTotalParameterRangeUpperInput('5'),
      mPvpGradeUpdatePage.setTotalParameterRangeLowerInput('5')
    ]);
    expect(await mPvpGradeUpdatePage.getWaveIdInput()).to.eq('5', 'Expected waveId value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getGradeInput()).to.eq('5', 'Expected grade value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getIsHigherInput()).to.eq('5', 'Expected isHigher value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getIsLowerInput()).to.eq('5', 'Expected isLower value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getGradeNameInput()).to.eq('gradeName', 'Expected GradeName value to be equals to gradeName');
    expect(await mPvpGradeUpdatePage.getLookInput()).to.eq('5', 'Expected look value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getUpRequirementIdInput()).to.eq('5', 'Expected upRequirementId value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getDownRequirementIdInput()).to.eq('5', 'Expected downRequirementId value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getWinPointInput()).to.eq('5', 'Expected winPoint value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getLosePointInput()).to.eq('5', 'Expected losePoint value to be equals to 5');
    expect(await mPvpGradeUpdatePage.getGradeUpSerifInput()).to.eq(
      'gradeUpSerif',
      'Expected GradeUpSerif value to be equals to gradeUpSerif'
    );
    expect(await mPvpGradeUpdatePage.getGradeDownSerifInput()).to.eq(
      'gradeDownSerif',
      'Expected GradeDownSerif value to be equals to gradeDownSerif'
    );
    expect(await mPvpGradeUpdatePage.getGradeUpCharaAssetNameInput()).to.eq(
      'gradeUpCharaAssetName',
      'Expected GradeUpCharaAssetName value to be equals to gradeUpCharaAssetName'
    );
    expect(await mPvpGradeUpdatePage.getGradeDownCharaAssetNameInput()).to.eq(
      'gradeDownCharaAssetName',
      'Expected GradeDownCharaAssetName value to be equals to gradeDownCharaAssetName'
    );
    expect(await mPvpGradeUpdatePage.getGradeUpVoiceCharaIdInput()).to.eq(
      'gradeUpVoiceCharaId',
      'Expected GradeUpVoiceCharaId value to be equals to gradeUpVoiceCharaId'
    );
    expect(await mPvpGradeUpdatePage.getGradeDownVoiceCharaIdInput()).to.eq(
      'gradeDownVoiceCharaId',
      'Expected GradeDownVoiceCharaId value to be equals to gradeDownVoiceCharaId'
    );
    expect(await mPvpGradeUpdatePage.getTotalParameterRangeUpperInput()).to.eq(
      '5',
      'Expected totalParameterRangeUpper value to be equals to 5'
    );
    expect(await mPvpGradeUpdatePage.getTotalParameterRangeLowerInput()).to.eq(
      '5',
      'Expected totalParameterRangeLower value to be equals to 5'
    );
    await mPvpGradeUpdatePage.save();
    expect(await mPvpGradeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpGradeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MPvpGrade', async () => {
    const nbButtonsBeforeDelete = await mPvpGradeComponentsPage.countDeleteButtons();
    await mPvpGradeComponentsPage.clickOnLastDeleteButton();

    mPvpGradeDeleteDialog = new MPvpGradeDeleteDialog();
    expect(await mPvpGradeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Pvp Grade?');
    await mPvpGradeDeleteDialog.clickOnConfirmButton();

    expect(await mPvpGradeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
