import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAdventQuestTsubasaPointRewardComponent,
  MAdventQuestTsubasaPointRewardDetailComponent,
  MAdventQuestTsubasaPointRewardUpdateComponent,
  MAdventQuestTsubasaPointRewardDeletePopupComponent,
  MAdventQuestTsubasaPointRewardDeleteDialogComponent,
  mAdventQuestTsubasaPointRewardRoute,
  mAdventQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mAdventQuestTsubasaPointRewardRoute, ...mAdventQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAdventQuestTsubasaPointRewardComponent,
    MAdventQuestTsubasaPointRewardDetailComponent,
    MAdventQuestTsubasaPointRewardUpdateComponent,
    MAdventQuestTsubasaPointRewardDeleteDialogComponent,
    MAdventQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MAdventQuestTsubasaPointRewardComponent,
    MAdventQuestTsubasaPointRewardUpdateComponent,
    MAdventQuestTsubasaPointRewardDeleteDialogComponent,
    MAdventQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAdventQuestTsubasaPointRewardModule {}
