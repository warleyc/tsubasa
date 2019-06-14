import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRecommendShopMessageComponent,
  MRecommendShopMessageDetailComponent,
  MRecommendShopMessageUpdateComponent,
  MRecommendShopMessageDeletePopupComponent,
  MRecommendShopMessageDeleteDialogComponent,
  mRecommendShopMessageRoute,
  mRecommendShopMessagePopupRoute
} from './';

const ENTITY_STATES = [...mRecommendShopMessageRoute, ...mRecommendShopMessagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRecommendShopMessageComponent,
    MRecommendShopMessageDetailComponent,
    MRecommendShopMessageUpdateComponent,
    MRecommendShopMessageDeleteDialogComponent,
    MRecommendShopMessageDeletePopupComponent
  ],
  entryComponents: [
    MRecommendShopMessageComponent,
    MRecommendShopMessageUpdateComponent,
    MRecommendShopMessageDeleteDialogComponent,
    MRecommendShopMessageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRecommendShopMessageModule {}
