import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonQuestTsubasaPointRewardComponent,
  MMarathonQuestTsubasaPointRewardDetailComponent,
  MMarathonQuestTsubasaPointRewardUpdateComponent,
  MMarathonQuestTsubasaPointRewardDeletePopupComponent,
  MMarathonQuestTsubasaPointRewardDeleteDialogComponent,
  mMarathonQuestTsubasaPointRewardRoute,
  mMarathonQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonQuestTsubasaPointRewardRoute, ...mMarathonQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonQuestTsubasaPointRewardComponent,
    MMarathonQuestTsubasaPointRewardDetailComponent,
    MMarathonQuestTsubasaPointRewardUpdateComponent,
    MMarathonQuestTsubasaPointRewardDeleteDialogComponent,
    MMarathonQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonQuestTsubasaPointRewardComponent,
    MMarathonQuestTsubasaPointRewardUpdateComponent,
    MMarathonQuestTsubasaPointRewardDeleteDialogComponent,
    MMarathonQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonQuestTsubasaPointRewardModule {}
