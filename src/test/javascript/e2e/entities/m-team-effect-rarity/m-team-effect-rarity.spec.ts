/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTeamEffectRarityComponentsPage,
  MTeamEffectRarityDeleteDialog,
  MTeamEffectRarityUpdatePage
} from './m-team-effect-rarity.page-object';

const expect = chai.expect;

describe('MTeamEffectRarity e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTeamEffectRarityUpdatePage: MTeamEffectRarityUpdatePage;
  let mTeamEffectRarityComponentsPage: MTeamEffectRarityComponentsPage;
  let mTeamEffectRarityDeleteDialog: MTeamEffectRarityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTeamEffectRarities', async () => {
    await navBarPage.goToEntity('m-team-effect-rarity');
    mTeamEffectRarityComponentsPage = new MTeamEffectRarityComponentsPage();
    await browser.wait(ec.visibilityOf(mTeamEffectRarityComponentsPage.title), 5000);
    expect(await mTeamEffectRarityComponentsPage.getTitle()).to.eq('M Team Effect Rarities');
  });

  it('should load create MTeamEffectRarity page', async () => {
    await mTeamEffectRarityComponentsPage.clickOnCreateButton();
    mTeamEffectRarityUpdatePage = new MTeamEffectRarityUpdatePage();
    expect(await mTeamEffectRarityUpdatePage.getPageTitle()).to.eq('Create or edit a M Team Effect Rarity');
    await mTeamEffectRarityUpdatePage.cancel();
  });

  it('should create and save MTeamEffectRarities', async () => {
    const nbButtonsBeforeCreate = await mTeamEffectRarityComponentsPage.countDeleteButtons();

    await mTeamEffectRarityComponentsPage.clickOnCreateButton();
    await promise.all([
      mTeamEffectRarityUpdatePage.setRarityInput('5'),
      mTeamEffectRarityUpdatePage.setNameInput('name'),
      mTeamEffectRarityUpdatePage.setMaxLevelInput('5')
    ]);
    expect(await mTeamEffectRarityUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mTeamEffectRarityUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mTeamEffectRarityUpdatePage.getMaxLevelInput()).to.eq('5', 'Expected maxLevel value to be equals to 5');
    await mTeamEffectRarityUpdatePage.save();
    expect(await mTeamEffectRarityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTeamEffectRarityComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTeamEffectRarity', async () => {
    const nbButtonsBeforeDelete = await mTeamEffectRarityComponentsPage.countDeleteButtons();
    await mTeamEffectRarityComponentsPage.clickOnLastDeleteButton();

    mTeamEffectRarityDeleteDialog = new MTeamEffectRarityDeleteDialog();
    expect(await mTeamEffectRarityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Team Effect Rarity?');
    await mTeamEffectRarityDeleteDialog.clickOnConfirmButton();

    expect(await mTeamEffectRarityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
