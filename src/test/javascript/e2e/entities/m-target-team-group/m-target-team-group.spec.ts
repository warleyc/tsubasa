/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetTeamGroupComponentsPage,
  MTargetTeamGroupDeleteDialog,
  MTargetTeamGroupUpdatePage
} from './m-target-team-group.page-object';

const expect = chai.expect;

describe('MTargetTeamGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetTeamGroupUpdatePage: MTargetTeamGroupUpdatePage;
  let mTargetTeamGroupComponentsPage: MTargetTeamGroupComponentsPage;
  /*let mTargetTeamGroupDeleteDialog: MTargetTeamGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetTeamGroups', async () => {
    await navBarPage.goToEntity('m-target-team-group');
    mTargetTeamGroupComponentsPage = new MTargetTeamGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetTeamGroupComponentsPage.title), 5000);
    expect(await mTargetTeamGroupComponentsPage.getTitle()).to.eq('M Target Team Groups');
  });

  it('should load create MTargetTeamGroup page', async () => {
    await mTargetTeamGroupComponentsPage.clickOnCreateButton();
    mTargetTeamGroupUpdatePage = new MTargetTeamGroupUpdatePage();
    expect(await mTargetTeamGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Team Group');
    await mTargetTeamGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetTeamGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetTeamGroupComponentsPage.countDeleteButtons();

        await mTargetTeamGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetTeamGroupUpdatePage.setGroupIdInput('5'),
            mTargetTeamGroupUpdatePage.setTeamIdInput('5'),
            mTargetTeamGroupUpdatePage.mteamSelectLastOption(),
        ]);
        expect(await mTargetTeamGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetTeamGroupUpdatePage.getTeamIdInput()).to.eq('5', 'Expected teamId value to be equals to 5');
        await mTargetTeamGroupUpdatePage.save();
        expect(await mTargetTeamGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetTeamGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetTeamGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetTeamGroupComponentsPage.countDeleteButtons();
        await mTargetTeamGroupComponentsPage.clickOnLastDeleteButton();

        mTargetTeamGroupDeleteDialog = new MTargetTeamGroupDeleteDialog();
        expect(await mTargetTeamGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Team Group?');
        await mTargetTeamGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetTeamGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
