import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionComponent,
  MActionDetailComponent,
  MActionUpdateComponent,
  MActionDeletePopupComponent,
  MActionDeleteDialogComponent,
  mActionRoute,
  mActionPopupRoute
} from './';

const ENTITY_STATES = [...mActionRoute, ...mActionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionComponent,
    MActionDetailComponent,
    MActionUpdateComponent,
    MActionDeleteDialogComponent,
    MActionDeletePopupComponent
  ],
  entryComponents: [MActionComponent, MActionUpdateComponent, MActionDeleteDialogComponent, MActionDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionModule {}
