import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MUniformUpComponent,
  MUniformUpDetailComponent,
  MUniformUpUpdateComponent,
  MUniformUpDeletePopupComponent,
  MUniformUpDeleteDialogComponent,
  mUniformUpRoute,
  mUniformUpPopupRoute
} from './';

const ENTITY_STATES = [...mUniformUpRoute, ...mUniformUpPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MUniformUpComponent,
    MUniformUpDetailComponent,
    MUniformUpUpdateComponent,
    MUniformUpDeleteDialogComponent,
    MUniformUpDeletePopupComponent
  ],
  entryComponents: [MUniformUpComponent, MUniformUpUpdateComponent, MUniformUpDeleteDialogComponent, MUniformUpDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMUniformUpModule {}
