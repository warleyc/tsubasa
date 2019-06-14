/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MUniformBottomComponentsPage, MUniformBottomDeleteDialog, MUniformBottomUpdatePage } from './m-uniform-bottom.page-object';

const expect = chai.expect;

describe('MUniformBottom e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mUniformBottomUpdatePage: MUniformBottomUpdatePage;
  let mUniformBottomComponentsPage: MUniformBottomComponentsPage;
  let mUniformBottomDeleteDialog: MUniformBottomDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MUniformBottoms', async () => {
    await navBarPage.goToEntity('m-uniform-bottom');
    mUniformBottomComponentsPage = new MUniformBottomComponentsPage();
    await browser.wait(ec.visibilityOf(mUniformBottomComponentsPage.title), 5000);
    expect(await mUniformBottomComponentsPage.getTitle()).to.eq('M Uniform Bottoms');
  });

  it('should load create MUniformBottom page', async () => {
    await mUniformBottomComponentsPage.clickOnCreateButton();
    mUniformBottomUpdatePage = new MUniformBottomUpdatePage();
    expect(await mUniformBottomUpdatePage.getPageTitle()).to.eq('Create or edit a M Uniform Bottom');
    await mUniformBottomUpdatePage.cancel();
  });

  it('should create and save MUniformBottoms', async () => {
    const nbButtonsBeforeCreate = await mUniformBottomComponentsPage.countDeleteButtons();

    await mUniformBottomComponentsPage.clickOnCreateButton();
    await promise.all([
      mUniformBottomUpdatePage.setNameInput('name'),
      mUniformBottomUpdatePage.setShortNameInput('shortName'),
      mUniformBottomUpdatePage.setMenuNameInput('menuName'),
      mUniformBottomUpdatePage.setModelIdInput('5'),
      mUniformBottomUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mUniformBottomUpdatePage.setUniformTypeInput('5'),
      mUniformBottomUpdatePage.setIsDefaultInput('5'),
      mUniformBottomUpdatePage.setOrderNumInput('5'),
      mUniformBottomUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mUniformBottomUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mUniformBottomUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mUniformBottomUpdatePage.getMenuNameInput()).to.eq('menuName', 'Expected MenuName value to be equals to menuName');
    expect(await mUniformBottomUpdatePage.getModelIdInput()).to.eq('5', 'Expected modelId value to be equals to 5');
    expect(await mUniformBottomUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mUniformBottomUpdatePage.getUniformTypeInput()).to.eq('5', 'Expected uniformType value to be equals to 5');
    expect(await mUniformBottomUpdatePage.getIsDefaultInput()).to.eq('5', 'Expected isDefault value to be equals to 5');
    expect(await mUniformBottomUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mUniformBottomUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mUniformBottomUpdatePage.save();
    expect(await mUniformBottomUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mUniformBottomComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MUniformBottom', async () => {
    const nbButtonsBeforeDelete = await mUniformBottomComponentsPage.countDeleteButtons();
    await mUniformBottomComponentsPage.clickOnLastDeleteButton();

    mUniformBottomDeleteDialog = new MUniformBottomDeleteDialog();
    expect(await mUniformBottomDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Uniform Bottom?');
    await mUniformBottomDeleteDialog.clickOnConfirmButton();

    expect(await mUniformBottomComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
