/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDummyEmblemComponentsPage, MDummyEmblemDeleteDialog, MDummyEmblemUpdatePage } from './m-dummy-emblem.page-object';

const expect = chai.expect;

describe('MDummyEmblem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDummyEmblemUpdatePage: MDummyEmblemUpdatePage;
  let mDummyEmblemComponentsPage: MDummyEmblemComponentsPage;
  /*let mDummyEmblemDeleteDialog: MDummyEmblemDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDummyEmblems', async () => {
    await navBarPage.goToEntity('m-dummy-emblem');
    mDummyEmblemComponentsPage = new MDummyEmblemComponentsPage();
    await browser.wait(ec.visibilityOf(mDummyEmblemComponentsPage.title), 5000);
    expect(await mDummyEmblemComponentsPage.getTitle()).to.eq('M Dummy Emblems');
  });

  it('should load create MDummyEmblem page', async () => {
    await mDummyEmblemComponentsPage.clickOnCreateButton();
    mDummyEmblemUpdatePage = new MDummyEmblemUpdatePage();
    expect(await mDummyEmblemUpdatePage.getPageTitle()).to.eq('Create or edit a M Dummy Emblem');
    await mDummyEmblemUpdatePage.cancel();
  });

  /* it('should create and save MDummyEmblems', async () => {
        const nbButtonsBeforeCreate = await mDummyEmblemComponentsPage.countDeleteButtons();

        await mDummyEmblemComponentsPage.clickOnCreateButton();
        await promise.all([
            mDummyEmblemUpdatePage.setBackgroundIdInput('5'),
            mDummyEmblemUpdatePage.setBackgroundColorInput('backgroundColor'),
            mDummyEmblemUpdatePage.setUpperIdInput('5'),
            mDummyEmblemUpdatePage.setUpperColorInput('upperColor'),
            mDummyEmblemUpdatePage.setMiddleIdInput('5'),
            mDummyEmblemUpdatePage.setMiddleColorInput('middleColor'),
            mDummyEmblemUpdatePage.setLowerIdInput('5'),
            mDummyEmblemUpdatePage.setLowerColorInput('lowerColor'),
            mDummyEmblemUpdatePage.idSelectLastOption(),
        ]);
        expect(await mDummyEmblemUpdatePage.getBackgroundIdInput()).to.eq('5', 'Expected backgroundId value to be equals to 5');
        expect(await mDummyEmblemUpdatePage.getBackgroundColorInput()).to.eq('backgroundColor', 'Expected BackgroundColor value to be equals to backgroundColor');
        expect(await mDummyEmblemUpdatePage.getUpperIdInput()).to.eq('5', 'Expected upperId value to be equals to 5');
        expect(await mDummyEmblemUpdatePage.getUpperColorInput()).to.eq('upperColor', 'Expected UpperColor value to be equals to upperColor');
        expect(await mDummyEmblemUpdatePage.getMiddleIdInput()).to.eq('5', 'Expected middleId value to be equals to 5');
        expect(await mDummyEmblemUpdatePage.getMiddleColorInput()).to.eq('middleColor', 'Expected MiddleColor value to be equals to middleColor');
        expect(await mDummyEmblemUpdatePage.getLowerIdInput()).to.eq('5', 'Expected lowerId value to be equals to 5');
        expect(await mDummyEmblemUpdatePage.getLowerColorInput()).to.eq('lowerColor', 'Expected LowerColor value to be equals to lowerColor');
        await mDummyEmblemUpdatePage.save();
        expect(await mDummyEmblemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mDummyEmblemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MDummyEmblem', async () => {
        const nbButtonsBeforeDelete = await mDummyEmblemComponentsPage.countDeleteButtons();
        await mDummyEmblemComponentsPage.clickOnLastDeleteButton();

        mDummyEmblemDeleteDialog = new MDummyEmblemDeleteDialog();
        expect(await mDummyEmblemDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Dummy Emblem?');
        await mDummyEmblemDeleteDialog.clickOnConfirmButton();

        expect(await mDummyEmblemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
