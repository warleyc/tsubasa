import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MConstantComponent,
  MConstantDetailComponent,
  MConstantUpdateComponent,
  MConstantDeletePopupComponent,
  MConstantDeleteDialogComponent,
  mConstantRoute,
  mConstantPopupRoute
} from './';

const ENTITY_STATES = [...mConstantRoute, ...mConstantPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MConstantComponent,
    MConstantDetailComponent,
    MConstantUpdateComponent,
    MConstantDeleteDialogComponent,
    MConstantDeletePopupComponent
  ],
  entryComponents: [MConstantComponent, MConstantUpdateComponent, MConstantDeleteDialogComponent, MConstantDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMConstantModule {}
