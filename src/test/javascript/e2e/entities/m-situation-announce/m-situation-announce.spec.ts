/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MSituationAnnounceComponentsPage,
  MSituationAnnounceDeleteDialog,
  MSituationAnnounceUpdatePage
} from './m-situation-announce.page-object';

const expect = chai.expect;

describe('MSituationAnnounce e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mSituationAnnounceUpdatePage: MSituationAnnounceUpdatePage;
  let mSituationAnnounceComponentsPage: MSituationAnnounceComponentsPage;
  let mSituationAnnounceDeleteDialog: MSituationAnnounceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MSituationAnnounces', async () => {
    await navBarPage.goToEntity('m-situation-announce');
    mSituationAnnounceComponentsPage = new MSituationAnnounceComponentsPage();
    await browser.wait(ec.visibilityOf(mSituationAnnounceComponentsPage.title), 5000);
    expect(await mSituationAnnounceComponentsPage.getTitle()).to.eq('M Situation Announces');
  });

  it('should load create MSituationAnnounce page', async () => {
    await mSituationAnnounceComponentsPage.clickOnCreateButton();
    mSituationAnnounceUpdatePage = new MSituationAnnounceUpdatePage();
    expect(await mSituationAnnounceUpdatePage.getPageTitle()).to.eq('Create or edit a M Situation Announce');
    await mSituationAnnounceUpdatePage.cancel();
  });

  it('should create and save MSituationAnnounces', async () => {
    const nbButtonsBeforeCreate = await mSituationAnnounceComponentsPage.countDeleteButtons();

    await mSituationAnnounceComponentsPage.clickOnCreateButton();
    await promise.all([mSituationAnnounceUpdatePage.setSituationIdInput('5'), mSituationAnnounceUpdatePage.setGroupIdInput('5')]);
    expect(await mSituationAnnounceUpdatePage.getSituationIdInput()).to.eq('5', 'Expected situationId value to be equals to 5');
    expect(await mSituationAnnounceUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    await mSituationAnnounceUpdatePage.save();
    expect(await mSituationAnnounceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mSituationAnnounceComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MSituationAnnounce', async () => {
    const nbButtonsBeforeDelete = await mSituationAnnounceComponentsPage.countDeleteButtons();
    await mSituationAnnounceComponentsPage.clickOnLastDeleteButton();

    mSituationAnnounceDeleteDialog = new MSituationAnnounceDeleteDialog();
    expect(await mSituationAnnounceDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Situation Announce?');
    await mSituationAnnounceDeleteDialog.clickOnConfirmButton();

    expect(await mSituationAnnounceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
