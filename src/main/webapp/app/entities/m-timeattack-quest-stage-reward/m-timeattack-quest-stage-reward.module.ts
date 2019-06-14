import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTimeattackQuestStageRewardComponent,
  MTimeattackQuestStageRewardDetailComponent,
  MTimeattackQuestStageRewardUpdateComponent,
  MTimeattackQuestStageRewardDeletePopupComponent,
  MTimeattackQuestStageRewardDeleteDialogComponent,
  mTimeattackQuestStageRewardRoute,
  mTimeattackQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mTimeattackQuestStageRewardRoute, ...mTimeattackQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTimeattackQuestStageRewardComponent,
    MTimeattackQuestStageRewardDetailComponent,
    MTimeattackQuestStageRewardUpdateComponent,
    MTimeattackQuestStageRewardDeleteDialogComponent,
    MTimeattackQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MTimeattackQuestStageRewardComponent,
    MTimeattackQuestStageRewardUpdateComponent,
    MTimeattackQuestStageRewardDeleteDialogComponent,
    MTimeattackQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTimeattackQuestStageRewardModule {}
