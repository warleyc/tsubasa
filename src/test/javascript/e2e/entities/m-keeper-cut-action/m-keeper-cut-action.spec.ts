/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MKeeperCutActionComponentsPage,
  MKeeperCutActionDeleteDialog,
  MKeeperCutActionUpdatePage
} from './m-keeper-cut-action.page-object';

const expect = chai.expect;

describe('MKeeperCutAction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mKeeperCutActionUpdatePage: MKeeperCutActionUpdatePage;
  let mKeeperCutActionComponentsPage: MKeeperCutActionComponentsPage;
  let mKeeperCutActionDeleteDialog: MKeeperCutActionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MKeeperCutActions', async () => {
    await navBarPage.goToEntity('m-keeper-cut-action');
    mKeeperCutActionComponentsPage = new MKeeperCutActionComponentsPage();
    await browser.wait(ec.visibilityOf(mKeeperCutActionComponentsPage.title), 5000);
    expect(await mKeeperCutActionComponentsPage.getTitle()).to.eq('M Keeper Cut Actions');
  });

  it('should load create MKeeperCutAction page', async () => {
    await mKeeperCutActionComponentsPage.clickOnCreateButton();
    mKeeperCutActionUpdatePage = new MKeeperCutActionUpdatePage();
    expect(await mKeeperCutActionUpdatePage.getPageTitle()).to.eq('Create or edit a M Keeper Cut Action');
    await mKeeperCutActionUpdatePage.cancel();
  });

  it('should create and save MKeeperCutActions', async () => {
    const nbButtonsBeforeCreate = await mKeeperCutActionComponentsPage.countDeleteButtons();

    await mKeeperCutActionComponentsPage.clickOnCreateButton();
    await promise.all([
      mKeeperCutActionUpdatePage.setActionCutIdInput('5'),
      mKeeperCutActionUpdatePage.setKeeperCutActionTypeInput('5'),
      mKeeperCutActionUpdatePage.setCutSequenceTextInput('cutSequenceText')
    ]);
    expect(await mKeeperCutActionUpdatePage.getActionCutIdInput()).to.eq('5', 'Expected actionCutId value to be equals to 5');
    expect(await mKeeperCutActionUpdatePage.getKeeperCutActionTypeInput()).to.eq(
      '5',
      'Expected keeperCutActionType value to be equals to 5'
    );
    expect(await mKeeperCutActionUpdatePage.getCutSequenceTextInput()).to.eq(
      'cutSequenceText',
      'Expected CutSequenceText value to be equals to cutSequenceText'
    );
    await mKeeperCutActionUpdatePage.save();
    expect(await mKeeperCutActionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mKeeperCutActionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MKeeperCutAction', async () => {
    const nbButtonsBeforeDelete = await mKeeperCutActionComponentsPage.countDeleteButtons();
    await mKeeperCutActionComponentsPage.clickOnLastDeleteButton();

    mKeeperCutActionDeleteDialog = new MKeeperCutActionDeleteDialog();
    expect(await mKeeperCutActionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Keeper Cut Action?');
    await mKeeperCutActionDeleteDialog.clickOnConfirmButton();

    expect(await mKeeperCutActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
