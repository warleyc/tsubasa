/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGuildEffectComponentsPage, MGuildEffectDeleteDialog, MGuildEffectUpdatePage } from './m-guild-effect.page-object';

const expect = chai.expect;

describe('MGuildEffect e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildEffectUpdatePage: MGuildEffectUpdatePage;
  let mGuildEffectComponentsPage: MGuildEffectComponentsPage;
  let mGuildEffectDeleteDialog: MGuildEffectDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildEffects', async () => {
    await navBarPage.goToEntity('m-guild-effect');
    mGuildEffectComponentsPage = new MGuildEffectComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildEffectComponentsPage.title), 5000);
    expect(await mGuildEffectComponentsPage.getTitle()).to.eq('M Guild Effects');
  });

  it('should load create MGuildEffect page', async () => {
    await mGuildEffectComponentsPage.clickOnCreateButton();
    mGuildEffectUpdatePage = new MGuildEffectUpdatePage();
    expect(await mGuildEffectUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Effect');
    await mGuildEffectUpdatePage.cancel();
  });

  it('should create and save MGuildEffects', async () => {
    const nbButtonsBeforeCreate = await mGuildEffectComponentsPage.countDeleteButtons();

    await mGuildEffectComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuildEffectUpdatePage.setEffectTypeInput('5'),
      mGuildEffectUpdatePage.setNameInput('name'),
      mGuildEffectUpdatePage.setEffectIdInput('5'),
      mGuildEffectUpdatePage.setThumbnailPathInput('thumbnailPath')
    ]);
    expect(await mGuildEffectUpdatePage.getEffectTypeInput()).to.eq('5', 'Expected effectType value to be equals to 5');
    expect(await mGuildEffectUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mGuildEffectUpdatePage.getEffectIdInput()).to.eq('5', 'Expected effectId value to be equals to 5');
    expect(await mGuildEffectUpdatePage.getThumbnailPathInput()).to.eq(
      'thumbnailPath',
      'Expected ThumbnailPath value to be equals to thumbnailPath'
    );
    await mGuildEffectUpdatePage.save();
    expect(await mGuildEffectUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildEffectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MGuildEffect', async () => {
    const nbButtonsBeforeDelete = await mGuildEffectComponentsPage.countDeleteButtons();
    await mGuildEffectComponentsPage.clickOnLastDeleteButton();

    mGuildEffectDeleteDialog = new MGuildEffectDeleteDialog();
    expect(await mGuildEffectDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Effect?');
    await mGuildEffectDeleteDialog.clickOnConfirmButton();

    expect(await mGuildEffectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
