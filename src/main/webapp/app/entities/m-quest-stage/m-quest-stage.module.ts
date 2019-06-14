import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestStageComponent,
  MQuestStageDetailComponent,
  MQuestStageUpdateComponent,
  MQuestStageDeletePopupComponent,
  MQuestStageDeleteDialogComponent,
  mQuestStageRoute,
  mQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mQuestStageRoute, ...mQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestStageComponent,
    MQuestStageDetailComponent,
    MQuestStageUpdateComponent,
    MQuestStageDeleteDialogComponent,
    MQuestStageDeletePopupComponent
  ],
  entryComponents: [MQuestStageComponent, MQuestStageUpdateComponent, MQuestStageDeleteDialogComponent, MQuestStageDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestStageModule {}
