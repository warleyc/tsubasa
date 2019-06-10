/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAdventQuestWorldComponentsPage,
  MAdventQuestWorldDeleteDialog,
  MAdventQuestWorldUpdatePage
} from './m-advent-quest-world.page-object';

const expect = chai.expect;

describe('MAdventQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAdventQuestWorldUpdatePage: MAdventQuestWorldUpdatePage;
  let mAdventQuestWorldComponentsPage: MAdventQuestWorldComponentsPage;
  let mAdventQuestWorldDeleteDialog: MAdventQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAdventQuestWorlds', async () => {
    await navBarPage.goToEntity('m-advent-quest-world');
    mAdventQuestWorldComponentsPage = new MAdventQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mAdventQuestWorldComponentsPage.title), 5000);
    expect(await mAdventQuestWorldComponentsPage.getTitle()).to.eq('M Advent Quest Worlds');
  });

  it('should load create MAdventQuestWorld page', async () => {
    await mAdventQuestWorldComponentsPage.clickOnCreateButton();
    mAdventQuestWorldUpdatePage = new MAdventQuestWorldUpdatePage();
    expect(await mAdventQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Advent Quest World');
    await mAdventQuestWorldUpdatePage.cancel();
  });

  it('should create and save MAdventQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mAdventQuestWorldComponentsPage.countDeleteButtons();

    await mAdventQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mAdventQuestWorldUpdatePage.setSetIdInput('5'),
      mAdventQuestWorldUpdatePage.setNumberInput('5'),
      mAdventQuestWorldUpdatePage.setNameInput('name'),
      mAdventQuestWorldUpdatePage.setSymbolTypeInput('5'),
      mAdventQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mAdventQuestWorldUpdatePage.setDescriptionInput('description'),
      mAdventQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mAdventQuestWorldUpdatePage.setArousalBannerInput('arousalBanner'),
      mAdventQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mAdventQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mAdventQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mAdventQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mAdventQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mAdventQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mAdventQuestWorldUpdatePage.getSymbolTypeInput()).to.eq('5', 'Expected symbolType value to be equals to 5');
    expect(await mAdventQuestWorldUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
    expect(await mAdventQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mAdventQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mAdventQuestWorldUpdatePage.getArousalBannerInput()).to.eq(
      'arousalBanner',
      'Expected ArousalBanner value to be equals to arousalBanner'
    );
    expect(await mAdventQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mAdventQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mAdventQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mAdventQuestWorldUpdatePage.save();
    expect(await mAdventQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAdventQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MAdventQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mAdventQuestWorldComponentsPage.countDeleteButtons();
    await mAdventQuestWorldComponentsPage.clickOnLastDeleteButton();

    mAdventQuestWorldDeleteDialog = new MAdventQuestWorldDeleteDialog();
    expect(await mAdventQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Advent Quest World?');
    await mAdventQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mAdventQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
