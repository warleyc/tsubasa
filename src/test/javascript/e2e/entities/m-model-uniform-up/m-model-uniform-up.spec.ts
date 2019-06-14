/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MModelUniformUpComponentsPage, MModelUniformUpDeleteDialog, MModelUniformUpUpdatePage } from './m-model-uniform-up.page-object';

const expect = chai.expect;

describe('MModelUniformUp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelUniformUpUpdatePage: MModelUniformUpUpdatePage;
  let mModelUniformUpComponentsPage: MModelUniformUpComponentsPage;
  let mModelUniformUpDeleteDialog: MModelUniformUpDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelUniformUps', async () => {
    await navBarPage.goToEntity('m-model-uniform-up');
    mModelUniformUpComponentsPage = new MModelUniformUpComponentsPage();
    await browser.wait(ec.visibilityOf(mModelUniformUpComponentsPage.title), 5000);
    expect(await mModelUniformUpComponentsPage.getTitle()).to.eq('M Model Uniform Ups');
  });

  it('should load create MModelUniformUp page', async () => {
    await mModelUniformUpComponentsPage.clickOnCreateButton();
    mModelUniformUpUpdatePage = new MModelUniformUpUpdatePage();
    expect(await mModelUniformUpUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Uniform Up');
    await mModelUniformUpUpdatePage.cancel();
  });

  it('should create and save MModelUniformUps', async () => {
    const nbButtonsBeforeCreate = await mModelUniformUpComponentsPage.countDeleteButtons();

    await mModelUniformUpComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelUniformUpUpdatePage.setUniformUpIdInput('5'),
      mModelUniformUpUpdatePage.setDefaultDressingTypeInput('5'),
      mModelUniformUpUpdatePage.setUniformModelInput('5'),
      mModelUniformUpUpdatePage.setUniformTextureInput('5'),
      mModelUniformUpUpdatePage.setUniformNoTextureInput('5'),
      mModelUniformUpUpdatePage.setDefaultColorInput('defaultColor'),
      mModelUniformUpUpdatePage.setUniformNoColorInput('uniformNoColor'),
      mModelUniformUpUpdatePage.setNumberChestInput('5'),
      mModelUniformUpUpdatePage.setNumberBellyInput('5'),
      mModelUniformUpUpdatePage.setNumberBackInput('5'),
      mModelUniformUpUpdatePage.setUniformNoPositionXInput('5'),
      mModelUniformUpUpdatePage.setUniformNoPositionYInput('5')
    ]);
    expect(await mModelUniformUpUpdatePage.getUniformUpIdInput()).to.eq('5', 'Expected uniformUpId value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getDefaultDressingTypeInput()).to.eq(
      '5',
      'Expected defaultDressingType value to be equals to 5'
    );
    expect(await mModelUniformUpUpdatePage.getUniformModelInput()).to.eq('5', 'Expected uniformModel value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getUniformTextureInput()).to.eq('5', 'Expected uniformTexture value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getUniformNoTextureInput()).to.eq('5', 'Expected uniformNoTexture value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getDefaultColorInput()).to.eq(
      'defaultColor',
      'Expected DefaultColor value to be equals to defaultColor'
    );
    expect(await mModelUniformUpUpdatePage.getUniformNoColorInput()).to.eq(
      'uniformNoColor',
      'Expected UniformNoColor value to be equals to uniformNoColor'
    );
    expect(await mModelUniformUpUpdatePage.getNumberChestInput()).to.eq('5', 'Expected numberChest value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getNumberBellyInput()).to.eq('5', 'Expected numberBelly value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getNumberBackInput()).to.eq('5', 'Expected numberBack value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getUniformNoPositionXInput()).to.eq('5', 'Expected uniformNoPositionX value to be equals to 5');
    expect(await mModelUniformUpUpdatePage.getUniformNoPositionYInput()).to.eq('5', 'Expected uniformNoPositionY value to be equals to 5');
    await mModelUniformUpUpdatePage.save();
    expect(await mModelUniformUpUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelUniformUpComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MModelUniformUp', async () => {
    const nbButtonsBeforeDelete = await mModelUniformUpComponentsPage.countDeleteButtons();
    await mModelUniformUpComponentsPage.clickOnLastDeleteButton();

    mModelUniformUpDeleteDialog = new MModelUniformUpDeleteDialog();
    expect(await mModelUniformUpDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Model Uniform Up?');
    await mModelUniformUpDeleteDialog.clickOnConfirmButton();

    expect(await mModelUniformUpComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
