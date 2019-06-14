import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTimeattackRankingRewardGroupComponent,
  MTimeattackRankingRewardGroupDetailComponent,
  MTimeattackRankingRewardGroupUpdateComponent,
  MTimeattackRankingRewardGroupDeletePopupComponent,
  MTimeattackRankingRewardGroupDeleteDialogComponent,
  mTimeattackRankingRewardGroupRoute,
  mTimeattackRankingRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTimeattackRankingRewardGroupRoute, ...mTimeattackRankingRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTimeattackRankingRewardGroupComponent,
    MTimeattackRankingRewardGroupDetailComponent,
    MTimeattackRankingRewardGroupUpdateComponent,
    MTimeattackRankingRewardGroupDeleteDialogComponent,
    MTimeattackRankingRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MTimeattackRankingRewardGroupComponent,
    MTimeattackRankingRewardGroupUpdateComponent,
    MTimeattackRankingRewardGroupDeleteDialogComponent,
    MTimeattackRankingRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTimeattackRankingRewardGroupModule {}
