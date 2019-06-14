/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MQuestCoopComponentsPage, MQuestCoopDeleteDialog, MQuestCoopUpdatePage } from './m-quest-coop.page-object';

const expect = chai.expect;

describe('MQuestCoop e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestCoopUpdatePage: MQuestCoopUpdatePage;
  let mQuestCoopComponentsPage: MQuestCoopComponentsPage;
  let mQuestCoopDeleteDialog: MQuestCoopDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestCoops', async () => {
    await navBarPage.goToEntity('m-quest-coop');
    mQuestCoopComponentsPage = new MQuestCoopComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestCoopComponentsPage.title), 5000);
    expect(await mQuestCoopComponentsPage.getTitle()).to.eq('M Quest Coops');
  });

  it('should load create MQuestCoop page', async () => {
    await mQuestCoopComponentsPage.clickOnCreateButton();
    mQuestCoopUpdatePage = new MQuestCoopUpdatePage();
    expect(await mQuestCoopUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Coop');
    await mQuestCoopUpdatePage.cancel();
  });

  it('should create and save MQuestCoops', async () => {
    const nbButtonsBeforeCreate = await mQuestCoopComponentsPage.countDeleteButtons();

    await mQuestCoopComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestCoopUpdatePage.setGroupIdInput('5'),
      mQuestCoopUpdatePage.setEffectRarityInput('5'),
      mQuestCoopUpdatePage.setEffectLevelFromInput('5'),
      mQuestCoopUpdatePage.setEffectLevelToInput('5'),
      mQuestCoopUpdatePage.setChoose1WeightInput('5'),
      mQuestCoopUpdatePage.setChoose2WeightInput('5')
    ]);
    expect(await mQuestCoopUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mQuestCoopUpdatePage.getEffectRarityInput()).to.eq('5', 'Expected effectRarity value to be equals to 5');
    expect(await mQuestCoopUpdatePage.getEffectLevelFromInput()).to.eq('5', 'Expected effectLevelFrom value to be equals to 5');
    expect(await mQuestCoopUpdatePage.getEffectLevelToInput()).to.eq('5', 'Expected effectLevelTo value to be equals to 5');
    expect(await mQuestCoopUpdatePage.getChoose1WeightInput()).to.eq('5', 'Expected choose1Weight value to be equals to 5');
    expect(await mQuestCoopUpdatePage.getChoose2WeightInput()).to.eq('5', 'Expected choose2Weight value to be equals to 5');
    await mQuestCoopUpdatePage.save();
    expect(await mQuestCoopUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestCoopComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MQuestCoop', async () => {
    const nbButtonsBeforeDelete = await mQuestCoopComponentsPage.countDeleteButtons();
    await mQuestCoopComponentsPage.clickOnLastDeleteButton();

    mQuestCoopDeleteDialog = new MQuestCoopDeleteDialog();
    expect(await mQuestCoopDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Coop?');
    await mQuestCoopDeleteDialog.clickOnConfirmButton();

    expect(await mQuestCoopComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
