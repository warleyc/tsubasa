/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MUniformOriginalSetComponentsPage,
  MUniformOriginalSetDeleteDialog,
  MUniformOriginalSetUpdatePage
} from './m-uniform-original-set.page-object';

const expect = chai.expect;

describe('MUniformOriginalSet e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mUniformOriginalSetUpdatePage: MUniformOriginalSetUpdatePage;
  let mUniformOriginalSetComponentsPage: MUniformOriginalSetComponentsPage;
  let mUniformOriginalSetDeleteDialog: MUniformOriginalSetDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MUniformOriginalSets', async () => {
    await navBarPage.goToEntity('m-uniform-original-set');
    mUniformOriginalSetComponentsPage = new MUniformOriginalSetComponentsPage();
    await browser.wait(ec.visibilityOf(mUniformOriginalSetComponentsPage.title), 5000);
    expect(await mUniformOriginalSetComponentsPage.getTitle()).to.eq('M Uniform Original Sets');
  });

  it('should load create MUniformOriginalSet page', async () => {
    await mUniformOriginalSetComponentsPage.clickOnCreateButton();
    mUniformOriginalSetUpdatePage = new MUniformOriginalSetUpdatePage();
    expect(await mUniformOriginalSetUpdatePage.getPageTitle()).to.eq('Create or edit a M Uniform Original Set');
    await mUniformOriginalSetUpdatePage.cancel();
  });

  it('should create and save MUniformOriginalSets', async () => {
    const nbButtonsBeforeCreate = await mUniformOriginalSetComponentsPage.countDeleteButtons();

    await mUniformOriginalSetComponentsPage.clickOnCreateButton();
    await promise.all([
      mUniformOriginalSetUpdatePage.setNameInput('name'),
      mUniformOriginalSetUpdatePage.setShortNameInput('shortName'),
      mUniformOriginalSetUpdatePage.setMenuNameInput('menuName'),
      mUniformOriginalSetUpdatePage.setUpModelIdInput('5'),
      mUniformOriginalSetUpdatePage.setBottomModelIdInput('5'),
      mUniformOriginalSetUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mUniformOriginalSetUpdatePage.setUniformTypeInput('5'),
      mUniformOriginalSetUpdatePage.setIsDefaultInput('5'),
      mUniformOriginalSetUpdatePage.setOrderNumInput('5'),
      mUniformOriginalSetUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mUniformOriginalSetUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mUniformOriginalSetUpdatePage.getShortNameInput()).to.eq(
      'shortName',
      'Expected ShortName value to be equals to shortName'
    );
    expect(await mUniformOriginalSetUpdatePage.getMenuNameInput()).to.eq('menuName', 'Expected MenuName value to be equals to menuName');
    expect(await mUniformOriginalSetUpdatePage.getUpModelIdInput()).to.eq('5', 'Expected upModelId value to be equals to 5');
    expect(await mUniformOriginalSetUpdatePage.getBottomModelIdInput()).to.eq('5', 'Expected bottomModelId value to be equals to 5');
    expect(await mUniformOriginalSetUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mUniformOriginalSetUpdatePage.getUniformTypeInput()).to.eq('5', 'Expected uniformType value to be equals to 5');
    expect(await mUniformOriginalSetUpdatePage.getIsDefaultInput()).to.eq('5', 'Expected isDefault value to be equals to 5');
    expect(await mUniformOriginalSetUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mUniformOriginalSetUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mUniformOriginalSetUpdatePage.save();
    expect(await mUniformOriginalSetUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mUniformOriginalSetComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MUniformOriginalSet', async () => {
    const nbButtonsBeforeDelete = await mUniformOriginalSetComponentsPage.countDeleteButtons();
    await mUniformOriginalSetComponentsPage.clickOnLastDeleteButton();

    mUniformOriginalSetDeleteDialog = new MUniformOriginalSetDeleteDialog();
    expect(await mUniformOriginalSetDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Uniform Original Set?');
    await mUniformOriginalSetDeleteDialog.clickOnConfirmButton();

    expect(await mUniformOriginalSetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
