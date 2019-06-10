/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MEventTitleEffectComponentsPage,
  MEventTitleEffectDeleteDialog,
  MEventTitleEffectUpdatePage
} from './m-event-title-effect.page-object';

const expect = chai.expect;

describe('MEventTitleEffect e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEventTitleEffectUpdatePage: MEventTitleEffectUpdatePage;
  let mEventTitleEffectComponentsPage: MEventTitleEffectComponentsPage;
  let mEventTitleEffectDeleteDialog: MEventTitleEffectDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEventTitleEffects', async () => {
    await navBarPage.goToEntity('m-event-title-effect');
    mEventTitleEffectComponentsPage = new MEventTitleEffectComponentsPage();
    await browser.wait(ec.visibilityOf(mEventTitleEffectComponentsPage.title), 5000);
    expect(await mEventTitleEffectComponentsPage.getTitle()).to.eq('M Event Title Effects');
  });

  it('should load create MEventTitleEffect page', async () => {
    await mEventTitleEffectComponentsPage.clickOnCreateButton();
    mEventTitleEffectUpdatePage = new MEventTitleEffectUpdatePage();
    expect(await mEventTitleEffectUpdatePage.getPageTitle()).to.eq('Create or edit a M Event Title Effect');
    await mEventTitleEffectUpdatePage.cancel();
  });

  it('should create and save MEventTitleEffects', async () => {
    const nbButtonsBeforeCreate = await mEventTitleEffectComponentsPage.countDeleteButtons();

    await mEventTitleEffectComponentsPage.clickOnCreateButton();
    await promise.all([
      mEventTitleEffectUpdatePage.setEffectPathInput('effectPath'),
      mEventTitleEffectUpdatePage.setBgmSoundEventInput('bgmSoundEvent'),
      mEventTitleEffectUpdatePage.setSeSoundEventInput('seSoundEvent'),
      mEventTitleEffectUpdatePage.setVoiceSoundEventSuffixesInput('voiceSoundEventSuffixes'),
      mEventTitleEffectUpdatePage.setCopyrightTextInput('copyrightText'),
      mEventTitleEffectUpdatePage.setStartAtInput('5'),
      mEventTitleEffectUpdatePage.setEndAtInput('5')
    ]);
    expect(await mEventTitleEffectUpdatePage.getEffectPathInput()).to.eq(
      'effectPath',
      'Expected EffectPath value to be equals to effectPath'
    );
    expect(await mEventTitleEffectUpdatePage.getBgmSoundEventInput()).to.eq(
      'bgmSoundEvent',
      'Expected BgmSoundEvent value to be equals to bgmSoundEvent'
    );
    expect(await mEventTitleEffectUpdatePage.getSeSoundEventInput()).to.eq(
      'seSoundEvent',
      'Expected SeSoundEvent value to be equals to seSoundEvent'
    );
    expect(await mEventTitleEffectUpdatePage.getVoiceSoundEventSuffixesInput()).to.eq(
      'voiceSoundEventSuffixes',
      'Expected VoiceSoundEventSuffixes value to be equals to voiceSoundEventSuffixes'
    );
    expect(await mEventTitleEffectUpdatePage.getCopyrightTextInput()).to.eq(
      'copyrightText',
      'Expected CopyrightText value to be equals to copyrightText'
    );
    expect(await mEventTitleEffectUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mEventTitleEffectUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mEventTitleEffectUpdatePage.save();
    expect(await mEventTitleEffectUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEventTitleEffectComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MEventTitleEffect', async () => {
    const nbButtonsBeforeDelete = await mEventTitleEffectComponentsPage.countDeleteButtons();
    await mEventTitleEffectComponentsPage.clickOnLastDeleteButton();

    mEventTitleEffectDeleteDialog = new MEventTitleEffectDeleteDialog();
    expect(await mEventTitleEffectDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Event Title Effect?');
    await mEventTitleEffectDeleteDialog.clickOnConfirmButton();

    expect(await mEventTitleEffectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
