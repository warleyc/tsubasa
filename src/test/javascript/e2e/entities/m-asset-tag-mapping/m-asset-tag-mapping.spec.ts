/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MAssetTagMappingComponentsPage,
  MAssetTagMappingDeleteDialog,
  MAssetTagMappingUpdatePage
} from './m-asset-tag-mapping.page-object';

const expect = chai.expect;

describe('MAssetTagMapping e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAssetTagMappingUpdatePage: MAssetTagMappingUpdatePage;
  let mAssetTagMappingComponentsPage: MAssetTagMappingComponentsPage;
  let mAssetTagMappingDeleteDialog: MAssetTagMappingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAssetTagMappings', async () => {
    await navBarPage.goToEntity('m-asset-tag-mapping');
    mAssetTagMappingComponentsPage = new MAssetTagMappingComponentsPage();
    await browser.wait(ec.visibilityOf(mAssetTagMappingComponentsPage.title), 5000);
    expect(await mAssetTagMappingComponentsPage.getTitle()).to.eq('M Asset Tag Mappings');
  });

  it('should load create MAssetTagMapping page', async () => {
    await mAssetTagMappingComponentsPage.clickOnCreateButton();
    mAssetTagMappingUpdatePage = new MAssetTagMappingUpdatePage();
    expect(await mAssetTagMappingUpdatePage.getPageTitle()).to.eq('Create or edit a M Asset Tag Mapping');
    await mAssetTagMappingUpdatePage.cancel();
  });

  it('should create and save MAssetTagMappings', async () => {
    const nbButtonsBeforeCreate = await mAssetTagMappingComponentsPage.countDeleteButtons();

    await mAssetTagMappingComponentsPage.clickOnCreateButton();
    await promise.all([mAssetTagMappingUpdatePage.setTagInput('5'), mAssetTagMappingUpdatePage.setIdsInput('ids')]);
    expect(await mAssetTagMappingUpdatePage.getTagInput()).to.eq('5', 'Expected tag value to be equals to 5');
    expect(await mAssetTagMappingUpdatePage.getIdsInput()).to.eq('ids', 'Expected Ids value to be equals to ids');
    await mAssetTagMappingUpdatePage.save();
    expect(await mAssetTagMappingUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAssetTagMappingComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MAssetTagMapping', async () => {
    const nbButtonsBeforeDelete = await mAssetTagMappingComponentsPage.countDeleteButtons();
    await mAssetTagMappingComponentsPage.clickOnLastDeleteButton();

    mAssetTagMappingDeleteDialog = new MAssetTagMappingDeleteDialog();
    expect(await mAssetTagMappingDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Asset Tag Mapping?');
    await mAssetTagMappingDeleteDialog.clickOnConfirmButton();

    expect(await mAssetTagMappingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
