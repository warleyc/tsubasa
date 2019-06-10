import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDistributeCardParameterComponent,
  MDistributeCardParameterDetailComponent,
  MDistributeCardParameterUpdateComponent,
  MDistributeCardParameterDeletePopupComponent,
  MDistributeCardParameterDeleteDialogComponent,
  mDistributeCardParameterRoute,
  mDistributeCardParameterPopupRoute
} from './';

const ENTITY_STATES = [...mDistributeCardParameterRoute, ...mDistributeCardParameterPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDistributeCardParameterComponent,
    MDistributeCardParameterDetailComponent,
    MDistributeCardParameterUpdateComponent,
    MDistributeCardParameterDeleteDialogComponent,
    MDistributeCardParameterDeletePopupComponent
  ],
  entryComponents: [
    MDistributeCardParameterComponent,
    MDistributeCardParameterUpdateComponent,
    MDistributeCardParameterDeleteDialogComponent,
    MDistributeCardParameterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDistributeCardParameterModule {}
