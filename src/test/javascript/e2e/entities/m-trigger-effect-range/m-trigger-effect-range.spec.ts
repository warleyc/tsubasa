/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTriggerEffectRangeComponentsPage,
  MTriggerEffectRangeDeleteDialog,
  MTriggerEffectRangeUpdatePage
} from './m-trigger-effect-range.page-object';

const expect = chai.expect;

describe('MTriggerEffectRange e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTriggerEffectRangeUpdatePage: MTriggerEffectRangeUpdatePage;
  let mTriggerEffectRangeComponentsPage: MTriggerEffectRangeComponentsPage;
  let mTriggerEffectRangeDeleteDialog: MTriggerEffectRangeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTriggerEffectRanges', async () => {
    await navBarPage.goToEntity('m-trigger-effect-range');
    mTriggerEffectRangeComponentsPage = new MTriggerEffectRangeComponentsPage();
    await browser.wait(ec.visibilityOf(mTriggerEffectRangeComponentsPage.title), 5000);
    expect(await mTriggerEffectRangeComponentsPage.getTitle()).to.eq('M Trigger Effect Ranges');
  });

  it('should load create MTriggerEffectRange page', async () => {
    await mTriggerEffectRangeComponentsPage.clickOnCreateButton();
    mTriggerEffectRangeUpdatePage = new MTriggerEffectRangeUpdatePage();
    expect(await mTriggerEffectRangeUpdatePage.getPageTitle()).to.eq('Create or edit a M Trigger Effect Range');
    await mTriggerEffectRangeUpdatePage.cancel();
  });

  it('should create and save MTriggerEffectRanges', async () => {
    const nbButtonsBeforeCreate = await mTriggerEffectRangeComponentsPage.countDeleteButtons();

    await mTriggerEffectRangeComponentsPage.clickOnCreateButton();
    await promise.all([
      mTriggerEffectRangeUpdatePage.setEffectTypeInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetPositionGkInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetPositionFwInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetPositionOmfInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetPositionDmfInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetPositionDfInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetAttributeInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetNationalityGroupIdInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetTeamGroupIdInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetCharacterGroupIdInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetFormationGroupIdInput('5'),
      mTriggerEffectRangeUpdatePage.setTargetActionCommandInput('5')
    ]);
    expect(await mTriggerEffectRangeUpdatePage.getEffectTypeInput()).to.eq('5', 'Expected effectType value to be equals to 5');
    expect(await mTriggerEffectRangeUpdatePage.getTargetPositionGkInput()).to.eq('5', 'Expected targetPositionGk value to be equals to 5');
    expect(await mTriggerEffectRangeUpdatePage.getTargetPositionFwInput()).to.eq('5', 'Expected targetPositionFw value to be equals to 5');
    expect(await mTriggerEffectRangeUpdatePage.getTargetPositionOmfInput()).to.eq(
      '5',
      'Expected targetPositionOmf value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetPositionDmfInput()).to.eq(
      '5',
      'Expected targetPositionDmf value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetPositionDfInput()).to.eq('5', 'Expected targetPositionDf value to be equals to 5');
    expect(await mTriggerEffectRangeUpdatePage.getTargetAttributeInput()).to.eq('5', 'Expected targetAttribute value to be equals to 5');
    expect(await mTriggerEffectRangeUpdatePage.getTargetNationalityGroupIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupId value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetTeamGroupIdInput()).to.eq(
      '5',
      'Expected targetTeamGroupId value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetCharacterGroupIdInput()).to.eq(
      '5',
      'Expected targetCharacterGroupId value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetFormationGroupIdInput()).to.eq(
      '5',
      'Expected targetFormationGroupId value to be equals to 5'
    );
    expect(await mTriggerEffectRangeUpdatePage.getTargetActionCommandInput()).to.eq(
      '5',
      'Expected targetActionCommand value to be equals to 5'
    );
    await mTriggerEffectRangeUpdatePage.save();
    expect(await mTriggerEffectRangeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mTriggerEffectRangeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MTriggerEffectRange', async () => {
    const nbButtonsBeforeDelete = await mTriggerEffectRangeComponentsPage.countDeleteButtons();
    await mTriggerEffectRangeComponentsPage.clickOnLastDeleteButton();

    mTriggerEffectRangeDeleteDialog = new MTriggerEffectRangeDeleteDialog();
    expect(await mTriggerEffectRangeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Trigger Effect Range?');
    await mTriggerEffectRangeDeleteDialog.clickOnConfirmButton();

    expect(await mTriggerEffectRangeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
