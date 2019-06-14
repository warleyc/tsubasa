/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MHomeMarqueeComponentsPage, MHomeMarqueeDeleteDialog, MHomeMarqueeUpdatePage } from './m-home-marquee.page-object';

const expect = chai.expect;

describe('MHomeMarquee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mHomeMarqueeUpdatePage: MHomeMarqueeUpdatePage;
  let mHomeMarqueeComponentsPage: MHomeMarqueeComponentsPage;
  let mHomeMarqueeDeleteDialog: MHomeMarqueeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MHomeMarquees', async () => {
    await navBarPage.goToEntity('m-home-marquee');
    mHomeMarqueeComponentsPage = new MHomeMarqueeComponentsPage();
    await browser.wait(ec.visibilityOf(mHomeMarqueeComponentsPage.title), 5000);
    expect(await mHomeMarqueeComponentsPage.getTitle()).to.eq('M Home Marquees');
  });

  it('should load create MHomeMarquee page', async () => {
    await mHomeMarqueeComponentsPage.clickOnCreateButton();
    mHomeMarqueeUpdatePage = new MHomeMarqueeUpdatePage();
    expect(await mHomeMarqueeUpdatePage.getPageTitle()).to.eq('Create or edit a M Home Marquee');
    await mHomeMarqueeUpdatePage.cancel();
  });

  it('should create and save MHomeMarquees', async () => {
    const nbButtonsBeforeCreate = await mHomeMarqueeComponentsPage.countDeleteButtons();

    await mHomeMarqueeComponentsPage.clickOnCreateButton();
    await promise.all([
      mHomeMarqueeUpdatePage.setPriorityInput('5'),
      mHomeMarqueeUpdatePage.setMarqueeTextInput('marqueeText'),
      mHomeMarqueeUpdatePage.setStartAtInput('5'),
      mHomeMarqueeUpdatePage.setEndAtInput('5')
    ]);
    expect(await mHomeMarqueeUpdatePage.getPriorityInput()).to.eq('5', 'Expected priority value to be equals to 5');
    expect(await mHomeMarqueeUpdatePage.getMarqueeTextInput()).to.eq(
      'marqueeText',
      'Expected MarqueeText value to be equals to marqueeText'
    );
    expect(await mHomeMarqueeUpdatePage.getStartAtInput()).to.eq('5', 'Expected startAt value to be equals to 5');
    expect(await mHomeMarqueeUpdatePage.getEndAtInput()).to.eq('5', 'Expected endAt value to be equals to 5');
    await mHomeMarqueeUpdatePage.save();
    expect(await mHomeMarqueeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mHomeMarqueeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last MHomeMarquee', async () => {
    const nbButtonsBeforeDelete = await mHomeMarqueeComponentsPage.countDeleteButtons();
    await mHomeMarqueeComponentsPage.clickOnLastDeleteButton();

    mHomeMarqueeDeleteDialog = new MHomeMarqueeDeleteDialog();
    expect(await mHomeMarqueeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Home Marquee?');
    await mHomeMarqueeDeleteDialog.clickOnConfirmButton();

    expect(await mHomeMarqueeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
