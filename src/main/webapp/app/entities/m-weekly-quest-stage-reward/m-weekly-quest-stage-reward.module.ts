import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MWeeklyQuestStageRewardComponent,
  MWeeklyQuestStageRewardDetailComponent,
  MWeeklyQuestStageRewardUpdateComponent,
  MWeeklyQuestStageRewardDeletePopupComponent,
  MWeeklyQuestStageRewardDeleteDialogComponent,
  mWeeklyQuestStageRewardRoute,
  mWeeklyQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mWeeklyQuestStageRewardRoute, ...mWeeklyQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MWeeklyQuestStageRewardComponent,
    MWeeklyQuestStageRewardDetailComponent,
    MWeeklyQuestStageRewardUpdateComponent,
    MWeeklyQuestStageRewardDeleteDialogComponent,
    MWeeklyQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MWeeklyQuestStageRewardComponent,
    MWeeklyQuestStageRewardUpdateComponent,
    MWeeklyQuestStageRewardDeleteDialogComponent,
    MWeeklyQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMWeeklyQuestStageRewardModule {}
