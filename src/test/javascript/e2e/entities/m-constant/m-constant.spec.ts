/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MConstantComponentsPage, MConstantDeleteDialog, MConstantUpdatePage } from './m-constant.page-object';

const expect = chai.expect;

describe('MConstant e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mConstantUpdatePage: MConstantUpdatePage;
  let mConstantComponentsPage: MConstantComponentsPage;
  let mConstantDeleteDialog: MConstantDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MConstants', async () => {
    await navBarPage.goToEntity('m-constant');
    mConstantComponentsPage = new MConstantComponentsPage();
    await browser.wait(ec.visibilityOf(mConstantComponentsPage.title), 5000);
    expect(await mConstantComponentsPage.getTitle()).to.eq('M Constants');
  });

  it('should load create MConstant page', async () => {
    await mConstantComponentsPage.clickOnCreateButton();
    mConstantUpdatePage = new MConstantUpdatePage();
    expect(await mConstantUpdatePage.getPageTitle()).to.eq('Create or edit a M Constant');
    await mConstantUpdatePage.cancel();
  });

  it('should create and save MConstants', async () => {
    const nbButtonsBeforeCreate = await mConstantComponentsPage.countDeleteButtons();

    await mConstantComponentsPage.clickOnCreateButton();
    await promise.all([mConstantUpdatePage.setKeyInput('key'), mConstantUpdatePage.setValueInput('5')]);
    expect(await mConstantUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mConstantUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    await mConstantUpdatePage.save();
    expect(await mConstantUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mConstantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MConstant', async () => {
    const nbButtonsBeforeDelete = await mConstantComponentsPage.countDeleteButtons();
    await mConstantComponentsPage.clickOnLastDeleteButton();

    mConstantDeleteDialog = new MConstantDeleteDialog();
    expect(await mConstantDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Constant?');
    await mConstantDeleteDialog.clickOnConfirmButton();

    expect(await mConstantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
