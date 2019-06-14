/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMatchEnvironmentComponentsPage,
  MMatchEnvironmentDeleteDialog,
  MMatchEnvironmentUpdatePage
} from './m-match-environment.page-object';

const expect = chai.expect;

describe('MMatchEnvironment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMatchEnvironmentUpdatePage: MMatchEnvironmentUpdatePage;
  let mMatchEnvironmentComponentsPage: MMatchEnvironmentComponentsPage;
  let mMatchEnvironmentDeleteDialog: MMatchEnvironmentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMatchEnvironments', async () => {
    await navBarPage.goToEntity('m-match-environment');
    mMatchEnvironmentComponentsPage = new MMatchEnvironmentComponentsPage();
    await browser.wait(ec.visibilityOf(mMatchEnvironmentComponentsPage.title), 5000);
    expect(await mMatchEnvironmentComponentsPage.getTitle()).to.eq('M Match Environments');
  });

  it('should load create MMatchEnvironment page', async () => {
    await mMatchEnvironmentComponentsPage.clickOnCreateButton();
    mMatchEnvironmentUpdatePage = new MMatchEnvironmentUpdatePage();
    expect(await mMatchEnvironmentUpdatePage.getPageTitle()).to.eq('Create or edit a M Match Environment');
    await mMatchEnvironmentUpdatePage.cancel();
  });

  it('should create and save MMatchEnvironments', async () => {
    const nbButtonsBeforeCreate = await mMatchEnvironmentComponentsPage.countDeleteButtons();

    await mMatchEnvironmentComponentsPage.clickOnCreateButton();
    await promise.all([
      mMatchEnvironmentUpdatePage.setCategoryInput('5'),
      mMatchEnvironmentUpdatePage.setSkyTexInput('skyTex'),
      mMatchEnvironmentUpdatePage.setBallInput('ball'),
      mMatchEnvironmentUpdatePage.setStadiumInput('stadium'),
      mMatchEnvironmentUpdatePage.setRainyAssetNameInput('rainyAssetName'),
      mMatchEnvironmentUpdatePage.setIsPlayRainySoundInput('5'),
      mMatchEnvironmentUpdatePage.setOffenseStartBgmInput('offenseStartBgm'),
      mMatchEnvironmentUpdatePage.setOffenseStopBgmInput('offenseStopBgm'),
      mMatchEnvironmentUpdatePage.setDefenseStartBgmInput('defenseStartBgm'),
      mMatchEnvironmentUpdatePage.setDefenseStopBgmInput('defenseStopBgm')
    ]);
    expect(await mMatchEnvironmentUpdatePage.getCategoryInput()).to.eq('5', 'Expected category value to be equals to 5');
    expect(await mMatchEnvironmentUpdatePage.getSkyTexInput()).to.eq('skyTex', 'Expected SkyTex value to be equals to skyTex');
    expect(await mMatchEnvironmentUpdatePage.getBallInput()).to.eq('ball', 'Expected Ball value to be equals to ball');
    expect(await mMatchEnvironmentUpdatePage.getStadiumInput()).to.eq('stadium', 'Expected Stadium value to be equals to stadium');
    expect(await mMatchEnvironmentUpdatePage.getRainyAssetNameInput()).to.eq(
      'rainyAssetName',
      'Expected RainyAssetName value to be equals to rainyAssetName'
    );
    expect(await mMatchEnvironmentUpdatePage.getIsPlayRainySoundInput()).to.eq('5', 'Expected isPlayRainySound value to be equals to 5');
    expect(await mMatchEnvironmentUpdatePage.getOffenseStartBgmInput()).to.eq(
      'offenseStartBgm',
      'Expected OffenseStartBgm value to be equals to offenseStartBgm'
    );
    expect(await mMatchEnvironmentUpdatePage.getOffenseStopBgmInput()).to.eq(
      'offenseStopBgm',
      'Expected OffenseStopBgm value to be equals to offenseStopBgm'
    );
    expect(await mMatchEnvironmentUpdatePage.getDefenseStartBgmInput()).to.eq(
      'defenseStartBgm',
      'Expected DefenseStartBgm value to be equals to defenseStartBgm'
    );
    expect(await mMatchEnvironmentUpdatePage.getDefenseStopBgmInput()).to.eq(
      'defenseStopBgm',
      'Expected DefenseStopBgm value to be equals to defenseStopBgm'
    );
    await mMatchEnvironmentUpdatePage.save();
    expect(await mMatchEnvironmentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMatchEnvironmentComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMatchEnvironment', async () => {
    const nbButtonsBeforeDelete = await mMatchEnvironmentComponentsPage.countDeleteButtons();
    await mMatchEnvironmentComponentsPage.clickOnLastDeleteButton();

    mMatchEnvironmentDeleteDialog = new MMatchEnvironmentDeleteDialog();
    expect(await mMatchEnvironmentDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Match Environment?');
    await mMatchEnvironmentDeleteDialog.clickOnConfirmButton();

    expect(await mMatchEnvironmentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
