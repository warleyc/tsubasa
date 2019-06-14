import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MNationalityComponent,
  MNationalityDetailComponent,
  MNationalityUpdateComponent,
  MNationalityDeletePopupComponent,
  MNationalityDeleteDialogComponent,
  mNationalityRoute,
  mNationalityPopupRoute
} from './';

const ENTITY_STATES = [...mNationalityRoute, ...mNationalityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MNationalityComponent,
    MNationalityDetailComponent,
    MNationalityUpdateComponent,
    MNationalityDeleteDialogComponent,
    MNationalityDeletePopupComponent
  ],
  entryComponents: [
    MNationalityComponent,
    MNationalityUpdateComponent,
    MNationalityDeleteDialogComponent,
    MNationalityDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMNationalityModule {}
