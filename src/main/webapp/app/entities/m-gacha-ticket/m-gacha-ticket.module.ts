import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaTicketComponent,
  MGachaTicketDetailComponent,
  MGachaTicketUpdateComponent,
  MGachaTicketDeletePopupComponent,
  MGachaTicketDeleteDialogComponent,
  mGachaTicketRoute,
  mGachaTicketPopupRoute
} from './';

const ENTITY_STATES = [...mGachaTicketRoute, ...mGachaTicketPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaTicketComponent,
    MGachaTicketDetailComponent,
    MGachaTicketUpdateComponent,
    MGachaTicketDeleteDialogComponent,
    MGachaTicketDeletePopupComponent
  ],
  entryComponents: [
    MGachaTicketComponent,
    MGachaTicketUpdateComponent,
    MGachaTicketDeleteDialogComponent,
    MGachaTicketDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaTicketModule {}
