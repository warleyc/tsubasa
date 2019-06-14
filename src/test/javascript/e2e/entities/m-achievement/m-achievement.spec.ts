/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MAchievementComponentsPage, MAchievementDeleteDialog, MAchievementUpdatePage } from './m-achievement.page-object';

const expect = chai.expect;

describe('MAchievement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAchievementUpdatePage: MAchievementUpdatePage;
  let mAchievementComponentsPage: MAchievementComponentsPage;
  /*let mAchievementDeleteDialog: MAchievementDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAchievements', async () => {
    await navBarPage.goToEntity('m-achievement');
    mAchievementComponentsPage = new MAchievementComponentsPage();
    await browser.wait(ec.visibilityOf(mAchievementComponentsPage.title), 5000);
    expect(await mAchievementComponentsPage.getTitle()).to.eq('M Achievements');
  });

  it('should load create MAchievement page', async () => {
    await mAchievementComponentsPage.clickOnCreateButton();
    mAchievementUpdatePage = new MAchievementUpdatePage();
    expect(await mAchievementUpdatePage.getPageTitle()).to.eq('Create or edit a M Achievement');
    await mAchievementUpdatePage.cancel();
  });

  /* it('should create and save MAchievements', async () => {
        const nbButtonsBeforeCreate = await mAchievementComponentsPage.countDeleteButtons();

        await mAchievementComponentsPage.clickOnCreateButton();
        await promise.all([
            mAchievementUpdatePage.setAchievementIdInput('achievementId'),
            mAchievementUpdatePage.setNameInput('name'),
            mAchievementUpdatePage.setTypeInput('5'),
            mAchievementUpdatePage.setMissionIdInput('5'),
            mAchievementUpdatePage.mmissionSelectLastOption(),
        ]);
        expect(await mAchievementUpdatePage.getAchievementIdInput()).to.eq('achievementId', 'Expected AchievementId value to be equals to achievementId');
        expect(await mAchievementUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await mAchievementUpdatePage.getTypeInput()).to.eq('5', 'Expected type value to be equals to 5');
        expect(await mAchievementUpdatePage.getMissionIdInput()).to.eq('5', 'Expected missionId value to be equals to 5');
        await mAchievementUpdatePage.save();
        expect(await mAchievementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mAchievementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MAchievement', async () => {
        const nbButtonsBeforeDelete = await mAchievementComponentsPage.countDeleteButtons();
        await mAchievementComponentsPage.clickOnLastDeleteButton();

        mAchievementDeleteDialog = new MAchievementDeleteDialog();
        expect(await mAchievementDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Achievement?');
        await mAchievementDeleteDialog.clickOnConfirmButton();

        expect(await mAchievementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
