/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MDetachActionSkillCostComponentsPage,
  MDetachActionSkillCostDeleteDialog,
  MDetachActionSkillCostUpdatePage
} from './m-detach-action-skill-cost.page-object';

const expect = chai.expect;

describe('MDetachActionSkillCost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDetachActionSkillCostUpdatePage: MDetachActionSkillCostUpdatePage;
  let mDetachActionSkillCostComponentsPage: MDetachActionSkillCostComponentsPage;
  let mDetachActionSkillCostDeleteDialog: MDetachActionSkillCostDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDetachActionSkillCosts', async () => {
    await navBarPage.goToEntity('m-detach-action-skill-cost');
    mDetachActionSkillCostComponentsPage = new MDetachActionSkillCostComponentsPage();
    await browser.wait(ec.visibilityOf(mDetachActionSkillCostComponentsPage.title), 5000);
    expect(await mDetachActionSkillCostComponentsPage.getTitle()).to.eq('M Detach Action Skill Costs');
  });

  it('should load create MDetachActionSkillCost page', async () => {
    await mDetachActionSkillCostComponentsPage.clickOnCreateButton();
    mDetachActionSkillCostUpdatePage = new MDetachActionSkillCostUpdatePage();
    expect(await mDetachActionSkillCostUpdatePage.getPageTitle()).to.eq('Create or edit a M Detach Action Skill Cost');
    await mDetachActionSkillCostUpdatePage.cancel();
  });

  it('should create and save MDetachActionSkillCosts', async () => {
    const nbButtonsBeforeCreate = await mDetachActionSkillCostComponentsPage.countDeleteButtons();

    await mDetachActionSkillCostComponentsPage.clickOnCreateButton();
    await promise.all([mDetachActionSkillCostUpdatePage.setRarityInput('5'), mDetachActionSkillCostUpdatePage.setCostInput('5')]);
    expect(await mDetachActionSkillCostUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mDetachActionSkillCostUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    await mDetachActionSkillCostUpdatePage.save();
    expect(await mDetachActionSkillCostUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDetachActionSkillCostComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDetachActionSkillCost', async () => {
    const nbButtonsBeforeDelete = await mDetachActionSkillCostComponentsPage.countDeleteButtons();
    await mDetachActionSkillCostComponentsPage.clickOnLastDeleteButton();

    mDetachActionSkillCostDeleteDialog = new MDetachActionSkillCostDeleteDialog();
    expect(await mDetachActionSkillCostDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Detach Action Skill Cost?'
    );
    await mDetachActionSkillCostDeleteDialog.clickOnConfirmButton();

    expect(await mDetachActionSkillCostComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
