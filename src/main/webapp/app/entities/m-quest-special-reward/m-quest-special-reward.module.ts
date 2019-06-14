import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestSpecialRewardComponent,
  MQuestSpecialRewardDetailComponent,
  MQuestSpecialRewardUpdateComponent,
  MQuestSpecialRewardDeletePopupComponent,
  MQuestSpecialRewardDeleteDialogComponent,
  mQuestSpecialRewardRoute,
  mQuestSpecialRewardPopupRoute
} from './';

const ENTITY_STATES = [...mQuestSpecialRewardRoute, ...mQuestSpecialRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestSpecialRewardComponent,
    MQuestSpecialRewardDetailComponent,
    MQuestSpecialRewardUpdateComponent,
    MQuestSpecialRewardDeleteDialogComponent,
    MQuestSpecialRewardDeletePopupComponent
  ],
  entryComponents: [
    MQuestSpecialRewardComponent,
    MQuestSpecialRewardUpdateComponent,
    MQuestSpecialRewardDeleteDialogComponent,
    MQuestSpecialRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestSpecialRewardModule {}
