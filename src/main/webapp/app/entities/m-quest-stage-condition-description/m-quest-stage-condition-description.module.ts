import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestStageConditionDescriptionComponent,
  MQuestStageConditionDescriptionDetailComponent,
  MQuestStageConditionDescriptionUpdateComponent,
  MQuestStageConditionDescriptionDeletePopupComponent,
  MQuestStageConditionDescriptionDeleteDialogComponent,
  mQuestStageConditionDescriptionRoute,
  mQuestStageConditionDescriptionPopupRoute
} from './';

const ENTITY_STATES = [...mQuestStageConditionDescriptionRoute, ...mQuestStageConditionDescriptionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestStageConditionDescriptionComponent,
    MQuestStageConditionDescriptionDetailComponent,
    MQuestStageConditionDescriptionUpdateComponent,
    MQuestStageConditionDescriptionDeleteDialogComponent,
    MQuestStageConditionDescriptionDeletePopupComponent
  ],
  entryComponents: [
    MQuestStageConditionDescriptionComponent,
    MQuestStageConditionDescriptionUpdateComponent,
    MQuestStageConditionDescriptionDeleteDialogComponent,
    MQuestStageConditionDescriptionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestStageConditionDescriptionModule {}
