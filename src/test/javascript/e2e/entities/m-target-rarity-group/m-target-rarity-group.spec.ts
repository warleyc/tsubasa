/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetRarityGroupComponentsPage,
  MTargetRarityGroupDeleteDialog,
  MTargetRarityGroupUpdatePage
} from './m-target-rarity-group.page-object';

const expect = chai.expect;

describe('MTargetRarityGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetRarityGroupUpdatePage: MTargetRarityGroupUpdatePage;
  let mTargetRarityGroupComponentsPage: MTargetRarityGroupComponentsPage;
  let mTargetRarityGroupDeleteDialog: MTargetRarityGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetRarityGroups', async () => {
    await navBarPage.goToEntity('m-target-rarity-group');
    mTargetRarityGroupComponentsPage = new MTargetRarityGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetRarityGroupComponentsPage.title), 5000);
    expect(await mTargetRarityGroupComponentsPage.getTitle()).to.eq('M Target Rarity Groups');
  });

  it('should load create MTargetRarityGroup page', async () => {
    await mTargetRarityGroupComponentsPage.clickOnCreateButton();
    mTargetRarityGroupUpdatePage = new MTargetRarityGroupUpdatePage();
    expect(await mTargetRarityGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Rarity Group');
    await mTargetRarityGroupUpdatePage.cancel();
  });

  it('should create and save MTargetRarityGroups', async () => {
    const nbButtonsBeforeCreate = await mTargetRarityGroupComponentsPage.countDeleteButtons();

    await mTargetRarityGroupComponentsPage.clickOnCreateButton();
    await promise.all([mTargetRarityGroupUpdatePage.setGroupIdInput('5'), mTargetRarityGroupUpdatePage.setCardRarityInput('5')]);
    expect(await mTargetRarityGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mTargetRarityGroupUpdatePage.getCardRarityInput()).to.eq('5', 'Expected cardRarity value to be equals to 5');
    await mTargetRarityGroupUpdatePage.save();
    expect(await mTargetRarityGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTargetRarityGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTargetRarityGroup', async () => {
    const nbButtonsBeforeDelete = await mTargetRarityGroupComponentsPage.countDeleteButtons();
    await mTargetRarityGroupComponentsPage.clickOnLastDeleteButton();

    mTargetRarityGroupDeleteDialog = new MTargetRarityGroupDeleteDialog();
    expect(await mTargetRarityGroupDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Target Rarity Group?');
    await mTargetRarityGroupDeleteDialog.clickOnConfirmButton();

    expect(await mTargetRarityGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
