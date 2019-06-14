import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuerillaQuestStageRewardComponent,
  MGuerillaQuestStageRewardDetailComponent,
  MGuerillaQuestStageRewardUpdateComponent,
  MGuerillaQuestStageRewardDeletePopupComponent,
  MGuerillaQuestStageRewardDeleteDialogComponent,
  mGuerillaQuestStageRewardRoute,
  mGuerillaQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mGuerillaQuestStageRewardRoute, ...mGuerillaQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuerillaQuestStageRewardComponent,
    MGuerillaQuestStageRewardDetailComponent,
    MGuerillaQuestStageRewardUpdateComponent,
    MGuerillaQuestStageRewardDeleteDialogComponent,
    MGuerillaQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MGuerillaQuestStageRewardComponent,
    MGuerillaQuestStageRewardUpdateComponent,
    MGuerillaQuestStageRewardDeleteDialogComponent,
    MGuerillaQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuerillaQuestStageRewardModule {}
