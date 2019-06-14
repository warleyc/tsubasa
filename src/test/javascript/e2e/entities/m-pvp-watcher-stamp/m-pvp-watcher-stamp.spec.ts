/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPvpWatcherStampComponentsPage,
  MPvpWatcherStampDeleteDialog,
  MPvpWatcherStampUpdatePage
} from './m-pvp-watcher-stamp.page-object';

const expect = chai.expect;

describe('MPvpWatcherStamp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpWatcherStampUpdatePage: MPvpWatcherStampUpdatePage;
  let mPvpWatcherStampComponentsPage: MPvpWatcherStampComponentsPage;
  let mPvpWatcherStampDeleteDialog: MPvpWatcherStampDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpWatcherStamps', async () => {
    await navBarPage.goToEntity('m-pvp-watcher-stamp');
    mPvpWatcherStampComponentsPage = new MPvpWatcherStampComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpWatcherStampComponentsPage.title), 5000);
    expect(await mPvpWatcherStampComponentsPage.getTitle()).to.eq('M Pvp Watcher Stamps');
  });

  it('should load create MPvpWatcherStamp page', async () => {
    await mPvpWatcherStampComponentsPage.clickOnCreateButton();
    mPvpWatcherStampUpdatePage = new MPvpWatcherStampUpdatePage();
    expect(await mPvpWatcherStampUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Watcher Stamp');
    await mPvpWatcherStampUpdatePage.cancel();
  });

  it('should create and save MPvpWatcherStamps', async () => {
    const nbButtonsBeforeCreate = await mPvpWatcherStampComponentsPage.countDeleteButtons();

    await mPvpWatcherStampComponentsPage.clickOnCreateButton();
    await promise.all([mPvpWatcherStampUpdatePage.setOrderNumInput('5'), mPvpWatcherStampUpdatePage.setMasterIdInput('5')]);
    expect(await mPvpWatcherStampUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mPvpWatcherStampUpdatePage.getMasterIdInput()).to.eq('5', 'Expected masterId value to be equals to 5');
    await mPvpWatcherStampUpdatePage.save();
    expect(await mPvpWatcherStampUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpWatcherStampComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPvpWatcherStamp', async () => {
    const nbButtonsBeforeDelete = await mPvpWatcherStampComponentsPage.countDeleteButtons();
    await mPvpWatcherStampComponentsPage.clickOnLastDeleteButton();

    mPvpWatcherStampDeleteDialog = new MPvpWatcherStampDeleteDialog();
    expect(await mPvpWatcherStampDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Pvp Watcher Stamp?');
    await mPvpWatcherStampDeleteDialog.clickOnConfirmButton();

    expect(await mPvpWatcherStampComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
