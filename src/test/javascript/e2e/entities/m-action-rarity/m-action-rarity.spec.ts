/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MActionRarityComponentsPage, MActionRarityDeleteDialog, MActionRarityUpdatePage } from './m-action-rarity.page-object';

const expect = chai.expect;

describe('MActionRarity e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionRarityUpdatePage: MActionRarityUpdatePage;
  let mActionRarityComponentsPage: MActionRarityComponentsPage;
  let mActionRarityDeleteDialog: MActionRarityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActionRarities', async () => {
    await navBarPage.goToEntity('m-action-rarity');
    mActionRarityComponentsPage = new MActionRarityComponentsPage();
    await browser.wait(ec.visibilityOf(mActionRarityComponentsPage.title), 5000);
    expect(await mActionRarityComponentsPage.getTitle()).to.eq('M Action Rarities');
  });

  it('should load create MActionRarity page', async () => {
    await mActionRarityComponentsPage.clickOnCreateButton();
    mActionRarityUpdatePage = new MActionRarityUpdatePage();
    expect(await mActionRarityUpdatePage.getPageTitle()).to.eq('Create or edit a M Action Rarity');
    await mActionRarityUpdatePage.cancel();
  });

  it('should create and save MActionRarities', async () => {
    const nbButtonsBeforeCreate = await mActionRarityComponentsPage.countDeleteButtons();

    await mActionRarityComponentsPage.clickOnCreateButton();
    await promise.all([mActionRarityUpdatePage.setRarityInput('5'), mActionRarityUpdatePage.setNameInput('name')]);
    expect(await mActionRarityUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mActionRarityUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await mActionRarityUpdatePage.save();
    expect(await mActionRarityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mActionRarityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MActionRarity', async () => {
    const nbButtonsBeforeDelete = await mActionRarityComponentsPage.countDeleteButtons();
    await mActionRarityComponentsPage.clickOnLastDeleteButton();

    mActionRarityDeleteDialog = new MActionRarityDeleteDialog();
    expect(await mActionRarityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Action Rarity?');
    await mActionRarityDeleteDialog.clickOnConfirmButton();

    expect(await mActionRarityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
