/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDummyOpponentComponentsPage, MDummyOpponentDeleteDialog, MDummyOpponentUpdatePage } from './m-dummy-opponent.page-object';

const expect = chai.expect;

describe('MDummyOpponent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDummyOpponentUpdatePage: MDummyOpponentUpdatePage;
  let mDummyOpponentComponentsPage: MDummyOpponentComponentsPage;
  /*let mDummyOpponentDeleteDialog: MDummyOpponentDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDummyOpponents', async () => {
    await navBarPage.goToEntity('m-dummy-opponent');
    mDummyOpponentComponentsPage = new MDummyOpponentComponentsPage();
    await browser.wait(ec.visibilityOf(mDummyOpponentComponentsPage.title), 5000);
    expect(await mDummyOpponentComponentsPage.getTitle()).to.eq('M Dummy Opponents');
  });

  it('should load create MDummyOpponent page', async () => {
    await mDummyOpponentComponentsPage.clickOnCreateButton();
    mDummyOpponentUpdatePage = new MDummyOpponentUpdatePage();
    expect(await mDummyOpponentUpdatePage.getPageTitle()).to.eq('Create or edit a M Dummy Opponent');
    await mDummyOpponentUpdatePage.cancel();
  });

  /* it('should create and save MDummyOpponents', async () => {
        const nbButtonsBeforeCreate = await mDummyOpponentComponentsPage.countDeleteButtons();

        await mDummyOpponentComponentsPage.clickOnCreateButton();
        await promise.all([
            mDummyOpponentUpdatePage.setNpcDeckIdInput('5'),
            mDummyOpponentUpdatePage.setTotalParameterInput('5'),
            mDummyOpponentUpdatePage.setNameInput('name'),
            mDummyOpponentUpdatePage.setRankInput('5'),
            mDummyOpponentUpdatePage.setEmblemIdInput('5'),
            mDummyOpponentUpdatePage.setFpUniformUpIdInput('5'),
            mDummyOpponentUpdatePage.setFpUniformUpColorInput('fpUniformUpColor'),
            mDummyOpponentUpdatePage.setFpUniformBottomIdInput('5'),
            mDummyOpponentUpdatePage.setFpUniformBottomColorInput('fpUniformBottomColor'),
            mDummyOpponentUpdatePage.setGkUniformUpIdInput('5'),
            mDummyOpponentUpdatePage.setGkUniformUpColorInput('gkUniformUpColor'),
            mDummyOpponentUpdatePage.setGkUniformBottomIdInput('5'),
            mDummyOpponentUpdatePage.setGkUniformBottomColorInput('gkUniformBottomColor'),
            mDummyOpponentUpdatePage.idSelectLastOption(),
        ]);
        expect(await mDummyOpponentUpdatePage.getNpcDeckIdInput()).to.eq('5', 'Expected npcDeckId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getTotalParameterInput()).to.eq('5', 'Expected totalParameter value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mDummyOpponentUpdatePage.getRankInput()).to.eq('5', 'Expected rank value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getEmblemIdInput()).to.eq('5', 'Expected emblemId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getFpUniformUpIdInput()).to.eq('5', 'Expected fpUniformUpId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getFpUniformUpColorInput()).to.eq('fpUniformUpColor', 'Expected FpUniformUpColor value to be equals to fpUniformUpColor');
        expect(await mDummyOpponentUpdatePage.getFpUniformBottomIdInput()).to.eq('5', 'Expected fpUniformBottomId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getFpUniformBottomColorInput()).to.eq('fpUniformBottomColor', 'Expected FpUniformBottomColor value to be equals to fpUniformBottomColor');
        expect(await mDummyOpponentUpdatePage.getGkUniformUpIdInput()).to.eq('5', 'Expected gkUniformUpId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getGkUniformUpColorInput()).to.eq('gkUniformUpColor', 'Expected GkUniformUpColor value to be equals to gkUniformUpColor');
        expect(await mDummyOpponentUpdatePage.getGkUniformBottomIdInput()).to.eq('5', 'Expected gkUniformBottomId value to be equals to 5');
        expect(await mDummyOpponentUpdatePage.getGkUniformBottomColorInput()).to.eq('gkUniformBottomColor', 'Expected GkUniformBottomColor value to be equals to gkUniformBottomColor');
        await mDummyOpponentUpdatePage.save();
        expect(await mDummyOpponentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mDummyOpponentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MDummyOpponent', async () => {
        const nbButtonsBeforeDelete = await mDummyOpponentComponentsPage.countDeleteButtons();
        await mDummyOpponentComponentsPage.clickOnLastDeleteButton();

        mDummyOpponentDeleteDialog = new MDummyOpponentDeleteDialog();
        expect(await mDummyOpponentDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Dummy Opponent?');
        await mDummyOpponentDeleteDialog.clickOnConfirmButton();

        expect(await mDummyOpponentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
