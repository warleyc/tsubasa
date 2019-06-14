/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MLeagueEffectComponentsPage, MLeagueEffectDeleteDialog, MLeagueEffectUpdatePage } from './m-league-effect.page-object';

const expect = chai.expect;

describe('MLeagueEffect e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueEffectUpdatePage: MLeagueEffectUpdatePage;
  let mLeagueEffectComponentsPage: MLeagueEffectComponentsPage;
  let mLeagueEffectDeleteDialog: MLeagueEffectDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagueEffects', async () => {
    await navBarPage.goToEntity('m-league-effect');
    mLeagueEffectComponentsPage = new MLeagueEffectComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueEffectComponentsPage.title), 5000);
    expect(await mLeagueEffectComponentsPage.getTitle()).to.eq('M League Effects');
  });

  it('should load create MLeagueEffect page', async () => {
    await mLeagueEffectComponentsPage.clickOnCreateButton();
    mLeagueEffectUpdatePage = new MLeagueEffectUpdatePage();
    expect(await mLeagueEffectUpdatePage.getPageTitle()).to.eq('Create or edit a M League Effect');
    await mLeagueEffectUpdatePage.cancel();
  });

  it('should create and save MLeagueEffects', async () => {
    const nbButtonsBeforeCreate = await mLeagueEffectComponentsPage.countDeleteButtons();

    await mLeagueEffectComponentsPage.clickOnCreateButton();
    await promise.all([
      mLeagueEffectUpdatePage.setEffectTypeInput('5'),
      mLeagueEffectUpdatePage.setLeagueHierarchyInput('5'),
      mLeagueEffectUpdatePage.setRateInput('5'),
      mLeagueEffectUpdatePage.setPriceInput('5')
    ]);
    expect(await mLeagueEffectUpdatePage.getEffectTypeInput()).to.eq('5', 'Expected effectType value to be equals to 5');
    expect(await mLeagueEffectUpdatePage.getLeagueHierarchyInput()).to.eq('5', 'Expected leagueHierarchy value to be equals to 5');
    expect(await mLeagueEffectUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    expect(await mLeagueEffectUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');
    await mLeagueEffectUpdatePage.save();
    expect(await mLeagueEffectUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLeagueEffectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MLeagueEffect', async () => {
    const nbButtonsBeforeDelete = await mLeagueEffectComponentsPage.countDeleteButtons();
    await mLeagueEffectComponentsPage.clickOnLastDeleteButton();

    mLeagueEffectDeleteDialog = new MLeagueEffectDeleteDialog();
    expect(await mLeagueEffectDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M League Effect?');
    await mLeagueEffectDeleteDialog.clickOnConfirmButton();

    expect(await mLeagueEffectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
