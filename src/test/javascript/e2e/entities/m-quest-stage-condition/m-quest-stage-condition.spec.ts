/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestStageConditionComponentsPage,
  MQuestStageConditionDeleteDialog,
  MQuestStageConditionUpdatePage
} from './m-quest-stage-condition.page-object';

const expect = chai.expect;

describe('MQuestStageCondition e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestStageConditionUpdatePage: MQuestStageConditionUpdatePage;
  let mQuestStageConditionComponentsPage: MQuestStageConditionComponentsPage;
  let mQuestStageConditionDeleteDialog: MQuestStageConditionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestStageConditions', async () => {
    await navBarPage.goToEntity('m-quest-stage-condition');
    mQuestStageConditionComponentsPage = new MQuestStageConditionComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestStageConditionComponentsPage.title), 5000);
    expect(await mQuestStageConditionComponentsPage.getTitle()).to.eq('M Quest Stage Conditions');
  });

  it('should load create MQuestStageCondition page', async () => {
    await mQuestStageConditionComponentsPage.clickOnCreateButton();
    mQuestStageConditionUpdatePage = new MQuestStageConditionUpdatePage();
    expect(await mQuestStageConditionUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Stage Condition');
    await mQuestStageConditionUpdatePage.cancel();
  });

  it('should create and save MQuestStageConditions', async () => {
    const nbButtonsBeforeCreate = await mQuestStageConditionComponentsPage.countDeleteButtons();

    await mQuestStageConditionComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestStageConditionUpdatePage.setSuccessConditionTypeInput('5'),
      mQuestStageConditionUpdatePage.setSuccessConditionDetailTypeInput('5'),
      mQuestStageConditionUpdatePage.setSuccessConditionValueInput('5'),
      mQuestStageConditionUpdatePage.setTargetCharacterGroupIdInput('5'),
      mQuestStageConditionUpdatePage.setFailureConditionTypeInput('5')
    ]);
    expect(await mQuestStageConditionUpdatePage.getSuccessConditionTypeInput()).to.eq(
      '5',
      'Expected successConditionType value to be equals to 5'
    );
    expect(await mQuestStageConditionUpdatePage.getSuccessConditionDetailTypeInput()).to.eq(
      '5',
      'Expected successConditionDetailType value to be equals to 5'
    );
    expect(await mQuestStageConditionUpdatePage.getSuccessConditionValueInput()).to.eq(
      '5',
      'Expected successConditionValue value to be equals to 5'
    );
    expect(await mQuestStageConditionUpdatePage.getTargetCharacterGroupIdInput()).to.eq(
      '5',
      'Expected targetCharacterGroupId value to be equals to 5'
    );
    expect(await mQuestStageConditionUpdatePage.getFailureConditionTypeInput()).to.eq(
      '5',
      'Expected failureConditionType value to be equals to 5'
    );
    await mQuestStageConditionUpdatePage.save();
    expect(await mQuestStageConditionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestStageConditionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestStageCondition', async () => {
    const nbButtonsBeforeDelete = await mQuestStageConditionComponentsPage.countDeleteButtons();
    await mQuestStageConditionComponentsPage.clickOnLastDeleteButton();

    mQuestStageConditionDeleteDialog = new MQuestStageConditionDeleteDialog();
    expect(await mQuestStageConditionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Quest Stage Condition?');
    await mQuestStageConditionDeleteDialog.clickOnConfirmButton();

    expect(await mQuestStageConditionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
