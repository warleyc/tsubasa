/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPvpWaveComponentsPage, MPvpWaveDeleteDialog, MPvpWaveUpdatePage } from './m-pvp-wave.page-object';

const expect = chai.expect;

describe('MPvpWave e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpWaveUpdatePage: MPvpWaveUpdatePage;
  let mPvpWaveComponentsPage: MPvpWaveComponentsPage;
  let mPvpWaveDeleteDialog: MPvpWaveDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpWaves', async () => {
    await navBarPage.goToEntity('m-pvp-wave');
    mPvpWaveComponentsPage = new MPvpWaveComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpWaveComponentsPage.title), 5000);
    expect(await mPvpWaveComponentsPage.getTitle()).to.eq('M Pvp Waves');
  });

  it('should load create MPvpWave page', async () => {
    await mPvpWaveComponentsPage.clickOnCreateButton();
    mPvpWaveUpdatePage = new MPvpWaveUpdatePage();
    expect(await mPvpWaveUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Wave');
    await mPvpWaveUpdatePage.cancel();
  });

  it('should create and save MPvpWaves', async () => {
    const nbButtonsBeforeCreate = await mPvpWaveComponentsPage.countDeleteButtons();

    await mPvpWaveComponentsPage.clickOnCreateButton();
    await promise.all([
      mPvpWaveUpdatePage.setStartAtInput('5'),
      mPvpWaveUpdatePage.setEndAtInput('5'),
      mPvpWaveUpdatePage.setIsRankingInput('5')
    ]);
    expect(await mPvpWaveUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mPvpWaveUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    expect(await mPvpWaveUpdatePage.getIsRankingInput()).to.eq('5', 'Expected isRanking value to be equals to 5');
    await mPvpWaveUpdatePage.save();
    expect(await mPvpWaveUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPvpWaveComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MPvpWave', async () => {
    const nbButtonsBeforeDelete = await mPvpWaveComponentsPage.countDeleteButtons();
    await mPvpWaveComponentsPage.clickOnLastDeleteButton();

    mPvpWaveDeleteDialog = new MPvpWaveDeleteDialog();
    expect(await mPvpWaveDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Pvp Wave?');
    await mPvpWaveDeleteDialog.clickOnConfirmButton();

    expect(await mPvpWaveComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
