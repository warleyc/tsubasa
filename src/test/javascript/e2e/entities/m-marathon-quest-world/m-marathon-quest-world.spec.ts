/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonQuestWorldComponentsPage,
  MMarathonQuestWorldDeleteDialog,
  MMarathonQuestWorldUpdatePage
} from './m-marathon-quest-world.page-object';

const expect = chai.expect;

describe('MMarathonQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonQuestWorldUpdatePage: MMarathonQuestWorldUpdatePage;
  let mMarathonQuestWorldComponentsPage: MMarathonQuestWorldComponentsPage;
  let mMarathonQuestWorldDeleteDialog: MMarathonQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonQuestWorlds', async () => {
    await navBarPage.goToEntity('m-marathon-quest-world');
    mMarathonQuestWorldComponentsPage = new MMarathonQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonQuestWorldComponentsPage.title), 5000);
    expect(await mMarathonQuestWorldComponentsPage.getTitle()).to.eq('M Marathon Quest Worlds');
  });

  it('should load create MMarathonQuestWorld page', async () => {
    await mMarathonQuestWorldComponentsPage.clickOnCreateButton();
    mMarathonQuestWorldUpdatePage = new MMarathonQuestWorldUpdatePage();
    expect(await mMarathonQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Quest World');
    await mMarathonQuestWorldUpdatePage.cancel();
  });

  it('should create and save MMarathonQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mMarathonQuestWorldComponentsPage.countDeleteButtons();

    await mMarathonQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonQuestWorldUpdatePage.setSetIdInput('5'),
      mMarathonQuestWorldUpdatePage.setNumberInput('5'),
      mMarathonQuestWorldUpdatePage.setNameInput('name'),
      mMarathonQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mMarathonQuestWorldUpdatePage.setDescriptionInput('description'),
      mMarathonQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mMarathonQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mMarathonQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mMarathonQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mMarathonQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mMarathonQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mMarathonQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mMarathonQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mMarathonQuestWorldUpdatePage.getImagePathInput()).to.eq(
      'imagePath',
      'Expected ImagePath value to be equals to imagePath'
    );
    expect(await mMarathonQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mMarathonQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mMarathonQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mMarathonQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mMarathonQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mMarathonQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mMarathonQuestWorldUpdatePage.save();
    expect(await mMarathonQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mMarathonQuestWorldComponentsPage.countDeleteButtons();
    await mMarathonQuestWorldComponentsPage.clickOnLastDeleteButton();

    mMarathonQuestWorldDeleteDialog = new MMarathonQuestWorldDeleteDialog();
    expect(await mMarathonQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Marathon Quest World?');
    await mMarathonQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
