/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCutShootOrbitComponentsPage, MCutShootOrbitDeleteDialog, MCutShootOrbitUpdatePage } from './m-cut-shoot-orbit.page-object';

const expect = chai.expect;

describe('MCutShootOrbit e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCutShootOrbitUpdatePage: MCutShootOrbitUpdatePage;
  let mCutShootOrbitComponentsPage: MCutShootOrbitComponentsPage;
  let mCutShootOrbitDeleteDialog: MCutShootOrbitDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCutShootOrbits', async () => {
    await navBarPage.goToEntity('m-cut-shoot-orbit');
    mCutShootOrbitComponentsPage = new MCutShootOrbitComponentsPage();
    await browser.wait(ec.visibilityOf(mCutShootOrbitComponentsPage.title), 5000);
    expect(await mCutShootOrbitComponentsPage.getTitle()).to.eq('M Cut Shoot Orbits');
  });

  it('should load create MCutShootOrbit page', async () => {
    await mCutShootOrbitComponentsPage.clickOnCreateButton();
    mCutShootOrbitUpdatePage = new MCutShootOrbitUpdatePage();
    expect(await mCutShootOrbitUpdatePage.getPageTitle()).to.eq('Create or edit a M Cut Shoot Orbit');
    await mCutShootOrbitUpdatePage.cancel();
  });

  it('should create and save MCutShootOrbits', async () => {
    const nbButtonsBeforeCreate = await mCutShootOrbitComponentsPage.countDeleteButtons();

    await mCutShootOrbitComponentsPage.clickOnCreateButton();
    await promise.all([
      mCutShootOrbitUpdatePage.setShootOrbitInput('5'),
      mCutShootOrbitUpdatePage.setShootResultInput('5'),
      mCutShootOrbitUpdatePage.setOffenseSequenceTextInput('offenseSequenceText'),
      mCutShootOrbitUpdatePage.setDefenseSequenceTextInput('defenseSequenceText')
    ]);
    expect(await mCutShootOrbitUpdatePage.getShootOrbitInput()).to.eq('5', 'Expected shootOrbit value to be equals to 5');
    expect(await mCutShootOrbitUpdatePage.getShootResultInput()).to.eq('5', 'Expected shootResult value to be equals to 5');
    expect(await mCutShootOrbitUpdatePage.getOffenseSequenceTextInput()).to.eq(
      'offenseSequenceText',
      'Expected OffenseSequenceText value to be equals to offenseSequenceText'
    );
    expect(await mCutShootOrbitUpdatePage.getDefenseSequenceTextInput()).to.eq(
      'defenseSequenceText',
      'Expected DefenseSequenceText value to be equals to defenseSequenceText'
    );
    await mCutShootOrbitUpdatePage.save();
    expect(await mCutShootOrbitUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCutShootOrbitComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCutShootOrbit', async () => {
    const nbButtonsBeforeDelete = await mCutShootOrbitComponentsPage.countDeleteButtons();
    await mCutShootOrbitComponentsPage.clickOnLastDeleteButton();

    mCutShootOrbitDeleteDialog = new MCutShootOrbitDeleteDialog();
    expect(await mCutShootOrbitDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Cut Shoot Orbit?');
    await mCutShootOrbitDeleteDialog.clickOnConfirmButton();

    expect(await mCutShootOrbitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
