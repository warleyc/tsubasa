import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPassiveEffectRangeComponent,
  MPassiveEffectRangeDetailComponent,
  MPassiveEffectRangeUpdateComponent,
  MPassiveEffectRangeDeletePopupComponent,
  MPassiveEffectRangeDeleteDialogComponent,
  mPassiveEffectRangeRoute,
  mPassiveEffectRangePopupRoute
} from './';

const ENTITY_STATES = [...mPassiveEffectRangeRoute, ...mPassiveEffectRangePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPassiveEffectRangeComponent,
    MPassiveEffectRangeDetailComponent,
    MPassiveEffectRangeUpdateComponent,
    MPassiveEffectRangeDeleteDialogComponent,
    MPassiveEffectRangeDeletePopupComponent
  ],
  entryComponents: [
    MPassiveEffectRangeComponent,
    MPassiveEffectRangeUpdateComponent,
    MPassiveEffectRangeDeleteDialogComponent,
    MPassiveEffectRangeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPassiveEffectRangeModule {}
