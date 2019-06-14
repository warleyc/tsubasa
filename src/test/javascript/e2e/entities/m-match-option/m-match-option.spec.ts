/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMatchOptionComponentsPage, MMatchOptionDeleteDialog, MMatchOptionUpdatePage } from './m-match-option.page-object';

const expect = chai.expect;

describe('MMatchOption e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMatchOptionUpdatePage: MMatchOptionUpdatePage;
  let mMatchOptionComponentsPage: MMatchOptionComponentsPage;
  /*let mMatchOptionDeleteDialog: MMatchOptionDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMatchOptions', async () => {
    await navBarPage.goToEntity('m-match-option');
    mMatchOptionComponentsPage = new MMatchOptionComponentsPage();
    await browser.wait(ec.visibilityOf(mMatchOptionComponentsPage.title), 5000);
    expect(await mMatchOptionComponentsPage.getTitle()).to.eq('M Match Options');
  });

  it('should load create MMatchOption page', async () => {
    await mMatchOptionComponentsPage.clickOnCreateButton();
    mMatchOptionUpdatePage = new MMatchOptionUpdatePage();
    expect(await mMatchOptionUpdatePage.getPageTitle()).to.eq('Create or edit a M Match Option');
    await mMatchOptionUpdatePage.cancel();
  });

  /* it('should create and save MMatchOptions', async () => {
        const nbButtonsBeforeCreate = await mMatchOptionComponentsPage.countDeleteButtons();

        await mMatchOptionComponentsPage.clickOnCreateButton();
        await promise.all([
            mMatchOptionUpdatePage.setPassiveEffectIdInput('5'),
            mMatchOptionUpdatePage.setPassiveEffectValueInput('5'),
            mMatchOptionUpdatePage.setActivateFullPowerTypeInput('5'),
            mMatchOptionUpdatePage.setAttributeMagnificationInput('5'),
            mMatchOptionUpdatePage.setUseStaminaMagnificationInput('5'),
            mMatchOptionUpdatePage.setIsIgnoreTeamSkillInput('5'),
            mMatchOptionUpdatePage.setStaminaInfinityTypeInput('5'),
            mMatchOptionUpdatePage.mpassiveeffectrangeSelectLastOption(),
        ]);
        expect(await mMatchOptionUpdatePage.getPassiveEffectIdInput()).to.eq('5', 'Expected passiveEffectId value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getPassiveEffectValueInput()).to.eq('5', 'Expected passiveEffectValue value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getActivateFullPowerTypeInput()).to.eq('5', 'Expected activateFullPowerType value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getAttributeMagnificationInput()).to.eq('5', 'Expected attributeMagnification value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getUseStaminaMagnificationInput()).to.eq('5', 'Expected useStaminaMagnification value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getIsIgnoreTeamSkillInput()).to.eq('5', 'Expected isIgnoreTeamSkill value to be equals to 5');
        expect(await mMatchOptionUpdatePage.getStaminaInfinityTypeInput()).to.eq('5', 'Expected staminaInfinityType value to be equals to 5');
        await mMatchOptionUpdatePage.save();
        expect(await mMatchOptionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mMatchOptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MMatchOption', async () => {
        const nbButtonsBeforeDelete = await mMatchOptionComponentsPage.countDeleteButtons();
        await mMatchOptionComponentsPage.clickOnLastDeleteButton();

        mMatchOptionDeleteDialog = new MMatchOptionDeleteDialog();
        expect(await mMatchOptionDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Match Option?');
        await mMatchOptionDeleteDialog.clickOnConfirmButton();

        expect(await mMatchOptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
