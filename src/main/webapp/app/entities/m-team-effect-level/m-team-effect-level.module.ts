import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTeamEffectLevelComponent,
  MTeamEffectLevelDetailComponent,
  MTeamEffectLevelUpdateComponent,
  MTeamEffectLevelDeletePopupComponent,
  MTeamEffectLevelDeleteDialogComponent,
  mTeamEffectLevelRoute,
  mTeamEffectLevelPopupRoute
} from './';

const ENTITY_STATES = [...mTeamEffectLevelRoute, ...mTeamEffectLevelPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTeamEffectLevelComponent,
    MTeamEffectLevelDetailComponent,
    MTeamEffectLevelUpdateComponent,
    MTeamEffectLevelDeleteDialogComponent,
    MTeamEffectLevelDeletePopupComponent
  ],
  entryComponents: [
    MTeamEffectLevelComponent,
    MTeamEffectLevelUpdateComponent,
    MTeamEffectLevelDeleteDialogComponent,
    MTeamEffectLevelDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTeamEffectLevelModule {}
