import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDistributeCardParameterStepComponent,
  MDistributeCardParameterStepDetailComponent,
  MDistributeCardParameterStepUpdateComponent,
  MDistributeCardParameterStepDeletePopupComponent,
  MDistributeCardParameterStepDeleteDialogComponent,
  mDistributeCardParameterStepRoute,
  mDistributeCardParameterStepPopupRoute
} from './';

const ENTITY_STATES = [...mDistributeCardParameterStepRoute, ...mDistributeCardParameterStepPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDistributeCardParameterStepComponent,
    MDistributeCardParameterStepDetailComponent,
    MDistributeCardParameterStepUpdateComponent,
    MDistributeCardParameterStepDeleteDialogComponent,
    MDistributeCardParameterStepDeletePopupComponent
  ],
  entryComponents: [
    MDistributeCardParameterStepComponent,
    MDistributeCardParameterStepUpdateComponent,
    MDistributeCardParameterStepDeleteDialogComponent,
    MDistributeCardParameterStepDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDistributeCardParameterStepModule {}
