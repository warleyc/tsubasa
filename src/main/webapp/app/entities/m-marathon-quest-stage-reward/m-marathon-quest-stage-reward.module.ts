import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonQuestStageRewardComponent,
  MMarathonQuestStageRewardDetailComponent,
  MMarathonQuestStageRewardUpdateComponent,
  MMarathonQuestStageRewardDeletePopupComponent,
  MMarathonQuestStageRewardDeleteDialogComponent,
  mMarathonQuestStageRewardRoute,
  mMarathonQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonQuestStageRewardRoute, ...mMarathonQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonQuestStageRewardComponent,
    MMarathonQuestStageRewardDetailComponent,
    MMarathonQuestStageRewardUpdateComponent,
    MMarathonQuestStageRewardDeleteDialogComponent,
    MMarathonQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonQuestStageRewardComponent,
    MMarathonQuestStageRewardUpdateComponent,
    MMarathonQuestStageRewardDeleteDialogComponent,
    MMarathonQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonQuestStageRewardModule {}
