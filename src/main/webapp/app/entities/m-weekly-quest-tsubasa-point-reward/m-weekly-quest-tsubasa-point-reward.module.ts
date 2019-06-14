import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MWeeklyQuestTsubasaPointRewardComponent,
  MWeeklyQuestTsubasaPointRewardDetailComponent,
  MWeeklyQuestTsubasaPointRewardUpdateComponent,
  MWeeklyQuestTsubasaPointRewardDeletePopupComponent,
  MWeeklyQuestTsubasaPointRewardDeleteDialogComponent,
  mWeeklyQuestTsubasaPointRewardRoute,
  mWeeklyQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mWeeklyQuestTsubasaPointRewardRoute, ...mWeeklyQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MWeeklyQuestTsubasaPointRewardComponent,
    MWeeklyQuestTsubasaPointRewardDetailComponent,
    MWeeklyQuestTsubasaPointRewardUpdateComponent,
    MWeeklyQuestTsubasaPointRewardDeleteDialogComponent,
    MWeeklyQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MWeeklyQuestTsubasaPointRewardComponent,
    MWeeklyQuestTsubasaPointRewardUpdateComponent,
    MWeeklyQuestTsubasaPointRewardDeleteDialogComponent,
    MWeeklyQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMWeeklyQuestTsubasaPointRewardModule {}
