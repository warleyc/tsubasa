/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MActionComponentsPage, MActionDeleteDialog, MActionUpdatePage } from './m-action.page-object';

const expect = chai.expect;

describe('MAction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionUpdatePage: MActionUpdatePage;
  let mActionComponentsPage: MActionComponentsPage;
  let mActionDeleteDialog: MActionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActions', async () => {
    await navBarPage.goToEntity('m-action');
    mActionComponentsPage = new MActionComponentsPage();
    await browser.wait(ec.visibilityOf(mActionComponentsPage.title), 5000);
    expect(await mActionComponentsPage.getTitle()).to.eq('M Actions');
  });

  it('should load create MAction page', async () => {
    await mActionComponentsPage.clickOnCreateButton();
    mActionUpdatePage = new MActionUpdatePage();
    expect(await mActionUpdatePage.getPageTitle()).to.eq('Create or edit a M Action');
    await mActionUpdatePage.cancel();
  });

  it('should create and save MActions', async () => {
    const nbButtonsBeforeCreate = await mActionComponentsPage.countDeleteButtons();

    await mActionComponentsPage.clickOnCreateButton();
    await promise.all([
      mActionUpdatePage.setNameInput('name'),
      mActionUpdatePage.setShortNameInput('shortName'),
      mActionUpdatePage.setNameRubyInput('nameRuby'),
      mActionUpdatePage.setDescriptionInput('description'),
      mActionUpdatePage.setEffectDescriptionInput('effectDescription'),
      mActionUpdatePage.setInitialCommandInput('5'),
      mActionUpdatePage.setRarityInput('5'),
      mActionUpdatePage.setCommandTypeInput('5'),
      mActionUpdatePage.setBallConditionGroundInput('5'),
      mActionUpdatePage.setBallConditionLowInput('5'),
      mActionUpdatePage.setBallConditionHighInput('5'),
      mActionUpdatePage.setStaminaLvMinInput('5'),
      mActionUpdatePage.setStaminaLvMaxInput('5'),
      mActionUpdatePage.setPowerLvMinInput('5'),
      mActionUpdatePage.setPowerLvMaxInput('5'),
      mActionUpdatePage.setBlowOffCountInput('5'),
      mActionUpdatePage.setMShootIdInput('5'),
      mActionUpdatePage.setFoulRateInput('5'),
      mActionUpdatePage.setDistanceDecayTypeInput('5'),
      mActionUpdatePage.setActivateCharacterCountInput('5'),
      mActionUpdatePage.setActionCutIdInput('5'),
      mActionUpdatePage.setPowerupGroupInput('5')
    ]);
    expect(await mActionUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mActionUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
    expect(await mActionUpdatePage.getNameRubyInput()).to.eq('nameRuby', 'Expected NameRuby value to be equals to nameRuby');
    expect(await mActionUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await mActionUpdatePage.getEffectDescriptionInput()).to.eq(
      'effectDescription',
      'Expected EffectDescription value to be equals to effectDescription'
    );
    expect(await mActionUpdatePage.getInitialCommandInput()).to.eq('5', 'Expected initialCommand value to be equals to 5');
    expect(await mActionUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
    expect(await mActionUpdatePage.getCommandTypeInput()).to.eq('5', 'Expected commandType value to be equals to 5');
    expect(await mActionUpdatePage.getBallConditionGroundInput()).to.eq('5', 'Expected ballConditionGround value to be equals to 5');
    expect(await mActionUpdatePage.getBallConditionLowInput()).to.eq('5', 'Expected ballConditionLow value to be equals to 5');
    expect(await mActionUpdatePage.getBallConditionHighInput()).to.eq('5', 'Expected ballConditionHigh value to be equals to 5');
    expect(await mActionUpdatePage.getStaminaLvMinInput()).to.eq('5', 'Expected staminaLvMin value to be equals to 5');
    expect(await mActionUpdatePage.getStaminaLvMaxInput()).to.eq('5', 'Expected staminaLvMax value to be equals to 5');
    expect(await mActionUpdatePage.getPowerLvMinInput()).to.eq('5', 'Expected powerLvMin value to be equals to 5');
    expect(await mActionUpdatePage.getPowerLvMaxInput()).to.eq('5', 'Expected powerLvMax value to be equals to 5');
    expect(await mActionUpdatePage.getBlowOffCountInput()).to.eq('5', 'Expected blowOffCount value to be equals to 5');
    expect(await mActionUpdatePage.getMShootIdInput()).to.eq('5', 'Expected mShootId value to be equals to 5');
    expect(await mActionUpdatePage.getFoulRateInput()).to.eq('5', 'Expected foulRate value to be equals to 5');
    expect(await mActionUpdatePage.getDistanceDecayTypeInput()).to.eq('5', 'Expected distanceDecayType value to be equals to 5');
    expect(await mActionUpdatePage.getActivateCharacterCountInput()).to.eq('5', 'Expected activateCharacterCount value to be equals to 5');
    expect(await mActionUpdatePage.getActionCutIdInput()).to.eq('5', 'Expected actionCutId value to be equals to 5');
    expect(await mActionUpdatePage.getPowerupGroupInput()).to.eq('5', 'Expected powerupGroup value to be equals to 5');
    await mActionUpdatePage.save();
    expect(await mActionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MAction', async () => {
    const nbButtonsBeforeDelete = await mActionComponentsPage.countDeleteButtons();
    await mActionComponentsPage.clickOnLastDeleteButton();

    mActionDeleteDialog = new MActionDeleteDialog();
    expect(await mActionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Action?');
    await mActionDeleteDialog.clickOnConfirmButton();

    expect(await mActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
