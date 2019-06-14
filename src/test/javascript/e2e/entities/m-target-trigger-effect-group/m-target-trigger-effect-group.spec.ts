/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetTriggerEffectGroupComponentsPage,
  MTargetTriggerEffectGroupDeleteDialog,
  MTargetTriggerEffectGroupUpdatePage
} from './m-target-trigger-effect-group.page-object';

const expect = chai.expect;

describe('MTargetTriggerEffectGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetTriggerEffectGroupUpdatePage: MTargetTriggerEffectGroupUpdatePage;
  let mTargetTriggerEffectGroupComponentsPage: MTargetTriggerEffectGroupComponentsPage;
  /*let mTargetTriggerEffectGroupDeleteDialog: MTargetTriggerEffectGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetTriggerEffectGroups', async () => {
    await navBarPage.goToEntity('m-target-trigger-effect-group');
    mTargetTriggerEffectGroupComponentsPage = new MTargetTriggerEffectGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetTriggerEffectGroupComponentsPage.title), 5000);
    expect(await mTargetTriggerEffectGroupComponentsPage.getTitle()).to.eq('M Target Trigger Effect Groups');
  });

  it('should load create MTargetTriggerEffectGroup page', async () => {
    await mTargetTriggerEffectGroupComponentsPage.clickOnCreateButton();
    mTargetTriggerEffectGroupUpdatePage = new MTargetTriggerEffectGroupUpdatePage();
    expect(await mTargetTriggerEffectGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Trigger Effect Group');
    await mTargetTriggerEffectGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetTriggerEffectGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetTriggerEffectGroupComponentsPage.countDeleteButtons();

        await mTargetTriggerEffectGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetTriggerEffectGroupUpdatePage.setGroupIdInput('5'),
            mTargetTriggerEffectGroupUpdatePage.setTriggerEffectIdInput('5'),
            mTargetTriggerEffectGroupUpdatePage.idSelectLastOption(),
        ]);
        expect(await mTargetTriggerEffectGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetTriggerEffectGroupUpdatePage.getTriggerEffectIdInput()).to.eq('5', 'Expected triggerEffectId value to be equals to 5');
        await mTargetTriggerEffectGroupUpdatePage.save();
        expect(await mTargetTriggerEffectGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetTriggerEffectGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetTriggerEffectGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetTriggerEffectGroupComponentsPage.countDeleteButtons();
        await mTargetTriggerEffectGroupComponentsPage.clickOnLastDeleteButton();

        mTargetTriggerEffectGroupDeleteDialog = new MTargetTriggerEffectGroupDeleteDialog();
        expect(await mTargetTriggerEffectGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Trigger Effect Group?');
        await mTargetTriggerEffectGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetTriggerEffectGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
