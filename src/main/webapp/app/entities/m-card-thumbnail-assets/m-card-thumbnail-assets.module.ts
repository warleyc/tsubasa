import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardThumbnailAssetsComponent,
  MCardThumbnailAssetsDetailComponent,
  MCardThumbnailAssetsUpdateComponent,
  MCardThumbnailAssetsDeletePopupComponent,
  MCardThumbnailAssetsDeleteDialogComponent,
  mCardThumbnailAssetsRoute,
  mCardThumbnailAssetsPopupRoute
} from './';

const ENTITY_STATES = [...mCardThumbnailAssetsRoute, ...mCardThumbnailAssetsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardThumbnailAssetsComponent,
    MCardThumbnailAssetsDetailComponent,
    MCardThumbnailAssetsUpdateComponent,
    MCardThumbnailAssetsDeleteDialogComponent,
    MCardThumbnailAssetsDeletePopupComponent
  ],
  entryComponents: [
    MCardThumbnailAssetsComponent,
    MCardThumbnailAssetsUpdateComponent,
    MCardThumbnailAssetsDeleteDialogComponent,
    MCardThumbnailAssetsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardThumbnailAssetsModule {}
