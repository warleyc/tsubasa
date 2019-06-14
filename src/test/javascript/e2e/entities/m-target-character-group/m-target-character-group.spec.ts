/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetCharacterGroupComponentsPage,
  MTargetCharacterGroupDeleteDialog,
  MTargetCharacterGroupUpdatePage
} from './m-target-character-group.page-object';

const expect = chai.expect;

describe('MTargetCharacterGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetCharacterGroupUpdatePage: MTargetCharacterGroupUpdatePage;
  let mTargetCharacterGroupComponentsPage: MTargetCharacterGroupComponentsPage;
  /*let mTargetCharacterGroupDeleteDialog: MTargetCharacterGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetCharacterGroups', async () => {
    await navBarPage.goToEntity('m-target-character-group');
    mTargetCharacterGroupComponentsPage = new MTargetCharacterGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetCharacterGroupComponentsPage.title), 5000);
    expect(await mTargetCharacterGroupComponentsPage.getTitle()).to.eq('M Target Character Groups');
  });

  it('should load create MTargetCharacterGroup page', async () => {
    await mTargetCharacterGroupComponentsPage.clickOnCreateButton();
    mTargetCharacterGroupUpdatePage = new MTargetCharacterGroupUpdatePage();
    expect(await mTargetCharacterGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Character Group');
    await mTargetCharacterGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetCharacterGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetCharacterGroupComponentsPage.countDeleteButtons();

        await mTargetCharacterGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetCharacterGroupUpdatePage.setGroupIdInput('5'),
            mTargetCharacterGroupUpdatePage.setCharacterIdInput('5'),
            mTargetCharacterGroupUpdatePage.mcharacterSelectLastOption(),
        ]);
        expect(await mTargetCharacterGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetCharacterGroupUpdatePage.getCharacterIdInput()).to.eq('5', 'Expected characterId value to be equals to 5');
        await mTargetCharacterGroupUpdatePage.save();
        expect(await mTargetCharacterGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetCharacterGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetCharacterGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetCharacterGroupComponentsPage.countDeleteButtons();
        await mTargetCharacterGroupComponentsPage.clickOnLastDeleteButton();

        mTargetCharacterGroupDeleteDialog = new MTargetCharacterGroupDeleteDialog();
        expect(await mTargetCharacterGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Character Group?');
        await mTargetCharacterGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetCharacterGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
