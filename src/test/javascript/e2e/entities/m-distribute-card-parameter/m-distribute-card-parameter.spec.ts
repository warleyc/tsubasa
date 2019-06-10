/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MDistributeCardParameterComponentsPage,
  MDistributeCardParameterDeleteDialog,
  MDistributeCardParameterUpdatePage
} from './m-distribute-card-parameter.page-object';

const expect = chai.expect;

describe('MDistributeCardParameter e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDistributeCardParameterUpdatePage: MDistributeCardParameterUpdatePage;
  let mDistributeCardParameterComponentsPage: MDistributeCardParameterComponentsPage;
  let mDistributeCardParameterDeleteDialog: MDistributeCardParameterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDistributeCardParameters', async () => {
    await navBarPage.goToEntity('m-distribute-card-parameter');
    mDistributeCardParameterComponentsPage = new MDistributeCardParameterComponentsPage();
    await browser.wait(ec.visibilityOf(mDistributeCardParameterComponentsPage.title), 5000);
    expect(await mDistributeCardParameterComponentsPage.getTitle()).to.eq('M Distribute Card Parameters');
  });

  it('should load create MDistributeCardParameter page', async () => {
    await mDistributeCardParameterComponentsPage.clickOnCreateButton();
    mDistributeCardParameterUpdatePage = new MDistributeCardParameterUpdatePage();
    expect(await mDistributeCardParameterUpdatePage.getPageTitle()).to.eq('Create or edit a M Distribute Card Parameter');
    await mDistributeCardParameterUpdatePage.cancel();
  });

  it('should create and save MDistributeCardParameters', async () => {
    const nbButtonsBeforeCreate = await mDistributeCardParameterComponentsPage.countDeleteButtons();

    await mDistributeCardParameterComponentsPage.clickOnCreateButton();
    await promise.all([
      mDistributeCardParameterUpdatePage.setRarityInput('5'),
      mDistributeCardParameterUpdatePage.setIsGkInput('5'),
      mDistributeCardParameterUpdatePage.setMaxStepCountInput('5'),
      mDistributeCardParameterUpdatePage.setMaxTotalStepCountInput('5'),
      mDistributeCardParameterUpdatePage.setDistributePointByStepInput('5')
    ]);
    expect(await mDistributeCardParameterUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mDistributeCardParameterUpdatePage.getIsGkInput()).to.eq('5', 'Expected isGk value to be equals to 5');
    expect(await mDistributeCardParameterUpdatePage.getMaxStepCountInput()).to.eq('5', 'Expected maxStepCount value to be equals to 5');
    expect(await mDistributeCardParameterUpdatePage.getMaxTotalStepCountInput()).to.eq(
      '5',
      'Expected maxTotalStepCount value to be equals to 5'
    );
    expect(await mDistributeCardParameterUpdatePage.getDistributePointByStepInput()).to.eq(
      '5',
      'Expected distributePointByStep value to be equals to 5'
    );
    await mDistributeCardParameterUpdatePage.save();
    expect(await mDistributeCardParameterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDistributeCardParameterComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDistributeCardParameter', async () => {
    const nbButtonsBeforeDelete = await mDistributeCardParameterComponentsPage.countDeleteButtons();
    await mDistributeCardParameterComponentsPage.clickOnLastDeleteButton();

    mDistributeCardParameterDeleteDialog = new MDistributeCardParameterDeleteDialog();
    expect(await mDistributeCardParameterDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Distribute Card Parameter?'
    );
    await mDistributeCardParameterDeleteDialog.clickOnConfirmButton();

    expect(await mDistributeCardParameterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
