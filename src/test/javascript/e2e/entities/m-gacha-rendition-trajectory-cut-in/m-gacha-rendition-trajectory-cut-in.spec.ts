/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionTrajectoryCutInComponentsPage,
  MGachaRenditionTrajectoryCutInDeleteDialog,
  MGachaRenditionTrajectoryCutInUpdatePage
} from './m-gacha-rendition-trajectory-cut-in.page-object';

const expect = chai.expect;

describe('MGachaRenditionTrajectoryCutIn e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionTrajectoryCutInUpdatePage: MGachaRenditionTrajectoryCutInUpdatePage;
  let mGachaRenditionTrajectoryCutInComponentsPage: MGachaRenditionTrajectoryCutInComponentsPage;
  let mGachaRenditionTrajectoryCutInDeleteDialog: MGachaRenditionTrajectoryCutInDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionTrajectoryCutIns', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-trajectory-cut-in');
    mGachaRenditionTrajectoryCutInComponentsPage = new MGachaRenditionTrajectoryCutInComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionTrajectoryCutInComponentsPage.title), 5000);
    expect(await mGachaRenditionTrajectoryCutInComponentsPage.getTitle()).to.eq('M Gacha Rendition Trajectory Cut Ins');
  });

  it('should load create MGachaRenditionTrajectoryCutIn page', async () => {
    await mGachaRenditionTrajectoryCutInComponentsPage.clickOnCreateButton();
    mGachaRenditionTrajectoryCutInUpdatePage = new MGachaRenditionTrajectoryCutInUpdatePage();
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Trajectory Cut In');
    await mGachaRenditionTrajectoryCutInUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionTrajectoryCutIns', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionTrajectoryCutInComponentsPage.countDeleteButtons();

    await mGachaRenditionTrajectoryCutInComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionTrajectoryCutInUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionTrajectoryCutInUpdatePage.setTrajectoryTypeInput('5'),
      mGachaRenditionTrajectoryCutInUpdatePage.setSpriteNameInput('spriteName'),
      mGachaRenditionTrajectoryCutInUpdatePage.setWeightInput('5'),
      mGachaRenditionTrajectoryCutInUpdatePage.setVoiceInput('voice'),
      mGachaRenditionTrajectoryCutInUpdatePage.setExceptKickerIdInput('5')
    ]);
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getTrajectoryTypeInput()).to.eq(
      '5',
      'Expected trajectoryType value to be equals to 5'
    );
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getSpriteNameInput()).to.eq(
      'spriteName',
      'Expected SpriteName value to be equals to spriteName'
    );
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getVoiceInput()).to.eq('voice', 'Expected Voice value to be equals to voice');
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getExceptKickerIdInput()).to.eq(
      '5',
      'Expected exceptKickerId value to be equals to 5'
    );
    await mGachaRenditionTrajectoryCutInUpdatePage.save();
    expect(await mGachaRenditionTrajectoryCutInUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionTrajectoryCutInComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionTrajectoryCutIn', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionTrajectoryCutInComponentsPage.countDeleteButtons();
    await mGachaRenditionTrajectoryCutInComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionTrajectoryCutInDeleteDialog = new MGachaRenditionTrajectoryCutInDeleteDialog();
    expect(await mGachaRenditionTrajectoryCutInDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Trajectory Cut In?'
    );
    await mGachaRenditionTrajectoryCutInDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionTrajectoryCutInComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
