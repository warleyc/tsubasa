/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTicketQuestWorldComponentsPage,
  MTicketQuestWorldDeleteDialog,
  MTicketQuestWorldUpdatePage
} from './m-ticket-quest-world.page-object';

const expect = chai.expect;

describe('MTicketQuestWorld e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTicketQuestWorldUpdatePage: MTicketQuestWorldUpdatePage;
  let mTicketQuestWorldComponentsPage: MTicketQuestWorldComponentsPage;
  let mTicketQuestWorldDeleteDialog: MTicketQuestWorldDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTicketQuestWorlds', async () => {
    await navBarPage.goToEntity('m-ticket-quest-world');
    mTicketQuestWorldComponentsPage = new MTicketQuestWorldComponentsPage();
    await browser.wait(ec.visibilityOf(mTicketQuestWorldComponentsPage.title), 5000);
    expect(await mTicketQuestWorldComponentsPage.getTitle()).to.eq('M Ticket Quest Worlds');
  });

  it('should load create MTicketQuestWorld page', async () => {
    await mTicketQuestWorldComponentsPage.clickOnCreateButton();
    mTicketQuestWorldUpdatePage = new MTicketQuestWorldUpdatePage();
    expect(await mTicketQuestWorldUpdatePage.getPageTitle()).to.eq('Create or edit a M Ticket Quest World');
    await mTicketQuestWorldUpdatePage.cancel();
  });

  it('should create and save MTicketQuestWorlds', async () => {
    const nbButtonsBeforeCreate = await mTicketQuestWorldComponentsPage.countDeleteButtons();

    await mTicketQuestWorldComponentsPage.clickOnCreateButton();
    await promise.all([
      mTicketQuestWorldUpdatePage.setSetIdInput('5'),
      mTicketQuestWorldUpdatePage.setNumberInput('5'),
      mTicketQuestWorldUpdatePage.setNameInput('name'),
      mTicketQuestWorldUpdatePage.setImagePathInput('imagePath'),
      mTicketQuestWorldUpdatePage.setDescriptionInput('description'),
      mTicketQuestWorldUpdatePage.setStageUnlockPatternInput('5'),
      mTicketQuestWorldUpdatePage.setSpecialRewardContentTypeInput('5'),
      mTicketQuestWorldUpdatePage.setSpecialRewardContentIdInput('5'),
      mTicketQuestWorldUpdatePage.setIsEnableCoopInput('5'),
      mTicketQuestWorldUpdatePage.setIsHideDoNotHavingTicketInput('5')
    ]);
    expect(await mTicketQuestWorldUpdatePage.getSetIdInput()).to.eq('5', 'Expected setId value to be equals to 5');
    expect(await mTicketQuestWorldUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await mTicketQuestWorldUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mTicketQuestWorldUpdatePage.getImagePathInput()).to.eq('imagePath', 'Expected ImagePath value to be equals to imagePath');
    expect(await mTicketQuestWorldUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mTicketQuestWorldUpdatePage.getStageUnlockPatternInput()).to.eq(
      '5',
      'Expected stageUnlockPattern value to be equals to 5'
    );
    expect(await mTicketQuestWorldUpdatePage.getSpecialRewardContentTypeInput()).to.eq(
      '5',
      'Expected specialRewardContentType value to be equals to 5'
    );
    expect(await mTicketQuestWorldUpdatePage.getSpecialRewardContentIdInput()).to.eq(
      '5',
      'Expected specialRewardContentId value to be equals to 5'
    );
    expect(await mTicketQuestWorldUpdatePage.getIsEnableCoopInput()).to.eq('5', 'Expected isEnableCoop value to be equals to 5');
    expect(await mTicketQuestWorldUpdatePage.getIsHideDoNotHavingTicketInput()).to.eq(
      '5',
      'Expected isHideDoNotHavingTicket value to be equals to 5'
    );
    await mTicketQuestWorldUpdatePage.save();
    expect(await mTicketQuestWorldUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTicketQuestWorldComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTicketQuestWorld', async () => {
    const nbButtonsBeforeDelete = await mTicketQuestWorldComponentsPage.countDeleteButtons();
    await mTicketQuestWorldComponentsPage.clickOnLastDeleteButton();

    mTicketQuestWorldDeleteDialog = new MTicketQuestWorldDeleteDialog();
    expect(await mTicketQuestWorldDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Ticket Quest World?');
    await mTicketQuestWorldDeleteDialog.clickOnConfirmButton();

    expect(await mTicketQuestWorldComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
