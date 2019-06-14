/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGuildLevelComponentsPage, MGuildLevelDeleteDialog, MGuildLevelUpdatePage } from './m-guild-level.page-object';

const expect = chai.expect;

describe('MGuildLevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildLevelUpdatePage: MGuildLevelUpdatePage;
  let mGuildLevelComponentsPage: MGuildLevelComponentsPage;
  let mGuildLevelDeleteDialog: MGuildLevelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildLevels', async () => {
    await navBarPage.goToEntity('m-guild-level');
    mGuildLevelComponentsPage = new MGuildLevelComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildLevelComponentsPage.title), 5000);
    expect(await mGuildLevelComponentsPage.getTitle()).to.eq('M Guild Levels');
  });

  it('should load create MGuildLevel page', async () => {
    await mGuildLevelComponentsPage.clickOnCreateButton();
    mGuildLevelUpdatePage = new MGuildLevelUpdatePage();
    expect(await mGuildLevelUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Level');
    await mGuildLevelUpdatePage.cancel();
  });

  it('should create and save MGuildLevels', async () => {
    const nbButtonsBeforeCreate = await mGuildLevelComponentsPage.countDeleteButtons();

    await mGuildLevelComponentsPage.clickOnCreateButton();
    await promise.all([
      mGuildLevelUpdatePage.setLevelInput('5'),
      mGuildLevelUpdatePage.setExpInput('5'),
      mGuildLevelUpdatePage.setMemberCountInput('5'),
      mGuildLevelUpdatePage.setRecommendMemberCountInput('5'),
      mGuildLevelUpdatePage.setGuildMedalInput('5')
    ]);
    expect(await mGuildLevelUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mGuildLevelUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    expect(await mGuildLevelUpdatePage.getMemberCountInput()).to.eq('5', 'Expected memberCount value to be equals to 5');
    expect(await mGuildLevelUpdatePage.getRecommendMemberCountInput()).to.eq('5', 'Expected recommendMemberCount value to be equals to 5');
    expect(await mGuildLevelUpdatePage.getGuildMedalInput()).to.eq('5', 'Expected guildMedal value to be equals to 5');
    await mGuildLevelUpdatePage.save();
    expect(await mGuildLevelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGuildLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MGuildLevel', async () => {
    const nbButtonsBeforeDelete = await mGuildLevelComponentsPage.countDeleteButtons();
    await mGuildLevelComponentsPage.clickOnLastDeleteButton();

    mGuildLevelDeleteDialog = new MGuildLevelDeleteDialog();
    expect(await mGuildLevelDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Guild Level?');
    await mGuildLevelDeleteDialog.clickOnConfirmButton();

    expect(await mGuildLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
