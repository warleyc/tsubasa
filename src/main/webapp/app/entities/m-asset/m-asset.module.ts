import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAssetComponent,
  MAssetDetailComponent,
  MAssetUpdateComponent,
  MAssetDeletePopupComponent,
  MAssetDeleteDialogComponent,
  mAssetRoute,
  mAssetPopupRoute
} from './';

const ENTITY_STATES = [...mAssetRoute, ...mAssetPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MAssetComponent, MAssetDetailComponent, MAssetUpdateComponent, MAssetDeleteDialogComponent, MAssetDeletePopupComponent],
  entryComponents: [MAssetComponent, MAssetUpdateComponent, MAssetDeleteDialogComponent, MAssetDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAssetModule {}
