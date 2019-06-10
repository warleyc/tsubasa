import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCoopRoomStampComponent,
  MCoopRoomStampDetailComponent,
  MCoopRoomStampUpdateComponent,
  MCoopRoomStampDeletePopupComponent,
  MCoopRoomStampDeleteDialogComponent,
  mCoopRoomStampRoute,
  mCoopRoomStampPopupRoute
} from './';

const ENTITY_STATES = [...mCoopRoomStampRoute, ...mCoopRoomStampPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCoopRoomStampComponent,
    MCoopRoomStampDetailComponent,
    MCoopRoomStampUpdateComponent,
    MCoopRoomStampDeleteDialogComponent,
    MCoopRoomStampDeletePopupComponent
  ],
  entryComponents: [
    MCoopRoomStampComponent,
    MCoopRoomStampUpdateComponent,
    MCoopRoomStampDeleteDialogComponent,
    MCoopRoomStampDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCoopRoomStampModule {}
