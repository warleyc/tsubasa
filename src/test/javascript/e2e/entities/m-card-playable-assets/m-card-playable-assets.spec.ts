/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCardPlayableAssetsComponentsPage,
  MCardPlayableAssetsDeleteDialog,
  MCardPlayableAssetsUpdatePage
} from './m-card-playable-assets.page-object';

const expect = chai.expect;

describe('MCardPlayableAssets e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardPlayableAssetsUpdatePage: MCardPlayableAssetsUpdatePage;
  let mCardPlayableAssetsComponentsPage: MCardPlayableAssetsComponentsPage;
  let mCardPlayableAssetsDeleteDialog: MCardPlayableAssetsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardPlayableAssets', async () => {
    await navBarPage.goToEntity('m-card-playable-assets');
    mCardPlayableAssetsComponentsPage = new MCardPlayableAssetsComponentsPage();
    await browser.wait(ec.visibilityOf(mCardPlayableAssetsComponentsPage.title), 5000);
    expect(await mCardPlayableAssetsComponentsPage.getTitle()).to.eq('M Card Playable Assets');
  });

  it('should load create MCardPlayableAssets page', async () => {
    await mCardPlayableAssetsComponentsPage.clickOnCreateButton();
    mCardPlayableAssetsUpdatePage = new MCardPlayableAssetsUpdatePage();
    expect(await mCardPlayableAssetsUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Playable Assets');
    await mCardPlayableAssetsUpdatePage.cancel();
  });

  it('should create and save MCardPlayableAssets', async () => {
    const nbButtonsBeforeCreate = await mCardPlayableAssetsComponentsPage.countDeleteButtons();

    await mCardPlayableAssetsComponentsPage.clickOnCreateButton();
    await promise.all([
      mCardPlayableAssetsUpdatePage.setCutinImageAssetNameInput('cutinImageAssetName'),
      mCardPlayableAssetsUpdatePage.setSoundEventSuffixInput('soundEventSuffix')
    ]);
    expect(await mCardPlayableAssetsUpdatePage.getCutinImageAssetNameInput()).to.eq(
      'cutinImageAssetName',
      'Expected CutinImageAssetName value to be equals to cutinImageAssetName'
    );
    expect(await mCardPlayableAssetsUpdatePage.getSoundEventSuffixInput()).to.eq(
      'soundEventSuffix',
      'Expected SoundEventSuffix value to be equals to soundEventSuffix'
    );
    await mCardPlayableAssetsUpdatePage.save();
    expect(await mCardPlayableAssetsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCardPlayableAssetsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCardPlayableAssets', async () => {
    const nbButtonsBeforeDelete = await mCardPlayableAssetsComponentsPage.countDeleteButtons();
    await mCardPlayableAssetsComponentsPage.clickOnLastDeleteButton();

    mCardPlayableAssetsDeleteDialog = new MCardPlayableAssetsDeleteDialog();
    expect(await mCardPlayableAssetsDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Card Playable Assets?');
    await mCardPlayableAssetsDeleteDialog.clickOnConfirmButton();

    expect(await mCardPlayableAssetsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
