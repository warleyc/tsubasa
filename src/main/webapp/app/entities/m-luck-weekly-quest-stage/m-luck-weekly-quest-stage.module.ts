import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLuckWeeklyQuestStageComponent,
  MLuckWeeklyQuestStageDetailComponent,
  MLuckWeeklyQuestStageUpdateComponent,
  MLuckWeeklyQuestStageDeletePopupComponent,
  MLuckWeeklyQuestStageDeleteDialogComponent,
  mLuckWeeklyQuestStageRoute,
  mLuckWeeklyQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mLuckWeeklyQuestStageRoute, ...mLuckWeeklyQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLuckWeeklyQuestStageComponent,
    MLuckWeeklyQuestStageDetailComponent,
    MLuckWeeklyQuestStageUpdateComponent,
    MLuckWeeklyQuestStageDeleteDialogComponent,
    MLuckWeeklyQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MLuckWeeklyQuestStageComponent,
    MLuckWeeklyQuestStageUpdateComponent,
    MLuckWeeklyQuestStageDeleteDialogComponent,
    MLuckWeeklyQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLuckWeeklyQuestStageModule {}
