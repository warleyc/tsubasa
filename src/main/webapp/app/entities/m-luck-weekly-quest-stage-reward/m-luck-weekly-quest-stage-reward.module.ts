import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLuckWeeklyQuestStageRewardComponent,
  MLuckWeeklyQuestStageRewardDetailComponent,
  MLuckWeeklyQuestStageRewardUpdateComponent,
  MLuckWeeklyQuestStageRewardDeletePopupComponent,
  MLuckWeeklyQuestStageRewardDeleteDialogComponent,
  mLuckWeeklyQuestStageRewardRoute,
  mLuckWeeklyQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mLuckWeeklyQuestStageRewardRoute, ...mLuckWeeklyQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLuckWeeklyQuestStageRewardComponent,
    MLuckWeeklyQuestStageRewardDetailComponent,
    MLuckWeeklyQuestStageRewardUpdateComponent,
    MLuckWeeklyQuestStageRewardDeleteDialogComponent,
    MLuckWeeklyQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MLuckWeeklyQuestStageRewardComponent,
    MLuckWeeklyQuestStageRewardUpdateComponent,
    MLuckWeeklyQuestStageRewardDeleteDialogComponent,
    MLuckWeeklyQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLuckWeeklyQuestStageRewardModule {}
