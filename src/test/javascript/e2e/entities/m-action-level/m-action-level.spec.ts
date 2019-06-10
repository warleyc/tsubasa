/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MActionLevelComponentsPage, MActionLevelDeleteDialog, MActionLevelUpdatePage } from './m-action-level.page-object';

const expect = chai.expect;

describe('MActionLevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionLevelUpdatePage: MActionLevelUpdatePage;
  let mActionLevelComponentsPage: MActionLevelComponentsPage;
  let mActionLevelDeleteDialog: MActionLevelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActionLevels', async () => {
    await navBarPage.goToEntity('m-action-level');
    mActionLevelComponentsPage = new MActionLevelComponentsPage();
    await browser.wait(ec.visibilityOf(mActionLevelComponentsPage.title), 5000);
    expect(await mActionLevelComponentsPage.getTitle()).to.eq('M Action Levels');
  });

  it('should load create MActionLevel page', async () => {
    await mActionLevelComponentsPage.clickOnCreateButton();
    mActionLevelUpdatePage = new MActionLevelUpdatePage();
    expect(await mActionLevelUpdatePage.getPageTitle()).to.eq('Create or edit a M Action Level');
    await mActionLevelUpdatePage.cancel();
  });

  it('should create and save MActionLevels', async () => {
    const nbButtonsBeforeCreate = await mActionLevelComponentsPage.countDeleteButtons();

    await mActionLevelComponentsPage.clickOnCreateButton();
    await promise.all([
      mActionLevelUpdatePage.setRarityInput('5'),
      mActionLevelUpdatePage.setLevelInput('5'),
      mActionLevelUpdatePage.setExpInput('5')
    ]);
    expect(await mActionLevelUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mActionLevelUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mActionLevelUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    await mActionLevelUpdatePage.save();
    expect(await mActionLevelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mActionLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MActionLevel', async () => {
    const nbButtonsBeforeDelete = await mActionLevelComponentsPage.countDeleteButtons();
    await mActionLevelComponentsPage.clickOnLastDeleteButton();

    mActionLevelDeleteDialog = new MActionLevelDeleteDialog();
    expect(await mActionLevelDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Action Level?');
    await mActionLevelDeleteDialog.clickOnConfirmButton();

    expect(await mActionLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
