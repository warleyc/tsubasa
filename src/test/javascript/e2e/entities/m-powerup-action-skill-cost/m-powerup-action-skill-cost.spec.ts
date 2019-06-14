/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPowerupActionSkillCostComponentsPage,
  MPowerupActionSkillCostDeleteDialog,
  MPowerupActionSkillCostUpdatePage
} from './m-powerup-action-skill-cost.page-object';

const expect = chai.expect;

describe('MPowerupActionSkillCost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPowerupActionSkillCostUpdatePage: MPowerupActionSkillCostUpdatePage;
  let mPowerupActionSkillCostComponentsPage: MPowerupActionSkillCostComponentsPage;
  let mPowerupActionSkillCostDeleteDialog: MPowerupActionSkillCostDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPowerupActionSkillCosts', async () => {
    await navBarPage.goToEntity('m-powerup-action-skill-cost');
    mPowerupActionSkillCostComponentsPage = new MPowerupActionSkillCostComponentsPage();
    await browser.wait(ec.visibilityOf(mPowerupActionSkillCostComponentsPage.title), 5000);
    expect(await mPowerupActionSkillCostComponentsPage.getTitle()).to.eq('M Powerup Action Skill Costs');
  });

  it('should load create MPowerupActionSkillCost page', async () => {
    await mPowerupActionSkillCostComponentsPage.clickOnCreateButton();
    mPowerupActionSkillCostUpdatePage = new MPowerupActionSkillCostUpdatePage();
    expect(await mPowerupActionSkillCostUpdatePage.getPageTitle()).to.eq('Create or edit a M Powerup Action Skill Cost');
    await mPowerupActionSkillCostUpdatePage.cancel();
  });

  it('should create and save MPowerupActionSkillCosts', async () => {
    const nbButtonsBeforeCreate = await mPowerupActionSkillCostComponentsPage.countDeleteButtons();

    await mPowerupActionSkillCostComponentsPage.clickOnCreateButton();
    await promise.all([
      mPowerupActionSkillCostUpdatePage.setActionRarityInput('5'),
      mPowerupActionSkillCostUpdatePage.setActionLevelInput('5'),
      mPowerupActionSkillCostUpdatePage.setCostInput('5')
    ]);
    expect(await mPowerupActionSkillCostUpdatePage.getActionRarityInput()).to.eq('5', 'Expected actionRarity value to be equals to 5');
    expect(await mPowerupActionSkillCostUpdatePage.getActionLevelInput()).to.eq('5', 'Expected actionLevel value to be equals to 5');
    expect(await mPowerupActionSkillCostUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    await mPowerupActionSkillCostUpdatePage.save();
    expect(await mPowerupActionSkillCostUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPowerupActionSkillCostComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPowerupActionSkillCost', async () => {
    const nbButtonsBeforeDelete = await mPowerupActionSkillCostComponentsPage.countDeleteButtons();
    await mPowerupActionSkillCostComponentsPage.clickOnLastDeleteButton();

    mPowerupActionSkillCostDeleteDialog = new MPowerupActionSkillCostDeleteDialog();
    expect(await mPowerupActionSkillCostDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Powerup Action Skill Cost?'
    );
    await mPowerupActionSkillCostDeleteDialog.clickOnConfirmButton();

    expect(await mPowerupActionSkillCostComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
