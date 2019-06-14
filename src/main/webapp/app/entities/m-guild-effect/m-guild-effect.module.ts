import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildEffectComponent,
  MGuildEffectDetailComponent,
  MGuildEffectUpdateComponent,
  MGuildEffectDeletePopupComponent,
  MGuildEffectDeleteDialogComponent,
  mGuildEffectRoute,
  mGuildEffectPopupRoute
} from './';

const ENTITY_STATES = [...mGuildEffectRoute, ...mGuildEffectPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildEffectComponent,
    MGuildEffectDetailComponent,
    MGuildEffectUpdateComponent,
    MGuildEffectDeleteDialogComponent,
    MGuildEffectDeletePopupComponent
  ],
  entryComponents: [
    MGuildEffectComponent,
    MGuildEffectUpdateComponent,
    MGuildEffectDeleteDialogComponent,
    MGuildEffectDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildEffectModule {}
