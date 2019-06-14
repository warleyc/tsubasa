/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLoginBonusSerifComponentsPage,
  MLoginBonusSerifDeleteDialog,
  MLoginBonusSerifUpdatePage
} from './m-login-bonus-serif.page-object';

const expect = chai.expect;

describe('MLoginBonusSerif e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLoginBonusSerifUpdatePage: MLoginBonusSerifUpdatePage;
  let mLoginBonusSerifComponentsPage: MLoginBonusSerifComponentsPage;
  let mLoginBonusSerifDeleteDialog: MLoginBonusSerifDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLoginBonusSerifs', async () => {
    await navBarPage.goToEntity('m-login-bonus-serif');
    mLoginBonusSerifComponentsPage = new MLoginBonusSerifComponentsPage();
    await browser.wait(ec.visibilityOf(mLoginBonusSerifComponentsPage.title), 5000);
    expect(await mLoginBonusSerifComponentsPage.getTitle()).to.eq('M Login Bonus Serifs');
  });

  it('should load create MLoginBonusSerif page', async () => {
    await mLoginBonusSerifComponentsPage.clickOnCreateButton();
    mLoginBonusSerifUpdatePage = new MLoginBonusSerifUpdatePage();
    expect(await mLoginBonusSerifUpdatePage.getPageTitle()).to.eq('Create or edit a M Login Bonus Serif');
    await mLoginBonusSerifUpdatePage.cancel();
  });

  it('should create and save MLoginBonusSerifs', async () => {
    const nbButtonsBeforeCreate = await mLoginBonusSerifComponentsPage.countDeleteButtons();

    await mLoginBonusSerifComponentsPage.clickOnCreateButton();
    await promise.all([
      mLoginBonusSerifUpdatePage.setSerifIdInput('5'),
      mLoginBonusSerifUpdatePage.setSerif1Input('serif1'),
      mLoginBonusSerifUpdatePage.setSerif2Input('serif2')
    ]);
    expect(await mLoginBonusSerifUpdatePage.getSerifIdInput()).to.eq('5', 'Expected serifId value to be equals to 5');
    expect(await mLoginBonusSerifUpdatePage.getSerif1Input()).to.eq('serif1', 'Expected Serif1 value to be equals to serif1');
    expect(await mLoginBonusSerifUpdatePage.getSerif2Input()).to.eq('serif2', 'Expected Serif2 value to be equals to serif2');
    await mLoginBonusSerifUpdatePage.save();
    expect(await mLoginBonusSerifUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLoginBonusSerifComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MLoginBonusSerif', async () => {
    const nbButtonsBeforeDelete = await mLoginBonusSerifComponentsPage.countDeleteButtons();
    await mLoginBonusSerifComponentsPage.clickOnLastDeleteButton();

    mLoginBonusSerifDeleteDialog = new MLoginBonusSerifDeleteDialog();
    expect(await mLoginBonusSerifDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Login Bonus Serif?');
    await mLoginBonusSerifDeleteDialog.clickOnConfirmButton();

    expect(await mLoginBonusSerifComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
