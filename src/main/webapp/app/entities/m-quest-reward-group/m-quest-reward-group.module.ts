import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestRewardGroupComponent,
  MQuestRewardGroupDetailComponent,
  MQuestRewardGroupUpdateComponent,
  MQuestRewardGroupDeletePopupComponent,
  MQuestRewardGroupDeleteDialogComponent,
  mQuestRewardGroupRoute,
  mQuestRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mQuestRewardGroupRoute, ...mQuestRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestRewardGroupComponent,
    MQuestRewardGroupDetailComponent,
    MQuestRewardGroupUpdateComponent,
    MQuestRewardGroupDeleteDialogComponent,
    MQuestRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MQuestRewardGroupComponent,
    MQuestRewardGroupUpdateComponent,
    MQuestRewardGroupDeleteDialogComponent,
    MQuestRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestRewardGroupModule {}
