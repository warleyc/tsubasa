/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionFirstGimmickComponentsPage,
  MGachaRenditionFirstGimmickDeleteDialog,
  MGachaRenditionFirstGimmickUpdatePage
} from './m-gacha-rendition-first-gimmick.page-object';

const expect = chai.expect;

describe('MGachaRenditionFirstGimmick e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionFirstGimmickUpdatePage: MGachaRenditionFirstGimmickUpdatePage;
  let mGachaRenditionFirstGimmickComponentsPage: MGachaRenditionFirstGimmickComponentsPage;
  let mGachaRenditionFirstGimmickDeleteDialog: MGachaRenditionFirstGimmickDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionFirstGimmicks', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-first-gimmick');
    mGachaRenditionFirstGimmickComponentsPage = new MGachaRenditionFirstGimmickComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionFirstGimmickComponentsPage.title), 5000);
    expect(await mGachaRenditionFirstGimmickComponentsPage.getTitle()).to.eq('M Gacha Rendition First Gimmicks');
  });

  it('should load create MGachaRenditionFirstGimmick page', async () => {
    await mGachaRenditionFirstGimmickComponentsPage.clickOnCreateButton();
    mGachaRenditionFirstGimmickUpdatePage = new MGachaRenditionFirstGimmickUpdatePage();
    expect(await mGachaRenditionFirstGimmickUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition First Gimmick');
    await mGachaRenditionFirstGimmickUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionFirstGimmicks', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionFirstGimmickComponentsPage.countDeleteButtons();

    await mGachaRenditionFirstGimmickComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionFirstGimmickUpdatePage.setIsSsrInput('5'),
      mGachaRenditionFirstGimmickUpdatePage.setWeightInput('5'),
      mGachaRenditionFirstGimmickUpdatePage.setBirdNumInput('5'),
      mGachaRenditionFirstGimmickUpdatePage.setIsTickerTapeInput('5')
    ]);
    expect(await mGachaRenditionFirstGimmickUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionFirstGimmickUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionFirstGimmickUpdatePage.getBirdNumInput()).to.eq('5', 'Expected birdNum value to be equals to 5');
    expect(await mGachaRenditionFirstGimmickUpdatePage.getIsTickerTapeInput()).to.eq('5', 'Expected isTickerTape value to be equals to 5');
    await mGachaRenditionFirstGimmickUpdatePage.save();
    expect(await mGachaRenditionFirstGimmickUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionFirstGimmickComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionFirstGimmick', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionFirstGimmickComponentsPage.countDeleteButtons();
    await mGachaRenditionFirstGimmickComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionFirstGimmickDeleteDialog = new MGachaRenditionFirstGimmickDeleteDialog();
    expect(await mGachaRenditionFirstGimmickDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition First Gimmick?'
    );
    await mGachaRenditionFirstGimmickDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionFirstGimmickComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
