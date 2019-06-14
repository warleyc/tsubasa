import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLeagueAffiliateRewardComponent,
  MLeagueAffiliateRewardDetailComponent,
  MLeagueAffiliateRewardUpdateComponent,
  MLeagueAffiliateRewardDeletePopupComponent,
  MLeagueAffiliateRewardDeleteDialogComponent,
  mLeagueAffiliateRewardRoute,
  mLeagueAffiliateRewardPopupRoute
} from './';

const ENTITY_STATES = [...mLeagueAffiliateRewardRoute, ...mLeagueAffiliateRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLeagueAffiliateRewardComponent,
    MLeagueAffiliateRewardDetailComponent,
    MLeagueAffiliateRewardUpdateComponent,
    MLeagueAffiliateRewardDeleteDialogComponent,
    MLeagueAffiliateRewardDeletePopupComponent
  ],
  entryComponents: [
    MLeagueAffiliateRewardComponent,
    MLeagueAffiliateRewardUpdateComponent,
    MLeagueAffiliateRewardDeleteDialogComponent,
    MLeagueAffiliateRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLeagueAffiliateRewardModule {}
