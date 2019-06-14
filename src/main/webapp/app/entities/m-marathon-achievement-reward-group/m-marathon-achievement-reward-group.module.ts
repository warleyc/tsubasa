import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonAchievementRewardGroupComponent,
  MMarathonAchievementRewardGroupDetailComponent,
  MMarathonAchievementRewardGroupUpdateComponent,
  MMarathonAchievementRewardGroupDeletePopupComponent,
  MMarathonAchievementRewardGroupDeleteDialogComponent,
  mMarathonAchievementRewardGroupRoute,
  mMarathonAchievementRewardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonAchievementRewardGroupRoute, ...mMarathonAchievementRewardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonAchievementRewardGroupComponent,
    MMarathonAchievementRewardGroupDetailComponent,
    MMarathonAchievementRewardGroupUpdateComponent,
    MMarathonAchievementRewardGroupDeleteDialogComponent,
    MMarathonAchievementRewardGroupDeletePopupComponent
  ],
  entryComponents: [
    MMarathonAchievementRewardGroupComponent,
    MMarathonAchievementRewardGroupUpdateComponent,
    MMarathonAchievementRewardGroupDeleteDialogComponent,
    MMarathonAchievementRewardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonAchievementRewardGroupModule {}
