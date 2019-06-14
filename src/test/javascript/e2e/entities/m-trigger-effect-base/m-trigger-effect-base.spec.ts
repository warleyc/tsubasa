/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTriggerEffectBaseComponentsPage,
  MTriggerEffectBaseDeleteDialog,
  MTriggerEffectBaseUpdatePage
} from './m-trigger-effect-base.page-object';

const expect = chai.expect;

describe('MTriggerEffectBase e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTriggerEffectBaseUpdatePage: MTriggerEffectBaseUpdatePage;
  let mTriggerEffectBaseComponentsPage: MTriggerEffectBaseComponentsPage;
  /*let mTriggerEffectBaseDeleteDialog: MTriggerEffectBaseDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTriggerEffectBases', async () => {
    await navBarPage.goToEntity('m-trigger-effect-base');
    mTriggerEffectBaseComponentsPage = new MTriggerEffectBaseComponentsPage();
    await browser.wait(ec.visibilityOf(mTriggerEffectBaseComponentsPage.title), 5000);
    expect(await mTriggerEffectBaseComponentsPage.getTitle()).to.eq('M Trigger Effect Bases');
  });

  it('should load create MTriggerEffectBase page', async () => {
    await mTriggerEffectBaseComponentsPage.clickOnCreateButton();
    mTriggerEffectBaseUpdatePage = new MTriggerEffectBaseUpdatePage();
    expect(await mTriggerEffectBaseUpdatePage.getPageTitle()).to.eq('Create or edit a M Trigger Effect Base');
    await mTriggerEffectBaseUpdatePage.cancel();
  });

  /* it('should create and save MTriggerEffectBases', async () => {
        const nbButtonsBeforeCreate = await mTriggerEffectBaseComponentsPage.countDeleteButtons();

        await mTriggerEffectBaseComponentsPage.clickOnCreateButton();
        await promise.all([
            mTriggerEffectBaseUpdatePage.setNameInput('name'),
            mTriggerEffectBaseUpdatePage.setRarityInput('5'),
            mTriggerEffectBaseUpdatePage.setEffectValueInput('5'),
            mTriggerEffectBaseUpdatePage.setTriggerProbabilityInput('5'),
            mTriggerEffectBaseUpdatePage.setTargetCardParameterInput('5'),
            mTriggerEffectBaseUpdatePage.setEffectIdInput('5'),
            mTriggerEffectBaseUpdatePage.setDescriptionInput('description'),
            mTriggerEffectBaseUpdatePage.mtriggereffectrangeSelectLastOption(),
        ]);
        expect(await mTriggerEffectBaseUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mTriggerEffectBaseUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mTriggerEffectBaseUpdatePage.getEffectValueInput()).to.eq('5', 'Expected effectValue value to be equals to 5');
        expect(await mTriggerEffectBaseUpdatePage.getTriggerProbabilityInput()).to.eq('5', 'Expected triggerProbability value to be equals to 5');
        expect(await mTriggerEffectBaseUpdatePage.getTargetCardParameterInput()).to.eq('5', 'Expected targetCardParameter value to be equals to 5');
        expect(await mTriggerEffectBaseUpdatePage.getEffectIdInput()).to.eq('5', 'Expected effectId value to be equals to 5');
        expect(await mTriggerEffectBaseUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        await mTriggerEffectBaseUpdatePage.save();
        expect(await mTriggerEffectBaseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTriggerEffectBaseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTriggerEffectBase', async () => {
        const nbButtonsBeforeDelete = await mTriggerEffectBaseComponentsPage.countDeleteButtons();
        await mTriggerEffectBaseComponentsPage.clickOnLastDeleteButton();

        mTriggerEffectBaseDeleteDialog = new MTriggerEffectBaseDeleteDialog();
        expect(await mTriggerEffectBaseDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Trigger Effect Base?');
        await mTriggerEffectBaseDeleteDialog.clickOnConfirmButton();

        expect(await mTriggerEffectBaseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
