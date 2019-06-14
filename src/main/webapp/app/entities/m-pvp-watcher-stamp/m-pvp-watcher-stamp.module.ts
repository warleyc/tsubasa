import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpWatcherStampComponent,
  MPvpWatcherStampDetailComponent,
  MPvpWatcherStampUpdateComponent,
  MPvpWatcherStampDeletePopupComponent,
  MPvpWatcherStampDeleteDialogComponent,
  mPvpWatcherStampRoute,
  mPvpWatcherStampPopupRoute
} from './';

const ENTITY_STATES = [...mPvpWatcherStampRoute, ...mPvpWatcherStampPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpWatcherStampComponent,
    MPvpWatcherStampDetailComponent,
    MPvpWatcherStampUpdateComponent,
    MPvpWatcherStampDeleteDialogComponent,
    MPvpWatcherStampDeletePopupComponent
  ],
  entryComponents: [
    MPvpWatcherStampComponent,
    MPvpWatcherStampUpdateComponent,
    MPvpWatcherStampDeleteDialogComponent,
    MPvpWatcherStampDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpWatcherStampModule {}
