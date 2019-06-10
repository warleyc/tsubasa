/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MDistributeParamPointComponentsPage,
  MDistributeParamPointDeleteDialog,
  MDistributeParamPointUpdatePage
} from './m-distribute-param-point.page-object';

const expect = chai.expect;

describe('MDistributeParamPoint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDistributeParamPointUpdatePage: MDistributeParamPointUpdatePage;
  let mDistributeParamPointComponentsPage: MDistributeParamPointComponentsPage;
  let mDistributeParamPointDeleteDialog: MDistributeParamPointDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDistributeParamPoints', async () => {
    await navBarPage.goToEntity('m-distribute-param-point');
    mDistributeParamPointComponentsPage = new MDistributeParamPointComponentsPage();
    await browser.wait(ec.visibilityOf(mDistributeParamPointComponentsPage.title), 5000);
    expect(await mDistributeParamPointComponentsPage.getTitle()).to.eq('M Distribute Param Points');
  });

  it('should load create MDistributeParamPoint page', async () => {
    await mDistributeParamPointComponentsPage.clickOnCreateButton();
    mDistributeParamPointUpdatePage = new MDistributeParamPointUpdatePage();
    expect(await mDistributeParamPointUpdatePage.getPageTitle()).to.eq('Create or edit a M Distribute Param Point');
    await mDistributeParamPointUpdatePage.cancel();
  });

  it('should create and save MDistributeParamPoints', async () => {
    const nbButtonsBeforeCreate = await mDistributeParamPointComponentsPage.countDeleteButtons();

    await mDistributeParamPointComponentsPage.clickOnCreateButton();
    await promise.all([
      mDistributeParamPointUpdatePage.setTargetAttributeInput('5'),
      mDistributeParamPointUpdatePage.setTargetNationalityGroupIdInput('5'),
      mDistributeParamPointUpdatePage.setNameInput('name'),
      mDistributeParamPointUpdatePage.setDescriptionInput('description'),
      mDistributeParamPointUpdatePage.setThumbnailAssetNameInput('thumbnailAssetName'),
      mDistributeParamPointUpdatePage.setIconAssetNameInput('iconAssetName')
    ]);
    expect(await mDistributeParamPointUpdatePage.getTargetAttributeInput()).to.eq('5', 'Expected targetAttribute value to be equals to 5');
    expect(await mDistributeParamPointUpdatePage.getTargetNationalityGroupIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupId value to be equals to 5'
    );
    expect(await mDistributeParamPointUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mDistributeParamPointUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mDistributeParamPointUpdatePage.getThumbnailAssetNameInput()).to.eq(
      'thumbnailAssetName',
      'Expected ThumbnailAssetName value to be equals to thumbnailAssetName'
    );
    expect(await mDistributeParamPointUpdatePage.getIconAssetNameInput()).to.eq(
      'iconAssetName',
      'Expected IconAssetName value to be equals to iconAssetName'
    );
    await mDistributeParamPointUpdatePage.save();
    expect(await mDistributeParamPointUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDistributeParamPointComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDistributeParamPoint', async () => {
    const nbButtonsBeforeDelete = await mDistributeParamPointComponentsPage.countDeleteButtons();
    await mDistributeParamPointComponentsPage.clickOnLastDeleteButton();

    mDistributeParamPointDeleteDialog = new MDistributeParamPointDeleteDialog();
    expect(await mDistributeParamPointDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Distribute Param Point?'
    );
    await mDistributeParamPointDeleteDialog.clickOnConfirmButton();

    expect(await mDistributeParamPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
