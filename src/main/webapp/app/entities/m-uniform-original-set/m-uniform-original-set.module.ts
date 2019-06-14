import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MUniformOriginalSetComponent,
  MUniformOriginalSetDetailComponent,
  MUniformOriginalSetUpdateComponent,
  MUniformOriginalSetDeletePopupComponent,
  MUniformOriginalSetDeleteDialogComponent,
  mUniformOriginalSetRoute,
  mUniformOriginalSetPopupRoute
} from './';

const ENTITY_STATES = [...mUniformOriginalSetRoute, ...mUniformOriginalSetPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MUniformOriginalSetComponent,
    MUniformOriginalSetDetailComponent,
    MUniformOriginalSetUpdateComponent,
    MUniformOriginalSetDeleteDialogComponent,
    MUniformOriginalSetDeletePopupComponent
  ],
  entryComponents: [
    MUniformOriginalSetComponent,
    MUniformOriginalSetUpdateComponent,
    MUniformOriginalSetDeleteDialogComponent,
    MUniformOriginalSetDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMUniformOriginalSetModule {}
