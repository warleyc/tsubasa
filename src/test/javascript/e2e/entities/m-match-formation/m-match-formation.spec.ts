/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MMatchFormationComponentsPage, MMatchFormationDeleteDialog, MMatchFormationUpdatePage } from './m-match-formation.page-object';

const expect = chai.expect;

describe('MMatchFormation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mMatchFormationUpdatePage: MMatchFormationUpdatePage;
  let mMatchFormationComponentsPage: MMatchFormationComponentsPage;
  let mMatchFormationDeleteDialog: MMatchFormationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MMatchFormations', async () => {
    await navBarPage.goToEntity('m-match-formation');
    mMatchFormationComponentsPage = new MMatchFormationComponentsPage();
    await browser.wait(ec.visibilityOf(mMatchFormationComponentsPage.title), 5000);
    expect(await mMatchFormationComponentsPage.getTitle()).to.eq('M Match Formations');
  });

  it('should load create MMatchFormation page', async () => {
    await mMatchFormationComponentsPage.clickOnCreateButton();
    mMatchFormationUpdatePage = new MMatchFormationUpdatePage();
    expect(await mMatchFormationUpdatePage.getPageTitle()).to.eq('Create or edit a M Match Formation');
    await mMatchFormationUpdatePage.cancel();
  });

  it('should create and save MMatchFormations', async () => {
    const nbButtonsBeforeCreate = await mMatchFormationComponentsPage.countDeleteButtons();

    await mMatchFormationComponentsPage.clickOnCreateButton();
    await promise.all([
      mMatchFormationUpdatePage.setPosition1Input('5'),
      mMatchFormationUpdatePage.setPosition2Input('5'),
      mMatchFormationUpdatePage.setPosition3Input('5'),
      mMatchFormationUpdatePage.setPosition4Input('5'),
      mMatchFormationUpdatePage.setPosition5Input('5'),
      mMatchFormationUpdatePage.setPosition6Input('5'),
      mMatchFormationUpdatePage.setPosition7Input('5'),
      mMatchFormationUpdatePage.setPosition8Input('5'),
      mMatchFormationUpdatePage.setPosition9Input('5'),
      mMatchFormationUpdatePage.setPosition10Input('5'),
      mMatchFormationUpdatePage.setPosition11Input('5')
    ]);
    expect(await mMatchFormationUpdatePage.getPosition1Input()).to.eq('5', 'Expected position1 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition2Input()).to.eq('5', 'Expected position2 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition3Input()).to.eq('5', 'Expected position3 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition4Input()).to.eq('5', 'Expected position4 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition5Input()).to.eq('5', 'Expected position5 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition6Input()).to.eq('5', 'Expected position6 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition7Input()).to.eq('5', 'Expected position7 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition8Input()).to.eq('5', 'Expected position8 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition9Input()).to.eq('5', 'Expected position9 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition10Input()).to.eq('5', 'Expected position10 value to be equals to 5');
    expect(await mMatchFormationUpdatePage.getPosition11Input()).to.eq('5', 'Expected position11 value to be equals to 5');
    await mMatchFormationUpdatePage.save();
    expect(await mMatchFormationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mMatchFormationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MMatchFormation', async () => {
    const nbButtonsBeforeDelete = await mMatchFormationComponentsPage.countDeleteButtons();
    await mMatchFormationComponentsPage.clickOnLastDeleteButton();

    mMatchFormationDeleteDialog = new MMatchFormationDeleteDialog();
    expect(await mMatchFormationDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this M Match Formation?');
    await mMatchFormationDeleteDialog.clickOnConfirmButton();

    expect(await mMatchFormationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
