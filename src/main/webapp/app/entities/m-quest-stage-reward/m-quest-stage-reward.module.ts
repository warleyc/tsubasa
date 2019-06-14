import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestStageRewardComponent,
  MQuestStageRewardDetailComponent,
  MQuestStageRewardUpdateComponent,
  MQuestStageRewardDeletePopupComponent,
  MQuestStageRewardDeleteDialogComponent,
  mQuestStageRewardRoute,
  mQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mQuestStageRewardRoute, ...mQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestStageRewardComponent,
    MQuestStageRewardDetailComponent,
    MQuestStageRewardUpdateComponent,
    MQuestStageRewardDeleteDialogComponent,
    MQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MQuestStageRewardComponent,
    MQuestStageRewardUpdateComponent,
    MQuestStageRewardDeleteDialogComponent,
    MQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestStageRewardModule {}
