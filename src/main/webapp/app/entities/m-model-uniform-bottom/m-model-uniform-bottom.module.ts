import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelUniformBottomComponent,
  MModelUniformBottomDetailComponent,
  MModelUniformBottomUpdateComponent,
  MModelUniformBottomDeletePopupComponent,
  MModelUniformBottomDeleteDialogComponent,
  mModelUniformBottomRoute,
  mModelUniformBottomPopupRoute
} from './';

const ENTITY_STATES = [...mModelUniformBottomRoute, ...mModelUniformBottomPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelUniformBottomComponent,
    MModelUniformBottomDetailComponent,
    MModelUniformBottomUpdateComponent,
    MModelUniformBottomDeleteDialogComponent,
    MModelUniformBottomDeletePopupComponent
  ],
  entryComponents: [
    MModelUniformBottomComponent,
    MModelUniformBottomUpdateComponent,
    MModelUniformBottomDeleteDialogComponent,
    MModelUniformBottomDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelUniformBottomModule {}
