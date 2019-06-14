import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MUserRankComponent,
  MUserRankDetailComponent,
  MUserRankUpdateComponent,
  MUserRankDeletePopupComponent,
  MUserRankDeleteDialogComponent,
  mUserRankRoute,
  mUserRankPopupRoute
} from './';

const ENTITY_STATES = [...mUserRankRoute, ...mUserRankPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MUserRankComponent,
    MUserRankDetailComponent,
    MUserRankUpdateComponent,
    MUserRankDeleteDialogComponent,
    MUserRankDeletePopupComponent
  ],
  entryComponents: [MUserRankComponent, MUserRankUpdateComponent, MUserRankDeleteDialogComponent, MUserRankDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMUserRankModule {}
