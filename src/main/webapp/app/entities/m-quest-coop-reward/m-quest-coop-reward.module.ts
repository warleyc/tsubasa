import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestCoopRewardComponent,
  MQuestCoopRewardDetailComponent,
  MQuestCoopRewardUpdateComponent,
  MQuestCoopRewardDeletePopupComponent,
  MQuestCoopRewardDeleteDialogComponent,
  mQuestCoopRewardRoute,
  mQuestCoopRewardPopupRoute
} from './';

const ENTITY_STATES = [...mQuestCoopRewardRoute, ...mQuestCoopRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestCoopRewardComponent,
    MQuestCoopRewardDetailComponent,
    MQuestCoopRewardUpdateComponent,
    MQuestCoopRewardDeleteDialogComponent,
    MQuestCoopRewardDeletePopupComponent
  ],
  entryComponents: [
    MQuestCoopRewardComponent,
    MQuestCoopRewardUpdateComponent,
    MQuestCoopRewardDeleteDialogComponent,
    MQuestCoopRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestCoopRewardModule {}
