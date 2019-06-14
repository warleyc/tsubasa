import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildMissionComponent,
  MGuildMissionDetailComponent,
  MGuildMissionUpdateComponent,
  MGuildMissionDeletePopupComponent,
  MGuildMissionDeleteDialogComponent,
  mGuildMissionRoute,
  mGuildMissionPopupRoute
} from './';

const ENTITY_STATES = [...mGuildMissionRoute, ...mGuildMissionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildMissionComponent,
    MGuildMissionDetailComponent,
    MGuildMissionUpdateComponent,
    MGuildMissionDeleteDialogComponent,
    MGuildMissionDeletePopupComponent
  ],
  entryComponents: [
    MGuildMissionComponent,
    MGuildMissionUpdateComponent,
    MGuildMissionDeleteDialogComponent,
    MGuildMissionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildMissionModule {}
