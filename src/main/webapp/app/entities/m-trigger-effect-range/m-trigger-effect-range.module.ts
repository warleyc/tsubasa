import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTriggerEffectRangeComponent,
  MTriggerEffectRangeDetailComponent,
  MTriggerEffectRangeUpdateComponent,
  MTriggerEffectRangeDeletePopupComponent,
  MTriggerEffectRangeDeleteDialogComponent,
  mTriggerEffectRangeRoute,
  mTriggerEffectRangePopupRoute
} from './';

const ENTITY_STATES = [...mTriggerEffectRangeRoute, ...mTriggerEffectRangePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTriggerEffectRangeComponent,
    MTriggerEffectRangeDetailComponent,
    MTriggerEffectRangeUpdateComponent,
    MTriggerEffectRangeDeleteDialogComponent,
    MTriggerEffectRangeDeletePopupComponent
  ],
  entryComponents: [
    MTriggerEffectRangeComponent,
    MTriggerEffectRangeUpdateComponent,
    MTriggerEffectRangeDeleteDialogComponent,
    MTriggerEffectRangeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTriggerEffectRangeModule {}
