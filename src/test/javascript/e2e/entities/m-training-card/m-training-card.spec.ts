/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MTrainingCardComponentsPage, MTrainingCardDeleteDialog, MTrainingCardUpdatePage } from './m-training-card.page-object';

const expect = chai.expect;

describe('MTrainingCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTrainingCardUpdatePage: MTrainingCardUpdatePage;
  let mTrainingCardComponentsPage: MTrainingCardComponentsPage;
  /*let mTrainingCardDeleteDialog: MTrainingCardDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTrainingCards', async () => {
    await navBarPage.goToEntity('m-training-card');
    mTrainingCardComponentsPage = new MTrainingCardComponentsPage();
    await browser.wait(ec.visibilityOf(mTrainingCardComponentsPage.title), 5000);
    expect(await mTrainingCardComponentsPage.getTitle()).to.eq('M Training Cards');
  });

  it('should load create MTrainingCard page', async () => {
    await mTrainingCardComponentsPage.clickOnCreateButton();
    mTrainingCardUpdatePage = new MTrainingCardUpdatePage();
    expect(await mTrainingCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Training Card');
    await mTrainingCardUpdatePage.cancel();
  });

  /* it('should create and save MTrainingCards', async () => {
        const nbButtonsBeforeCreate = await mTrainingCardComponentsPage.countDeleteButtons();

        await mTrainingCardComponentsPage.clickOnCreateButton();
        await promise.all([
            mTrainingCardUpdatePage.setNameInput('name'),
            mTrainingCardUpdatePage.setShortNameInput('shortName'),
            mTrainingCardUpdatePage.setDescriptionInput('description'),
            mTrainingCardUpdatePage.setRarityInput('5'),
            mTrainingCardUpdatePage.setAttributeInput('5'),
            mTrainingCardUpdatePage.setGainExpInput('5'),
            mTrainingCardUpdatePage.setCoinInput('5'),
            mTrainingCardUpdatePage.setSellMedalIdInput('5'),
            mTrainingCardUpdatePage.setPlusDribbleInput('5'),
            mTrainingCardUpdatePage.setPlusShootInput('5'),
            mTrainingCardUpdatePage.setPlusPassInput('5'),
            mTrainingCardUpdatePage.setPlusTackleInput('5'),
            mTrainingCardUpdatePage.setPlusBlockInput('5'),
            mTrainingCardUpdatePage.setPlusInterceptInput('5'),
            mTrainingCardUpdatePage.setPlusSpeedInput('5'),
            mTrainingCardUpdatePage.setPlusPowerInput('5'),
            mTrainingCardUpdatePage.setPlusTechniqueInput('5'),
            mTrainingCardUpdatePage.setPlusPunchingInput('5'),
            mTrainingCardUpdatePage.setPlusCatchingInput('5'),
            mTrainingCardUpdatePage.setThumbnailAssetsIdInput('5'),
            mTrainingCardUpdatePage.setCardIllustAssetsIdInput('5'),
            mTrainingCardUpdatePage.mcardthumbnailassetsSelectLastOption(),
        ]);
        expect(await mTrainingCardUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mTrainingCardUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
        expect(await mTrainingCardUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await mTrainingCardUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getAttributeInput()).to.eq('5', 'Expected attribute value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getGainExpInput()).to.eq('5', 'Expected gainExp value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getSellMedalIdInput()).to.eq('5', 'Expected sellMedalId value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusDribbleInput()).to.eq('5', 'Expected plusDribble value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusShootInput()).to.eq('5', 'Expected plusShoot value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusPassInput()).to.eq('5', 'Expected plusPass value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusTackleInput()).to.eq('5', 'Expected plusTackle value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusBlockInput()).to.eq('5', 'Expected plusBlock value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusInterceptInput()).to.eq('5', 'Expected plusIntercept value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusSpeedInput()).to.eq('5', 'Expected plusSpeed value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusPowerInput()).to.eq('5', 'Expected plusPower value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusTechniqueInput()).to.eq('5', 'Expected plusTechnique value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusPunchingInput()).to.eq('5', 'Expected plusPunching value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getPlusCatchingInput()).to.eq('5', 'Expected plusCatching value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getThumbnailAssetsIdInput()).to.eq('5', 'Expected thumbnailAssetsId value to be equals to 5');
        expect(await mTrainingCardUpdatePage.getCardIllustAssetsIdInput()).to.eq('5', 'Expected cardIllustAssetsId value to be equals to 5');
        await mTrainingCardUpdatePage.save();
        expect(await mTrainingCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTrainingCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTrainingCard', async () => {
        const nbButtonsBeforeDelete = await mTrainingCardComponentsPage.countDeleteButtons();
        await mTrainingCardComponentsPage.clickOnLastDeleteButton();

        mTrainingCardDeleteDialog = new MTrainingCardDeleteDialog();
        expect(await mTrainingCardDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Training Card?');
        await mTrainingCardDeleteDialog.clickOnConfirmButton();

        expect(await mTrainingCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
