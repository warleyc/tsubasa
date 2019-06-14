import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestStageConditionComponent,
  MQuestStageConditionDetailComponent,
  MQuestStageConditionUpdateComponent,
  MQuestStageConditionDeletePopupComponent,
  MQuestStageConditionDeleteDialogComponent,
  mQuestStageConditionRoute,
  mQuestStageConditionPopupRoute
} from './';

const ENTITY_STATES = [...mQuestStageConditionRoute, ...mQuestStageConditionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestStageConditionComponent,
    MQuestStageConditionDetailComponent,
    MQuestStageConditionUpdateComponent,
    MQuestStageConditionDeleteDialogComponent,
    MQuestStageConditionDeletePopupComponent
  ],
  entryComponents: [
    MQuestStageConditionComponent,
    MQuestStageConditionUpdateComponent,
    MQuestStageConditionDeleteDialogComponent,
    MQuestStageConditionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestStageConditionModule {}
