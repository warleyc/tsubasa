/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionBallComponentsPage,
  MGachaRenditionBallDeleteDialog,
  MGachaRenditionBallUpdatePage
} from './m-gacha-rendition-ball.page-object';

const expect = chai.expect;

describe('MGachaRenditionBall e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionBallUpdatePage: MGachaRenditionBallUpdatePage;
  let mGachaRenditionBallComponentsPage: MGachaRenditionBallComponentsPage;
  let mGachaRenditionBallDeleteDialog: MGachaRenditionBallDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionBalls', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-ball');
    mGachaRenditionBallComponentsPage = new MGachaRenditionBallComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionBallComponentsPage.title), 5000);
    expect(await mGachaRenditionBallComponentsPage.getTitle()).to.eq('M Gacha Rendition Balls');
  });

  it('should load create MGachaRenditionBall page', async () => {
    await mGachaRenditionBallComponentsPage.clickOnCreateButton();
    mGachaRenditionBallUpdatePage = new MGachaRenditionBallUpdatePage();
    expect(await mGachaRenditionBallUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Ball');
    await mGachaRenditionBallUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionBalls', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionBallComponentsPage.countDeleteButtons();

    await mGachaRenditionBallComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionBallUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionBallUpdatePage.setIsSsrInput('5'),
      mGachaRenditionBallUpdatePage.setWeightInput('5'),
      mGachaRenditionBallUpdatePage.setBallTextureNameInput('ballTextureName'),
      mGachaRenditionBallUpdatePage.setTrajectoryNormalTextureNameInput('trajectoryNormalTextureName'),
      mGachaRenditionBallUpdatePage.setTrajectoryGoldTextureNameInput('trajectoryGoldTextureName'),
      mGachaRenditionBallUpdatePage.setTrajectoryRainbowTextureNameInput('trajectoryRainbowTextureName')
    ]);
    expect(await mGachaRenditionBallUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionBallUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionBallUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionBallUpdatePage.getBallTextureNameInput()).to.eq(
      'ballTextureName',
      'Expected BallTextureName value to be equals to ballTextureName'
    );
    expect(await mGachaRenditionBallUpdatePage.getTrajectoryNormalTextureNameInput()).to.eq(
      'trajectoryNormalTextureName',
      'Expected TrajectoryNormalTextureName value to be equals to trajectoryNormalTextureName'
    );
    expect(await mGachaRenditionBallUpdatePage.getTrajectoryGoldTextureNameInput()).to.eq(
      'trajectoryGoldTextureName',
      'Expected TrajectoryGoldTextureName value to be equals to trajectoryGoldTextureName'
    );
    expect(await mGachaRenditionBallUpdatePage.getTrajectoryRainbowTextureNameInput()).to.eq(
      'trajectoryRainbowTextureName',
      'Expected TrajectoryRainbowTextureName value to be equals to trajectoryRainbowTextureName'
    );
    await mGachaRenditionBallUpdatePage.save();
    expect(await mGachaRenditionBallUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionBallComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionBall', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionBallComponentsPage.countDeleteButtons();
    await mGachaRenditionBallComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionBallDeleteDialog = new MGachaRenditionBallDeleteDialog();
    expect(await mGachaRenditionBallDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Gacha Rendition Ball?');
    await mGachaRenditionBallDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionBallComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
