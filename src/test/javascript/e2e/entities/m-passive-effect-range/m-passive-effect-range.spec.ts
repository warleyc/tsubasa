/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MPassiveEffectRangeComponentsPage,
  MPassiveEffectRangeDeleteDialog,
  MPassiveEffectRangeUpdatePage
} from './m-passive-effect-range.page-object';

const expect = chai.expect;

describe('MPassiveEffectRange e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mPassiveEffectRangeUpdatePage: MPassiveEffectRangeUpdatePage;
  let mPassiveEffectRangeComponentsPage: MPassiveEffectRangeComponentsPage;
  let mPassiveEffectRangeDeleteDialog: MPassiveEffectRangeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MPassiveEffectRanges', async () => {
    await navBarPage.goToEntity('m-passive-effect-range');
    mPassiveEffectRangeComponentsPage = new MPassiveEffectRangeComponentsPage();
    await browser.wait(ec.visibilityOf(mPassiveEffectRangeComponentsPage.title), 5000);
    expect(await mPassiveEffectRangeComponentsPage.getTitle()).to.eq('M Passive Effect Ranges');
  });

  it('should load create MPassiveEffectRange page', async () => {
    await mPassiveEffectRangeComponentsPage.clickOnCreateButton();
    mPassiveEffectRangeUpdatePage = new MPassiveEffectRangeUpdatePage();
    expect(await mPassiveEffectRangeUpdatePage.getPageTitle()).to.eq('Create or edit a M Passive Effect Range');
    await mPassiveEffectRangeUpdatePage.cancel();
  });

  it('should create and save MPassiveEffectRanges', async () => {
    const nbButtonsBeforeCreate = await mPassiveEffectRangeComponentsPage.countDeleteButtons();

    await mPassiveEffectRangeComponentsPage.clickOnCreateButton();
    await promise.all([
      mPassiveEffectRangeUpdatePage.setNameInput('name'),
      mPassiveEffectRangeUpdatePage.setEffectParamTypeInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPositionGkInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPositionFwInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPositionOmfInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPositionDmfInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPositionDfInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetAttributeInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetNationalityGroupIdInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetTeamGroupIdInput('5'),
      mPassiveEffectRangeUpdatePage.setTargetPlayableCardGroupIdInput('5'),
      mPassiveEffectRangeUpdatePage.setDescriptionInput('description')
    ]);
    expect(await mPassiveEffectRangeUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await mPassiveEffectRangeUpdatePage.getEffectParamTypeInput()).to.eq('5', 'Expected effectParamType value to be equals to 5');
    expect(await mPassiveEffectRangeUpdatePage.getTargetPositionGkInput()).to.eq('5', 'Expected targetPositionGk value to be equals to 5');
    expect(await mPassiveEffectRangeUpdatePage.getTargetPositionFwInput()).to.eq('5', 'Expected targetPositionFw value to be equals to 5');
    expect(await mPassiveEffectRangeUpdatePage.getTargetPositionOmfInput()).to.eq(
      '5',
      'Expected targetPositionOmf value to be equals to 5'
    );
    expect(await mPassiveEffectRangeUpdatePage.getTargetPositionDmfInput()).to.eq(
      '5',
      'Expected targetPositionDmf value to be equals to 5'
    );
    expect(await mPassiveEffectRangeUpdatePage.getTargetPositionDfInput()).to.eq('5', 'Expected targetPositionDf value to be equals to 5');
    expect(await mPassiveEffectRangeUpdatePage.getTargetAttributeInput()).to.eq('5', 'Expected targetAttribute value to be equals to 5');
    expect(await mPassiveEffectRangeUpdatePage.getTargetNationalityGroupIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupId value to be equals to 5'
    );
    expect(await mPassiveEffectRangeUpdatePage.getTargetTeamGroupIdInput()).to.eq(
      '5',
      'Expected targetTeamGroupId value to be equals to 5'
    );
    expect(await mPassiveEffectRangeUpdatePage.getTargetPlayableCardGroupIdInput()).to.eq(
      '5',
      'Expected targetPlayableCardGroupId value to be equals to 5'
    );
    expect(await mPassiveEffectRangeUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    await mPassiveEffectRangeUpdatePage.save();
    expect(await mPassiveEffectRangeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mPassiveEffectRangeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MPassiveEffectRange', async () => {
    const nbButtonsBeforeDelete = await mPassiveEffectRangeComponentsPage.countDeleteButtons();
    await mPassiveEffectRangeComponentsPage.clickOnLastDeleteButton();

    mPassiveEffectRangeDeleteDialog = new MPassiveEffectRangeDeleteDialog();
    expect(await mPassiveEffectRangeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Passive Effect Range?');
    await mPassiveEffectRangeDeleteDialog.clickOnConfirmButton();

    expect(await mPassiveEffectRangeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
