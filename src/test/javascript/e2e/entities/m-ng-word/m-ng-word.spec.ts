/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MNgWordComponentsPage, MNgWordDeleteDialog, MNgWordUpdatePage } from './m-ng-word.page-object';

const expect = chai.expect;

describe('MNgWord e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mNgWordUpdatePage: MNgWordUpdatePage;
  let mNgWordComponentsPage: MNgWordComponentsPage;
  let mNgWordDeleteDialog: MNgWordDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MNgWords', async () => {
    await navBarPage.goToEntity('m-ng-word');
    mNgWordComponentsPage = new MNgWordComponentsPage();
    await browser.wait(ec.visibilityOf(mNgWordComponentsPage.title), 5000);
    expect(await mNgWordComponentsPage.getTitle()).to.eq('M Ng Words');
  });

  it('should load create MNgWord page', async () => {
    await mNgWordComponentsPage.clickOnCreateButton();
    mNgWordUpdatePage = new MNgWordUpdatePage();
    expect(await mNgWordUpdatePage.getPageTitle()).to.eq('Create or edit a M Ng Word');
    await mNgWordUpdatePage.cancel();
  });

  it('should create and save MNgWords', async () => {
    const nbButtonsBeforeCreate = await mNgWordComponentsPage.countDeleteButtons();

    await mNgWordComponentsPage.clickOnCreateButton();
    await promise.all([mNgWordUpdatePage.setWordInput('word'), mNgWordUpdatePage.setJudgeTypeInput('5')]);
    expect(await mNgWordUpdatePage.getWordInput()).to.eq('word', 'Expected Word value to be equals to word');
    expect(await mNgWordUpdatePage.getJudgeTypeInput()).to.eq('5', 'Expected judgeType value to be equals to 5');
    await mNgWordUpdatePage.save();
    expect(await mNgWordUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mNgWordComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MNgWord', async () => {
    const nbButtonsBeforeDelete = await mNgWordComponentsPage.countDeleteButtons();
    await mNgWordComponentsPage.clickOnLastDeleteButton();

    mNgWordDeleteDialog = new MNgWordDeleteDialog();
    expect(await mNgWordDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Ng Word?');
    await mNgWordDeleteDialog.clickOnConfirmButton();

    expect(await mNgWordComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
