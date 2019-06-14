import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLuckWeeklyQuestWorldComponent,
  MLuckWeeklyQuestWorldDetailComponent,
  MLuckWeeklyQuestWorldUpdateComponent,
  MLuckWeeklyQuestWorldDeletePopupComponent,
  MLuckWeeklyQuestWorldDeleteDialogComponent,
  mLuckWeeklyQuestWorldRoute,
  mLuckWeeklyQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mLuckWeeklyQuestWorldRoute, ...mLuckWeeklyQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLuckWeeklyQuestWorldComponent,
    MLuckWeeklyQuestWorldDetailComponent,
    MLuckWeeklyQuestWorldUpdateComponent,
    MLuckWeeklyQuestWorldDeleteDialogComponent,
    MLuckWeeklyQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MLuckWeeklyQuestWorldComponent,
    MLuckWeeklyQuestWorldUpdateComponent,
    MLuckWeeklyQuestWorldDeleteDialogComponent,
    MLuckWeeklyQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLuckWeeklyQuestWorldModule {}
