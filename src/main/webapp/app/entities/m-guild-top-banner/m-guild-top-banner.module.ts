import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildTopBannerComponent,
  MGuildTopBannerDetailComponent,
  MGuildTopBannerUpdateComponent,
  MGuildTopBannerDeletePopupComponent,
  MGuildTopBannerDeleteDialogComponent,
  mGuildTopBannerRoute,
  mGuildTopBannerPopupRoute
} from './';

const ENTITY_STATES = [...mGuildTopBannerRoute, ...mGuildTopBannerPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildTopBannerComponent,
    MGuildTopBannerDetailComponent,
    MGuildTopBannerUpdateComponent,
    MGuildTopBannerDeleteDialogComponent,
    MGuildTopBannerDeletePopupComponent
  ],
  entryComponents: [
    MGuildTopBannerComponent,
    MGuildTopBannerUpdateComponent,
    MGuildTopBannerDeleteDialogComponent,
    MGuildTopBannerDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildTopBannerModule {}
