import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardIllustAssetsComponent,
  MCardIllustAssetsDetailComponent,
  MCardIllustAssetsUpdateComponent,
  MCardIllustAssetsDeletePopupComponent,
  MCardIllustAssetsDeleteDialogComponent,
  mCardIllustAssetsRoute,
  mCardIllustAssetsPopupRoute
} from './';

const ENTITY_STATES = [...mCardIllustAssetsRoute, ...mCardIllustAssetsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardIllustAssetsComponent,
    MCardIllustAssetsDetailComponent,
    MCardIllustAssetsUpdateComponent,
    MCardIllustAssetsDeleteDialogComponent,
    MCardIllustAssetsDeletePopupComponent
  ],
  entryComponents: [
    MCardIllustAssetsComponent,
    MCardIllustAssetsUpdateComponent,
    MCardIllustAssetsDeleteDialogComponent,
    MCardIllustAssetsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardIllustAssetsModule {}
