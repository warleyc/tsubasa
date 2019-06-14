/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MModelUniformBottomComponentsPage,
  MModelUniformBottomDeleteDialog,
  MModelUniformBottomUpdatePage
} from './m-model-uniform-bottom.page-object';

const expect = chai.expect;

describe('MModelUniformBottom e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelUniformBottomUpdatePage: MModelUniformBottomUpdatePage;
  let mModelUniformBottomComponentsPage: MModelUniformBottomComponentsPage;
  let mModelUniformBottomDeleteDialog: MModelUniformBottomDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelUniformBottoms', async () => {
    await navBarPage.goToEntity('m-model-uniform-bottom');
    mModelUniformBottomComponentsPage = new MModelUniformBottomComponentsPage();
    await browser.wait(ec.visibilityOf(mModelUniformBottomComponentsPage.title), 5000);
    expect(await mModelUniformBottomComponentsPage.getTitle()).to.eq('M Model Uniform Bottoms');
  });

  it('should load create MModelUniformBottom page', async () => {
    await mModelUniformBottomComponentsPage.clickOnCreateButton();
    mModelUniformBottomUpdatePage = new MModelUniformBottomUpdatePage();
    expect(await mModelUniformBottomUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Uniform Bottom');
    await mModelUniformBottomUpdatePage.cancel();
  });

  it('should create and save MModelUniformBottoms', async () => {
    const nbButtonsBeforeCreate = await mModelUniformBottomComponentsPage.countDeleteButtons();

    await mModelUniformBottomComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelUniformBottomUpdatePage.setUniformBottomIdInput('5'),
      mModelUniformBottomUpdatePage.setDefaultDressingTypeInput('5'),
      mModelUniformBottomUpdatePage.setUniformModelInput('5'),
      mModelUniformBottomUpdatePage.setUniformTextureInput('5'),
      mModelUniformBottomUpdatePage.setUniformNoTextureInput('5'),
      mModelUniformBottomUpdatePage.setDefaultColorInput('defaultColor'),
      mModelUniformBottomUpdatePage.setUniformNoColorInput('uniformNoColor'),
      mModelUniformBottomUpdatePage.setNumberRightLegInput('5'),
      mModelUniformBottomUpdatePage.setNumberLeftLegInput('5'),
      mModelUniformBottomUpdatePage.setUniformNoPositionXInput('5'),
      mModelUniformBottomUpdatePage.setUniformNoPositionYInput('5')
    ]);
    expect(await mModelUniformBottomUpdatePage.getUniformBottomIdInput()).to.eq('5', 'Expected uniformBottomId value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getDefaultDressingTypeInput()).to.eq(
      '5',
      'Expected defaultDressingType value to be equals to 5'
    );
    expect(await mModelUniformBottomUpdatePage.getUniformModelInput()).to.eq('5', 'Expected uniformModel value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getUniformTextureInput()).to.eq('5', 'Expected uniformTexture value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getUniformNoTextureInput()).to.eq('5', 'Expected uniformNoTexture value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getDefaultColorInput()).to.eq(
      'defaultColor',
      'Expected DefaultColor value to be equals to defaultColor'
    );
    expect(await mModelUniformBottomUpdatePage.getUniformNoColorInput()).to.eq(
      'uniformNoColor',
      'Expected UniformNoColor value to be equals to uniformNoColor'
    );
    expect(await mModelUniformBottomUpdatePage.getNumberRightLegInput()).to.eq('5', 'Expected numberRightLeg value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getNumberLeftLegInput()).to.eq('5', 'Expected numberLeftLeg value to be equals to 5');
    expect(await mModelUniformBottomUpdatePage.getUniformNoPositionXInput()).to.eq(
      '5',
      'Expected uniformNoPositionX value to be equals to 5'
    );
    expect(await mModelUniformBottomUpdatePage.getUniformNoPositionYInput()).to.eq(
      '5',
      'Expected uniformNoPositionY value to be equals to 5'
    );
    await mModelUniformBottomUpdatePage.save();
    expect(await mModelUniformBottomUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelUniformBottomComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MModelUniformBottom', async () => {
    const nbButtonsBeforeDelete = await mModelUniformBottomComponentsPage.countDeleteButtons();
    await mModelUniformBottomComponentsPage.clickOnLastDeleteButton();

    mModelUniformBottomDeleteDialog = new MModelUniformBottomDeleteDialog();
    expect(await mModelUniformBottomDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Model Uniform Bottom?');
    await mModelUniformBottomDeleteDialog.clickOnConfirmButton();

    expect(await mModelUniformBottomComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
