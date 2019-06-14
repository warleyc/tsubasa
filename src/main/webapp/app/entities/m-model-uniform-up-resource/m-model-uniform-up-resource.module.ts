import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelUniformUpResourceComponent,
  MModelUniformUpResourceDetailComponent,
  MModelUniformUpResourceUpdateComponent,
  MModelUniformUpResourceDeletePopupComponent,
  MModelUniformUpResourceDeleteDialogComponent,
  mModelUniformUpResourceRoute,
  mModelUniformUpResourcePopupRoute
} from './';

const ENTITY_STATES = [...mModelUniformUpResourceRoute, ...mModelUniformUpResourcePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelUniformUpResourceComponent,
    MModelUniformUpResourceDetailComponent,
    MModelUniformUpResourceUpdateComponent,
    MModelUniformUpResourceDeleteDialogComponent,
    MModelUniformUpResourceDeletePopupComponent
  ],
  entryComponents: [
    MModelUniformUpResourceComponent,
    MModelUniformUpResourceUpdateComponent,
    MModelUniformUpResourceDeleteDialogComponent,
    MModelUniformUpResourceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelUniformUpResourceModule {}
