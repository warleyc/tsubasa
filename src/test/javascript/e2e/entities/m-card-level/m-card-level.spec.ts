/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCardLevelComponentsPage, MCardLevelDeleteDialog, MCardLevelUpdatePage } from './m-card-level.page-object';

const expect = chai.expect;

describe('MCardLevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardLevelUpdatePage: MCardLevelUpdatePage;
  let mCardLevelComponentsPage: MCardLevelComponentsPage;
  let mCardLevelDeleteDialog: MCardLevelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardLevels', async () => {
    await navBarPage.goToEntity('m-card-level');
    mCardLevelComponentsPage = new MCardLevelComponentsPage();
    await browser.wait(ec.visibilityOf(mCardLevelComponentsPage.title), 5000);
    expect(await mCardLevelComponentsPage.getTitle()).to.eq('M Card Levels');
  });

  it('should load create MCardLevel page', async () => {
    await mCardLevelComponentsPage.clickOnCreateButton();
    mCardLevelUpdatePage = new MCardLevelUpdatePage();
    expect(await mCardLevelUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Level');
    await mCardLevelUpdatePage.cancel();
  });

  it('should create and save MCardLevels', async () => {
    const nbButtonsBeforeCreate = await mCardLevelComponentsPage.countDeleteButtons();

    await mCardLevelComponentsPage.clickOnCreateButton();
    await promise.all([
      mCardLevelUpdatePage.setRarityInput('5'),
      mCardLevelUpdatePage.setLevelInput('5'),
      mCardLevelUpdatePage.setGroupIdInput('5'),
      mCardLevelUpdatePage.setExpInput('5')
    ]);
    expect(await mCardLevelUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mCardLevelUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mCardLevelUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mCardLevelUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    await mCardLevelUpdatePage.save();
    expect(await mCardLevelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCardLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCardLevel', async () => {
    const nbButtonsBeforeDelete = await mCardLevelComponentsPage.countDeleteButtons();
    await mCardLevelComponentsPage.clickOnLastDeleteButton();

    mCardLevelDeleteDialog = new MCardLevelDeleteDialog();
    expect(await mCardLevelDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Card Level?');
    await mCardLevelDeleteDialog.clickOnConfirmButton();

    expect(await mCardLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
