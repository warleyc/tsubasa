/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMissionComponentsPage, MMissionDeleteDialog, MMissionUpdatePage } from './m-mission.page-object';

const expect = chai.expect;

describe('MMission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMissionUpdatePage: MMissionUpdatePage;
  let mMissionComponentsPage: MMissionComponentsPage;
  /*let mMissionDeleteDialog: MMissionDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMissions', async () => {
    await navBarPage.goToEntity('m-mission');
    mMissionComponentsPage = new MMissionComponentsPage();
    await browser.wait(ec.visibilityOf(mMissionComponentsPage.title), 5000);
    expect(await mMissionComponentsPage.getTitle()).to.eq('M Missions');
  });

  it('should load create MMission page', async () => {
    await mMissionComponentsPage.clickOnCreateButton();
    mMissionUpdatePage = new MMissionUpdatePage();
    expect(await mMissionUpdatePage.getPageTitle()).to.eq('Create or edit a M Mission');
    await mMissionUpdatePage.cancel();
  });

  /* it('should create and save MMissions', async () => {
        const nbButtonsBeforeCreate = await mMissionComponentsPage.countDeleteButtons();

        await mMissionComponentsPage.clickOnCreateButton();
        await promise.all([
            mMissionUpdatePage.setTermInput('5'),
            mMissionUpdatePage.setRoundNumInput('5'),
            mMissionUpdatePage.setTitleInput('title'),
            mMissionUpdatePage.setDescriptionInput('description'),
            mMissionUpdatePage.setMissionTypeInput('5'),
            mMissionUpdatePage.setAbsoluteInput('5'),
            mMissionUpdatePage.setParam1Input('5'),
            mMissionUpdatePage.setParam2Input('5'),
            mMissionUpdatePage.setParam3Input('5'),
            mMissionUpdatePage.setParam4Input('5'),
            mMissionUpdatePage.setParam5Input('5'),
            mMissionUpdatePage.setTriggerInput('5'),
            mMissionUpdatePage.setTriggerConditionInput('5'),
            mMissionUpdatePage.setRewardIdInput('5'),
            mMissionUpdatePage.setStartAtInput('5'),
            mMissionUpdatePage.setEndAtInput('5'),
            mMissionUpdatePage.setLinkInput('5'),
            mMissionUpdatePage.setSceneTransitionParamInput('sceneTransitionParam'),
            mMissionUpdatePage.setPickupInput('5'),
            mMissionUpdatePage.setOrderNumInput('5'),
            mMissionUpdatePage.idSelectLastOption(),
        ]);
        expect(await mMissionUpdatePage.getTermInput()).to.eq('5', 'Expected term value to be equals to 5');
        expect(await mMissionUpdatePage.getRoundNumInput()).to.eq('5', 'Expected roundNum value to be equals to 5');
        expect(await mMissionUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
        expect(await mMissionUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await mMissionUpdatePage.getMissionTypeInput()).to.eq('5', 'Expected missionType value to be equals to 5');
        expect(await mMissionUpdatePage.getAbsoluteInput()).to.eq('5', 'Expected absolute value to be equals to 5');
        expect(await mMissionUpdatePage.getParam1Input()).to.eq('5', 'Expected param1 value to be equals to 5');
        expect(await mMissionUpdatePage.getParam2Input()).to.eq('5', 'Expected param2 value to be equals to 5');
        expect(await mMissionUpdatePage.getParam3Input()).to.eq('5', 'Expected param3 value to be equals to 5');
        expect(await mMissionUpdatePage.getParam4Input()).to.eq('5', 'Expected param4 value to be equals to 5');
        expect(await mMissionUpdatePage.getParam5Input()).to.eq('5', 'Expected param5 value to be equals to 5');
        expect(await mMissionUpdatePage.getTriggerInput()).to.eq('5', 'Expected trigger value to be equals to 5');
        expect(await mMissionUpdatePage.getTriggerConditionInput()).to.eq('5', 'Expected triggerCondition value to be equals to 5');
        expect(await mMissionUpdatePage.getRewardIdInput()).to.eq('5', 'Expected rewardId value to be equals to 5');
        expect(await mMissionUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
        expect(await mMissionUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
        expect(await mMissionUpdatePage.getLinkInput()).to.eq('5', 'Expected link value to be equals to 5');
        expect(await mMissionUpdatePage.getSceneTransitionParamInput()).to.eq('sceneTransitionParam', 'Expected SceneTransitionParam value to be equals to sceneTransitionParam');
        expect(await mMissionUpdatePage.getPickupInput()).to.eq('5', 'Expected pickup value to be equals to 5');
        expect(await mMissionUpdatePage.getOrderNumInput()).to.eq('5', 'Expected orderNum value to be equals to 5');
        await mMissionUpdatePage.save();
        expect(await mMissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mMissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MMission', async () => {
        const nbButtonsBeforeDelete = await mMissionComponentsPage.countDeleteButtons();
        await mMissionComponentsPage.clickOnLastDeleteButton();

        mMissionDeleteDialog = new MMissionDeleteDialog();
        expect(await mMissionDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Mission?');
        await mMissionDeleteDialog.clickOnConfirmButton();

        expect(await mMissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
