import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEncountersCommandCompatibilityComponent,
  MEncountersCommandCompatibilityDetailComponent,
  MEncountersCommandCompatibilityUpdateComponent,
  MEncountersCommandCompatibilityDeletePopupComponent,
  MEncountersCommandCompatibilityDeleteDialogComponent,
  mEncountersCommandCompatibilityRoute,
  mEncountersCommandCompatibilityPopupRoute
} from './';

const ENTITY_STATES = [...mEncountersCommandCompatibilityRoute, ...mEncountersCommandCompatibilityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEncountersCommandCompatibilityComponent,
    MEncountersCommandCompatibilityDetailComponent,
    MEncountersCommandCompatibilityUpdateComponent,
    MEncountersCommandCompatibilityDeleteDialogComponent,
    MEncountersCommandCompatibilityDeletePopupComponent
  ],
  entryComponents: [
    MEncountersCommandCompatibilityComponent,
    MEncountersCommandCompatibilityUpdateComponent,
    MEncountersCommandCompatibilityDeleteDialogComponent,
    MEncountersCommandCompatibilityDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEncountersCommandCompatibilityModule {}
