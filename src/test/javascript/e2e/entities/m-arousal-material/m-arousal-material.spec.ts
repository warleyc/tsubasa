/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MArousalMaterialComponentsPage, MArousalMaterialDeleteDialog, MArousalMaterialUpdatePage } from './m-arousal-material.page-object';

const expect = chai.expect;

describe('MArousalMaterial e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mArousalMaterialUpdatePage: MArousalMaterialUpdatePage;
  let mArousalMaterialComponentsPage: MArousalMaterialComponentsPage;
  let mArousalMaterialDeleteDialog: MArousalMaterialDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MArousalMaterials', async () => {
    await navBarPage.goToEntity('m-arousal-material');
    mArousalMaterialComponentsPage = new MArousalMaterialComponentsPage();
    await browser.wait(ec.visibilityOf(mArousalMaterialComponentsPage.title), 5000);
    expect(await mArousalMaterialComponentsPage.getTitle()).to.eq('M Arousal Materials');
  });

  it('should load create MArousalMaterial page', async () => {
    await mArousalMaterialComponentsPage.clickOnCreateButton();
    mArousalMaterialUpdatePage = new MArousalMaterialUpdatePage();
    expect(await mArousalMaterialUpdatePage.getPageTitle()).to.eq('Create or edit a M Arousal Material');
    await mArousalMaterialUpdatePage.cancel();
  });

  it('should create and save MArousalMaterials', async () => {
    const nbButtonsBeforeCreate = await mArousalMaterialComponentsPage.countDeleteButtons();

    await mArousalMaterialComponentsPage.clickOnCreateButton();
    await promise.all([
      mArousalMaterialUpdatePage.setGroupIdInput('5'),
      mArousalMaterialUpdatePage.setContentTypeInput('5'),
      mArousalMaterialUpdatePage.setContentIdInput('5'),
      mArousalMaterialUpdatePage.setContentAmountInput('5'),
      mArousalMaterialUpdatePage.setMainActionLevelInput('5'),
      mArousalMaterialUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mArousalMaterialUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mArousalMaterialUpdatePage.getContentTypeInput()).to.eq('5', 'Expected contentType value to be equals to 5');
    expect(await mArousalMaterialUpdatePage.getContentIdInput()).to.eq('5', 'Expected contentId value to be equals to 5');
    expect(await mArousalMaterialUpdatePage.getContentAmountInput()).to.eq('5', 'Expected contentAmount value to be equals to 5');
    expect(await mArousalMaterialUpdatePage.getMainActionLevelInput()).to.eq('5', 'Expected mainActionLevel value to be equals to 5');
    expect(await mArousalMaterialUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mArousalMaterialUpdatePage.save();
    expect(await mArousalMaterialUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mArousalMaterialComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MArousalMaterial', async () => {
    const nbButtonsBeforeDelete = await mArousalMaterialComponentsPage.countDeleteButtons();
    await mArousalMaterialComponentsPage.clickOnLastDeleteButton();

    mArousalMaterialDeleteDialog = new MArousalMaterialDeleteDialog();
    expect(await mArousalMaterialDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Arousal Material?');
    await mArousalMaterialDeleteDialog.clickOnConfirmButton();

    expect(await mArousalMaterialComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
