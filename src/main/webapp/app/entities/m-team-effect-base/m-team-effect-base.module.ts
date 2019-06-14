import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTeamEffectBaseComponent,
  MTeamEffectBaseDetailComponent,
  MTeamEffectBaseUpdateComponent,
  MTeamEffectBaseDeletePopupComponent,
  MTeamEffectBaseDeleteDialogComponent,
  mTeamEffectBaseRoute,
  mTeamEffectBasePopupRoute
} from './';

const ENTITY_STATES = [...mTeamEffectBaseRoute, ...mTeamEffectBasePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTeamEffectBaseComponent,
    MTeamEffectBaseDetailComponent,
    MTeamEffectBaseUpdateComponent,
    MTeamEffectBaseDeleteDialogComponent,
    MTeamEffectBaseDeletePopupComponent
  ],
  entryComponents: [
    MTeamEffectBaseComponent,
    MTeamEffectBaseUpdateComponent,
    MTeamEffectBaseDeleteDialogComponent,
    MTeamEffectBaseDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTeamEffectBaseModule {}
