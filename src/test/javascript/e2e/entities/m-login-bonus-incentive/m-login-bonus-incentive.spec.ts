/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLoginBonusIncentiveComponentsPage,
  MLoginBonusIncentiveDeleteDialog,
  MLoginBonusIncentiveUpdatePage
} from './m-login-bonus-incentive.page-object';

const expect = chai.expect;

describe('MLoginBonusIncentive e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLoginBonusIncentiveUpdatePage: MLoginBonusIncentiveUpdatePage;
  let mLoginBonusIncentiveComponentsPage: MLoginBonusIncentiveComponentsPage;
  let mLoginBonusIncentiveDeleteDialog: MLoginBonusIncentiveDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLoginBonusIncentives', async () => {
    await navBarPage.goToEntity('m-login-bonus-incentive');
    mLoginBonusIncentiveComponentsPage = new MLoginBonusIncentiveComponentsPage();
    await browser.wait(ec.visibilityOf(mLoginBonusIncentiveComponentsPage.title), 5000);
    expect(await mLoginBonusIncentiveComponentsPage.getTitle()).to.eq('M Login Bonus Incentives');
  });

  it('should load create MLoginBonusIncentive page', async () => {
    await mLoginBonusIncentiveComponentsPage.clickOnCreateButton();
    mLoginBonusIncentiveUpdatePage = new MLoginBonusIncentiveUpdatePage();
    expect(await mLoginBonusIncentiveUpdatePage.getPageTitle()).to.eq('Create or edit a M Login Bonus Incentive');
    await mLoginBonusIncentiveUpdatePage.cancel();
  });

  it('should create and save MLoginBonusIncentives', async () => {
    const nbButtonsBeforeCreate = await mLoginBonusIncentiveComponentsPage.countDeleteButtons();

    await mLoginBonusIncentiveComponentsPage.clickOnCreateButton();
    await promise.all([
      mLoginBonusIncentiveUpdatePage.setRoundIdInput('5'),
      mLoginBonusIncentiveUpdatePage.setDayInput('5'),
      mLoginBonusIncentiveUpdatePage.setContentTypeInput('5'),
      mLoginBonusIncentiveUpdatePage.setContentIdInput('5'),
      mLoginBonusIncentiveUpdatePage.setContentAmountInput('5'),
      mLoginBonusIncentiveUpdatePage.setPresentDetailInput('presentDetail'),
      mLoginBonusIncentiveUpdatePage.setIsDecoratedInput('5')
    ]);
    expect(await mLoginBonusIncentiveUpdatePage.getRoundIdInput()).to.eq('5', 'Expected roundId value to be equals to 5');
    expect(await mLoginBonusIncentiveUpdatePage.getDayInput()).to.eq('5', 'Expected day value to be equals to 5');
    expect(await mLoginBonusIncentiveUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mLoginBonusIncentiveUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mLoginBonusIncentiveUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    expect(await mLoginBonusIncentiveUpdatePage.getPresentDetailInput()).to.eq(
      'presentDetail',
      'Expected PresentDetail value to be equals to presentDetail'
    );
    expect(await mLoginBonusIncentiveUpdatePage.getIsDecoratedInput()).to.eq('5', 'Expected isDecorated value to be equals to 5');
    await mLoginBonusIncentiveUpdatePage.save();
    expect(await mLoginBonusIncentiveUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLoginBonusIncentiveComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLoginBonusIncentive', async () => {
    const nbButtonsBeforeDelete = await mLoginBonusIncentiveComponentsPage.countDeleteButtons();
    await mLoginBonusIncentiveComponentsPage.clickOnLastDeleteButton();

    mLoginBonusIncentiveDeleteDialog = new MLoginBonusIncentiveDeleteDialog();
    expect(await mLoginBonusIncentiveDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Login Bonus Incentive?');
    await mLoginBonusIncentiveDeleteDialog.clickOnConfirmButton();

    expect(await mLoginBonusIncentiveComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
