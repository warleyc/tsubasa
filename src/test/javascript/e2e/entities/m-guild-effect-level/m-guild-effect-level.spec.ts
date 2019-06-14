/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGuildEffectLevelComponentsPage,
  MGuildEffectLevelDeleteDialog,
  MGuildEffectLevelUpdatePage
} from './m-guild-effect-level.page-object';

const expect = chai.expect;

describe('MGuildEffectLevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildEffectLevelUpdatePage: MGuildEffectLevelUpdatePage;
  let mGuildEffectLevelComponentsPage: MGuildEffectLevelComponentsPage;
  let mGuildEffectLevelDeleteDialog: MGuildEffectLevelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildEffectLevels', async () => {
    await navBarPage.goToEntity('m-guild-effect-level');
    mGuildEffectLevelComponentsPage = new MGuildEffectLevelComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildEffectLevelComponentsPage.title), 5000);
    expect(await mGuildEffectLevelComponentsPage.getTitle()).to.eq('M Guild Effect Levels');
  });

  it('should load create MGuildEffectLevel page', async () => {
    await mGuildEffectLevelComponentsPage.clickOnCreateButton();
    mGuildEffectLevelUpdatePage = new MGuildEffectLevelUpdatePage();
    expect(await mGuildEffectLevelUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Effect Level');
    await mGuildEffectLevelUpdatePage.cancel();
  });

  it('should create and save MGuildEffectLevels', async () => {
    const nbButtonsBeforeCreate = await mGuildEffectLevelComponentsPage.countDeleteButtons();

    await mGuildEffectLevelComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuildEffectLevelUpdatePage.setEffectTypeInput('5'),
      mGuildEffectLevelUpdatePage.setLevelInput('5'),
      mGuildEffectLevelUpdatePage.setRateInput('5'),
      mGuildEffectLevelUpdatePage.setRateStrInput('rateStr'),
      mGuildEffectLevelUpdatePage.setCoinInput('5'),
      mGuildEffectLevelUpdatePage.setGuildLevelInput('5'),
      mGuildEffectLevelUpdatePage.setGuildMedalInput('5')
    ]);
    expect(await mGuildEffectLevelUpdatePage.getEffectTypeInput()).to.eq('5', 'Expected effectType value to be equals to 5');
    expect(await mGuildEffectLevelUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mGuildEffectLevelUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    expect(await mGuildEffectLevelUpdatePage.getRateStrInput()).to.eq('rateStr', 'Expected RateStr value to be equals to rateStr');
    expect(await mGuildEffectLevelUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
    expect(await mGuildEffectLevelUpdatePage.getGuildLevelInput()).to.eq('5', 'Expected guildLevel value to be equals to 5');
    expect(await mGuildEffectLevelUpdatePage.getGuildMedalInput()).to.eq('5', 'Expected guildMedal value to be equals to 5');
    await mGuildEffectLevelUpdatePage.save();
    expect(await mGuildEffectLevelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildEffectLevelComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGuildEffectLevel', async () => {
    const nbButtonsBeforeDelete = await mGuildEffectLevelComponentsPage.countDeleteButtons();
    await mGuildEffectLevelComponentsPage.clickOnLastDeleteButton();

    mGuildEffectLevelDeleteDialog = new MGuildEffectLevelDeleteDialog();
    expect(await mGuildEffectLevelDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Effect Level?');
    await mGuildEffectLevelDeleteDialog.clickOnConfirmButton();

    expect(await mGuildEffectLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
