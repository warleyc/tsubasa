/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MShootComponentsPage, MShootDeleteDialog, MShootUpdatePage } from './m-shoot.page-object';

const expect = chai.expect;

describe('MShoot e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mShootUpdatePage: MShootUpdatePage;
  let mShootComponentsPage: MShootComponentsPage;
  let mShootDeleteDialog: MShootDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MShoots', async () => {
    await navBarPage.goToEntity('m-shoot');
    mShootComponentsPage = new MShootComponentsPage();
    await browser.wait(ec.visibilityOf(mShootComponentsPage.title), 5000);
    expect(await mShootComponentsPage.getTitle()).to.eq('M Shoots');
  });

  it('should load create MShoot page', async () => {
    await mShootComponentsPage.clickOnCreateButton();
    mShootUpdatePage = new MShootUpdatePage();
    expect(await mShootUpdatePage.getPageTitle()).to.eq('Create or edit a M Shoot');
    await mShootUpdatePage.cancel();
  });

  it('should create and save MShoots', async () => {
    const nbButtonsBeforeCreate = await mShootComponentsPage.countDeleteButtons();

    await mShootComponentsPage.clickOnCreateButton();
    await promise.all([
      mShootUpdatePage.setAngleDecayTypeInput('5'),
      mShootUpdatePage.setShootOrbitInput('5'),
      mShootUpdatePage.setJudgementIdInput('5')
    ]);
    expect(await mShootUpdatePage.getAngleDecayTypeInput()).to.eq('5', 'Expected angleDecayType value to be equals to 5');
    expect(await mShootUpdatePage.getShootOrbitInput()).to.eq('5', 'Expected shootOrbit value to be equals to 5');
    expect(await mShootUpdatePage.getJudgementIdInput()).to.eq('5', 'Expected judgementId value to be equals to 5');
    await mShootUpdatePage.save();
    expect(await mShootUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mShootComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MShoot', async () => {
    const nbButtonsBeforeDelete = await mShootComponentsPage.countDeleteButtons();
    await mShootComponentsPage.clickOnLastDeleteButton();

    mShootDeleteDialog = new MShootDeleteDialog();
    expect(await mShootDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Shoot?');
    await mShootDeleteDialog.clickOnConfirmButton();

    expect(await mShootComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
