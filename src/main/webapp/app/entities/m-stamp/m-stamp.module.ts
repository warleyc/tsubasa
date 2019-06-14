import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MStampComponent,
  MStampDetailComponent,
  MStampUpdateComponent,
  MStampDeletePopupComponent,
  MStampDeleteDialogComponent,
  mStampRoute,
  mStampPopupRoute
} from './';

const ENTITY_STATES = [...mStampRoute, ...mStampPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MStampComponent, MStampDetailComponent, MStampUpdateComponent, MStampDeleteDialogComponent, MStampDeletePopupComponent],
  entryComponents: [MStampComponent, MStampUpdateComponent, MStampDeleteDialogComponent, MStampDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMStampModule {}
