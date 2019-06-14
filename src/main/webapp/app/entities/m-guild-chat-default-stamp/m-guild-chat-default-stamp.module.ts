import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildChatDefaultStampComponent,
  MGuildChatDefaultStampDetailComponent,
  MGuildChatDefaultStampUpdateComponent,
  MGuildChatDefaultStampDeletePopupComponent,
  MGuildChatDefaultStampDeleteDialogComponent,
  mGuildChatDefaultStampRoute,
  mGuildChatDefaultStampPopupRoute
} from './';

const ENTITY_STATES = [...mGuildChatDefaultStampRoute, ...mGuildChatDefaultStampPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildChatDefaultStampComponent,
    MGuildChatDefaultStampDetailComponent,
    MGuildChatDefaultStampUpdateComponent,
    MGuildChatDefaultStampDeleteDialogComponent,
    MGuildChatDefaultStampDeletePopupComponent
  ],
  entryComponents: [
    MGuildChatDefaultStampComponent,
    MGuildChatDefaultStampUpdateComponent,
    MGuildChatDefaultStampDeleteDialogComponent,
    MGuildChatDefaultStampDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildChatDefaultStampModule {}
