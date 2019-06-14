import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGoalJudgementComponent,
  MGoalJudgementDetailComponent,
  MGoalJudgementUpdateComponent,
  MGoalJudgementDeletePopupComponent,
  MGoalJudgementDeleteDialogComponent,
  mGoalJudgementRoute,
  mGoalJudgementPopupRoute
} from './';

const ENTITY_STATES = [...mGoalJudgementRoute, ...mGoalJudgementPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGoalJudgementComponent,
    MGoalJudgementDetailComponent,
    MGoalJudgementUpdateComponent,
    MGoalJudgementDeleteDialogComponent,
    MGoalJudgementDeletePopupComponent
  ],
  entryComponents: [
    MGoalJudgementComponent,
    MGoalJudgementUpdateComponent,
    MGoalJudgementDeleteDialogComponent,
    MGoalJudgementDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGoalJudgementModule {}
