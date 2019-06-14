/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTrainingCostComponentsPage, MTrainingCostDeleteDialog, MTrainingCostUpdatePage } from './m-training-cost.page-object';

const expect = chai.expect;

describe('MTrainingCost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTrainingCostUpdatePage: MTrainingCostUpdatePage;
  let mTrainingCostComponentsPage: MTrainingCostComponentsPage;
  let mTrainingCostDeleteDialog: MTrainingCostDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTrainingCosts', async () => {
    await navBarPage.goToEntity('m-training-cost');
    mTrainingCostComponentsPage = new MTrainingCostComponentsPage();
    await browser.wait(ec.visibilityOf(mTrainingCostComponentsPage.title), 5000);
    expect(await mTrainingCostComponentsPage.getTitle()).to.eq('M Training Costs');
  });

  it('should load create MTrainingCost page', async () => {
    await mTrainingCostComponentsPage.clickOnCreateButton();
    mTrainingCostUpdatePage = new MTrainingCostUpdatePage();
    expect(await mTrainingCostUpdatePage.getPageTitle()).to.eq('Create or edit a M Training Cost');
    await mTrainingCostUpdatePage.cancel();
  });

  it('should create and save MTrainingCosts', async () => {
    const nbButtonsBeforeCreate = await mTrainingCostComponentsPage.countDeleteButtons();

    await mTrainingCostComponentsPage.clickOnCreateButton();
    await promise.all([
      mTrainingCostUpdatePage.setRarityInput('5'),
      mTrainingCostUpdatePage.setLevelInput('5'),
      mTrainingCostUpdatePage.setCostInput('5')
    ]);
    expect(await mTrainingCostUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mTrainingCostUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mTrainingCostUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    await mTrainingCostUpdatePage.save();
    expect(await mTrainingCostUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTrainingCostComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MTrainingCost', async () => {
    const nbButtonsBeforeDelete = await mTrainingCostComponentsPage.countDeleteButtons();
    await mTrainingCostComponentsPage.clickOnLastDeleteButton();

    mTrainingCostDeleteDialog = new MTrainingCostDeleteDialog();
    expect(await mTrainingCostDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Training Cost?');
    await mTrainingCostDeleteDialog.clickOnConfirmButton();

    expect(await mTrainingCostComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
