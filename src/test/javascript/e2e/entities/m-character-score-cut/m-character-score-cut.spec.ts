/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCharacterScoreCutComponentsPage,
  MCharacterScoreCutDeleteDialog,
  MCharacterScoreCutUpdatePage
} from './m-character-score-cut.page-object';

const expect = chai.expect;

describe('MCharacterScoreCut e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCharacterScoreCutUpdatePage: MCharacterScoreCutUpdatePage;
  let mCharacterScoreCutComponentsPage: MCharacterScoreCutComponentsPage;
  let mCharacterScoreCutDeleteDialog: MCharacterScoreCutDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCharacterScoreCuts', async () => {
    await navBarPage.goToEntity('m-character-score-cut');
    mCharacterScoreCutComponentsPage = new MCharacterScoreCutComponentsPage();
    await browser.wait(ec.visibilityOf(mCharacterScoreCutComponentsPage.title), 5000);
    expect(await mCharacterScoreCutComponentsPage.getTitle()).to.eq('M Character Score Cuts');
  });

  it('should load create MCharacterScoreCut page', async () => {
    await mCharacterScoreCutComponentsPage.clickOnCreateButton();
    mCharacterScoreCutUpdatePage = new MCharacterScoreCutUpdatePage();
    expect(await mCharacterScoreCutUpdatePage.getPageTitle()).to.eq('Create or edit a M Character Score Cut');
    await mCharacterScoreCutUpdatePage.cancel();
  });

  it('should create and save MCharacterScoreCuts', async () => {
    const nbButtonsBeforeCreate = await mCharacterScoreCutComponentsPage.countDeleteButtons();

    await mCharacterScoreCutComponentsPage.clickOnCreateButton();
    await promise.all([
      mCharacterScoreCutUpdatePage.setCharacterIdInput('5'),
      mCharacterScoreCutUpdatePage.setTeamIdInput('5'),
      mCharacterScoreCutUpdatePage.setScoreCutTypeInput('5')
    ]);
    expect(await mCharacterScoreCutUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
    expect(await mCharacterScoreCutUpdatePage.getTeamIdInput()).to.eq('5', 'Expected teamId value to be equals to 5');
    expect(await mCharacterScoreCutUpdatePage.getScoreCutTypeInput()).to.eq('5', 'Expected scoreCutType value to be equals to 5');
    await mCharacterScoreCutUpdatePage.save();
    expect(await mCharacterScoreCutUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCharacterScoreCutComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCharacterScoreCut', async () => {
    const nbButtonsBeforeDelete = await mCharacterScoreCutComponentsPage.countDeleteButtons();
    await mCharacterScoreCutComponentsPage.clickOnLastDeleteButton();

    mCharacterScoreCutDeleteDialog = new MCharacterScoreCutDeleteDialog();
    expect(await mCharacterScoreCutDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Character Score Cut?');
    await mCharacterScoreCutDeleteDialog.clickOnConfirmButton();

    expect(await mCharacterScoreCutComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
