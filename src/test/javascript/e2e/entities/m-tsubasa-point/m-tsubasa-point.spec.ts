/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTsubasaPointComponentsPage, MTsubasaPointDeleteDialog, MTsubasaPointUpdatePage } from './m-tsubasa-point.page-object';

const expect = chai.expect;

describe('MTsubasaPoint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTsubasaPointUpdatePage: MTsubasaPointUpdatePage;
  let mTsubasaPointComponentsPage: MTsubasaPointComponentsPage;
  let mTsubasaPointDeleteDialog: MTsubasaPointDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTsubasaPoints', async () => {
    await navBarPage.goToEntity('m-tsubasa-point');
    mTsubasaPointComponentsPage = new MTsubasaPointComponentsPage();
    await browser.wait(ec.visibilityOf(mTsubasaPointComponentsPage.title), 5000);
    expect(await mTsubasaPointComponentsPage.getTitle()).to.eq('M Tsubasa Points');
  });

  it('should load create MTsubasaPoint page', async () => {
    await mTsubasaPointComponentsPage.clickOnCreateButton();
    mTsubasaPointUpdatePage = new MTsubasaPointUpdatePage();
    expect(await mTsubasaPointUpdatePage.getPageTitle()).to.eq('Create or edit a M Tsubasa Point');
    await mTsubasaPointUpdatePage.cancel();
  });

  it('should create and save MTsubasaPoints', async () => {
    const nbButtonsBeforeCreate = await mTsubasaPointComponentsPage.countDeleteButtons();

    await mTsubasaPointComponentsPage.clickOnCreateButton();
    await promise.all([
      mTsubasaPointUpdatePage.setMatchTypeInput('5'),
      mTsubasaPointUpdatePage.setPointTypeInput('5'),
      mTsubasaPointUpdatePage.setCalcTypeInput('5'),
      mTsubasaPointUpdatePage.setAValueInput('5'),
      mTsubasaPointUpdatePage.setBValueInput('5'),
      mTsubasaPointUpdatePage.setOrderNumInput('5')
    ]);
    expect(await mTsubasaPointUpdatePage.getMatchTypeInput()).to.eq('5', 'Expected matchType value to be equals to 5');
    expect(await mTsubasaPointUpdatePage.getPointTypeInput()).to.eq('5', 'Expected pointType value to be equals to 5');
    expect(await mTsubasaPointUpdatePage.getCalcTypeInput()).to.eq('5', 'Expected calcType value to be equals to 5');
    expect(await mTsubasaPointUpdatePage.getAValueInput()).to.eq('5', 'Expected aValue value to be equals to 5');
    expect(await mTsubasaPointUpdatePage.getBValueInput()).to.eq('5', 'Expected bValue value to be equals to 5');
    expect(await mTsubasaPointUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    await mTsubasaPointUpdatePage.save();
    expect(await mTsubasaPointUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTsubasaPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MTsubasaPoint', async () => {
    const nbButtonsBeforeDelete = await mTsubasaPointComponentsPage.countDeleteButtons();
    await mTsubasaPointComponentsPage.clickOnLastDeleteButton();

    mTsubasaPointDeleteDialog = new MTsubasaPointDeleteDialog();
    expect(await mTsubasaPointDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Tsubasa Point?');
    await mTsubasaPointDeleteDialog.clickOnConfirmButton();

    expect(await mTsubasaPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
