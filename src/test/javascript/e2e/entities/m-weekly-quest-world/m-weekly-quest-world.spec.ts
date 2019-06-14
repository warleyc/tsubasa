/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MWeeklyQuestWorldComponentsPage,
  MWeeklyQuestWorldDeleteDialog,
  MWeeklyQuestWorldUpdatePage
} from './m-weekly-quest-world.page-object';

const expect = chai.expect;

describe('MWeeklyQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mWeeklyQuestWorldUpdatePage: MWeeklyQuestWorldUpdatePage;
  let mWeeklyQuestWorldComponentsPage: MWeeklyQuestWorldComponentsPage;
  let mWeeklyQuestWorldDeleteDialog: MWeeklyQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MWeeklyQuestWorlds', async () => {
    await navBarPage.goToEntity('m-weekly-quest-world');
    mWeeklyQuestWorldComponentsPage = new MWeeklyQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mWeeklyQuestWorldComponentsPage.title), 5000);
    expect(await mWeeklyQuestWorldComponentsPage.getTitle()).to.eq('M Weekly Quest Worlds');
  });

  it('should load create MWeeklyQuestWorld page', async () => {
    await mWeeklyQuestWorldComponentsPage.clickOnCreateButton();
    mWeeklyQuestWorldUpdatePage = new MWeeklyQuestWorldUpdatePage();
    expect(await mWeeklyQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Weekly Quest World');
    await mWeeklyQuestWorldUpdatePage.cancel();
  });

  it('should create and save MWeeklyQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mWeeklyQuestWorldComponentsPage.countDeleteButtons();

    await mWeeklyQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mWeeklyQuestWorldUpdatePage.setSetIdInput('5'),
      mWeeklyQuestWorldUpdatePage.setNumberInput('5'),
      mWeeklyQuestWorldUpdatePage.setNameInput('name'),
      mWeeklyQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mWeeklyQuestWorldUpdatePage.setDescriptionInput('description'),
      mWeeklyQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mWeeklyQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mWeeklyQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mWeeklyQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mWeeklyQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mWeeklyQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mWeeklyQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mWeeklyQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mWeeklyQuestWorldUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
    expect(await mWeeklyQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mWeeklyQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mWeeklyQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mWeeklyQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mWeeklyQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mWeeklyQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mWeeklyQuestWorldUpdatePage.save();
    expect(await mWeeklyQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mWeeklyQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MWeeklyQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mWeeklyQuestWorldComponentsPage.countDeleteButtons();
    await mWeeklyQuestWorldComponentsPage.clickOnLastDeleteButton();

    mWeeklyQuestWorldDeleteDialog = new MWeeklyQuestWorldDeleteDialog();
    expect(await mWeeklyQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Weekly Quest World?');
    await mWeeklyQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mWeeklyQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
