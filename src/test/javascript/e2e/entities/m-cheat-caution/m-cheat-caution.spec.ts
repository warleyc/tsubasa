/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCheatCautionComponentsPage, MCheatCautionDeleteDialog, MCheatCautionUpdatePage } from './m-cheat-caution.page-object';

const expect = chai.expect;

describe('MCheatCaution e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCheatCautionUpdatePage: MCheatCautionUpdatePage;
  let mCheatCautionComponentsPage: MCheatCautionComponentsPage;
  let mCheatCautionDeleteDialog: MCheatCautionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCheatCautions', async () => {
    await navBarPage.goToEntity('m-cheat-caution');
    mCheatCautionComponentsPage = new MCheatCautionComponentsPage();
    await browser.wait(ec.visibilityOf(mCheatCautionComponentsPage.title), 5000);
    expect(await mCheatCautionComponentsPage.getTitle()).to.eq('M Cheat Cautions');
  });

  it('should load create MCheatCaution page', async () => {
    await mCheatCautionComponentsPage.clickOnCreateButton();
    mCheatCautionUpdatePage = new MCheatCautionUpdatePage();
    expect(await mCheatCautionUpdatePage.getPageTitle()).to.eq('Create or edit a M Cheat Caution');
    await mCheatCautionUpdatePage.cancel();
  });

  it('should create and save MCheatCautions', async () => {
    const nbButtonsBeforeCreate = await mCheatCautionComponentsPage.countDeleteButtons();

    await mCheatCautionComponentsPage.clickOnCreateButton();
    await promise.all([
      mCheatCautionUpdatePage.setCautionInput('5'),
      mCheatCautionUpdatePage.setDescriptionInput('description'),
      mCheatCautionUpdatePage.setImageAssetNameInput('imageAssetName')
    ]);
    expect(await mCheatCautionUpdatePage.getCautionInput()).to.eq('5', 'Expected caution value to be equals to 5');
    expect(await mCheatCautionUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mCheatCautionUpdatePage.getImageAssetNameInput()).to.eq(
      'imageAssetName',
      'Expected ImageAssetName value to be equals to imageAssetName'
    );
    await mCheatCautionUpdatePage.save();
    expect(await mCheatCautionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCheatCautionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCheatCaution', async () => {
    const nbButtonsBeforeDelete = await mCheatCautionComponentsPage.countDeleteButtons();
    await mCheatCautionComponentsPage.clickOnLastDeleteButton();

    mCheatCautionDeleteDialog = new MCheatCautionDeleteDialog();
    expect(await mCheatCautionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Cheat Caution?');
    await mCheatCautionDeleteDialog.clickOnConfirmButton();

    expect(await mCheatCautionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
