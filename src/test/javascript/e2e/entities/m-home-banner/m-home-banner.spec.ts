/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MHomeBannerComponentsPage, MHomeBannerDeleteDialog, MHomeBannerUpdatePage } from './m-home-banner.page-object';

const expect = chai.expect;

describe('MHomeBanner e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mHomeBannerUpdatePage: MHomeBannerUpdatePage;
  let mHomeBannerComponentsPage: MHomeBannerComponentsPage;
  let mHomeBannerDeleteDialog: MHomeBannerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MHomeBanners', async () => {
    await navBarPage.goToEntity('m-home-banner');
    mHomeBannerComponentsPage = new MHomeBannerComponentsPage();
    await browser.wait(ec.visibilityOf(mHomeBannerComponentsPage.title), 5000);
    expect(await mHomeBannerComponentsPage.getTitle()).to.eq('M Home Banners');
  });

  it('should load create MHomeBanner page', async () => {
    await mHomeBannerComponentsPage.clickOnCreateButton();
    mHomeBannerUpdatePage = new MHomeBannerUpdatePage();
    expect(await mHomeBannerUpdatePage.getPageTitle()).to.eq('Create or edit a M Home Banner');
    await mHomeBannerUpdatePage.cancel();
  });

  it('should create and save MHomeBanners', async () => {
    const nbButtonsBeforeCreate = await mHomeBannerComponentsPage.countDeleteButtons();

    await mHomeBannerComponentsPage.clickOnCreateButton();
    await promise.all([
      mHomeBannerUpdatePage.setBannerTypeInput('5'),
      mHomeBannerUpdatePage.setAssetNameInput('assetName'),
      mHomeBannerUpdatePage.setStartAtInput('5'),
      mHomeBannerUpdatePage.setEndAtInput('5'),
      mHomeBannerUpdatePage.setLabelEndAtInput('5'),
      mHomeBannerUpdatePage.setSceneTransitionInput('5'),
      mHomeBannerUpdatePage.setSceneTransitionParamInput('sceneTransitionParam'),
      mHomeBannerUpdatePage.setOrderNumInput('5'),
      mHomeBannerUpdatePage.setAppealTypeInput('5'),
      mHomeBannerUpdatePage.setAppealContentIdInput('5')
    ]);
    expect(await mHomeBannerUpdatePage.getBannerTypeInput()).to.eq('5', 'Expected bannerType value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mHomeBannerUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getLabelEndAtInput()).to.eq('5', 'Expected labelEndAt value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getSceneTransitionInput()).to.eq('5', 'Expected sceneTransition value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getSceneTransitionParamInput()).to.eq(
      'sceneTransitionParam',
      'Expected SceneTransitionParam value to be equals to sceneTransitionParam'
    );
    expect(await mHomeBannerUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getAppealTypeInput()).to.eq('5', 'Expected appealType value to be equals to 5');
    expect(await mHomeBannerUpdatePage.getAppealContentIdInput()).to.eq('5', 'Expected appealContentId value to be equals to 5');
    await mHomeBannerUpdatePage.save();
    expect(await mHomeBannerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mHomeBannerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MHomeBanner', async () => {
    const nbButtonsBeforeDelete = await mHomeBannerComponentsPage.countDeleteButtons();
    await mHomeBannerComponentsPage.clickOnLastDeleteButton();

    mHomeBannerDeleteDialog = new MHomeBannerDeleteDialog();
    expect(await mHomeBannerDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Home Banner?');
    await mHomeBannerDeleteDialog.clickOnConfirmButton();

    expect(await mHomeBannerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
