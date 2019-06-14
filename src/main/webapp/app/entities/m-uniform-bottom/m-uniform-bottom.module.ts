import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MUniformBottomComponent,
  MUniformBottomDetailComponent,
  MUniformBottomUpdateComponent,
  MUniformBottomDeletePopupComponent,
  MUniformBottomDeleteDialogComponent,
  mUniformBottomRoute,
  mUniformBottomPopupRoute
} from './';

const ENTITY_STATES = [...mUniformBottomRoute, ...mUniformBottomPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MUniformBottomComponent,
    MUniformBottomDetailComponent,
    MUniformBottomUpdateComponent,
    MUniformBottomDeleteDialogComponent,
    MUniformBottomDeletePopupComponent
  ],
  entryComponents: [
    MUniformBottomComponent,
    MUniformBottomUpdateComponent,
    MUniformBottomDeleteDialogComponent,
    MUniformBottomDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMUniformBottomModule {}
