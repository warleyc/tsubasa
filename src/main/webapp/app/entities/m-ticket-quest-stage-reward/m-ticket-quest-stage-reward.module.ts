import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTicketQuestStageRewardComponent,
  MTicketQuestStageRewardDetailComponent,
  MTicketQuestStageRewardUpdateComponent,
  MTicketQuestStageRewardDeletePopupComponent,
  MTicketQuestStageRewardDeleteDialogComponent,
  mTicketQuestStageRewardRoute,
  mTicketQuestStageRewardPopupRoute
} from './';

const ENTITY_STATES = [...mTicketQuestStageRewardRoute, ...mTicketQuestStageRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTicketQuestStageRewardComponent,
    MTicketQuestStageRewardDetailComponent,
    MTicketQuestStageRewardUpdateComponent,
    MTicketQuestStageRewardDeleteDialogComponent,
    MTicketQuestStageRewardDeletePopupComponent
  ],
  entryComponents: [
    MTicketQuestStageRewardComponent,
    MTicketQuestStageRewardUpdateComponent,
    MTicketQuestStageRewardDeleteDialogComponent,
    MTicketQuestStageRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTicketQuestStageRewardModule {}
