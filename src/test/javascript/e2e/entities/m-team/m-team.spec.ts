/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTeamComponentsPage, MTeamDeleteDialog, MTeamUpdatePage } from './m-team.page-object';

const expect = chai.expect;

describe('MTeam e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTeamUpdatePage: MTeamUpdatePage;
  let mTeamComponentsPage: MTeamComponentsPage;
  let mTeamDeleteDialog: MTeamDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTeams', async () => {
    await navBarPage.goToEntity('m-team');
    mTeamComponentsPage = new MTeamComponentsPage();
    await browser.wait(ec.visibilityOf(mTeamComponentsPage.title), 5000);
    expect(await mTeamComponentsPage.getTitle()).to.eq('M Teams');
  });

  it('should load create MTeam page', async () => {
    await mTeamComponentsPage.clickOnCreateButton();
    mTeamUpdatePage = new MTeamUpdatePage();
    expect(await mTeamUpdatePage.getPageTitle()).to.eq('Create or edit a M Team');
    await mTeamUpdatePage.cancel();
  });

  it('should create and save MTeams', async () => {
    const nbButtonsBeforeCreate = await mTeamComponentsPage.countDeleteButtons();

    await mTeamComponentsPage.clickOnCreateButton();
    await promise.all([mTeamUpdatePage.setNameInput('name')]);
    expect(await mTeamUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await mTeamUpdatePage.save();
    expect(await mTeamUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTeamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MTeam', async () => {
    const nbButtonsBeforeDelete = await mTeamComponentsPage.countDeleteButtons();
    await mTeamComponentsPage.clickOnLastDeleteButton();

    mTeamDeleteDialog = new MTeamDeleteDialog();
    expect(await mTeamDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Team?');
    await mTeamDeleteDialog.clickOnConfirmButton();

    expect(await mTeamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
