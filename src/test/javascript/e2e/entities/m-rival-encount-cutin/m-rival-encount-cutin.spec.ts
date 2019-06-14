/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MRivalEncountCutinComponentsPage,
  MRivalEncountCutinDeleteDialog,
  MRivalEncountCutinUpdatePage
} from './m-rival-encount-cutin.page-object';

const expect = chai.expect;

describe('MRivalEncountCutin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mRivalEncountCutinUpdatePage: MRivalEncountCutinUpdatePage;
  let mRivalEncountCutinComponentsPage: MRivalEncountCutinComponentsPage;
  let mRivalEncountCutinDeleteDialog: MRivalEncountCutinDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MRivalEncountCutins', async () => {
    await navBarPage.goToEntity('m-rival-encount-cutin');
    mRivalEncountCutinComponentsPage = new MRivalEncountCutinComponentsPage();
    await browser.wait(ec.visibilityOf(mRivalEncountCutinComponentsPage.title), 5000);
    expect(await mRivalEncountCutinComponentsPage.getTitle()).to.eq('M Rival Encount Cutins');
  });

  it('should load create MRivalEncountCutin page', async () => {
    await mRivalEncountCutinComponentsPage.clickOnCreateButton();
    mRivalEncountCutinUpdatePage = new MRivalEncountCutinUpdatePage();
    expect(await mRivalEncountCutinUpdatePage.getPageTitle()).to.eq('Create or edit a M Rival Encount Cutin');
    await mRivalEncountCutinUpdatePage.cancel();
  });

  it('should create and save MRivalEncountCutins', async () => {
    const nbButtonsBeforeCreate = await mRivalEncountCutinComponentsPage.countDeleteButtons();

    await mRivalEncountCutinComponentsPage.clickOnCreateButton();
    await promise.all([
      mRivalEncountCutinUpdatePage.setOffenseCharacterIdInput('5'),
      mRivalEncountCutinUpdatePage.setOffenseTeamIdInput('5'),
      mRivalEncountCutinUpdatePage.setDefenseCharacterIdInput('5'),
      mRivalEncountCutinUpdatePage.setDefenseTeamIdInput('5'),
      mRivalEncountCutinUpdatePage.setCutinTypeInput('5'),
      mRivalEncountCutinUpdatePage.setChapter1TextInput('chapter1Text'),
      mRivalEncountCutinUpdatePage.setChapter1SoundEventInput('chapter1SoundEvent'),
      mRivalEncountCutinUpdatePage.setChapter2TextInput('chapter2Text'),
      mRivalEncountCutinUpdatePage.setChapter2SoundEventInput('chapter2SoundEvent')
    ]);
    expect(await mRivalEncountCutinUpdatePage.getOffenseCharacterIdInput()).to.eq(
      '5',
      'Expected offenseCharacterId value to be equals to 5'
    );
    expect(await mRivalEncountCutinUpdatePage.getOffenseTeamIdInput()).to.eq('5', 'Expected offenseTeamId value to be equals to 5');
    expect(await mRivalEncountCutinUpdatePage.getDefenseCharacterIdInput()).to.eq(
      '5',
      'Expected defenseCharacterId value to be equals to 5'
    );
    expect(await mRivalEncountCutinUpdatePage.getDefenseTeamIdInput()).to.eq('5', 'Expected defenseTeamId value to be equals to 5');
    expect(await mRivalEncountCutinUpdatePage.getCutinTypeInput()).to.eq('5', 'Expected cutinType value to be equals to 5');
    expect(await mRivalEncountCutinUpdatePage.getChapter1TextInput()).to.eq(
      'chapter1Text',
      'Expected Chapter1Text value to be equals to chapter1Text'
    );
    expect(await mRivalEncountCutinUpdatePage.getChapter1SoundEventInput()).to.eq(
      'chapter1SoundEvent',
      'Expected Chapter1SoundEvent value to be equals to chapter1SoundEvent'
    );
    expect(await mRivalEncountCutinUpdatePage.getChapter2TextInput()).to.eq(
      'chapter2Text',
      'Expected Chapter2Text value to be equals to chapter2Text'
    );
    expect(await mRivalEncountCutinUpdatePage.getChapter2SoundEventInput()).to.eq(
      'chapter2SoundEvent',
      'Expected Chapter2SoundEvent value to be equals to chapter2SoundEvent'
    );
    await mRivalEncountCutinUpdatePage.save();
    expect(await mRivalEncountCutinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mRivalEncountCutinComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MRivalEncountCutin', async () => {
    const nbButtonsBeforeDelete = await mRivalEncountCutinComponentsPage.countDeleteButtons();
    await mRivalEncountCutinComponentsPage.clickOnLastDeleteButton();

    mRivalEncountCutinDeleteDialog = new MRivalEncountCutinDeleteDialog();
    expect(await mRivalEncountCutinDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Rival Encount Cutin?');
    await mRivalEncountCutinDeleteDialog.clickOnConfirmButton();

    expect(await mRivalEncountCutinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
