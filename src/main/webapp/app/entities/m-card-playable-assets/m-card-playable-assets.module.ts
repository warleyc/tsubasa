import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardPlayableAssetsComponent,
  MCardPlayableAssetsDetailComponent,
  MCardPlayableAssetsUpdateComponent,
  MCardPlayableAssetsDeletePopupComponent,
  MCardPlayableAssetsDeleteDialogComponent,
  mCardPlayableAssetsRoute,
  mCardPlayableAssetsPopupRoute
} from './';

const ENTITY_STATES = [...mCardPlayableAssetsRoute, ...mCardPlayableAssetsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardPlayableAssetsComponent,
    MCardPlayableAssetsDetailComponent,
    MCardPlayableAssetsUpdateComponent,
    MCardPlayableAssetsDeleteDialogComponent,
    MCardPlayableAssetsDeletePopupComponent
  ],
  entryComponents: [
    MCardPlayableAssetsComponent,
    MCardPlayableAssetsUpdateComponent,
    MCardPlayableAssetsDeleteDialogComponent,
    MCardPlayableAssetsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardPlayableAssetsModule {}
