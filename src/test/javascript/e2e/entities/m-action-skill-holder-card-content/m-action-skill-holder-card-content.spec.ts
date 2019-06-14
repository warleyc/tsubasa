/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MActionSkillHolderCardContentComponentsPage,
  MActionSkillHolderCardContentDeleteDialog,
  MActionSkillHolderCardContentUpdatePage
} from './m-action-skill-holder-card-content.page-object';

const expect = chai.expect;

describe('MActionSkillHolderCardContent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionSkillHolderCardContentUpdatePage: MActionSkillHolderCardContentUpdatePage;
  let mActionSkillHolderCardContentComponentsPage: MActionSkillHolderCardContentComponentsPage;
  /*let mActionSkillHolderCardContentDeleteDialog: MActionSkillHolderCardContentDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActionSkillHolderCardContents', async () => {
    await navBarPage.goToEntity('m-action-skill-holder-card-content');
    mActionSkillHolderCardContentComponentsPage = new MActionSkillHolderCardContentComponentsPage();
    await browser.wait(ec.visibilityOf(mActionSkillHolderCardContentComponentsPage.title), 5000);
    expect(await mActionSkillHolderCardContentComponentsPage.getTitle()).to.eq('M Action Skill Holder Card Contents');
  });

  it('should load create MActionSkillHolderCardContent page', async () => {
    await mActionSkillHolderCardContentComponentsPage.clickOnCreateButton();
    mActionSkillHolderCardContentUpdatePage = new MActionSkillHolderCardContentUpdatePage();
    expect(await mActionSkillHolderCardContentUpdatePage.getPageTitle()).to.eq('Create or edit a M Action Skill Holder Card Content');
    await mActionSkillHolderCardContentUpdatePage.cancel();
  });

  /* it('should create and save MActionSkillHolderCardContents', async () => {
        const nbButtonsBeforeCreate = await mActionSkillHolderCardContentComponentsPage.countDeleteButtons();

        await mActionSkillHolderCardContentComponentsPage.clickOnCreateButton();
        await promise.all([
            mActionSkillHolderCardContentUpdatePage.setTargetCharaIdInput('5'),
            mActionSkillHolderCardContentUpdatePage.setActionMasterIdInput('5'),
            mActionSkillHolderCardContentUpdatePage.setActionSkillExpInput('5'),
            mActionSkillHolderCardContentUpdatePage.setNameInput('name'),
            mActionSkillHolderCardContentUpdatePage.setDescriptionInput('description'),
            mActionSkillHolderCardContentUpdatePage.mcharacterSelectLastOption(),
        ]);
        expect(await mActionSkillHolderCardContentUpdatePage.getTargetCharaIdInput()).to.eq('5', 'Expected targetCharaId value to be equals to 5');
        expect(await mActionSkillHolderCardContentUpdatePage.getActionMasterIdInput()).to.eq('5', 'Expected actionMasterId value to be equals to 5');
        expect(await mActionSkillHolderCardContentUpdatePage.getActionSkillExpInput()).to.eq('5', 'Expected actionSkillExp value to be equals to 5');
        expect(await mActionSkillHolderCardContentUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mActionSkillHolderCardContentUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        await mActionSkillHolderCardContentUpdatePage.save();
        expect(await mActionSkillHolderCardContentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mActionSkillHolderCardContentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MActionSkillHolderCardContent', async () => {
        const nbButtonsBeforeDelete = await mActionSkillHolderCardContentComponentsPage.countDeleteButtons();
        await mActionSkillHolderCardContentComponentsPage.clickOnLastDeleteButton();

        mActionSkillHolderCardContentDeleteDialog = new MActionSkillHolderCardContentDeleteDialog();
        expect(await mActionSkillHolderCardContentDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Action Skill Holder Card Content?');
        await mActionSkillHolderCardContentDeleteDialog.clickOnConfirmButton();

        expect(await mActionSkillHolderCardContentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
