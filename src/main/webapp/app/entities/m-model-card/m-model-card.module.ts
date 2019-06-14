import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelCardComponent,
  MModelCardDetailComponent,
  MModelCardUpdateComponent,
  MModelCardDeletePopupComponent,
  MModelCardDeleteDialogComponent,
  mModelCardRoute,
  mModelCardPopupRoute
} from './';

const ENTITY_STATES = [...mModelCardRoute, ...mModelCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelCardComponent,
    MModelCardDetailComponent,
    MModelCardUpdateComponent,
    MModelCardDeleteDialogComponent,
    MModelCardDeletePopupComponent
  ],
  entryComponents: [MModelCardComponent, MModelCardUpdateComponent, MModelCardDeleteDialogComponent, MModelCardDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelCardModule {}
