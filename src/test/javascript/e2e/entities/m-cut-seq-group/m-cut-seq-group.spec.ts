/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCutSeqGroupComponentsPage, MCutSeqGroupDeleteDialog, MCutSeqGroupUpdatePage } from './m-cut-seq-group.page-object';

const expect = chai.expect;

describe('MCutSeqGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCutSeqGroupUpdatePage: MCutSeqGroupUpdatePage;
  let mCutSeqGroupComponentsPage: MCutSeqGroupComponentsPage;
  let mCutSeqGroupDeleteDialog: MCutSeqGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCutSeqGroups', async () => {
    await navBarPage.goToEntity('m-cut-seq-group');
    mCutSeqGroupComponentsPage = new MCutSeqGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mCutSeqGroupComponentsPage.title), 5000);
    expect(await mCutSeqGroupComponentsPage.getTitle()).to.eq('M Cut Seq Groups');
  });

  it('should load create MCutSeqGroup page', async () => {
    await mCutSeqGroupComponentsPage.clickOnCreateButton();
    mCutSeqGroupUpdatePage = new MCutSeqGroupUpdatePage();
    expect(await mCutSeqGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Cut Seq Group');
    await mCutSeqGroupUpdatePage.cancel();
  });

  it('should create and save MCutSeqGroups', async () => {
    const nbButtonsBeforeCreate = await mCutSeqGroupComponentsPage.countDeleteButtons();

    await mCutSeqGroupComponentsPage.clickOnCreateButton();
    await promise.all([
      mCutSeqGroupUpdatePage.setKeyInput('key'),
      mCutSeqGroupUpdatePage.setParamInput('param'),
      mCutSeqGroupUpdatePage.setCutSequenceTextInput('cutSequenceText')
    ]);
    expect(await mCutSeqGroupUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mCutSeqGroupUpdatePage.getParamInput()).to.eq('param', 'Expected Param value to be equals to param');
    expect(await mCutSeqGroupUpdatePage.getCutSequenceTextInput()).to.eq(
      'cutSequenceText',
      'Expected CutSequenceText value to be equals to cutSequenceText'
    );
    await mCutSeqGroupUpdatePage.save();
    expect(await mCutSeqGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCutSeqGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCutSeqGroup', async () => {
    const nbButtonsBeforeDelete = await mCutSeqGroupComponentsPage.countDeleteButtons();
    await mCutSeqGroupComponentsPage.clickOnLastDeleteButton();

    mCutSeqGroupDeleteDialog = new MCutSeqGroupDeleteDialog();
    expect(await mCutSeqGroupDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Cut Seq Group?');
    await mCutSeqGroupDeleteDialog.clickOnConfirmButton();

    expect(await mCutSeqGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
