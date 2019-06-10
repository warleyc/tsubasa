import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChallengeQuestAchievementRewardComponent,
  MChallengeQuestAchievementRewardDetailComponent,
  MChallengeQuestAchievementRewardUpdateComponent,
  MChallengeQuestAchievementRewardDeletePopupComponent,
  MChallengeQuestAchievementRewardDeleteDialogComponent,
  mChallengeQuestAchievementRewardRoute,
  mChallengeQuestAchievementRewardPopupRoute
} from './';

const ENTITY_STATES = [...mChallengeQuestAchievementRewardRoute, ...mChallengeQuestAchievementRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChallengeQuestAchievementRewardComponent,
    MChallengeQuestAchievementRewardDetailComponent,
    MChallengeQuestAchievementRewardUpdateComponent,
    MChallengeQuestAchievementRewardDeleteDialogComponent,
    MChallengeQuestAchievementRewardDeletePopupComponent
  ],
  entryComponents: [
    MChallengeQuestAchievementRewardComponent,
    MChallengeQuestAchievementRewardUpdateComponent,
    MChallengeQuestAchievementRewardDeleteDialogComponent,
    MChallengeQuestAchievementRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChallengeQuestAchievementRewardModule {}
