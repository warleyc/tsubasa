/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTeamEffectBaseComponentsPage, MTeamEffectBaseDeleteDialog, MTeamEffectBaseUpdatePage } from './m-team-effect-base.page-object';

const expect = chai.expect;

describe('MTeamEffectBase e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTeamEffectBaseUpdatePage: MTeamEffectBaseUpdatePage;
  let mTeamEffectBaseComponentsPage: MTeamEffectBaseComponentsPage;
  /*let mTeamEffectBaseDeleteDialog: MTeamEffectBaseDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTeamEffectBases', async () => {
    await navBarPage.goToEntity('m-team-effect-base');
    mTeamEffectBaseComponentsPage = new MTeamEffectBaseComponentsPage();
    await browser.wait(ec.visibilityOf(mTeamEffectBaseComponentsPage.title), 5000);
    expect(await mTeamEffectBaseComponentsPage.getTitle()).to.eq('M Team Effect Bases');
  });

  it('should load create MTeamEffectBase page', async () => {
    await mTeamEffectBaseComponentsPage.clickOnCreateButton();
    mTeamEffectBaseUpdatePage = new MTeamEffectBaseUpdatePage();
    expect(await mTeamEffectBaseUpdatePage.getPageTitle()).to.eq('Create or edit a M Team Effect Base');
    await mTeamEffectBaseUpdatePage.cancel();
  });

  /* it('should create and save MTeamEffectBases', async () => {
        const nbButtonsBeforeCreate = await mTeamEffectBaseComponentsPage.countDeleteButtons();

        await mTeamEffectBaseComponentsPage.clickOnCreateButton();
        await promise.all([
            mTeamEffectBaseUpdatePage.setNameInput('name'),
            mTeamEffectBaseUpdatePage.setRarityInput('5'),
            mTeamEffectBaseUpdatePage.setEffectValueMinInput('5'),
            mTeamEffectBaseUpdatePage.setEffectValueMaxInput('5'),
            mTeamEffectBaseUpdatePage.setTriggerProbabilityMinInput('5'),
            mTeamEffectBaseUpdatePage.setTriggerProbabilityMaxInput('5'),
            mTeamEffectBaseUpdatePage.setEffectIdInput('5'),
            mTeamEffectBaseUpdatePage.setEffectValueMin2Input('5'),
            mTeamEffectBaseUpdatePage.setEffectValueMax2Input('5'),
            mTeamEffectBaseUpdatePage.setTriggerProbabilityMin2Input('5'),
            mTeamEffectBaseUpdatePage.setTriggerProbabilityMax2Input('5'),
            mTeamEffectBaseUpdatePage.setEffectId2Input('5'),
            mTeamEffectBaseUpdatePage.setDescriptionInput('description'),
            mTeamEffectBaseUpdatePage.mpassiveeffectrangeSelectLastOption(),
        ]);
        expect(await mTeamEffectBaseUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mTeamEffectBaseUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectValueMinInput()).to.eq('5', 'Expected effectValueMin value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectValueMaxInput()).to.eq('5', 'Expected effectValueMax value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getTriggerProbabilityMinInput()).to.eq('5', 'Expected triggerProbabilityMin value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getTriggerProbabilityMaxInput()).to.eq('5', 'Expected triggerProbabilityMax value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectIdInput()).to.eq('5', 'Expected effectId value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectValueMin2Input()).to.eq('5', 'Expected effectValueMin2 value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectValueMax2Input()).to.eq('5', 'Expected effectValueMax2 value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getTriggerProbabilityMin2Input()).to.eq('5', 'Expected triggerProbabilityMin2 value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getTriggerProbabilityMax2Input()).to.eq('5', 'Expected triggerProbabilityMax2 value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getEffectId2Input()).to.eq('5', 'Expected effectId2 value to be equals to 5');
        expect(await mTeamEffectBaseUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        await mTeamEffectBaseUpdatePage.save();
        expect(await mTeamEffectBaseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTeamEffectBaseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTeamEffectBase', async () => {
        const nbButtonsBeforeDelete = await mTeamEffectBaseComponentsPage.countDeleteButtons();
        await mTeamEffectBaseComponentsPage.clickOnLastDeleteButton();

        mTeamEffectBaseDeleteDialog = new MTeamEffectBaseDeleteDialog();
        expect(await mTeamEffectBaseDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Team Effect Base?');
        await mTeamEffectBaseDeleteDialog.clickOnConfirmButton();

        expect(await mTeamEffectBaseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
