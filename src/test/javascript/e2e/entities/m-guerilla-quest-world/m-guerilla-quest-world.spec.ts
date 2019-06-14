/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuerillaQuestWorldComponentsPage,
  MGuerillaQuestWorldDeleteDialog,
  MGuerillaQuestWorldUpdatePage
} from './m-guerilla-quest-world.page-object';

const expect = chai.expect;

describe('MGuerillaQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuerillaQuestWorldUpdatePage: MGuerillaQuestWorldUpdatePage;
  let mGuerillaQuestWorldComponentsPage: MGuerillaQuestWorldComponentsPage;
  let mGuerillaQuestWorldDeleteDialog: MGuerillaQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuerillaQuestWorlds', async () => {
    await navBarPage.goToEntity('m-guerilla-quest-world');
    mGuerillaQuestWorldComponentsPage = new MGuerillaQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mGuerillaQuestWorldComponentsPage.title), 5000);
    expect(await mGuerillaQuestWorldComponentsPage.getTitle()).to.eq('M Guerilla Quest Worlds');
  });

  it('should load create MGuerillaQuestWorld page', async () => {
    await mGuerillaQuestWorldComponentsPage.clickOnCreateButton();
    mGuerillaQuestWorldUpdatePage = new MGuerillaQuestWorldUpdatePage();
    expect(await mGuerillaQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Guerilla Quest World');
    await mGuerillaQuestWorldUpdatePage.cancel();
  });

  it('should create and save MGuerillaQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mGuerillaQuestWorldComponentsPage.countDeleteButtons();

    await mGuerillaQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuerillaQuestWorldUpdatePage.setSetIdInput('5'),
      mGuerillaQuestWorldUpdatePage.setNumberInput('5'),
      mGuerillaQuestWorldUpdatePage.setNameInput('name'),
      mGuerillaQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mGuerillaQuestWorldUpdatePage.setDescriptionInput('description'),
      mGuerillaQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mGuerillaQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mGuerillaQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mGuerillaQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mGuerillaQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mGuerillaQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mGuerillaQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mGuerillaQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mGuerillaQuestWorldUpdatePage.getImagePathInput()).to.eq(
      'imagePath',
      'Expected ImagePath value to be equals to imagePath'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mGuerillaQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mGuerillaQuestWorldUpdatePage.save();
    expect(await mGuerillaQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuerillaQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuerillaQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mGuerillaQuestWorldComponentsPage.countDeleteButtons();
    await mGuerillaQuestWorldComponentsPage.clickOnLastDeleteButton();

    mGuerillaQuestWorldDeleteDialog = new MGuerillaQuestWorldDeleteDialog();
    expect(await mGuerillaQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guerilla Quest World?');
    await mGuerillaQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mGuerillaQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
