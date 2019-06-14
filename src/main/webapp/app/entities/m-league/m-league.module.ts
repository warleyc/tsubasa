import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueComponent,
  MLeagueDetailComponent,
  MLeagueUpdateComponent,
  MLeagueDeletePopupComponent,
  MLeagueDeleteDialogComponent,
  mLeagueRoute,
  mLeaguePopupRoute
} from './';

const ENTITY_STATES = [...mLeagueRoute, ...mLeaguePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueComponent,
    MLeagueDetailComponent,
    MLeagueUpdateComponent,
    MLeagueDeleteDialogComponent,
    MLeagueDeletePopupComponent
  ],
  entryComponents: [MLeagueComponent, MLeagueUpdateComponent, MLeagueDeleteDialogComponent, MLeagueDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueModule {}
