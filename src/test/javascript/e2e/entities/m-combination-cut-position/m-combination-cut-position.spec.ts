/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCombinationCutPositionComponentsPage,
  MCombinationCutPositionDeleteDialog,
  MCombinationCutPositionUpdatePage
} from './m-combination-cut-position.page-object';

const expect = chai.expect;

describe('MCombinationCutPosition e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCombinationCutPositionUpdatePage: MCombinationCutPositionUpdatePage;
  let mCombinationCutPositionComponentsPage: MCombinationCutPositionComponentsPage;
  /*let mCombinationCutPositionDeleteDialog: MCombinationCutPositionDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCombinationCutPositions', async () => {
    await navBarPage.goToEntity('m-combination-cut-position');
    mCombinationCutPositionComponentsPage = new MCombinationCutPositionComponentsPage();
    await browser.wait(ec.visibilityOf(mCombinationCutPositionComponentsPage.title), 5000);
    expect(await mCombinationCutPositionComponentsPage.getTitle()).to.eq('M Combination Cut Positions');
  });

  it('should load create MCombinationCutPosition page', async () => {
    await mCombinationCutPositionComponentsPage.clickOnCreateButton();
    mCombinationCutPositionUpdatePage = new MCombinationCutPositionUpdatePage();
    expect(await mCombinationCutPositionUpdatePage.getPageTitle()).to.eq('Create or edit a M Combination Cut Position');
    await mCombinationCutPositionUpdatePage.cancel();
  });

  /* it('should create and save MCombinationCutPositions', async () => {
        const nbButtonsBeforeCreate = await mCombinationCutPositionComponentsPage.countDeleteButtons();

        await mCombinationCutPositionComponentsPage.clickOnCreateButton();
        await promise.all([
            mCombinationCutPositionUpdatePage.setActionCutIdInput('5'),
            mCombinationCutPositionUpdatePage.setCharacterIdInput('5'),
            mCombinationCutPositionUpdatePage.setActivatorPositionInput('5'),
            mCombinationCutPositionUpdatePage.setParticipantPosition1Input('5'),
            mCombinationCutPositionUpdatePage.setParticipantPosition2Input('5'),
            mCombinationCutPositionUpdatePage.setParticipantPosition3Input('5'),
            mCombinationCutPositionUpdatePage.setParticipantPosition4Input('5'),
            mCombinationCutPositionUpdatePage.setParticipantPosition5Input('5'),
            mCombinationCutPositionUpdatePage.idSelectLastOption(),
        ]);
        expect(await mCombinationCutPositionUpdatePage.getActionCutIdInput()).to.eq('5', 'Expected actionCutId value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getActivatorPositionInput()).to.eq('5', 'Expected activatorPosition value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getParticipantPosition1Input()).to.eq('5', 'Expected participantPosition1 value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getParticipantPosition2Input()).to.eq('5', 'Expected participantPosition2 value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getParticipantPosition3Input()).to.eq('5', 'Expected participantPosition3 value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getParticipantPosition4Input()).to.eq('5', 'Expected participantPosition4 value to be equals to 5');
        expect(await mCombinationCutPositionUpdatePage.getParticipantPosition5Input()).to.eq('5', 'Expected participantPosition5 value to be equals to 5');
        await mCombinationCutPositionUpdatePage.save();
        expect(await mCombinationCutPositionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mCombinationCutPositionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MCombinationCutPosition', async () => {
        const nbButtonsBeforeDelete = await mCombinationCutPositionComponentsPage.countDeleteButtons();
        await mCombinationCutPositionComponentsPage.clickOnLastDeleteButton();

        mCombinationCutPositionDeleteDialog = new MCombinationCutPositionDeleteDialog();
        expect(await mCombinationCutPositionDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Combination Cut Position?');
        await mCombinationCutPositionDeleteDialog.clickOnConfirmButton();

        expect(await mCombinationCutPositionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
