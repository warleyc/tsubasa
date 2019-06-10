/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCharacterComponentsPage, MCharacterDeleteDialog, MCharacterUpdatePage } from './m-character.page-object';

const expect = chai.expect;

describe('MCharacter e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCharacterUpdatePage: MCharacterUpdatePage;
  let mCharacterComponentsPage: MCharacterComponentsPage;
  let mCharacterDeleteDialog: MCharacterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCharacters', async () => {
    await navBarPage.goToEntity('m-character');
    mCharacterComponentsPage = new MCharacterComponentsPage();
    await browser.wait(ec.visibilityOf(mCharacterComponentsPage.title), 5000);
    expect(await mCharacterComponentsPage.getTitle()).to.eq('M Characters');
  });

  it('should load create MCharacter page', async () => {
    await mCharacterComponentsPage.clickOnCreateButton();
    mCharacterUpdatePage = new MCharacterUpdatePage();
    expect(await mCharacterUpdatePage.getPageTitle()).to.eq('Create or edit a M Character');
    await mCharacterUpdatePage.cancel();
  });

  it('should create and save MCharacters', async () => {
    const nbButtonsBeforeCreate = await mCharacterComponentsPage.countDeleteButtons();

    await mCharacterComponentsPage.clickOnCreateButton();
    await promise.all([
      mCharacterUpdatePage.setNameInput('name'),
      mCharacterUpdatePage.setAnnounceNameInput('announceName'),
      mCharacterUpdatePage.setShortNameInput('shortName'),
      mCharacterUpdatePage.setCharacterBookPriorityInput('5')
    ]);
    expect(await mCharacterUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mCharacterUpdatePage.getAnnounceNameInput()).to.eq(
      'announceName',
      'Expected AnnounceName value to be equals to announceName'
    );
    expect(await mCharacterUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mCharacterUpdatePage.getCharacterBookPriorityInput()).to.eq('5', 'Expected characterBookPriority value to be equals to 5');
    await mCharacterUpdatePage.save();
    expect(await mCharacterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCharacterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MCharacter', async () => {
    const nbButtonsBeforeDelete = await mCharacterComponentsPage.countDeleteButtons();
    await mCharacterComponentsPage.clickOnLastDeleteButton();

    mCharacterDeleteDialog = new MCharacterDeleteDialog();
    expect(await mCharacterDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Character?');
    await mCharacterDeleteDialog.clickOnConfirmButton();

    expect(await mCharacterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
