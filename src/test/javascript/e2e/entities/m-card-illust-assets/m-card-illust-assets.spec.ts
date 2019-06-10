/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCardIllustAssetsComponentsPage,
  MCardIllustAssetsDeleteDialog,
  MCardIllustAssetsUpdatePage
} from './m-card-illust-assets.page-object';

const expect = chai.expect;

describe('MCardIllustAssets e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardIllustAssetsUpdatePage: MCardIllustAssetsUpdatePage;
  let mCardIllustAssetsComponentsPage: MCardIllustAssetsComponentsPage;
  let mCardIllustAssetsDeleteDialog: MCardIllustAssetsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardIllustAssets', async () => {
    await navBarPage.goToEntity('m-card-illust-assets');
    mCardIllustAssetsComponentsPage = new MCardIllustAssetsComponentsPage();
    await browser.wait(ec.visibilityOf(mCardIllustAssetsComponentsPage.title), 5000);
    expect(await mCardIllustAssetsComponentsPage.getTitle()).to.eq('M Card Illust Assets');
  });

  it('should load create MCardIllustAssets page', async () => {
    await mCardIllustAssetsComponentsPage.clickOnCreateButton();
    mCardIllustAssetsUpdatePage = new MCardIllustAssetsUpdatePage();
    expect(await mCardIllustAssetsUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Illust Assets');
    await mCardIllustAssetsUpdatePage.cancel();
  });

  it('should create and save MCardIllustAssets', async () => {
    const nbButtonsBeforeCreate = await mCardIllustAssetsComponentsPage.countDeleteButtons();

    await mCardIllustAssetsComponentsPage.clickOnCreateButton();
    await promise.all([
      mCardIllustAssetsUpdatePage.setAssetNameInput('assetName'),
      mCardIllustAssetsUpdatePage.setSubAssetNameInput('subAssetName'),
      mCardIllustAssetsUpdatePage.setOffsetInput('offset'),
      mCardIllustAssetsUpdatePage.setBackgroundAssetNameInput('backgroundAssetName'),
      mCardIllustAssetsUpdatePage.setDecorationAssetNameInput('decorationAssetName'),
      mCardIllustAssetsUpdatePage.setEffectAssetNameInput('effectAssetName')
    ]);
    expect(await mCardIllustAssetsUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mCardIllustAssetsUpdatePage.getSubAssetNameInput()).to.eq(
      'subAssetName',
      'Expected SubAssetName value to be equals to subAssetName'
    );
    expect(await mCardIllustAssetsUpdatePage.getOffsetInput()).to.eq('offset', 'Expected Offset value to be equals to offset');
    expect(await mCardIllustAssetsUpdatePage.getBackgroundAssetNameInput()).to.eq(
      'backgroundAssetName',
      'Expected BackgroundAssetName value to be equals to backgroundAssetName'
    );
    expect(await mCardIllustAssetsUpdatePage.getDecorationAssetNameInput()).to.eq(
      'decorationAssetName',
      'Expected DecorationAssetName value to be equals to decorationAssetName'
    );
    expect(await mCardIllustAssetsUpdatePage.getEffectAssetNameInput()).to.eq(
      'effectAssetName',
      'Expected EffectAssetName value to be equals to effectAssetName'
    );
    await mCardIllustAssetsUpdatePage.save();
    expect(await mCardIllustAssetsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCardIllustAssetsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCardIllustAssets', async () => {
    const nbButtonsBeforeDelete = await mCardIllustAssetsComponentsPage.countDeleteButtons();
    await mCardIllustAssetsComponentsPage.clickOnLastDeleteButton();

    mCardIllustAssetsDeleteDialog = new MCardIllustAssetsDeleteDialog();
    expect(await mCardIllustAssetsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Card Illust Assets?');
    await mCardIllustAssetsDeleteDialog.clickOnConfirmButton();

    expect(await mCardIllustAssetsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
