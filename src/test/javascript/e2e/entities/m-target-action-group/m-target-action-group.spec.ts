/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetActionGroupComponentsPage,
  MTargetActionGroupDeleteDialog,
  MTargetActionGroupUpdatePage
} from './m-target-action-group.page-object';

const expect = chai.expect;

describe('MTargetActionGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetActionGroupUpdatePage: MTargetActionGroupUpdatePage;
  let mTargetActionGroupComponentsPage: MTargetActionGroupComponentsPage;
  /*let mTargetActionGroupDeleteDialog: MTargetActionGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetActionGroups', async () => {
    await navBarPage.goToEntity('m-target-action-group');
    mTargetActionGroupComponentsPage = new MTargetActionGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetActionGroupComponentsPage.title), 5000);
    expect(await mTargetActionGroupComponentsPage.getTitle()).to.eq('M Target Action Groups');
  });

  it('should load create MTargetActionGroup page', async () => {
    await mTargetActionGroupComponentsPage.clickOnCreateButton();
    mTargetActionGroupUpdatePage = new MTargetActionGroupUpdatePage();
    expect(await mTargetActionGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Action Group');
    await mTargetActionGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetActionGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetActionGroupComponentsPage.countDeleteButtons();

        await mTargetActionGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetActionGroupUpdatePage.setGroupIdInput('5'),
            mTargetActionGroupUpdatePage.setActionIdInput('5'),
            mTargetActionGroupUpdatePage.mactionSelectLastOption(),
        ]);
        expect(await mTargetActionGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetActionGroupUpdatePage.getActionIdInput()).to.eq('5', 'Expected actionId value to be equals to 5');
        await mTargetActionGroupUpdatePage.save();
        expect(await mTargetActionGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetActionGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetActionGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetActionGroupComponentsPage.countDeleteButtons();
        await mTargetActionGroupComponentsPage.clickOnLastDeleteButton();

        mTargetActionGroupDeleteDialog = new MTargetActionGroupDeleteDialog();
        expect(await mTargetActionGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Action Group?');
        await mTargetActionGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetActionGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
