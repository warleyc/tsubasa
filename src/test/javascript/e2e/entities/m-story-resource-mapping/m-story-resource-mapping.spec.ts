/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MStoryResourceMappingComponentsPage,
  MStoryResourceMappingDeleteDialog,
  MStoryResourceMappingUpdatePage
} from './m-story-resource-mapping.page-object';

const expect = chai.expect;

describe('MStoryResourceMapping e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mStoryResourceMappingUpdatePage: MStoryResourceMappingUpdatePage;
  let mStoryResourceMappingComponentsPage: MStoryResourceMappingComponentsPage;
  let mStoryResourceMappingDeleteDialog: MStoryResourceMappingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MStoryResourceMappings', async () => {
    await navBarPage.goToEntity('m-story-resource-mapping');
    mStoryResourceMappingComponentsPage = new MStoryResourceMappingComponentsPage();
    await browser.wait(ec.visibilityOf(mStoryResourceMappingComponentsPage.title), 5000);
    expect(await mStoryResourceMappingComponentsPage.getTitle()).to.eq('M Story Resource Mappings');
  });

  it('should load create MStoryResourceMapping page', async () => {
    await mStoryResourceMappingComponentsPage.clickOnCreateButton();
    mStoryResourceMappingUpdatePage = new MStoryResourceMappingUpdatePage();
    expect(await mStoryResourceMappingUpdatePage.getPageTitle()).to.eq('Create or edit a M Story Resource Mapping');
    await mStoryResourceMappingUpdatePage.cancel();
  });

  it('should create and save MStoryResourceMappings', async () => {
    const nbButtonsBeforeCreate = await mStoryResourceMappingComponentsPage.countDeleteButtons();

    await mStoryResourceMappingComponentsPage.clickOnCreateButton();
    await promise.all([
      mStoryResourceMappingUpdatePage.setLangInput('5'),
      mStoryResourceMappingUpdatePage.setScriptNameInput('scriptName'),
      mStoryResourceMappingUpdatePage.setIdsInput('ids')
    ]);
    expect(await mStoryResourceMappingUpdatePage.getLangInput()).to.eq('5', 'Expected lang value to be equals to 5');
    expect(await mStoryResourceMappingUpdatePage.getScriptNameInput()).to.eq(
      'scriptName',
      'Expected ScriptName value to be equals to scriptName'
    );
    expect(await mStoryResourceMappingUpdatePage.getIdsInput()).to.eq('ids', 'Expected Ids value to be equals to ids');
    await mStoryResourceMappingUpdatePage.save();
    expect(await mStoryResourceMappingUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mStoryResourceMappingComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MStoryResourceMapping', async () => {
    const nbButtonsBeforeDelete = await mStoryResourceMappingComponentsPage.countDeleteButtons();
    await mStoryResourceMappingComponentsPage.clickOnLastDeleteButton();

    mStoryResourceMappingDeleteDialog = new MStoryResourceMappingDeleteDialog();
    expect(await mStoryResourceMappingDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Story Resource Mapping?'
    );
    await mStoryResourceMappingDeleteDialog.clickOnConfirmButton();

    expect(await mStoryResourceMappingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
