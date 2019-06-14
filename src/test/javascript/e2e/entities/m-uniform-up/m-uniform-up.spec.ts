/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MUniformUpComponentsPage, MUniformUpDeleteDialog, MUniformUpUpdatePage } from './m-uniform-up.page-object';

const expect = chai.expect;

describe('MUniformUp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mUniformUpUpdatePage: MUniformUpUpdatePage;
  let mUniformUpComponentsPage: MUniformUpComponentsPage;
  let mUniformUpDeleteDialog: MUniformUpDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MUniformUps', async () => {
    await navBarPage.goToEntity('m-uniform-up');
    mUniformUpComponentsPage = new MUniformUpComponentsPage();
    await browser.wait(ec.visibilityOf(mUniformUpComponentsPage.title), 5000);
    expect(await mUniformUpComponentsPage.getTitle()).to.eq('M Uniform Ups');
  });

  it('should load create MUniformUp page', async () => {
    await mUniformUpComponentsPage.clickOnCreateButton();
    mUniformUpUpdatePage = new MUniformUpUpdatePage();
    expect(await mUniformUpUpdatePage.getPageTitle()).to.eq('Create or edit a M Uniform Up');
    await mUniformUpUpdatePage.cancel();
  });

  it('should create and save MUniformUps', async () => {
    const nbButtonsBeforeCreate = await mUniformUpComponentsPage.countDeleteButtons();

    await mUniformUpComponentsPage.clickOnCreateButton();
    await promise.all([
      mUniformUpUpdatePage.setNameInput('name'),
      mUniformUpUpdatePage.setShortNameInput('shortName'),
      mUniformUpUpdatePage.setMenuNameInput('menuName'),
      mUniformUpUpdatePage.setModelIdInput('5'),
      mUniformUpUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mUniformUpUpdatePage.setUniformTypeInput('5'),
      mUniformUpUpdatePage.setIsDefaultInput('5'),
      mUniformUpUpdatePage.setOrderNumInput('5'),
      mUniformUpUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mUniformUpUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mUniformUpUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mUniformUpUpdatePage.getMenuNameInput()).to.eq('menuName', 'Expected MenuName value to be equals to menuName');
    expect(await mUniformUpUpdatePage.getModelIdInput()).to.eq('5', 'Expected modelId value to be equals to 5');
    expect(await mUniformUpUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mUniformUpUpdatePage.getUniformTypeInput()).to.eq('5', 'Expected uniformType value to be equals to 5');
    expect(await mUniformUpUpdatePage.getIsDefaultInput()).to.eq('5', 'Expected isDefault value to be equals to 5');
    expect(await mUniformUpUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mUniformUpUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    await mUniformUpUpdatePage.save();
    expect(await mUniformUpUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mUniformUpComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MUniformUp', async () => {
    const nbButtonsBeforeDelete = await mUniformUpComponentsPage.countDeleteButtons();
    await mUniformUpComponentsPage.clickOnLastDeleteButton();

    mUniformUpDeleteDialog = new MUniformUpDeleteDialog();
    expect(await mUniformUpDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Uniform Up?');
    await mUniformUpDeleteDialog.clickOnConfirmButton();

    expect(await mUniformUpComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
