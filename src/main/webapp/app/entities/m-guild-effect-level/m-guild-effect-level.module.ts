import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildEffectLevelComponent,
  MGuildEffectLevelDetailComponent,
  MGuildEffectLevelUpdateComponent,
  MGuildEffectLevelDeletePopupComponent,
  MGuildEffectLevelDeleteDialogComponent,
  mGuildEffectLevelRoute,
  mGuildEffectLevelPopupRoute
} from './';

const ENTITY_STATES = [...mGuildEffectLevelRoute, ...mGuildEffectLevelPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildEffectLevelComponent,
    MGuildEffectLevelDetailComponent,
    MGuildEffectLevelUpdateComponent,
    MGuildEffectLevelDeleteDialogComponent,
    MGuildEffectLevelDeletePopupComponent
  ],
  entryComponents: [
    MGuildEffectLevelComponent,
    MGuildEffectLevelUpdateComponent,
    MGuildEffectLevelDeleteDialogComponent,
    MGuildEffectLevelDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildEffectLevelModule {}
