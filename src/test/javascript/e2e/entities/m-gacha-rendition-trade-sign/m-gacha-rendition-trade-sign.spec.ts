/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionTradeSignComponentsPage,
  MGachaRenditionTradeSignDeleteDialog,
  MGachaRenditionTradeSignUpdatePage
} from './m-gacha-rendition-trade-sign.page-object';

const expect = chai.expect;

describe('MGachaRenditionTradeSign e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionTradeSignUpdatePage: MGachaRenditionTradeSignUpdatePage;
  let mGachaRenditionTradeSignComponentsPage: MGachaRenditionTradeSignComponentsPage;
  let mGachaRenditionTradeSignDeleteDialog: MGachaRenditionTradeSignDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionTradeSigns', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-trade-sign');
    mGachaRenditionTradeSignComponentsPage = new MGachaRenditionTradeSignComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionTradeSignComponentsPage.title), 5000);
    expect(await mGachaRenditionTradeSignComponentsPage.getTitle()).to.eq('M Gacha Rendition Trade Signs');
  });

  it('should load create MGachaRenditionTradeSign page', async () => {
    await mGachaRenditionTradeSignComponentsPage.clickOnCreateButton();
    mGachaRenditionTradeSignUpdatePage = new MGachaRenditionTradeSignUpdatePage();
    expect(await mGachaRenditionTradeSignUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Trade Sign');
    await mGachaRenditionTradeSignUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionTradeSigns', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionTradeSignComponentsPage.countDeleteButtons();

    await mGachaRenditionTradeSignComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionTradeSignUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionTradeSignUpdatePage.setIsSsrInput('5'),
      mGachaRenditionTradeSignUpdatePage.setWeightInput('5'),
      mGachaRenditionTradeSignUpdatePage.setSignTextureNameInput('signTextureName')
    ]);
    expect(await mGachaRenditionTradeSignUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionTradeSignUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionTradeSignUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionTradeSignUpdatePage.getSignTextureNameInput()).to.eq(
      'signTextureName',
      'Expected SignTextureName value to be equals to signTextureName'
    );
    await mGachaRenditionTradeSignUpdatePage.save();
    expect(await mGachaRenditionTradeSignUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionTradeSignComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionTradeSign', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionTradeSignComponentsPage.countDeleteButtons();
    await mGachaRenditionTradeSignComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionTradeSignDeleteDialog = new MGachaRenditionTradeSignDeleteDialog();
    expect(await mGachaRenditionTradeSignDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Trade Sign?'
    );
    await mGachaRenditionTradeSignDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionTradeSignComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
