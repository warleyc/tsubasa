/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTeamEffectLevelComponentsPage,
  MTeamEffectLevelDeleteDialog,
  MTeamEffectLevelUpdatePage
} from './m-team-effect-level.page-object';

const expect = chai.expect;

describe('MTeamEffectLevel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTeamEffectLevelUpdatePage: MTeamEffectLevelUpdatePage;
  let mTeamEffectLevelComponentsPage: MTeamEffectLevelComponentsPage;
  let mTeamEffectLevelDeleteDialog: MTeamEffectLevelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTeamEffectLevels', async () => {
    await navBarPage.goToEntity('m-team-effect-level');
    mTeamEffectLevelComponentsPage = new MTeamEffectLevelComponentsPage();
    await browser.wait(ec.visibilityOf(mTeamEffectLevelComponentsPage.title), 5000);
    expect(await mTeamEffectLevelComponentsPage.getTitle()).to.eq('M Team Effect Levels');
  });

  it('should load create MTeamEffectLevel page', async () => {
    await mTeamEffectLevelComponentsPage.clickOnCreateButton();
    mTeamEffectLevelUpdatePage = new MTeamEffectLevelUpdatePage();
    expect(await mTeamEffectLevelUpdatePage.getPageTitle()).to.eq('Create or edit a M Team Effect Level');
    await mTeamEffectLevelUpdatePage.cancel();
  });

  it('should create and save MTeamEffectLevels', async () => {
    const nbButtonsBeforeCreate = await mTeamEffectLevelComponentsPage.countDeleteButtons();

    await mTeamEffectLevelComponentsPage.clickOnCreateButton();
    await promise.all([
      mTeamEffectLevelUpdatePage.setRarityInput('5'),
      mTeamEffectLevelUpdatePage.setLevelInput('5'),
      mTeamEffectLevelUpdatePage.setExpInput('5')
    ]);
    expect(await mTeamEffectLevelUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mTeamEffectLevelUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mTeamEffectLevelUpdatePage.getExpInput()).to.eq('5', 'Expected exp value to be equals to 5');
    await mTeamEffectLevelUpdatePage.save();
    expect(await mTeamEffectLevelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTeamEffectLevelComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTeamEffectLevel', async () => {
    const nbButtonsBeforeDelete = await mTeamEffectLevelComponentsPage.countDeleteButtons();
    await mTeamEffectLevelComponentsPage.clickOnLastDeleteButton();

    mTeamEffectLevelDeleteDialog = new MTeamEffectLevelDeleteDialog();
    expect(await mTeamEffectLevelDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Team Effect Level?');
    await mTeamEffectLevelDeleteDialog.clickOnConfirmButton();

    expect(await mTeamEffectLevelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
