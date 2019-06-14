/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MLeagueComponentsPage, MLeagueDeleteDialog, MLeagueUpdatePage } from './m-league.page-object';

const expect = chai.expect;

describe('MLeague e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueUpdatePage: MLeagueUpdatePage;
  let mLeagueComponentsPage: MLeagueComponentsPage;
  let mLeagueDeleteDialog: MLeagueDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagues', async () => {
    await navBarPage.goToEntity('m-league');
    mLeagueComponentsPage = new MLeagueComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueComponentsPage.title), 5000);
    expect(await mLeagueComponentsPage.getTitle()).to.eq('M Leagues');
  });

  it('should load create MLeague page', async () => {
    await mLeagueComponentsPage.clickOnCreateButton();
    mLeagueUpdatePage = new MLeagueUpdatePage();
    expect(await mLeagueUpdatePage.getPageTitle()).to.eq('Create or edit a M League');
    await mLeagueUpdatePage.cancel();
  });

  it('should create and save MLeagues', async () => {
    const nbButtonsBeforeCreate = await mLeagueComponentsPage.countDeleteButtons();

    await mLeagueComponentsPage.clickOnCreateButton();
    await promise.all([
      mLeagueUpdatePage.setHierarchyInput('5'),
      mLeagueUpdatePage.setWeightInput('5'),
      mLeagueUpdatePage.setNameInput('name'),
      mLeagueUpdatePage.setRoomsInput('5'),
      mLeagueUpdatePage.setPromotionRateInput('5'),
      mLeagueUpdatePage.setDemotionRateInput('5'),
      mLeagueUpdatePage.setTotalParameterRangeUpperInput('5'),
      mLeagueUpdatePage.setTotalParameterRangeLowerInput('5'),
      mLeagueUpdatePage.setRequiredMatchesInput('5'),
      mLeagueUpdatePage.setStartWeekIdInput('5')
    ]);
    expect(await mLeagueUpdatePage.getHierarchyInput()).to.eq('5', 'Expected hierarchy value to be equals to 5');
    expect(await mLeagueUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mLeagueUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mLeagueUpdatePage.getRoomsInput()).to.eq('5', 'Expected rooms value to be equals to 5');
    expect(await mLeagueUpdatePage.getPromotionRateInput()).to.eq('5', 'Expected promotionRate value to be equals to 5');
    expect(await mLeagueUpdatePage.getDemotionRateInput()).to.eq('5', 'Expected demotionRate value to be equals to 5');
    expect(await mLeagueUpdatePage.getTotalParameterRangeUpperInput()).to.eq(
      '5',
      'Expected totalParameterRangeUpper value to be equals to 5'
    );
    expect(await mLeagueUpdatePage.getTotalParameterRangeLowerInput()).to.eq(
      '5',
      'Expected totalParameterRangeLower value to be equals to 5'
    );
    expect(await mLeagueUpdatePage.getRequiredMatchesInput()).to.eq('5', 'Expected requiredMatches value to be equals to 5');
    expect(await mLeagueUpdatePage.getStartWeekIdInput()).to.eq('5', 'Expected startWeekId value to be equals to 5');
    await mLeagueUpdatePage.save();
    expect(await mLeagueUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mLeagueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MLeague', async () => {
    const nbButtonsBeforeDelete = await mLeagueComponentsPage.countDeleteButtons();
    await mLeagueComponentsPage.clickOnLastDeleteButton();

    mLeagueDeleteDialog = new MLeagueDeleteDialog();
    expect(await mLeagueDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M League?');
    await mLeagueDeleteDialog.clickOnConfirmButton();

    expect(await mLeagueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
