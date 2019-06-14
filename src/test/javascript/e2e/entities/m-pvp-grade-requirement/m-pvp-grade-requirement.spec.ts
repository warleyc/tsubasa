/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPvpGradeRequirementComponentsPage,
  MPvpGradeRequirementDeleteDialog,
  MPvpGradeRequirementUpdatePage
} from './m-pvp-grade-requirement.page-object';

const expect = chai.expect;

describe('MPvpGradeRequirement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpGradeRequirementUpdatePage: MPvpGradeRequirementUpdatePage;
  let mPvpGradeRequirementComponentsPage: MPvpGradeRequirementComponentsPage;
  let mPvpGradeRequirementDeleteDialog: MPvpGradeRequirementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpGradeRequirements', async () => {
    await navBarPage.goToEntity('m-pvp-grade-requirement');
    mPvpGradeRequirementComponentsPage = new MPvpGradeRequirementComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpGradeRequirementComponentsPage.title), 5000);
    expect(await mPvpGradeRequirementComponentsPage.getTitle()).to.eq('M Pvp Grade Requirements');
  });

  it('should load create MPvpGradeRequirement page', async () => {
    await mPvpGradeRequirementComponentsPage.clickOnCreateButton();
    mPvpGradeRequirementUpdatePage = new MPvpGradeRequirementUpdatePage();
    expect(await mPvpGradeRequirementUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Grade Requirement');
    await mPvpGradeRequirementUpdatePage.cancel();
  });

  it('should create and save MPvpGradeRequirements', async () => {
    const nbButtonsBeforeCreate = await mPvpGradeRequirementComponentsPage.countDeleteButtons();

    await mPvpGradeRequirementComponentsPage.clickOnCreateButton();
    await promise.all([
      mPvpGradeRequirementUpdatePage.setUpDescriptionInput('upDescription'),
      mPvpGradeRequirementUpdatePage.setDownDescriptionInput('downDescription'),
      mPvpGradeRequirementUpdatePage.setTargetTypeInput('5'),
      mPvpGradeRequirementUpdatePage.setTargetWaveInput('5'),
      mPvpGradeRequirementUpdatePage.setTargetLowerGradeInput('5'),
      mPvpGradeRequirementUpdatePage.setTargetPointInput('5')
    ]);
    expect(await mPvpGradeRequirementUpdatePage.getUpDescriptionInput()).to.eq(
      'upDescription',
      'Expected UpDescription value to be equals to upDescription'
    );
    expect(await mPvpGradeRequirementUpdatePage.getDownDescriptionInput()).to.eq(
      'downDescription',
      'Expected DownDescription value to be equals to downDescription'
    );
    expect(await mPvpGradeRequirementUpdatePage.getTargetTypeInput()).to.eq('5', 'Expected targetType value to be equals to 5');
    expect(await mPvpGradeRequirementUpdatePage.getTargetWaveInput()).to.eq('5', 'Expected targetWave value to be equals to 5');
    expect(await mPvpGradeRequirementUpdatePage.getTargetLowerGradeInput()).to.eq('5', 'Expected targetLowerGrade value to be equals to 5');
    expect(await mPvpGradeRequirementUpdatePage.getTargetPointInput()).to.eq('5', 'Expected targetPoint value to be equals to 5');
    await mPvpGradeRequirementUpdatePage.save();
    expect(await mPvpGradeRequirementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpGradeRequirementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPvpGradeRequirement', async () => {
    const nbButtonsBeforeDelete = await mPvpGradeRequirementComponentsPage.countDeleteButtons();
    await mPvpGradeRequirementComponentsPage.clickOnLastDeleteButton();

    mPvpGradeRequirementDeleteDialog = new MPvpGradeRequirementDeleteDialog();
    expect(await mPvpGradeRequirementDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Pvp Grade Requirement?');
    await mPvpGradeRequirementDeleteDialog.clickOnConfirmButton();

    expect(await mPvpGradeRequirementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
