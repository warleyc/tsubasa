/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MAssetComponentsPage, MAssetDeleteDialog, MAssetUpdatePage } from './m-asset.page-object';

const expect = chai.expect;

describe('MAsset e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAssetUpdatePage: MAssetUpdatePage;
  let mAssetComponentsPage: MAssetComponentsPage;
  let mAssetDeleteDialog: MAssetDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAssets', async () => {
    await navBarPage.goToEntity('m-asset');
    mAssetComponentsPage = new MAssetComponentsPage();
    await browser.wait(ec.visibilityOf(mAssetComponentsPage.title), 5000);
    expect(await mAssetComponentsPage.getTitle()).to.eq('M Assets');
  });

  it('should load create MAsset page', async () => {
    await mAssetComponentsPage.clickOnCreateButton();
    mAssetUpdatePage = new MAssetUpdatePage();
    expect(await mAssetUpdatePage.getPageTitle()).to.eq('Create or edit a M Asset');
    await mAssetUpdatePage.cancel();
  });

  it('should create and save MAssets', async () => {
    const nbButtonsBeforeCreate = await mAssetComponentsPage.countDeleteButtons();

    await mAssetComponentsPage.clickOnCreateButton();
    await promise.all([
      mAssetUpdatePage.setAssetBundleNameInput('assetBundleName'),
      mAssetUpdatePage.setTagInput('tag'),
      mAssetUpdatePage.setDependenciesInput('dependencies'),
      mAssetUpdatePage.setI18nInput('5'),
      mAssetUpdatePage.setPlatformInput('platform'),
      mAssetUpdatePage.setPackNameInput('packName'),
      mAssetUpdatePage.setHeadInput('5'),
      mAssetUpdatePage.setSizeInput('5'),
      mAssetUpdatePage.setKey1Input('5'),
      mAssetUpdatePage.setKey2Input('5')
    ]);
    expect(await mAssetUpdatePage.getAssetBundleNameInput()).to.eq(
      'assetBundleName',
      'Expected AssetBundleName value to be equals to assetBundleName'
    );
    expect(await mAssetUpdatePage.getTagInput()).to.eq('tag', 'Expected Tag value to be equals to tag');
    expect(await mAssetUpdatePage.getDependenciesInput()).to.eq('dependencies', 'Expected Dependencies value to be equals to dependencies');
    expect(await mAssetUpdatePage.getI18nInput()).to.eq('5', 'Expected i18n value to be equals to 5');
    expect(await mAssetUpdatePage.getPlatformInput()).to.eq('platform', 'Expected Platform value to be equals to platform');
    expect(await mAssetUpdatePage.getPackNameInput()).to.eq('packName', 'Expected PackName value to be equals to packName');
    expect(await mAssetUpdatePage.getHeadInput()).to.eq('5', 'Expected head value to be equals to 5');
    expect(await mAssetUpdatePage.getSizeInput()).to.eq('5', 'Expected size value to be equals to 5');
    expect(await mAssetUpdatePage.getKey1Input()).to.eq('5', 'Expected key1 value to be equals to 5');
    expect(await mAssetUpdatePage.getKey2Input()).to.eq('5', 'Expected key2 value to be equals to 5');
    await mAssetUpdatePage.save();
    expect(await mAssetUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAssetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MAsset', async () => {
    const nbButtonsBeforeDelete = await mAssetComponentsPage.countDeleteButtons();
    await mAssetComponentsPage.clickOnLastDeleteButton();

    mAssetDeleteDialog = new MAssetDeleteDialog();
    expect(await mAssetDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Asset?');
    await mAssetDeleteDialog.clickOnConfirmButton();

    expect(await mAssetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
