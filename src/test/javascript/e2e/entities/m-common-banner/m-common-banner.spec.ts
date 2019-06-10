/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCommonBannerComponentsPage, MCommonBannerDeleteDialog, MCommonBannerUpdatePage } from './m-common-banner.page-object';

const expect = chai.expect;

describe('MCommonBanner e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCommonBannerUpdatePage: MCommonBannerUpdatePage;
  let mCommonBannerComponentsPage: MCommonBannerComponentsPage;
  let mCommonBannerDeleteDialog: MCommonBannerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCommonBanners', async () => {
    await navBarPage.goToEntity('m-common-banner');
    mCommonBannerComponentsPage = new MCommonBannerComponentsPage();
    await browser.wait(ec.visibilityOf(mCommonBannerComponentsPage.title), 5000);
    expect(await mCommonBannerComponentsPage.getTitle()).to.eq('M Common Banners');
  });

  it('should load create MCommonBanner page', async () => {
    await mCommonBannerComponentsPage.clickOnCreateButton();
    mCommonBannerUpdatePage = new MCommonBannerUpdatePage();
    expect(await mCommonBannerUpdatePage.getPageTitle()).to.eq('Create or edit a M Common Banner');
    await mCommonBannerUpdatePage.cancel();
  });

  it('should create and save MCommonBanners', async () => {
    const nbButtonsBeforeCreate = await mCommonBannerComponentsPage.countDeleteButtons();

    await mCommonBannerComponentsPage.clickOnCreateButton();
    await promise.all([
      mCommonBannerUpdatePage.setAssetNameInput('assetName'),
      mCommonBannerUpdatePage.setStartAtInput('5'),
      mCommonBannerUpdatePage.setEndAtInput('5'),
      mCommonBannerUpdatePage.setLabelEndAtInput('5'),
      mCommonBannerUpdatePage.setSceneTransitionInput('5'),
      mCommonBannerUpdatePage.setSceneTransitionParamInput('sceneTransitionParam'),
      mCommonBannerUpdatePage.setOrderNumInput('5'),
      mCommonBannerUpdatePage.setAppealTypeInput('5'),
      mCommonBannerUpdatePage.setAppealContentIdInput('5')
    ]);
    expect(await mCommonBannerUpdatePage.getAssetNameInput()).to.eq('assetName', 'Expected AssetName value to be equals to assetName');
    expect(await mCommonBannerUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getLabelEndAtInput()).to.eq('5', 'Expected labelEndAt value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getSceneTransitionInput()).to.eq('5', 'Expected sceneTransition value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getSceneTransitionParamInput()).to.eq(
      'sceneTransitionParam',
      'Expected SceneTransitionParam value to be equals to sceneTransitionParam'
    );
    expect(await mCommonBannerUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getAppealTypeInput()).to.eq('5', 'Expected appealType value to be equals to 5');
    expect(await mCommonBannerUpdatePage.getAppealContentIdInput()).to.eq('5', 'Expected appealContentId value to be equals to 5');
    await mCommonBannerUpdatePage.save();
    expect(await mCommonBannerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCommonBannerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCommonBanner', async () => {
    const nbButtonsBeforeDelete = await mCommonBannerComponentsPage.countDeleteButtons();
    await mCommonBannerComponentsPage.clickOnLastDeleteButton();

    mCommonBannerDeleteDialog = new MCommonBannerDeleteDialog();
    expect(await mCommonBannerDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Common Banner?');
    await mCommonBannerDeleteDialog.clickOnConfirmButton();

    expect(await mCommonBannerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
