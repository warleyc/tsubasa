/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MUserRankComponentsPage, MUserRankDeleteDialog, MUserRankUpdatePage } from './m-user-rank.page-object';

const expect = chai.expect;

describe('MUserRank e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mUserRankUpdatePage: MUserRankUpdatePage;
  let mUserRankComponentsPage: MUserRankComponentsPage;
  let mUserRankDeleteDialog: MUserRankDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MUserRanks', async () => {
    await navBarPage.goToEntity('m-user-rank');
    mUserRankComponentsPage = new MUserRankComponentsPage();
    await browser.wait(ec.visibilityOf(mUserRankComponentsPage.title), 5000);
    expect(await mUserRankComponentsPage.getTitle()).to.eq('M User Ranks');
  });

  it('should load create MUserRank page', async () => {
    await mUserRankComponentsPage.clickOnCreateButton();
    mUserRankUpdatePage = new MUserRankUpdatePage();
    expect(await mUserRankUpdatePage.getPageTitle()).to.eq('Create or edit a M User Rank');
    await mUserRankUpdatePage.cancel();
  });

  it('should create and save MUserRanks', async () => {
    const nbButtonsBeforeCreate = await mUserRankComponentsPage.countDeleteButtons();

    await mUserRankComponentsPage.clickOnCreateButton();
    await promise.all([
      mUserRankUpdatePage.setRankInput('5'),
      mUserRankUpdatePage.setExpInput('5'),
      mUserRankUpdatePage.setQuestApInput('5'),
      mUserRankUpdatePage.setMaxFriendCountInput('5'),
      mUserRankUpdatePage.setRankupSerifInput('rankupSerif'),
      mUserRankUpdatePage.setCharaAssetNameInput('charaAssetName'),
      mUserRankUpdatePage.setVoiceCharaIdInput('voiceCharaId')
    ]);
    expect(await mUserRankUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
    expect(await mUserRankUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mUserRankUpdatePage.getQuestApInput()).to.eq('5', 'Expected questAp value to be equals to 5');
    expect(await mUserRankUpdatePage.getMaxFriendCountInput()).to.eq('5', 'Expected maxFriendCount value to be equals to 5');
    expect(await mUserRankUpdatePage.getRankupSerifInput()).to.eq('rankupSerif', 'Expected RankupSerif value to be equals to rankupSerif');
    expect(await mUserRankUpdatePage.getCharaAssetNameInput()).to.eq(
      'charaAssetName',
      'Expected CharaAssetName value to be equals to charaAssetName'
    );
    expect(await mUserRankUpdatePage.getVoiceCharaIdInput()).to.eq(
      'voiceCharaId',
      'Expected VoiceCharaId value to be equals to voiceCharaId'
    );
    await mUserRankUpdatePage.save();
    expect(await mUserRankUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mUserRankComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MUserRank', async () => {
    const nbButtonsBeforeDelete = await mUserRankComponentsPage.countDeleteButtons();
    await mUserRankComponentsPage.clickOnLastDeleteButton();

    mUserRankDeleteDialog = new MUserRankDeleteDialog();
    expect(await mUserRankDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M User Rank?');
    await mUserRankDeleteDialog.clickOnConfirmButton();

    expect(await mUserRankComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
