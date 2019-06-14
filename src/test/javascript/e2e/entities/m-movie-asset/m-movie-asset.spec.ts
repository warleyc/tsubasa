/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMovieAssetComponentsPage, MMovieAssetDeleteDialog, MMovieAssetUpdatePage } from './m-movie-asset.page-object';

const expect = chai.expect;

describe('MMovieAsset e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMovieAssetUpdatePage: MMovieAssetUpdatePage;
  let mMovieAssetComponentsPage: MMovieAssetComponentsPage;
  let mMovieAssetDeleteDialog: MMovieAssetDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMovieAssets', async () => {
    await navBarPage.goToEntity('m-movie-asset');
    mMovieAssetComponentsPage = new MMovieAssetComponentsPage();
    await browser.wait(ec.visibilityOf(mMovieAssetComponentsPage.title), 5000);
    expect(await mMovieAssetComponentsPage.getTitle()).to.eq('M Movie Assets');
  });

  it('should load create MMovieAsset page', async () => {
    await mMovieAssetComponentsPage.clickOnCreateButton();
    mMovieAssetUpdatePage = new MMovieAssetUpdatePage();
    expect(await mMovieAssetUpdatePage.getPageTitle()).to.eq('Create or edit a M Movie Asset');
    await mMovieAssetUpdatePage.cancel();
  });

  it('should create and save MMovieAssets', async () => {
    const nbButtonsBeforeCreate = await mMovieAssetComponentsPage.countDeleteButtons();

    await mMovieAssetComponentsPage.clickOnCreateButton();
    await promise.all([
      mMovieAssetUpdatePage.setLangInput('5'),
      mMovieAssetUpdatePage.setNameInput('name'),
      mMovieAssetUpdatePage.setSizeInput('5'),
      mMovieAssetUpdatePage.setVersionInput('5'),
      mMovieAssetUpdatePage.setTypeInput('5')
    ]);
    expect(await mMovieAssetUpdatePage.getLangInput()).to.eq('5', 'Expected lang value to be equals to 5');
    expect(await mMovieAssetUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mMovieAssetUpdatePage.getSizeInput()).to.eq('5', 'Expected size value to be equals to 5');
    expect(await mMovieAssetUpdatePage.getVersionInput()).to.eq('5', 'Expected version value to be equals to 5');
    expect(await mMovieAssetUpdatePage.getTypeInput()).to.eq('5', 'Expected type value to be equals to 5');
    await mMovieAssetUpdatePage.save();
    expect(await mMovieAssetUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMovieAssetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MMovieAsset', async () => {
    const nbButtonsBeforeDelete = await mMovieAssetComponentsPage.countDeleteButtons();
    await mMovieAssetComponentsPage.clickOnLastDeleteButton();

    mMovieAssetDeleteDialog = new MMovieAssetDeleteDialog();
    expect(await mMovieAssetDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Movie Asset?');
    await mMovieAssetDeleteDialog.clickOnConfirmButton();

    expect(await mMovieAssetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
