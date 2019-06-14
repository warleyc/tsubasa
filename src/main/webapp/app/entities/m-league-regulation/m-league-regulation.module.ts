import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueRegulationComponent,
  MLeagueRegulationDetailComponent,
  MLeagueRegulationUpdateComponent,
  MLeagueRegulationDeletePopupComponent,
  MLeagueRegulationDeleteDialogComponent,
  mLeagueRegulationRoute,
  mLeagueRegulationPopupRoute
} from './';

const ENTITY_STATES = [...mLeagueRegulationRoute, ...mLeagueRegulationPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueRegulationComponent,
    MLeagueRegulationDetailComponent,
    MLeagueRegulationUpdateComponent,
    MLeagueRegulationDeleteDialogComponent,
    MLeagueRegulationDeletePopupComponent
  ],
  entryComponents: [
    MLeagueRegulationComponent,
    MLeagueRegulationUpdateComponent,
    MLeagueRegulationDeleteDialogComponent,
    MLeagueRegulationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueRegulationModule {}
