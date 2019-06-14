import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpRankingRewardComponent,
  MPvpRankingRewardDetailComponent,
  MPvpRankingRewardUpdateComponent,
  MPvpRankingRewardDeletePopupComponent,
  MPvpRankingRewardDeleteDialogComponent,
  mPvpRankingRewardRoute,
  mPvpRankingRewardPopupRoute
} from './';

const ENTITY_STATES = [...mPvpRankingRewardRoute, ...mPvpRankingRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpRankingRewardComponent,
    MPvpRankingRewardDetailComponent,
    MPvpRankingRewardUpdateComponent,
    MPvpRankingRewardDeleteDialogComponent,
    MPvpRankingRewardDeletePopupComponent
  ],
  entryComponents: [
    MPvpRankingRewardComponent,
    MPvpRankingRewardUpdateComponent,
    MPvpRankingRewardDeleteDialogComponent,
    MPvpRankingRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpRankingRewardModule {}
