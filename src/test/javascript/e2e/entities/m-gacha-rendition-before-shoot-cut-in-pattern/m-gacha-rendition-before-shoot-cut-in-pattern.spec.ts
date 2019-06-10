/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionBeforeShootCutInPatternComponentsPage,
  MGachaRenditionBeforeShootCutInPatternDeleteDialog,
  MGachaRenditionBeforeShootCutInPatternUpdatePage
} from './m-gacha-rendition-before-shoot-cut-in-pattern.page-object';

const expect = chai.expect;

describe('MGachaRenditionBeforeShootCutInPattern e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionBeforeShootCutInPatternUpdatePage: MGachaRenditionBeforeShootCutInPatternUpdatePage;
  let mGachaRenditionBeforeShootCutInPatternComponentsPage: MGachaRenditionBeforeShootCutInPatternComponentsPage;
  let mGachaRenditionBeforeShootCutInPatternDeleteDialog: MGachaRenditionBeforeShootCutInPatternDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionBeforeShootCutInPatterns', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-before-shoot-cut-in-pattern');
    mGachaRenditionBeforeShootCutInPatternComponentsPage = new MGachaRenditionBeforeShootCutInPatternComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionBeforeShootCutInPatternComponentsPage.title), 5000);
    expect(await mGachaRenditionBeforeShootCutInPatternComponentsPage.getTitle()).to.eq('M Gacha Rendition Before Shoot Cut In Patterns');
  });

  it('should load create MGachaRenditionBeforeShootCutInPattern page', async () => {
    await mGachaRenditionBeforeShootCutInPatternComponentsPage.clickOnCreateButton();
    mGachaRenditionBeforeShootCutInPatternUpdatePage = new MGachaRenditionBeforeShootCutInPatternUpdatePage();
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getPageTitle()).to.eq(
      'Create or edit a M Gacha Rendition Before Shoot Cut In Pattern'
    );
    await mGachaRenditionBeforeShootCutInPatternUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionBeforeShootCutInPatterns', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionBeforeShootCutInPatternComponentsPage.countDeleteButtons();

    await mGachaRenditionBeforeShootCutInPatternComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setIsManySsrInput('5'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setIsSsrInput('5'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setWeightInput('5'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setPatternInput('5'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setNormalPrefabNameInput('normalPrefabName'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setFlashBackPrefabName1Input('flashBackPrefabName1'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setFlashBackPrefabName2Input('flashBackPrefabName2'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setFlashBackPrefabName3Input('flashBackPrefabName3'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setFlashBackPrefabName4Input('flashBackPrefabName4'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setVoicePrefixInput('voicePrefix'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setSeNormalInput('seNormal'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setSeFlashBackInput('seFlashBack'),
      mGachaRenditionBeforeShootCutInPatternUpdatePage.setExceptKickerIdInput('5')
    ]);
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getRenditionIdInput()).to.eq(
      '5',
      'Expected renditionId value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getIsManySsrInput()).to.eq(
      '5',
      'Expected isManySsr value to be equals to 5'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getPatternInput()).to.eq('5', 'Expected pattern value to be equals to 5');
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getNormalPrefabNameInput()).to.eq(
      'normalPrefabName',
      'Expected NormalPrefabName value to be equals to normalPrefabName'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getFlashBackPrefabName1Input()).to.eq(
      'flashBackPrefabName1',
      'Expected FlashBackPrefabName1 value to be equals to flashBackPrefabName1'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getFlashBackPrefabName2Input()).to.eq(
      'flashBackPrefabName2',
      'Expected FlashBackPrefabName2 value to be equals to flashBackPrefabName2'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getFlashBackPrefabName3Input()).to.eq(
      'flashBackPrefabName3',
      'Expected FlashBackPrefabName3 value to be equals to flashBackPrefabName3'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getFlashBackPrefabName4Input()).to.eq(
      'flashBackPrefabName4',
      'Expected FlashBackPrefabName4 value to be equals to flashBackPrefabName4'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getVoicePrefixInput()).to.eq(
      'voicePrefix',
      'Expected VoicePrefix value to be equals to voicePrefix'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getSeNormalInput()).to.eq(
      'seNormal',
      'Expected SeNormal value to be equals to seNormal'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getSeFlashBackInput()).to.eq(
      'seFlashBack',
      'Expected SeFlashBack value to be equals to seFlashBack'
    );
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getExceptKickerIdInput()).to.eq(
      '5',
      'Expected exceptKickerId value to be equals to 5'
    );
    await mGachaRenditionBeforeShootCutInPatternUpdatePage.save();
    expect(await mGachaRenditionBeforeShootCutInPatternUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be
      .false;

    expect(await mGachaRenditionBeforeShootCutInPatternComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionBeforeShootCutInPattern', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionBeforeShootCutInPatternComponentsPage.countDeleteButtons();
    await mGachaRenditionBeforeShootCutInPatternComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionBeforeShootCutInPatternDeleteDialog = new MGachaRenditionBeforeShootCutInPatternDeleteDialog();
    expect(await mGachaRenditionBeforeShootCutInPatternDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Before Shoot Cut In Pattern?'
    );
    await mGachaRenditionBeforeShootCutInPatternDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionBeforeShootCutInPatternComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
