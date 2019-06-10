import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEventTitleEffectComponent,
  MEventTitleEffectDetailComponent,
  MEventTitleEffectUpdateComponent,
  MEventTitleEffectDeletePopupComponent,
  MEventTitleEffectDeleteDialogComponent,
  mEventTitleEffectRoute,
  mEventTitleEffectPopupRoute
} from './';

const ENTITY_STATES = [...mEventTitleEffectRoute, ...mEventTitleEffectPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEventTitleEffectComponent,
    MEventTitleEffectDetailComponent,
    MEventTitleEffectUpdateComponent,
    MEventTitleEffectDeleteDialogComponent,
    MEventTitleEffectDeletePopupComponent
  ],
  entryComponents: [
    MEventTitleEffectComponent,
    MEventTitleEffectUpdateComponent,
    MEventTitleEffectDeleteDialogComponent,
    MEventTitleEffectDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEventTitleEffectModule {}
