/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionAfterInputCutInTextColorComponentsPage,
  MGachaRenditionAfterInputCutInTextColorDeleteDialog,
  MGachaRenditionAfterInputCutInTextColorUpdatePage
} from './m-gacha-rendition-after-input-cut-in-text-color.page-object';

const expect = chai.expect;

describe('MGachaRenditionAfterInputCutInTextColor e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionAfterInputCutInTextColorUpdatePage: MGachaRenditionAfterInputCutInTextColorUpdatePage;
  let mGachaRenditionAfterInputCutInTextColorComponentsPage: MGachaRenditionAfterInputCutInTextColorComponentsPage;
  let mGachaRenditionAfterInputCutInTextColorDeleteDialog: MGachaRenditionAfterInputCutInTextColorDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionAfterInputCutInTextColors', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-after-input-cut-in-text-color');
    mGachaRenditionAfterInputCutInTextColorComponentsPage = new MGachaRenditionAfterInputCutInTextColorComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionAfterInputCutInTextColorComponentsPage.title), 5000);
    expect(await mGachaRenditionAfterInputCutInTextColorComponentsPage.getTitle()).to.eq(
      'M Gacha Rendition After Input Cut In Text Colors'
    );
  });

  it('should load create MGachaRenditionAfterInputCutInTextColor page', async () => {
    await mGachaRenditionAfterInputCutInTextColorComponentsPage.clickOnCreateButton();
    mGachaRenditionAfterInputCutInTextColorUpdatePage = new MGachaRenditionAfterInputCutInTextColorUpdatePage();
    expect(await mGachaRenditionAfterInputCutInTextColorUpdatePage.getPageTitle()).to.eq(
      'Create or edit a M Gacha Rendition After Input Cut In Text Color'
    );
    await mGachaRenditionAfterInputCutInTextColorUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionAfterInputCutInTextColors', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionAfterInputCutInTextColorComponentsPage.countDeleteButtons();

    await mGachaRenditionAfterInputCutInTextColorComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionAfterInputCutInTextColorUpdatePage.setIsSsrInput('5'),
      mGachaRenditionAfterInputCutInTextColorUpdatePage.setWeightInput('5'),
      mGachaRenditionAfterInputCutInTextColorUpdatePage.setColorInput('color')
    ]);
    expect(await mGachaRenditionAfterInputCutInTextColorUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionAfterInputCutInTextColorUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionAfterInputCutInTextColorUpdatePage.getColorInput()).to.eq(
      'color',
      'Expected Color value to be equals to color'
    );
    await mGachaRenditionAfterInputCutInTextColorUpdatePage.save();
    expect(await mGachaRenditionAfterInputCutInTextColorUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be
      .false;

    expect(await mGachaRenditionAfterInputCutInTextColorComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionAfterInputCutInTextColor', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionAfterInputCutInTextColorComponentsPage.countDeleteButtons();
    await mGachaRenditionAfterInputCutInTextColorComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionAfterInputCutInTextColorDeleteDialog = new MGachaRenditionAfterInputCutInTextColorDeleteDialog();
    expect(await mGachaRenditionAfterInputCutInTextColorDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition After Input Cut In Text Color?'
    );
    await mGachaRenditionAfterInputCutInTextColorDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionAfterInputCutInTextColorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
