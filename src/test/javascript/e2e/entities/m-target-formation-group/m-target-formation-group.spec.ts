/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetFormationGroupComponentsPage,
  MTargetFormationGroupDeleteDialog,
  MTargetFormationGroupUpdatePage
} from './m-target-formation-group.page-object';

const expect = chai.expect;

describe('MTargetFormationGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetFormationGroupUpdatePage: MTargetFormationGroupUpdatePage;
  let mTargetFormationGroupComponentsPage: MTargetFormationGroupComponentsPage;
  /*let mTargetFormationGroupDeleteDialog: MTargetFormationGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetFormationGroups', async () => {
    await navBarPage.goToEntity('m-target-formation-group');
    mTargetFormationGroupComponentsPage = new MTargetFormationGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetFormationGroupComponentsPage.title), 5000);
    expect(await mTargetFormationGroupComponentsPage.getTitle()).to.eq('M Target Formation Groups');
  });

  it('should load create MTargetFormationGroup page', async () => {
    await mTargetFormationGroupComponentsPage.clickOnCreateButton();
    mTargetFormationGroupUpdatePage = new MTargetFormationGroupUpdatePage();
    expect(await mTargetFormationGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Formation Group');
    await mTargetFormationGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetFormationGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetFormationGroupComponentsPage.countDeleteButtons();

        await mTargetFormationGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetFormationGroupUpdatePage.setGroupIdInput('5'),
            mTargetFormationGroupUpdatePage.setFormationIdInput('5'),
            mTargetFormationGroupUpdatePage.idSelectLastOption(),
        ]);
        expect(await mTargetFormationGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetFormationGroupUpdatePage.getFormationIdInput()).to.eq('5', 'Expected formationId value to be equals to 5');
        await mTargetFormationGroupUpdatePage.save();
        expect(await mTargetFormationGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetFormationGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetFormationGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetFormationGroupComponentsPage.countDeleteButtons();
        await mTargetFormationGroupComponentsPage.clickOnLastDeleteButton();

        mTargetFormationGroupDeleteDialog = new MTargetFormationGroupDeleteDialog();
        expect(await mTargetFormationGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Formation Group?');
        await mTargetFormationGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetFormationGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
