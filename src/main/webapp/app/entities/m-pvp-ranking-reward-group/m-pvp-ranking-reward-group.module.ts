import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpRankingRewardGroupComponent,
  MPvpRankingRewardGroupDetailComponent,
  MPvpRankingRewardGroupUpdateComponent,
  MPvpRankingRewardGroupDeletePopupComponent,
  MPvpRankingRewardGroupDeleteDialogComponent,
  mPvpRankingRewardGroupRoute,
  mPvpRankingRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mPvpRankingRewardGroupRoute, ...mPvpRankingRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpRankingRewardGroupComponent,
    MPvpRankingRewardGroupDetailComponent,
    MPvpRankingRewardGroupUpdateComponent,
    MPvpRankingRewardGroupDeleteDialogComponent,
    MPvpRankingRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MPvpRankingRewardGroupComponent,
    MPvpRankingRewardGroupUpdateComponent,
    MPvpRankingRewardGroupDeleteDialogComponent,
    MPvpRankingRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpRankingRewardGroupModule {}
