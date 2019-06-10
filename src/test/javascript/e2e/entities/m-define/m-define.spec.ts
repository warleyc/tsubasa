/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDefineComponentsPage, MDefineDeleteDialog, MDefineUpdatePage } from './m-define.page-object';

const expect = chai.expect;

describe('MDefine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDefineUpdatePage: MDefineUpdatePage;
  let mDefineComponentsPage: MDefineComponentsPage;
  let mDefineDeleteDialog: MDefineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDefines', async () => {
    await navBarPage.goToEntity('m-define');
    mDefineComponentsPage = new MDefineComponentsPage();
    await browser.wait(ec.visibilityOf(mDefineComponentsPage.title), 5000);
    expect(await mDefineComponentsPage.getTitle()).to.eq('M Defines');
  });

  it('should load create MDefine page', async () => {
    await mDefineComponentsPage.clickOnCreateButton();
    mDefineUpdatePage = new MDefineUpdatePage();
    expect(await mDefineUpdatePage.getPageTitle()).to.eq('Create or edit a M Define');
    await mDefineUpdatePage.cancel();
  });

  it('should create and save MDefines', async () => {
    const nbButtonsBeforeCreate = await mDefineComponentsPage.countDeleteButtons();

    await mDefineComponentsPage.clickOnCreateButton();
    await promise.all([mDefineUpdatePage.setKeyInput('key'), mDefineUpdatePage.setValueInput('5')]);
    expect(await mDefineUpdatePage.getKeyInput()).to.eq('key', 'Expected Key value to be equals to key');
    expect(await mDefineUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    await mDefineUpdatePage.save();
    expect(await mDefineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDefineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MDefine', async () => {
    const nbButtonsBeforeDelete = await mDefineComponentsPage.countDeleteButtons();
    await mDefineComponentsPage.clickOnLastDeleteButton();

    mDefineDeleteDialog = new MDefineDeleteDialog();
    expect(await mDefineDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Define?');
    await mDefineDeleteDialog.clickOnConfirmButton();

    expect(await mDefineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
