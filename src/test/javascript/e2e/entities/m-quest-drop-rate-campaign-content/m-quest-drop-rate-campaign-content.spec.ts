/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestDropRateCampaignContentComponentsPage,
  MQuestDropRateCampaignContentDeleteDialog,
  MQuestDropRateCampaignContentUpdatePage
} from './m-quest-drop-rate-campaign-content.page-object';

const expect = chai.expect;

describe('MQuestDropRateCampaignContent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestDropRateCampaignContentUpdatePage: MQuestDropRateCampaignContentUpdatePage;
  let mQuestDropRateCampaignContentComponentsPage: MQuestDropRateCampaignContentComponentsPage;
  let mQuestDropRateCampaignContentDeleteDialog: MQuestDropRateCampaignContentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestDropRateCampaignContents', async () => {
    await navBarPage.goToEntity('m-quest-drop-rate-campaign-content');
    mQuestDropRateCampaignContentComponentsPage = new MQuestDropRateCampaignContentComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestDropRateCampaignContentComponentsPage.title), 5000);
    expect(await mQuestDropRateCampaignContentComponentsPage.getTitle()).to.eq('M Quest Drop Rate Campaign Contents');
  });

  it('should load create MQuestDropRateCampaignContent page', async () => {
    await mQuestDropRateCampaignContentComponentsPage.clickOnCreateButton();
    mQuestDropRateCampaignContentUpdatePage = new MQuestDropRateCampaignContentUpdatePage();
    expect(await mQuestDropRateCampaignContentUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Drop Rate Campaign Content');
    await mQuestDropRateCampaignContentUpdatePage.cancel();
  });

  it('should create and save MQuestDropRateCampaignContents', async () => {
    const nbButtonsBeforeCreate = await mQuestDropRateCampaignContentComponentsPage.countDeleteButtons();

    await mQuestDropRateCampaignContentComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestDropRateCampaignContentUpdatePage.setGroupIdInput('5'),
      mQuestDropRateCampaignContentUpdatePage.setContentTypeInput('5'),
      mQuestDropRateCampaignContentUpdatePage.setContentIdInput('5'),
      mQuestDropRateCampaignContentUpdatePage.setRateInput('5')
    ]);
    expect(await mQuestDropRateCampaignContentUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestDropRateCampaignContentUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mQuestDropRateCampaignContentUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mQuestDropRateCampaignContentUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    await mQuestDropRateCampaignContentUpdatePage.save();
    expect(await mQuestDropRateCampaignContentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestDropRateCampaignContentComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestDropRateCampaignContent', async () => {
    const nbButtonsBeforeDelete = await mQuestDropRateCampaignContentComponentsPage.countDeleteButtons();
    await mQuestDropRateCampaignContentComponentsPage.clickOnLastDeleteButton();

    mQuestDropRateCampaignContentDeleteDialog = new MQuestDropRateCampaignContentDeleteDialog();
    expect(await mQuestDropRateCampaignContentDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Quest Drop Rate Campaign Content?'
    );
    await mQuestDropRateCampaignContentDeleteDialog.clickOnConfirmButton();

    expect(await mQuestDropRateCampaignContentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
