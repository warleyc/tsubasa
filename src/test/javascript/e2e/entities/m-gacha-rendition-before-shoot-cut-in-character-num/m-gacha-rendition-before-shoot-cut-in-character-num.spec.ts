/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionBeforeShootCutInCharacterNumComponentsPage,
  MGachaRenditionBeforeShootCutInCharacterNumDeleteDialog,
  MGachaRenditionBeforeShootCutInCharacterNumUpdatePage
} from './m-gacha-rendition-before-shoot-cut-in-character-num.page-object';

const expect = chai.expect;

describe('MGachaRenditionBeforeShootCutInCharacterNum e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionBeforeShootCutInCharacterNumUpdatePage: MGachaRenditionBeforeShootCutInCharacterNumUpdatePage;
  let mGachaRenditionBeforeShootCutInCharacterNumComponentsPage: MGachaRenditionBeforeShootCutInCharacterNumComponentsPage;
  let mGachaRenditionBeforeShootCutInCharacterNumDeleteDialog: MGachaRenditionBeforeShootCutInCharacterNumDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionBeforeShootCutInCharacterNums', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-before-shoot-cut-in-character-num');
    mGachaRenditionBeforeShootCutInCharacterNumComponentsPage = new MGachaRenditionBeforeShootCutInCharacterNumComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.title), 5000);
    expect(await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.getTitle()).to.eq(
      'M Gacha Rendition Before Shoot Cut In Character Nums'
    );
  });

  it('should load create MGachaRenditionBeforeShootCutInCharacterNum page', async () => {
    await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.clickOnCreateButton();
    mGachaRenditionBeforeShootCutInCharacterNumUpdatePage = new MGachaRenditionBeforeShootCutInCharacterNumUpdatePage();
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getPageTitle()).to.eq(
      'Create or edit a M Gacha Rendition Before Shoot Cut In Character Num'
    );
    await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionBeforeShootCutInCharacterNums', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.countDeleteButtons();

    await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.setIsManySsrInput('5'),
      mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.setIsSsrInput('5'),
      mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.setPatternInput('5'),
      mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.setWeightInput('5'),
      mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.setNumInput('5')
    ]);
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getIsManySsrInput()).to.eq(
      '5',
      'Expected isManySsr value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getIsSsrInput()).to.eq(
      '5',
      'Expected isSsr value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getPatternInput()).to.eq(
      '5',
      'Expected pattern value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getWeightInput()).to.eq(
      '5',
      'Expected weight value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getNumInput()).to.eq('5', 'Expected num value to be equals to 5');
    await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.save();
    expect(await mGachaRenditionBeforeShootCutInCharacterNumUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be
      .false;

    expect(await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionBeforeShootCutInCharacterNum', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.countDeleteButtons();
    await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionBeforeShootCutInCharacterNumDeleteDialog = new MGachaRenditionBeforeShootCutInCharacterNumDeleteDialog();
    expect(await mGachaRenditionBeforeShootCutInCharacterNumDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Before Shoot Cut In Character Num?'
    );
    await mGachaRenditionBeforeShootCutInCharacterNumDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionBeforeShootCutInCharacterNumComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
