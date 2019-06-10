/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MContentableCardComponentsPage, MContentableCardDeleteDialog, MContentableCardUpdatePage } from './m-contentable-card.page-object';

const expect = chai.expect;

describe('MContentableCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mContentableCardUpdatePage: MContentableCardUpdatePage;
  let mContentableCardComponentsPage: MContentableCardComponentsPage;
  let mContentableCardDeleteDialog: MContentableCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MContentableCards', async () => {
    await navBarPage.goToEntity('m-contentable-card');
    mContentableCardComponentsPage = new MContentableCardComponentsPage();
    await browser.wait(ec.visibilityOf(mContentableCardComponentsPage.title), 5000);
    expect(await mContentableCardComponentsPage.getTitle()).to.eq('M Contentable Cards');
  });

  it('should load create MContentableCard page', async () => {
    await mContentableCardComponentsPage.clickOnCreateButton();
    mContentableCardUpdatePage = new MContentableCardUpdatePage();
    expect(await mContentableCardUpdatePage.getPageTitle()).to.eq('Create or edit a M Contentable Card');
    await mContentableCardUpdatePage.cancel();
  });

  it('should create and save MContentableCards', async () => {
    const nbButtonsBeforeCreate = await mContentableCardComponentsPage.countDeleteButtons();

    await mContentableCardComponentsPage.clickOnCreateButton();
    await promise.all([
      mContentableCardUpdatePage.setPlayableCardIdInput('5'),
      mContentableCardUpdatePage.setLevelInput('5'),
      mContentableCardUpdatePage.setActionMainLevelInput('5'),
      mContentableCardUpdatePage.setActionSub1LevelInput('5'),
      mContentableCardUpdatePage.setActionSub2LevelInput('5'),
      mContentableCardUpdatePage.setActionSub3LevelInput('5'),
      mContentableCardUpdatePage.setActionSub4LevelInput('5'),
      mContentableCardUpdatePage.setPlusRateInput('5')
    ]);
    expect(await mContentableCardUpdatePage.getPlayableCardIdInput()).to.eq('5', 'Expected playableCardId value to be equals to 5');
    expect(await mContentableCardUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await mContentableCardUpdatePage.getActionMainLevelInput()).to.eq('5', 'Expected actionMainLevel value to be equals to 5');
    expect(await mContentableCardUpdatePage.getActionSub1LevelInput()).to.eq('5', 'Expected actionSub1Level value to be equals to 5');
    expect(await mContentableCardUpdatePage.getActionSub2LevelInput()).to.eq('5', 'Expected actionSub2Level value to be equals to 5');
    expect(await mContentableCardUpdatePage.getActionSub3LevelInput()).to.eq('5', 'Expected actionSub3Level value to be equals to 5');
    expect(await mContentableCardUpdatePage.getActionSub4LevelInput()).to.eq('5', 'Expected actionSub4Level value to be equals to 5');
    expect(await mContentableCardUpdatePage.getPlusRateInput()).to.eq('5', 'Expected plusRate value to be equals to 5');
    await mContentableCardUpdatePage.save();
    expect(await mContentableCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mContentableCardComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MContentableCard', async () => {
    const nbButtonsBeforeDelete = await mContentableCardComponentsPage.countDeleteButtons();
    await mContentableCardComponentsPage.clickOnLastDeleteButton();

    mContentableCardDeleteDialog = new MContentableCardDeleteDialog();
    expect(await mContentableCardDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Contentable Card?');
    await mContentableCardDeleteDialog.clickOnConfirmButton();

    expect(await mContentableCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
