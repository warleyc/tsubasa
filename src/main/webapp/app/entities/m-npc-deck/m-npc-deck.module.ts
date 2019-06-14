import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MNpcDeckComponent,
  MNpcDeckDetailComponent,
  MNpcDeckUpdateComponent,
  MNpcDeckDeletePopupComponent,
  MNpcDeckDeleteDialogComponent,
  mNpcDeckRoute,
  mNpcDeckPopupRoute
} from './';

const ENTITY_STATES = [...mNpcDeckRoute, ...mNpcDeckPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MNpcDeckComponent,
    MNpcDeckDetailComponent,
    MNpcDeckUpdateComponent,
    MNpcDeckDeleteDialogComponent,
    MNpcDeckDeletePopupComponent
  ],
  entryComponents: [MNpcDeckComponent, MNpcDeckUpdateComponent, MNpcDeckDeleteDialogComponent, MNpcDeckDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMNpcDeckModule {}
