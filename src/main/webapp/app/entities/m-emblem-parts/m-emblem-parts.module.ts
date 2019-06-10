import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEmblemPartsComponent,
  MEmblemPartsDetailComponent,
  MEmblemPartsUpdateComponent,
  MEmblemPartsDeletePopupComponent,
  MEmblemPartsDeleteDialogComponent,
  mEmblemPartsRoute,
  mEmblemPartsPopupRoute
} from './';

const ENTITY_STATES = [...mEmblemPartsRoute, ...mEmblemPartsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEmblemPartsComponent,
    MEmblemPartsDetailComponent,
    MEmblemPartsUpdateComponent,
    MEmblemPartsDeleteDialogComponent,
    MEmblemPartsDeletePopupComponent
  ],
  entryComponents: [
    MEmblemPartsComponent,
    MEmblemPartsUpdateComponent,
    MEmblemPartsDeleteDialogComponent,
    MEmblemPartsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEmblemPartsModule {}
