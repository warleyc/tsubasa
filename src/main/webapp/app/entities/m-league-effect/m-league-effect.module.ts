import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueEffectComponent,
  MLeagueEffectDetailComponent,
  MLeagueEffectUpdateComponent,
  MLeagueEffectDeletePopupComponent,
  MLeagueEffectDeleteDialogComponent,
  mLeagueEffectRoute,
  mLeagueEffectPopupRoute
} from './';

const ENTITY_STATES = [...mLeagueEffectRoute, ...mLeagueEffectPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueEffectComponent,
    MLeagueEffectDetailComponent,
    MLeagueEffectUpdateComponent,
    MLeagueEffectDeleteDialogComponent,
    MLeagueEffectDeletePopupComponent
  ],
  entryComponents: [
    MLeagueEffectComponent,
    MLeagueEffectUpdateComponent,
    MLeagueEffectDeleteDialogComponent,
    MLeagueEffectDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueEffectModule {}
