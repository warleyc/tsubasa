/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MPlayableCardComponentsPage, MPlayableCardDeleteDialog, MPlayableCardUpdatePage } from './m-playable-card.page-object';

const expect = chai.expect;

describe('MPlayableCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPlayableCardUpdatePage: MPlayableCardUpdatePage;
  let mPlayableCardComponentsPage: MPlayableCardComponentsPage;
  /*let mPlayableCardDeleteDialog: MPlayableCardDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPlayableCards', async () => {
    await navBarPage.goToEntity('m-playable-card');
    mPlayableCardComponentsPage = new MPlayableCardComponentsPage();
    await browser.wait(ec.visibilityOf(mPlayableCardComponentsPage.title), 5000);
    expect(await mPlayableCardComponentsPage.getTitle()).to.eq('M Playable Cards');
  });

  it('should load create MPlayableCard page', async () => {
    await mPlayableCardComponentsPage.clickOnCreateButton();
    mPlayableCardUpdatePage = new MPlayableCardUpdatePage();
    expect(await mPlayableCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Playable Card');
    await mPlayableCardUpdatePage.cancel();
  });

  /* it('should create and save MPlayableCards', async () => {
        const nbButtonsBeforeCreate = await mPlayableCardComponentsPage.countDeleteButtons();

        await mPlayableCardComponentsPage.clickOnCreateButton();
        await promise.all([
            mPlayableCardUpdatePage.setModelIdInput('5'),
            mPlayableCardUpdatePage.setProperPositionGkInput('5'),
            mPlayableCardUpdatePage.setProperPositionFwInput('5'),
            mPlayableCardUpdatePage.setProperPositionOmfInput('5'),
            mPlayableCardUpdatePage.setProperPositionDmfInput('5'),
            mPlayableCardUpdatePage.setProperPositionDfInput('5'),
            mPlayableCardUpdatePage.setCharacterIdInput('5'),
            mPlayableCardUpdatePage.setNickNameInput('nickName'),
            mPlayableCardUpdatePage.setTeamIdInput('5'),
            mPlayableCardUpdatePage.setNationalityIdInput('5'),
            mPlayableCardUpdatePage.setRarityInput('5'),
            mPlayableCardUpdatePage.setAttributeInput('5'),
            mPlayableCardUpdatePage.setThumbnailAssetsIdInput('5'),
            mPlayableCardUpdatePage.setCardIllustAssetsIdInput('5'),
            mPlayableCardUpdatePage.setPlayableAssetsIdInput('5'),
            mPlayableCardUpdatePage.setTeamEffectIdInput('5'),
            mPlayableCardUpdatePage.setTriggerEffectIdInput('5'),
            mPlayableCardUpdatePage.setMaxActionSlotInput('5'),
            mPlayableCardUpdatePage.setInitialActionId1Input('5'),
            mPlayableCardUpdatePage.setInitialActionId2Input('5'),
            mPlayableCardUpdatePage.setInitialActionId3Input('5'),
            mPlayableCardUpdatePage.setInitialActionId4Input('5'),
            mPlayableCardUpdatePage.setInitialActionId5Input('5'),
            mPlayableCardUpdatePage.setInitialStaminaInput('5'),
            mPlayableCardUpdatePage.setInitialDribbleInput('5'),
            mPlayableCardUpdatePage.setInitialShootInput('5'),
            mPlayableCardUpdatePage.setInitialPassInput('5'),
            mPlayableCardUpdatePage.setInitialTackleInput('5'),
            mPlayableCardUpdatePage.setInitialBlockInput('5'),
            mPlayableCardUpdatePage.setInitialInterceptInput('5'),
            mPlayableCardUpdatePage.setInitialSpeedInput('5'),
            mPlayableCardUpdatePage.setInitialPowerInput('5'),
            mPlayableCardUpdatePage.setInitialTechniqueInput('5'),
            mPlayableCardUpdatePage.setInitialPunchingInput('5'),
            mPlayableCardUpdatePage.setInitialCatchingInput('5'),
            mPlayableCardUpdatePage.setMaxStaminaInput('5'),
            mPlayableCardUpdatePage.setMaxDribbleInput('5'),
            mPlayableCardUpdatePage.setMaxShootInput('5'),
            mPlayableCardUpdatePage.setMaxPassInput('5'),
            mPlayableCardUpdatePage.setMaxTackleInput('5'),
            mPlayableCardUpdatePage.setMaxBlockInput('5'),
            mPlayableCardUpdatePage.setMaxInterceptInput('5'),
            mPlayableCardUpdatePage.setMaxSpeedInput('5'),
            mPlayableCardUpdatePage.setMaxPowerInput('5'),
            mPlayableCardUpdatePage.setMaxTechniqueInput('5'),
            mPlayableCardUpdatePage.setMaxPunchingInput('5'),
            mPlayableCardUpdatePage.setMaxCatchingInput('5'),
            mPlayableCardUpdatePage.setMaxPlusDribbleInput('5'),
            mPlayableCardUpdatePage.setMaxPlusShootInput('5'),
            mPlayableCardUpdatePage.setMaxPlusPassInput('5'),
            mPlayableCardUpdatePage.setMaxPlusTackleInput('5'),
            mPlayableCardUpdatePage.setMaxPlusBlockInput('5'),
            mPlayableCardUpdatePage.setMaxPlusInterceptInput('5'),
            mPlayableCardUpdatePage.setMaxPlusSpeedInput('5'),
            mPlayableCardUpdatePage.setMaxPlusPowerInput('5'),
            mPlayableCardUpdatePage.setMaxPlusTechniqueInput('5'),
            mPlayableCardUpdatePage.setMaxPlusPunchingInput('5'),
            mPlayableCardUpdatePage.setMaxPlusCatchingInput('5'),
            mPlayableCardUpdatePage.setHighBallBonusInput('5'),
            mPlayableCardUpdatePage.setLowBallBonusInput('5'),
            mPlayableCardUpdatePage.setIsDropOnlyInput('5'),
            mPlayableCardUpdatePage.setSellCoinGroupNumInput('5'),
            mPlayableCardUpdatePage.setSellMedalIdInput('5'),
            mPlayableCardUpdatePage.setCharacterBookIdInput('5'),
            mPlayableCardUpdatePage.setBookNoInput('5'),
            mPlayableCardUpdatePage.setIsShowBookInput('5'),
            mPlayableCardUpdatePage.setLevelGroupIdInput('5'),
            mPlayableCardUpdatePage.setStartAtInput('5'),
            mPlayableCardUpdatePage.mmodelcardSelectLastOption(),
        ]);
        expect(await mPlayableCardUpdatePage.getModelIdInput()).to.eq('5', 'Expected modelId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getProperPositionGkInput()).to.eq('5', 'Expected properPositionGk value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getProperPositionFwInput()).to.eq('5', 'Expected properPositionFw value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getProperPositionOmfInput()).to.eq('5', 'Expected properPositionOmf value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getProperPositionDmfInput()).to.eq('5', 'Expected properPositionDmf value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getProperPositionDfInput()).to.eq('5', 'Expected properPositionDf value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getNickNameInput()).to.eq('nickName', 'Expected NickName value to be equals to nickName');
        expect(await mPlayableCardUpdatePage.getTeamIdInput()).to.eq('5', 'Expected teamId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getNationalityIdInput()).to.eq('5', 'Expected nationalityId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getAttributeInput()).to.eq('5', 'Expected attribute value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getThumbnailAssetsIdInput()).to.eq('5', 'Expected thumbnailAssetsId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getCardIllustAssetsIdInput()).to.eq('5', 'Expected cardIllustAssetsId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getPlayableAssetsIdInput()).to.eq('5', 'Expected playableAssetsId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getTeamEffectIdInput()).to.eq('5', 'Expected teamEffectId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getTriggerEffectIdInput()).to.eq('5', 'Expected triggerEffectId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxActionSlotInput()).to.eq('5', 'Expected maxActionSlot value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialActionId1Input()).to.eq('5', 'Expected initialActionId1 value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialActionId2Input()).to.eq('5', 'Expected initialActionId2 value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialActionId3Input()).to.eq('5', 'Expected initialActionId3 value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialActionId4Input()).to.eq('5', 'Expected initialActionId4 value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialActionId5Input()).to.eq('5', 'Expected initialActionId5 value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialStaminaInput()).to.eq('5', 'Expected initialStamina value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialDribbleInput()).to.eq('5', 'Expected initialDribble value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialShootInput()).to.eq('5', 'Expected initialShoot value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialPassInput()).to.eq('5', 'Expected initialPass value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialTackleInput()).to.eq('5', 'Expected initialTackle value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialBlockInput()).to.eq('5', 'Expected initialBlock value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialInterceptInput()).to.eq('5', 'Expected initialIntercept value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialSpeedInput()).to.eq('5', 'Expected initialSpeed value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialPowerInput()).to.eq('5', 'Expected initialPower value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialTechniqueInput()).to.eq('5', 'Expected initialTechnique value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialPunchingInput()).to.eq('5', 'Expected initialPunching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getInitialCatchingInput()).to.eq('5', 'Expected initialCatching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxStaminaInput()).to.eq('5', 'Expected maxStamina value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxDribbleInput()).to.eq('5', 'Expected maxDribble value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxShootInput()).to.eq('5', 'Expected maxShoot value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPassInput()).to.eq('5', 'Expected maxPass value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxTackleInput()).to.eq('5', 'Expected maxTackle value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxBlockInput()).to.eq('5', 'Expected maxBlock value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxInterceptInput()).to.eq('5', 'Expected maxIntercept value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxSpeedInput()).to.eq('5', 'Expected maxSpeed value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPowerInput()).to.eq('5', 'Expected maxPower value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxTechniqueInput()).to.eq('5', 'Expected maxTechnique value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPunchingInput()).to.eq('5', 'Expected maxPunching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxCatchingInput()).to.eq('5', 'Expected maxCatching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusDribbleInput()).to.eq('5', 'Expected maxPlusDribble value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusShootInput()).to.eq('5', 'Expected maxPlusShoot value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusPassInput()).to.eq('5', 'Expected maxPlusPass value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusTackleInput()).to.eq('5', 'Expected maxPlusTackle value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusBlockInput()).to.eq('5', 'Expected maxPlusBlock value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusInterceptInput()).to.eq('5', 'Expected maxPlusIntercept value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusSpeedInput()).to.eq('5', 'Expected maxPlusSpeed value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusPowerInput()).to.eq('5', 'Expected maxPlusPower value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusTechniqueInput()).to.eq('5', 'Expected maxPlusTechnique value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusPunchingInput()).to.eq('5', 'Expected maxPlusPunching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getMaxPlusCatchingInput()).to.eq('5', 'Expected maxPlusCatching value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getHighBallBonusInput()).to.eq('5', 'Expected highBallBonus value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getLowBallBonusInput()).to.eq('5', 'Expected lowBallBonus value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getIsDropOnlyInput()).to.eq('5', 'Expected isDropOnly value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getSellCoinGroupNumInput()).to.eq('5', 'Expected sellCoinGroupNum value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getSellMedalIdInput()).to.eq('5', 'Expected sellMedalId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getCharacterBookIdInput()).to.eq('5', 'Expected characterBookId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getBookNoInput()).to.eq('5', 'Expected bookNo value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getIsShowBookInput()).to.eq('5', 'Expected isShowBook value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getLevelGroupIdInput()).to.eq('5', 'Expected levelGroupId value to be equals to 5');
        expect(await mPlayableCardUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
        await mPlayableCardUpdatePage.save();
        expect(await mPlayableCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mPlayableCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MPlayableCard', async () => {
        const nbButtonsBeforeDelete = await mPlayableCardComponentsPage.countDeleteButtons();
        await mPlayableCardComponentsPage.clickOnLastDeleteButton();

        mPlayableCardDeleteDialog = new MPlayableCardDeleteDialog();
        expect(await mPlayableCardDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Playable Card?');
        await mPlayableCardDeleteDialog.clickOnConfirmButton();

        expect(await mPlayableCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
