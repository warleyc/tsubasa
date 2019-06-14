import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MInitUserDeckComponent,
  MInitUserDeckDetailComponent,
  MInitUserDeckUpdateComponent,
  MInitUserDeckDeletePopupComponent,
  MInitUserDeckDeleteDialogComponent,
  mInitUserDeckRoute,
  mInitUserDeckPopupRoute
} from './';

const ENTITY_STATES = [...mInitUserDeckRoute, ...mInitUserDeckPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MInitUserDeckComponent,
    MInitUserDeckDetailComponent,
    MInitUserDeckUpdateComponent,
    MInitUserDeckDeleteDialogComponent,
    MInitUserDeckDeletePopupComponent
  ],
  entryComponents: [
    MInitUserDeckComponent,
    MInitUserDeckUpdateComponent,
    MInitUserDeckDeleteDialogComponent,
    MInitUserDeckDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMInitUserDeckModule {}
