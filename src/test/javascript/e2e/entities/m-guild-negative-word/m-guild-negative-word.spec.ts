/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuildNegativeWordComponentsPage,
  MGuildNegativeWordDeleteDialog,
  MGuildNegativeWordUpdatePage
} from './m-guild-negative-word.page-object';

const expect = chai.expect;

describe('MGuildNegativeWord e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildNegativeWordUpdatePage: MGuildNegativeWordUpdatePage;
  let mGuildNegativeWordComponentsPage: MGuildNegativeWordComponentsPage;
  let mGuildNegativeWordDeleteDialog: MGuildNegativeWordDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildNegativeWords', async () => {
    await navBarPage.goToEntity('m-guild-negative-word');
    mGuildNegativeWordComponentsPage = new MGuildNegativeWordComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildNegativeWordComponentsPage.title), 5000);
    expect(await mGuildNegativeWordComponentsPage.getTitle()).to.eq('M Guild Negative Words');
  });

  it('should load create MGuildNegativeWord page', async () => {
    await mGuildNegativeWordComponentsPage.clickOnCreateButton();
    mGuildNegativeWordUpdatePage = new MGuildNegativeWordUpdatePage();
    expect(await mGuildNegativeWordUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Negative Word');
    await mGuildNegativeWordUpdatePage.cancel();
  });

  it('should create and save MGuildNegativeWords', async () => {
    const nbButtonsBeforeCreate = await mGuildNegativeWordComponentsPage.countDeleteButtons();

    await mGuildNegativeWordComponentsPage.clickOnCreateButton();
    await promise.all([mGuildNegativeWordUpdatePage.setWordInput('word')]);
    expect(await mGuildNegativeWordUpdatePage.getWordInput()).to.eq('word', 'Expected Word value to be equals to word');
    await mGuildNegativeWordUpdatePage.save();
    expect(await mGuildNegativeWordUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildNegativeWordComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuildNegativeWord', async () => {
    const nbButtonsBeforeDelete = await mGuildNegativeWordComponentsPage.countDeleteButtons();
    await mGuildNegativeWordComponentsPage.clickOnLastDeleteButton();

    mGuildNegativeWordDeleteDialog = new MGuildNegativeWordDeleteDialog();
    expect(await mGuildNegativeWordDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Negative Word?');
    await mGuildNegativeWordDeleteDialog.clickOnConfirmButton();

    expect(await mGuildNegativeWordComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
