import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonLoopRewardComponent,
  MMarathonLoopRewardDetailComponent,
  MMarathonLoopRewardUpdateComponent,
  MMarathonLoopRewardDeletePopupComponent,
  MMarathonLoopRewardDeleteDialogComponent,
  mMarathonLoopRewardRoute,
  mMarathonLoopRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonLoopRewardRoute, ...mMarathonLoopRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonLoopRewardComponent,
    MMarathonLoopRewardDetailComponent,
    MMarathonLoopRewardUpdateComponent,
    MMarathonLoopRewardDeleteDialogComponent,
    MMarathonLoopRewardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonLoopRewardComponent,
    MMarathonLoopRewardUpdateComponent,
    MMarathonLoopRewardDeleteDialogComponent,
    MMarathonLoopRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonLoopRewardModule {}
