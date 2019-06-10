import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCommonBannerComponent,
  MCommonBannerDetailComponent,
  MCommonBannerUpdateComponent,
  MCommonBannerDeletePopupComponent,
  MCommonBannerDeleteDialogComponent,
  mCommonBannerRoute,
  mCommonBannerPopupRoute
} from './';

const ENTITY_STATES = [...mCommonBannerRoute, ...mCommonBannerPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCommonBannerComponent,
    MCommonBannerDetailComponent,
    MCommonBannerUpdateComponent,
    MCommonBannerDeleteDialogComponent,
    MCommonBannerDeletePopupComponent
  ],
  entryComponents: [
    MCommonBannerComponent,
    MCommonBannerUpdateComponent,
    MCommonBannerDeleteDialogComponent,
    MCommonBannerDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCommonBannerModule {}
