import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MHomeMarqueeComponent,
  MHomeMarqueeDetailComponent,
  MHomeMarqueeUpdateComponent,
  MHomeMarqueeDeletePopupComponent,
  MHomeMarqueeDeleteDialogComponent,
  mHomeMarqueeRoute,
  mHomeMarqueePopupRoute
} from './';

const ENTITY_STATES = [...mHomeMarqueeRoute, ...mHomeMarqueePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MHomeMarqueeComponent,
    MHomeMarqueeDetailComponent,
    MHomeMarqueeUpdateComponent,
    MHomeMarqueeDeleteDialogComponent,
    MHomeMarqueeDeletePopupComponent
  ],
  entryComponents: [
    MHomeMarqueeComponent,
    MHomeMarqueeUpdateComponent,
    MHomeMarqueeDeleteDialogComponent,
    MHomeMarqueeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMHomeMarqueeModule {}
