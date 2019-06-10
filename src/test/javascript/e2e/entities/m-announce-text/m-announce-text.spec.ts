/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MAnnounceTextComponentsPage, MAnnounceTextDeleteDialog, MAnnounceTextUpdatePage } from './m-announce-text.page-object';

const expect = chai.expect;

describe('MAnnounceText e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mAnnounceTextUpdatePage: MAnnounceTextUpdatePage;
  let mAnnounceTextComponentsPage: MAnnounceTextComponentsPage;
  let mAnnounceTextDeleteDialog: MAnnounceTextDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MAnnounceTexts', async () => {
    await navBarPage.goToEntity('m-announce-text');
    mAnnounceTextComponentsPage = new MAnnounceTextComponentsPage();
    await browser.wait(ec.visibilityOf(mAnnounceTextComponentsPage.title), 5000);
    expect(await mAnnounceTextComponentsPage.getTitle()).to.eq('M Announce Texts');
  });

  it('should load create MAnnounceText page', async () => {
    await mAnnounceTextComponentsPage.clickOnCreateButton();
    mAnnounceTextUpdatePage = new MAnnounceTextUpdatePage();
    expect(await mAnnounceTextUpdatePage.getPageTitle()).to.eq('Create or edit a M Announce Text');
    await mAnnounceTextUpdatePage.cancel();
  });

  it('should create and save MAnnounceTexts', async () => {
    const nbButtonsBeforeCreate = await mAnnounceTextComponentsPage.countDeleteButtons();

    await mAnnounceTextComponentsPage.clickOnCreateButton();
    await promise.all([
      mAnnounceTextUpdatePage.setGroupIdInput('5'),
      mAnnounceTextUpdatePage.setSeqNoInput('5'),
      mAnnounceTextUpdatePage.setNormalAnnounceInput('normalAnnounce'),
      mAnnounceTextUpdatePage.setCriticalSAnnounceInput('criticalSAnnounce'),
      mAnnounceTextUpdatePage.setCriticalMAnnounceInput('criticalMAnnounce'),
      mAnnounceTextUpdatePage.setCriticalLAnnounceInput('criticalLAnnounce'),
      mAnnounceTextUpdatePage.setDelayTimeInput('5'),
      mAnnounceTextUpdatePage.setContinueTimeInput('5')
    ]);
    expect(await mAnnounceTextUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await mAnnounceTextUpdatePage.getSeqNoInput()).to.eq('5', 'Expected seqNo value to be equals to 5');
    expect(await mAnnounceTextUpdatePage.getNormalAnnounceInput()).to.eq(
      'normalAnnounce',
      'Expected NormalAnnounce value to be equals to normalAnnounce'
    );
    expect(await mAnnounceTextUpdatePage.getCriticalSAnnounceInput()).to.eq(
      'criticalSAnnounce',
      'Expected CriticalSAnnounce value to be equals to criticalSAnnounce'
    );
    expect(await mAnnounceTextUpdatePage.getCriticalMAnnounceInput()).to.eq(
      'criticalMAnnounce',
      'Expected CriticalMAnnounce value to be equals to criticalMAnnounce'
    );
    expect(await mAnnounceTextUpdatePage.getCriticalLAnnounceInput()).to.eq(
      'criticalLAnnounce',
      'Expected CriticalLAnnounce value to be equals to criticalLAnnounce'
    );
    expect(await mAnnounceTextUpdatePage.getDelayTimeInput()).to.eq('5', 'Expected delayTime value to be equals to 5');
    expect(await mAnnounceTextUpdatePage.getContinueTimeInput()).to.eq('5', 'Expected continueTime value to be equals to 5');
    await mAnnounceTextUpdatePage.save();
    expect(await mAnnounceTextUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mAnnounceTextComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MAnnounceText', async () => {
    const nbButtonsBeforeDelete = await mAnnounceTextComponentsPage.countDeleteButtons();
    await mAnnounceTextComponentsPage.clickOnLastDeleteButton();

    mAnnounceTextDeleteDialog = new MAnnounceTextDeleteDialog();
    expect(await mAnnounceTextDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Announce Text?');
    await mAnnounceTextDeleteDialog.clickOnConfirmButton();

    expect(await mAnnounceTextComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
