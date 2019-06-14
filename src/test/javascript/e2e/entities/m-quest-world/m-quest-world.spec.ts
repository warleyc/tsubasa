/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MQuestWorldComponentsPage, MQuestWorldDeleteDialog, MQuestWorldUpdatePage } from './m-quest-world.page-object';

const expect = chai.expect;

describe('MQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestWorldUpdatePage: MQuestWorldUpdatePage;
  let mQuestWorldComponentsPage: MQuestWorldComponentsPage;
  let mQuestWorldDeleteDialog: MQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestWorlds', async () => {
    await navBarPage.goToEntity('m-quest-world');
    mQuestWorldComponentsPage = new MQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestWorldComponentsPage.title), 5000);
    expect(await mQuestWorldComponentsPage.getTitle()).to.eq('M Quest Worlds');
  });

  it('should load create MQuestWorld page', async () => {
    await mQuestWorldComponentsPage.clickOnCreateButton();
    mQuestWorldUpdatePage = new MQuestWorldUpdatePage();
    expect(await mQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest World');
    await mQuestWorldUpdatePage.cancel();
  });

  it('should create and save MQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mQuestWorldComponentsPage.countDeleteButtons();

    await mQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestWorldUpdatePage.setNumberInput('5'),
      mQuestWorldUpdatePage.setNameInput('name'),
      mQuestWorldUpdatePage.setStartAtInput('5'),
      mQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mQuestWorldUpdatePage.setBackgroundImagePathInput('backgroundImagePath'),
      mQuestWorldUpdatePage.setDescriptionInput('description'),
      mQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mQuestWorldUpdatePage.setIsEnableCoopInput('5')
    ]);
    expect(await mQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mQuestWorldUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mQuestWorldUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
    expect(await mQuestWorldUpdatePage.getBackgroundImagePathInput()).to.eq(
      'backgroundImagePath',
      'Expected BackgroundImagePath value to be equals to backgroundImagePath'
    );
    expect(await mQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq('5', 'Expected stageUnlockPattern value to be equals to 5');
    expect(await mQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    await mQuestWorldUpdatePage.save();
    expect(await mQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mQuestWorldComponentsPage.countDeleteButtons();
    await mQuestWorldComponentsPage.clickOnLastDeleteButton();

    mQuestWorldDeleteDialog = new MQuestWorldDeleteDialog();
    expect(await mQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest World?');
    await mQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
