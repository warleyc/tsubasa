/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRegulationMatchTutorialMessageComponentsPage,
  MRegulationMatchTutorialMessageDeleteDialog,
  MRegulationMatchTutorialMessageUpdatePage
} from './m-regulation-match-tutorial-message.page-object';

const expect = chai.expect;

describe('MRegulationMatchTutorialMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRegulationMatchTutorialMessageUpdatePage: MRegulationMatchTutorialMessageUpdatePage;
  let mRegulationMatchTutorialMessageComponentsPage: MRegulationMatchTutorialMessageComponentsPage;
  let mRegulationMatchTutorialMessageDeleteDialog: MRegulationMatchTutorialMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRegulationMatchTutorialMessages', async () => {
    await navBarPage.goToEntity('m-regulation-match-tutorial-message');
    mRegulationMatchTutorialMessageComponentsPage = new MRegulationMatchTutorialMessageComponentsPage();
    await browser.wait(ec.visibilityOf(mRegulationMatchTutorialMessageComponentsPage.title), 5000);
    expect(await mRegulationMatchTutorialMessageComponentsPage.getTitle()).to.eq('M Regulation Match Tutorial Messages');
  });

  it('should load create MRegulationMatchTutorialMessage page', async () => {
    await mRegulationMatchTutorialMessageComponentsPage.clickOnCreateButton();
    mRegulationMatchTutorialMessageUpdatePage = new MRegulationMatchTutorialMessageUpdatePage();
    expect(await mRegulationMatchTutorialMessageUpdatePage.getPageTitle()).to.eq('Create or edit a M Regulation Match Tutorial Message');
    await mRegulationMatchTutorialMessageUpdatePage.cancel();
  });

  it('should create and save MRegulationMatchTutorialMessages', async () => {
    const nbButtonsBeforeCreate = await mRegulationMatchTutorialMessageComponentsPage.countDeleteButtons();

    await mRegulationMatchTutorialMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      mRegulationMatchTutorialMessageUpdatePage.setRuleIdInput('5'),
      mRegulationMatchTutorialMessageUpdatePage.setOrderNumInput('5'),
      mRegulationMatchTutorialMessageUpdatePage.setPositionInput('5'),
      mRegulationMatchTutorialMessageUpdatePage.setMessageInput('message'),
      mRegulationMatchTutorialMessageUpdatePage.setAssetNameInput('assetName'),
      mRegulationMatchTutorialMessageUpdatePage.setTitleInput('title')
    ]);
    expect(await mRegulationMatchTutorialMessageUpdatePage.getRuleIdInput()).to.eq('5', 'Expected ruleId value to be equals to 5');
    expect(await mRegulationMatchTutorialMessageUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mRegulationMatchTutorialMessageUpdatePage.getPositionInput()).to.eq('5', 'Expected position value to be equals to 5');
    expect(await mRegulationMatchTutorialMessageUpdatePage.getMessageInput()).to.eq(
      'message',
      'Expected Message value to be equals to message'
    );
    expect(await mRegulationMatchTutorialMessageUpdatePage.getAssetNameInput()).to.eq(
      'assetName',
      'Expected AssetName value to be equals to assetName'
    );
    expect(await mRegulationMatchTutorialMessageUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    await mRegulationMatchTutorialMessageUpdatePage.save();
    expect(await mRegulationMatchTutorialMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRegulationMatchTutorialMessageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRegulationMatchTutorialMessage', async () => {
    const nbButtonsBeforeDelete = await mRegulationMatchTutorialMessageComponentsPage.countDeleteButtons();
    await mRegulationMatchTutorialMessageComponentsPage.clickOnLastDeleteButton();

    mRegulationMatchTutorialMessageDeleteDialog = new MRegulationMatchTutorialMessageDeleteDialog();
    expect(await mRegulationMatchTutorialMessageDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Regulation Match Tutorial Message?'
    );
    await mRegulationMatchTutorialMessageDeleteDialog.clickOnConfirmButton();

    expect(await mRegulationMatchTutorialMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
