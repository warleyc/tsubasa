/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MEncountersCommandCompatibilityComponentsPage,
  MEncountersCommandCompatibilityDeleteDialog,
  MEncountersCommandCompatibilityUpdatePage
} from './m-encounters-command-compatibility.page-object';

const expect = chai.expect;

describe('MEncountersCommandCompatibility e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEncountersCommandCompatibilityUpdatePage: MEncountersCommandCompatibilityUpdatePage;
  let mEncountersCommandCompatibilityComponentsPage: MEncountersCommandCompatibilityComponentsPage;
  let mEncountersCommandCompatibilityDeleteDialog: MEncountersCommandCompatibilityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEncountersCommandCompatibilities', async () => {
    await navBarPage.goToEntity('m-encounters-command-compatibility');
    mEncountersCommandCompatibilityComponentsPage = new MEncountersCommandCompatibilityComponentsPage();
    await browser.wait(ec.visibilityOf(mEncountersCommandCompatibilityComponentsPage.title), 5000);
    expect(await mEncountersCommandCompatibilityComponentsPage.getTitle()).to.eq('M Encounters Command Compatibilities');
  });

  it('should load create MEncountersCommandCompatibility page', async () => {
    await mEncountersCommandCompatibilityComponentsPage.clickOnCreateButton();
    mEncountersCommandCompatibilityUpdatePage = new MEncountersCommandCompatibilityUpdatePage();
    expect(await mEncountersCommandCompatibilityUpdatePage.getPageTitle()).to.eq('Create or edit a M Encounters Command Compatibility');
    await mEncountersCommandCompatibilityUpdatePage.cancel();
  });

  it('should create and save MEncountersCommandCompatibilities', async () => {
    const nbButtonsBeforeCreate = await mEncountersCommandCompatibilityComponentsPage.countDeleteButtons();

    await mEncountersCommandCompatibilityComponentsPage.clickOnCreateButton();
    await promise.all([
      mEncountersCommandCompatibilityUpdatePage.setEncountersTypeInput('5'),
      mEncountersCommandCompatibilityUpdatePage.setOffenseCommandTypeInput('5'),
      mEncountersCommandCompatibilityUpdatePage.setDefenseCommandTypeInput('5'),
      mEncountersCommandCompatibilityUpdatePage.setOffenseMagnificationInput('5'),
      mEncountersCommandCompatibilityUpdatePage.setDefenseMagnificationInput('5')
    ]);
    expect(await mEncountersCommandCompatibilityUpdatePage.getEncountersTypeInput()).to.eq(
      '5',
      'Expected encountersType value to be equals to 5'
    );
    expect(await mEncountersCommandCompatibilityUpdatePage.getOffenseCommandTypeInput()).to.eq(
      '5',
      'Expected offenseCommandType value to be equals to 5'
    );
    expect(await mEncountersCommandCompatibilityUpdatePage.getDefenseCommandTypeInput()).to.eq(
      '5',
      'Expected defenseCommandType value to be equals to 5'
    );
    expect(await mEncountersCommandCompatibilityUpdatePage.getOffenseMagnificationInput()).to.eq(
      '5',
      'Expected offenseMagnification value to be equals to 5'
    );
    expect(await mEncountersCommandCompatibilityUpdatePage.getDefenseMagnificationInput()).to.eq(
      '5',
      'Expected defenseMagnification value to be equals to 5'
    );
    await mEncountersCommandCompatibilityUpdatePage.save();
    expect(await mEncountersCommandCompatibilityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEncountersCommandCompatibilityComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MEncountersCommandCompatibility', async () => {
    const nbButtonsBeforeDelete = await mEncountersCommandCompatibilityComponentsPage.countDeleteButtons();
    await mEncountersCommandCompatibilityComponentsPage.clickOnLastDeleteButton();

    mEncountersCommandCompatibilityDeleteDialog = new MEncountersCommandCompatibilityDeleteDialog();
    expect(await mEncountersCommandCompatibilityDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Encounters Command Compatibility?'
    );
    await mEncountersCommandCompatibilityDeleteDialog.clickOnConfirmButton();

    expect(await mEncountersCommandCompatibilityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
