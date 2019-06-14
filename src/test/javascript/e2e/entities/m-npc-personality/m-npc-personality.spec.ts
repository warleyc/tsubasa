/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MNpcPersonalityComponentsPage, MNpcPersonalityDeleteDialog, MNpcPersonalityUpdatePage } from './m-npc-personality.page-object';

const expect = chai.expect;

describe('MNpcPersonality e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mNpcPersonalityUpdatePage: MNpcPersonalityUpdatePage;
  let mNpcPersonalityComponentsPage: MNpcPersonalityComponentsPage;
  let mNpcPersonalityDeleteDialog: MNpcPersonalityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MNpcPersonalities', async () => {
    await navBarPage.goToEntity('m-npc-personality');
    mNpcPersonalityComponentsPage = new MNpcPersonalityComponentsPage();
    await browser.wait(ec.visibilityOf(mNpcPersonalityComponentsPage.title), 5000);
    expect(await mNpcPersonalityComponentsPage.getTitle()).to.eq('M Npc Personalities');
  });

  it('should load create MNpcPersonality page', async () => {
    await mNpcPersonalityComponentsPage.clickOnCreateButton();
    mNpcPersonalityUpdatePage = new MNpcPersonalityUpdatePage();
    expect(await mNpcPersonalityUpdatePage.getPageTitle()).to.eq('Create or edit a M Npc Personality');
    await mNpcPersonalityUpdatePage.cancel();
  });

  it('should create and save MNpcPersonalities', async () => {
    const nbButtonsBeforeCreate = await mNpcPersonalityComponentsPage.countDeleteButtons();

    await mNpcPersonalityComponentsPage.clickOnCreateButton();
    await promise.all([
      mNpcPersonalityUpdatePage.setPassingTargetRateInput('5'),
      mNpcPersonalityUpdatePage.setActionSkillRateInput('5'),
      mNpcPersonalityUpdatePage.setDribbleMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setPassingMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setOnetwoMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setShootMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setVolleyShootMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setHeadingShootMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setTackleMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setBlockMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setPassCutMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setClearMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setCompeteMagnificationInput('5'),
      mNpcPersonalityUpdatePage.setTrapMagnificationInput('5')
    ]);
    expect(await mNpcPersonalityUpdatePage.getPassingTargetRateInput()).to.eq('5', 'Expected passingTargetRate value to be equals to 5');
    expect(await mNpcPersonalityUpdatePage.getActionSkillRateInput()).to.eq('5', 'Expected actionSkillRate value to be equals to 5');
    expect(await mNpcPersonalityUpdatePage.getDribbleMagnificationInput()).to.eq(
      '5',
      'Expected dribbleMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getPassingMagnificationInput()).to.eq(
      '5',
      'Expected passingMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getOnetwoMagnificationInput()).to.eq(
      '5',
      'Expected onetwoMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getShootMagnificationInput()).to.eq('5', 'Expected shootMagnification value to be equals to 5');
    expect(await mNpcPersonalityUpdatePage.getVolleyShootMagnificationInput()).to.eq(
      '5',
      'Expected volleyShootMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getHeadingShootMagnificationInput()).to.eq(
      '5',
      'Expected headingShootMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getTackleMagnificationInput()).to.eq(
      '5',
      'Expected tackleMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getBlockMagnificationInput()).to.eq('5', 'Expected blockMagnification value to be equals to 5');
    expect(await mNpcPersonalityUpdatePage.getPassCutMagnificationInput()).to.eq(
      '5',
      'Expected passCutMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getClearMagnificationInput()).to.eq('5', 'Expected clearMagnification value to be equals to 5');
    expect(await mNpcPersonalityUpdatePage.getCompeteMagnificationInput()).to.eq(
      '5',
      'Expected competeMagnification value to be equals to 5'
    );
    expect(await mNpcPersonalityUpdatePage.getTrapMagnificationInput()).to.eq('5', 'Expected trapMagnification value to be equals to 5');
    await mNpcPersonalityUpdatePage.save();
    expect(await mNpcPersonalityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mNpcPersonalityComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MNpcPersonality', async () => {
    const nbButtonsBeforeDelete = await mNpcPersonalityComponentsPage.countDeleteButtons();
    await mNpcPersonalityComponentsPage.clickOnLastDeleteButton();

    mNpcPersonalityDeleteDialog = new MNpcPersonalityDeleteDialog();
    expect(await mNpcPersonalityDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Npc Personality?');
    await mNpcPersonalityDeleteDialog.clickOnConfirmButton();

    expect(await mNpcPersonalityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
