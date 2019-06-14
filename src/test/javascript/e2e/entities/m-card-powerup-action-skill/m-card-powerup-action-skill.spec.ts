/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MCardPowerupActionSkillComponentsPage,
  MCardPowerupActionSkillDeleteDialog,
  MCardPowerupActionSkillUpdatePage
} from './m-card-powerup-action-skill.page-object';

const expect = chai.expect;

describe('MCardPowerupActionSkill e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCardPowerupActionSkillUpdatePage: MCardPowerupActionSkillUpdatePage;
  let mCardPowerupActionSkillComponentsPage: MCardPowerupActionSkillComponentsPage;
  /*let mCardPowerupActionSkillDeleteDialog: MCardPowerupActionSkillDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCardPowerupActionSkills', async () => {
    await navBarPage.goToEntity('m-card-powerup-action-skill');
    mCardPowerupActionSkillComponentsPage = new MCardPowerupActionSkillComponentsPage();
    await browser.wait(ec.visibilityOf(mCardPowerupActionSkillComponentsPage.title), 5000);
    expect(await mCardPowerupActionSkillComponentsPage.getTitle()).to.eq('M Card Powerup Action Skills');
  });

  it('should load create MCardPowerupActionSkill page', async () => {
    await mCardPowerupActionSkillComponentsPage.clickOnCreateButton();
    mCardPowerupActionSkillUpdatePage = new MCardPowerupActionSkillUpdatePage();
    expect(await mCardPowerupActionSkillUpdatePage.getPageTitle()).to.eq('Create or edit a M Card Powerup Action Skill');
    await mCardPowerupActionSkillUpdatePage.cancel();
  });

  /* it('should create and save MCardPowerupActionSkills', async () => {
        const nbButtonsBeforeCreate = await mCardPowerupActionSkillComponentsPage.countDeleteButtons();

        await mCardPowerupActionSkillComponentsPage.clickOnCreateButton();
        await promise.all([
            mCardPowerupActionSkillUpdatePage.setNameInput('name'),
            mCardPowerupActionSkillUpdatePage.setShortNameInput('shortName'),
            mCardPowerupActionSkillUpdatePage.setDescriptionInput('description'),
            mCardPowerupActionSkillUpdatePage.setRarityInput('5'),
            mCardPowerupActionSkillUpdatePage.setAttributeInput('5'),
            mCardPowerupActionSkillUpdatePage.setActionRarityInput('5'),
            mCardPowerupActionSkillUpdatePage.setGainActionExpInput('5'),
            mCardPowerupActionSkillUpdatePage.setCoinInput('5'),
            mCardPowerupActionSkillUpdatePage.setSellMedalIdInput('5'),
            mCardPowerupActionSkillUpdatePage.setThumbnailAssetsIdInput('5'),
            mCardPowerupActionSkillUpdatePage.setCardIllustAssetsIdInput('5'),
            mCardPowerupActionSkillUpdatePage.setTargetActionCommandTypeInput('5'),
            mCardPowerupActionSkillUpdatePage.setTargetCharacterIdInput('5'),
            mCardPowerupActionSkillUpdatePage.mcardthumbnailassetsSelectLastOption(),
        ]);
        expect(await mCardPowerupActionSkillUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mCardPowerupActionSkillUpdatePage.getShortNameInput()).to.eq('shortName', 'Expected ShortName value to be equals to shortName');
        expect(await mCardPowerupActionSkillUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await mCardPowerupActionSkillUpdatePage.getRarityInput()).to.eq('5', 'Expected rarity value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getAttributeInput()).to.eq('5', 'Expected attribute value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getActionRarityInput()).to.eq('5', 'Expected actionRarity value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getGainActionExpInput()).to.eq('5', 'Expected gainActionExp value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getCoinInput()).to.eq('5', 'Expected coin value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getSellMedalIdInput()).to.eq('5', 'Expected sellMedalId value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getThumbnailAssetsIdInput()).to.eq('5', 'Expected thumbnailAssetsId value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getCardIllustAssetsIdInput()).to.eq('5', 'Expected cardIllustAssetsId value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getTargetActionCommandTypeInput()).to.eq('5', 'Expected targetActionCommandType value to be equals to 5');
        expect(await mCardPowerupActionSkillUpdatePage.getTargetCharacterIdInput()).to.eq('5', 'Expected targetCharacterId value to be equals to 5');
        await mCardPowerupActionSkillUpdatePage.save();
        expect(await mCardPowerupActionSkillUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mCardPowerupActionSkillComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MCardPowerupActionSkill', async () => {
        const nbButtonsBeforeDelete = await mCardPowerupActionSkillComponentsPage.countDeleteButtons();
        await mCardPowerupActionSkillComponentsPage.clickOnLastDeleteButton();

        mCardPowerupActionSkillDeleteDialog = new MCardPowerupActionSkillDeleteDialog();
        expect(await mCardPowerupActionSkillDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Card Powerup Action Skill?');
        await mCardPowerupActionSkillDeleteDialog.clickOnConfirmButton();

        expect(await mCardPowerupActionSkillComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
