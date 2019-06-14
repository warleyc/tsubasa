/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MMarathonEffectiveCardComponentsPage,
  MMarathonEffectiveCardDeleteDialog,
  MMarathonEffectiveCardUpdatePage
} from './m-marathon-effective-card.page-object';

const expect = chai.expect;

describe('MMarathonEffectiveCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMarathonEffectiveCardUpdatePage: MMarathonEffectiveCardUpdatePage;
  let mMarathonEffectiveCardComponentsPage: MMarathonEffectiveCardComponentsPage;
  let mMarathonEffectiveCardDeleteDialog: MMarathonEffectiveCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMarathonEffectiveCards', async () => {
    await navBarPage.goToEntity('m-marathon-effective-card');
    mMarathonEffectiveCardComponentsPage = new MMarathonEffectiveCardComponentsPage();
    await browser.wait(ec.visibilityOf(mMarathonEffectiveCardComponentsPage.title), 5000);
    expect(await mMarathonEffectiveCardComponentsPage.getTitle()).to.eq('M Marathon Effective Cards');
  });

  it('should load create MMarathonEffectiveCard page', async () => {
    await mMarathonEffectiveCardComponentsPage.clickOnCreateButton();
    mMarathonEffectiveCardUpdatePage = new MMarathonEffectiveCardUpdatePage();
    expect(await mMarathonEffectiveCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Marathon Effective Card');
    await mMarathonEffectiveCardUpdatePage.cancel();
  });

  it('should create and save MMarathonEffectiveCards', async () => {
    const nbButtonsBeforeCreate = await mMarathonEffectiveCardComponentsPage.countDeleteButtons();

    await mMarathonEffectiveCardComponentsPage.clickOnCreateButton();
    await promise.all([
      mMarathonEffectiveCardUpdatePage.setEventIdInput('5'),
      mMarathonEffectiveCardUpdatePage.setPlayableCardIdInput('5'),
      mMarathonEffectiveCardUpdatePage.setRateInput('5')
    ]);
    expect(await mMarathonEffectiveCardUpdatePage.getEventIdInput()).to.eq('5', 'Expected eventId value to be equals to 5');
    expect(await mMarathonEffectiveCardUpdatePage.getPlayableCardIdInput()).to.eq('5', 'Expected playableCardId value to be equals to 5');
    expect(await mMarathonEffectiveCardUpdatePage.getRateInput()).to.eq('5', 'Expected rate value to be equals to 5');
    await mMarathonEffectiveCardUpdatePage.save();
    expect(await mMarathonEffectiveCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMarathonEffectiveCardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMarathonEffectiveCard', async () => {
    const nbButtonsBeforeDelete = await mMarathonEffectiveCardComponentsPage.countDeleteButtons();
    await mMarathonEffectiveCardComponentsPage.clickOnLastDeleteButton();

    mMarathonEffectiveCardDeleteDialog = new MMarathonEffectiveCardDeleteDialog();
    expect(await mMarathonEffectiveCardDeleteDialog.getDialogTitle()).to.eq(
      'Are you sure you want to delete this M Marathon Effective Card?'
    );
    await mMarathonEffectiveCardDeleteDialog.clickOnConfirmButton();

    expect(await mMarathonEffectiveCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
