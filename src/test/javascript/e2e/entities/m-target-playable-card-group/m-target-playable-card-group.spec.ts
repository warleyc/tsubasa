/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  MTargetPlayableCardGroupComponentsPage,
  MTargetPlayableCardGroupDeleteDialog,
  MTargetPlayableCardGroupUpdatePage
} from './m-target-playable-card-group.page-object';

const expect = chai.expect;

describe('MTargetPlayableCardGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mTargetPlayableCardGroupUpdatePage: MTargetPlayableCardGroupUpdatePage;
  let mTargetPlayableCardGroupComponentsPage: MTargetPlayableCardGroupComponentsPage;
  /*let mTargetPlayableCardGroupDeleteDialog: MTargetPlayableCardGroupDeleteDialog;*/

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MTargetPlayableCardGroups', async () => {
    await navBarPage.goToEntity('m-target-playable-card-group');
    mTargetPlayableCardGroupComponentsPage = new MTargetPlayableCardGroupComponentsPage();
    await browser.wait(ec.visibilityOf(mTargetPlayableCardGroupComponentsPage.title), 5000);
    expect(await mTargetPlayableCardGroupComponentsPage.getTitle()).to.eq('M Target Playable Card Groups');
  });

  it('should load create MTargetPlayableCardGroup page', async () => {
    await mTargetPlayableCardGroupComponentsPage.clickOnCreateButton();
    mTargetPlayableCardGroupUpdatePage = new MTargetPlayableCardGroupUpdatePage();
    expect(await mTargetPlayableCardGroupUpdatePage.getPageTitle()).to.eq('Create or edit a M Target Playable Card Group');
    await mTargetPlayableCardGroupUpdatePage.cancel();
  });

  /* it('should create and save MTargetPlayableCardGroups', async () => {
        const nbButtonsBeforeCreate = await mTargetPlayableCardGroupComponentsPage.countDeleteButtons();

        await mTargetPlayableCardGroupComponentsPage.clickOnCreateButton();
        await promise.all([
            mTargetPlayableCardGroupUpdatePage.setGroupIdInput('5'),
            mTargetPlayableCardGroupUpdatePage.setCardIdInput('5'),
            mTargetPlayableCardGroupUpdatePage.setIsShowThumbnailInput('5'),
            mTargetPlayableCardGroupUpdatePage.mplayablecardSelectLastOption(),
        ]);
        expect(await mTargetPlayableCardGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
        expect(await mTargetPlayableCardGroupUpdatePage.getCardIdInput()).to.eq('5', 'Expected cardId value to be equals to 5');
        expect(await mTargetPlayableCardGroupUpdatePage.getIsShowThumbnailInput()).to.eq('5', 'Expected isShowThumbnail value to be equals to 5');
        await mTargetPlayableCardGroupUpdatePage.save();
        expect(await mTargetPlayableCardGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await mTargetPlayableCardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    });*/

  /* it('should delete last MTargetPlayableCardGroup', async () => {
        const nbButtonsBeforeDelete = await mTargetPlayableCardGroupComponentsPage.countDeleteButtons();
        await mTargetPlayableCardGroupComponentsPage.clickOnLastDeleteButton();

        mTargetPlayableCardGroupDeleteDialog = new MTargetPlayableCardGroupDeleteDialog();
        expect(await mTargetPlayableCardGroupDeleteDialog.getDialogTitle())
            .to.eq('Are you sure you want to delete this M Target Playable Card Group?');
        await mTargetPlayableCardGroupDeleteDialog.clickOnConfirmButton();

        expect(await mTargetPlayableCardGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
