/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MExtensionSaleComponentsPage, MExtensionSaleDeleteDialog, MExtensionSaleUpdatePage } from './m-extension-sale.page-object';

const expect = chai.expect;

describe('MExtensionSale e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mExtensionSaleUpdatePage: MExtensionSaleUpdatePage;
  let mExtensionSaleComponentsPage: MExtensionSaleComponentsPage;
  let mExtensionSaleDeleteDialog: MExtensionSaleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MExtensionSales', async () => {
    await navBarPage.goToEntity('m-extension-sale');
    mExtensionSaleComponentsPage = new MExtensionSaleComponentsPage();
    await browser.wait(ec.visibilityOf(mExtensionSaleComponentsPage.title), 5000);
    expect(await mExtensionSaleComponentsPage.getTitle()).to.eq('M Extension Sales');
  });

  it('should load create MExtensionSale page', async () => {
    await mExtensionSaleComponentsPage.clickOnCreateButton();
    mExtensionSaleUpdatePage = new MExtensionSaleUpdatePage();
    expect(await mExtensionSaleUpdatePage.getPageTitle()).to.eq('Create or edit a M Extension Sale');
    await mExtensionSaleUpdatePage.cancel();
  });

  it('should create and save MExtensionSales', async () => {
    const nbButtonsBeforeCreate = await mExtensionSaleComponentsPage.countDeleteButtons();

    await mExtensionSaleComponentsPage.clickOnCreateButton();
    await promise.all([
      mExtensionSaleUpdatePage.setMenuMessageInput('menuMessage'),
      mExtensionSaleUpdatePage.setStartAtInput('5'),
      mExtensionSaleUpdatePage.setEndAtInput('5'),
      mExtensionSaleUpdatePage.setTypeInput('5'),
      mExtensionSaleUpdatePage.setAddExtensionInput('5')
    ]);
    expect(await mExtensionSaleUpdatePage.getMenuMessageInput()).to.eq(
      'menuMessage',
      'Expected MenuMessage value to be equals to menuMessage'
    );
    expect(await mExtensionSaleUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mExtensionSaleUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    expect(await mExtensionSaleUpdatePage.getTypeInput()).to.eq('5', 'Expected type value to be equals to 5');
    expect(await mExtensionSaleUpdatePage.getAddExtensionInput()).to.eq('5', 'Expected addExtension value to be equals to 5');
    await mExtensionSaleUpdatePage.save();
    expect(await mExtensionSaleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mExtensionSaleComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MExtensionSale', async () => {
    const nbButtonsBeforeDelete = await mExtensionSaleComponentsPage.countDeleteButtons();
    await mExtensionSaleComponentsPage.clickOnLastDeleteButton();

    mExtensionSaleDeleteDialog = new MExtensionSaleDeleteDialog();
    expect(await mExtensionSaleDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Extension Sale?');
    await mExtensionSaleDeleteDialog.clickOnConfirmButton();

    expect(await mExtensionSaleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
