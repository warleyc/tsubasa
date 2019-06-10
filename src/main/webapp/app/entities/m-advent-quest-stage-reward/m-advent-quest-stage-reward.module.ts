import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAdventQuestStageRewardComponent,
  MAdventQuestStageRewardDetailComponent,
  MAdventQuestStageRewardUpdateComponent,
  MAdventQuestStageRewardDeletePopupComponent,
  MAdventQuestStageRewardDeleteDialogComponent,
  mAdventQuestStageRewardRoute,
  mAdventQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mAdventQuestStageRewardRoute, ...mAdventQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAdventQuestStageRewardComponent,
    MAdventQuestStageRewardDetailComponent,
    MAdventQuestStageRewardUpdateComponent,
    MAdventQuestStageRewardDeleteDialogComponent,
    MAdventQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MAdventQuestStageRewardComponent,
    MAdventQuestStageRewardUpdateComponent,
    MAdventQuestStageRewardDeleteDialogComponent,
    MAdventQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAdventQuestStageRewardModule {}
