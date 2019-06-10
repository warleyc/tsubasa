/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCardThumbnailAssetsComponentsPage,
  MCardThumbnailAssetsDeleteDialog,
  MCardThumbnailAssetsUpdatePage
} from './m-card-thumbnail-assets.page-object';

const expect = chai.expect;

describe('MCardThumbnailAssets e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardThumbnailAssetsUpdatePage: MCardThumbnailAssetsUpdatePage;
  let mCardThumbnailAssetsComponentsPage: MCardThumbnailAssetsComponentsPage;
  let mCardThumbnailAssetsDeleteDialog: MCardThumbnailAssetsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardThumbnailAssets', async () => {
    await navBarPage.goToEntity('m-card-thumbnail-assets');
    mCardThumbnailAssetsComponentsPage = new MCardThumbnailAssetsComponentsPage();
    await browser.wait(ec.visibilityOf(mCardThumbnailAssetsComponentsPage.title), 5000);
    expect(await mCardThumbnailAssetsComponentsPage.getTitle()).to.eq('M Card Thumbnail Assets');
  });

  it('should load create MCardThumbnailAssets page', async () => {
    await mCardThumbnailAssetsComponentsPage.clickOnCreateButton();
    mCardThumbnailAssetsUpdatePage = new MCardThumbnailAssetsUpdatePage();
    expect(await mCardThumbnailAssetsUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Thumbnail Assets');
    await mCardThumbnailAssetsUpdatePage.cancel();
  });

  it('should create and save MCardThumbnailAssets', async () => {
    const nbButtonsBeforeCreate = await mCardThumbnailAssetsComponentsPage.countDeleteButtons();

    await mCardThumbnailAssetsComponentsPage.clickOnCreateButton();
    await promise.all([
      mCardThumbnailAssetsUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mCardThumbnailAssetsUpdatePage.setThumbnailFrameNameInput('thumbnailFrameName'),
      mCardThumbnailAssetsUpdatePage.setThumbnailBackgoundAssetNameInput('thumbnailBackgoundAssetName'),
      mCardThumbnailAssetsUpdatePage.setThumbnailEffectAssetNameInput('thumbnailEffectAssetName')
    ]);
    expect(await mCardThumbnailAssetsUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mCardThumbnailAssetsUpdatePage.getThumbnailFrameNameInput()).to.eq(
      'thumbnailFrameName',
      'Expected ThumbnailFrameName value to be equals to thumbnailFrameName'
    );
    expect(await mCardThumbnailAssetsUpdatePage.getThumbnailBackgoundAssetNameInput()).to.eq(
      'thumbnailBackgoundAssetName',
      'Expected ThumbnailBackgoundAssetName value to be equals to thumbnailBackgoundAssetName'
    );
    expect(await mCardThumbnailAssetsUpdatePage.getThumbnailEffectAssetNameInput()).to.eq(
      'thumbnailEffectAssetName',
      'Expected ThumbnailEffectAssetName value to be equals to thumbnailEffectAssetName'
    );
    await mCardThumbnailAssetsUpdatePage.save();
    expect(await mCardThumbnailAssetsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCardThumbnailAssetsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCardThumbnailAssets', async () => {
    const nbButtonsBeforeDelete = await mCardThumbnailAssetsComponentsPage.countDeleteButtons();
    await mCardThumbnailAssetsComponentsPage.clickOnLastDeleteButton();

    mCardThumbnailAssetsDeleteDialog = new MCardThumbnailAssetsDeleteDialog();
    expect(await mCardThumbnailAssetsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Card Thumbnail Assets?');
    await mCardThumbnailAssetsDeleteDialog.clickOnConfirmButton();

    expect(await mCardThumbnailAssetsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
