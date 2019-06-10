/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MColorPaletteComponentsPage, MColorPaletteDeleteDialog, MColorPaletteUpdatePage } from './m-color-palette.page-object';

const expect = chai.expect;

describe('MColorPalette e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mColorPaletteUpdatePage: MColorPaletteUpdatePage;
  let mColorPaletteComponentsPage: MColorPaletteComponentsPage;
  let mColorPaletteDeleteDialog: MColorPaletteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MColorPalettes', async () => {
    await navBarPage.goToEntity('m-color-palette');
    mColorPaletteComponentsPage = new MColorPaletteComponentsPage();
    await browser.wait(ec.visibilityOf(mColorPaletteComponentsPage.title), 5000);
    expect(await mColorPaletteComponentsPage.getTitle()).to.eq('M Color Palettes');
  });

  it('should load create MColorPalette page', async () => {
    await mColorPaletteComponentsPage.clickOnCreateButton();
    mColorPaletteUpdatePage = new MColorPaletteUpdatePage();
    expect(await mColorPaletteUpdatePage.getPageTitle()).to.eq('Create or edit a M Color Palette');
    await mColorPaletteUpdatePage.cancel();
  });

  it('should create and save MColorPalettes', async () => {
    const nbButtonsBeforeCreate = await mColorPaletteComponentsPage.countDeleteButtons();

    await mColorPaletteComponentsPage.clickOnCreateButton();
    await promise.all([mColorPaletteUpdatePage.setColorInput('color'), mColorPaletteUpdatePage.setOrderNumInput('5')]);
    expect(await mColorPaletteUpdatePage.getColorInput()).to.eq('color', 'Expected Color value to be equals to color');
    expect(await mColorPaletteUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
    await mColorPaletteUpdatePage.save();
    expect(await mColorPaletteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mColorPaletteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MColorPalette', async () => {
    const nbButtonsBeforeDelete = await mColorPaletteComponentsPage.countDeleteButtons();
    await mColorPaletteComponentsPage.clickOnLastDeleteButton();

    mColorPaletteDeleteDialog = new MColorPaletteDeleteDialog();
    expect(await mColorPaletteDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Color Palette?');
    await mColorPaletteDeleteDialog.clickOnConfirmButton();

    expect(await mColorPaletteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
