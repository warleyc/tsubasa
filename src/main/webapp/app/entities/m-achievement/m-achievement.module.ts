import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAchievementComponent,
  MAchievementDetailComponent,
  MAchievementUpdateComponent,
  MAchievementDeletePopupComponent,
  MAchievementDeleteDialogComponent,
  mAchievementRoute,
  mAchievementPopupRoute
} from './';

const ENTITY_STATES = [...mAchievementRoute, ...mAchievementPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAchievementComponent,
    MAchievementDetailComponent,
    MAchievementUpdateComponent,
    MAchievementDeleteDialogComponent,
    MAchievementDeletePopupComponent
  ],
  entryComponents: [
    MAchievementComponent,
    MAchievementUpdateComponent,
    MAchievementDeleteDialogComponent,
    MAchievementDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAchievementModule {}
