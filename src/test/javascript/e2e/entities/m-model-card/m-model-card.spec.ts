/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MModelCardComponentsPage, MModelCardDeleteDialog, MModelCardUpdatePage } from './m-model-card.page-object';

const expect = chai.expect;

describe('MModelCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mModelCardUpdatePage: MModelCardUpdatePage;
  let mModelCardComponentsPage: MModelCardComponentsPage;
  let mModelCardDeleteDialog: MModelCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MModelCards', async () => {
    await navBarPage.goToEntity('m-model-card');
    mModelCardComponentsPage = new MModelCardComponentsPage();
    await browser.wait(ec.visibilityOf(mModelCardComponentsPage.title), 5000);
    expect(await mModelCardComponentsPage.getTitle()).to.eq('M Model Cards');
  });

  it('should load create MModelCard page', async () => {
    await mModelCardComponentsPage.clickOnCreateButton();
    mModelCardUpdatePage = new MModelCardUpdatePage();
    expect(await mModelCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Model Card');
    await mModelCardUpdatePage.cancel();
  });

  it('should create and save MModelCards', async () => {
    const nbButtonsBeforeCreate = await mModelCardComponentsPage.countDeleteButtons();

    await mModelCardComponentsPage.clickOnCreateButton();
    await promise.all([
      mModelCardUpdatePage.setHairModelInput('5'),
      mModelCardUpdatePage.setHairTextureInput('5'),
      mModelCardUpdatePage.setHeadModelInput('5'),
      mModelCardUpdatePage.setHeadTextureInput('5'),
      mModelCardUpdatePage.setSkinTextureInput('5'),
      mModelCardUpdatePage.setShoesModelInput('5'),
      mModelCardUpdatePage.setShoesTextureInput('5'),
      mModelCardUpdatePage.setGlobeTextureInput('5'),
      mModelCardUpdatePage.setUniqueModelInput('5'),
      mModelCardUpdatePage.setUniqueTextureInput('5'),
      mModelCardUpdatePage.setDressingTypeUpInput('5'),
      mModelCardUpdatePage.setDressingTypeBottomInput('5'),
      mModelCardUpdatePage.setHeightInput('5'),
      mModelCardUpdatePage.setWidthInput('5')
    ]);
    expect(await mModelCardUpdatePage.getHairModelInput()).to.eq('5', 'Expected hairModel value to be equals to 5');
    expect(await mModelCardUpdatePage.getHairTextureInput()).to.eq('5', 'Expected hairTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getHeadModelInput()).to.eq('5', 'Expected headModel value to be equals to 5');
    expect(await mModelCardUpdatePage.getHeadTextureInput()).to.eq('5', 'Expected headTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getSkinTextureInput()).to.eq('5', 'Expected skinTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getShoesModelInput()).to.eq('5', 'Expected shoesModel value to be equals to 5');
    expect(await mModelCardUpdatePage.getShoesTextureInput()).to.eq('5', 'Expected shoesTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getGlobeTextureInput()).to.eq('5', 'Expected globeTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getUniqueModelInput()).to.eq('5', 'Expected uniqueModel value to be equals to 5');
    expect(await mModelCardUpdatePage.getUniqueTextureInput()).to.eq('5', 'Expected uniqueTexture value to be equals to 5');
    expect(await mModelCardUpdatePage.getDressingTypeUpInput()).to.eq('5', 'Expected dressingTypeUp value to be equals to 5');
    expect(await mModelCardUpdatePage.getDressingTypeBottomInput()).to.eq('5', 'Expected dressingTypeBottom value to be equals to 5');
    expect(await mModelCardUpdatePage.getHeightInput()).to.eq('5', 'Expected height value to be equals to 5');
    expect(await mModelCardUpdatePage.getWidthInput()).to.eq('5', 'Expected width value to be equals to 5');
    await mModelCardUpdatePage.save();
    expect(await mModelCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mModelCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MModelCard', async () => {
    const nbButtonsBeforeDelete = await mModelCardComponentsPage.countDeleteButtons();
    await mModelCardComponentsPage.clickOnLastDeleteButton();

    mModelCardDeleteDialog = new MModelCardDeleteDialog();
    expect(await mModelCardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Model Card?');
    await mModelCardDeleteDialog.clickOnConfirmButton();

    expect(await mModelCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
