/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MInheritActionSkillCostComponentsPage,
  MInheritActionSkillCostDeleteDialog,
  MInheritActionSkillCostUpdatePage
} from './m-inherit-action-skill-cost.page-object';

const expect = chai.expect;

describe('MInheritActionSkillCost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mInheritActionSkillCostUpdatePage: MInheritActionSkillCostUpdatePage;
  let mInheritActionSkillCostComponentsPage: MInheritActionSkillCostComponentsPage;
  let mInheritActionSkillCostDeleteDialog: MInheritActionSkillCostDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MInheritActionSkillCosts', async () => {
    await navBarPage.goToEntity('m-inherit-action-skill-cost');
    mInheritActionSkillCostComponentsPage = new MInheritActionSkillCostComponentsPage();
    await browser.wait(ec.visibilityOf(mInheritActionSkillCostComponentsPage.title), 5000);
    expect(await mInheritActionSkillCostComponentsPage.getTitle()).to.eq('M Inherit Action Skill Costs');
  });

  it('should load create MInheritActionSkillCost page', async () => {
    await mInheritActionSkillCostComponentsPage.clickOnCreateButton();
    mInheritActionSkillCostUpdatePage = new MInheritActionSkillCostUpdatePage();
    expect(await mInheritActionSkillCostUpdatePage.getPageTitle()).to.eq('Create or edit a M Inherit Action Skill Cost');
    await mInheritActionSkillCostUpdatePage.cancel();
  });

  it('should create and save MInheritActionSkillCosts', async () => {
    const nbButtonsBeforeCreate = await mInheritActionSkillCostComponentsPage.countDeleteButtons();

    await mInheritActionSkillCostComponentsPage.clickOnCreateButton();
    await promise.all([
      mInheritActionSkillCostUpdatePage.setRarityInput('5'),
      mInheritActionSkillCostUpdatePage.setLevelInput('5'),
      mInheritActionSkillCostUpdatePage.setCostInput('5')
    ]);
    expect(await mInheritActionSkillCostUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mInheritActionSkillCostUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mInheritActionSkillCostUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    await mInheritActionSkillCostUpdatePage.save();
    expect(await mInheritActionSkillCostUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mInheritActionSkillCostComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MInheritActionSkillCost', async () => {
    const nbButtonsBeforeDelete = await mInheritActionSkillCostComponentsPage.countDeleteButtons();
    await mInheritActionSkillCostComponentsPage.clickOnLastDeleteButton();

    mInheritActionSkillCostDeleteDialog = new MInheritActionSkillCostDeleteDialog();
    expect(await mInheritActionSkillCostDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Inherit Action Skill Cost?'
    );
    await mInheritActionSkillCostDeleteDialog.clickOnConfirmButton();

    expect(await mInheritActionSkillCostComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
