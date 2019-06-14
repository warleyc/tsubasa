import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuerillaQuestStageComponent,
  MGuerillaQuestStageDetailComponent,
  MGuerillaQuestStageUpdateComponent,
  MGuerillaQuestStageDeletePopupComponent,
  MGuerillaQuestStageDeleteDialogComponent,
  mGuerillaQuestStageRoute,
  mGuerillaQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mGuerillaQuestStageRoute, ...mGuerillaQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuerillaQuestStageComponent,
    MGuerillaQuestStageDetailComponent,
    MGuerillaQuestStageUpdateComponent,
    MGuerillaQuestStageDeleteDialogComponent,
    MGuerillaQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MGuerillaQuestStageComponent,
    MGuerillaQuestStageUpdateComponent,
    MGuerillaQuestStageDeleteDialogComponent,
    MGuerillaQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuerillaQuestStageModule {}
