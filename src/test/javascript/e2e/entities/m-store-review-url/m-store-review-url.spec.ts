/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MStoreReviewUrlComponentsPage, MStoreReviewUrlDeleteDialog, MStoreReviewUrlUpdatePage } from './m-store-review-url.page-object';

const expect = chai.expect;

describe('MStoreReviewUrl e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mStoreReviewUrlUpdatePage: MStoreReviewUrlUpdatePage;
  let mStoreReviewUrlComponentsPage: MStoreReviewUrlComponentsPage;
  let mStoreReviewUrlDeleteDialog: MStoreReviewUrlDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MStoreReviewUrls', async () => {
    await navBarPage.goToEntity('m-store-review-url');
    mStoreReviewUrlComponentsPage = new MStoreReviewUrlComponentsPage();
    await browser.wait(ec.visibilityOf(mStoreReviewUrlComponentsPage.title), 5000);
    expect(await mStoreReviewUrlComponentsPage.getTitle()).to.eq('M Store Review Urls');
  });

  it('should load create MStoreReviewUrl page', async () => {
    await mStoreReviewUrlComponentsPage.clickOnCreateButton();
    mStoreReviewUrlUpdatePage = new MStoreReviewUrlUpdatePage();
    expect(await mStoreReviewUrlUpdatePage.getPageTitle()).to.eq('Create or edit a M Store Review Url');
    await mStoreReviewUrlUpdatePage.cancel();
  });

  it('should create and save MStoreReviewUrls', async () => {
    const nbButtonsBeforeCreate = await mStoreReviewUrlComponentsPage.countDeleteButtons();

    await mStoreReviewUrlComponentsPage.clickOnCreateButton();
    await promise.all([mStoreReviewUrlUpdatePage.setPlatformInput('5'), mStoreReviewUrlUpdatePage.setUrlInput('url')]);
    expect(await mStoreReviewUrlUpdatePage.getPlatformInput()).to.eq('5', 'Expected platform value to be equals to 5');
    expect(await mStoreReviewUrlUpdatePage.getUrlInput()).to.eq('url', 'Expected Url value to be equals to url');
    await mStoreReviewUrlUpdatePage.save();
    expect(await mStoreReviewUrlUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mStoreReviewUrlComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MStoreReviewUrl', async () => {
    const nbButtonsBeforeDelete = await mStoreReviewUrlComponentsPage.countDeleteButtons();
    await mStoreReviewUrlComponentsPage.clickOnLastDeleteButton();

    mStoreReviewUrlDeleteDialog = new MStoreReviewUrlDeleteDialog();
    expect(await mStoreReviewUrlDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Store Review Url?');
    await mStoreReviewUrlDeleteDialog.clickOnConfirmButton();

    expect(await mStoreReviewUrlComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
