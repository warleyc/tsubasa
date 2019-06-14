import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTicketQuestWorldComponent,
  MTicketQuestWorldDetailComponent,
  MTicketQuestWorldUpdateComponent,
  MTicketQuestWorldDeletePopupComponent,
  MTicketQuestWorldDeleteDialogComponent,
  mTicketQuestWorldRoute,
  mTicketQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mTicketQuestWorldRoute, ...mTicketQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTicketQuestWorldComponent,
    MTicketQuestWorldDetailComponent,
    MTicketQuestWorldUpdateComponent,
    MTicketQuestWorldDeleteDialogComponent,
    MTicketQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MTicketQuestWorldComponent,
    MTicketQuestWorldUpdateComponent,
    MTicketQuestWorldDeleteDialogComponent,
    MTicketQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTicketQuestWorldModule {}
