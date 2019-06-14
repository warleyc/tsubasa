import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTicketQuestStageComponent,
  MTicketQuestStageDetailComponent,
  MTicketQuestStageUpdateComponent,
  MTicketQuestStageDeletePopupComponent,
  MTicketQuestStageDeleteDialogComponent,
  mTicketQuestStageRoute,
  mTicketQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mTicketQuestStageRoute, ...mTicketQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTicketQuestStageComponent,
    MTicketQuestStageDetailComponent,
    MTicketQuestStageUpdateComponent,
    MTicketQuestStageDeleteDialogComponent,
    MTicketQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MTicketQuestStageComponent,
    MTicketQuestStageUpdateComponent,
    MTicketQuestStageDeleteDialogComponent,
    MTicketQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTicketQuestStageModule {}
