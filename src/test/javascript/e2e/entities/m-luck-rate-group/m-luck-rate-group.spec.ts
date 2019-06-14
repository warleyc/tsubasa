/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MLuckRateGroupComponentsPage, MLuckRateGroupDeleteDialog, MLuckRateGroupUpdatePage } from './m-luck-rate-group.page-object';

const expect = chai.expect;

describe('MLuckRateGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLuckRateGroupUpdatePage: MLuckRateGroupUpdatePage;
  let mLuckRateGroupComponentsPage: MLuckRateGroupComponentsPage;
  let mLuckRateGroupDeleteDialog: MLuckRateGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLuckRateGroups', async () => {
    await navBarPage.goToEntity('m-luck-rate-group');
    mLuckRateGroupComponentsPage = new MLuckRateGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mLuckRateGroupComponentsPage.title), 5000);
    expect(await mLuckRateGroupComponentsPage.getTitle()).to.eq('M Luck Rate Groups');
  });

  it('should load create MLuckRateGroup page', async () => {
    await mLuckRateGroupComponentsPage.clickOnCreateButton();
    mLuckRateGroupUpdatePage = new MLuckRateGroupUpdatePage();
    expect(await mLuckRateGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Luck Rate Group');
    await mLuckRateGroupUpdatePage.cancel();
  });

  it('should create and save MLuckRateGroups', async () => {
    const nbButtonsBeforeCreate = await mLuckRateGroupComponentsPage.countDeleteButtons();

    await mLuckRateGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mLuckRateGroupUpdatePage.setGroupIdInput('5'),
      mLuckRateGroupUpdatePage.setRarityInput('5'),
      mLuckRateGroupUpdatePage.setRateInput('5')
    ]);
    expect(await mLuckRateGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mLuckRateGroupUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mLuckRateGroupUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    await mLuckRateGroupUpdatePage.save();
    expect(await mLuckRateGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLuckRateGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLuckRateGroup', async () => {
    const nbButtonsBeforeDelete = await mLuckRateGroupComponentsPage.countDeleteButtons();
    await mLuckRateGroupComponentsPage.clickOnLastDeleteButton();

    mLuckRateGroupDeleteDialog = new MLuckRateGroupDeleteDialog();
    expect(await mLuckRateGroupDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Luck Rate Group?');
    await mLuckRateGroupDeleteDialog.clickOnConfirmButton();

    expect(await mLuckRateGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
