import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEncountersBonusComponent,
  MEncountersBonusDetailComponent,
  MEncountersBonusUpdateComponent,
  MEncountersBonusDeletePopupComponent,
  MEncountersBonusDeleteDialogComponent,
  mEncountersBonusRoute,
  mEncountersBonusPopupRoute
} from './';

const ENTITY_STATES = [...mEncountersBonusRoute, ...mEncountersBonusPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEncountersBonusComponent,
    MEncountersBonusDetailComponent,
    MEncountersBonusUpdateComponent,
    MEncountersBonusDeleteDialogComponent,
    MEncountersBonusDeletePopupComponent
  ],
  entryComponents: [
    MEncountersBonusComponent,
    MEncountersBonusUpdateComponent,
    MEncountersBonusDeleteDialogComponent,
    MEncountersBonusDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEncountersBonusModule {}
