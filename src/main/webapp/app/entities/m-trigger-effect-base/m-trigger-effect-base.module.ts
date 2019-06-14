import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTriggerEffectBaseComponent,
  MTriggerEffectBaseDetailComponent,
  MTriggerEffectBaseUpdateComponent,
  MTriggerEffectBaseDeletePopupComponent,
  MTriggerEffectBaseDeleteDialogComponent,
  mTriggerEffectBaseRoute,
  mTriggerEffectBasePopupRoute
} from './';

const ENTITY_STATES = [...mTriggerEffectBaseRoute, ...mTriggerEffectBasePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTriggerEffectBaseComponent,
    MTriggerEffectBaseDetailComponent,
    MTriggerEffectBaseUpdateComponent,
    MTriggerEffectBaseDeleteDialogComponent,
    MTriggerEffectBaseDeletePopupComponent
  ],
  entryComponents: [
    MTriggerEffectBaseComponent,
    MTriggerEffectBaseUpdateComponent,
    MTriggerEffectBaseDeleteDialogComponent,
    MTriggerEffectBaseDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTriggerEffectBaseModule {}
