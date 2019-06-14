/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMatchResultCutinComponentsPage,
  MMatchResultCutinDeleteDialog,
  MMatchResultCutinUpdatePage
} from './m-match-result-cutin.page-object';

const expect = chai.expect;

describe('MMatchResultCutin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMatchResultCutinUpdatePage: MMatchResultCutinUpdatePage;
  let mMatchResultCutinComponentsPage: MMatchResultCutinComponentsPage;
  /*let mMatchResultCutinDeleteDialog: MMatchResultCutinDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMatchResultCutins', async () => {
    await navBarPage.goToEntity('m-match-result-cutin');
    mMatchResultCutinComponentsPage = new MMatchResultCutinComponentsPage();
    await browser.wait(ec.visibilityOf(mMatchResultCutinComponentsPage.title), 5000);
    expect(await mMatchResultCutinComponentsPage.getTitle()).to.eq('M Match Result Cutins');
  });

  it('should load create MMatchResultCutin page', async () => {
    await mMatchResultCutinComponentsPage.clickOnCreateButton();
    mMatchResultCutinUpdatePage = new MMatchResultCutinUpdatePage();
    expect(await mMatchResultCutinUpdatePage.getPageTitle()).to.eq('Create or edit a M Match Result Cutin');
    await mMatchResultCutinUpdatePage.cancel();
  });

  /* it('should create and save MMatchResultCutins', async () => {
        const nbButtonsBeforeCreate = await mMatchResultCutinComponentsPage.countDeleteButtons();

        await mMatchResultCutinComponentsPage.clickOnCreateButton();
        await promise.all([
            mMatchResultCutinUpdatePage.setCharacterIdInput('5'),
            mMatchResultCutinUpdatePage.setTeamIdInput('5'),
            mMatchResultCutinUpdatePage.setIsWinInput('5'),
            mMatchResultCutinUpdatePage.setTextInput('text'),
            mMatchResultCutinUpdatePage.setSoundEventInput('soundEvent'),
            mMatchResultCutinUpdatePage.mcharacterSelectLastOption(),
        ]);
        expect(await mMatchResultCutinUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
        expect(await mMatchResultCutinUpdatePage.getTeamIdInput()).to.eq('5', 'Expected teamId value to be equals to 5');
        expect(await mMatchResultCutinUpdatePage.getIsWinInput()).to.eq('5', 'Expected isWin value to be equals to 5');
        expect(await mMatchResultCutinUpdatePage.getTextInput()).to.eq('text', 'Expected Text value to be equals to text');
        expect(await mMatchResultCutinUpdatePage.getSoundEventInput()).to.eq('soundEvent', 'Expected SoundEvent value to be equals to soundEvent');
        await mMatchResultCutinUpdatePage.save();
        expect(await mMatchResultCutinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mMatchResultCutinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MMatchResultCutin', async () => {
        const nbButtonsBeforeDelete = await mMatchResultCutinComponentsPage.countDeleteButtons();
        await mMatchResultCutinComponentsPage.clickOnLastDeleteButton();

        mMatchResultCutinDeleteDialog = new MMatchResultCutinDeleteDialog();
        expect(await mMatchResultCutinDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Match Result Cutin?');
        await mMatchResultCutinDeleteDialog.clickOnConfirmButton();

        expect(await mMatchResultCutinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
