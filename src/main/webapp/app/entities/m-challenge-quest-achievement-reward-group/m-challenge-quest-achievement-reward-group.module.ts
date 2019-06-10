import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChallengeQuestAchievementRewardGroupComponent,
  MChallengeQuestAchievementRewardGroupDetailComponent,
  MChallengeQuestAchievementRewardGroupUpdateComponent,
  MChallengeQuestAchievementRewardGroupDeletePopupComponent,
  MChallengeQuestAchievementRewardGroupDeleteDialogComponent,
  mChallengeQuestAchievementRewardGroupRoute,
  mChallengeQuestAchievementRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mChallengeQuestAchievementRewardGroupRoute, ...mChallengeQuestAchievementRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChallengeQuestAchievementRewardGroupComponent,
    MChallengeQuestAchievementRewardGroupDetailComponent,
    MChallengeQuestAchievementRewardGroupUpdateComponent,
    MChallengeQuestAchievementRewardGroupDeleteDialogComponent,
    MChallengeQuestAchievementRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MChallengeQuestAchievementRewardGroupComponent,
    MChallengeQuestAchievementRewardGroupUpdateComponent,
    MChallengeQuestAchievementRewardGroupDeleteDialogComponent,
    MChallengeQuestAchievementRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChallengeQuestAchievementRewardGroupModule {}
