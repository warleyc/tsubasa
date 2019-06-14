import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRecommendFormationFilterComponent,
  MRecommendFormationFilterDetailComponent,
  MRecommendFormationFilterUpdateComponent,
  MRecommendFormationFilterDeletePopupComponent,
  MRecommendFormationFilterDeleteDialogComponent,
  mRecommendFormationFilterRoute,
  mRecommendFormationFilterPopupRoute
} from './';

const ENTITY_STATES = [...mRecommendFormationFilterRoute, ...mRecommendFormationFilterPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRecommendFormationFilterComponent,
    MRecommendFormationFilterDetailComponent,
    MRecommendFormationFilterUpdateComponent,
    MRecommendFormationFilterDeleteDialogComponent,
    MRecommendFormationFilterDeletePopupComponent
  ],
  entryComponents: [
    MRecommendFormationFilterComponent,
    MRecommendFormationFilterUpdateComponent,
    MRecommendFormationFilterDeleteDialogComponent,
    MRecommendFormationFilterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRecommendFormationFilterModule {}
