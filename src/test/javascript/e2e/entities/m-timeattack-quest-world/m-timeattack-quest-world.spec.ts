/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTimeattackQuestWorldComponentsPage,
  MTimeattackQuestWorldDeleteDialog,
  MTimeattackQuestWorldUpdatePage
} from './m-timeattack-quest-world.page-object';

const expect = chai.expect;

describe('MTimeattackQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTimeattackQuestWorldUpdatePage: MTimeattackQuestWorldUpdatePage;
  let mTimeattackQuestWorldComponentsPage: MTimeattackQuestWorldComponentsPage;
  let mTimeattackQuestWorldDeleteDialog: MTimeattackQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTimeattackQuestWorlds', async () => {
    await navBarPage.goToEntity('m-timeattack-quest-world');
    mTimeattackQuestWorldComponentsPage = new MTimeattackQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mTimeattackQuestWorldComponentsPage.title), 5000);
    expect(await mTimeattackQuestWorldComponentsPage.getTitle()).to.eq('M Timeattack Quest Worlds');
  });

  it('should load create MTimeattackQuestWorld page', async () => {
    await mTimeattackQuestWorldComponentsPage.clickOnCreateButton();
    mTimeattackQuestWorldUpdatePage = new MTimeattackQuestWorldUpdatePage();
    expect(await mTimeattackQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Timeattack Quest World');
    await mTimeattackQuestWorldUpdatePage.cancel();
  });

  it('should create and save MTimeattackQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mTimeattackQuestWorldComponentsPage.countDeleteButtons();

    await mTimeattackQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mTimeattackQuestWorldUpdatePage.setSetIdInput('5'),
      mTimeattackQuestWorldUpdatePage.setNumberInput('5'),
      mTimeattackQuestWorldUpdatePage.setNameInput('name'),
      mTimeattackQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mTimeattackQuestWorldUpdatePage.setBackgroundImagePathInput('backgroundImagePath'),
      mTimeattackQuestWorldUpdatePage.setDescriptionInput('description'),
      mTimeattackQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mTimeattackQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mTimeattackQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mTimeattackQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mTimeattackQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mTimeattackQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mTimeattackQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mTimeattackQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mTimeattackQuestWorldUpdatePage.getImagePathInput()).to.eq(
      'imagePath',
      'Expected ImagePath value to be equals to imagePath'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getBackgroundImagePathInput()).to.eq(
      'backgroundImagePath',
      'Expected BackgroundImagePath value to be equals to backgroundImagePath'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mTimeattackQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mTimeattackQuestWorldUpdatePage.save();
    expect(await mTimeattackQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTimeattackQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTimeattackQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mTimeattackQuestWorldComponentsPage.countDeleteButtons();
    await mTimeattackQuestWorldComponentsPage.clickOnLastDeleteButton();

    mTimeattackQuestWorldDeleteDialog = new MTimeattackQuestWorldDeleteDialog();
    expect(await mTimeattackQuestWorldDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Timeattack Quest World?'
    );
    await mTimeattackQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mTimeattackQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
