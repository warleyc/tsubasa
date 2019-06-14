/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRecommendFormationFilterComponentsPage,
  MRecommendFormationFilterDeleteDialog,
  MRecommendFormationFilterUpdatePage
} from './m-recommend-formation-filter.page-object';

const expect = chai.expect;

describe('MRecommendFormationFilter e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRecommendFormationFilterUpdatePage: MRecommendFormationFilterUpdatePage;
  let mRecommendFormationFilterComponentsPage: MRecommendFormationFilterComponentsPage;
  let mRecommendFormationFilterDeleteDialog: MRecommendFormationFilterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRecommendFormationFilters', async () => {
    await navBarPage.goToEntity('m-recommend-formation-filter');
    mRecommendFormationFilterComponentsPage = new MRecommendFormationFilterComponentsPage();
    await browser.wait(ec.visibilityOf(mRecommendFormationFilterComponentsPage.title), 5000);
    expect(await mRecommendFormationFilterComponentsPage.getTitle()).to.eq('M Recommend Formation Filters');
  });

  it('should load create MRecommendFormationFilter page', async () => {
    await mRecommendFormationFilterComponentsPage.clickOnCreateButton();
    mRecommendFormationFilterUpdatePage = new MRecommendFormationFilterUpdatePage();
    expect(await mRecommendFormationFilterUpdatePage.getPageTitle()).to.eq('Create or edit a M Recommend Formation Filter');
    await mRecommendFormationFilterUpdatePage.cancel();
  });

  it('should create and save MRecommendFormationFilters', async () => {
    const nbButtonsBeforeCreate = await mRecommendFormationFilterComponentsPage.countDeleteButtons();

    await mRecommendFormationFilterComponentsPage.clickOnCreateButton();
    await promise.all([
      mRecommendFormationFilterUpdatePage.setFilterTypeInput('5'),
      mRecommendFormationFilterUpdatePage.setNameInput('name')
    ]);
    expect(await mRecommendFormationFilterUpdatePage.getFilterTypeInput()).to.eq('5', 'Expected filterType value to be equals to 5');
    expect(await mRecommendFormationFilterUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await mRecommendFormationFilterUpdatePage.save();
    expect(await mRecommendFormationFilterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRecommendFormationFilterComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRecommendFormationFilter', async () => {
    const nbButtonsBeforeDelete = await mRecommendFormationFilterComponentsPage.countDeleteButtons();
    await mRecommendFormationFilterComponentsPage.clickOnLastDeleteButton();

    mRecommendFormationFilterDeleteDialog = new MRecommendFormationFilterDeleteDialog();
    expect(await mRecommendFormationFilterDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Recommend Formation Filter?'
    );
    await mRecommendFormationFilterDeleteDialog.clickOnConfirmButton();

    expect(await mRecommendFormationFilterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
