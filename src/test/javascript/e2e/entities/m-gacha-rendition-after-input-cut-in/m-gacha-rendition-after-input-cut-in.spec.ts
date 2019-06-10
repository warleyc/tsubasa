/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionAfterInputCutInComponentsPage,
  MGachaRenditionAfterInputCutInDeleteDialog,
  MGachaRenditionAfterInputCutInUpdatePage
} from './m-gacha-rendition-after-input-cut-in.page-object';

const expect = chai.expect;

describe('MGachaRenditionAfterInputCutIn e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionAfterInputCutInUpdatePage: MGachaRenditionAfterInputCutInUpdatePage;
  let mGachaRenditionAfterInputCutInComponentsPage: MGachaRenditionAfterInputCutInComponentsPage;
  let mGachaRenditionAfterInputCutInDeleteDialog: MGachaRenditionAfterInputCutInDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionAfterInputCutIns', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-after-input-cut-in');
    mGachaRenditionAfterInputCutInComponentsPage = new MGachaRenditionAfterInputCutInComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionAfterInputCutInComponentsPage.title), 5000);
    expect(await mGachaRenditionAfterInputCutInComponentsPage.getTitle()).to.eq('M Gacha Rendition After Input Cut Ins');
  });

  it('should load create MGachaRenditionAfterInputCutIn page', async () => {
    await mGachaRenditionAfterInputCutInComponentsPage.clickOnCreateButton();
    mGachaRenditionAfterInputCutInUpdatePage = new MGachaRenditionAfterInputCutInUpdatePage();
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition After Input Cut In');
    await mGachaRenditionAfterInputCutInUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionAfterInputCutIns', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionAfterInputCutInComponentsPage.countDeleteButtons();

    await mGachaRenditionAfterInputCutInComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionAfterInputCutInUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionAfterInputCutInUpdatePage.setIsSsrInput('5'),
      mGachaRenditionAfterInputCutInUpdatePage.setWeightInput('5'),
      mGachaRenditionAfterInputCutInUpdatePage.setCutInBgPrefabNameInput('cutInBgPrefabName'),
      mGachaRenditionAfterInputCutInUpdatePage.setSeStartCutInInput('seStartCutIn')
    ]);
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getCutInBgPrefabNameInput()).to.eq(
      'cutInBgPrefabName',
      'Expected CutInBgPrefabName value to be equals to cutInBgPrefabName'
    );
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getSeStartCutInInput()).to.eq(
      'seStartCutIn',
      'Expected SeStartCutIn value to be equals to seStartCutIn'
    );
    await mGachaRenditionAfterInputCutInUpdatePage.save();
    expect(await mGachaRenditionAfterInputCutInUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionAfterInputCutInComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionAfterInputCutIn', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionAfterInputCutInComponentsPage.countDeleteButtons();
    await mGachaRenditionAfterInputCutInComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionAfterInputCutInDeleteDialog = new MGachaRenditionAfterInputCutInDeleteDialog();
    expect(await mGachaRenditionAfterInputCutInDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition After Input Cut In?'
    );
    await mGachaRenditionAfterInputCutInDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionAfterInputCutInComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
