import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonQuestStageComponent,
  MMarathonQuestStageDetailComponent,
  MMarathonQuestStageUpdateComponent,
  MMarathonQuestStageDeletePopupComponent,
  MMarathonQuestStageDeleteDialogComponent,
  mMarathonQuestStageRoute,
  mMarathonQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mMarathonQuestStageRoute, ...mMarathonQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonQuestStageComponent,
    MMarathonQuestStageDetailComponent,
    MMarathonQuestStageUpdateComponent,
    MMarathonQuestStageDeleteDialogComponent,
    MMarathonQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MMarathonQuestStageComponent,
    MMarathonQuestStageUpdateComponent,
    MMarathonQuestStageDeleteDialogComponent,
    MMarathonQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonQuestStageModule {}
