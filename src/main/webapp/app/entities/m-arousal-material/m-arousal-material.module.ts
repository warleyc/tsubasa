import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MArousalMaterialComponent,
  MArousalMaterialDetailComponent,
  MArousalMaterialUpdateComponent,
  MArousalMaterialDeletePopupComponent,
  MArousalMaterialDeleteDialogComponent,
  mArousalMaterialRoute,
  mArousalMaterialPopupRoute
} from './';

const ENTITY_STATES = [...mArousalMaterialRoute, ...mArousalMaterialPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MArousalMaterialComponent,
    MArousalMaterialDetailComponent,
    MArousalMaterialUpdateComponent,
    MArousalMaterialDeleteDialogComponent,
    MArousalMaterialDeletePopupComponent
  ],
  entryComponents: [
    MArousalMaterialComponent,
    MArousalMaterialUpdateComponent,
    MArousalMaterialDeleteDialogComponent,
    MArousalMaterialDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMArousalMaterialModule {}
