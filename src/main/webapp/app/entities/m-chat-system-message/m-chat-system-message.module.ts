import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChatSystemMessageComponent,
  MChatSystemMessageDetailComponent,
  MChatSystemMessageUpdateComponent,
  MChatSystemMessageDeletePopupComponent,
  MChatSystemMessageDeleteDialogComponent,
  mChatSystemMessageRoute,
  mChatSystemMessagePopupRoute
} from './';

const ENTITY_STATES = [...mChatSystemMessageRoute, ...mChatSystemMessagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChatSystemMessageComponent,
    MChatSystemMessageDetailComponent,
    MChatSystemMessageUpdateComponent,
    MChatSystemMessageDeleteDialogComponent,
    MChatSystemMessageDeletePopupComponent
  ],
  entryComponents: [
    MChatSystemMessageComponent,
    MChatSystemMessageUpdateComponent,
    MChatSystemMessageDeleteDialogComponent,
    MChatSystemMessageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChatSystemMessageModule {}
