/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTutorialMessageComponentsPage, MTutorialMessageDeleteDialog, MTutorialMessageUpdatePage } from './m-tutorial-message.page-object';

const expect = chai.expect;

describe('MTutorialMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTutorialMessageUpdatePage: MTutorialMessageUpdatePage;
  let mTutorialMessageComponentsPage: MTutorialMessageComponentsPage;
  let mTutorialMessageDeleteDialog: MTutorialMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTutorialMessages', async () => {
    await navBarPage.goToEntity('m-tutorial-message');
    mTutorialMessageComponentsPage = new MTutorialMessageComponentsPage();
    await browser.wait(ec.visibilityOf(mTutorialMessageComponentsPage.title), 5000);
    expect(await mTutorialMessageComponentsPage.getTitle()).to.eq('M Tutorial Messages');
  });

  it('should load create MTutorialMessage page', async () => {
    await mTutorialMessageComponentsPage.clickOnCreateButton();
    mTutorialMessageUpdatePage = new MTutorialMessageUpdatePage();
    expect(await mTutorialMessageUpdatePage.getPageTitle()).to.eq('Create or edit a M Tutorial Message');
    await mTutorialMessageUpdatePage.cancel();
  });

  it('should create and save MTutorialMessages', async () => {
    const nbButtonsBeforeCreate = await mTutorialMessageComponentsPage.countDeleteButtons();

    await mTutorialMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      mTutorialMessageUpdatePage.setStepInput('5'),
      mTutorialMessageUpdatePage.setOrderNumInput('5'),
      mTutorialMessageUpdatePage.setPositionInput('5'),
      mTutorialMessageUpdatePage.setMessageInput('message')
    ]);
    expect(await mTutorialMessageUpdatePage.getStepInput()).to.eq('5', 'Expected step value to be equals to 5');
    expect(await mTutorialMessageUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    expect(await mTutorialMessageUpdatePage.getPositionInput()).to.eq('5', 'Expected position value to be equals to 5');
    expect(await mTutorialMessageUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await mTutorialMessageUpdatePage.save();
    expect(await mTutorialMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTutorialMessageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTutorialMessage', async () => {
    const nbButtonsBeforeDelete = await mTutorialMessageComponentsPage.countDeleteButtons();
    await mTutorialMessageComponentsPage.clickOnLastDeleteButton();

    mTutorialMessageDeleteDialog = new MTutorialMessageDeleteDialog();
    expect(await mTutorialMessageDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Tutorial Message?');
    await mTutorialMessageDeleteDialog.clickOnConfirmButton();

    expect(await mTutorialMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
