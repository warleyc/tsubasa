import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonAchievementRewardComponent,
  MMarathonAchievementRewardDetailComponent,
  MMarathonAchievementRewardUpdateComponent,
  MMarathonAchievementRewardDeletePopupComponent,
  MMarathonAchievementRewardDeleteDialogComponent,
  mMarathonAchievementRewardRoute,
  mMarathonAchievementRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonAchievementRewardRoute, ...mMarathonAchievementRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonAchievementRewardComponent,
    MMarathonAchievementRewardDetailComponent,
    MMarathonAchievementRewardUpdateComponent,
    MMarathonAchievementRewardDeleteDialogComponent,
    MMarathonAchievementRewardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonAchievementRewardComponent,
    MMarathonAchievementRewardUpdateComponent,
    MMarathonAchievementRewardDeleteDialogComponent,
    MMarathonAchievementRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonAchievementRewardModule {}
