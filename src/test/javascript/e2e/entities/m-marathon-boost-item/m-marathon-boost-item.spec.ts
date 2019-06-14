/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonBoostItemComponentsPage,
  MMarathonBoostItemDeleteDialog,
  MMarathonBoostItemUpdatePage
} from './m-marathon-boost-item.page-object';

const expect = chai.expect;

describe('MMarathonBoostItem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonBoostItemUpdatePage: MMarathonBoostItemUpdatePage;
  let mMarathonBoostItemComponentsPage: MMarathonBoostItemComponentsPage;
  let mMarathonBoostItemDeleteDialog: MMarathonBoostItemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonBoostItems', async () => {
    await navBarPage.goToEntity('m-marathon-boost-item');
    mMarathonBoostItemComponentsPage = new MMarathonBoostItemComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonBoostItemComponentsPage.title), 5000);
    expect(await mMarathonBoostItemComponentsPage.getTitle()).to.eq('M Marathon Boost Items');
  });

  it('should load create MMarathonBoostItem page', async () => {
    await mMarathonBoostItemComponentsPage.clickOnCreateButton();
    mMarathonBoostItemUpdatePage = new MMarathonBoostItemUpdatePage();
    expect(await mMarathonBoostItemUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Boost Item');
    await mMarathonBoostItemUpdatePage.cancel();
  });

  it('should create and save MMarathonBoostItems', async () => {
    const nbButtonsBeforeCreate = await mMarathonBoostItemComponentsPage.countDeleteButtons();

    await mMarathonBoostItemComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonBoostItemUpdatePage.setEventIdInput('5'),
      mMarathonBoostItemUpdatePage.setBoostRatioInput('5'),
      mMarathonBoostItemUpdatePage.setNameInput('name'),
      mMarathonBoostItemUpdatePage.setShortNameInput('shortName'),
      mMarathonBoostItemUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mMarathonBoostItemUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mMarathonBoostItemUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonBoostItemUpdatePage.getBoostRatioInput()).to.eq('5', 'Expected boostRatio value to be equals to 5');
    expect(await mMarathonBoostItemUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mMarathonBoostItemUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mMarathonBoostItemUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mMarathonBoostItemUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mMarathonBoostItemUpdatePage.save();
    expect(await mMarathonBoostItemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonBoostItemComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonBoostItem', async () => {
    const nbButtonsBeforeDelete = await mMarathonBoostItemComponentsPage.countDeleteButtons();
    await mMarathonBoostItemComponentsPage.clickOnLastDeleteButton();

    mMarathonBoostItemDeleteDialog = new MMarathonBoostItemDeleteDialog();
    expect(await mMarathonBoostItemDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Marathon Boost Item?');
    await mMarathonBoostItemDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonBoostItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
