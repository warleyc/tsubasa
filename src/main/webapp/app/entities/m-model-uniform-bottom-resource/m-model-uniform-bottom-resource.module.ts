import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelUniformBottomResourceComponent,
  MModelUniformBottomResourceDetailComponent,
  MModelUniformBottomResourceUpdateComponent,
  MModelUniformBottomResourceDeletePopupComponent,
  MModelUniformBottomResourceDeleteDialogComponent,
  mModelUniformBottomResourceRoute,
  mModelUniformBottomResourcePopupRoute
} from './';

const ENTITY_STATES = [...mModelUniformBottomResourceRoute, ...mModelUniformBottomResourcePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelUniformBottomResourceComponent,
    MModelUniformBottomResourceDetailComponent,
    MModelUniformBottomResourceUpdateComponent,
    MModelUniformBottomResourceDeleteDialogComponent,
    MModelUniformBottomResourceDeletePopupComponent
  ],
  entryComponents: [
    MModelUniformBottomResourceComponent,
    MModelUniformBottomResourceUpdateComponent,
    MModelUniformBottomResourceDeleteDialogComponent,
    MModelUniformBottomResourceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelUniformBottomResourceModule {}
