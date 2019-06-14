import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTicketQuestTsubasaPointRewardComponent,
  MTicketQuestTsubasaPointRewardDetailComponent,
  MTicketQuestTsubasaPointRewardUpdateComponent,
  MTicketQuestTsubasaPointRewardDeletePopupComponent,
  MTicketQuestTsubasaPointRewardDeleteDialogComponent,
  mTicketQuestTsubasaPointRewardRoute,
  mTicketQuestTsubasaPointRewardPopupRoute
} from './';

const ENTITY_STATES = [...mTicketQuestTsubasaPointRewardRoute, ...mTicketQuestTsubasaPointRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTicketQuestTsubasaPointRewardComponent,
    MTicketQuestTsubasaPointRewardDetailComponent,
    MTicketQuestTsubasaPointRewardUpdateComponent,
    MTicketQuestTsubasaPointRewardDeleteDialogComponent,
    MTicketQuestTsubasaPointRewardDeletePopupComponent
  ],
  entryComponents: [
    MTicketQuestTsubasaPointRewardComponent,
    MTicketQuestTsubasaPointRewardUpdateComponent,
    MTicketQuestTsubasaPointRewardDeleteDialogComponent,
    MTicketQuestTsubasaPointRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTicketQuestTsubasaPointRewardModule {}
