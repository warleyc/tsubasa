/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MLuckComponentsPage, MLuckDeleteDialog, MLuckUpdatePage } from './m-luck.page-object';

const expect = chai.expect;

describe('MLuck e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLuckUpdatePage: MLuckUpdatePage;
  let mLuckComponentsPage: MLuckComponentsPage;
  let mLuckDeleteDialog: MLuckDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLucks', async () => {
    await navBarPage.goToEntity('m-luck');
    mLuckComponentsPage = new MLuckComponentsPage();
    await browser.wait(ec.visibilityOf(mLuckComponentsPage.title), 5000);
    expect(await mLuckComponentsPage.getTitle()).to.eq('M Lucks');
  });

  it('should load create MLuck page', async () => {
    await mLuckComponentsPage.clickOnCreateButton();
    mLuckUpdatePage = new MLuckUpdatePage();
    expect(await mLuckUpdatePage.getPageTitle()).to.eq('Create or edit a M Luck');
    await mLuckUpdatePage.cancel();
  });

  it('should create and save MLucks', async () => {
    const nbButtonsBeforeCreate = await mLuckComponentsPage.countDeleteButtons();

    await mLuckComponentsPage.clickOnCreateButton();
    await promise.all([
      mLuckUpdatePage.setTargetAttributeInput('5'),
      mLuckUpdatePage.setTargetCharacterGroupIdInput('5'),
      mLuckUpdatePage.setTargetTeamGroupIdInput('5'),
      mLuckUpdatePage.setTargetNationalityGroupIdInput('5'),
      mLuckUpdatePage.setLuckRateGroupIdInput('5'),
      mLuckUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mLuckUpdatePage.getTargetAttributeInput()).to.eq('5', 'Expected targetAttribute value to be equals to 5');
    expect(await mLuckUpdatePage.getTargetCharacterGroupIdInput()).to.eq('5', 'Expected targetCharacterGroupId value to be equals to 5');
    expect(await mLuckUpdatePage.getTargetTeamGroupIdInput()).to.eq('5', 'Expected targetTeamGroupId value to be equals to 5');
    expect(await mLuckUpdatePage.getTargetNationalityGroupIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupId value to be equals to 5'
    );
    expect(await mLuckUpdatePage.getLuckRateGroupIdInput()).to.eq('5', 'Expected luckRateGroupId value to be equals to 5');
    expect(await mLuckUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    await mLuckUpdatePage.save();
    expect(await mLuckUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLuckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MLuck', async () => {
    const nbButtonsBeforeDelete = await mLuckComponentsPage.countDeleteButtons();
    await mLuckComponentsPage.clickOnLastDeleteButton();

    mLuckDeleteDialog = new MLuckDeleteDialog();
    expect(await mLuckDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Luck?');
    await mLuckDeleteDialog.clickOnConfirmButton();

    expect(await mLuckComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
