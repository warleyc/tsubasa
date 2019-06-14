import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestTsubasaPointRewardComponent,
  MQuestTsubasaPointRewardDetailComponent,
  MQuestTsubasaPointRewardUpdateComponent,
  MQuestTsubasaPointRewardDeletePopupComponent,
  MQuestTsubasaPointRewardDeleteDialogComponent,
  mQuestTsubasaPointRewardRoute,
  mQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mQuestTsubasaPointRewardRoute, ...mQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestTsubasaPointRewardComponent,
    MQuestTsubasaPointRewardDetailComponent,
    MQuestTsubasaPointRewardUpdateComponent,
    MQuestTsubasaPointRewardDeleteDialogComponent,
    MQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MQuestTsubasaPointRewardComponent,
    MQuestTsubasaPointRewardUpdateComponent,
    MQuestTsubasaPointRewardDeleteDialogComponent,
    MQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestTsubasaPointRewardModule {}
