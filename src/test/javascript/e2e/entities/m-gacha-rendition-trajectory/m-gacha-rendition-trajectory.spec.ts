/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionTrajectoryComponentsPage,
  MGachaRenditionTrajectoryDeleteDialog,
  MGachaRenditionTrajectoryUpdatePage
} from './m-gacha-rendition-trajectory.page-object';

const expect = chai.expect;

describe('MGachaRenditionTrajectory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionTrajectoryUpdatePage: MGachaRenditionTrajectoryUpdatePage;
  let mGachaRenditionTrajectoryComponentsPage: MGachaRenditionTrajectoryComponentsPage;
  let mGachaRenditionTrajectoryDeleteDialog: MGachaRenditionTrajectoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionTrajectories', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-trajectory');
    mGachaRenditionTrajectoryComponentsPage = new MGachaRenditionTrajectoryComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionTrajectoryComponentsPage.title), 5000);
    expect(await mGachaRenditionTrajectoryComponentsPage.getTitle()).to.eq('M Gacha Rendition Trajectories');
  });

  it('should load create MGachaRenditionTrajectory page', async () => {
    await mGachaRenditionTrajectoryComponentsPage.clickOnCreateButton();
    mGachaRenditionTrajectoryUpdatePage = new MGachaRenditionTrajectoryUpdatePage();
    expect(await mGachaRenditionTrajectoryUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Trajectory');
    await mGachaRenditionTrajectoryUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionTrajectories', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionTrajectoryComponentsPage.countDeleteButtons();

    await mGachaRenditionTrajectoryComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionTrajectoryUpdatePage.setWeightInput('5'),
      mGachaRenditionTrajectoryUpdatePage.setTrajectoryTypeInput('5')
    ]);
    expect(await mGachaRenditionTrajectoryUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionTrajectoryUpdatePage.getTrajectoryTypeInput()).to.eq(
      '5',
      'Expected trajectoryType value to be equals to 5'
    );
    await mGachaRenditionTrajectoryUpdatePage.save();
    expect(await mGachaRenditionTrajectoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionTrajectoryComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionTrajectory', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionTrajectoryComponentsPage.countDeleteButtons();
    await mGachaRenditionTrajectoryComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionTrajectoryDeleteDialog = new MGachaRenditionTrajectoryDeleteDialog();
    expect(await mGachaRenditionTrajectoryDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Trajectory?'
    );
    await mGachaRenditionTrajectoryDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionTrajectoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
