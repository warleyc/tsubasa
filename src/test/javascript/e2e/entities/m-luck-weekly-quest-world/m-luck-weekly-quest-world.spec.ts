/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLuckWeeklyQuestWorldComponentsPage,
  MLuckWeeklyQuestWorldDeleteDialog,
  MLuckWeeklyQuestWorldUpdatePage
} from './m-luck-weekly-quest-world.page-object';

const expect = chai.expect;

describe('MLuckWeeklyQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLuckWeeklyQuestWorldUpdatePage: MLuckWeeklyQuestWorldUpdatePage;
  let mLuckWeeklyQuestWorldComponentsPage: MLuckWeeklyQuestWorldComponentsPage;
  let mLuckWeeklyQuestWorldDeleteDialog: MLuckWeeklyQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLuckWeeklyQuestWorlds', async () => {
    await navBarPage.goToEntity('m-luck-weekly-quest-world');
    mLuckWeeklyQuestWorldComponentsPage = new MLuckWeeklyQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mLuckWeeklyQuestWorldComponentsPage.title), 5000);
    expect(await mLuckWeeklyQuestWorldComponentsPage.getTitle()).to.eq('M Luck Weekly Quest Worlds');
  });

  it('should load create MLuckWeeklyQuestWorld page', async () => {
    await mLuckWeeklyQuestWorldComponentsPage.clickOnCreateButton();
    mLuckWeeklyQuestWorldUpdatePage = new MLuckWeeklyQuestWorldUpdatePage();
    expect(await mLuckWeeklyQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Luck Weekly Quest World');
    await mLuckWeeklyQuestWorldUpdatePage.cancel();
  });

  it('should create and save MLuckWeeklyQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mLuckWeeklyQuestWorldComponentsPage.countDeleteButtons();

    await mLuckWeeklyQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mLuckWeeklyQuestWorldUpdatePage.setSetIdInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setNumberInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setNameInput('name'),
      mLuckWeeklyQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mLuckWeeklyQuestWorldUpdatePage.setDescriptionInput('description'),
      mLuckWeeklyQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mLuckWeeklyQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setIsEnableCoopInput('5'),
      mLuckWeeklyQuestWorldUpdatePage.setClearLimitInput('5')
    ]);
    expect(await mLuckWeeklyQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mLuckWeeklyQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mLuckWeeklyQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mLuckWeeklyQuestWorldUpdatePage.getImagePathInput()).to.eq(
      'imagePath',
      'Expected ImagePath value to be equals to imagePath'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mLuckWeeklyQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    expect(await mLuckWeeklyQuestWorldUpdatePage.getClearLimitInput()).to.eq('5', 'Expected clearLimit value to be equals to 5');
    await mLuckWeeklyQuestWorldUpdatePage.save();
    expect(await mLuckWeeklyQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLuckWeeklyQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLuckWeeklyQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mLuckWeeklyQuestWorldComponentsPage.countDeleteButtons();
    await mLuckWeeklyQuestWorldComponentsPage.clickOnLastDeleteButton();

    mLuckWeeklyQuestWorldDeleteDialog = new MLuckWeeklyQuestWorldDeleteDialog();
    expect(await mLuckWeeklyQuestWorldDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Luck Weekly Quest World?'
    );
    await mLuckWeeklyQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mLuckWeeklyQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
