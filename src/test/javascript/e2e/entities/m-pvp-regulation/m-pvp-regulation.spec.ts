/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPvpRegulationComponentsPage, MPvpRegulationDeleteDialog, MPvpRegulationUpdatePage } from './m-pvp-regulation.page-object';

const expect = chai.expect;

describe('MPvpRegulation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPvpRegulationUpdatePage: MPvpRegulationUpdatePage;
  let mPvpRegulationComponentsPage: MPvpRegulationComponentsPage;
  /*let mPvpRegulationDeleteDialog: MPvpRegulationDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPvpRegulations', async () => {
    await navBarPage.goToEntity('m-pvp-regulation');
    mPvpRegulationComponentsPage = new MPvpRegulationComponentsPage();
    await browser.wait(ec.visibilityOf(mPvpRegulationComponentsPage.title), 5000);
    expect(await mPvpRegulationComponentsPage.getTitle()).to.eq('M Pvp Regulations');
  });

  it('should load create MPvpRegulation page', async () => {
    await mPvpRegulationComponentsPage.clickOnCreateButton();
    mPvpRegulationUpdatePage = new MPvpRegulationUpdatePage();
    expect(await mPvpRegulationUpdatePage.getPageTitle()).to.eq('Create or edit a M Pvp Regulation');
    await mPvpRegulationUpdatePage.cancel();
  });

  /* it('should create and save MPvpRegulations', async () => {
        const nbButtonsBeforeCreate = await mPvpRegulationComponentsPage.countDeleteButtons();

        await mPvpRegulationComponentsPage.clickOnCreateButton();
        await promise.all([
            mPvpRegulationUpdatePage.setStartAtInput('5'),
            mPvpRegulationUpdatePage.setEndAtInput('5'),
            mPvpRegulationUpdatePage.setMatchOptionIdInput('5'),
            mPvpRegulationUpdatePage.setDeckConditionIdInput('5'),
            mPvpRegulationUpdatePage.setRuleTutorialIdInput('5'),
            mPvpRegulationUpdatePage.mmatchoptionSelectLastOption(),
        ]);
        expect(await mPvpRegulationUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
        expect(await mPvpRegulationUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
        expect(await mPvpRegulationUpdatePage.getMatchOptionIdInput()).to.eq('5', 'Expected matchOptionId value to be equals to 5');
        expect(await mPvpRegulationUpdatePage.getDeckConditionIdInput()).to.eq('5', 'Expected deckConditionId value to be equals to 5');
        expect(await mPvpRegulationUpdatePage.getRuleTutorialIdInput()).to.eq('5', 'Expected ruleTutorialId value to be equals to 5');
        await mPvpRegulationUpdatePage.save();
        expect(await mPvpRegulationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mPvpRegulationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MPvpRegulation', async () => {
        const nbButtonsBeforeDelete = await mPvpRegulationComponentsPage.countDeleteButtons();
        await mPvpRegulationComponentsPage.clickOnLastDeleteButton();

        mPvpRegulationDeleteDialog = new MPvpRegulationDeleteDialog();
        expect(await mPvpRegulationDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Pvp Regulation?');
        await mPvpRegulationDeleteDialog.clickOnConfirmButton();

        expect(await mPvpRegulationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
