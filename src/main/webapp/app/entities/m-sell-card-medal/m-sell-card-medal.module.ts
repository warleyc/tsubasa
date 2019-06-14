import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSellCardMedalComponent,
  MSellCardMedalDetailComponent,
  MSellCardMedalUpdateComponent,
  MSellCardMedalDeletePopupComponent,
  MSellCardMedalDeleteDialogComponent,
  mSellCardMedalRoute,
  mSellCardMedalPopupRoute
} from './';

const ENTITY_STATES = [...mSellCardMedalRoute, ...mSellCardMedalPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSellCardMedalComponent,
    MSellCardMedalDetailComponent,
    MSellCardMedalUpdateComponent,
    MSellCardMedalDeleteDialogComponent,
    MSellCardMedalDeletePopupComponent
  ],
  entryComponents: [
    MSellCardMedalComponent,
    MSellCardMedalUpdateComponent,
    MSellCardMedalDeleteDialogComponent,
    MSellCardMedalDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSellCardMedalModule {}
