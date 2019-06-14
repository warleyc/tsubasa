/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGuildTopBannerComponentsPage, MGuildTopBannerDeleteDialog, MGuildTopBannerUpdatePage } from './m-guild-top-banner.page-object';

const expect = chai.expect;

describe('MGuildTopBanner e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildTopBannerUpdatePage: MGuildTopBannerUpdatePage;
  let mGuildTopBannerComponentsPage: MGuildTopBannerComponentsPage;
  let mGuildTopBannerDeleteDialog: MGuildTopBannerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildTopBanners', async () => {
    await navBarPage.goToEntity('m-guild-top-banner');
    mGuildTopBannerComponentsPage = new MGuildTopBannerComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildTopBannerComponentsPage.title), 5000);
    expect(await mGuildTopBannerComponentsPage.getTitle()).to.eq('M Guild Top Banners');
  });

  it('should load create MGuildTopBanner page', async () => {
    await mGuildTopBannerComponentsPage.clickOnCreateButton();
    mGuildTopBannerUpdatePage = new MGuildTopBannerUpdatePage();
    expect(await mGuildTopBannerUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Top Banner');
    await mGuildTopBannerUpdatePage.cancel();
  });

  it('should create and save MGuildTopBanners', async () => {
    const nbButtonsBeforeCreate = await mGuildTopBannerComponentsPage.countDeleteButtons();

    await mGuildTopBannerComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuildTopBannerUpdatePage.setAssetPathInput('assetPath'),
      mGuildTopBannerUpdatePage.setGuildBannerTypeInput('5'),
      mGuildTopBannerUpdatePage.setStartAtInput('5'),
      mGuildTopBannerUpdatePage.setEndAtInput('5')
    ]);
    expect(await mGuildTopBannerUpdatePage.getAssetPathInput()).to.eq('assetPath', 'Expected AssetPath value to be equals to assetPath');
    expect(await mGuildTopBannerUpdatePage.getGuildBannerTypeInput()).to.eq('5', 'Expected guildBannerType value to be equals to 5');
    expect(await mGuildTopBannerUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mGuildTopBannerUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mGuildTopBannerUpdatePage.save();
    expect(await mGuildTopBannerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildTopBannerComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuildTopBanner', async () => {
    const nbButtonsBeforeDelete = await mGuildTopBannerComponentsPage.countDeleteButtons();
    await mGuildTopBannerComponentsPage.clickOnLastDeleteButton();

    mGuildTopBannerDeleteDialog = new MGuildTopBannerDeleteDialog();
    expect(await mGuildTopBannerDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Top Banner?');
    await mGuildTopBannerDeleteDialog.clickOnConfirmButton();

    expect(await mGuildTopBannerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
