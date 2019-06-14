import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueRankingRewardGroupComponent,
  MLeagueRankingRewardGroupDetailComponent,
  MLeagueRankingRewardGroupUpdateComponent,
  MLeagueRankingRewardGroupDeletePopupComponent,
  MLeagueRankingRewardGroupDeleteDialogComponent,
  mLeagueRankingRewardGroupRoute,
  mLeagueRankingRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mLeagueRankingRewardGroupRoute, ...mLeagueRankingRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueRankingRewardGroupComponent,
    MLeagueRankingRewardGroupDetailComponent,
    MLeagueRankingRewardGroupUpdateComponent,
    MLeagueRankingRewardGroupDeleteDialogComponent,
    MLeagueRankingRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MLeagueRankingRewardGroupComponent,
    MLeagueRankingRewardGroupUpdateComponent,
    MLeagueRankingRewardGroupDeleteDialogComponent,
    MLeagueRankingRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueRankingRewardGroupModule {}
