/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetNationalityGroupComponentsPage,
  MTargetNationalityGroupDeleteDialog,
  MTargetNationalityGroupUpdatePage
} from './m-target-nationality-group.page-object';

const expect = chai.expect;

describe('MTargetNationalityGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetNationalityGroupUpdatePage: MTargetNationalityGroupUpdatePage;
  let mTargetNationalityGroupComponentsPage: MTargetNationalityGroupComponentsPage;
  /*let mTargetNationalityGroupDeleteDialog: MTargetNationalityGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetNationalityGroups', async () => {
    await navBarPage.goToEntity('m-target-nationality-group');
    mTargetNationalityGroupComponentsPage = new MTargetNationalityGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetNationalityGroupComponentsPage.title), 5000);
    expect(await mTargetNationalityGroupComponentsPage.getTitle()).to.eq('M Target Nationality Groups');
  });

  it('should load create MTargetNationalityGroup page', async () => {
    await mTargetNationalityGroupComponentsPage.clickOnCreateButton();
    mTargetNationalityGroupUpdatePage = new MTargetNationalityGroupUpdatePage();
    expect(await mTargetNationalityGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Nationality Group');
    await mTargetNationalityGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetNationalityGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetNationalityGroupComponentsPage.countDeleteButtons();

        await mTargetNationalityGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetNationalityGroupUpdatePage.setGroupIdInput('5'),
            mTargetNationalityGroupUpdatePage.setNationalityIdInput('5'),
            mTargetNationalityGroupUpdatePage.idSelectLastOption(),
        ]);
        expect(await mTargetNationalityGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetNationalityGroupUpdatePage.getNationalityIdInput()).to.eq('5', 'Expected nationalityId value to be equals to 5');
        await mTargetNationalityGroupUpdatePage.save();
        expect(await mTargetNationalityGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetNationalityGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetNationalityGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetNationalityGroupComponentsPage.countDeleteButtons();
        await mTargetNationalityGroupComponentsPage.clickOnLastDeleteButton();

        mTargetNationalityGroupDeleteDialog = new MTargetNationalityGroupDeleteDialog();
        expect(await mTargetNationalityGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Nationality Group?');
        await mTargetNationalityGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetNationalityGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
