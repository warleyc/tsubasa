/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MSellCardMedalComponentsPage, MSellCardMedalDeleteDialog, MSellCardMedalUpdatePage } from './m-sell-card-medal.page-object';

const expect = chai.expect;

describe('MSellCardMedal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSellCardMedalUpdatePage: MSellCardMedalUpdatePage;
  let mSellCardMedalComponentsPage: MSellCardMedalComponentsPage;
  let mSellCardMedalDeleteDialog: MSellCardMedalDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSellCardMedals', async () => {
    await navBarPage.goToEntity('m-sell-card-medal');
    mSellCardMedalComponentsPage = new MSellCardMedalComponentsPage();
    await browser.wait(ec.visibilityOf(mSellCardMedalComponentsPage.title), 5000);
    expect(await mSellCardMedalComponentsPage.getTitle()).to.eq('M Sell Card Medals');
  });

  it('should load create MSellCardMedal page', async () => {
    await mSellCardMedalComponentsPage.clickOnCreateButton();
    mSellCardMedalUpdatePage = new MSellCardMedalUpdatePage();
    expect(await mSellCardMedalUpdatePage.getPageTitle()).to.eq('Create or edit a M Sell Card Medal');
    await mSellCardMedalUpdatePage.cancel();
  });

  it('should create and save MSellCardMedals', async () => {
    const nbButtonsBeforeCreate = await mSellCardMedalComponentsPage.countDeleteButtons();

    await mSellCardMedalComponentsPage.clickOnCreateButton();
    await promise.all([mSellCardMedalUpdatePage.setMedalIdInput('5'), mSellCardMedalUpdatePage.setAmountInput('5')]);
    expect(await mSellCardMedalUpdatePage.getMedalIdInput()).to.eq('5', 'Expected medalId value to be equals to 5');
    expect(await mSellCardMedalUpdatePage.getAmountInput()).to.eq('5', 'Expected amount value to be equals to 5');
    await mSellCardMedalUpdatePage.save();
    expect(await mSellCardMedalUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSellCardMedalComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MSellCardMedal', async () => {
    const nbButtonsBeforeDelete = await mSellCardMedalComponentsPage.countDeleteButtons();
    await mSellCardMedalComponentsPage.clickOnLastDeleteButton();

    mSellCardMedalDeleteDialog = new MSellCardMedalDeleteDialog();
    expect(await mSellCardMedalDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Sell Card Medal?');
    await mSellCardMedalDeleteDialog.clickOnConfirmButton();

    expect(await mSellCardMedalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
