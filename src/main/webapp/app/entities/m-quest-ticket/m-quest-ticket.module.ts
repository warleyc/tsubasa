import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestTicketComponent,
  MQuestTicketDetailComponent,
  MQuestTicketUpdateComponent,
  MQuestTicketDeletePopupComponent,
  MQuestTicketDeleteDialogComponent,
  mQuestTicketRoute,
  mQuestTicketPopupRoute
} from './';

const ENTITY_STATES = [...mQuestTicketRoute, ...mQuestTicketPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestTicketComponent,
    MQuestTicketDetailComponent,
    MQuestTicketUpdateComponent,
    MQuestTicketDeleteDialogComponent,
    MQuestTicketDeletePopupComponent
  ],
  entryComponents: [
    MQuestTicketComponent,
    MQuestTicketUpdateComponent,
    MQuestTicketDeleteDialogComponent,
    MQuestTicketDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestTicketModule {}
