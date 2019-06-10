/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAreaActionWeightComponentsPage,
  MAreaActionWeightDeleteDialog,
  MAreaActionWeightUpdatePage
} from './m-area-action-weight.page-object';

const expect = chai.expect;

describe('MAreaActionWeight e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAreaActionWeightUpdatePage: MAreaActionWeightUpdatePage;
  let mAreaActionWeightComponentsPage: MAreaActionWeightComponentsPage;
  let mAreaActionWeightDeleteDialog: MAreaActionWeightDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAreaActionWeights', async () => {
    await navBarPage.goToEntity('m-area-action-weight');
    mAreaActionWeightComponentsPage = new MAreaActionWeightComponentsPage();
    await browser.wait(ec.visibilityOf(mAreaActionWeightComponentsPage.title), 5000);
    expect(await mAreaActionWeightComponentsPage.getTitle()).to.eq('M Area Action Weights');
  });

  it('should load create MAreaActionWeight page', async () => {
    await mAreaActionWeightComponentsPage.clickOnCreateButton();
    mAreaActionWeightUpdatePage = new MAreaActionWeightUpdatePage();
    expect(await mAreaActionWeightUpdatePage.getPageTitle()).to.eq('Create or edit a M Area Action Weight');
    await mAreaActionWeightUpdatePage.cancel();
  });

  it('should create and save MAreaActionWeights', async () => {
    const nbButtonsBeforeCreate = await mAreaActionWeightComponentsPage.countDeleteButtons();

    await mAreaActionWeightComponentsPage.clickOnCreateButton();
    await promise.all([
      mAreaActionWeightUpdatePage.setAreaTypeInput('5'),
      mAreaActionWeightUpdatePage.setDribbleRateInput('5'),
      mAreaActionWeightUpdatePage.setPassingRateInput('5'),
      mAreaActionWeightUpdatePage.setOnetwoRateInput('5'),
      mAreaActionWeightUpdatePage.setShootRateInput('5'),
      mAreaActionWeightUpdatePage.setVolleyShootRateInput('5'),
      mAreaActionWeightUpdatePage.setHeadingShootRateInput('5'),
      mAreaActionWeightUpdatePage.setTackleRateInput('5'),
      mAreaActionWeightUpdatePage.setBlockRateInput('5'),
      mAreaActionWeightUpdatePage.setPassCutRateInput('5'),
      mAreaActionWeightUpdatePage.setClearRateInput('5'),
      mAreaActionWeightUpdatePage.setCompeteRateInput('5'),
      mAreaActionWeightUpdatePage.setTrapRateInput('5')
    ]);
    expect(await mAreaActionWeightUpdatePage.getAreaTypeInput()).to.eq('5', 'Expected areaType value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getDribbleRateInput()).to.eq('5', 'Expected dribbleRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getPassingRateInput()).to.eq('5', 'Expected passingRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getOnetwoRateInput()).to.eq('5', 'Expected onetwoRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getShootRateInput()).to.eq('5', 'Expected shootRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getVolleyShootRateInput()).to.eq('5', 'Expected volleyShootRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getHeadingShootRateInput()).to.eq('5', 'Expected headingShootRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getTackleRateInput()).to.eq('5', 'Expected tackleRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getBlockRateInput()).to.eq('5', 'Expected blockRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getPassCutRateInput()).to.eq('5', 'Expected passCutRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getClearRateInput()).to.eq('5', 'Expected clearRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getCompeteRateInput()).to.eq('5', 'Expected competeRate value to be equals to 5');
    expect(await mAreaActionWeightUpdatePage.getTrapRateInput()).to.eq('5', 'Expected trapRate value to be equals to 5');
    await mAreaActionWeightUpdatePage.save();
    expect(await mAreaActionWeightUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAreaActionWeightComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MAreaActionWeight', async () => {
    const nbButtonsBeforeDelete = await mAreaActionWeightComponentsPage.countDeleteButtons();
    await mAreaActionWeightComponentsPage.clickOnLastDeleteButton();

    mAreaActionWeightDeleteDialog = new MAreaActionWeightDeleteDialog();
    expect(await mAreaActionWeightDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Area Action Weight?');
    await mAreaActionWeightDeleteDialog.clickOnConfirmButton();

    expect(await mAreaActionWeightComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
