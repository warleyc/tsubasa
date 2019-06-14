import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMovieAssetComponent,
  MMovieAssetDetailComponent,
  MMovieAssetUpdateComponent,
  MMovieAssetDeletePopupComponent,
  MMovieAssetDeleteDialogComponent,
  mMovieAssetRoute,
  mMovieAssetPopupRoute
} from './';

const ENTITY_STATES = [...mMovieAssetRoute, ...mMovieAssetPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMovieAssetComponent,
    MMovieAssetDetailComponent,
    MMovieAssetUpdateComponent,
    MMovieAssetDeleteDialogComponent,
    MMovieAssetDeletePopupComponent
  ],
  entryComponents: [MMovieAssetComponent, MMovieAssetUpdateComponent, MMovieAssetDeleteDialogComponent, MMovieAssetDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMovieAssetModule {}
