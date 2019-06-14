import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MHomeBannerComponent,
  MHomeBannerDetailComponent,
  MHomeBannerUpdateComponent,
  MHomeBannerDeletePopupComponent,
  MHomeBannerDeleteDialogComponent,
  mHomeBannerRoute,
  mHomeBannerPopupRoute
} from './';

const ENTITY_STATES = [...mHomeBannerRoute, ...mHomeBannerPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MHomeBannerComponent,
    MHomeBannerDetailComponent,
    MHomeBannerUpdateComponent,
    MHomeBannerDeleteDialogComponent,
    MHomeBannerDeletePopupComponent
  ],
  entryComponents: [MHomeBannerComponent, MHomeBannerUpdateComponent, MHomeBannerDeleteDialogComponent, MHomeBannerDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMHomeBannerModule {}
