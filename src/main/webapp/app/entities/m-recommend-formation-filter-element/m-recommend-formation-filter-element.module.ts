import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRecommendFormationFilterElementComponent,
  MRecommendFormationFilterElementDetailComponent,
  MRecommendFormationFilterElementUpdateComponent,
  MRecommendFormationFilterElementDeletePopupComponent,
  MRecommendFormationFilterElementDeleteDialogComponent,
  mRecommendFormationFilterElementRoute,
  mRecommendFormationFilterElementPopupRoute
} from './';

const ENTITY_STATES = [...mRecommendFormationFilterElementRoute, ...mRecommendFormationFilterElementPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRecommendFormationFilterElementComponent,
    MRecommendFormationFilterElementDetailComponent,
    MRecommendFormationFilterElementUpdateComponent,
    MRecommendFormationFilterElementDeleteDialogComponent,
    MRecommendFormationFilterElementDeletePopupComponent
  ],
  entryComponents: [
    MRecommendFormationFilterElementComponent,
    MRecommendFormationFilterElementUpdateComponent,
    MRecommendFormationFilterElementDeleteDialogComponent,
    MRecommendFormationFilterElementDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRecommendFormationFilterElementModule {}
