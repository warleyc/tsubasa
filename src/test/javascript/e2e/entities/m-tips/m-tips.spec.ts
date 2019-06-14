/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTipsComponentsPage, MTipsDeleteDialog, MTipsUpdatePage } from './m-tips.page-object';

const expect = chai.expect;

describe('MTips e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTipsUpdatePage: MTipsUpdatePage;
  let mTipsComponentsPage: MTipsComponentsPage;
  let mTipsDeleteDialog: MTipsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTips', async () => {
    await navBarPage.goToEntity('m-tips');
    mTipsComponentsPage = new MTipsComponentsPage();
    await browser.wait(ec.visibilityOf(mTipsComponentsPage.title), 5000);
    expect(await mTipsComponentsPage.getTitle()).to.eq('M Tips');
  });

  it('should load create MTips page', async () => {
    await mTipsComponentsPage.clickOnCreateButton();
    mTipsUpdatePage = new MTipsUpdatePage();
    expect(await mTipsUpdatePage.getPageTitle()).to.eq('Create or edit a M Tips');
    await mTipsUpdatePage.cancel();
  });

  it('should create and save MTips', async () => {
    const nbButtonsBeforeCreate = await mTipsComponentsPage.countDeleteButtons();

    await mTipsComponentsPage.clickOnCreateButton();
    await promise.all([
      mTipsUpdatePage.setPriorityInput('5'),
      mTipsUpdatePage.setTitleInput('title'),
      mTipsUpdatePage.setDescriptionInput('description'),
      mTipsUpdatePage.setImageAssetNameInput('imageAssetName'),
      mTipsUpdatePage.setColorTypeInput('5'),
      mTipsUpdatePage.setIsTipsInput('5'),
      mTipsUpdatePage.setStartAtInput('5'),
      mTipsUpdatePage.setEndAtInput('5')
    ]);
    expect(await mTipsUpdatePage.getPriorityInput()).to.eq('5', 'Expected priority value to be equals to 5');
    expect(await mTipsUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await mTipsUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await mTipsUpdatePage.getImageAssetNameInput()).to.eq(
      'imageAssetName',
      'Expected ImageAssetName value to be equals to imageAssetName'
    );
    expect(await mTipsUpdatePage.getColorTypeInput()).to.eq('5', 'Expected colorType value to be equals to 5');
    expect(await mTipsUpdatePage.getIsTipsInput()).to.eq('5', 'Expected isTips value to be equals to 5');
    expect(await mTipsUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mTipsUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mTipsUpdatePage.save();
    expect(await mTipsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTipsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MTips', async () => {
    const nbButtonsBeforeDelete = await mTipsComponentsPage.countDeleteButtons();
    await mTipsComponentsPage.clickOnLastDeleteButton();

    mTipsDeleteDialog = new MTipsDeleteDialog();
    expect(await mTipsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Tips?');
    await mTipsDeleteDialog.clickOnConfirmButton();

    expect(await mTipsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
