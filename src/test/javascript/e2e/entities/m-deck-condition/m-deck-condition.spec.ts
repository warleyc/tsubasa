/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MDeckConditionComponentsPage, MDeckConditionDeleteDialog, MDeckConditionUpdatePage } from './m-deck-condition.page-object';

const expect = chai.expect;

describe('MDeckCondition e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDeckConditionUpdatePage: MDeckConditionUpdatePage;
  let mDeckConditionComponentsPage: MDeckConditionComponentsPage;
  let mDeckConditionDeleteDialog: MDeckConditionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDeckConditions', async () => {
    await navBarPage.goToEntity('m-deck-condition');
    mDeckConditionComponentsPage = new MDeckConditionComponentsPage();
    await browser.wait(ec.visibilityOf(mDeckConditionComponentsPage.title), 5000);
    expect(await mDeckConditionComponentsPage.getTitle()).to.eq('M Deck Conditions');
  });

  it('should load create MDeckCondition page', async () => {
    await mDeckConditionComponentsPage.clickOnCreateButton();
    mDeckConditionUpdatePage = new MDeckConditionUpdatePage();
    expect(await mDeckConditionUpdatePage.getPageTitle()).to.eq('Create or edit a M Deck Condition');
    await mDeckConditionUpdatePage.cancel();
  });

  it('should create and save MDeckConditions', async () => {
    const nbButtonsBeforeCreate = await mDeckConditionComponentsPage.countDeleteButtons();

    await mDeckConditionComponentsPage.clickOnCreateButton();
    await promise.all([
      mDeckConditionUpdatePage.setTargetFormationGroupIdInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetRarityGroupIdInput('5'),
      mDeckConditionUpdatePage.setTargetAttributeInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetTeamGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetTeamGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetActionGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetActionGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectGroupMinIdInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectGroupMaxIdInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetRarityMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetAttributeMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetTeamMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetTeamMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetActionMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetActionMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectMinCountInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectMaxCountInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetCharacterIsStartingMaxInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetPlayableCardIsStartingMaxInput('5'),
      mDeckConditionUpdatePage.setTargetRarityIsStartingInput('5'),
      mDeckConditionUpdatePage.setTargetAttributeIsStartingInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetNationalityIsStartingMaxInput('5'),
      mDeckConditionUpdatePage.setTargetTeamIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetTeamIsStartingMaxInput('5'),
      mDeckConditionUpdatePage.setTargetActionIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetActionIsStartingMaxInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectIsStartingMinInput('5'),
      mDeckConditionUpdatePage.setTargetTriggerEffectIsStartingMaxInput('5')
    ]);
    expect(await mDeckConditionUpdatePage.getTargetFormationGroupIdInput()).to.eq(
      '5',
      'Expected targetFormationGroupId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterGroupMinIdInput()).to.eq(
      '5',
      'Expected targetCharacterGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetCharacterGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardGroupMinIdInput()).to.eq(
      '5',
      'Expected targetPlayableCardGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetPlayableCardGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetRarityGroupIdInput()).to.eq('5', 'Expected targetRarityGroupId value to be equals to 5');
    expect(await mDeckConditionUpdatePage.getTargetAttributeInput()).to.eq('5', 'Expected targetAttribute value to be equals to 5');
    expect(await mDeckConditionUpdatePage.getTargetNationalityGroupMinIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetNationalityGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetNationalityGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTeamGroupMinIdInput()).to.eq(
      '5',
      'Expected targetTeamGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTeamGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetTeamGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetActionGroupMinIdInput()).to.eq(
      '5',
      'Expected targetActionGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetActionGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetActionGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectGroupMinIdInput()).to.eq(
      '5',
      'Expected targetTriggerEffectGroupMinId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectGroupMaxIdInput()).to.eq(
      '5',
      'Expected targetTriggerEffectGroupMaxId value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterMinCountInput()).to.eq(
      '5',
      'Expected targetCharacterMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterMaxCountInput()).to.eq(
      '5',
      'Expected targetCharacterMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardMinCountInput()).to.eq(
      '5',
      'Expected targetPlayableCardMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardMaxCountInput()).to.eq(
      '5',
      'Expected targetPlayableCardMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetRarityMaxCountInput()).to.eq(
      '5',
      'Expected targetRarityMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetAttributeMinCountInput()).to.eq(
      '5',
      'Expected targetAttributeMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetNationalityMinCountInput()).to.eq(
      '5',
      'Expected targetNationalityMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetNationalityMaxCountInput()).to.eq(
      '5',
      'Expected targetNationalityMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTeamMinCountInput()).to.eq('5', 'Expected targetTeamMinCount value to be equals to 5');
    expect(await mDeckConditionUpdatePage.getTargetTeamMaxCountInput()).to.eq('5', 'Expected targetTeamMaxCount value to be equals to 5');
    expect(await mDeckConditionUpdatePage.getTargetActionMinCountInput()).to.eq(
      '5',
      'Expected targetActionMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetActionMaxCountInput()).to.eq(
      '5',
      'Expected targetActionMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectMinCountInput()).to.eq(
      '5',
      'Expected targetTriggerEffectMinCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectMaxCountInput()).to.eq(
      '5',
      'Expected targetTriggerEffectMaxCount value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterIsStartingMinInput()).to.eq(
      '5',
      'Expected targetCharacterIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetCharacterIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetCharacterIsStartingMax value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardIsStartingMinInput()).to.eq(
      '5',
      'Expected targetPlayableCardIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetPlayableCardIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetPlayableCardIsStartingMax value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetRarityIsStartingInput()).to.eq(
      '5',
      'Expected targetRarityIsStarting value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetAttributeIsStartingInput()).to.eq(
      '5',
      'Expected targetAttributeIsStarting value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetNationalityIsStartingMinInput()).to.eq(
      '5',
      'Expected targetNationalityIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetNationalityIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetNationalityIsStartingMax value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTeamIsStartingMinInput()).to.eq(
      '5',
      'Expected targetTeamIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTeamIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetTeamIsStartingMax value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetActionIsStartingMinInput()).to.eq(
      '5',
      'Expected targetActionIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetActionIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetActionIsStartingMax value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectIsStartingMinInput()).to.eq(
      '5',
      'Expected targetTriggerEffectIsStartingMin value to be equals to 5'
    );
    expect(await mDeckConditionUpdatePage.getTargetTriggerEffectIsStartingMaxInput()).to.eq(
      '5',
      'Expected targetTriggerEffectIsStartingMax value to be equals to 5'
    );
    await mDeckConditionUpdatePage.save();
    expect(await mDeckConditionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDeckConditionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDeckCondition', async () => {
    const nbButtonsBeforeDelete = await mDeckConditionComponentsPage.countDeleteButtons();
    await mDeckConditionComponentsPage.clickOnLastDeleteButton();

    mDeckConditionDeleteDialog = new MDeckConditionDeleteDialog();
    expect(await mDeckConditionDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Deck Condition?');
    await mDeckConditionDeleteDialog.clickOnConfirmButton();

    expect(await mDeckConditionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
