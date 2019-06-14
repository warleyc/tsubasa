import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MWeeklyQuestStageComponent,
  MWeeklyQuestStageDetailComponent,
  MWeeklyQuestStageUpdateComponent,
  MWeeklyQuestStageDeletePopupComponent,
  MWeeklyQuestStageDeleteDialogComponent,
  mWeeklyQuestStageRoute,
  mWeeklyQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mWeeklyQuestStageRoute, ...mWeeklyQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MWeeklyQuestStageComponent,
    MWeeklyQuestStageDetailComponent,
    MWeeklyQuestStageUpdateComponent,
    MWeeklyQuestStageDeleteDialogComponent,
    MWeeklyQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MWeeklyQuestStageComponent,
    MWeeklyQuestStageUpdateComponent,
    MWeeklyQuestStageDeleteDialogComponent,
    MWeeklyQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMWeeklyQuestStageModule {}
