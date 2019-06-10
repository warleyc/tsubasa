/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MActionSkillHolderCardCtComponentsPage,
  MActionSkillHolderCardCtDeleteDialog,
  MActionSkillHolderCardCtUpdatePage
} from './m-action-skill-holder-card-ct.page-object';

const expect = chai.expect;

describe('MActionSkillHolderCardCt e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionSkillHolderCardCtUpdatePage: MActionSkillHolderCardCtUpdatePage;
  let mActionSkillHolderCardCtComponentsPage: MActionSkillHolderCardCtComponentsPage;
  /*let mActionSkillHolderCardCtDeleteDialog: MActionSkillHolderCardCtDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActionSkillHolderCardCts', async () => {
    await navBarPage.goToEntity('m-action-skill-holder-card-ct');
    mActionSkillHolderCardCtComponentsPage = new MActionSkillHolderCardCtComponentsPage();
    await browser.wait(ec.visibilityOf(mActionSkillHolderCardCtComponentsPage.title), 5000);
    expect(await mActionSkillHolderCardCtComponentsPage.getTitle()).to.eq('M Action Skill Holder Card Cts');
  });

  it('should load create MActionSkillHolderCardCt page', async () => {
    await mActionSkillHolderCardCtComponentsPage.clickOnCreateButton();
    mActionSkillHolderCardCtUpdatePage = new MActionSkillHolderCardCtUpdatePage();
    expect(await mActionSkillHolderCardCtUpdatePage.getPageTitle()).to.eq('Create or edit a M Action Skill Holder Card Ct');
    await mActionSkillHolderCardCtUpdatePage.cancel();
  });

  /* it('should create and save MActionSkillHolderCardCts', async () => {
        const nbButtonsBeforeCreate = await mActionSkillHolderCardCtComponentsPage.countDeleteButtons();

        await mActionSkillHolderCardCtComponentsPage.clickOnCreateButton();
        await promise.all([
            mActionSkillHolderCardCtUpdatePage.setTargetCharaIdInput('5'),
            mActionSkillHolderCardCtUpdatePage.setActionMasterIdInput('5'),
            mActionSkillHolderCardCtUpdatePage.setActionSkillExpInput('5'),
            mActionSkillHolderCardCtUpdatePage.setNameInput('name'),
            mActionSkillHolderCardCtUpdatePage.setDescriptionInput('description'),
            mActionSkillHolderCardCtUpdatePage.idSelectLastOption(),
        ]);
        expect(await mActionSkillHolderCardCtUpdatePage.getTargetCharaIdInput()).to.eq('5', 'Expected targetCharaId value to be equals to 5');
        expect(await mActionSkillHolderCardCtUpdatePage.getActionMasterIdInput()).to.eq('5', 'Expected actionMasterId value to be equals to 5');
        expect(await mActionSkillHolderCardCtUpdatePage.getActionSkillExpInput()).to.eq('5', 'Expected actionSkillExp value to be equals to 5');
        expect(await mActionSkillHolderCardCtUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mActionSkillHolderCardCtUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        await mActionSkillHolderCardCtUpdatePage.save();
        expect(await mActionSkillHolderCardCtUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mActionSkillHolderCardCtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MActionSkillHolderCardCt', async () => {
        const nbButtonsBeforeDelete = await mActionSkillHolderCardCtComponentsPage.countDeleteButtons();
        await mActionSkillHolderCardCtComponentsPage.clickOnLastDeleteButton();

        mActionSkillHolderCardCtDeleteDialog = new MActionSkillHolderCardCtDeleteDialog();
        expect(await mActionSkillHolderCardCtDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Action Skill Holder Card Ct?');
        await mActionSkillHolderCardCtDeleteDialog.clickOnConfirmButton();

        expect(await mActionSkillHolderCardCtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
