/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MQuestStageConditionDescriptionComponentsPage,
  MQuestStageConditionDescriptionDeleteDialog,
  MQuestStageConditionDescriptionUpdatePage
} from './m-quest-stage-condition-description.page-object';

const expect = chai.expect;

describe('MQuestStageConditionDescription e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mQuestStageConditionDescriptionUpdatePage: MQuestStageConditionDescriptionUpdatePage;
  let mQuestStageConditionDescriptionComponentsPage: MQuestStageConditionDescriptionComponentsPage;
  let mQuestStageConditionDescriptionDeleteDialog: MQuestStageConditionDescriptionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MQuestStageConditionDescriptions', async () => {
    await navBarPage.goToEntity('m-quest-stage-condition-description');
    mQuestStageConditionDescriptionComponentsPage = new MQuestStageConditionDescriptionComponentsPage();
    await browser.wait(ec.visibilityOf(mQuestStageConditionDescriptionComponentsPage.title), 5000);
    expect(await mQuestStageConditionDescriptionComponentsPage.getTitle()).to.eq('M Quest Stage Condition Descriptions');
  });

  it('should load create MQuestStageConditionDescription page', async () => {
    await mQuestStageConditionDescriptionComponentsPage.clickOnCreateButton();
    mQuestStageConditionDescriptionUpdatePage = new MQuestStageConditionDescriptionUpdatePage();
    expect(await mQuestStageConditionDescriptionUpdatePage.getPageTitle()).to.eq('Create or edit a M Quest Stage Condition Description');
    await mQuestStageConditionDescriptionUpdatePage.cancel();
  });

  it('should create and save MQuestStageConditionDescriptions', async () => {
    const nbButtonsBeforeCreate = await mQuestStageConditionDescriptionComponentsPage.countDeleteButtons();

    await mQuestStageConditionDescriptionComponentsPage.clickOnCreateButton();
    await promise.all([
      mQuestStageConditionDescriptionUpdatePage.setSuccessConditionTypeInput('5'),
      mQuestStageConditionDescriptionUpdatePage.setSuccessConditionDetailTypeValueInput('5'),
      mQuestStageConditionDescriptionUpdatePage.setHasExistTargetCharacterGroupInput('5'),
      mQuestStageConditionDescriptionUpdatePage.setHasSuccessConditionValueOneOnlyInput('5'),
      mQuestStageConditionDescriptionUpdatePage.setFailureConditionTypeValueInput('5'),
      mQuestStageConditionDescriptionUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mQuestStageConditionDescriptionUpdatePage.getSuccessConditionTypeInput()).to.eq(
      '5',
      'Expected successConditionType value to be equals to 5'
    );
    expect(await mQuestStageConditionDescriptionUpdatePage.getSuccessConditionDetailTypeValueInput()).to.eq(
      '5',
      'Expected successConditionDetailTypeValue value to be equals to 5'
    );
    expect(await mQuestStageConditionDescriptionUpdatePage.getHasExistTargetCharacterGroupInput()).to.eq(
      '5',
      'Expected hasExistTargetCharacterGroup value to be equals to 5'
    );
    expect(await mQuestStageConditionDescriptionUpdatePage.getHasSuccessConditionValueOneOnlyInput()).to.eq(
      '5',
      'Expected hasSuccessConditionValueOneOnly value to be equals to 5'
    );
    expect(await mQuestStageConditionDescriptionUpdatePage.getFailureConditionTypeValueInput()).to.eq(
      '5',
      'Expected failureConditionTypeValue value to be equals to 5'
    );
    expect(await mQuestStageConditionDescriptionUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mQuestStageConditionDescriptionUpdatePage.save();
    expect(await mQuestStageConditionDescriptionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mQuestStageConditionDescriptionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MQuestStageConditionDescription', async () => {
    const nbButtonsBeforeDelete = await mQuestStageConditionDescriptionComponentsPage.countDeleteButtons();
    await mQuestStageConditionDescriptionComponentsPage.clickOnLastDeleteButton();

    mQuestStageConditionDescriptionDeleteDialog = new MQuestStageConditionDescriptionDeleteDialog();
    expect(await mQuestStageConditionDescriptionDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Quest Stage Condition Description?'
    );
    await mQuestStageConditionDescriptionDeleteDialog.clickOnConfirmButton();

    expect(await mQuestStageConditionDescriptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
