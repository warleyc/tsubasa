/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRecommendFormationFilterElementComponentsPage,
  MRecommendFormationFilterElementDeleteDialog,
  MRecommendFormationFilterElementUpdatePage
} from './m-recommend-formation-filter-element.page-object';

const expect = chai.expect;

describe('MRecommendFormationFilterElement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRecommendFormationFilterElementUpdatePage: MRecommendFormationFilterElementUpdatePage;
  let mRecommendFormationFilterElementComponentsPage: MRecommendFormationFilterElementComponentsPage;
  let mRecommendFormationFilterElementDeleteDialog: MRecommendFormationFilterElementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRecommendFormationFilterElements', async () => {
    await navBarPage.goToEntity('m-recommend-formation-filter-element');
    mRecommendFormationFilterElementComponentsPage = new MRecommendFormationFilterElementComponentsPage();
    await browser.wait(ec.visibilityOf(mRecommendFormationFilterElementComponentsPage.title), 5000);
    expect(await mRecommendFormationFilterElementComponentsPage.getTitle()).to.eq('M Recommend Formation Filter Elements');
  });

  it('should load create MRecommendFormationFilterElement page', async () => {
    await mRecommendFormationFilterElementComponentsPage.clickOnCreateButton();
    mRecommendFormationFilterElementUpdatePage = new MRecommendFormationFilterElementUpdatePage();
    expect(await mRecommendFormationFilterElementUpdatePage.getPageTitle()).to.eq('Create or edit a M Recommend Formation Filter Element');
    await mRecommendFormationFilterElementUpdatePage.cancel();
  });

  it('should create and save MRecommendFormationFilterElements', async () => {
    const nbButtonsBeforeCreate = await mRecommendFormationFilterElementComponentsPage.countDeleteButtons();

    await mRecommendFormationFilterElementComponentsPage.clickOnCreateButton();
    await promise.all([
      mRecommendFormationFilterElementUpdatePage.setFilterTypeInput('5'),
      mRecommendFormationFilterElementUpdatePage.setElementIdInput('5'),
      mRecommendFormationFilterElementUpdatePage.setNameInput('name')
    ]);
    expect(await mRecommendFormationFilterElementUpdatePage.getFilterTypeInput()).to.eq('5', 'Expected filterType value to be equals to 5');
    expect(await mRecommendFormationFilterElementUpdatePage.getElementIdInput()).to.eq('5', 'Expected elementId value to be equals to 5');
    expect(await mRecommendFormationFilterElementUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await mRecommendFormationFilterElementUpdatePage.save();
    expect(await mRecommendFormationFilterElementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRecommendFormationFilterElementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRecommendFormationFilterElement', async () => {
    const nbButtonsBeforeDelete = await mRecommendFormationFilterElementComponentsPage.countDeleteButtons();
    await mRecommendFormationFilterElementComponentsPage.clickOnLastDeleteButton();

    mRecommendFormationFilterElementDeleteDialog = new MRecommendFormationFilterElementDeleteDialog();
    expect(await mRecommendFormationFilterElementDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Recommend Formation Filter Element?'
    );
    await mRecommendFormationFilterElementDeleteDialog.clickOnConfirmButton();

    expect(await mRecommendFormationFilterElementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
