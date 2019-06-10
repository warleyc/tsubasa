/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGachaRenditionComponentsPage, MGachaRenditionDeleteDialog, MGachaRenditionUpdatePage } from './m-gacha-rendition.page-object';

const expect = chai.expect;

describe('MGachaRendition e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionUpdatePage: MGachaRenditionUpdatePage;
  let mGachaRenditionComponentsPage: MGachaRenditionComponentsPage;
  let mGachaRenditionDeleteDialog: MGachaRenditionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditions', async () => {
    await navBarPage.goToEntity('m-gacha-rendition');
    mGachaRenditionComponentsPage = new MGachaRenditionComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionComponentsPage.title), 5000);
    expect(await mGachaRenditionComponentsPage.getTitle()).to.eq('M Gacha Renditions');
  });

  it('should load create MGachaRendition page', async () => {
    await mGachaRenditionComponentsPage.clickOnCreateButton();
    mGachaRenditionUpdatePage = new MGachaRenditionUpdatePage();
    expect(await mGachaRenditionUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition');
    await mGachaRenditionUpdatePage.cancel();
  });

  it('should create and save MGachaRenditions', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionComponentsPage.countDeleteButtons();

    await mGachaRenditionComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionUpdatePage.setMainPrefabNameInput('mainPrefabName'),
      mGachaRenditionUpdatePage.setResultExpectedUpPrefabNameInput('resultExpectedUpPrefabName'),
      mGachaRenditionUpdatePage.setResultQuestionPrefabNameInput('resultQuestionPrefabName'),
      mGachaRenditionUpdatePage.setSoundSwitchEventNameInput('soundSwitchEventName')
    ]);
    expect(await mGachaRenditionUpdatePage.getMainPrefabNameInput()).to.eq(
      'mainPrefabName',
      'Expected MainPrefabName value to be equals to mainPrefabName'
    );
    expect(await mGachaRenditionUpdatePage.getResultExpectedUpPrefabNameInput()).to.eq(
      'resultExpectedUpPrefabName',
      'Expected ResultExpectedUpPrefabName value to be equals to resultExpectedUpPrefabName'
    );
    expect(await mGachaRenditionUpdatePage.getResultQuestionPrefabNameInput()).to.eq(
      'resultQuestionPrefabName',
      'Expected ResultQuestionPrefabName value to be equals to resultQuestionPrefabName'
    );
    expect(await mGachaRenditionUpdatePage.getSoundSwitchEventNameInput()).to.eq(
      'soundSwitchEventName',
      'Expected SoundSwitchEventName value to be equals to soundSwitchEventName'
    );
    await mGachaRenditionUpdatePage.save();
    expect(await mGachaRenditionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRendition', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionComponentsPage.countDeleteButtons();
    await mGachaRenditionComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionDeleteDialog = new MGachaRenditionDeleteDialog();
    expect(await mGachaRenditionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Gacha Rendition?');
    await mGachaRenditionDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
