import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRegulatedLeagueRankingRewardComponent,
  MRegulatedLeagueRankingRewardDetailComponent,
  MRegulatedLeagueRankingRewardUpdateComponent,
  MRegulatedLeagueRankingRewardDeletePopupComponent,
  MRegulatedLeagueRankingRewardDeleteDialogComponent,
  mRegulatedLeagueRankingRewardRoute,
  mRegulatedLeagueRankingRewardPopupRoute
} from './';

const ENTITY_STATES = [...mRegulatedLeagueRankingRewardRoute, ...mRegulatedLeagueRankingRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRegulatedLeagueRankingRewardComponent,
    MRegulatedLeagueRankingRewardDetailComponent,
    MRegulatedLeagueRankingRewardUpdateComponent,
    MRegulatedLeagueRankingRewardDeleteDialogComponent,
    MRegulatedLeagueRankingRewardDeletePopupComponent
  ],
  entryComponents: [
    MRegulatedLeagueRankingRewardComponent,
    MRegulatedLeagueRankingRewardUpdateComponent,
    MRegulatedLeagueRankingRewardDeleteDialogComponent,
    MRegulatedLeagueRankingRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRegulatedLeagueRankingRewardModule {}
