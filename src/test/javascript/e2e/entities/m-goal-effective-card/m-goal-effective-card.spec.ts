/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MGoalEffectiveCardComponentsPage,
  MGoalEffectiveCardDeleteDialog,
  MGoalEffectiveCardUpdatePage
} from './m-goal-effective-card.page-object';

const expect = chai.expect;

describe('MGoalEffectiveCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mGoalEffectiveCardUpdatePage: MGoalEffectiveCardUpdatePage;
  let mGoalEffectiveCardComponentsPage: MGoalEffectiveCardComponentsPage;
  let mGoalEffectiveCardDeleteDialog: MGoalEffectiveCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MGoalEffectiveCards', async () => {
    await navBarPage.goToEntity('m-goal-effective-card');
    mGoalEffectiveCardComponentsPage = new MGoalEffectiveCardComponentsPage();
    await browser.wait(ec.visibilityOf(mGoalEffectiveCardComponentsPage.title), 5000);
    expect(await mGoalEffectiveCardComponentsPage.getTitle()).to.eq('M Goal Effective Cards');
  });

  it('should load create MGoalEffectiveCard page', async () => {
    await mGoalEffectiveCardComponentsPage.clickOnCreateButton();
    mGoalEffectiveCardUpdatePage = new MGoalEffectiveCardUpdatePage();
    expect(await mGoalEffectiveCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Goal Effective Card');
    await mGoalEffectiveCardUpdatePage.cancel();
  });

  it('should create and save MGoalEffectiveCards', async () => {
    const nbButtonsBeforeCreate = await mGoalEffectiveCardComponentsPage.countDeleteButtons();

    await mGoalEffectiveCardComponentsPage.clickOnCreateButton();
    await promise.all([
      mGoalEffectiveCardUpdatePage.setEventTypeInput('5'),
      mGoalEffectiveCardUpdatePage.setEventIdInput('5'),
      mGoalEffectiveCardUpdatePage.setPlayableCardIdInput('5'),
      mGoalEffectiveCardUpdatePage.setRateInput('5')
    ]);
    expect(await mGoalEffectiveCardUpdatePage.getEventTypeInput()).to.eq('5', 'Expected eventType value to be equals to 5');
    expect(await mGoalEffectiveCardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mGoalEffectiveCardUpdatePage.getPlayableCardIdInput()).to.eq('5', 'Expected playableCardId value to be equals to 5');
    expect(await mGoalEffectiveCardUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    await mGoalEffectiveCardUpdatePage.save();
    expect(await mGoalEffectiveCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mGoalEffectiveCardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MGoalEffectiveCard', async () => {
    const nbButtonsBeforeDelete = await mGoalEffectiveCardComponentsPage.countDeleteButtons();
    await mGoalEffectiveCardComponentsPage.clickOnLastDeleteButton();

    mGoalEffectiveCardDeleteDialog = new MGoalEffectiveCardDeleteDialog();
    expect(await mGoalEffectiveCardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Goal Effective Card?');
    await mGoalEffectiveCardDeleteDialog.clickOnConfirmButton();

    expect(await mGoalEffectiveCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
