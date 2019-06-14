import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonLoopRewardGroupComponent,
  MMarathonLoopRewardGroupDetailComponent,
  MMarathonLoopRewardGroupUpdateComponent,
  MMarathonLoopRewardGroupDeletePopupComponent,
  MMarathonLoopRewardGroupDeleteDialogComponent,
  mMarathonLoopRewardGroupRoute,
  mMarathonLoopRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonLoopRewardGroupRoute, ...mMarathonLoopRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonLoopRewardGroupComponent,
    MMarathonLoopRewardGroupDetailComponent,
    MMarathonLoopRewardGroupUpdateComponent,
    MMarathonLoopRewardGroupDeleteDialogComponent,
    MMarathonLoopRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MMarathonLoopRewardGroupComponent,
    MMarathonLoopRewardGroupUpdateComponent,
    MMarathonLoopRewardGroupDeleteDialogComponent,
    MMarathonLoopRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonLoopRewardGroupModule {}
