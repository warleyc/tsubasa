import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelUniformUpComponent,
  MModelUniformUpDetailComponent,
  MModelUniformUpUpdateComponent,
  MModelUniformUpDeletePopupComponent,
  MModelUniformUpDeleteDialogComponent,
  mModelUniformUpRoute,
  mModelUniformUpPopupRoute
} from './';

const ENTITY_STATES = [...mModelUniformUpRoute, ...mModelUniformUpPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelUniformUpComponent,
    MModelUniformUpDetailComponent,
    MModelUniformUpUpdateComponent,
    MModelUniformUpDeleteDialogComponent,
    MModelUniformUpDeletePopupComponent
  ],
  entryComponents: [
    MModelUniformUpComponent,
    MModelUniformUpUpdateComponent,
    MModelUniformUpDeleteDialogComponent,
    MModelUniformUpDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelUniformUpModule {}
