/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MArousalComponentsPage, MArousalDeleteDialog, MArousalUpdatePage } from './m-arousal.page-object';

const expect = chai.expect;

describe('MArousal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mArousalUpdatePage: MArousalUpdatePage;
  let mArousalComponentsPage: MArousalComponentsPage;
  /*let mArousalDeleteDialog: MArousalDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MArousals', async () => {
    await navBarPage.goToEntity('m-arousal');
    mArousalComponentsPage = new MArousalComponentsPage();
    await browser.wait(ec.visibilityOf(mArousalComponentsPage.title), 5000);
    expect(await mArousalComponentsPage.getTitle()).to.eq('M Arousals');
  });

  it('should load create MArousal page', async () => {
    await mArousalComponentsPage.clickOnCreateButton();
    mArousalUpdatePage = new MArousalUpdatePage();
    expect(await mArousalUpdatePage.getPageTitle()).to.eq('Create or edit a M Arousal');
    await mArousalUpdatePage.cancel();
  });

  /* it('should create and save MArousals', async () => {
        const nbButtonsBeforeCreate = await mArousalComponentsPage.countDeleteButtons();

        await mArousalComponentsPage.clickOnCreateButton();
        await promise.all([
            mArousalUpdatePage.setBeforeIdInput('5'),
            mArousalUpdatePage.setAfterIdInput('5'),
            mArousalUpdatePage.setCostInput('5'),
            mArousalUpdatePage.setMaterialGroupIdInput('5'),
            mArousalUpdatePage.mplayablecardSelectLastOption(),
        ]);
        expect(await mArousalUpdatePage.getBeforeIdInput()).to.eq('5', 'Expected beforeId value to be equals to 5');
        expect(await mArousalUpdatePage.getAfterIdInput()).to.eq('5', 'Expected afterId value to be equals to 5');
        expect(await mArousalUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
        expect(await mArousalUpdatePage.getMaterialGroupIdInput()).to.eq('5', 'Expected materialGroupId value to be equals to 5');
        await mArousalUpdatePage.save();
        expect(await mArousalUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mArousalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MArousal', async () => {
        const nbButtonsBeforeDelete = await mArousalComponentsPage.countDeleteButtons();
        await mArousalComponentsPage.clickOnLastDeleteButton();

        mArousalDeleteDialog = new MArousalDeleteDialog();
        expect(await mArousalDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Arousal?');
        await mArousalDeleteDialog.clickOnConfirmButton();

        expect(await mArousalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
