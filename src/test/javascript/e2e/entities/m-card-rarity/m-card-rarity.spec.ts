/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCardRarityComponentsPage, MCardRarityDeleteDialog, MCardRarityUpdatePage } from './m-card-rarity.page-object';

const expect = chai.expect;

describe('MCardRarity e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardRarityUpdatePage: MCardRarityUpdatePage;
  let mCardRarityComponentsPage: MCardRarityComponentsPage;
  let mCardRarityDeleteDialog: MCardRarityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardRarities', async () => {
    await navBarPage.goToEntity('m-card-rarity');
    mCardRarityComponentsPage = new MCardRarityComponentsPage();
    await browser.wait(ec.visibilityOf(mCardRarityComponentsPage.title), 5000);
    expect(await mCardRarityComponentsPage.getTitle()).to.eq('M Card Rarities');
  });

  it('should load create MCardRarity page', async () => {
    await mCardRarityComponentsPage.clickOnCreateButton();
    mCardRarityUpdatePage = new MCardRarityUpdatePage();
    expect(await mCardRarityUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Rarity');
    await mCardRarityUpdatePage.cancel();
  });

  it('should create and save MCardRarities', async () => {
    const nbButtonsBeforeCreate = await mCardRarityComponentsPage.countDeleteButtons();

    await mCardRarityComponentsPage.clickOnCreateButton();
    await promise.all([mCardRarityUpdatePage.setRarityInput('5'), mCardRarityUpdatePage.setNameInput('name')]);
    expect(await mCardRarityUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mCardRarityUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await mCardRarityUpdatePage.save();
    expect(await mCardRarityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCardRarityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCardRarity', async () => {
    const nbButtonsBeforeDelete = await mCardRarityComponentsPage.countDeleteButtons();
    await mCardRarityComponentsPage.clickOnLastDeleteButton();

    mCardRarityDeleteDialog = new MCardRarityDeleteDialog();
    expect(await mCardRarityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Card Rarity?');
    await mCardRarityDeleteDialog.clickOnConfirmButton();

    expect(await mCardRarityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
