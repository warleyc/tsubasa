/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MModelUniformUpResourceComponentsPage,
  MModelUniformUpResourceDeleteDialog,
  MModelUniformUpResourceUpdatePage
} from './m-model-uniform-up-resource.page-object';

const expect = chai.expect;

describe('MModelUniformUpResource e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelUniformUpResourceUpdatePage: MModelUniformUpResourceUpdatePage;
  let mModelUniformUpResourceComponentsPage: MModelUniformUpResourceComponentsPage;
  let mModelUniformUpResourceDeleteDialog: MModelUniformUpResourceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelUniformUpResources', async () => {
    await navBarPage.goToEntity('m-model-uniform-up-resource');
    mModelUniformUpResourceComponentsPage = new MModelUniformUpResourceComponentsPage();
    await browser.wait(ec.visibilityOf(mModelUniformUpResourceComponentsPage.title), 5000);
    expect(await mModelUniformUpResourceComponentsPage.getTitle()).to.eq('M Model Uniform Up Resources');
  });

  it('should load create MModelUniformUpResource page', async () => {
    await mModelUniformUpResourceComponentsPage.clickOnCreateButton();
    mModelUniformUpResourceUpdatePage = new MModelUniformUpResourceUpdatePage();
    expect(await mModelUniformUpResourceUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Uniform Up Resource');
    await mModelUniformUpResourceUpdatePage.cancel();
  });

  it('should create and save MModelUniformUpResources', async () => {
    const nbButtonsBeforeCreate = await mModelUniformUpResourceComponentsPage.countDeleteButtons();

    await mModelUniformUpResourceComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelUniformUpResourceUpdatePage.setUniformTypeIdInput('5'),
      mModelUniformUpResourceUpdatePage.setDressingTypeInput('5'),
      mModelUniformUpResourceUpdatePage.setWidthInput('5')
    ]);
    expect(await mModelUniformUpResourceUpdatePage.getUniformTypeIdInput()).to.eq('5', 'Expected uniformTypeId value to be equals to 5');
    expect(await mModelUniformUpResourceUpdatePage.getDressingTypeInput()).to.eq('5', 'Expected dressingType value to be equals to 5');
    expect(await mModelUniformUpResourceUpdatePage.getWidthInput()).to.eq('5', 'Expected width value to be equals to 5');
    await mModelUniformUpResourceUpdatePage.save();
    expect(await mModelUniformUpResourceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelUniformUpResourceComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MModelUniformUpResource', async () => {
    const nbButtonsBeforeDelete = await mModelUniformUpResourceComponentsPage.countDeleteButtons();
    await mModelUniformUpResourceComponentsPage.clickOnLastDeleteButton();

    mModelUniformUpResourceDeleteDialog = new MModelUniformUpResourceDeleteDialog();
    expect(await mModelUniformUpResourceDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Model Uniform Up Resource?'
    );
    await mModelUniformUpResourceDeleteDialog.clickOnConfirmButton();

    expect(await mModelUniformUpResourceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
