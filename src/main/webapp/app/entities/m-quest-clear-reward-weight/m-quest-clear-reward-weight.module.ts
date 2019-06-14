import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestClearRewardWeightComponent,
  MQuestClearRewardWeightDetailComponent,
  MQuestClearRewardWeightUpdateComponent,
  MQuestClearRewardWeightDeletePopupComponent,
  MQuestClearRewardWeightDeleteDialogComponent,
  mQuestClearRewardWeightRoute,
  mQuestClearRewardWeightPopupRoute
} from './';

const ENTITY_STATES = [...mQuestClearRewardWeightRoute, ...mQuestClearRewardWeightPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestClearRewardWeightComponent,
    MQuestClearRewardWeightDetailComponent,
    MQuestClearRewardWeightUpdateComponent,
    MQuestClearRewardWeightDeleteDialogComponent,
    MQuestClearRewardWeightDeletePopupComponent
  ],
  entryComponents: [
    MQuestClearRewardWeightComponent,
    MQuestClearRewardWeightUpdateComponent,
    MQuestClearRewardWeightDeleteDialogComponent,
    MQuestClearRewardWeightDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestClearRewardWeightModule {}
