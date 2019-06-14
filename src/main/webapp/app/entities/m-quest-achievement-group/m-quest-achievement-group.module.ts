import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestAchievementGroupComponent,
  MQuestAchievementGroupDetailComponent,
  MQuestAchievementGroupUpdateComponent,
  MQuestAchievementGroupDeletePopupComponent,
  MQuestAchievementGroupDeleteDialogComponent,
  mQuestAchievementGroupRoute,
  mQuestAchievementGroupPopupRoute
} from './';

const ENTITY_STATES = [...mQuestAchievementGroupRoute, ...mQuestAchievementGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestAchievementGroupComponent,
    MQuestAchievementGroupDetailComponent,
    MQuestAchievementGroupUpdateComponent,
    MQuestAchievementGroupDeleteDialogComponent,
    MQuestAchievementGroupDeletePopupComponent
  ],
  entryComponents: [
    MQuestAchievementGroupComponent,
    MQuestAchievementGroupUpdateComponent,
    MQuestAchievementGroupDeleteDialogComponent,
    MQuestAchievementGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestAchievementGroupModule {}
