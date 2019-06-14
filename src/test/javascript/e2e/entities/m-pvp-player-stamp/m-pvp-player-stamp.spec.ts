/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPvpPlayerStampComponentsPage, MPvpPlayerStampDeleteDialog, MPvpPlayerStampUpdatePage } from './m-pvp-player-stamp.page-object';

const expect = chai.expect;

describe('MPvpPlayerStamp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpPlayerStampUpdatePage: MPvpPlayerStampUpdatePage;
  let mPvpPlayerStampComponentsPage: MPvpPlayerStampComponentsPage;
  let mPvpPlayerStampDeleteDialog: MPvpPlayerStampDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpPlayerStamps', async () => {
    await navBarPage.goToEntity('m-pvp-player-stamp');
    mPvpPlayerStampComponentsPage = new MPvpPlayerStampComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpPlayerStampComponentsPage.title), 5000);
    expect(await mPvpPlayerStampComponentsPage.getTitle()).to.eq('M Pvp Player Stamps');
  });

  it('should load create MPvpPlayerStamp page', async () => {
    await mPvpPlayerStampComponentsPage.clickOnCreateButton();
    mPvpPlayerStampUpdatePage = new MPvpPlayerStampUpdatePage();
    expect(await mPvpPlayerStampUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Player Stamp');
    await mPvpPlayerStampUpdatePage.cancel();
  });

  it('should create and save MPvpPlayerStamps', async () => {
    const nbButtonsBeforeCreate = await mPvpPlayerStampComponentsPage.countDeleteButtons();

    await mPvpPlayerStampComponentsPage.clickOnCreateButton();
    await promise.all([mPvpPlayerStampUpdatePage.setOrderNumInput('5'), mPvpPlayerStampUpdatePage.setMasterIdInput('5')]);
    expect(await mPvpPlayerStampUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mPvpPlayerStampUpdatePage.getMasterIdInput()).to.eq('5', 'Expected masterId value to be equals to 5');
    await mPvpPlayerStampUpdatePage.save();
    expect(await mPvpPlayerStampUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpPlayerStampComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPvpPlayerStamp', async () => {
    const nbButtonsBeforeDelete = await mPvpPlayerStampComponentsPage.countDeleteButtons();
    await mPvpPlayerStampComponentsPage.clickOnLastDeleteButton();

    mPvpPlayerStampDeleteDialog = new MPvpPlayerStampDeleteDialog();
    expect(await mPvpPlayerStampDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Pvp Player Stamp?');
    await mPvpPlayerStampDeleteDialog.clickOnConfirmButton();

    expect(await mPvpPlayerStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
