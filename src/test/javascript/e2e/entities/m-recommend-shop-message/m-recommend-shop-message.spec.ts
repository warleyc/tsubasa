/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRecommendShopMessageComponentsPage,
  MRecommendShopMessageDeleteDialog,
  MRecommendShopMessageUpdatePage
} from './m-recommend-shop-message.page-object';

const expect = chai.expect;

describe('MRecommendShopMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRecommendShopMessageUpdatePage: MRecommendShopMessageUpdatePage;
  let mRecommendShopMessageComponentsPage: MRecommendShopMessageComponentsPage;
  let mRecommendShopMessageDeleteDialog: MRecommendShopMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRecommendShopMessages', async () => {
    await navBarPage.goToEntity('m-recommend-shop-message');
    mRecommendShopMessageComponentsPage = new MRecommendShopMessageComponentsPage();
    await browser.wait(ec.visibilityOf(mRecommendShopMessageComponentsPage.title), 5000);
    expect(await mRecommendShopMessageComponentsPage.getTitle()).to.eq('M Recommend Shop Messages');
  });

  it('should load create MRecommendShopMessage page', async () => {
    await mRecommendShopMessageComponentsPage.clickOnCreateButton();
    mRecommendShopMessageUpdatePage = new MRecommendShopMessageUpdatePage();
    expect(await mRecommendShopMessageUpdatePage.getPageTitle()).to.eq('Create or edit a M Recommend Shop Message');
    await mRecommendShopMessageUpdatePage.cancel();
  });

  it('should create and save MRecommendShopMessages', async () => {
    const nbButtonsBeforeCreate = await mRecommendShopMessageComponentsPage.countDeleteButtons();

    await mRecommendShopMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      mRecommendShopMessageUpdatePage.setMessageInput('message'),
      mRecommendShopMessageUpdatePage.setHasSalesPeriodInput('5')
    ]);
    expect(await mRecommendShopMessageUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    expect(await mRecommendShopMessageUpdatePage.getHasSalesPeriodInput()).to.eq('5', 'Expected hasSalesPeriod value to be equals to 5');
    await mRecommendShopMessageUpdatePage.save();
    expect(await mRecommendShopMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRecommendShopMessageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRecommendShopMessage', async () => {
    const nbButtonsBeforeDelete = await mRecommendShopMessageComponentsPage.countDeleteButtons();
    await mRecommendShopMessageComponentsPage.clickOnLastDeleteButton();

    mRecommendShopMessageDeleteDialog = new MRecommendShopMessageDeleteDialog();
    expect(await mRecommendShopMessageDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Recommend Shop Message?'
    );
    await mRecommendShopMessageDeleteDialog.clickOnConfirmButton();

    expect(await mRecommendShopMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
