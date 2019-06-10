import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEncountersCutComponent,
  MEncountersCutDetailComponent,
  MEncountersCutUpdateComponent,
  MEncountersCutDeletePopupComponent,
  MEncountersCutDeleteDialogComponent,
  mEncountersCutRoute,
  mEncountersCutPopupRoute
} from './';

const ENTITY_STATES = [...mEncountersCutRoute, ...mEncountersCutPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEncountersCutComponent,
    MEncountersCutDetailComponent,
    MEncountersCutUpdateComponent,
    MEncountersCutDeleteDialogComponent,
    MEncountersCutDeletePopupComponent
  ],
  entryComponents: [
    MEncountersCutComponent,
    MEncountersCutUpdateComponent,
    MEncountersCutDeleteDialogComponent,
    MEncountersCutDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEncountersCutModule {}
