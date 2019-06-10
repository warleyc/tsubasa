/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGachaRenditionKickerComponentsPage,
  MGachaRenditionKickerDeleteDialog,
  MGachaRenditionKickerUpdatePage
} from './m-gacha-rendition-kicker.page-object';

const expect = chai.expect;

describe('MGachaRenditionKicker e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGachaRenditionKickerUpdatePage: MGachaRenditionKickerUpdatePage;
  let mGachaRenditionKickerComponentsPage: MGachaRenditionKickerComponentsPage;
  let mGachaRenditionKickerDeleteDialog: MGachaRenditionKickerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGachaRenditionKickers', async () => {
    await navBarPage.goToEntity('m-gacha-rendition-kicker');
    mGachaRenditionKickerComponentsPage = new MGachaRenditionKickerComponentsPage();
    await browser.wait(ec.visibilityOf(mGachaRenditionKickerComponentsPage.title), 5000);
    expect(await mGachaRenditionKickerComponentsPage.getTitle()).to.eq('M Gacha Rendition Kickers');
  });

  it('should load create MGachaRenditionKicker page', async () => {
    await mGachaRenditionKickerComponentsPage.clickOnCreateButton();
    mGachaRenditionKickerUpdatePage = new MGachaRenditionKickerUpdatePage();
    expect(await mGachaRenditionKickerUpdatePage.getPageTitle()).to.eq('Create or edit a M Gacha Rendition Kicker');
    await mGachaRenditionKickerUpdatePage.cancel();
  });

  it('should create and save MGachaRenditionKickers', async () => {
    const nbButtonsBeforeCreate = await mGachaRenditionKickerComponentsPage.countDeleteButtons();

    await mGachaRenditionKickerComponentsPage.clickOnCreateButton();
    await promise.all([
      mGachaRenditionKickerUpdatePage.setRenditionIdInput('5'),
      mGachaRenditionKickerUpdatePage.setIsManySsrInput('5'),
      mGachaRenditionKickerUpdatePage.setIsSsrInput('5'),
      mGachaRenditionKickerUpdatePage.setWeightInput('5'),
      mGachaRenditionKickerUpdatePage.setLeftModelIdInput('5'),
      mGachaRenditionKickerUpdatePage.setLeftUniformUpIdInput('5'),
      mGachaRenditionKickerUpdatePage.setLeftUniformBottomIdInput('5'),
      mGachaRenditionKickerUpdatePage.setLeftUniformNumberInput('5'),
      mGachaRenditionKickerUpdatePage.setRightModelIdInput('5'),
      mGachaRenditionKickerUpdatePage.setRightUniformUpIdInput('5'),
      mGachaRenditionKickerUpdatePage.setRightUniformBottomIdInput('5'),
      mGachaRenditionKickerUpdatePage.setRightUniformNumberInput('5'),
      mGachaRenditionKickerUpdatePage.setCutInSpriteNameInput('cutInSpriteName'),
      mGachaRenditionKickerUpdatePage.setLeftMessageInput('leftMessage'),
      mGachaRenditionKickerUpdatePage.setRightMessageInput('rightMessage'),
      mGachaRenditionKickerUpdatePage.setVoiceTriggerInput('voiceTrigger'),
      mGachaRenditionKickerUpdatePage.setVoiceStartCutInInput('voiceStartCutIn'),
      mGachaRenditionKickerUpdatePage.setKickerIdInput('5')
    ]);
    expect(await mGachaRenditionKickerUpdatePage.getRenditionIdInput()).to.eq('5', 'Expected renditionId value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getIsManySsrInput()).to.eq('5', 'Expected isManySsr value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getIsSsrInput()).to.eq('5', 'Expected isSsr value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getLeftModelIdInput()).to.eq('5', 'Expected leftModelId value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getLeftUniformUpIdInput()).to.eq('5', 'Expected leftUniformUpId value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getLeftUniformBottomIdInput()).to.eq(
      '5',
      'Expected leftUniformBottomId value to be equals to 5'
    );
    expect(await mGachaRenditionKickerUpdatePage.getLeftUniformNumberInput()).to.eq(
      '5',
      'Expected leftUniformNumber value to be equals to 5'
    );
    expect(await mGachaRenditionKickerUpdatePage.getRightModelIdInput()).to.eq('5', 'Expected rightModelId value to be equals to 5');
    expect(await mGachaRenditionKickerUpdatePage.getRightUniformUpIdInput()).to.eq(
      '5',
      'Expected rightUniformUpId value to be equals to 5'
    );
    expect(await mGachaRenditionKickerUpdatePage.getRightUniformBottomIdInput()).to.eq(
      '5',
      'Expected rightUniformBottomId value to be equals to 5'
    );
    expect(await mGachaRenditionKickerUpdatePage.getRightUniformNumberInput()).to.eq(
      '5',
      'Expected rightUniformNumber value to be equals to 5'
    );
    expect(await mGachaRenditionKickerUpdatePage.getCutInSpriteNameInput()).to.eq(
      'cutInSpriteName',
      'Expected CutInSpriteName value to be equals to cutInSpriteName'
    );
    expect(await mGachaRenditionKickerUpdatePage.getLeftMessageInput()).to.eq(
      'leftMessage',
      'Expected LeftMessage value to be equals to leftMessage'
    );
    expect(await mGachaRenditionKickerUpdatePage.getRightMessageInput()).to.eq(
      'rightMessage',
      'Expected RightMessage value to be equals to rightMessage'
    );
    expect(await mGachaRenditionKickerUpdatePage.getVoiceTriggerInput()).to.eq(
      'voiceTrigger',
      'Expected VoiceTrigger value to be equals to voiceTrigger'
    );
    expect(await mGachaRenditionKickerUpdatePage.getVoiceStartCutInInput()).to.eq(
      'voiceStartCutIn',
      'Expected VoiceStartCutIn value to be equals to voiceStartCutIn'
    );
    expect(await mGachaRenditionKickerUpdatePage.getKickerIdInput()).to.eq('5', 'Expected kickerId value to be equals to 5');
    await mGachaRenditionKickerUpdatePage.save();
    expect(await mGachaRenditionKickerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGachaRenditionKickerComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGachaRenditionKicker', async () => {
    const nbButtonsBeforeDelete = await mGachaRenditionKickerComponentsPage.countDeleteButtons();
    await mGachaRenditionKickerComponentsPage.clickOnLastDeleteButton();

    mGachaRenditionKickerDeleteDialog = new MGachaRenditionKickerDeleteDialog();
    expect(await mGachaRenditionKickerDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Gacha Rendition Kicker?'
    );
    await mGachaRenditionKickerDeleteDialog.clickOnConfirmButton();

    expect(await mGachaRenditionKickerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
