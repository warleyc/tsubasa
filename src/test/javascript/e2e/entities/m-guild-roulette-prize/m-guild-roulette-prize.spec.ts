/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuildRoulettePrizeComponentsPage,
  MGuildRoulettePrizeDeleteDialog,
  MGuildRoulettePrizeUpdatePage
} from './m-guild-roulette-prize.page-object';

const expect = chai.expect;

describe('MGuildRoulettePrize e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildRoulettePrizeUpdatePage: MGuildRoulettePrizeUpdatePage;
  let mGuildRoulettePrizeComponentsPage: MGuildRoulettePrizeComponentsPage;
  let mGuildRoulettePrizeDeleteDialog: MGuildRoulettePrizeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildRoulettePrizes', async () => {
    await navBarPage.goToEntity('m-guild-roulette-prize');
    mGuildRoulettePrizeComponentsPage = new MGuildRoulettePrizeComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildRoulettePrizeComponentsPage.title), 5000);
    expect(await mGuildRoulettePrizeComponentsPage.getTitle()).to.eq('M Guild Roulette Prizes');
  });

  it('should load create MGuildRoulettePrize page', async () => {
    await mGuildRoulettePrizeComponentsPage.clickOnCreateButton();
    mGuildRoulettePrizeUpdatePage = new MGuildRoulettePrizeUpdatePage();
    expect(await mGuildRoulettePrizeUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Roulette Prize');
    await mGuildRoulettePrizeUpdatePage.cancel();
  });

  it('should create and save MGuildRoulettePrizes', async () => {
    const nbButtonsBeforeCreate = await mGuildRoulettePrizeComponentsPage.countDeleteButtons();

    await mGuildRoulettePrizeComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuildRoulettePrizeUpdatePage.setRankInput('5'),
      mGuildRoulettePrizeUpdatePage.setContentTypeInput('5'),
      mGuildRoulettePrizeUpdatePage.setContentIdInput('5'),
      mGuildRoulettePrizeUpdatePage.setContentAmountInput('5')
    ]);
    expect(await mGuildRoulettePrizeUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mGuildRoulettePrizeUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mGuildRoulettePrizeUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mGuildRoulettePrizeUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    await mGuildRoulettePrizeUpdatePage.save();
    expect(await mGuildRoulettePrizeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildRoulettePrizeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuildRoulettePrize', async () => {
    const nbButtonsBeforeDelete = await mGuildRoulettePrizeComponentsPage.countDeleteButtons();
    await mGuildRoulettePrizeComponentsPage.clickOnLastDeleteButton();

    mGuildRoulettePrizeDeleteDialog = new MGuildRoulettePrizeDeleteDialog();
    expect(await mGuildRoulettePrizeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Roulette Prize?');
    await mGuildRoulettePrizeDeleteDialog.clickOnConfirmButton();

    expect(await mGuildRoulettePrizeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
