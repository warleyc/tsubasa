import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMatchEnvironmentComponent,
  MMatchEnvironmentDetailComponent,
  MMatchEnvironmentUpdateComponent,
  MMatchEnvironmentDeletePopupComponent,
  MMatchEnvironmentDeleteDialogComponent,
  mMatchEnvironmentRoute,
  mMatchEnvironmentPopupRoute
} from './';

const ENTITY_STATES = [...mMatchEnvironmentRoute, ...mMatchEnvironmentPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMatchEnvironmentComponent,
    MMatchEnvironmentDetailComponent,
    MMatchEnvironmentUpdateComponent,
    MMatchEnvironmentDeleteDialogComponent,
    MMatchEnvironmentDeletePopupComponent
  ],
  entryComponents: [
    MMatchEnvironmentComponent,
    MMatchEnvironmentUpdateComponent,
    MMatchEnvironmentDeleteDialogComponent,
    MMatchEnvironmentDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMatchEnvironmentModule {}
