/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MArousalItemComponentsPage, MArousalItemDeleteDialog, MArousalItemUpdatePage } from './m-arousal-item.page-object';

const expect = chai.expect;

describe('MArousalItem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mArousalItemUpdatePage: MArousalItemUpdatePage;
  let mArousalItemComponentsPage: MArousalItemComponentsPage;
  let mArousalItemDeleteDialog: MArousalItemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MArousalItems', async () => {
    await navBarPage.goToEntity('m-arousal-item');
    mArousalItemComponentsPage = new MArousalItemComponentsPage();
    await browser.wait(ec.visibilityOf(mArousalItemComponentsPage.title), 5000);
    expect(await mArousalItemComponentsPage.getTitle()).to.eq('M Arousal Items');
  });

  it('should load create MArousalItem page', async () => {
    await mArousalItemComponentsPage.clickOnCreateButton();
    mArousalItemUpdatePage = new MArousalItemUpdatePage();
    expect(await mArousalItemUpdatePage.getPageTitle()).to.eq('Create or edit a M Arousal Item');
    await mArousalItemUpdatePage.cancel();
  });

  it('should create and save MArousalItems', async () => {
    const nbButtonsBeforeCreate = await mArousalItemComponentsPage.countDeleteButtons();

    await mArousalItemComponentsPage.clickOnCreateButton();
    await promise.all([
      mArousalItemUpdatePage.setNameInput('name'),
      mArousalItemUpdatePage.setDescriptionInput('description'),
      mArousalItemUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mArousalItemUpdatePage.setThumbnailBgAssetNameInput('thumbnailBgAssetName'),
      mArousalItemUpdatePage.setThumbnailFrameAssetNameInput('thumbnailFrameAssetName'),
      mArousalItemUpdatePage.setArousalItemTypeInput('5')
    ]);
    expect(await mArousalItemUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mArousalItemUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mArousalItemUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mArousalItemUpdatePage.getThumbnailBgAssetNameInput()).to.eq(
      'thumbnailBgAssetName',
      'Expected ThumbnailBgAssetName value to be equals to thumbnailBgAssetName'
    );
    expect(await mArousalItemUpdatePage.getThumbnailFrameAssetNameInput()).to.eq(
      'thumbnailFrameAssetName',
      'Expected ThumbnailFrameAssetName value to be equals to thumbnailFrameAssetName'
    );
    expect(await mArousalItemUpdatePage.getArousalItemTypeInput()).to.eq('5', 'Expected arousalItemType value to be equals to 5');
    await mArousalItemUpdatePage.save();
    expect(await mArousalItemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mArousalItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MArousalItem', async () => {
    const nbButtonsBeforeDelete = await mArousalItemComponentsPage.countDeleteButtons();
    await mArousalItemComponentsPage.clickOnLastDeleteButton();

    mArousalItemDeleteDialog = new MArousalItemDeleteDialog();
    expect(await mArousalItemDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Arousal Item?');
    await mArousalItemDeleteDialog.clickOnConfirmButton();

    expect(await mArousalItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
