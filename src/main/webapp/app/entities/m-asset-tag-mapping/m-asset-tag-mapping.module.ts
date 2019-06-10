import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAssetTagMappingComponent,
  MAssetTagMappingDetailComponent,
  MAssetTagMappingUpdateComponent,
  MAssetTagMappingDeletePopupComponent,
  MAssetTagMappingDeleteDialogComponent,
  mAssetTagMappingRoute,
  mAssetTagMappingPopupRoute
} from './';

const ENTITY_STATES = [...mAssetTagMappingRoute, ...mAssetTagMappingPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAssetTagMappingComponent,
    MAssetTagMappingDetailComponent,
    MAssetTagMappingUpdateComponent,
    MAssetTagMappingDeleteDialogComponent,
    MAssetTagMappingDeletePopupComponent
  ],
  entryComponents: [
    MAssetTagMappingComponent,
    MAssetTagMappingUpdateComponent,
    MAssetTagMappingDeleteDialogComponent,
    MAssetTagMappingDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAssetTagMappingModule {}
