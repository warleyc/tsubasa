/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MLeagueRegulationComponentsPage,
  MLeagueRegulationDeleteDialog,
  MLeagueRegulationUpdatePage
} from './m-league-regulation.page-object';

const expect = chai.expect;

describe('MLeagueRegulation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mLeagueRegulationUpdatePage: MLeagueRegulationUpdatePage;
  let mLeagueRegulationComponentsPage: MLeagueRegulationComponentsPage;
  /*let mLeagueRegulationDeleteDialog: MLeagueRegulationDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MLeagueRegulations', async () => {
    await navBarPage.goToEntity('m-league-regulation');
    mLeagueRegulationComponentsPage = new MLeagueRegulationComponentsPage();
    await browser.wait(ec.visibilityOf(mLeagueRegulationComponentsPage.title), 5000);
    expect(await mLeagueRegulationComponentsPage.getTitle()).to.eq('M League Regulations');
  });

  it('should load create MLeagueRegulation page', async () => {
    await mLeagueRegulationComponentsPage.clickOnCreateButton();
    mLeagueRegulationUpdatePage = new MLeagueRegulationUpdatePage();
    expect(await mLeagueRegulationUpdatePage.getPageTitle()).to.eq('Create or edit a M League Regulation');
    await mLeagueRegulationUpdatePage.cancel();
  });

  /* it('should create and save MLeagueRegulations', async () => {
        const nbButtonsBeforeCreate = await mLeagueRegulationComponentsPage.countDeleteButtons();

        await mLeagueRegulationComponentsPage.clickOnCreateButton();
        await promise.all([
            mLeagueRegulationUpdatePage.setStartAtInput('5'),
            mLeagueRegulationUpdatePage.setEndAtInput('5'),
            mLeagueRegulationUpdatePage.setMatchOptionIdInput('5'),
            mLeagueRegulationUpdatePage.setDeckConditionIdInput('5'),
            mLeagueRegulationUpdatePage.setRuleTutorialIdInput('5'),
            mLeagueRegulationUpdatePage.mmatchoptionSelectLastOption(),
        ]);
        expect(await mLeagueRegulationUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
        expect(await mLeagueRegulationUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
        expect(await mLeagueRegulationUpdatePage.getMatchOptionIdInput()).to.eq('5', 'Expected matchOptionId value to be equals to 5');
        expect(await mLeagueRegulationUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        expect(await mLeagueRegulationUpdatePage.getRuleTutorialIdInput()).to.eq('5', 'Expected ruleTutorialId value to be equals to 5');
        await mLeagueRegulationUpdatePage.save();
        expect(await mLeagueRegulationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mLeagueRegulationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MLeagueRegulation', async () => {
        const nbButtonsBeforeDelete = await mLeagueRegulationComponentsPage.countDeleteButtons();
        await mLeagueRegulationComponentsPage.clickOnLastDeleteButton();

        mLeagueRegulationDeleteDialog = new MLeagueRegulationDeleteDialog();
        expect(await mLeagueRegulationDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M League Regulation?');
        await mLeagueRegulationDeleteDialog.clickOnConfirmButton();

        expect(await mLeagueRegulationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
