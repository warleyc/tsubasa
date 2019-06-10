/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MActionSkillCutinComponentsPage,
  MActionSkillCutinDeleteDialog,
  MActionSkillCutinUpdatePage
} from './m-action-skill-cutin.page-object';

const expect = chai.expect;

describe('MActionSkillCutin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mActionSkillCutinUpdatePage: MActionSkillCutinUpdatePage;
  let mActionSkillCutinComponentsPage: MActionSkillCutinComponentsPage;
  let mActionSkillCutinDeleteDialog: MActionSkillCutinDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MActionSkillCutins', async () => {
    await navBarPage.goToEntity('m-action-skill-cutin');
    mActionSkillCutinComponentsPage = new MActionSkillCutinComponentsPage();
    await browser.wait(ec.visibilityOf(mActionSkillCutinComponentsPage.title), 5000);
    expect(await mActionSkillCutinComponentsPage.getTitle()).to.eq('M Action Skill Cutins');
  });

  it('should load create MActionSkillCutin page', async () => {
    await mActionSkillCutinComponentsPage.clickOnCreateButton();
    mActionSkillCutinUpdatePage = new MActionSkillCutinUpdatePage();
    expect(await mActionSkillCutinUpdatePage.getPageTitle()).to.eq('Create or edit a M Action Skill Cutin');
    await mActionSkillCutinUpdatePage.cancel();
  });

  it('should create and save MActionSkillCutins', async () => {
    const nbButtonsBeforeCreate = await mActionSkillCutinComponentsPage.countDeleteButtons();

    await mActionSkillCutinComponentsPage.clickOnCreateButton();
    await promise.all([
      mActionSkillCutinUpdatePage.setActionCutIdInput('5'),
      mActionSkillCutinUpdatePage.setCharacterIdInput('5'),
      mActionSkillCutinUpdatePage.setCutNameInput('cutName'),
      mActionSkillCutinUpdatePage.setTypeInput('5'),
      mActionSkillCutinUpdatePage.setStartSynchronizeInput('5'),
      mActionSkillCutinUpdatePage.setFinishSynchronizeInput('5'),
      mActionSkillCutinUpdatePage.setStartDelayInput('5'),
      mActionSkillCutinUpdatePage.setChapter1CharacterInput('5'),
      mActionSkillCutinUpdatePage.setChapter1TextInput('chapter1Text'),
      mActionSkillCutinUpdatePage.setChapter1SoundEventInput('chapter1SoundEvent'),
      mActionSkillCutinUpdatePage.setChapter2CharacterInput('5'),
      mActionSkillCutinUpdatePage.setChapter2TextInput('chapter2Text'),
      mActionSkillCutinUpdatePage.setChapter2SoundEventInput('chapter2SoundEvent'),
      mActionSkillCutinUpdatePage.setChapter3CharacterInput('5'),
      mActionSkillCutinUpdatePage.setChapter3TextInput('chapter3Text'),
      mActionSkillCutinUpdatePage.setChapter3SoundEventInput('chapter3SoundEvent'),
      mActionSkillCutinUpdatePage.setChapter4CharacterInput('5'),
      mActionSkillCutinUpdatePage.setChapter4TextInput('chapter4Text'),
      mActionSkillCutinUpdatePage.setChapter4SoundEventInput('chapter4SoundEvent'),
      mActionSkillCutinUpdatePage.setChapter5CharacterInput('5'),
      mActionSkillCutinUpdatePage.setChapter5TextInput('chapter5Text'),
      mActionSkillCutinUpdatePage.setChapter5SoundEventInput('chapter5SoundEvent'),
      mActionSkillCutinUpdatePage.setSynchronizeTextInput('synchronizeText'),
      mActionSkillCutinUpdatePage.setSynchronizeSoundEventInput('synchronizeSoundEvent')
    ]);
    expect(await mActionSkillCutinUpdatePage.getActionCutIdInput()).to.eq('5', 'Expected actionCutId value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getCutNameInput()).to.eq('cutName', 'Expected CutName value to be equals to cutName');
    expect(await mActionSkillCutinUpdatePage.getTypeInput()).to.eq('5', 'Expected type value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getStartSynchronizeInput()).to.eq('5', 'Expected startSynchronize value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getFinishSynchronizeInput()).to.eq('5', 'Expected finishSynchronize value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getStartDelayInput()).to.eq('5', 'Expected startDelay value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter1CharacterInput()).to.eq('5', 'Expected chapter1Character value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter1TextInput()).to.eq(
      'chapter1Text',
      'Expected Chapter1Text value to be equals to chapter1Text'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter1SoundEventInput()).to.eq(
      'chapter1SoundEvent',
      'Expected Chapter1SoundEvent value to be equals to chapter1SoundEvent'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter2CharacterInput()).to.eq('5', 'Expected chapter2Character value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter2TextInput()).to.eq(
      'chapter2Text',
      'Expected Chapter2Text value to be equals to chapter2Text'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter2SoundEventInput()).to.eq(
      'chapter2SoundEvent',
      'Expected Chapter2SoundEvent value to be equals to chapter2SoundEvent'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter3CharacterInput()).to.eq('5', 'Expected chapter3Character value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter3TextInput()).to.eq(
      'chapter3Text',
      'Expected Chapter3Text value to be equals to chapter3Text'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter3SoundEventInput()).to.eq(
      'chapter3SoundEvent',
      'Expected Chapter3SoundEvent value to be equals to chapter3SoundEvent'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter4CharacterInput()).to.eq('5', 'Expected chapter4Character value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter4TextInput()).to.eq(
      'chapter4Text',
      'Expected Chapter4Text value to be equals to chapter4Text'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter4SoundEventInput()).to.eq(
      'chapter4SoundEvent',
      'Expected Chapter4SoundEvent value to be equals to chapter4SoundEvent'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter5CharacterInput()).to.eq('5', 'Expected chapter5Character value to be equals to 5');
    expect(await mActionSkillCutinUpdatePage.getChapter5TextInput()).to.eq(
      'chapter5Text',
      'Expected Chapter5Text value to be equals to chapter5Text'
    );
    expect(await mActionSkillCutinUpdatePage.getChapter5SoundEventInput()).to.eq(
      'chapter5SoundEvent',
      'Expected Chapter5SoundEvent value to be equals to chapter5SoundEvent'
    );
    expect(await mActionSkillCutinUpdatePage.getSynchronizeTextInput()).to.eq(
      'synchronizeText',
      'Expected SynchronizeText value to be equals to synchronizeText'
    );
    expect(await mActionSkillCutinUpdatePage.getSynchronizeSoundEventInput()).to.eq(
      'synchronizeSoundEvent',
      'Expected SynchronizeSoundEvent value to be equals to synchronizeSoundEvent'
    );
    await mActionSkillCutinUpdatePage.save();
    expect(await mActionSkillCutinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mActionSkillCutinComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MActionSkillCutin', async () => {
    const nbButtonsBeforeDelete = await mActionSkillCutinComponentsPage.countDeleteButtons();
    await mActionSkillCutinComponentsPage.clickOnLastDeleteButton();

    mActionSkillCutinDeleteDialog = new MActionSkillCutinDeleteDialog();
    expect(await mActionSkillCutinDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Action Skill Cutin?');
    await mActionSkillCutinDeleteDialog.clickOnConfirmButton();

    expect(await mActionSkillCutinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
