import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChallengeQuestStageRewardComponent,
  MChallengeQuestStageRewardDetailComponent,
  MChallengeQuestStageRewardUpdateComponent,
  MChallengeQuestStageRewardDeletePopupComponent,
  MChallengeQuestStageRewardDeleteDialogComponent,
  mChallengeQuestStageRewardRoute,
  mChallengeQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mChallengeQuestStageRewardRoute, ...mChallengeQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChallengeQuestStageRewardComponent,
    MChallengeQuestStageRewardDetailComponent,
    MChallengeQuestStageRewardUpdateComponent,
    MChallengeQuestStageRewardDeleteDialogComponent,
    MChallengeQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MChallengeQuestStageRewardComponent,
    MChallengeQuestStageRewardUpdateComponent,
    MChallengeQuestStageRewardDeleteDialogComponent,
    MChallengeQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChallengeQuestStageRewardModule {}
