/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MEncountersBonusComponentsPage, MEncountersBonusDeleteDialog, MEncountersBonusUpdatePage } from './m-encounters-bonus.page-object';

const expect = chai.expect;

describe('MEncountersBonus e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEncountersBonusUpdatePage: MEncountersBonusUpdatePage;
  let mEncountersBonusComponentsPage: MEncountersBonusComponentsPage;
  let mEncountersBonusDeleteDialog: MEncountersBonusDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEncountersBonuses', async () => {
    await navBarPage.goToEntity('m-encounters-bonus');
    mEncountersBonusComponentsPage = new MEncountersBonusComponentsPage();
    await browser.wait(ec.visibilityOf(mEncountersBonusComponentsPage.title), 5000);
    expect(await mEncountersBonusComponentsPage.getTitle()).to.eq('M Encounters Bonuses');
  });

  it('should load create MEncountersBonus page', async () => {
    await mEncountersBonusComponentsPage.clickOnCreateButton();
    mEncountersBonusUpdatePage = new MEncountersBonusUpdatePage();
    expect(await mEncountersBonusUpdatePage.getPageTitle()).to.eq('Create or edit a M Encounters Bonus');
    await mEncountersBonusUpdatePage.cancel();
  });

  it('should create and save MEncountersBonuses', async () => {
    const nbButtonsBeforeCreate = await mEncountersBonusComponentsPage.countDeleteButtons();

    await mEncountersBonusComponentsPage.clickOnCreateButton();
    await promise.all([
      mEncountersBonusUpdatePage.setEncountersTypeInput('5'),
      mEncountersBonusUpdatePage.setIsSkillSuccessInput('5'),
      mEncountersBonusUpdatePage.setThresholdInput('5'),
      mEncountersBonusUpdatePage.setProbabilityBonusValueInput('5')
    ]);
    expect(await mEncountersBonusUpdatePage.getEncountersTypeInput()).to.eq('5', 'Expected encountersType value to be equals to 5');
    expect(await mEncountersBonusUpdatePage.getIsSkillSuccessInput()).to.eq('5', 'Expected isSkillSuccess value to be equals to 5');
    expect(await mEncountersBonusUpdatePage.getThresholdInput()).to.eq('5', 'Expected threshold value to be equals to 5');
    expect(await mEncountersBonusUpdatePage.getProbabilityBonusValueInput()).to.eq(
      '5',
      'Expected probabilityBonusValue value to be equals to 5'
    );
    await mEncountersBonusUpdatePage.save();
    expect(await mEncountersBonusUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEncountersBonusComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MEncountersBonus', async () => {
    const nbButtonsBeforeDelete = await mEncountersBonusComponentsPage.countDeleteButtons();
    await mEncountersBonusComponentsPage.clickOnLastDeleteButton();

    mEncountersBonusDeleteDialog = new MEncountersBonusDeleteDialog();
    expect(await mEncountersBonusDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Encounters Bonus?');
    await mEncountersBonusDeleteDialog.clickOnConfirmButton();

    expect(await mEncountersBonusComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
