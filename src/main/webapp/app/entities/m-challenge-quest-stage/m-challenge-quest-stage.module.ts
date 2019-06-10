import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChallengeQuestStageComponent,
  MChallengeQuestStageDetailComponent,
  MChallengeQuestStageUpdateComponent,
  MChallengeQuestStageDeletePopupComponent,
  MChallengeQuestStageDeleteDialogComponent,
  mChallengeQuestStageRoute,
  mChallengeQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mChallengeQuestStageRoute, ...mChallengeQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChallengeQuestStageComponent,
    MChallengeQuestStageDetailComponent,
    MChallengeQuestStageUpdateComponent,
    MChallengeQuestStageDeleteDialogComponent,
    MChallengeQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MChallengeQuestStageComponent,
    MChallengeQuestStageUpdateComponent,
    MChallengeQuestStageDeleteDialogComponent,
    MChallengeQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChallengeQuestStageModule {}
