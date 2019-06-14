/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLoginBonusRoundComponentsPage,
  MLoginBonusRoundDeleteDialog,
  MLoginBonusRoundUpdatePage
} from './m-login-bonus-round.page-object';

const expect = chai.expect;

describe('MLoginBonusRound e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLoginBonusRoundUpdatePage: MLoginBonusRoundUpdatePage;
  let mLoginBonusRoundComponentsPage: MLoginBonusRoundComponentsPage;
  let mLoginBonusRoundDeleteDialog: MLoginBonusRoundDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLoginBonusRounds', async () => {
    await navBarPage.goToEntity('m-login-bonus-round');
    mLoginBonusRoundComponentsPage = new MLoginBonusRoundComponentsPage();
    await browser.wait(ec.visibilityOf(mLoginBonusRoundComponentsPage.title), 5000);
    expect(await mLoginBonusRoundComponentsPage.getTitle()).to.eq('M Login Bonus Rounds');
  });

  it('should load create MLoginBonusRound page', async () => {
    await mLoginBonusRoundComponentsPage.clickOnCreateButton();
    mLoginBonusRoundUpdatePage = new MLoginBonusRoundUpdatePage();
    expect(await mLoginBonusRoundUpdatePage.getPageTitle()).to.eq('Create or edit a M Login Bonus Round');
    await mLoginBonusRoundUpdatePage.cancel();
  });

  it('should create and save MLoginBonusRounds', async () => {
    const nbButtonsBeforeCreate = await mLoginBonusRoundComponentsPage.countDeleteButtons();

    await mLoginBonusRoundComponentsPage.clickOnCreateButton();
    await promise.all([
      mLoginBonusRoundUpdatePage.setRoundIdInput('5'),
      mLoginBonusRoundUpdatePage.setBonusTypeInput('5'),
      mLoginBonusRoundUpdatePage.setStartAtInput('5'),
      mLoginBonusRoundUpdatePage.setEndAtInput('5'),
      mLoginBonusRoundUpdatePage.setSerifSanaeInput('serifSanae'),
      mLoginBonusRoundUpdatePage.setSerifYayoiInput('serifYayoi'),
      mLoginBonusRoundUpdatePage.setSerifYoshikoInput('serifYoshiko'),
      mLoginBonusRoundUpdatePage.setSerifMakiInput('serifMaki'),
      mLoginBonusRoundUpdatePage.setSanaeImageInput('sanaeImage'),
      mLoginBonusRoundUpdatePage.setYayoiImageInput('yayoiImage'),
      mLoginBonusRoundUpdatePage.setYoshikoImageInput('yoshikoImage'),
      mLoginBonusRoundUpdatePage.setMakiImageInput('makiImage'),
      mLoginBonusRoundUpdatePage.setSoundSwitchEventNameInput('soundSwitchEventName'),
      mLoginBonusRoundUpdatePage.setNextIdInput('5'),
      mLoginBonusRoundUpdatePage.setLastDayAppealCommentInput('lastDayAppealComment'),
      mLoginBonusRoundUpdatePage.setBackgroundImageInput('backgroundImage')
    ]);
    expect(await mLoginBonusRoundUpdatePage.getRoundIdInput()).to.eq('5', 'Expected roundId value to be equals to 5');
    expect(await mLoginBonusRoundUpdatePage.getBonusTypeInput()).to.eq('5', 'Expected bonusType value to be equals to 5');
    expect(await mLoginBonusRoundUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mLoginBonusRoundUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    expect(await mLoginBonusRoundUpdatePage.getSerifSanaeInput()).to.eq(
      'serifSanae',
      'Expected SerifSanae value to be equals to serifSanae'
    );
    expect(await mLoginBonusRoundUpdatePage.getSerifYayoiInput()).to.eq(
      'serifYayoi',
      'Expected SerifYayoi value to be equals to serifYayoi'
    );
    expect(await mLoginBonusRoundUpdatePage.getSerifYoshikoInput()).to.eq(
      'serifYoshiko',
      'Expected SerifYoshiko value to be equals to serifYoshiko'
    );
    expect(await mLoginBonusRoundUpdatePage.getSerifMakiInput()).to.eq('serifMaki', 'Expected SerifMaki value to be equals to serifMaki');
    expect(await mLoginBonusRoundUpdatePage.getSanaeImageInput()).to.eq(
      'sanaeImage',
      'Expected SanaeImage value to be equals to sanaeImage'
    );
    expect(await mLoginBonusRoundUpdatePage.getYayoiImageInput()).to.eq(
      'yayoiImage',
      'Expected YayoiImage value to be equals to yayoiImage'
    );
    expect(await mLoginBonusRoundUpdatePage.getYoshikoImageInput()).to.eq(
      'yoshikoImage',
      'Expected YoshikoImage value to be equals to yoshikoImage'
    );
    expect(await mLoginBonusRoundUpdatePage.getMakiImageInput()).to.eq('makiImage', 'Expected MakiImage value to be equals to makiImage');
    expect(await mLoginBonusRoundUpdatePage.getSoundSwitchEventNameInput()).to.eq(
      'soundSwitchEventName',
      'Expected SoundSwitchEventName value to be equals to soundSwitchEventName'
    );
    expect(await mLoginBonusRoundUpdatePage.getNextIdInput()).to.eq('5', 'Expected nextId value to be equals to 5');
    expect(await mLoginBonusRoundUpdatePage.getLastDayAppealCommentInput()).to.eq(
      'lastDayAppealComment',
      'Expected LastDayAppealComment value to be equals to lastDayAppealComment'
    );
    expect(await mLoginBonusRoundUpdatePage.getBackgroundImageInput()).to.eq(
      'backgroundImage',
      'Expected BackgroundImage value to be equals to backgroundImage'
    );
    await mLoginBonusRoundUpdatePage.save();
    expect(await mLoginBonusRoundUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLoginBonusRoundComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLoginBonusRound', async () => {
    const nbButtonsBeforeDelete = await mLoginBonusRoundComponentsPage.countDeleteButtons();
    await mLoginBonusRoundComponentsPage.clickOnLastDeleteButton();

    mLoginBonusRoundDeleteDialog = new MLoginBonusRoundDeleteDialog();
    expect(await mLoginBonusRoundDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Login Bonus Round?');
    await mLoginBonusRoundDeleteDialog.clickOnConfirmButton();

    expect(await mLoginBonusRoundComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
