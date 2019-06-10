/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCoopRoomStampComponentsPage, MCoopRoomStampDeleteDialog, MCoopRoomStampUpdatePage } from './m-coop-room-stamp.page-object';

const expect = chai.expect;

describe('MCoopRoomStamp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCoopRoomStampUpdatePage: MCoopRoomStampUpdatePage;
  let mCoopRoomStampComponentsPage: MCoopRoomStampComponentsPage;
  let mCoopRoomStampDeleteDialog: MCoopRoomStampDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCoopRoomStamps', async () => {
    await navBarPage.goToEntity('m-coop-room-stamp');
    mCoopRoomStampComponentsPage = new MCoopRoomStampComponentsPage();
    await browser.wait(ec.visibilityOf(mCoopRoomStampComponentsPage.title), 5000);
    expect(await mCoopRoomStampComponentsPage.getTitle()).to.eq('M Coop Room Stamps');
  });

  it('should load create MCoopRoomStamp page', async () => {
    await mCoopRoomStampComponentsPage.clickOnCreateButton();
    mCoopRoomStampUpdatePage = new MCoopRoomStampUpdatePage();
    expect(await mCoopRoomStampUpdatePage.getPageTitle()).to.eq('Create or edit a M Coop Room Stamp');
    await mCoopRoomStampUpdatePage.cancel();
  });

  it('should create and save MCoopRoomStamps', async () => {
    const nbButtonsBeforeCreate = await mCoopRoomStampComponentsPage.countDeleteButtons();

    await mCoopRoomStampComponentsPage.clickOnCreateButton();
    await promise.all([
      mCoopRoomStampUpdatePage.setRoleInput('5'),
      mCoopRoomStampUpdatePage.setOrderNumInput('5'),
      mCoopRoomStampUpdatePage.setMasterIdInput('5')
    ]);
    expect(await mCoopRoomStampUpdatePage.getRoleInput()).to.eq('5', 'Expected role value to be equals to 5');
    expect(await mCoopRoomStampUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mCoopRoomStampUpdatePage.getMasterIdInput()).to.eq('5', 'Expected masterId value to be equals to 5');
    await mCoopRoomStampUpdatePage.save();
    expect(await mCoopRoomStampUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCoopRoomStampComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCoopRoomStamp', async () => {
    const nbButtonsBeforeDelete = await mCoopRoomStampComponentsPage.countDeleteButtons();
    await mCoopRoomStampComponentsPage.clickOnLastDeleteButton();

    mCoopRoomStampDeleteDialog = new MCoopRoomStampDeleteDialog();
    expect(await mCoopRoomStampDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Coop Room Stamp?');
    await mCoopRoomStampDeleteDialog.clickOnConfirmButton();

    expect(await mCoopRoomStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
