import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpPlayerStampComponent,
  MPvpPlayerStampDetailComponent,
  MPvpPlayerStampUpdateComponent,
  MPvpPlayerStampDeletePopupComponent,
  MPvpPlayerStampDeleteDialogComponent,
  mPvpPlayerStampRoute,
  mPvpPlayerStampPopupRoute
} from './';

const ENTITY_STATES = [...mPvpPlayerStampRoute, ...mPvpPlayerStampPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpPlayerStampComponent,
    MPvpPlayerStampDetailComponent,
    MPvpPlayerStampUpdateComponent,
    MPvpPlayerStampDeleteDialogComponent,
    MPvpPlayerStampDeletePopupComponent
  ],
  entryComponents: [
    MPvpPlayerStampComponent,
    MPvpPlayerStampUpdateComponent,
    MPvpPlayerStampDeleteDialogComponent,
    MPvpPlayerStampDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpPlayerStampModule {}
