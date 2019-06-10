/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MBadgeComponentsPage, MBadgeDeleteDialog, MBadgeUpdatePage } from './m-badge.page-object';

const expect = chai.expect;

describe('MBadge e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mBadgeUpdatePage: MBadgeUpdatePage;
  let mBadgeComponentsPage: MBadgeComponentsPage;
  let mBadgeDeleteDialog: MBadgeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MBadges', async () => {
    await navBarPage.goToEntity('m-badge');
    mBadgeComponentsPage = new MBadgeComponentsPage();
    await browser.wait(ec.visibilityOf(mBadgeComponentsPage.title), 5000);
    expect(await mBadgeComponentsPage.getTitle()).to.eq('M Badges');
  });

  it('should load create MBadge page', async () => {
    await mBadgeComponentsPage.clickOnCreateButton();
    mBadgeUpdatePage = new MBadgeUpdatePage();
    expect(await mBadgeUpdatePage.getPageTitle()).to.eq('Create or edit a M Badge');
    await mBadgeUpdatePage.cancel();
  });

  it('should create and save MBadges', async () => {
    const nbButtonsBeforeCreate = await mBadgeComponentsPage.countDeleteButtons();

    await mBadgeComponentsPage.clickOnCreateButton();
    await promise.all([
      mBadgeUpdatePage.setNameInput('name'),
      mBadgeUpdatePage.setTypeInput('5'),
      mBadgeUpdatePage.setDescriptionInput('description'),
      mBadgeUpdatePage.setAssetNameInput('assetName')
    ]);
    expect(await mBadgeUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mBadgeUpdatePage.getTypeInput()).to.eq('5', 'Expected type value to be equals to 5');
    expect(await mBadgeUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await mBadgeUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    await mBadgeUpdatePage.save();
    expect(await mBadgeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mBadgeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MBadge', async () => {
    const nbButtonsBeforeDelete = await mBadgeComponentsPage.countDeleteButtons();
    await mBadgeComponentsPage.clickOnLastDeleteButton();

    mBadgeDeleteDialog = new MBadgeDeleteDialog();
    expect(await mBadgeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Badge?');
    await mBadgeDeleteDialog.clickOnConfirmButton();

    expect(await mBadgeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
