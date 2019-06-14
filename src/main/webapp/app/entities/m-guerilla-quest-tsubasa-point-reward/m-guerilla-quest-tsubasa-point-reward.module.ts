import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuerillaQuestTsubasaPointRewardComponent,
  MGuerillaQuestTsubasaPointRewardDetailComponent,
  MGuerillaQuestTsubasaPointRewardUpdateComponent,
  MGuerillaQuestTsubasaPointRewardDeletePopupComponent,
  MGuerillaQuestTsubasaPointRewardDeleteDialogComponent,
  mGuerillaQuestTsubasaPointRewardRoute,
  mGuerillaQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mGuerillaQuestTsubasaPointRewardRoute, ...mGuerillaQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuerillaQuestTsubasaPointRewardComponent,
    MGuerillaQuestTsubasaPointRewardDetailComponent,
    MGuerillaQuestTsubasaPointRewardUpdateComponent,
    MGuerillaQuestTsubasaPointRewardDeleteDialogComponent,
    MGuerillaQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MGuerillaQuestTsubasaPointRewardComponent,
    MGuerillaQuestTsubasaPointRewardUpdateComponent,
    MGuerillaQuestTsubasaPointRewardDeleteDialogComponent,
    MGuerillaQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuerillaQuestTsubasaPointRewardModule {}
