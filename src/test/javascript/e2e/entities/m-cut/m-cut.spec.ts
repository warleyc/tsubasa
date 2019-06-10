/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCutComponentsPage, MCutDeleteDialog, MCutUpdatePage } from './m-cut.page-object';

const expect = chai.expect;

describe('MCut e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCutUpdatePage: MCutUpdatePage;
  let mCutComponentsPage: MCutComponentsPage;
  let mCutDeleteDialog: MCutDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCuts', async () => {
    await navBarPage.goToEntity('m-cut');
    mCutComponentsPage = new MCutComponentsPage();
    await browser.wait(ec.visibilityOf(mCutComponentsPage.title), 5000);
    expect(await mCutComponentsPage.getTitle()).to.eq('M Cuts');
  });

  it('should load create MCut page', async () => {
    await mCutComponentsPage.clickOnCreateButton();
    mCutUpdatePage = new MCutUpdatePage();
    expect(await mCutUpdatePage.getPageTitle()).to.eq('Create or edit a M Cut');
    await mCutUpdatePage.cancel();
  });

  it('should create and save MCuts', async () => {
    const nbButtonsBeforeCreate = await mCutComponentsPage.countDeleteButtons();

    await mCutComponentsPage.clickOnCreateButton();
    await promise.all([
      mCutUpdatePage.setNameInput('name'),
      mCutUpdatePage.setFpAInput('5'),
      mCutUpdatePage.setFpBInput('5'),
      mCutUpdatePage.setFpCInput('5'),
      mCutUpdatePage.setFpDInput('5'),
      mCutUpdatePage.setFpEInput('5'),
      mCutUpdatePage.setFpFInput('5'),
      mCutUpdatePage.setGkAInput('5'),
      mCutUpdatePage.setGkBInput('5'),
      mCutUpdatePage.setShowEnvironmentalEffectInput('5'),
      mCutUpdatePage.setCutTypeInput('5'),
      mCutUpdatePage.setGroupIdInput('5'),
      mCutUpdatePage.setIsCombiCutInput('5')
    ]);
    expect(await mCutUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mCutUpdatePage.getFpAInput()).to.eq('5', 'Expected fpA value to be equals to 5');
    expect(await mCutUpdatePage.getFpBInput()).to.eq('5', 'Expected fpB value to be equals to 5');
    expect(await mCutUpdatePage.getFpCInput()).to.eq('5', 'Expected fpC value to be equals to 5');
    expect(await mCutUpdatePage.getFpDInput()).to.eq('5', 'Expected fpD value to be equals to 5');
    expect(await mCutUpdatePage.getFpEInput()).to.eq('5', 'Expected fpE value to be equals to 5');
    expect(await mCutUpdatePage.getFpFInput()).to.eq('5', 'Expected fpF value to be equals to 5');
    expect(await mCutUpdatePage.getGkAInput()).to.eq('5', 'Expected gkA value to be equals to 5');
    expect(await mCutUpdatePage.getGkBInput()).to.eq('5', 'Expected gkB value to be equals to 5');
    expect(await mCutUpdatePage.getShowEnvironmentalEffectInput()).to.eq('5', 'Expected showEnvironmentalEffect value to be equals to 5');
    expect(await mCutUpdatePage.getCutTypeInput()).to.eq('5', 'Expected cutType value to be equals to 5');
    expect(await mCutUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mCutUpdatePage.getIsCombiCutInput()).to.eq('5', 'Expected isCombiCut value to be equals to 5');
    await mCutUpdatePage.save();
    expect(await mCutUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCutComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCut', async () => {
    const nbButtonsBeforeDelete = await mCutComponentsPage.countDeleteButtons();
    await mCutComponentsPage.clickOnLastDeleteButton();

    mCutDeleteDialog = new MCutDeleteDialog();
    expect(await mCutDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Cut?');
    await mCutDeleteDialog.clickOnConfirmButton();

    expect(await mCutComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
