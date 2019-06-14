/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MSellCardCoinComponentsPage, MSellCardCoinDeleteDialog, MSellCardCoinUpdatePage } from './m-sell-card-coin.page-object';

const expect = chai.expect;

describe('MSellCardCoin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSellCardCoinUpdatePage: MSellCardCoinUpdatePage;
  let mSellCardCoinComponentsPage: MSellCardCoinComponentsPage;
  let mSellCardCoinDeleteDialog: MSellCardCoinDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSellCardCoins', async () => {
    await navBarPage.goToEntity('m-sell-card-coin');
    mSellCardCoinComponentsPage = new MSellCardCoinComponentsPage();
    await browser.wait(ec.visibilityOf(mSellCardCoinComponentsPage.title), 5000);
    expect(await mSellCardCoinComponentsPage.getTitle()).to.eq('M Sell Card Coins');
  });

  it('should load create MSellCardCoin page', async () => {
    await mSellCardCoinComponentsPage.clickOnCreateButton();
    mSellCardCoinUpdatePage = new MSellCardCoinUpdatePage();
    expect(await mSellCardCoinUpdatePage.getPageTitle()).to.eq('Create or edit a M Sell Card Coin');
    await mSellCardCoinUpdatePage.cancel();
  });

  it('should create and save MSellCardCoins', async () => {
    const nbButtonsBeforeCreate = await mSellCardCoinComponentsPage.countDeleteButtons();

    await mSellCardCoinComponentsPage.clickOnCreateButton();
    await promise.all([
      mSellCardCoinUpdatePage.setGroupNumInput('5'),
      mSellCardCoinUpdatePage.setLevelInput('5'),
      mSellCardCoinUpdatePage.setCoinInput('5')
    ]);
    expect(await mSellCardCoinUpdatePage.getGroupNumInput()).to.eq('5', 'Expected groupNum value to be equals to 5');
    expect(await mSellCardCoinUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mSellCardCoinUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    await mSellCardCoinUpdatePage.save();
    expect(await mSellCardCoinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSellCardCoinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MSellCardCoin', async () => {
    const nbButtonsBeforeDelete = await mSellCardCoinComponentsPage.countDeleteButtons();
    await mSellCardCoinComponentsPage.clickOnLastDeleteButton();

    mSellCardCoinDeleteDialog = new MSellCardCoinDeleteDialog();
    expect(await mSellCardCoinDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Sell Card Coin?');
    await mSellCardCoinDeleteDialog.clickOnConfirmButton();

    expect(await mSellCardCoinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
