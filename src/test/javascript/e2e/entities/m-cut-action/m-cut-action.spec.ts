/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCutActionComponentsPage, MCutActionDeleteDialog, MCutActionUpdatePage } from './m-cut-action.page-object';

const expect = chai.expect;

describe('MCutAction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCutActionUpdatePage: MCutActionUpdatePage;
  let mCutActionComponentsPage: MCutActionComponentsPage;
  let mCutActionDeleteDialog: MCutActionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCutActions', async () => {
    await navBarPage.goToEntity('m-cut-action');
    mCutActionComponentsPage = new MCutActionComponentsPage();
    await browser.wait(ec.visibilityOf(mCutActionComponentsPage.title), 5000);
    expect(await mCutActionComponentsPage.getTitle()).to.eq('M Cut Actions');
  });

  it('should load create MCutAction page', async () => {
    await mCutActionComponentsPage.clickOnCreateButton();
    mCutActionUpdatePage = new MCutActionUpdatePage();
    expect(await mCutActionUpdatePage.getPageTitle()).to.eq('Create or edit a M Cut Action');
    await mCutActionUpdatePage.cancel();
  });

  it('should create and save MCutActions', async () => {
    const nbButtonsBeforeCreate = await mCutActionComponentsPage.countDeleteButtons();

    await mCutActionComponentsPage.clickOnCreateButton();
    await promise.all([
      mCutActionUpdatePage.setActionCutIdInput('5'),
      mCutActionUpdatePage.setCutActionTypeInput('5'),
      mCutActionUpdatePage.setCutSequenceTextInput('cutSequenceText')
    ]);
    expect(await mCutActionUpdatePage.getActionCutIdInput()).to.eq('5', 'Expected actionCutId value to be equals to 5');
    expect(await mCutActionUpdatePage.getCutActionTypeInput()).to.eq('5', 'Expected cutActionType value to be equals to 5');
    expect(await mCutActionUpdatePage.getCutSequenceTextInput()).to.eq(
      'cutSequenceText',
      'Expected CutSequenceText value to be equals to cutSequenceText'
    );
    await mCutActionUpdatePage.save();
    expect(await mCutActionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCutActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCutAction', async () => {
    const nbButtonsBeforeDelete = await mCutActionComponentsPage.countDeleteButtons();
    await mCutActionComponentsPage.clickOnLastDeleteButton();

    mCutActionDeleteDialog = new MCutActionDeleteDialog();
    expect(await mCutActionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Cut Action?');
    await mCutActionDeleteDialog.clickOnConfirmButton();

    expect(await mCutActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
