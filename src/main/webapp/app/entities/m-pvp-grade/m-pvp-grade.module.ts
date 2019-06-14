import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpGradeComponent,
  MPvpGradeDetailComponent,
  MPvpGradeUpdateComponent,
  MPvpGradeDeletePopupComponent,
  MPvpGradeDeleteDialogComponent,
  mPvpGradeRoute,
  mPvpGradePopupRoute
} from './';

const ENTITY_STATES = [...mPvpGradeRoute, ...mPvpGradePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpGradeComponent,
    MPvpGradeDetailComponent,
    MPvpGradeUpdateComponent,
    MPvpGradeDeleteDialogComponent,
    MPvpGradeDeletePopupComponent
  ],
  entryComponents: [MPvpGradeComponent, MPvpGradeUpdateComponent, MPvpGradeDeleteDialogComponent, MPvpGradeDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpGradeModule {}
