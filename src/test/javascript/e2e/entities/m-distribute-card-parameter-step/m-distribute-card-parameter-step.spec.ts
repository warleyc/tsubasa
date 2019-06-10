/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MDistributeCardParameterStepComponentsPage,
  MDistributeCardParameterStepDeleteDialog,
  MDistributeCardParameterStepUpdatePage
} from './m-distribute-card-parameter-step.page-object';

const expect = chai.expect;

describe('MDistributeCardParameterStep e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDistributeCardParameterStepUpdatePage: MDistributeCardParameterStepUpdatePage;
  let mDistributeCardParameterStepComponentsPage: MDistributeCardParameterStepComponentsPage;
  let mDistributeCardParameterStepDeleteDialog: MDistributeCardParameterStepDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDistributeCardParameterSteps', async () => {
    await navBarPage.goToEntity('m-distribute-card-parameter-step');
    mDistributeCardParameterStepComponentsPage = new MDistributeCardParameterStepComponentsPage();
    await browser.wait(ec.visibilityOf(mDistributeCardParameterStepComponentsPage.title), 5000);
    expect(await mDistributeCardParameterStepComponentsPage.getTitle()).to.eq('M Distribute Card Parameter Steps');
  });

  it('should load create MDistributeCardParameterStep page', async () => {
    await mDistributeCardParameterStepComponentsPage.clickOnCreateButton();
    mDistributeCardParameterStepUpdatePage = new MDistributeCardParameterStepUpdatePage();
    expect(await mDistributeCardParameterStepUpdatePage.getPageTitle()).to.eq('Create or edit a M Distribute Card Parameter Step');
    await mDistributeCardParameterStepUpdatePage.cancel();
  });

  it('should create and save MDistributeCardParameterSteps', async () => {
    const nbButtonsBeforeCreate = await mDistributeCardParameterStepComponentsPage.countDeleteButtons();

    await mDistributeCardParameterStepComponentsPage.clickOnCreateButton();
    await promise.all([
      mDistributeCardParameterStepUpdatePage.setIsGkInput('5'),
      mDistributeCardParameterStepUpdatePage.setStepInput('5'),
      mDistributeCardParameterStepUpdatePage.setParamInput('5')
    ]);
    expect(await mDistributeCardParameterStepUpdatePage.getIsGkInput()).to.eq('5', 'Expected isGk value to be equals to 5');
    expect(await mDistributeCardParameterStepUpdatePage.getStepInput()).to.eq('5', 'Expected step value to be equals to 5');
    expect(await mDistributeCardParameterStepUpdatePage.getParamInput()).to.eq('5', 'Expected param value to be equals to 5');
    await mDistributeCardParameterStepUpdatePage.save();
    expect(await mDistributeCardParameterStepUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDistributeCardParameterStepComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDistributeCardParameterStep', async () => {
    const nbButtonsBeforeDelete = await mDistributeCardParameterStepComponentsPage.countDeleteButtons();
    await mDistributeCardParameterStepComponentsPage.clickOnLastDeleteButton();

    mDistributeCardParameterStepDeleteDialog = new MDistributeCardParameterStepDeleteDialog();
    expect(await mDistributeCardParameterStepDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Distribute Card Parameter Step?'
    );
    await mDistributeCardParameterStepDeleteDialog.clickOnConfirmButton();

    expect(await mDistributeCardParameterStepComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
