/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MGuildMissionComponentsPage, MGuildMissionDeleteDialog, MGuildMissionUpdatePage } from './m-guild-mission.page-object';

const expect = chai.expect;

describe('MGuildMission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGuildMissionUpdatePage: MGuildMissionUpdatePage;
  let mGuildMissionComponentsPage: MGuildMissionComponentsPage;
  /*let mGuildMissionDeleteDialog: MGuildMissionDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGuildMissions', async () => {
    await navBarPage.goToEntity('m-guild-mission');
    mGuildMissionComponentsPage = new MGuildMissionComponentsPage();
    await browser.wait(ec.visibilityOf(mGuildMissionComponentsPage.title), 5000);
    expect(await mGuildMissionComponentsPage.getTitle()).to.eq('M Guild Missions');
  });

  it('should load create MGuildMission page', async () => {
    await mGuildMissionComponentsPage.clickOnCreateButton();
    mGuildMissionUpdatePage = new MGuildMissionUpdatePage();
    expect(await mGuildMissionUpdatePage.getPageTitle()).to.eq('Create or edit a M Guild Mission');
    await mGuildMissionUpdatePage.cancel();
  });

  /* it('should create and save MGuildMissions', async () => {
        const nbButtonsBeforeCreate = await mGuildMissionComponentsPage.countDeleteButtons();

        await mGuildMissionComponentsPage.clickOnCreateButton();
        await promise.all([
            mGuildMissionUpdatePage.setTermInput('5'),
            mGuildMissionUpdatePage.setTitleInput('title'),
            mGuildMissionUpdatePage.setDescriptionInput('description'),
            mGuildMissionUpdatePage.setMissionTypeInput('5'),
            mGuildMissionUpdatePage.setParam1Input('5'),
            mGuildMissionUpdatePage.setRewardIdInput('5'),
            mGuildMissionUpdatePage.setLinkInput('5'),
            mGuildMissionUpdatePage.setSceneTransitionParamInput('sceneTransitionParam'),
            mGuildMissionUpdatePage.setPickupInput('5'),
            mGuildMissionUpdatePage.setTriggerMissionIdInput('5'),
            mGuildMissionUpdatePage.setOrderNumInput('5'),
            mGuildMissionUpdatePage.mmissionrewardSelectLastOption(),
        ]);
        expect(await mGuildMissionUpdatePage.getTermInput()).to.eq('5', 'Expected term value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
        expect(await mGuildMissionUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await mGuildMissionUpdatePage.getMissionTypeInput()).to.eq('5', 'Expected missionType value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getParam1Input()).to.eq('5', 'Expected param1 value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getRewardIdInput()).to.eq('5', 'Expected rewardId value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getLinkInput()).to.eq('5', 'Expected link value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getSceneTransitionParamInput()).to.eq('sceneTransitionParam', 'Expected SceneTransitionParam value to be equals to sceneTransitionParam');
        expect(await mGuildMissionUpdatePage.getPickupInput()).to.eq('5', 'Expected pickup value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getTriggerMissionIdInput()).to.eq('5', 'Expected triggerMissionId value to be equals to 5');
        expect(await mGuildMissionUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
        await mGuildMissionUpdatePage.save();
        expect(await mGuildMissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mGuildMissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MGuildMission', async () => {
        const nbButtonsBeforeDelete = await mGuildMissionComponentsPage.countDeleteButtons();
        await mGuildMissionComponentsPage.clickOnLastDeleteButton();

        mGuildMissionDeleteDialog = new MGuildMissionDeleteDialog();
        expect(await mGuildMissionDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Guild Mission?');
        await mGuildMissionDeleteDialog.clickOnConfirmButton();

        expect(await mGuildMissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
