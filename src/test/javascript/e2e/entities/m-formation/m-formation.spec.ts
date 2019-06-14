/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MFormationComponentsPage, MFormationDeleteDialog, MFormationUpdatePage } from './m-formation.page-object';

const expect = chai.expect;

describe('MFormation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mFormationUpdatePage: MFormationUpdatePage;
  let mFormationComponentsPage: MFormationComponentsPage;
  /*let mFormationDeleteDialog: MFormationDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MFormations', async () => {
    await navBarPage.goToEntity('m-formation');
    mFormationComponentsPage = new MFormationComponentsPage();
    await browser.wait(ec.visibilityOf(mFormationComponentsPage.title), 5000);
    expect(await mFormationComponentsPage.getTitle()).to.eq('M Formations');
  });

  it('should load create MFormation page', async () => {
    await mFormationComponentsPage.clickOnCreateButton();
    mFormationUpdatePage = new MFormationUpdatePage();
    expect(await mFormationUpdatePage.getPageTitle()).to.eq('Create or edit a M Formation');
    await mFormationUpdatePage.cancel();
  });

  /* it('should create and save MFormations', async () => {
        const nbButtonsBeforeCreate = await mFormationComponentsPage.countDeleteButtons();

        await mFormationComponentsPage.clickOnCreateButton();
        await promise.all([
            mFormationUpdatePage.setEffectValueInput('5'),
            mFormationUpdatePage.setEffectProbabilityInput('5'),
            mFormationUpdatePage.setFormationArrangementFwInput('formationArrangementFw'),
            mFormationUpdatePage.setFormationArrangementOmfInput('formationArrangementOmf'),
            mFormationUpdatePage.setFormationArrangementDmfInput('formationArrangementDmf'),
            mFormationUpdatePage.setFormationArrangementDfInput('formationArrangementDf'),
            mFormationUpdatePage.setEffectIdInput('5'),
            mFormationUpdatePage.setDescriptionInput('description'),
            mFormationUpdatePage.setShortDescriptionInput('shortDescription'),
            mFormationUpdatePage.setNameInput('name'),
            mFormationUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
            mFormationUpdatePage.setStartingUniformNosInput('startingUniformNos'),
            mFormationUpdatePage.setSubUniformNosInput('subUniformNos'),
            mFormationUpdatePage.setExTypeInput('5'),
            mFormationUpdatePage.setMatchFormationIdInput('5'),
            mFormationUpdatePage.mpassiveeffectrangeSelectLastOption(),
        ]);
        expect(await mFormationUpdatePage.getEffectValueInput()).to.eq('5', 'Expected effectValue value to be equals to 5');
        expect(await mFormationUpdatePage.getEffectProbabilityInput()).to.eq('5', 'Expected effectProbability value to be equals to 5');
        expect(await mFormationUpdatePage.getFormationArrangementFwInput()).to.eq('formationArrangementFw', 'Expected FormationArrangementFw value to be equals to formationArrangementFw');
        expect(await mFormationUpdatePage.getFormationArrangementOmfInput()).to.eq('formationArrangementOmf', 'Expected FormationArrangementOmf value to be equals to formationArrangementOmf');
        expect(await mFormationUpdatePage.getFormationArrangementDmfInput()).to.eq('formationArrangementDmf', 'Expected FormationArrangementDmf value to be equals to formationArrangementDmf');
        expect(await mFormationUpdatePage.getFormationArrangementDfInput()).to.eq('formationArrangementDf', 'Expected FormationArrangementDf value to be equals to formationArrangementDf');
        expect(await mFormationUpdatePage.getEffectIdInput()).to.eq('5', 'Expected effectId value to be equals to 5');
        expect(await mFormationUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await mFormationUpdatePage.getShortDescriptionInput()).to.eq('shortDescription', 'Expected ShortDescription value to be equals to shortDescription');
        expect(await mFormationUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mFormationUpdatePage.getThumbnailAssetNameInput()).to.eq('thumbnailAssetName', 'Expected ThumbnailAssetName value to be equals to thumbnailAssetName');
        expect(await mFormationUpdatePage.getStartingUniformNosInput()).to.eq('startingUniformNos', 'Expected StartingUniformNos value to be equals to startingUniformNos');
        expect(await mFormationUpdatePage.getSubUniformNosInput()).to.eq('subUniformNos', 'Expected SubUniformNos value to be equals to subUniformNos');
        expect(await mFormationUpdatePage.getExTypeInput()).to.eq('5', 'Expected exType value to be equals to 5');
        expect(await mFormationUpdatePage.getMatchFormationIdInput()).to.eq('5', 'Expected matchFormationId value to be equals to 5');
        await mFormationUpdatePage.save();
        expect(await mFormationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mFormationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MFormation', async () => {
        const nbButtonsBeforeDelete = await mFormationComponentsPage.countDeleteButtons();
        await mFormationComponentsPage.clickOnLastDeleteButton();

        mFormationDeleteDialog = new MFormationDeleteDialog();
        expect(await mFormationDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Formation?');
        await mFormationDeleteDialog.clickOnConfirmButton();

        expect(await mFormationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
