import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestGoalRewardComponent,
  MQuestGoalRewardDetailComponent,
  MQuestGoalRewardUpdateComponent,
  MQuestGoalRewardDeletePopupComponent,
  MQuestGoalRewardDeleteDialogComponent,
  mQuestGoalRewardRoute,
  mQuestGoalRewardPopupRoute
} from './';

const ENTITY_STATES = [...mQuestGoalRewardRoute, ...mQuestGoalRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestGoalRewardComponent,
    MQuestGoalRewardDetailComponent,
    MQuestGoalRewardUpdateComponent,
    MQuestGoalRewardDeleteDialogComponent,
    MQuestGoalRewardDeletePopupComponent
  ],
  entryComponents: [
    MQuestGoalRewardComponent,
    MQuestGoalRewardUpdateComponent,
    MQuestGoalRewardDeleteDialogComponent,
    MQuestGoalRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestGoalRewardModule {}
