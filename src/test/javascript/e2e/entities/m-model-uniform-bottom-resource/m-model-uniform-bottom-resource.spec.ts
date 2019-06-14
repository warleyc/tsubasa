/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MModelUniformBottomResourceComponentsPage,
  MModelUniformBottomResourceDeleteDialog,
  MModelUniformBottomResourceUpdatePage
} from './m-model-uniform-bottom-resource.page-object';

const expect = chai.expect;

describe('MModelUniformBottomResource e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelUniformBottomResourceUpdatePage: MModelUniformBottomResourceUpdatePage;
  let mModelUniformBottomResourceComponentsPage: MModelUniformBottomResourceComponentsPage;
  let mModelUniformBottomResourceDeleteDialog: MModelUniformBottomResourceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelUniformBottomResources', async () => {
    await navBarPage.goToEntity('m-model-uniform-bottom-resource');
    mModelUniformBottomResourceComponentsPage = new MModelUniformBottomResourceComponentsPage();
    await browser.wait(ec.visibilityOf(mModelUniformBottomResourceComponentsPage.title), 5000);
    expect(await mModelUniformBottomResourceComponentsPage.getTitle()).to.eq('M Model Uniform Bottom Resources');
  });

  it('should load create MModelUniformBottomResource page', async () => {
    await mModelUniformBottomResourceComponentsPage.clickOnCreateButton();
    mModelUniformBottomResourceUpdatePage = new MModelUniformBottomResourceUpdatePage();
    expect(await mModelUniformBottomResourceUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Uniform Bottom Resource');
    await mModelUniformBottomResourceUpdatePage.cancel();
  });

  it('should create and save MModelUniformBottomResources', async () => {
    const nbButtonsBeforeCreate = await mModelUniformBottomResourceComponentsPage.countDeleteButtons();

    await mModelUniformBottomResourceComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelUniformBottomResourceUpdatePage.setUniformTypeIdInput('5'),
      mModelUniformBottomResourceUpdatePage.setDressingTypeInput('5'),
      mModelUniformBottomResourceUpdatePage.setWidthInput('5')
    ]);
    expect(await mModelUniformBottomResourceUpdatePage.getUniformTypeIdInput()).to.eq(
      '5',
      'Expected uniformTypeId value to be equals to 5'
    );
    expect(await mModelUniformBottomResourceUpdatePage.getDressingTypeInput()).to.eq('5', 'Expected dressingType value to be equals to 5');
    expect(await mModelUniformBottomResourceUpdatePage.getWidthInput()).to.eq('5', 'Expected width value to be equals to 5');
    await mModelUniformBottomResourceUpdatePage.save();
    expect(await mModelUniformBottomResourceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelUniformBottomResourceComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MModelUniformBottomResource', async () => {
    const nbButtonsBeforeDelete = await mModelUniformBottomResourceComponentsPage.countDeleteButtons();
    await mModelUniformBottomResourceComponentsPage.clickOnLastDeleteButton();

    mModelUniformBottomResourceDeleteDialog = new MModelUniformBottomResourceDeleteDialog();
    expect(await mModelUniformBottomResourceDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Model Uniform Bottom Resource?'
    );
    await mModelUniformBottomResourceDeleteDialog.clickOnConfirmButton();

    expect(await mModelUniformBottomResourceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
