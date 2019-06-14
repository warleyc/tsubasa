/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionSwipeIconComponentsPage,
  MGachaRenditionSwipeIconDeleteDialog,
  MGachaRenditionSwipeIconUpdatePage
} from './m-gacha-rendition-swipe-icon.page-object';

const expect = chai.expect;

describe('MGachaRenditionSwipeIcon e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionSwipeIconUpdatePage: MGachaRenditionSwipeIconUpdatePage;
  let mGachaRenditionSwipeIconComponentsPage: MGachaRenditionSwipeIconComponentsPage;
  let mGachaRenditionSwipeIconDeleteDialog: MGachaRenditionSwipeIconDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionSwipeIcons', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-swipe-icon');
    mGachaRenditionSwipeIconComponentsPage = new MGachaRenditionSwipeIconComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionSwipeIconComponentsPage.title), 5000);
    expect(await mGachaRenditionSwipeIconComponentsPage.getTitle()).to.eq('M Gacha Rendition Swipe Icons');
  });

  it('should load create MGachaRenditionSwipeIcon page', async () => {
    await mGachaRenditionSwipeIconComponentsPage.clickOnCreateButton();
    mGachaRenditionSwipeIconUpdatePage = new MGachaRenditionSwipeIconUpdatePage();
    expect(await mGachaRenditionSwipeIconUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Swipe Icon');
    await mGachaRenditionSwipeIconUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionSwipeIcons', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionSwipeIconComponentsPage.countDeleteButtons();

    await mGachaRenditionSwipeIconComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionSwipeIconUpdatePage.setIsSsrInput('5'),
      mGachaRenditionSwipeIconUpdatePage.setIsROrLessInput('5'),
      mGachaRenditionSwipeIconUpdatePage.setWeightInput('5'),
      mGachaRenditionSwipeIconUpdatePage.setSwipeIconPrefabNameInput('swipeIconPrefabName')
    ]);
    expect(await mGachaRenditionSwipeIconUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionSwipeIconUpdatePage.getIsROrLessInput()).to.eq('5', 'Expected isROrLess value to be equals to 5');
    expect(await mGachaRenditionSwipeIconUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionSwipeIconUpdatePage.getSwipeIconPrefabNameInput()).to.eq(
      'swipeIconPrefabName',
      'Expected SwipeIconPrefabName value to be equals to swipeIconPrefabName'
    );
    await mGachaRenditionSwipeIconUpdatePage.save();
    expect(await mGachaRenditionSwipeIconUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionSwipeIconComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionSwipeIcon', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionSwipeIconComponentsPage.countDeleteButtons();
    await mGachaRenditionSwipeIconComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionSwipeIconDeleteDialog = new MGachaRenditionSwipeIconDeleteDialog();
    expect(await mGachaRenditionSwipeIconDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Swipe Icon?'
    );
    await mGachaRenditionSwipeIconDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionSwipeIconComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
