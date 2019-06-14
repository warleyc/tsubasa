import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpGradeRequirementComponent,
  MPvpGradeRequirementDetailComponent,
  MPvpGradeRequirementUpdateComponent,
  MPvpGradeRequirementDeletePopupComponent,
  MPvpGradeRequirementDeleteDialogComponent,
  mPvpGradeRequirementRoute,
  mPvpGradeRequirementPopupRoute
} from './';

const ENTITY_STATES = [...mPvpGradeRequirementRoute, ...mPvpGradeRequirementPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpGradeRequirementComponent,
    MPvpGradeRequirementDetailComponent,
    MPvpGradeRequirementUpdateComponent,
    MPvpGradeRequirementDeleteDialogComponent,
    MPvpGradeRequirementDeletePopupComponent
  ],
  entryComponents: [
    MPvpGradeRequirementComponent,
    MPvpGradeRequirementUpdateComponent,
    MPvpGradeRequirementDeleteDialogComponent,
    MPvpGradeRequirementDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpGradeRequirementModule {}
