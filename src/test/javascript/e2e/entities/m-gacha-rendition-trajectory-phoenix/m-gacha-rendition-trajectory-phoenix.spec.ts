/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionTrajectoryPhoenixComponentsPage,
  MGachaRenditionTrajectoryPhoenixDeleteDialog,
  MGachaRenditionTrajectoryPhoenixUpdatePage
} from './m-gacha-rendition-trajectory-phoenix.page-object';

const expect = chai.expect;

describe('MGachaRenditionTrajectoryPhoenix e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionTrajectoryPhoenixUpdatePage: MGachaRenditionTrajectoryPhoenixUpdatePage;
  let mGachaRenditionTrajectoryPhoenixComponentsPage: MGachaRenditionTrajectoryPhoenixComponentsPage;
  let mGachaRenditionTrajectoryPhoenixDeleteDialog: MGachaRenditionTrajectoryPhoenixDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionTrajectoryPhoenixes', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-trajectory-phoenix');
    mGachaRenditionTrajectoryPhoenixComponentsPage = new MGachaRenditionTrajectoryPhoenixComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionTrajectoryPhoenixComponentsPage.title), 5000);
    expect(await mGachaRenditionTrajectoryPhoenixComponentsPage.getTitle()).to.eq('M Gacha Rendition Trajectory Phoenixes');
  });

  it('should load create MGachaRenditionTrajectoryPhoenix page', async () => {
    await mGachaRenditionTrajectoryPhoenixComponentsPage.clickOnCreateButton();
    mGachaRenditionTrajectoryPhoenixUpdatePage = new MGachaRenditionTrajectoryPhoenixUpdatePage();
    expect(await mGachaRenditionTrajectoryPhoenixUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Trajectory Phoenix');
    await mGachaRenditionTrajectoryPhoenixUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionTrajectoryPhoenixes', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionTrajectoryPhoenixComponentsPage.countDeleteButtons();

    await mGachaRenditionTrajectoryPhoenixComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionTrajectoryPhoenixUpdatePage.setIsPhoenixInput('5'),
      mGachaRenditionTrajectoryPhoenixUpdatePage.setWeightInput('5')
    ]);
    expect(await mGachaRenditionTrajectoryPhoenixUpdatePage.getIsPhoenixInput()).to.eq('5', 'Expected isPhoenix value to be equals to 5');
    expect(await mGachaRenditionTrajectoryPhoenixUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    await mGachaRenditionTrajectoryPhoenixUpdatePage.save();
    expect(await mGachaRenditionTrajectoryPhoenixUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionTrajectoryPhoenixComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionTrajectoryPhoenix', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionTrajectoryPhoenixComponentsPage.countDeleteButtons();
    await mGachaRenditionTrajectoryPhoenixComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionTrajectoryPhoenixDeleteDialog = new MGachaRenditionTrajectoryPhoenixDeleteDialog();
    expect(await mGachaRenditionTrajectoryPhoenixDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Trajectory Phoenix?'
    );
    await mGachaRenditionTrajectoryPhoenixDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionTrajectoryPhoenixComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
