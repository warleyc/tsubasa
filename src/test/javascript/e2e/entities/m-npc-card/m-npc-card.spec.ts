/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MNpcCardComponentsPage, MNpcCardDeleteDialog, MNpcCardUpdatePage } from './m-npc-card.page-object';

const expect = chai.expect;

describe('MNpcCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mNpcCardUpdatePage: MNpcCardUpdatePage;
  let mNpcCardComponentsPage: MNpcCardComponentsPage;
  /*let mNpcCardDeleteDialog: MNpcCardDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MNpcCards', async () => {
    await navBarPage.goToEntity('m-npc-card');
    mNpcCardComponentsPage = new MNpcCardComponentsPage();
    await browser.wait(ec.visibilityOf(mNpcCardComponentsPage.title), 5000);
    expect(await mNpcCardComponentsPage.getTitle()).to.eq('M Npc Cards');
  });

  it('should load create MNpcCard page', async () => {
    await mNpcCardComponentsPage.clickOnCreateButton();
    mNpcCardUpdatePage = new MNpcCardUpdatePage();
    expect(await mNpcCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Npc Card');
    await mNpcCardUpdatePage.cancel();
  });

  /* it('should create and save MNpcCards', async () => {
        const nbButtonsBeforeCreate = await mNpcCardComponentsPage.countDeleteButtons();

        await mNpcCardComponentsPage.clickOnCreateButton();
        await promise.all([
            mNpcCardUpdatePage.setCharacterIdInput('5'),
            mNpcCardUpdatePage.setShortNameInput('shortName'),
            mNpcCardUpdatePage.setNickNameInput('nickName'),
            mNpcCardUpdatePage.setTeamIdInput('5'),
            mNpcCardUpdatePage.setNationalityIdInput('5'),
            mNpcCardUpdatePage.setRarityInput('5'),
            mNpcCardUpdatePage.setAttributeInput('5'),
            mNpcCardUpdatePage.setModelIdInput('5'),
            mNpcCardUpdatePage.setLevelInput('5'),
            mNpcCardUpdatePage.setThumbnailAssetsIdInput('5'),
            mNpcCardUpdatePage.setCardIllustAssetsIdInput('5'),
            mNpcCardUpdatePage.setPlayableAssetsIdInput('5'),
            mNpcCardUpdatePage.setTeamEffectIdInput('5'),
            mNpcCardUpdatePage.setTeamEffectLevelInput('5'),
            mNpcCardUpdatePage.setTriggerEffectIdInput('5'),
            mNpcCardUpdatePage.setAction1IdInput('5'),
            mNpcCardUpdatePage.setAction1LevelInput('5'),
            mNpcCardUpdatePage.setAction2IdInput('5'),
            mNpcCardUpdatePage.setAction2LevelInput('5'),
            mNpcCardUpdatePage.setAction3IdInput('5'),
            mNpcCardUpdatePage.setAction3LevelInput('5'),
            mNpcCardUpdatePage.setAction4IdInput('5'),
            mNpcCardUpdatePage.setAction4LevelInput('5'),
            mNpcCardUpdatePage.setAction5IdInput('5'),
            mNpcCardUpdatePage.setAction5LevelInput('5'),
            mNpcCardUpdatePage.setStaminaInput('5'),
            mNpcCardUpdatePage.setDribbleInput('5'),
            mNpcCardUpdatePage.setShootInput('5'),
            mNpcCardUpdatePage.setBallPassInput('5'),
            mNpcCardUpdatePage.setTackleInput('5'),
            mNpcCardUpdatePage.setBlockInput('5'),
            mNpcCardUpdatePage.setInterceptInput('5'),
            mNpcCardUpdatePage.setSpeedInput('5'),
            mNpcCardUpdatePage.setPowerInput('5'),
            mNpcCardUpdatePage.setTechniqueInput('5'),
            mNpcCardUpdatePage.setPunchingInput('5'),
            mNpcCardUpdatePage.setCatchingInput('5'),
            mNpcCardUpdatePage.setHighBallBonusInput('5'),
            mNpcCardUpdatePage.setLowBallBonusInput('5'),
            mNpcCardUpdatePage.setPersonalityIdInput('5'),
            mNpcCardUpdatePage.setUniformNoInput('5'),
            mNpcCardUpdatePage.setLevelGroupIdInput('5'),
            mNpcCardUpdatePage.mcharacterSelectLastOption(),
        ]);
        expect(await mNpcCardUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
        expect(await mNpcCardUpdatePage.getNickNameInput()).to.eq('nickName', 'Expected NickName value to be equals to nickName');
        expect(await mNpcCardUpdatePage.getTeamIdInput()).to.eq('5', 'Expected teamId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getNationalityIdInput()).to.eq('5', 'Expected nationalityId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAttributeInput()).to.eq('5', 'Expected attribute value to be equals to 5');
        expect(await mNpcCardUpdatePage.getModelIdInput()).to.eq('5', 'Expected modelId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getThumbnailAssetsIdInput()).to.eq('5', 'Expected thumbnailAssetsId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getCardIllustAssetsIdInput()).to.eq('5', 'Expected cardIllustAssetsId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getPlayableAssetsIdInput()).to.eq('5', 'Expected playableAssetsId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getTeamEffectIdInput()).to.eq('5', 'Expected teamEffectId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getTeamEffectLevelInput()).to.eq('5', 'Expected teamEffectLevel value to be equals to 5');
        expect(await mNpcCardUpdatePage.getTriggerEffectIdInput()).to.eq('5', 'Expected triggerEffectId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction1IdInput()).to.eq('5', 'Expected action1Id value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction1LevelInput()).to.eq('5', 'Expected action1Level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction2IdInput()).to.eq('5', 'Expected action2Id value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction2LevelInput()).to.eq('5', 'Expected action2Level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction3IdInput()).to.eq('5', 'Expected action3Id value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction3LevelInput()).to.eq('5', 'Expected action3Level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction4IdInput()).to.eq('5', 'Expected action4Id value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction4LevelInput()).to.eq('5', 'Expected action4Level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction5IdInput()).to.eq('5', 'Expected action5Id value to be equals to 5');
        expect(await mNpcCardUpdatePage.getAction5LevelInput()).to.eq('5', 'Expected action5Level value to be equals to 5');
        expect(await mNpcCardUpdatePage.getStaminaInput()).to.eq('5', 'Expected stamina value to be equals to 5');
        expect(await mNpcCardUpdatePage.getDribbleInput()).to.eq('5', 'Expected dribble value to be equals to 5');
        expect(await mNpcCardUpdatePage.getShootInput()).to.eq('5', 'Expected shoot value to be equals to 5');
        expect(await mNpcCardUpdatePage.getBallPassInput()).to.eq('5', 'Expected ballPass value to be equals to 5');
        expect(await mNpcCardUpdatePage.getTackleInput()).to.eq('5', 'Expected tackle value to be equals to 5');
        expect(await mNpcCardUpdatePage.getBlockInput()).to.eq('5', 'Expected block value to be equals to 5');
        expect(await mNpcCardUpdatePage.getInterceptInput()).to.eq('5', 'Expected intercept value to be equals to 5');
        expect(await mNpcCardUpdatePage.getSpeedInput()).to.eq('5', 'Expected speed value to be equals to 5');
        expect(await mNpcCardUpdatePage.getPowerInput()).to.eq('5', 'Expected power value to be equals to 5');
        expect(await mNpcCardUpdatePage.getTechniqueInput()).to.eq('5', 'Expected technique value to be equals to 5');
        expect(await mNpcCardUpdatePage.getPunchingInput()).to.eq('5', 'Expected punching value to be equals to 5');
        expect(await mNpcCardUpdatePage.getCatchingInput()).to.eq('5', 'Expected catching value to be equals to 5');
        expect(await mNpcCardUpdatePage.getHighBallBonusInput()).to.eq('5', 'Expected highBallBonus value to be equals to 5');
        expect(await mNpcCardUpdatePage.getLowBallBonusInput()).to.eq('5', 'Expected lowBallBonus value to be equals to 5');
        expect(await mNpcCardUpdatePage.getPersonalityIdInput()).to.eq('5', 'Expected personalityId value to be equals to 5');
        expect(await mNpcCardUpdatePage.getUniformNoInput()).to.eq('5', 'Expected uniformNo value to be equals to 5');
        expect(await mNpcCardUpdatePage.getLevelGroupIdInput()).to.eq('5', 'Expected levelGroupId value to be equals to 5');
        await mNpcCardUpdatePage.save();
        expect(await mNpcCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mNpcCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MNpcCard', async () => {
        const nbButtonsBeforeDelete = await mNpcCardComponentsPage.countDeleteButtons();
        await mNpcCardComponentsPage.clickOnLastDeleteButton();

        mNpcCardDeleteDialog = new MNpcCardDeleteDialog();
        expect(await mNpcCardDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Npc Card?');
        await mNpcCardDeleteDialog.clickOnConfirmButton();

        expect(await mNpcCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
