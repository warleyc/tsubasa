/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MCharacterBookComponentsPage, MCharacterBookDeleteDialog, MCharacterBookUpdatePage } from './m-character-book.page-object';

const expect = chai.expect;

describe('MCharacterBook e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mCharacterBookUpdatePage: MCharacterBookUpdatePage;
  let mCharacterBookComponentsPage: MCharacterBookComponentsPage;
  let mCharacterBookDeleteDialog: MCharacterBookDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MCharacterBooks', async () => {
    await navBarPage.goToEntity('m-character-book');
    mCharacterBookComponentsPage = new MCharacterBookComponentsPage();
    await browser.wait(ec.visibilityOf(mCharacterBookComponentsPage.title), 5000);
    expect(await mCharacterBookComponentsPage.getTitle()).to.eq('M Character Books');
  });

  it('should load create MCharacterBook page', async () => {
    await mCharacterBookComponentsPage.clickOnCreateButton();
    mCharacterBookUpdatePage = new MCharacterBookUpdatePage();
    expect(await mCharacterBookUpdatePage.getPageTitle()).to.eq('Create or edit a M Character Book');
    await mCharacterBookUpdatePage.cancel();
  });

  it('should create and save MCharacterBooks', async () => {
    const nbButtonsBeforeCreate = await mCharacterBookComponentsPage.countDeleteButtons();

    await mCharacterBookComponentsPage.clickOnCreateButton();
    await promise.all([
      mCharacterBookUpdatePage.setCvNameInput('cvName'),
      mCharacterBookUpdatePage.setSeriesInput('5'),
      mCharacterBookUpdatePage.setHeightInput('5'),
      mCharacterBookUpdatePage.setWeightInput('5'),
      mCharacterBookUpdatePage.setIntroductionInput('introduction'),
      mCharacterBookUpdatePage.setFirstAppearedComicInput('firstAppearedComic'),
      mCharacterBookUpdatePage.setFirstAppearedComicLinkInput('firstAppearedComicLink'),
      mCharacterBookUpdatePage.setSkill1Input('skill1'),
      mCharacterBookUpdatePage.setSkill1ComicInput('skill1Comic'),
      mCharacterBookUpdatePage.setSkill1ComicLinkInput('skill1ComicLink'),
      mCharacterBookUpdatePage.setSkill2Input('skill2'),
      mCharacterBookUpdatePage.setSkill2ComicInput('skill2Comic'),
      mCharacterBookUpdatePage.setSkill2ComicLinkInput('skill2ComicLink'),
      mCharacterBookUpdatePage.setSkill3Input('skill3'),
      mCharacterBookUpdatePage.setSkill3ComicInput('skill3Comic'),
      mCharacterBookUpdatePage.setSkill3ComicLinkInput('skill3ComicLink')
    ]);
    expect(await mCharacterBookUpdatePage.getCvNameInput()).to.eq('cvName', 'Expected CvName value to be equals to cvName');
    expect(await mCharacterBookUpdatePage.getSeriesInput()).to.eq('5', 'Expected series value to be equals to 5');
    expect(await mCharacterBookUpdatePage.getHeightInput()).to.eq('5', 'Expected height value to be equals to 5');
    expect(await mCharacterBookUpdatePage.getWeightInput()).to.eq('5', 'Expected weight value to be equals to 5');
    expect(await mCharacterBookUpdatePage.getIntroductionInput()).to.eq(
      'introduction',
      'Expected Introduction value to be equals to introduction'
    );
    expect(await mCharacterBookUpdatePage.getFirstAppearedComicInput()).to.eq(
      'firstAppearedComic',
      'Expected FirstAppearedComic value to be equals to firstAppearedComic'
    );
    expect(await mCharacterBookUpdatePage.getFirstAppearedComicLinkInput()).to.eq(
      'firstAppearedComicLink',
      'Expected FirstAppearedComicLink value to be equals to firstAppearedComicLink'
    );
    expect(await mCharacterBookUpdatePage.getSkill1Input()).to.eq('skill1', 'Expected Skill1 value to be equals to skill1');
    expect(await mCharacterBookUpdatePage.getSkill1ComicInput()).to.eq(
      'skill1Comic',
      'Expected Skill1Comic value to be equals to skill1Comic'
    );
    expect(await mCharacterBookUpdatePage.getSkill1ComicLinkInput()).to.eq(
      'skill1ComicLink',
      'Expected Skill1ComicLink value to be equals to skill1ComicLink'
    );
    expect(await mCharacterBookUpdatePage.getSkill2Input()).to.eq('skill2', 'Expected Skill2 value to be equals to skill2');
    expect(await mCharacterBookUpdatePage.getSkill2ComicInput()).to.eq(
      'skill2Comic',
      'Expected Skill2Comic value to be equals to skill2Comic'
    );
    expect(await mCharacterBookUpdatePage.getSkill2ComicLinkInput()).to.eq(
      'skill2ComicLink',
      'Expected Skill2ComicLink value to be equals to skill2ComicLink'
    );
    expect(await mCharacterBookUpdatePage.getSkill3Input()).to.eq('skill3', 'Expected Skill3 value to be equals to skill3');
    expect(await mCharacterBookUpdatePage.getSkill3ComicInput()).to.eq(
      'skill3Comic',
      'Expected Skill3Comic value to be equals to skill3Comic'
    );
    expect(await mCharacterBookUpdatePage.getSkill3ComicLinkInput()).to.eq(
      'skill3ComicLink',
      'Expected Skill3ComicLink value to be equals to skill3ComicLink'
    );
    await mCharacterBookUpdatePage.save();
    expect(await mCharacterBookUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mCharacterBookComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MCharacterBook', async () => {
    const nbButtonsBeforeDelete = await mCharacterBookComponentsPage.countDeleteButtons();
    await mCharacterBookComponentsPage.clickOnLastDeleteButton();

    mCharacterBookDeleteDialog = new MCharacterBookDeleteDialog();
    expect(await mCharacterBookDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Character Book?');
    await mCharacterBookDeleteDialog.clickOnConfirmButton();

    expect(await mCharacterBookComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
