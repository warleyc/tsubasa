import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTeamEffectRarityComponent,
  MTeamEffectRarityDetailComponent,
  MTeamEffectRarityUpdateComponent,
  MTeamEffectRarityDeletePopupComponent,
  MTeamEffectRarityDeleteDialogComponent,
  mTeamEffectRarityRoute,
  mTeamEffectRarityPopupRoute
} from './';

const ENTITY_STATES = [...mTeamEffectRarityRoute, ...mTeamEffectRarityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTeamEffectRarityComponent,
    MTeamEffectRarityDetailComponent,
    MTeamEffectRarityUpdateComponent,
    MTeamEffectRarityDeleteDialogComponent,
    MTeamEffectRarityDeletePopupComponent
  ],
  entryComponents: [
    MTeamEffectRarityComponent,
    MTeamEffectRarityUpdateComponent,
    MTeamEffectRarityDeleteDialogComponent,
    MTeamEffectRarityDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTeamEffectRarityModule {}
