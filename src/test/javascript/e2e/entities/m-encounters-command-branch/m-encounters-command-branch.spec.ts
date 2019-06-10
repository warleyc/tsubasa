/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MEncountersCommandBranchComponentsPage,
  MEncountersCommandBranchDeleteDialog,
  MEncountersCommandBranchUpdatePage
} from './m-encounters-command-branch.page-object';

const expect = chai.expect;

describe('MEncountersCommandBranch e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEncountersCommandBranchUpdatePage: MEncountersCommandBranchUpdatePage;
  let mEncountersCommandBranchComponentsPage: MEncountersCommandBranchComponentsPage;
  let mEncountersCommandBranchDeleteDialog: MEncountersCommandBranchDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEncountersCommandBranches', async () => {
    await navBarPage.goToEntity('m-encounters-command-branch');
    mEncountersCommandBranchComponentsPage = new MEncountersCommandBranchComponentsPage();
    await browser.wait(ec.visibilityOf(mEncountersCommandBranchComponentsPage.title), 5000);
    expect(await mEncountersCommandBranchComponentsPage.getTitle()).to.eq('M Encounters Command Branches');
  });

  it('should load create MEncountersCommandBranch page', async () => {
    await mEncountersCommandBranchComponentsPage.clickOnCreateButton();
    mEncountersCommandBranchUpdatePage = new MEncountersCommandBranchUpdatePage();
    expect(await mEncountersCommandBranchUpdatePage.getPageTitle()).to.eq('Create or edit a M Encounters Command Branch');
    await mEncountersCommandBranchUpdatePage.cancel();
  });

  it('should create and save MEncountersCommandBranches', async () => {
    const nbButtonsBeforeCreate = await mEncountersCommandBranchComponentsPage.countDeleteButtons();

    await mEncountersCommandBranchComponentsPage.clickOnCreateButton();
    await promise.all([
      mEncountersCommandBranchUpdatePage.setBallFloatConditionInput('5'),
      mEncountersCommandBranchUpdatePage.setConditionInput('5'),
      mEncountersCommandBranchUpdatePage.setEncountersTypeInput('5'),
      mEncountersCommandBranchUpdatePage.setIsSuccessInput('5'),
      mEncountersCommandBranchUpdatePage.setCommandTypeInput('5'),
      mEncountersCommandBranchUpdatePage.setSuccessRateInput('5'),
      mEncountersCommandBranchUpdatePage.setLooseBallRateInput('5'),
      mEncountersCommandBranchUpdatePage.setTouchLightlyRateInput('5'),
      mEncountersCommandBranchUpdatePage.setPostRateInput('5')
    ]);
    expect(await mEncountersCommandBranchUpdatePage.getBallFloatConditionInput()).to.eq(
      '5',
      'Expected ballFloatCondition value to be equals to 5'
    );
    expect(await mEncountersCommandBranchUpdatePage.getConditionInput()).to.eq('5', 'Expected condition value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getEncountersTypeInput()).to.eq('5', 'Expected encountersType value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getIsSuccessInput()).to.eq('5', 'Expected isSuccess value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getCommandTypeInput()).to.eq('5', 'Expected commandType value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getSuccessRateInput()).to.eq('5', 'Expected successRate value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getLooseBallRateInput()).to.eq('5', 'Expected looseBallRate value to be equals to 5');
    expect(await mEncountersCommandBranchUpdatePage.getTouchLightlyRateInput()).to.eq(
      '5',
      'Expected touchLightlyRate value to be equals to 5'
    );
    expect(await mEncountersCommandBranchUpdatePage.getPostRateInput()).to.eq('5', 'Expected postRate value to be equals to 5');
    await mEncountersCommandBranchUpdatePage.save();
    expect(await mEncountersCommandBranchUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEncountersCommandBranchComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MEncountersCommandBranch', async () => {
    const nbButtonsBeforeDelete = await mEncountersCommandBranchComponentsPage.countDeleteButtons();
    await mEncountersCommandBranchComponentsPage.clickOnLastDeleteButton();

    mEncountersCommandBranchDeleteDialog = new MEncountersCommandBranchDeleteDialog();
    expect(await mEncountersCommandBranchDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Encounters Command Branch?'
    );
    await mEncountersCommandBranchDeleteDialog.clickOnConfirmButton();

    expect(await mEncountersCommandBranchComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
