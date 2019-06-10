/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MFullPowerPointComponentsPage, MFullPowerPointDeleteDialog, MFullPowerPointUpdatePage } from './m-full-power-point.page-object';

const expect = chai.expect;

describe('MFullPowerPoint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mFullPowerPointUpdatePage: MFullPowerPointUpdatePage;
  let mFullPowerPointComponentsPage: MFullPowerPointComponentsPage;
  let mFullPowerPointDeleteDialog: MFullPowerPointDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MFullPowerPoints', async () => {
    await navBarPage.goToEntity('m-full-power-point');
    mFullPowerPointComponentsPage = new MFullPowerPointComponentsPage();
    await browser.wait(ec.visibilityOf(mFullPowerPointComponentsPage.title), 5000);
    expect(await mFullPowerPointComponentsPage.getTitle()).to.eq('M Full Power Points');
  });

  it('should load create MFullPowerPoint page', async () => {
    await mFullPowerPointComponentsPage.clickOnCreateButton();
    mFullPowerPointUpdatePage = new MFullPowerPointUpdatePage();
    expect(await mFullPowerPointUpdatePage.getPageTitle()).to.eq('Create or edit a M Full Power Point');
    await mFullPowerPointUpdatePage.cancel();
  });

  it('should create and save MFullPowerPoints', async () => {
    const nbButtonsBeforeCreate = await mFullPowerPointComponentsPage.countDeleteButtons();

    await mFullPowerPointComponentsPage.clickOnCreateButton();
    await promise.all([mFullPowerPointUpdatePage.setPointTypeInput('5'), mFullPowerPointUpdatePage.setValueInput('5')]);
    expect(await mFullPowerPointUpdatePage.getPointTypeInput()).to.eq('5', 'Expected pointType value to be equals to 5');
    expect(await mFullPowerPointUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    await mFullPowerPointUpdatePage.save();
    expect(await mFullPowerPointUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mFullPowerPointComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MFullPowerPoint', async () => {
    const nbButtonsBeforeDelete = await mFullPowerPointComponentsPage.countDeleteButtons();
    await mFullPowerPointComponentsPage.clickOnLastDeleteButton();

    mFullPowerPointDeleteDialog = new MFullPowerPointDeleteDialog();
    expect(await mFullPowerPointDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Full Power Point?');
    await mFullPowerPointDeleteDialog.clickOnConfirmButton();

    expect(await mFullPowerPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
