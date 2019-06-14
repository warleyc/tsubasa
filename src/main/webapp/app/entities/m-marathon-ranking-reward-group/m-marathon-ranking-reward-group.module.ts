import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonRankingRewardGroupComponent,
  MMarathonRankingRewardGroupDetailComponent,
  MMarathonRankingRewardGroupUpdateComponent,
  MMarathonRankingRewardGroupDeletePopupComponent,
  MMarathonRankingRewardGroupDeleteDialogComponent,
  mMarathonRankingRewardGroupRoute,
  mMarathonRankingRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonRankingRewardGroupRoute, ...mMarathonRankingRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonRankingRewardGroupComponent,
    MMarathonRankingRewardGroupDetailComponent,
    MMarathonRankingRewardGroupUpdateComponent,
    MMarathonRankingRewardGroupDeleteDialogComponent,
    MMarathonRankingRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MMarathonRankingRewardGroupComponent,
    MMarathonRankingRewardGroupUpdateComponent,
    MMarathonRankingRewardGroupDeleteDialogComponent,
    MMarathonRankingRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonRankingRewardGroupModule {}
