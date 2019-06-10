/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MChallengeQuestWorldComponentsPage,
  MChallengeQuestWorldDeleteDialog,
  MChallengeQuestWorldUpdatePage
} from './m-challenge-quest-world.page-object';

const expect = chai.expect;

describe('MChallengeQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mChallengeQuestWorldUpdatePage: MChallengeQuestWorldUpdatePage;
  let mChallengeQuestWorldComponentsPage: MChallengeQuestWorldComponentsPage;
  let mChallengeQuestWorldDeleteDialog: MChallengeQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MChallengeQuestWorlds', async () => {
    await navBarPage.goToEntity('m-challenge-quest-world');
    mChallengeQuestWorldComponentsPage = new MChallengeQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mChallengeQuestWorldComponentsPage.title), 5000);
    expect(await mChallengeQuestWorldComponentsPage.getTitle()).to.eq('M Challenge Quest Worlds');
  });

  it('should load create MChallengeQuestWorld page', async () => {
    await mChallengeQuestWorldComponentsPage.clickOnCreateButton();
    mChallengeQuestWorldUpdatePage = new MChallengeQuestWorldUpdatePage();
    expect(await mChallengeQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Challenge Quest World');
    await mChallengeQuestWorldUpdatePage.cancel();
  });

  it('should create and save MChallengeQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mChallengeQuestWorldComponentsPage.countDeleteButtons();

    await mChallengeQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mChallengeQuestWorldUpdatePage.setSetIdInput('5'),
      mChallengeQuestWorldUpdatePage.setNumberInput('5'),
      mChallengeQuestWorldUpdatePage.setNameInput('name'),
      mChallengeQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mChallengeQuestWorldUpdatePage.setBackgroundImagePathInput('backgroundImagePath'),
      mChallengeQuestWorldUpdatePage.setDescriptionInput('description'),
      mChallengeQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mChallengeQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mChallengeQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mChallengeQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mChallengeQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mChallengeQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mChallengeQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mChallengeQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mChallengeQuestWorldUpdatePage.getImagePathInput()).to.eq(
      'imagePath',
      'Expected ImagePath value to be equals to imagePath'
    );
    expect(await mChallengeQuestWorldUpdatePage.getBackgroundImagePathInput()).to.eq(
      'backgroundImagePath',
      'Expected BackgroundImagePath value to be equals to backgroundImagePath'
    );
    expect(await mChallengeQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mChallengeQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mChallengeQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mChallengeQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mChallengeQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mChallengeQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mChallengeQuestWorldUpdatePage.save();
    expect(await mChallengeQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mChallengeQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MChallengeQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mChallengeQuestWorldComponentsPage.countDeleteButtons();
    await mChallengeQuestWorldComponentsPage.clickOnLastDeleteButton();

    mChallengeQuestWorldDeleteDialog = new MChallengeQuestWorldDeleteDialog();
    expect(await mChallengeQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Challenge Quest World?');
    await mChallengeQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mChallengeQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
