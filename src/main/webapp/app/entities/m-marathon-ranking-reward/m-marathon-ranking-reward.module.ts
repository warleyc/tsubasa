import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonRankingRewardComponent,
  MMarathonRankingRewardDetailComponent,
  MMarathonRankingRewardUpdateComponent,
  MMarathonRankingRewardDeletePopupComponent,
  MMarathonRankingRewardDeleteDialogComponent,
  mMarathonRankingRewardRoute,
  mMarathonRankingRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonRankingRewardRoute, ...mMarathonRankingRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonRankingRewardComponent,
    MMarathonRankingRewardDetailComponent,
    MMarathonRankingRewardUpdateComponent,
    MMarathonRankingRewardDeleteDialogComponent,
    MMarathonRankingRewardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonRankingRewardComponent,
    MMarathonRankingRewardUpdateComponent,
    MMarathonRankingRewardDeleteDialogComponent,
    MMarathonRankingRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonRankingRewardModule {}
