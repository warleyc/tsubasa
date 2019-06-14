/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetActionTypeGroupComponentsPage,
  MTargetActionTypeGroupDeleteDialog,
  MTargetActionTypeGroupUpdatePage
} from './m-target-action-type-group.page-object';

const expect = chai.expect;

describe('MTargetActionTypeGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetActionTypeGroupUpdatePage: MTargetActionTypeGroupUpdatePage;
  let mTargetActionTypeGroupComponentsPage: MTargetActionTypeGroupComponentsPage;
  let mTargetActionTypeGroupDeleteDialog: MTargetActionTypeGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetActionTypeGroups', async () => {
    await navBarPage.goToEntity('m-target-action-type-group');
    mTargetActionTypeGroupComponentsPage = new MTargetActionTypeGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetActionTypeGroupComponentsPage.title), 5000);
    expect(await mTargetActionTypeGroupComponentsPage.getTitle()).to.eq('M Target Action Type Groups');
  });

  it('should load create MTargetActionTypeGroup page', async () => {
    await mTargetActionTypeGroupComponentsPage.clickOnCreateButton();
    mTargetActionTypeGroupUpdatePage = new MTargetActionTypeGroupUpdatePage();
    expect(await mTargetActionTypeGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Action Type Group');
    await mTargetActionTypeGroupUpdatePage.cancel();
  });

  it('should create and save MTargetActionTypeGroups', async () => {
    const nbButtonsBeforeCreate = await mTargetActionTypeGroupComponentsPage.countDeleteButtons();

    await mTargetActionTypeGroupComponentsPage.clickOnCreateButton();
    await promise.all([mTargetActionTypeGroupUpdatePage.setGroupIdInput('5'), mTargetActionTypeGroupUpdatePage.setCommandTypeInput('5')]);
    expect(await mTargetActionTypeGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mTargetActionTypeGroupUpdatePage.getCommandTypeInput()).to.eq('5', 'Expected commandType value to be equals to 5');
    await mTargetActionTypeGroupUpdatePage.save();
    expect(await mTargetActionTypeGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTargetActionTypeGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTargetActionTypeGroup', async () => {
    const nbButtonsBeforeDelete = await mTargetActionTypeGroupComponentsPage.countDeleteButtons();
    await mTargetActionTypeGroupComponentsPage.clickOnLastDeleteButton();

    mTargetActionTypeGroupDeleteDialog = new MTargetActionTypeGroupDeleteDialog();
    expect(await mTargetActionTypeGroupDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Target Action Type Group?'
    );
    await mTargetActionTypeGroupDeleteDialog.clickOnConfirmButton();

    expect(await mTargetActionTypeGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
