import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MStoreReviewUrlComponent,
  MStoreReviewUrlDetailComponent,
  MStoreReviewUrlUpdateComponent,
  MStoreReviewUrlDeletePopupComponent,
  MStoreReviewUrlDeleteDialogComponent,
  mStoreReviewUrlRoute,
  mStoreReviewUrlPopupRoute
} from './';

const ENTITY_STATES = [...mStoreReviewUrlRoute, ...mStoreReviewUrlPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MStoreReviewUrlComponent,
    MStoreReviewUrlDetailComponent,
    MStoreReviewUrlUpdateComponent,
    MStoreReviewUrlDeleteDialogComponent,
    MStoreReviewUrlDeletePopupComponent
  ],
  entryComponents: [
    MStoreReviewUrlComponent,
    MStoreReviewUrlUpdateComponent,
    MStoreReviewUrlDeleteDialogComponent,
    MStoreReviewUrlDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMStoreReviewUrlModule {}
