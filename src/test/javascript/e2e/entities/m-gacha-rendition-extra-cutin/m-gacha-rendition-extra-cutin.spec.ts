/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionExtraCutinComponentsPage,
  MGachaRenditionExtraCutinDeleteDialog,
  MGachaRenditionExtraCutinUpdatePage
} from './m-gacha-rendition-extra-cutin.page-object';

const expect = chai.expect;

describe('MGachaRenditionExtraCutin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionExtraCutinUpdatePage: MGachaRenditionExtraCutinUpdatePage;
  let mGachaRenditionExtraCutinComponentsPage: MGachaRenditionExtraCutinComponentsPage;
  let mGachaRenditionExtraCutinDeleteDialog: MGachaRenditionExtraCutinDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionExtraCutins', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-extra-cutin');
    mGachaRenditionExtraCutinComponentsPage = new MGachaRenditionExtraCutinComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionExtraCutinComponentsPage.title), 5000);
    expect(await mGachaRenditionExtraCutinComponentsPage.getTitle()).to.eq('M Gacha Rendition Extra Cutins');
  });

  it('should load create MGachaRenditionExtraCutin page', async () => {
    await mGachaRenditionExtraCutinComponentsPage.clickOnCreateButton();
    mGachaRenditionExtraCutinUpdatePage = new MGachaRenditionExtraCutinUpdatePage();
    expect(await mGachaRenditionExtraCutinUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Extra Cutin');
    await mGachaRenditionExtraCutinUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionExtraCutins', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionExtraCutinComponentsPage.countDeleteButtons();

    await mGachaRenditionExtraCutinComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionExtraCutinUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionExtraCutinUpdatePage.setMainPrefabNameInput('mainPrefabName'),
      mGachaRenditionExtraCutinUpdatePage.setVoiceStartCutInInput('voiceStartCutIn'),
      mGachaRenditionExtraCutinUpdatePage.setSerifInput('serif')
    ]);
    expect(await mGachaRenditionExtraCutinUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionExtraCutinUpdatePage.getMainPrefabNameInput()).to.eq(
      'mainPrefabName',
      'Expected MainPrefabName value to be equals to mainPrefabName'
    );
    expect(await mGachaRenditionExtraCutinUpdatePage.getVoiceStartCutInInput()).to.eq(
      'voiceStartCutIn',
      'Expected VoiceStartCutIn value to be equals to voiceStartCutIn'
    );
    expect(await mGachaRenditionExtraCutinUpdatePage.getSerifInput()).to.eq('serif', 'Expected Serif value to be equals to serif');
    await mGachaRenditionExtraCutinUpdatePage.save();
    expect(await mGachaRenditionExtraCutinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionExtraCutinComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionExtraCutin', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionExtraCutinComponentsPage.countDeleteButtons();
    await mGachaRenditionExtraCutinComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionExtraCutinDeleteDialog = new MGachaRenditionExtraCutinDeleteDialog();
    expect(await mGachaRenditionExtraCutinDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Extra Cutin?'
    );
    await mGachaRenditionExtraCutinDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionExtraCutinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
