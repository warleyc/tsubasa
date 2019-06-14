/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MUserPolicyUpdateDateComponentsPage,
  MUserPolicyUpdateDateDeleteDialog,
  MUserPolicyUpdateDateUpdatePage
} from './m-user-policy-update-date.page-object';

const expect = chai.expect;

describe('MUserPolicyUpdateDate e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mUserPolicyUpdateDateUpdatePage: MUserPolicyUpdateDateUpdatePage;
  let mUserPolicyUpdateDateComponentsPage: MUserPolicyUpdateDateComponentsPage;
  let mUserPolicyUpdateDateDeleteDialog: MUserPolicyUpdateDateDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MUserPolicyUpdateDates', async () => {
    await navBarPage.goToEntity('m-user-policy-update-date');
    mUserPolicyUpdateDateComponentsPage = new MUserPolicyUpdateDateComponentsPage();
    await browser.wait(ec.visibilityOf(mUserPolicyUpdateDateComponentsPage.title), 5000);
    expect(await mUserPolicyUpdateDateComponentsPage.getTitle()).to.eq('M User Policy Update Dates');
  });

  it('should load create MUserPolicyUpdateDate page', async () => {
    await mUserPolicyUpdateDateComponentsPage.clickOnCreateButton();
    mUserPolicyUpdateDateUpdatePage = new MUserPolicyUpdateDateUpdatePage();
    expect(await mUserPolicyUpdateDateUpdatePage.getPageTitle()).to.eq('Create or edit a M User Policy Update Date');
    await mUserPolicyUpdateDateUpdatePage.cancel();
  });

  it('should create and save MUserPolicyUpdateDates', async () => {
    const nbButtonsBeforeCreate = await mUserPolicyUpdateDateComponentsPage.countDeleteButtons();

    await mUserPolicyUpdateDateComponentsPage.clickOnCreateButton();
    await promise.all([mUserPolicyUpdateDateUpdatePage.setUpdateDateInput('5')]);
    expect(await mUserPolicyUpdateDateUpdatePage.getUpdateDateInput()).to.eq('5', 'Expected updateDate value to be equals to 5');
    await mUserPolicyUpdateDateUpdatePage.save();
    expect(await mUserPolicyUpdateDateUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mUserPolicyUpdateDateComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MUserPolicyUpdateDate', async () => {
    const nbButtonsBeforeDelete = await mUserPolicyUpdateDateComponentsPage.countDeleteButtons();
    await mUserPolicyUpdateDateComponentsPage.clickOnLastDeleteButton();

    mUserPolicyUpdateDateDeleteDialog = new MUserPolicyUpdateDateDeleteDialog();
    expect(await mUserPolicyUpdateDateDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M User Policy Update Date?'
    );
    await mUserPolicyUpdateDateDeleteDialog.clickOnConfirmButton();

    expect(await mUserPolicyUpdateDateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
