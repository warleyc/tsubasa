/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MEncountersCutComponentsPage, MEncountersCutDeleteDialog, MEncountersCutUpdatePage } from './m-encounters-cut.page-object';

const expect = chai.expect;

describe('MEncountersCut e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mEncountersCutUpdatePage: MEncountersCutUpdatePage;
  let mEncountersCutComponentsPage: MEncountersCutComponentsPage;
  let mEncountersCutDeleteDialog: MEncountersCutDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MEncountersCuts', async () => {
    await navBarPage.goToEntity('m-encounters-cut');
    mEncountersCutComponentsPage = new MEncountersCutComponentsPage();
    await browser.wait(ec.visibilityOf(mEncountersCutComponentsPage.title), 5000);
    expect(await mEncountersCutComponentsPage.getTitle()).to.eq('M Encounters Cuts');
  });

  it('should load create MEncountersCut page', async () => {
    await mEncountersCutComponentsPage.clickOnCreateButton();
    mEncountersCutUpdatePage = new MEncountersCutUpdatePage();
    expect(await mEncountersCutUpdatePage.getPageTitle()).to.eq('Create or edit a M Encounters Cut');
    await mEncountersCutUpdatePage.cancel();
  });

  it('should create and save MEncountersCuts', async () => {
    const nbButtonsBeforeCreate = await mEncountersCutComponentsPage.countDeleteButtons();

    await mEncountersCutComponentsPage.clickOnCreateButton();
    await promise.all([
      mEncountersCutUpdatePage.setConditionInput('5'),
      mEncountersCutUpdatePage.setBallFloatConditionInput('5'),
      mEncountersCutUpdatePage.setCommand1TypeInput('5'),
      mEncountersCutUpdatePage.setCommand1IsSkillInput('5'),
      mEncountersCutUpdatePage.setCommand2TypeInput('5'),
      mEncountersCutUpdatePage.setCommand2IsSkillInput('5'),
      mEncountersCutUpdatePage.setResultInput('5'),
      mEncountersCutUpdatePage.setShootResultInput('5'),
      mEncountersCutUpdatePage.setIsThrownInput('5'),
      mEncountersCutUpdatePage.setOffenseSequenceTextInput('offenseSequenceText'),
      mEncountersCutUpdatePage.setDefenseSequenceTextInput('defenseSequenceText')
    ]);
    expect(await mEncountersCutUpdatePage.getConditionInput()).to.eq('5', 'Expected condition value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getBallFloatConditionInput()).to.eq('5', 'Expected ballFloatCondition value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getCommand1TypeInput()).to.eq('5', 'Expected command1Type value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getCommand1IsSkillInput()).to.eq('5', 'Expected command1IsSkill value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getCommand2TypeInput()).to.eq('5', 'Expected command2Type value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getCommand2IsSkillInput()).to.eq('5', 'Expected command2IsSkill value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getResultInput()).to.eq('5', 'Expected result value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getShootResultInput()).to.eq('5', 'Expected shootResult value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getIsThrownInput()).to.eq('5', 'Expected isThrown value to be equals to 5');
    expect(await mEncountersCutUpdatePage.getOffenseSequenceTextInput()).to.eq(
      'offenseSequenceText',
      'Expected OffenseSequenceText value to be equals to offenseSequenceText'
    );
    expect(await mEncountersCutUpdatePage.getDefenseSequenceTextInput()).to.eq(
      'defenseSequenceText',
      'Expected DefenseSequenceText value to be equals to defenseSequenceText'
    );
    await mEncountersCutUpdatePage.save();
    expect(await mEncountersCutUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mEncountersCutComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MEncountersCut', async () => {
    const nbButtonsBeforeDelete = await mEncountersCutComponentsPage.countDeleteButtons();
    await mEncountersCutComponentsPage.clickOnLastDeleteButton();

    mEncountersCutDeleteDialog = new MEncountersCutDeleteDialog();
    expect(await mEncountersCutDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Encounters Cut?');
    await mEncountersCutDeleteDialog.clickOnConfirmButton();

    expect(await mEncountersCutComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
