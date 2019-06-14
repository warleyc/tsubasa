import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueRankingRewardComponent,
  MLeagueRankingRewardDetailComponent,
  MLeagueRankingRewardUpdateComponent,
  MLeagueRankingRewardDeletePopupComponent,
  MLeagueRankingRewardDeleteDialogComponent,
  mLeagueRankingRewardRoute,
  mLeagueRankingRewardPopupRoute
} from './';

const ENTITY_STATES = [...mLeagueRankingRewardRoute, ...mLeagueRankingRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueRankingRewardComponent,
    MLeagueRankingRewardDetailComponent,
    MLeagueRankingRewardUpdateComponent,
    MLeagueRankingRewardDeleteDialogComponent,
    MLeagueRankingRewardDeletePopupComponent
  ],
  entryComponents: [
    MLeagueRankingRewardComponent,
    MLeagueRankingRewardUpdateComponent,
    MLeagueRankingRewardDeleteDialogComponent,
    MLeagueRankingRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueRankingRewardModule {}
