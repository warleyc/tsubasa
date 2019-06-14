import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLuckRateGroupComponent,
  MLuckRateGroupDetailComponent,
  MLuckRateGroupUpdateComponent,
  MLuckRateGroupDeletePopupComponent,
  MLuckRateGroupDeleteDialogComponent,
  mLuckRateGroupRoute,
  mLuckRateGroupPopupRoute
} from './';

const ENTITY_STATES = [...mLuckRateGroupRoute, ...mLuckRateGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLuckRateGroupComponent,
    MLuckRateGroupDetailComponent,
    MLuckRateGroupUpdateComponent,
    MLuckRateGroupDeleteDialogComponent,
    MLuckRateGroupDeletePopupComponent
  ],
  entryComponents: [
    MLuckRateGroupComponent,
    MLuckRateGroupUpdateComponent,
    MLuckRateGroupDeleteDialogComponent,
    MLuckRateGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLuckRateGroupModule {}
