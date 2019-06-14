import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTimeattackRankingRewardComponent,
  MTimeattackRankingRewardDetailComponent,
  MTimeattackRankingRewardUpdateComponent,
  MTimeattackRankingRewardDeletePopupComponent,
  MTimeattackRankingRewardDeleteDialogComponent,
  mTimeattackRankingRewardRoute,
  mTimeattackRankingRewardPopupRoute
} from './';

const ENTITY_STATES = [...mTimeattackRankingRewardRoute, ...mTimeattackRankingRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTimeattackRankingRewardComponent,
    MTimeattackRankingRewardDetailComponent,
    MTimeattackRankingRewardUpdateComponent,
    MTimeattackRankingRewardDeleteDialogComponent,
    MTimeattackRankingRewardDeletePopupComponent
  ],
  entryComponents: [
    MTimeattackRankingRewardComponent,
    MTimeattackRankingRewardUpdateComponent,
    MTimeattackRankingRewardDeleteDialogComponent,
    MTimeattackRankingRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTimeattackRankingRewardModule {}
