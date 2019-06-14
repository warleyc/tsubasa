import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MNpcCardComponent,
  MNpcCardDetailComponent,
  MNpcCardUpdateComponent,
  MNpcCardDeletePopupComponent,
  MNpcCardDeleteDialogComponent,
  mNpcCardRoute,
  mNpcCardPopupRoute
} from './';

const ENTITY_STATES = [...mNpcCardRoute, ...mNpcCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MNpcCardComponent,
    MNpcCardDetailComponent,
    MNpcCardUpdateComponent,
    MNpcCardDeleteDialogComponent,
    MNpcCardDeletePopupComponent
  ],
  entryComponents: [MNpcCardComponent, MNpcCardUpdateComponent, MNpcCardDeleteDialogComponent, MNpcCardDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMNpcCardModule {}
