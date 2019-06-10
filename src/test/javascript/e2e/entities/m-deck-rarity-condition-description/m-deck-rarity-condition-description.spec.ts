/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MDeckRarityConditionDescriptionComponentsPage,
  MDeckRarityConditionDescriptionDeleteDialog,
  MDeckRarityConditionDescriptionUpdatePage
} from './m-deck-rarity-condition-description.page-object';

const expect = chai.expect;

describe('MDeckRarityConditionDescription e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mDeckRarityConditionDescriptionUpdatePage: MDeckRarityConditionDescriptionUpdatePage;
  let mDeckRarityConditionDescriptionComponentsPage: MDeckRarityConditionDescriptionComponentsPage;
  let mDeckRarityConditionDescriptionDeleteDialog: MDeckRarityConditionDescriptionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MDeckRarityConditionDescriptions', async () => {
    await navBarPage.goToEntity('m-deck-rarity-condition-description');
    mDeckRarityConditionDescriptionComponentsPage = new MDeckRarityConditionDescriptionComponentsPage();
    await browser.wait(ec.visibilityOf(mDeckRarityConditionDescriptionComponentsPage.title), 5000);
    expect(await mDeckRarityConditionDescriptionComponentsPage.getTitle()).to.eq('M Deck Rarity Condition Descriptions');
  });

  it('should load create MDeckRarityConditionDescription page', async () => {
    await mDeckRarityConditionDescriptionComponentsPage.clickOnCreateButton();
    mDeckRarityConditionDescriptionUpdatePage = new MDeckRarityConditionDescriptionUpdatePage();
    expect(await mDeckRarityConditionDescriptionUpdatePage.getPageTitle()).to.eq('Create or edit a M Deck Rarity Condition Description');
    await mDeckRarityConditionDescriptionUpdatePage.cancel();
  });

  it('should create and save MDeckRarityConditionDescriptions', async () => {
    const nbButtonsBeforeCreate = await mDeckRarityConditionDescriptionComponentsPage.countDeleteButtons();

    await mDeckRarityConditionDescriptionComponentsPage.clickOnCreateButton();
    await promise.all([
      mDeckRarityConditionDescriptionUpdatePage.setRarityGroupIdInput('5'),
      mDeckRarityConditionDescriptionUpdatePage.setCountTypeInput('5'),
      mDeckRarityConditionDescriptionUpdatePage.setIsStartingInput('5'),
      mDeckRarityConditionDescriptionUpdatePage.setDescriptionInput('description'),
      mDeckRarityConditionDescriptionUpdatePage.setIconNameInput('iconName'),
      mDeckRarityConditionDescriptionUpdatePage.setSmallIconNameInput('smallIconName')
    ]);
    expect(await mDeckRarityConditionDescriptionUpdatePage.getRarityGroupIdInput()).to.eq(
      '5',
      'Expected rarityGroupId value to be equals to 5'
    );
    expect(await mDeckRarityConditionDescriptionUpdatePage.getCountTypeInput()).to.eq('5', 'Expected countType value to be equals to 5');
    expect(await mDeckRarityConditionDescriptionUpdatePage.getIsStartingInput()).to.eq('5', 'Expected isStarting value to be equals to 5');
    expect(await mDeckRarityConditionDescriptionUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await mDeckRarityConditionDescriptionUpdatePage.getIconNameInput()).to.eq(
      'iconName',
      'Expected IconName value to be equals to iconName'
    );
    expect(await mDeckRarityConditionDescriptionUpdatePage.getSmallIconNameInput()).to.eq(
      'smallIconName',
      'Expected SmallIconName value to be equals to smallIconName'
    );
    await mDeckRarityConditionDescriptionUpdatePage.save();
    expect(await mDeckRarityConditionDescriptionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mDeckRarityConditionDescriptionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MDeckRarityConditionDescription', async () => {
    const nbButtonsBeforeDelete = await mDeckRarityConditionDescriptionComponentsPage.countDeleteButtons();
    await mDeckRarityConditionDescriptionComponentsPage.clickOnLastDeleteButton();

    mDeckRarityConditionDescriptionDeleteDialog = new MDeckRarityConditionDescriptionDeleteDialog();
    expect(await mDeckRarityConditionDescriptionDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Deck Rarity Condition Description?'
    );
    await mDeckRarityConditionDescriptionDeleteDialog.clickOnConfirmButton();

    expect(await mDeckRarityConditionDescriptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
